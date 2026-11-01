#include <stdio.h>
#include <unistd.h>

#include "jni_fake.h"
#include "jni_interno.h"
#include "jni_preferencias.h"
#include "jni_env_basico.h"
#include "jni_despachante.h"

// Inicializador principal do subsistema JNI simulado
void jni_init(void) {
  printf("[JNI] Inicializando ambiente JNI modular...\n");
  fflush(stdout);

  // Inicializa tabelas de despacho JNIEnv e JavaVM
  jni_env_basico_init();

  // Carrega preferencias salvas do jogo
  prefs_load();

  // Configura preferencias de consentimento e idioma padrao pt-BR
  pref_set("eula_has_been_accepted", "1");
  pref_set("eula_accepted_version", "999");
  pref_set("consent_flow_completed", "1");
  pref_set("privacy_accepted", "1");
  pref_set("first_run", "0");
  pref_set("language", "translations/pt-BR.po");
  pref_set("current_language", "translations/pt-BR.po");

  printf("[JNI] Ambiente JNI modular pronto com sucesso.\n");
  fflush(stdout);
}
