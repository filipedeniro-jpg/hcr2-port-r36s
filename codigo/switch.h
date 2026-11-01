#ifndef __SWITCH_COMPAT_H__
#define __SWITCH_COMPAT_H__

#include <stdint.h>
#include <stddef.h>
#include <stdbool.h>
#include <time.h>
#include <unistd.h>

typedef uint8_t  u8;
typedef uint16_t u16;
typedef uint32_t u32;
typedef uint64_t u64;

typedef int8_t   s8;
typedef int16_t  s16;
typedef int32_t  s32;
typedef int64_t  s64;

typedef uint32_t Result;
#define R_SUCCEEDED(res) ((res) == 0)
#define R_FAILED(res)    ((res) != 0)

static inline uint64_t armGetSystemTick(void) {
  struct timespec ts;
  clock_gettime(CLOCK_MONOTONIC, &ts);
  return (uint64_t)ts.tv_sec * 1000000000ULL + (uint64_t)ts.tv_nsec;
}

static inline uint64_t armTicksToNs(uint64_t ticks) {
  return ticks;
}

static inline void armDCacheFlush(void *addr, size_t size) {
  __builtin___clear_cache((char *)addr, (char *)addr + size);
}

static inline void armICacheInvalidate(void *addr, size_t size) {
  __builtin___clear_cache((char *)addr, (char *)addr + size);
}

static inline void consoleInit(void *p) { (void)p; }
static inline void consoleClear(void) {}
static inline void consoleUpdate(void *p) { (void)p; }
static inline void consoleExit(void *p) { (void)p; }

void tls_setup_guard(void);

#include <pthread.h>
typedef pthread_mutex_t Mutex;
#define mutexInit(m)   pthread_mutex_init((m), NULL)
#define mutexLock(m)   pthread_mutex_lock(m)
#define mutexUnlock(m) pthread_mutex_unlock(m)

#endif
