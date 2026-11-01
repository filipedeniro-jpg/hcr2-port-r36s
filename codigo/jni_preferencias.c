#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>

#include "jni_fake.h"
#include "jni_preferencias.h"
#include "jni_preferencias_disco.h"

#define PREF_HASH_SIZE 65536
#define PREFS_APPLY_DEBOUNCE_US 120000

static Pref prefs[MAX_PREFS];
static size_t prefs_count;
static uint16_t pref_hash[PREF_HASH_SIZE];
static int prefs_dirty;
static uint64_t prefs_generation;
static uint64_t prefs_saved_generation;
static int prefs_write_error;
static pthread_mutex_t prefs_mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t prefs_cond = PTHREAD_COND_INITIALIZER;
static pthread_t prefs_thread;
static int prefs_thread_started;
static int prefs_stop;
static int prefs_force_flush;

static uint32_t pref_key_hash(const char *key) {
  uint32_t h = 2166136261u;
  const unsigned char *p = (const unsigned char *)(key ? key : "");
  while (*p) { h ^= *p++; h *= 16777619u; }
  return h;
}

static int pref_find_nolock(const char *key) {
  key = key ? key : "";
  size_t slot = pref_key_hash(key) & (PREF_HASH_SIZE - 1);
  for (size_t probe = 0; probe < PREF_HASH_SIZE; ++probe) {
    const uint16_t value = pref_hash[slot];
    if (!value) return -1;
    const size_t index = (size_t)value - 1;
    if (index < prefs_count && !strcmp(prefs[index].key, key)) return (int)index;
    slot = (slot + 1) & (PREF_HASH_SIZE - 1);
  }
  return -1;
}

static void pref_hash_insert_nolock(size_t index) {
  const char *key = prefs[index].key ? prefs[index].key : "";
  size_t slot = pref_key_hash(key) & (PREF_HASH_SIZE - 1);
  while (pref_hash[slot]) {
    slot = (slot + 1) & (PREF_HASH_SIZE - 1);
  }
  pref_hash[slot] = (uint16_t)(index + 1);
}

static void pref_hash_rebuild(void) {
  memset(pref_hash, 0, sizeof(pref_hash));
  for (size_t i = 0; i < prefs_count; ++i) {
    pref_hash_insert_nolock(i);
  }
}

void prefs_load(void) {
  static Pref loaded[MAX_PREFS];
  size_t loaded_count = 0;
  int legacy = 0;

  if (prefs_carregar_do_disco(loaded, &loaded_count, &legacy) != 0) return;

  pthread_mutex_lock(&prefs_mutex);
  prefs_liberar_array(prefs, prefs_count);
  memcpy(prefs, loaded, loaded_count * sizeof(Pref));
  prefs_count = loaded_count;
  pref_hash_rebuild();
  prefs_dirty = legacy;
  prefs_generation = 0;
  prefs_saved_generation = legacy ? 0 : 1;
  prefs_write_error = 0;
  pthread_mutex_unlock(&prefs_mutex);

  printf("[HCR] Loaded %zu preferences from save/preferences.bin (legacy=%d)\n",
         loaded_count, legacy);
}

static void prefs_write_snapshot(void) {
  Pref *snapshot = NULL;
  size_t count = 0;
  uint64_t gen = 0;

  pthread_mutex_lock(&prefs_mutex);
  if (!prefs_dirty) {
    pthread_mutex_unlock(&prefs_mutex);
    return;
  }
  count = prefs_count;
  gen = prefs_generation;
  if (count > 0) {
    snapshot = malloc(count * sizeof(Pref));
    if (snapshot) {
      for (size_t i = 0; i < count; ++i) {
        snapshot[i].key = strdup(prefs[i].key ? prefs[i].key : "");
        snapshot[i].val = strdup(prefs[i].val ? prefs[i].val : "");
      }
    }
  }
  pthread_mutex_unlock(&prefs_mutex);

  if (!snapshot && count > 0) return;

  const int ok = prefs_gravar_no_disco(snapshot, count);
  prefs_liberar_array(snapshot, count);
  free(snapshot);

  pthread_mutex_lock(&prefs_mutex);
  if (ok == 0) {
    prefs_saved_generation = gen;
    if (prefs_generation == gen) prefs_dirty = 0;
    prefs_write_error = 0;
  } else {
    prefs_write_error = 1;
  }
  pthread_mutex_unlock(&prefs_mutex);
}

static void *prefs_worker_thread(void *arg) {
  (void)arg;
  pthread_mutex_lock(&prefs_mutex);
  while (!prefs_stop) {
    while (!prefs_stop && !prefs_force_flush &&
           (!prefs_dirty || prefs_generation == prefs_saved_generation)) {
      pthread_cond_wait(&prefs_cond, &prefs_mutex);
    }
    if (prefs_stop) break;

    if (!prefs_force_flush) {
      pthread_mutex_unlock(&prefs_mutex);
      usleep(PREFS_APPLY_DEBOUNCE_US);
      pthread_mutex_lock(&prefs_mutex);
      if (prefs_stop) break;
    }
    prefs_force_flush = 0;
    pthread_mutex_unlock(&prefs_mutex);
    prefs_write_snapshot();
    pthread_mutex_lock(&prefs_mutex);
  }
  pthread_mutex_unlock(&prefs_mutex);
  return NULL;
}

void prefs_start_worker(void) {
  pthread_mutex_lock(&prefs_mutex);
  if (!prefs_thread_started) {
    prefs_stop = 0;
    prefs_force_flush = 0;
    if (pthread_create(&prefs_thread, NULL, prefs_worker_thread, NULL) == 0) {
      prefs_thread_started = 1;
    }
  }
  pthread_mutex_unlock(&prefs_mutex);
}

void prefs_apply_async(void) {
  pthread_mutex_lock(&prefs_mutex);
  if (prefs_thread_started) {
    pthread_cond_signal(&prefs_cond);
  }
  pthread_mutex_unlock(&prefs_mutex);
}

void prefs_commit_sync(void) {
  prefs_write_snapshot();
}

char *pref_dup(const char *key, const char *dflt) {
  pthread_mutex_lock(&prefs_mutex);
  const int index = pref_find_nolock(key);
  char *result = NULL;
  if (index >= 0 && prefs[index].val) {
    result = strdup(prefs[index].val);
  } else if (dflt) {
    result = strdup(dflt);
  }
  pthread_mutex_unlock(&prefs_mutex);
  return result;
}

int pref_has(const char *key) {
  pthread_mutex_lock(&prefs_mutex);
  const int exists = (pref_find_nolock(key) >= 0);
  pthread_mutex_unlock(&prefs_mutex);
  return exists;
}

void *pref_make_jni_string(const char *key, const char *dflt) {
  char *val = pref_dup(key, dflt);
  void *res = jni_make_string(val ? val : "");
  free(val);
  return res;
}

int pref_set(const char *key, const char *val) {
  if (!key || !val) return PREF_SET_NOMEM;
  pthread_mutex_lock(&prefs_mutex);
  const int index = pref_find_nolock(key);
  if (index >= 0) {
    if (!strcmp(prefs[index].val, val)) {
      pthread_mutex_unlock(&prefs_mutex);
      return PREF_SET_UNCHANGED;
    }
    char *copy = strdup(val);
    if (!copy) {
      pthread_mutex_unlock(&prefs_mutex);
      return PREF_SET_NOMEM;
    }
    free(prefs[index].val);
    prefs[index].val = copy;
  } else if (prefs_count < MAX_PREFS) {
    char *key_copy = strdup(key);
    char *val_copy = strdup(val);
    if (!key_copy || !val_copy) {
      free(key_copy); free(val_copy);
      pthread_mutex_unlock(&prefs_mutex);
      return PREF_SET_NOMEM;
    }
    const size_t new_index = prefs_count++;
    prefs[new_index].key = key_copy;
    prefs[new_index].val = val_copy;
    pref_hash_insert_nolock(new_index);
  } else {
    pthread_mutex_unlock(&prefs_mutex);
    return PREF_SET_FULL;
  }
  prefs_generation++;
  prefs_dirty = 1;
  prefs_write_error = 0;
  pthread_mutex_unlock(&prefs_mutex);
  return PREF_SET_CHANGED;
}

void jni_flush_prefs(void) {
  prefs_write_snapshot();
}
