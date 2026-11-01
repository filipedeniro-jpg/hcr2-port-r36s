#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <sys/mman.h>

#include "hotpatches_arm64.h"
#include "so_util.h"

extern so_module game_mod;

// Trampolim de 8 instruções ARM64 (32 bytes) para a função GetGlyphName (0x00972404)
static uint32_t s_trampolim_glyph[8] __attribute__((aligned(8)));

typedef struct {
  char bytes[24];
} StdString24;

typedef StdString24 (*fn_glyph_name_orig)(void *glyph, int w1, int w2);

static StdString24 hook_get_glyph_name(void *glyph, int w1, int w2) {
  StdString24 vazio;
  memset(&vazio, 0, sizeof(vazio));
  if (!glyph) {
    return vazio; // Retorna std::string vazio limpo se o glifo for nulo
  }
  fn_glyph_name_orig fn_orig = (fn_glyph_name_orig)(void *)s_trampolim_glyph;
  return fn_orig(glyph, w1, w2);
}

void patch_validacao_savegame_offline(void) {
  enum { OFFSET_VALIDACAO = 0x533440 };
  if (game_mod.load_size >= OFFSET_VALIDACAO + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_VALIDACAO);
    printf("[HCR2] Aplicando bypass de validacao de savegame offline em 0x%x (0x%08x -> 0x140002da)\n",
           OFFSET_VALIDACAO, *site);
    *site = 0x140002dau; // b #0x533fa8
  }

  static const uint32_t checagens_servidor[] = { 0x53c680, 0x53c6a0 };
  for (size_t i = 0; i < sizeof(checagens_servidor)/sizeof(checagens_servidor[0]); i++) {
    uint32_t off = checagens_servidor[i];
    if (game_mod.load_size >= off + 4) {
      uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + off);
      printf("[HCR2] Blindando despachador de savegame em 0x%x (0x%08x -> NOP)\n", off, *site);
      *site = 0xd503201fu; // NOP
    }
  }
}

void patch_blindagem_veiculos_garagem(void) {
  // Blindagem preventiva contra ponteiro de Sprite nulo na montagem de veículos na Garagem
  if (game_mod.load_size >= 0x00C68368 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68368);
    printf("[HCR2] Blindagem chassis veiculo 0xc68368 (0x%08x -> cbz x0, #0xc68944 / 0xb4002ee0)\n", *site);
    *site = 0xb4002ee0u;
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= 0x00C68D00 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68D00);
    printf("[HCR2] Blindagem roda/suspensao veiculo 0xc68d00 (0x%08x -> cbz x0, #0xc68de4 / 0xb4000720)\n", *site);
    *site = 0xb4000720u;
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= 0x00C68EEC + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68EEC);
    printf("[HCR2] Blindagem tuning/acessorios veiculo 0xc68eec (0x%08x -> cbz x0, #0xc68fb4 / 0xb4000640)\n", *site);
    *site = 0xb4000640u;
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  fflush(stdout);
}

void patch_blindagem_texto_fontes(void) {
  // Blindagem de glifo ausente no font cache: ignora glifo nulo em vez de crashar em 0x97243c
  if (game_mod.load_size >= 0x00A7CE34 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00A7CE34);
    printf("[HCR2] Blindando loop de fontes em 0xa7ce34 (0x%08x -> cbz x0, #0xa7ce70 / 0xb40001e0)\n", *site);
    *site = 0xb40001e0u;
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  fflush(stdout);
}

void patch_blindagem_universal_glifos(void) {
  enum { OFFSET_GLYPH = 0x00972404 };
  if (game_mod.load_size >= OFFSET_GLYPH + 16) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_GLYPH);
    printf("[HCR2] Instalando blindagem universal de glifos em 0x%x...\n", OFFSET_GLYPH);

    // 1. Monta o trampolim copiando as 4 instruções originais de 0x00972404
    s_trampolim_glyph[0] = site[0]; // stp x22, x21, [sp, #-0x30]!
    s_trampolim_glyph[1] = site[1]; // stp x20, x19, [sp, #0x10]
    s_trampolim_glyph[2] = site[2]; // stp x29, x30, [sp, #0x20]
    s_trampolim_glyph[3] = site[3]; // add x29, sp, #0x20

    // Salto do trampolim para 0x00972414 (continuação da função)
    s_trampolim_glyph[4] = 0x58000050u; // ldr x16, [pc, #8]
    s_trampolim_glyph[5] = 0xd61f0200u; // br x16
    *(uint64_t *)(&s_trampolim_glyph[6]) = (uint64_t)((uintptr_t)game_mod.load_base + 0x00972414);

    // Torna a página do trampolim executável
    uintptr_t pag = (uintptr_t)s_trampolim_glyph & ~(uintptr_t)0xfff;
    mprotect((void *)pag, 0x2000, PROT_READ | PROT_WRITE | PROT_EXEC);
    __builtin___clear_cache((char *)s_trampolim_glyph, (char *)s_trampolim_glyph + sizeof(s_trampolim_glyph));

    // 2. Instala o gancho (hook) no início de 0x00972404 desviando para hook_get_glyph_name
    site[0] = 0x58000050u; // ldr x16, [pc, #8]
    site[1] = 0xd61f0200u; // br x16
    *(uint64_t *)(&site[2]) = (uint64_t)(uintptr_t)&hook_get_glyph_name;
    __builtin___clear_cache((char *)site, (char *)site + 16);

    printf("[HCR2] Blindagem universal de glifos ativa com sucesso (50 chamadores protegidos)!\n");
    fflush(stdout);
  }
}

void aplicar_hotpatches_arm64(void) {
  patch_validacao_savegame_offline();
  patch_blindagem_veiculos_garagem();
  patch_blindagem_texto_fontes();
  patch_blindagem_universal_glifos();
}

void patch_ativar_transicao_garagem_imediata(void) {
  enum {
    OFFSET_SPLASH_TIMER     = 0x00A1D794,
    OFFSET_SPLASH_GARAGEM   = 0x00A1D834,
    OFFSET_SPLASH_CHALLENGE = 0x00A1D938,
    OFFSET_SPLASH_PENDING   = 0x00A1D9D4
  };
  if (game_mod.load_size >= OFFSET_SPLASH_TIMER + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_TIMER);
    if (*site != 0xd503201fu) {
      printf("[HCR2] Neutralizando trava de timer em 0x%x (0x%08x -> NOP)\n",
             OFFSET_SPLASH_TIMER, *site);
      *site = 0xd503201fu; // NOP
      __builtin___clear_cache((char *)site, (char *)site + 4);
    }
  }
  if (game_mod.load_size >= OFFSET_SPLASH_GARAGEM + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_GARAGEM);
    if (*site != 0xd503201fu) {
      printf("[HCR2] Destravando transicao para Garagem em 0x%x (0x%08x -> NOP)\n",
             OFFSET_SPLASH_GARAGEM, *site);
      *site = 0xd503201fu; // NOP
      __builtin___clear_cache((char *)site, (char *)site + 4);
    }
  }
  if (game_mod.load_size >= OFFSET_SPLASH_CHALLENGE + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_CHALLENGE);
    // 0x14000060u = b #0x0a1dab8 (salta direto para a carga da Garagem)
    if (*site != 0x14000060u) {
      printf("[HCR2] Desviando challenge de rede para Garagem em 0x%x (0x%08x -> b #0xa1dab8)\n",
             OFFSET_SPLASH_CHALLENGE, *site);
      *site = 0x14000060u;
      __builtin___clear_cache((char *)site, (char *)site + 4);
    }
  }
  if (game_mod.load_size >= OFFSET_SPLASH_PENDING + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_PENDING);
    if (*site != 0xd503201fu) {
      printf("[HCR2] Neutralizando pendencias de rede em 0x%x (0x%08x -> NOP)\n",
             OFFSET_SPLASH_PENDING, *site);
      *site = 0xd503201fu; // NOP
      __builtin___clear_cache((char *)site, (char *)site + 4);
    }
  }
  fflush(stdout);
}

