#ifndef HCR_LOG_H
#define HCR_LOG_H

#include <stdint.h>
#include <stddef.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Low-overhead asynchronous logger.  Game/render threads only format and
 * enqueue; the writer thread owns the SD-card FILE and flushes when the queue
 * drains.  hcr_nx.log is the current boot, hcr_nx_prev.log is the previous. */
void hcr_log_init(void);
void hcr_log_shutdown(void);
void hcr_log_flush(void);
void hcr_logf(const char *level, const char *tag, const char *fmt, ...)
  __attribute__((format(printf, 3, 4)));
uint64_t hcr_time_us(void);

#define HLOGI(tag, fmt, ...) hcr_logf("I", (tag), (fmt), ##__VA_ARGS__)
#define HLOGW(tag, fmt, ...) hcr_logf("W", (tag), (fmt), ##__VA_ARGS__)
#define HLOGE(tag, fmt, ...) hcr_logf("E", (tag), (fmt), ##__VA_ARGS__)
#define HLOGD(tag, fmt, ...) hcr_logf("D", (tag), (fmt), ##__VA_ARGS__)

#ifdef __cplusplus
}
#endif
#endif
