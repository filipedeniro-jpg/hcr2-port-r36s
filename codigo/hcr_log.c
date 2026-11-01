#define _POSIX_C_SOURCE 200809L
#include "hcr_log.h"

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>
#include <errno.h>
#include <stdint.h>

#define HCR_LOG_FILE "hcr_nx.log"
#define HCR_LOG_PREV "hcr_nx_prev.log"
#define HCR_LOG_RING 1024
#define HCR_LOG_LINE 768

typedef struct { char text[HCR_LOG_LINE]; } LogLine;
static LogLine g_ring[HCR_LOG_RING];
static unsigned g_head, g_tail, g_count;
static unsigned g_dropped;
static int g_running, g_started, g_writer_busy;
static FILE *g_file;
static pthread_t g_thread;
static pthread_mutex_t g_lock = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t g_cond = PTHREAD_COND_INITIALIZER;
static uint64_t g_t0_us;

uint64_t hcr_time_us(void) {
  struct timespec ts;
  clock_gettime(CLOCK_MONOTONIC, &ts);
  return (uint64_t)ts.tv_sec * 1000000ull + (uint64_t)ts.tv_nsec / 1000ull;
}

static void write_line(const char *s) {
  if (!g_file || !s) return;
  fputs(s, g_file);
  fputc('\n', g_file);
}

static void *log_worker(void *unused) {
  (void)unused;
  uint64_t last_flush_us = hcr_time_us();
  for (;;) {
    char line[HCR_LOG_LINE];
    pthread_mutex_lock(&g_lock);
    while (!g_count && g_running)
      pthread_cond_wait(&g_cond, &g_lock);
    if (!g_count && !g_running) {
      pthread_mutex_unlock(&g_lock);
      break;
    }
    memcpy(line, g_ring[g_tail].text, sizeof(line));
    line[sizeof(line)-1] = 0;
    g_tail = (g_tail + 1) % HCR_LOG_RING;
    g_count--;
    g_writer_busy = 1;
    pthread_mutex_unlock(&g_lock);

    write_line(line);

    pthread_mutex_lock(&g_lock);
    g_writer_busy = 0;
    /* Flush only when caught up.  This keeps crash/debug logs useful without
     * making render/loader threads wait on SD writes. */
    const int drained = (g_count == 0);
    pthread_cond_broadcast(&g_cond);
    pthread_mutex_unlock(&g_lock);
    if (drained && g_file) {
      const uint64_t now = hcr_time_us();
      /* Do not turn the diagnostic logger into an SD-card benchmark. Native
       * Android logs can arrive one line at a time; flushing each line would
       * itself create the stalls we are trying to measure. PROFILE emits at
       * least once per second, and explicit/fatal flushes remain immediate. */
      if (now - last_flush_us >= 250000) {
        fflush(g_file);
        last_flush_us = now;
      }
    }
  }
  if (g_file) fflush(g_file);
  return NULL;
}

void hcr_log_init(void) {
  pthread_mutex_lock(&g_lock);
  if (g_started) { pthread_mutex_unlock(&g_lock); return; }
  g_started = 1;
  pthread_mutex_unlock(&g_lock);

  remove(HCR_LOG_PREV);
  rename(HCR_LOG_FILE, HCR_LOG_PREV);
  g_file = fopen(HCR_LOG_FILE, "wb");
  if (!g_file) return;
  setvbuf(g_file, NULL, _IOFBF, 128u * 1024u);
  g_t0_us = hcr_time_us();
  g_running = 1;
  if (pthread_create(&g_thread, NULL, log_worker, NULL) != 0) {
    g_running = 0;
    return; /* hcr_logf falls back to synchronous writes */
  }
  HLOGI("BOOT", "logger started cwd-relative file=%s", HCR_LOG_FILE);
}

void hcr_logf(const char *level, const char *tag, const char *fmt, ...) {
  if (!g_file || !fmt) return;
  char payload[560];
  va_list va;
  va_start(va, fmt);
  vsnprintf(payload, sizeof(payload), fmt, va);
  va_end(va);
  payload[sizeof(payload)-1] = 0;

  const uint64_t now = hcr_time_us();
  const uint64_t ms = g_t0_us ? (now - g_t0_us) / 1000ull : 0;
  const unsigned long tid = (unsigned long)(uintptr_t)pthread_self();
  char line[HCR_LOG_LINE];
  snprintf(line, sizeof(line), "[%8llu.%03llu] [%s] [%s] [t%lx] %s",
           (unsigned long long)(ms / 1000ull),
           (unsigned long long)(ms % 1000ull),
           level ? level : "?", tag ? tag : "?", tid, payload);
  line[sizeof(line)-1] = 0;

  pthread_mutex_lock(&g_lock);
  if (g_running) {
    if (g_count == HCR_LOG_RING) {
      g_dropped++;
      pthread_mutex_unlock(&g_lock);
      return;
    }
    if (g_dropped) {
      char dropped[HCR_LOG_LINE];
      snprintf(dropped, sizeof(dropped), "[%8llu.%03llu] [W] [LOGGER] [t%lx] dropped %u log lines (queue full)",
               (unsigned long long)(ms / 1000ull),
               (unsigned long long)(ms % 1000ull), tid, g_dropped);
      if (g_count < HCR_LOG_RING) {
        snprintf(g_ring[g_head].text, sizeof(g_ring[g_head].text), "%s", dropped);
        g_head = (g_head + 1) % HCR_LOG_RING;
        g_count++;
      }
      g_dropped = 0;
    }
    if (g_count < HCR_LOG_RING) {
      snprintf(g_ring[g_head].text, sizeof(g_ring[g_head].text), "%s", line);
      g_head = (g_head + 1) % HCR_LOG_RING;
      g_count++;
      pthread_cond_signal(&g_cond);
    }
    pthread_mutex_unlock(&g_lock);
    return;
  }

  /* Writer thread unavailable: correctness/debug visibility beats speed. */
  write_line(line);
  fflush(g_file);
  pthread_mutex_unlock(&g_lock);
}

void hcr_log_flush(void) {
  if (!g_file) return;
  pthread_mutex_lock(&g_lock);
  while (g_running && (g_count || g_writer_busy))
    pthread_cond_wait(&g_cond, &g_lock);
  fflush(g_file);
  pthread_mutex_unlock(&g_lock);
}

void hcr_log_shutdown(void) {
  if (!g_file) return;
  HLOGI("BOOT", "logger shutdown");
  hcr_log_flush();
  pthread_mutex_lock(&g_lock);
  const int join = g_running;
  g_running = 0;
  pthread_cond_broadcast(&g_cond);
  pthread_mutex_unlock(&g_lock);
  if (join) pthread_join(g_thread, NULL);
  fflush(g_file);
  fclose(g_file);
  g_file = NULL;
}
