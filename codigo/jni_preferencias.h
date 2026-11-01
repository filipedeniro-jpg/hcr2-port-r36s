#ifndef JNI_PREFERENCIAS_H
#define JNI_PREFERENCIAS_H

#include <stdint.h>
#include <stddef.h>

enum {
  PREF_SET_UNCHANGED = 0,
  PREF_SET_CHANGED = 1,
  PREF_SET_NOMEM = -1,
  PREF_SET_FULL = -2,
};

// Carrega as preferências salvas no disco (save/preferences.bin)
void prefs_load(void);

// Força a gravação imediata de todas as preferências pendentes no disco
void jni_flush_prefs(void);

// Duplica o valor associado a uma chave (ou retorna padrão se não existir)
char *pref_dup(const char *key, const char *dflt);

// Verifica se uma chave existe no mapa de preferências
int pref_has(const char *key);

// Define ou atualiza uma chave/valor no mapa de preferências
int pref_set(const char *key, const char *val);

// Cria um objeto jstring JNI contendo o valor da preferência
void *pref_make_jni_string(const char *key, const char *dflt);

// Solicita persistência assíncrona na thread de gravação
void prefs_apply_async(void);

// Realiza persistência síncrona imediata
void prefs_commit_sync(void);

#endif // JNI_PREFERENCIAS_H
