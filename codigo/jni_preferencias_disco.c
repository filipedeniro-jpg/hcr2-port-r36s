#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

#include "jni_preferencias_disco.h"

#define PREFS_MAGIC_V1 0x48435031u
#define PREFS_MAGIC_V2 0x48435032u
#define PREFS_MAX_FILE (64u * 1024u * 1024u)
#define PREFS_MAX_KEY  (256u * 1024u)
#define PREFS_MAX_VALUE (32u * 1024u * 1024u)

typedef struct {
  uint32_t magic;
  uint32_t count;
  uint32_t payload_size;
  uint32_t checksum;
} PrefHeaderV2;

static uint32_t prefs_checksum(const void *data, size_t size) {
  const uint8_t *p = data;
  uint32_t h = 2166136261u;
  while (size--) { h ^= *p++; h *= 16777619u; }
  return h;
}

void prefs_liberar_array(Pref *array, size_t count) {
  if (!array) return;
  for (size_t i = 0; i < count; ++i) {
    free(array[i].key);
    free(array[i].val);
  }
}

static uint8_t *prefs_read_file(const char *path, size_t *out_size) {
  if (out_size) *out_size = 0;
  FILE *f = fopen(path, "rb");
  if (!f) return NULL;
  struct stat st;
  if (fstat(fileno(f), &st) != 0 || st.st_size <= 0 || (uint64_t)st.st_size > PREFS_MAX_FILE) {
    fclose(f);
    return NULL;
  }
  const size_t size = (size_t)st.st_size;
  uint8_t *buf = malloc(size ? size : 1);
  if (!buf) { fclose(f); return NULL; }
  if (fread(buf, 1, size, f) != size) {
    free(buf);
    fclose(f);
    return NULL;
  }
  fclose(f);
  if (out_size) *out_size = size;
  return buf;
}

static int prefs_parse_v2(const uint8_t *data, size_t size, Pref *out_prefs, size_t *out_count) {
  if (size < sizeof(PrefHeaderV2)) return -1;
  const PrefHeaderV2 *hdr = (const PrefHeaderV2 *)data;
  if (hdr->magic != PREFS_MAGIC_V2) return -1;
  if (hdr->count > MAX_PREFS) return -1;
  if (sizeof(PrefHeaderV2) + hdr->payload_size != size) return -1;
  const uint8_t *payload = data + sizeof(PrefHeaderV2);
  if (prefs_checksum(payload, hdr->payload_size) != hdr->checksum) return -1;

  size_t offset = 0;
  size_t parsed = 0;
  while (parsed < hdr->count) {
    if (offset + 8 > hdr->payload_size) goto fail;
    uint32_t klen = *(const uint32_t *)(payload + offset);
    uint32_t vlen = *(const uint32_t *)(payload + offset + 4);
    offset += 8;
    if (klen > PREFS_MAX_KEY || vlen > PREFS_MAX_VALUE) goto fail;
    if (offset + klen + vlen > hdr->payload_size) goto fail;

    char *k = malloc((size_t)klen + 1);
    char *v = malloc((size_t)vlen + 1);
    if (!k || !v) { free(k); free(v); goto fail; }
    memcpy(k, payload + offset, klen); k[klen] = '\0'; offset += klen;
    memcpy(v, payload + offset, vlen); v[vlen] = '\0'; offset += vlen;
    out_prefs[parsed].key = k;
    out_prefs[parsed].val = v;
    parsed++;
  }
  if (offset != hdr->payload_size) goto fail;
  *out_count = parsed;
  return 0;

fail:
  prefs_liberar_array(out_prefs, parsed);
  *out_count = 0;
  return -1;
}

static int prefs_parse_v1(const uint8_t *data, size_t size, Pref *out_prefs, size_t *out_count) {
  if (size < 4) return -1;
  const uint32_t magic = *(const uint32_t *)data;
  if (magic != PREFS_MAGIC_V1) return -1;

  size_t offset = 4;
  size_t parsed = 0;
  while (offset + 4 <= size && parsed < MAX_PREFS) {
    uint16_t klen = *(const uint16_t *)(data + offset);
    uint16_t vlen = *(const uint16_t *)(data + offset + 2);
    offset += 4;
    if (offset + klen + vlen > size) goto fail;

    char *k = malloc((size_t)klen + 1);
    char *v = malloc((size_t)vlen + 1);
    if (!k || !v) { free(k); free(v); goto fail; }
    memcpy(k, data + offset, klen); k[klen] = '\0'; offset += klen;
    memcpy(v, data + offset, vlen); v[vlen] = '\0'; offset += vlen;
    out_prefs[parsed].key = k;
    out_prefs[parsed].val = v;
    parsed++;
  }
  if (offset != size) goto fail;
  *out_count = parsed;
  return 0;

fail:
  prefs_liberar_array(out_prefs, parsed);
  *out_count = 0;
  return -1;
}

static int prefs_load_candidate(const char *path, Pref *out_prefs, size_t *out_count, int *out_legacy) {
  size_t size = 0;
  uint8_t *data = prefs_read_file(path, &size);
  if (!data) return -1;
  if (prefs_parse_v2(data, size, out_prefs, out_count) == 0) {
    free(data);
    if (out_legacy) *out_legacy = 0;
    return 0;
  }
  if (prefs_parse_v1(data, size, out_prefs, out_count) == 0) {
    free(data);
    if (out_legacy) *out_legacy = 1;
    return 0;
  }
  free(data);
  return -1;
}

int prefs_carregar_do_disco(Pref *out_prefs, size_t *out_count, int *out_legacy) {
  if (prefs_load_candidate(PREFS_FILE, out_prefs, out_count, out_legacy) == 0) return 0;
  return prefs_load_candidate(PREFS_BAK_FILE, out_prefs, out_count, out_legacy);
}

static uint8_t *prefs_serialize(const Pref *prefs, size_t count, size_t *out_size) {
  size_t payload_size = 0;
  for (size_t i = 0; i < count; ++i) {
    const size_t klen = strlen(prefs[i].key ? prefs[i].key : "");
    const size_t vlen = strlen(prefs[i].val ? prefs[i].val : "");
    payload_size += 8 + klen + vlen;
  }

  const size_t total_size = sizeof(PrefHeaderV2) + payload_size;
  uint8_t *buf = malloc(total_size ? total_size : 1);
  if (!buf) return NULL;

  uint8_t *payload = buf + sizeof(PrefHeaderV2);
  size_t offset = 0;
  for (size_t i = 0; i < count; ++i) {
    const char *k = prefs[i].key ? prefs[i].key : "";
    const char *v = prefs[i].val ? prefs[i].val : "";
    const uint32_t klen = (uint32_t)strlen(k);
    const uint32_t vlen = (uint32_t)strlen(v);
    memcpy(payload + offset, &klen, 4);
    memcpy(payload + offset + 4, &vlen, 4);
    offset += 8;
    memcpy(payload + offset, k, klen); offset += klen;
    memcpy(payload + offset, v, vlen); offset += vlen;
  }

  PrefHeaderV2 *hdr = (PrefHeaderV2 *)buf;
  hdr->magic = PREFS_MAGIC_V2;
  hdr->count = (uint32_t)count;
  hdr->payload_size = (uint32_t)payload_size;
  hdr->checksum = prefs_checksum(payload, payload_size);
  if (out_size) *out_size = total_size;
  return buf;
}

static int prefs_validate_v2_file(const char *path, size_t expected_size) {
  size_t size = 0;
  uint8_t *data = prefs_read_file(path, &size);
  if (!data) return -1;
  if (size != expected_size || size < sizeof(PrefHeaderV2)) { free(data); return -1; }
  const PrefHeaderV2 *hdr = (const PrefHeaderV2 *)data;
  if (hdr->magic != PREFS_MAGIC_V2) { free(data); return -1; }
  const uint8_t *payload = data + sizeof(PrefHeaderV2);
  const int ok = (prefs_checksum(payload, hdr->payload_size) == hdr->checksum);
  free(data);
  return ok ? 0 : -1;
}

int prefs_gravar_no_disco(const Pref *prefs, size_t count) {
  size_t bytes = 0;
  uint8_t *data = prefs_serialize(prefs, count, &bytes);
  if (!data) return -1;

  mkdir("save", 0755);
  FILE *f = fopen(PREFS_TMP_FILE, "wb");
  if (!f) { free(data); return -1; }
  if (fwrite(data, 1, bytes, f) != bytes) { fclose(f); unlink(PREFS_TMP_FILE); free(data); return -1; }
  fflush(f);
  int fd = fileno(f);
  if (fd >= 0) fsync(fd);
  fclose(f);
  free(data);

  if (prefs_validate_v2_file(PREFS_TMP_FILE, bytes) != 0) {
    unlink(PREFS_TMP_FILE);
    return -1;
  }

  struct stat st;
  if (stat(PREFS_FILE, &st) == 0 && st.st_size > 0) {
    unlink(PREFS_BAK_FILE);
    (void)link(PREFS_FILE, PREFS_BAK_FILE);
  }

  if (rename(PREFS_TMP_FILE, PREFS_FILE) != 0) {
    unlink(PREFS_TMP_FILE);
    return -1;
  }
  return 0;
}
