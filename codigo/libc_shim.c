/* libc_shim.c -- bionic-compatible libc wrappers for libjniproxy.so
 *
 * Where the bionic and newlib ABIs differ (struct layouts, flag values, missing
 * functions) we provide converting wrappers here; everything that matches is
 * passed straight through from imports.c.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

#define _GNU_SOURCE

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <stdarg.h>
#include <string.h>
#include <math.h>
#include <errno.h>
#include <malloc.h>
#include <limits.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <pthread.h>
#include <switch.h>

#include "config.h"
#include "libc_shim.h"

// NULL-source-tolerant memcpy/memmove. VP blits sprite data into its emulated
// PS1 VRAM via XpPs1VRam::loadImage2, which memmove()s from an image-data pointer
// that is sometimes NULL (a Camp graphic whose data never loaded). On Android the
// call happened to survive; on newlib memmove(dst, NULL, n) faults. A NULL src (or
// dst) is always a bug, so treat it as a Operacao nula (operacao nula (no-op)) instead of crashing -- the sprite
// is skipped, the scene proceeds. n==0 is also a safe Operacao nula (operacao nula (no-op)).
// A src/dst in the first 64KB of address space is never a real pointer (heap and
// modules live far higher); such a memcpy/memmove is a bug (NULL or a garbage
// low value like 0x20 -- e.g. loadImage2 blitting from an uninitialized Camp
// image-data pointer). Skip it instead of faulting.
#define BOGUS_PTR(p) ((uintptr_t)(p) < 0x10000u)
void *memcpy_fake(void *dst, const void *src, size_t n) {
  if (n == 0 || BOGUS_PTR(dst) || BOGUS_PTR(src)) return dst;
  return memcpy(dst, src, n);
}
void *memmove_fake(void *dst, const void *src, size_t n) {
  if (n == 0 || BOGUS_PTR(dst) || BOGUS_PTR(src)) return dst;
  return memmove(dst, src, n);
}

// fortify (_chk) wrappers: ignore the object-size argument
void *__memcpy_chk_fake(void *dst, const void *src, size_t n, size_t dstlen) {
  (void)dstlen; return memcpy_fake(dst, src, n);
}
void *__memmove_chk_fake(void *dst, const void *src, size_t n, size_t dstlen) {
  (void)dstlen; return memmove_fake(dst, src, n);
}
char *__strcat_chk_fake(char *dst, const char *src, size_t dstlen) {
  (void)dstlen; return strcat(dst, src);
}
char *__strcpy_chk_fake(char *dst, const char *src, size_t dstlen) {
  (void)dstlen; return strcpy(dst, src);
}
size_t __strlen_chk_fake(const char *s, size_t slen) {
  (void)slen; return strlen(s);
}
int __vsnprintf_chk_fake(char *s, size_t maxlen, int flag, size_t slen, const char *fmt, va_list va) {
  (void)flag; (void)slen; return vsnprintf(s, maxlen, fmt, va);
}
int __vsprintf_chk_fake(char *s, int flag, size_t slen, const char *fmt, va_list va) {
  (void)flag; (void)slen; return vsprintf(s, fmt, va);
}

// misc bionic functions
int __system_property_get_fake(const char *name, char *value) {
  (void)name; value[0] = '\0'; return 0;
}

unsigned long getauxval_fake(unsigned long type) {
  (void)type; return 0;
}

#include <sys/syscall.h>
#include <unistd.h>

int gettid_fake(void) {
  return (int)syscall(SYS_gettid);
}

long syscall_fake(long number, ...) {
  va_list args;
  va_start(args, number);
  long a1 = va_arg(args, long);
  long a2 = va_arg(args, long);
  long a3 = va_arg(args, long);
  long a4 = va_arg(args, long);
  long a5 = va_arg(args, long);
  long a6 = va_arg(args, long);
  va_end(args);
  return syscall(number, a1, a2, a3, a4, a5, a6);
}

void sincosf_fake(float x, float *s, float *c) {
  *s = sinf(x); *c = cosf(x);
}

void android_set_abort_message_fake(const char *msg) {
  (void)msg;
}

int posix_memalign_fake(void **out, size_t align, size_t size) {
  void *p = memalign(align, size);
  if (!p) return ENOMEM;
  *out = p;
  return 0;
}

// ---------------------------------------------------------------------------
// stdio over the fake bionic __sF (stdin/stdout/stderr). libc++_shared inits
// std::cout/cerr against &__sF[1]/&__sF[2]; these wrappers absorb those and
// forward everything else to real FILEs.
// ---------------------------------------------------------------------------

uint8_t fake_sF[3][0x100];

#define APK_STREAM_MAX 8
#define APK_CACHE_WAYS 4
#define APK_CACHE_BYTES (512u * 1024u)
#define APK_SHARED_CACHE_WAYS 64
#define APK_TAIL_CACHE_BYTES (2u * 1024u * 1024u)

typedef struct {
  uint8_t *data;
  int64_t off;
  size_t len;
  uint64_t age;
} ApkCacheWay;

typedef struct {
  FILE *f;
  int cache_fd;
  int64_t pos;
  int64_t size;
  int error;
  int passthrough;
  uint64_t age;
  ApkCacheWay way[APK_CACHE_WAYS];
} ApkStream;
static ApkStream g_apk_streams[APK_STREAM_MAX];

// Misses from the per-stream caches used to go straight back to SD. The
// logs show the same 512 KiB regions being evicted and re-read every few ms,
// often by multiple APK FILE* streams. Keep a shared 32 MiB LRU behind those
// stream-local ways. It is only locked on a local-cache miss, not on millions
// of byte-sized logical fread() calls.
typedef struct {
  uint8_t *data;
  int64_t off;
  int64_t file_size;
  size_t len;
  uint64_t age;
} ApkSharedBlock;
static ApkSharedBlock g_apk_shared[APK_SHARED_CACHE_WAYS];
static uint64_t g_apk_shared_age;
static pthread_mutex_t g_apk_shared_mutex = PTHREAD_MUTEX_INITIALIZER;

/* The ZIP central directory lives at the end of the APK. Cocos/minizip opens
 * the APK again for many individual assets and linearly walks that directory
 * each time. Keep the tail shared across all APK streams so those repeated
 * walks never Retorna to the SD card. The supplied HCR APK has a ~270 KiB
 * central directory, comfortably inside this 2 MiB tail window. */
static uint8_t *g_apk_tail_cache;
static int64_t g_apk_tail_off;
static size_t g_apk_tail_len;
static int64_t g_apk_tail_file_size = -1;
static int g_apk_tail_ready;
static pthread_mutex_t g_apk_tail_mutex = PTHREAD_MUTEX_INITIALIZER;

static ApkStream *apk_stream_find(FILE *f) {
  for (unsigned i = 0; i < APK_STREAM_MAX; ++i)
    if (g_apk_streams[i].f == f) return &g_apk_streams[i];
  return NULL;
}

static void apk_tail_prepare(int fd, int64_t file_size) {
  if (fd < 0 || file_size <= 0) return;
  if (g_apk_tail_ready && g_apk_tail_file_size == file_size) return;
  pthread_mutex_lock(&g_apk_tail_mutex);
  if (!g_apk_tail_ready || g_apk_tail_file_size != file_size) {
    const size_t want = (size_t)(file_size < (int64_t)APK_TAIL_CACHE_BYTES ?
                                 file_size : (int64_t)APK_TAIL_CACHE_BYTES);
    const int64_t off = file_size - (int64_t)want;
    uint8_t *buf = malloc(want);
    size_t got = 0;
    if (buf && lseek(fd, (off_t)off, SEEK_SET) >= 0) {
      while (got < want) {
        const ssize_t rc = read(fd, buf + got, want - got);
        if (rc > 0) { got += (size_t)rc; continue; }
        if (rc < 0 && errno == EINTR) continue;
        break;
      }
    }
    if (got == want) {
      free(g_apk_tail_cache);
      g_apk_tail_cache = buf;
      g_apk_tail_off = off;
      g_apk_tail_len = got;
      g_apk_tail_file_size = file_size;
      __sync_synchronize();
      g_apk_tail_ready = 1;
    } else {
      free(buf);
    }
  }
  pthread_mutex_unlock(&g_apk_tail_mutex);
}

static ApkStream *remember_apk_stream(FILE *f, const char *path) {
  if (!f || !path) return NULL;
  for (unsigned i = 0; i < APK_STREAM_MAX; ++i) {
    ApkStream *a = &g_apk_streams[i];
    if (a->f) continue;
    memset(a, 0, sizeof(*a));
    a->f = f;
    a->cache_fd = open(path, O_RDONLY);
    a->size = -1;
    if (a->cache_fd >= 0) {
      struct stat st;
      if (fstat(a->cache_fd, &st) == 0) a->size = (int64_t)st.st_size;
    }
    if (a->cache_fd >= 0 && a->size > 0) apk_tail_prepare(a->cache_fd, a->size);
    if (a->cache_fd < 0 || a->size < 0) {
      if (a->cache_fd >= 0) close(a->cache_fd);
      a->cache_fd = -1;
      a->passthrough = 1;
      return a;
    }
    for (unsigned w = 0; w < APK_CACHE_WAYS; ++w) {
      a->way[w].data = malloc(APK_CACHE_BYTES);
      a->way[w].off = -1;
      if (!a->way[w].data) {
        for (unsigned j = 0; j < w; ++j) free(a->way[j].data);
        close(a->cache_fd);
        a->cache_fd = -1;
        a->passthrough = 1;
        return a;
      }
    }
    return a;
  }
  return NULL;
}

static void forget_apk_stream(FILE *f) {
  ApkStream *a = apk_stream_find(f);
  if (!a) return;
  if (a->cache_fd >= 0) close(a->cache_fd);
  for (unsigned w = 0; w < APK_CACHE_WAYS; ++w) free(a->way[w].data);
  memset(a, 0, sizeof(*a));
}

static int apk_cache_fill(ApkStream *a, ApkCacheWay *way, int64_t pos) {
  if (!a || !way || a->cache_fd < 0 || !way->data || pos < 0 || pos >= a->size) return -1;
  const int64_t base = pos & ~((int64_t)APK_CACHE_BYTES - 1);
  const size_t want = (size_t)((a->size - base) < (int64_t)APK_CACHE_BYTES ?
                               (a->size - base) : (int64_t)APK_CACHE_BYTES);

  // Second-level shared cache. A local miss that hits here is a RAM copy, not
  // another 6-8 ms SD read. Holding this mutex is cheap because this function
  // only runs after the per-stream cache misses.
  pthread_mutex_lock(&g_apk_shared_mutex);
  ApkSharedBlock *victim = &g_apk_shared[0];
  for (unsigned i = 0; i < APK_SHARED_CACHE_WAYS; ++i) {
    ApkSharedBlock *b = &g_apk_shared[i];
    if (b->data && b->file_size == a->size && b->off == base && b->len >= want) {
      memcpy(way->data, b->data, want);
      b->age = ++g_apk_shared_age;
      way->off = base;
      way->len = want;
      way->age = ++a->age;
      pthread_mutex_unlock(&g_apk_shared_mutex);
      return 0;
    }
    if (!b->data || b->off < 0) victim = b;
    else if (victim->data && victim->off >= 0 && b->age < victim->age) victim = b;
  }

  if (!victim->data) victim->data = malloc(APK_CACHE_BYTES);
  if (victim->data) {
    size_t got = 0;
    if (lseek(a->cache_fd, (off_t)base, SEEK_SET) >= 0) {
      while (got < want) {
        const ssize_t rc = read(a->cache_fd, victim->data + got, want - got);
        if (rc > 0) { got += (size_t)rc; continue; }
        if (rc < 0 && errno == EINTR) continue;
        if (rc < 0) a->error = 1;
        break;
      }
    } else {
      a->error = 1;
    }
    if (got) {
      victim->off = base;
      victim->file_size = a->size;
      victim->len = got;
      victim->age = ++g_apk_shared_age;
      memcpy(way->data, victim->data, got);
      way->off = base;
      way->len = got;
      way->age = ++a->age;
      pthread_mutex_unlock(&g_apk_shared_mutex);
      return 0;
    }
  }
  pthread_mutex_unlock(&g_apk_shared_mutex);

  // Allocation falha contingencia (fallback): preserve correctness even without L2 cache.
  if (lseek(a->cache_fd, (off_t)base, SEEK_SET) < 0) { a->error = 1; return -1; }
  size_t got = 0;
  while (got < want) {
    const ssize_t rc = read(a->cache_fd, way->data + got, want - got);
    if (rc > 0) { got += (size_t)rc; continue; }
    if (rc < 0 && errno == EINTR) continue;
    if (rc < 0) a->error = 1;
    break;
  }
  way->off = base;
  way->len = got;
  way->age = ++a->age;
  return got ? 0 : -1;
}

static ApkCacheWay *apk_cache_way_for(ApkStream *a, int64_t pos) {
  ApkCacheWay *oldest = &a->way[0];
  for (unsigned w = 0; w < APK_CACHE_WAYS; ++w) {
    ApkCacheWay *way = &a->way[w];
    if (way->off >= 0 && pos >= way->off && pos < way->off + (int64_t)way->len) {
      way->age = ++a->age;
      return way;
    }
    if (way->off < 0) oldest = way;
    else if (oldest->off >= 0 && way->age < oldest->age) oldest = way;
  }
  if (apk_cache_fill(a, oldest, pos) != 0) return NULL;
  return oldest;
}

static int is_fake_file(const void *f) {
  if (!f) return 0;
  uintptr_t p = (uintptr_t)f;
  uintptr_t base = (uintptr_t)fake_sF;
  return p >= base && p < base + sizeof(fake_sF);
}

static FILE *get_real_file(FILE *f) {
  if (!f) return NULL;
  uintptr_t p = (uintptr_t)f;
  uintptr_t base = (uintptr_t)fake_sF;
  if (p >= base && p < base + sizeof(fake_sF)) {
    uintptr_t off = p - base;
    if (off >= 0x130) return stderr;
    if (off >= 0x98) return stdout;
    return stdin;
  }
  return f;
}

FILE *fopen_fake(const char *path, const char *mode) {
  FILE *f = fopen(path, mode);
  // The offline Android build normally creates this server-event cache before
  // the shop is opened. On Switch there is no backend fetch, but the native
  // shop loader assumes the cache exists. An vazio JSON object selects its
  // no-event path.
  const char *name = path ? strrchr(path, '/') : NULL;
  if (!f && path && mode && mode[0] == 'r' &&
      !strcmp(name ? name + 1 : path, "gcEventDetails")) {
    f = fopen(path, "w+");
    if (f) {
      fputs("{}", f);
      rewind(f);
    }
  }

  const int apk = f && path && mode && mode[0] == 'r' &&
                  !strcmp(name ? name + 1 : path, GAME_APK);
  if (apk) {
    setvbuf(f, NULL, _IOFBF, 32u * 1024u);
    remember_apk_stream(f, path);
  }
  return f;
}

size_t fwrite_fake(const void *ptr, size_t size, size_t n, FILE *f) {
  f = get_real_file(f);
  if (!f) return 0;
  return fwrite(ptr, size, n, f);
}

size_t fread_fake(void *ptr, size_t size, size_t n, FILE *f) {
  f = get_real_file(f);
  if (!f || !size || !n) return 0;
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough && apk->cache_fd >= 0) {
    if (n > SIZE_MAX / size) { errno = EOVERFLOW; apk->error = 1; return 0; }
    const size_t requested = size * n;
    size_t done = 0;
    while (done < requested && apk->pos < apk->size) {
      if (g_apk_tail_ready && g_apk_tail_file_size == apk->size &&
          apk->pos >= g_apk_tail_off &&
          apk->pos < g_apk_tail_off + (int64_t)g_apk_tail_len) {
        const size_t in_tail = (size_t)(apk->pos - g_apk_tail_off);
        size_t avail = g_apk_tail_len - in_tail;
        if (avail > requested - done) avail = requested - done;
        memcpy((uint8_t *)ptr + done, g_apk_tail_cache + in_tail, avail);
        apk->pos += (int64_t)avail;
        done += avail;
        continue;
      }
      ApkCacheWay *way = apk_cache_way_for(apk, apk->pos);
      if (!way) break;
      const size_t in_way = (size_t)(apk->pos - way->off);
      if (in_way >= way->len) break;
      size_t avail = way->len - in_way;
      if (avail > requested - done) avail = requested - done;
      memcpy((uint8_t *)ptr + done, way->data + in_way, avail);
      apk->pos += (int64_t)avail;
      done += avail;
    }
    return done / size;
  }

  return fread(ptr, size, n, f);
}

int fputc_fake(int c, FILE *f) {
  f = get_real_file(f);
  return f ? fputc(c, f) : c;
}

char *fgets_fake(char *s, int n, FILE *f) {
  f = get_real_file(f);
  return f ? fgets(s, n, f) : NULL;
}

int fputs_fake(const char *s, FILE *f) {
  f = get_real_file(f);
  return f ? fputs(s, f) : 0;
}

int ferror_fake(FILE *f) {
  f = get_real_file(f);
  if (!f) return 0;
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough) return apk->error;
  return ferror(f);
}

void clearerr_fake(FILE *f) {
  f = get_real_file(f);
  if (!f) return;
  ApkStream *apk = apk_stream_find(f);
  if (apk) apk->error = 0;
  clearerr(f);
}

int fileno_fake(FILE *f) {
  f = get_real_file(f);
  if (!f) { errno = EBADF; return -1; }
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough) {
    /* A caller asking for the raw fd may subsequently bypass our stdio shim.
     * Synchronize the real FILE position and permanently fall back for this
     * stream so mixed fd/FILE access stays correct. */
    if (fseek(f, (long)apk->pos, SEEK_SET) != 0) apk->error = 1;
    apk->passthrough = 1;
  }
  return fileno(f);
}

int fflush_fake(FILE *f) {
  // POSIX fflush(NULL) flushes all output streams. Silently discarding that
  // request can lose native file-backed state on lifecycle transitions.
  if (f == NULL) return fflush(NULL);
  if (is_fake_file(f)) return 0;
  return fflush(f);
}

int fclose_fake(FILE *f) {
  if (is_fake_file(f)) return 0;
  forget_apk_stream(f);
  return fclose(f);
}

int fseek_fake(FILE *f, long off, int whence) {
  if (is_fake_file(f)) return -1;
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough && apk->cache_fd >= 0) {
    int64_t base;
    if (whence == SEEK_SET) base = 0;
    else if (whence == SEEK_CUR) base = apk->pos;
    else if (whence == SEEK_END) base = apk->size;
    else { errno = EINVAL; apk->error = 1; return -1; }
    if ((off > 0 && base > INT64_MAX - (int64_t)off) ||
        (off < 0 && base < INT64_MIN - (int64_t)off)) {
      errno = EOVERFLOW; apk->error = 1; return -1;
    }
    const int64_t target = base + (int64_t)off;
    if (target < 0) { errno = EINVAL; apk->error = 1; return -1; }
    apk->pos = target;
    return 0;
  }
  return fseek(f, off, whence);
}

long ftell_fake(FILE *f) {
  if (is_fake_file(f)) return 0;
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough && apk->cache_fd >= 0) return (long)apk->pos;
  return ftell(f);
}

void rewind_fake(FILE *f) {
  if (is_fake_file(f)) return;
  ApkStream *apk = apk_stream_find(f);
  if (apk && !apk->passthrough && apk->cache_fd >= 0) {
    apk->pos = 0; apk->error = 0; return;
  }
  rewind(f);
}

int fprintf_fake(FILE *f, const char *fmt, ...) {
  f = get_real_file(f);
  if (!f) return 0;
  va_list va;
  va_start(va, fmt);
  int ret = vfprintf(f, fmt, va);
  va_end(va);
  return ret;
}

int vfprintf_fake(FILE *f, const char *fmt, va_list va) {
  f = get_real_file(f);
  if (!f) return 0;
  return vfprintf(f, fmt, va);
}

int getc_fake(FILE *f) {
  f = get_real_file(f);
  if (!f) return EOF;
  return getc(f);
}

int ungetc_fake(int c, FILE *f) {
  f = get_real_file(f);
  if (!f) return EOF;
  return ungetc(c, f);
}

