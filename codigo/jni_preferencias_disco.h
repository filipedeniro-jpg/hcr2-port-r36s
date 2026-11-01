#ifndef JNI_PREFERENCIAS_DISCO_H
#define JNI_PREFERENCIAS_DISCO_H

#include <stdint.h>
#include <stddef.h>

#define MAX_PREFS 32768
#define PREFS_FILE "save/preferences.bin"
#define PREFS_TMP_FILE PREFS_FILE ".tmp"
#define PREFS_BAK_FILE PREFS_FILE ".bak"

typedef struct {
  char *key;
  char *val;
} Pref;

// Carrega as preferências do disco (tentando arquivo primário e backup)
int prefs_carregar_do_disco(Pref *out_prefs, size_t *out_count, int *out_legacy);

// Grava snapshot de preferências no disco usando transação atômica V2 (.tmp -> rename -> .bak)
int prefs_gravar_no_disco(const Pref *prefs, size_t count);

// Libera memória de um array de preferências
void prefs_liberar_array(Pref *array, size_t count);

#endif // JNI_PREFERENCIAS_DISCO_H
