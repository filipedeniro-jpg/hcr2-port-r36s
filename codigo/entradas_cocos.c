#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "entradas_cocos.h"
#include "error.h"
#include "jni_fake.h"
#include "text_render.h"

// ---------------------------------------------------------------------------
// Prefixos JNI de classes Cocos2d-x
// ---------------------------------------------------------------------------
#define RN "Java_org_cocos2dx_lib_Cocos2dxRenderer_"
#define HN "Java_org_cocos2dx_lib_Cocos2dxHelper_"

// ---------------------------------------------------------------------------
// Ponteiros de funções nativas e instâncias JNI
// ---------------------------------------------------------------------------
void *renderer_cls = NULL;
int  (*JNI_OnLoad)(void *vm, void *reserved) = NULL;
void (*nativeInit)(void *env, void *cls, int w, int h) = NULL;
void (*nativeRender)(void *env, void *cls) = NULL;
void (*nativeOnResume)(void *env, void *cls) = NULL;
void (*nativeOnPause)(void *env, void *cls) = NULL;
void (*nativeResize)(void *env, void *cls, int w, int h) = NULL;
void (*nativeTouchesBegin)(void *env, void *cls, int id, float x, float y) = NULL;
void (*nativeTouchesEnd)(void *env, void *cls, int id, float x, float y) = NULL;
void (*nativeTouchesMove)(void *env, void *cls, void *ids, void *xs, void *ys) = NULL;
void (*nativeSetApkPath)(void *env, void *cls, void *caminho_apk) = NULL;
void (*nativeSetContext)(void *env, void *cls, void *contexto, void *asset_manager) = NULL;
void (*nativeKeyDown)(void *env, void *cls, int keycode) = NULL;
void (*nativeOnRemoteConfigRead)(void *env, void *cls, int success) = NULL;
void (*nativeConsentInfoFinished)(void *env, void *cls) = NULL;
void (*nativeConsentFlowFinished)(void *env, void *cls) = NULL;
void (*nativeControllerConnected)(void *env, void *cls, void *vendorName, int ctrl) = NULL;
void *g_ctrl_name = NULL;
void (*nativeReturnPopupOfferJson)(void *env, void *cls, void *json) = NULL;
void (*nativeReturnMissionJson)(void *env, void *cls, void *json) = NULL;
void (*nativeSetInAppItem)(void *env, void *cls, void *id, void *price, void *amount, void *bonus, void *icon, uint8_t ad_free, uint8_t unknown, uint8_t special, uint8_t gems, uint8_t paints, int bundle) = NULL;
void (*nativeSetInAppItemPrice)(void *env, void *cls, void *id, void *price) = NULL;
void (*nativeSetInAppItemCurrencyCode)(void *env, void *cls, void *id, void *currency) = NULL;
void (*nativeHcrControllerKeyEvent)(void *env, void *cls, int keycode, int pressed) = NULL;
void (*nativeEulaHasBeenAccepted)(void *env, void *cls) = NULL;
void (*nativeSetNetworkAvailable)(void *env, void *cls, int available) = NULL;
void (*nativeReturnCountryCode)(void *env, void *cls, void *code) = NULL;
void (*nativeInitBitmapDC)(void *env, void *cls, int width, int height, void *pixels) = NULL;
void (*nativeOnConfigurationCompleted)(void *env, void *cls) = NULL;
void (*nativeOnFinishedAssetLoader)(void *env, void *cls) = NULL;
void (*nativeOnNetworkConnectivityChanged)(void *env, void *cls, uint8_t connected) = NULL;
void (*nativeOnAdSettingsReceived)(void *env, void *cls) = NULL;
void (*nativeOnDataRead)(void *env, void *cls, uint8_t success, int code, int subcode, void *key, void *data, int tag) = NULL;
void (*nativeOnFirebaseRemoteConfigReceived)(void *env, void *cls) = NULL;
void (*nativeOnAllIapPricesUpdated)(void *env, void *cls) = NULL;
void (*nativeOnLoginSuccess)(void *env, void *cls, int player_id) = NULL;
void (*nativeOnGameServicesConnected)(void *env, void *cls) = NULL;
void (*nativeOnJsonReceived)(void *env, void *cls, void *url, void *json) = NULL;
void (*nativeNotifyVideoAdCompleted)(void *env, void *cls) = NULL;
void (*nativeNotifyInterstitialDismissed)(void *env, void *cls) = NULL;
void (*nativeOnNewsDataAvailable)(void *env, void *cls, void *popup_id, int new_items) = NULL;

// Símbolos nativos de introspecção do Cocos2d-x Director
void *(*cocos_director_getInstance)(void) = NULL;
void *(*cocos_director_getRunningScene)(void *director) = NULL;

static void resolve_function(void *destination, size_t destination_size, uintptr_t address) {
  if (destination_size != sizeof(address)) {
    fatal_error("Tamanho de ponteiro de funcao inesperado.");
  }
  memcpy(destination, &address, destination_size);
}

#define RESOLVE_REQUIRED(dst, symbol) \
  resolve_function(&(dst), sizeof(dst), so_find_addr_rx(mod, (symbol)))
#define RESOLVE_OPTIONAL(dst, symbol) \
  resolve_function(&(dst), sizeof(dst), so_try_find_addr_rx(mod, (symbol)))

void resolve_entry_points(so_module *mod) {
  RESOLVE_REQUIRED(JNI_OnLoad, "JNI_OnLoad");
  RESOLVE_REQUIRED(nativeInit, RN "nativeInit");
  RESOLVE_REQUIRED(nativeRender, RN "nativeRender");
  RESOLVE_OPTIONAL(nativeOnResume, RN "nativeOnResume");
  RESOLVE_OPTIONAL(nativeOnPause, RN "nativeOnPause");
  RESOLVE_OPTIONAL(nativeTouchesBegin, RN "nativeTouchesBegin");
  RESOLVE_OPTIONAL(nativeTouchesEnd, RN "nativeTouchesEnd");
  RESOLVE_OPTIONAL(nativeTouchesMove, RN "nativeTouchesMove");
  RESOLVE_OPTIONAL(nativeSetApkPath, HN "nativeSetApkPath");
  RESOLVE_OPTIONAL(nativeSetContext, HN "nativeSetContext");
  
  // Metodos de transicao do Hill Climb Racing 2 (v1.28.2)
  RESOLVE_OPTIONAL(nativeOnConfigurationCompleted, "Java_com_fingersoft_hcr2_AppActivity_onConfigurationCompleted");
  RESOLVE_OPTIONAL(nativeOnNetworkConnectivityChanged, "Java_com_fingersoft_hcr2_AppActivity_onNetworkConnectivityChanged");
  RESOLVE_OPTIONAL(nativeOnAdSettingsReceived, "Java_com_fingersoft_hcr2_AppActivity_onAdSettingsReceived");
  RESOLVE_OPTIONAL(nativeOnDataRead, "Java_com_fingersoft_hcr2_AppActivity_onDataRead");
  RESOLVE_OPTIONAL(nativeOnFirebaseRemoteConfigReceived, "Java_com_fingersoft_hcr2_AppActivity_onFirebaseRemoteConfigReceived");
  RESOLVE_OPTIONAL(nativeOnAllIapPricesUpdated, "Java_com_fingersoft_hcr2_AppActivity_onAllIapPricesUpdated");
  RESOLVE_OPTIONAL(nativeOnLoginSuccess, "Java_com_fingersoft_hcr2_AppActivity_onLoginSuccess");
  RESOLVE_OPTIONAL(nativeOnGameServicesConnected, "Java_com_fingersoft_hcr2_AppActivity_onGameServicesConnected");
  RESOLVE_OPTIONAL(nativeOnJsonReceived, "Java_com_fingersoft_hcr2_AppActivity_onJsonReceived");
  RESOLVE_OPTIONAL(nativeNotifyVideoAdCompleted, "Java_com_fingersoft_hcr2_AppActivity_notifyVideoAdCompleted");
  RESOLVE_OPTIONAL(nativeNotifyInterstitialDismissed, "Java_com_fingersoft_hcr2_AppActivity_notifyInterstitialDismissed");
  RESOLVE_OPTIONAL(nativeOnNewsDataAvailable, "Java_com_fingersoft_hcr2_AppActivity_onNewsDataAvailable");

  // Introspecção da cena ativa no Cocos2d-x Director
  RESOLVE_OPTIONAL(cocos_director_getInstance, "_ZN7cocos2d8Director11getInstanceEv");
  RESOLVE_OPTIONAL(cocos_director_getRunningScene, "_ZN7cocos2d8Director15getRunningSceneEv");
}

void on_bitmap_create(const char *text, int width, int height) {
  if (!nativeInitBitmapDC) return;
  int w = 0, h = 0;
  void *pixels = text_render_create_bitmap(text, width, height, &w, &h);
  if (!pixels) {
    w = width > 0 ? width : 128;
    h = height > 0 ? height : 32;
    pixels = calloc(1, (size_t)w * (size_t)h * 4);
  }
  if (!pixels) return;
  size_t bytes = (size_t)w * (size_t)h * 4;
  void *jarr = jni_make_input_array(bytes, 1);
  if (jarr) {
    jni_input_array_set(jarr, pixels, bytes);
    nativeInitBitmapDC(fake_env, renderer_cls, w, h, jarr);
  }
  free(pixels);
}

static inline int ptr_valido_aarch64(const void *p) {
  uintptr_t v = (uintptr_t)p;
  return (v >= 0x0000005000000000ULL && v < 0x0000800000000000ULL && (v & 0x7) == 0);
}

const char *cocos_obter_nome_classe_rtti(void *obj) {
  if (!obj) return "Nulo";
  if (!ptr_valido_aarch64(obj)) return "Invalido";
  void **vptr = *(void ***)obj;
  if (!ptr_valido_aarch64(vptr)) return "SemVtable";
  void *ti = vptr[-1];
  if (!ptr_valido_aarch64(ti)) return "SemTypeInfo";
  void **ti_arr = (void **)ti;
  const char *nome = (const char *)ti_arr[1];
  if (!nome || (uintptr_t)nome < 0x0000005000000000ULL || (uintptr_t)nome >= 0x0000800000000000ULL) {
    return "NomeInvalido";
  }
  return (nome[0] >= '0' && nome[0] <= '9') ? (nome + 2) : nome;
}
