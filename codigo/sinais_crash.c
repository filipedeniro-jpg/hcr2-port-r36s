#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <ucontext.h>

#include "sinais_crash.h"
#include "so_util.h"
#include "jni_fake.h"

extern so_module game_mod;

static void *s_fake_env = NULL;
static void *s_renderer_cls = NULL;
static void (*s_nativeOnPause)(void *env, void *cls) = NULL;
static void (*s_sdl_gl_deinit)(void) = NULL;

void configurar_shutdown_hooks(void *env, void *renderer, void (*on_pause)(void*, void*), void (*gl_deinit)(void)) {
  s_fake_env = env;
  s_renderer_cls = renderer;
  s_nativeOnPause = on_pause;
  s_sdl_gl_deinit = gl_deinit;
}

static void crash_handler(int sig, siginfo_t *info, void *uctx) {
  ucontext_t *uc = (ucontext_t *)uctx;
  uintptr_t pc = 0, lr = 0, sp = 0, fp = 0;
#if defined(__aarch64__)
  pc = (uintptr_t)uc->uc_mcontext.pc;
  lr = (uintptr_t)uc->uc_mcontext.regs[30];
  fp = (uintptr_t)uc->uc_mcontext.regs[29];
  sp = (uintptr_t)uc->uc_mcontext.sp;
#endif
  fprintf(stderr, "\n==================== CRASH ====================\n");
  fprintf(stderr, "Signal: %d (%s)\n", sig, strsignal(sig));
  fprintf(stderr, "Fault Address: %p\n", info->si_addr);
  fprintf(stderr, "PC: 0x%lx  LR: 0x%lx  SP: 0x%lx  FP: 0x%lx\n",
          (unsigned long)pc, (unsigned long)lr, (unsigned long)sp, (unsigned long)fp);
#if defined(__aarch64__)
  for (int i = 0; i < 30; i += 2) {
    fprintf(stderr, "x%02d: 0x%016lx  x%02d: 0x%016lx\n",
            i, (unsigned long)uc->uc_mcontext.regs[i],
            i + 1, (unsigned long)uc->uc_mcontext.regs[i + 1]);
  }
#endif
  if (game_mod.load_base) {
    fprintf(stderr, "libcocos2dcpp.so base: %p..%p (rel PC: 0x%lx, rel LR: 0x%lx)\n",
            game_mod.load_base, (char*)game_mod.load_base + game_mod.load_size,
            (unsigned long)(pc - (uintptr_t)game_mod.load_base),
            (unsigned long)(lr - (uintptr_t)game_mod.load_base));
    if (pc >= (uintptr_t)game_mod.load_base &&
        pc + 16 < (uintptr_t)game_mod.load_base + game_mod.load_size) {
      fprintf(stderr, "--- Code around PC ---\n");
      uint32_t *code = (uint32_t *)pc;
      for (int i = -3; i <= 3; i++) {
        fprintf(stderr, "  %s [0x%lx]: 0x%08x\n", (i == 0) ? "=>" : "  ",
                (unsigned long)(pc + i * 4 - (uintptr_t)game_mod.load_base), code[i]);
      }
    }
  }

  // Frame pointer backtrace walk
  if (fp) {
    fprintf(stderr, "\n--- Call Stack (Frame Pointer Walk) ---\n");
    uintptr_t curr_fp = fp;
    int depth = 0;
    while (curr_fp && depth < 32) {
      uintptr_t *fp_ptr = (uintptr_t *)curr_fp;
      if (curr_fp < sp || curr_fp > sp + 0x200000) break;
      uintptr_t ret_addr = fp_ptr[1];
      uintptr_t next_fp = fp_ptr[0];
      fprintf(stderr, "  #%02d: 0x%016lx (FP: 0x%016lx)\n", depth++, (unsigned long)ret_addr, (unsigned long)curr_fp);
      if (next_fp <= curr_fp) break;
      curr_fp = next_fp;
    }
  }

  // Dump stack words
  if (sp) {
    fprintf(stderr, "\n--- Stack Dump (SP=%p) ---\n", (void*)sp);
    uintptr_t *sptr = (uintptr_t *)sp;
    for (int i = 0; i < 64; i++) {
      fprintf(stderr, "sp[%02d]: 0x%016lx\n", i, (unsigned long)sptr[i]);
    }
  }

  // Dump /proc/self/maps
  FILE *maps = fopen("/proc/self/maps", "r");
  if (maps) {
    fprintf(stderr, "\n--- Memory Maps ---\n");
    char line[512];
    while (fgets(line, sizeof(line), maps)) {
      fprintf(stderr, "%s", line);
    }
    fclose(maps);
  }
  fprintf(stderr, "===============================================\n");
  fflush(stdout);
  fflush(stderr);
  _exit(128 + sig);
}

static void graceful_shutdown_handler(int sig) {
  printf("[HCR] Sinal %d recebido, salvando progresso e encerrando com seguranca...\n", sig);
  fflush(stdout);
  if (s_fake_env && s_renderer_cls && s_nativeOnPause) {
    s_nativeOnPause(s_fake_env, s_renderer_cls);
  }
  jni_flush_prefs();
  sync();
  if (s_sdl_gl_deinit) s_sdl_gl_deinit();
  FILE *fb = fopen("/sys/class/graphics/fb0/blank", "w");
  if (fb) { fputs("0\n", fb); fclose(fb); }
  printf("[HCR] Encerramento limpo concluido com sucesso. Retornando ao ArkOS...\n");
  fflush(stdout);
  _exit(0);
}

void install_signal_handlers(void) {
  struct sigaction sa;
  memset(&sa, 0, sizeof(sa));
  sa.sa_sigaction = crash_handler;
  sa.sa_flags = SA_SIGINFO;
  sigaction(SIGSEGV, &sa, NULL);
  sigaction(SIGBUS, &sa, NULL);
  sigaction(SIGILL, &sa, NULL);
  sigaction(SIGABRT, &sa, NULL);
  sigaction(SIGFPE, &sa, NULL);

  struct sigaction sa_term;
  memset(&sa_term, 0, sizeof(sa_term));
  sa_term.sa_handler = graceful_shutdown_handler;
  sigaction(SIGTERM, &sa_term, NULL);
  sigaction(SIGINT, &sa_term, NULL);
  sigaction(SIGHUP, &sa_term, NULL);
}
