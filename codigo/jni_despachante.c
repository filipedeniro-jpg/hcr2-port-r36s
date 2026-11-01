#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <stdarg.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sys/stat.h>

#include "config.h"
#include "util.h"
#include "asset_shim.h"
#include "hcr_http.h"
#include "jni_interno.h"
#include "jni_preferencias.h"
#include "jni_iap_billing.h"
#include "jni_env_basico.h"
#include "jni_despachante.h"
#include "opensles.h"

#define HCR_PACKAGE "com.fingersoft.hillclimb"

static volatile int jni_consent_form_requested = 0;
static jni_popup_offer_callback g_popup_offer_callback = NULL;
static jni_bitmap_callback g_bitmap_callback = NULL;

volatile int g_asset_loader_notificado = 0;
volatile int g_finished_launching_notificado = 0;
volatile int jni_quit_requested = 0;
int g_is_in_game = 0;

void jni_set_popup_offer_callback(jni_popup_offer_callback callback) { g_popup_offer_callback = callback; }
void jni_set_bitmap_callback(jni_bitmap_callback callback) { g_bitmap_callback = callback; }
int jni_take_consent_form_request(void) {
  int req = jni_consent_form_requested; jni_consent_form_requested = 0; return req;
}

const char *data_dir(void) {
  static char dir[256];
  if (!dir[0]) {
    if (!getcwd(dir, sizeof(dir)) || !dir[0]) strcpy(dir, ".");
    size_t n = strlen(dir);
    if (n && dir[n - 1] != '/' && n + 1 < sizeof(dir)) { dir[n] = '/'; dir[n + 1] = 0; }
  }
  return dir;
}

void log_inteligente_jni(const char *categoria, const char *classe, const char *nome, const char *sig) {
  static char g_ultimo_metodo[128] = "";
  static uint32_t g_contagem_repeticoes = 0;
  char id[128];
  if (classe && classe[0]) snprintf(id, sizeof(id), "%s->%s", classe, nome ? nome : "desconhecido");
  else snprintf(id, sizeof(id), "%s", nome ? nome : "desconhecido");

  if (!strcmp(g_ultimo_metodo, id)) {
    g_contagem_repeticoes++;
    if (g_contagem_repeticoes <= 3) {
      if (sig && sig[0]) printf("[%s] %s (%s)\n", categoria, id, sig);
      else printf("[%s] %s\n", categoria, id);
      fflush(stdout);
    }
  } else {
    if (g_contagem_repeticoes > 3) {
      printf("[JNI RESUMO] '%s' repetido %u vezes consecutivas\n", g_ultimo_metodo, g_contagem_repeticoes);
      fflush(stdout);
    }
    strncpy(g_ultimo_metodo, id, sizeof(g_ultimo_metodo) - 1);
    g_ultimo_metodo[sizeof(g_ultimo_metodo) - 1] = '\0';
    g_contagem_repeticoes = 1;
    if (sig && sig[0]) printf("[%s] %s (%s)\n", categoria, id, sig);
    else printf("[%s] %s\n", categoria, id);
    fflush(stdout);
  }
}

void *call_object(FakeID *id, va_list va) {
  log_inteligente_jni("JNI-CallObject", id ? id->cls : NULL, id ? id->name : NULL, id ? id->sig : NULL);
  const char *n = id ? id->name : "";
  if (!strcmp(n, "getClassLoader")) return get_class("java/lang/ClassLoader");
  if (!strcmp(n, "loadClass")) return get_class(obj_str(va_arg(va, void *)));
  if (!strcmp(n, "getContext") || !strcmp(n, "getApplicationContext") ||
      !strcmp(n, "getActivity") || !strcmp(n, "getBaseContext")) return jni_make_thiz();
  if (!strcmp(n, "getAssets") || !strcmp(n, "getAssetManager")) return get_class("android/content/res/AssetManager");
  if (!strcmp(n, "getCocos2dxWritablePath") || !strcmp(n, "getWritablePath") ||
      !strcmp(n, "getFilesDir") || !strcmp(n, "getFilesDirectory") ||
      !strcmp(n, "getCocos2dxCachePath")) return jni_make_string(data_dir());
  if (!strcmp(n, "getCocos2dxPackageName") || !strcmp(n, "getPackageName")) return jni_make_string(HCR_PACKAGE);
  if (!strcmp(n, "getCurrentLanguage") || !strcmp(n, "getCurrentLanguageCode") ||
      !strcmp(n, "getDeviceLanguage")) return jni_make_string("pt-BR");
  if (!strcmp(n, "getDeviceModel")) return jni_make_string("Nintendo Switch");
  if (!strcmp(n, "getAssetsPath")) return jni_make_string(ASSETS_DIR);
  if (!strcmp(n, "getAndroidVersion")) return jni_make_string("10");
  if (!strcmp(n, "getVersion") || !strcmp(n, "getSystemVersion")) return jni_make_string("1.0");
  if (!strcmp(n, "getFirebaseRemoteConfigString") || !strcmp(n, "getAdSettingString")) {
    (void)va_arg(va, void *); return jni_make_string(obj_str(va_arg(va, void *)));
  }
  if (!strcmp(n, "aesEncrypt")) return jni_make_string(obj_str(va_arg(va, void *)));
  if (!strcmp(n, "retrieveDefaultsString")) return pref_make_jni_string(obj_str(va_arg(va, void *)), "");
  if (!strcmp(n, "aesDecrypt")) { void *v = va_arg(va, void *); return v ? jni_make_string(obj_str(v)) : NULL; }
  if (!strcmp(n, "getStringForKey") || !strcmp(n, "getSettingString")) {
    const char *k = obj_str(va_arg(va, void *)), *d = obj_str(va_arg(va, void *));
    char *val = pref_dup(k, NULL);
    void *res = jni_make_string(val ? val : (d ? d : ""));
    free(val); return res;
  }
  if (!strcmp(n, "createHttpURLConnection")) return jni_make_thiz();
  if (!strcmp(n, "getResponseMessage")) return jni_make_string("OK");
  if (!strcmp(n, "getResponseHeaderValue") || !strcmp(n, "getHeaderFields")) return NULL;
  if (!strcmp(n, "getFirebaseRemoteConfigKeysWithPrefix")) {
    void **arr = malloc(sizeof(void *)); return make_pri_array_adopt(arr, 0, sizeof(void *));
  }
  if (!strcmp(n, "getResponseContent") || !strcmp(n, "getInputStream")) {
    uint8_t *v = malloc(1); return make_pri_array_adopt(v, 0, 1);
  }
  if (!strcmp(n, "getObbAssetFileDescriptor")) {
    AAsset *a = AAssetManager_open_fake(asset_manager(), obj_str(va_arg(va, void *)), 0);
    if (!a) return NULL;
    int64_t start = 0, len = 0;
    int fd = AAsset_openFileDescriptor64_fake(a, &start, &len);
    AAsset_close_fake(a);
    if (fd < 0) return NULL;
    int64_t *arr = malloc(3 * sizeof(int64_t));
    arr[0] = fd; arr[1] = start; arr[2] = len;
    return make_pri_array_adopt(arr, 3, 8);
  }
  return NULL;
}

int call_int(FakeID *id, va_list va) {
  log_inteligente_jni("JNI-CallInt", id ? id->cls : NULL, id ? id->name : NULL, id ? id->sig : NULL);
  const char *n = id ? id->name : "";
  if (!strcmp(n, "getBoolForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int d = va_arg(va, int);
    char *v = pref_dup(k, NULL); int r = v ? (!strcmp(v, "1") || !strcasecmp(v, "true")) : d;
    free(v); return r;
  }
  if (!strcmp(n, "getIntegerForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int d = va_arg(va, int);
    char *v = pref_dup(k, NULL); int r = v ? atoi(v) : d; free(v); return r;
  }
  if (!strcmp(n, "getFirebaseRemoteConfigBool") || !strcmp(n, "getFirebaseRemoteConfigInt") ||
      !strcmp(n, "getSettingInt") || !strcmp(n, "getAdSettingInt")) {
    (void)obj_str(va_arg(va, void *)); return va_arg(va, int);
  }
  if (!strcmp(n, "playEffect")) {
    const char *p = obj_str(va_arg(va, void *));
    int lp = va_arg(va, int);
    float pitch = (float)va_arg(va, double);
    float pan = (float)va_arg(va, double);
    float gain = (float)va_arg(va, double);
    float l = gain * (1.0f - (pan > 0.0f ? pan : 0.0f));
    float r = gain * (1.0f - (pan < 0.0f ? -pan : 0.0f));
    int id_efeito = cocos_audio_play_effect(p, lp, l, r);
    if (pitch != 1.0f && id_efeito > 0) cocos_audio_set_effect_rate(id_efeito, pitch);
    return id_efeito;
  }
  if (!strcmp(n, "isBackgroundMusicPlaying")) return cocos_audio_background_playing();
  if (!strcmp(n, "hasValue")) return pref_has(obj_str(va_arg(va, void *)));
  if (!strcmp(n, "checkPermission") || !strcmp(n, "hasPermission") ||
      !strcmp(n, "isPermissionGranted") || !strcmp(n, "isPermissionCheck") ||
      !strcmp(n, "checkStoragePermission") || !strcmp(n, "hasFinishedLaunching") ||
      !strcmp(n, "isFinishedLaunching") || !strcmp(n, "isObbCheckExist") ||
      !strcmp(n, "isObbExist") || !strcmp(n, "isObbMounted") || !strcmp(n, "checkObb"))
    return 1;
  if (!strcmp(n, "isNetworkConnect") || !strcmp(n, "isNetworkConnected") ||
      !strcmp(n, "isNetworkAvailable") || !strcmp(n, "hasActiveInternetConnection"))
    return hcr_http_network_available();
  if (!strcmp(n, "getIAPAdFree")) return freeshop_get_adfree();
  if (!strcmp(n, "getIAPBundle")) return freeshop_get_bundle();
  if (!strcmp(n, "getIAPCoins")) return freeshop_get_coins();
  if (!strcmp(n, "getIAPGems")) return freeshop_get_gems();
  if (!strcmp(n, "getIAPPaints")) return freeshop_get_paints();
  if (!strcmp(n, "getIAPError")) return freeshop_get_error();
  if (!strcmp(n, "getResponseCode")) return 200;
  if (!strcmp(n, "getDPI")) return 320;
  return 0;
}

int64_t call_long(FakeID *id, va_list va) {
  if (id && !strcmp(id->name, "getLongForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int64_t d = va_arg(va, int64_t);
    char *v = pref_dup(k, NULL); int64_t r = v ? (int64_t)strtoll(v, NULL, 10) : d; free(v); return r;
  }
  return 0;
}

double call_double(FakeID *id, va_list va) {
  const char *n = id ? id->name : "";
  if (!strcmp(n, "getBackgroundMusicVolume")) return cocos_audio_background_volume();
  if (!strcmp(n, "getEffectsVolume")) return cocos_audio_effects_volume();
  if (!strcmp(n, "getFloatForKey") || !strcmp(n, "getDoubleForKey")) {
    const char *k = obj_str(va_arg(va, void *)); double d = va_arg(va, double);
    char *v = pref_dup(k, NULL); double r = v ? strtod(v, NULL) : d; free(v); return r;
  }
  return 0.0;
}

float call_float(FakeID *id, va_list va) { return (float)call_double(id, va); }

void call_void(FakeID *id, va_list va) {
  log_inteligente_jni("JNI-CallVoid", id ? id->cls : NULL, id ? id->name : NULL, id ? id->sig : NULL);
  const char *n = id ? id->name : "";
  if (!strcmp(n, "preloadEffect")) { cocos_audio_preload(obj_str(va_arg(va, void *))); return; }
  if (!strcmp(n, "stopEffect")) { cocos_audio_stop_effect(va_arg(va, int)); return; }
  if (!strcmp(n, "pauseEffect")) { cocos_audio_pause_effect(va_arg(va, int), 1); return; }
  if (!strcmp(n, "resumeEffect")) { cocos_audio_pause_effect(va_arg(va, int), 0); return; }
  if (!strcmp(n, "stopAllEffects")) { cocos_audio_stop_all_effects(); return; }
  if (!strcmp(n, "pauseAllEffects")) { cocos_audio_pause_all_effects(1); return; }
  if (!strcmp(n, "resumeAllEffects")) { cocos_audio_pause_all_effects(0); return; }
  if (!strcmp(n, "setEffectsVolume")) { cocos_audio_set_effects_volume((float)va_arg(va, double)); return; }
  if (!strcmp(n, "setEffectVolume")) {
    int id_efeito = va_arg(va, int); float vol = (float)va_arg(va, double);
    cocos_audio_set_effect_volume(id_efeito, vol); return;
  }
  if (!strcmp(n, "setEffectRate")) {
    int id_efeito = va_arg(va, int); float taxa = (float)va_arg(va, double);
    cocos_audio_set_effect_rate(id_efeito, taxa); return;
  }
  if (!strcmp(n, "unloadEffect")) { (void)va_arg(va, void *); return; }
  if (!strcmp(n, "playBackgroundMusic")) {
    const char *p = obj_str(va_arg(va, void *)); cocos_audio_play_background(p, va_arg(va, int)); return;
  }
  if (!strcmp(n, "stopBackgroundMusic")) { cocos_audio_stop_background(); return; }
  if (!strcmp(n, "pauseBackgroundMusic")) { cocos_audio_pause_background(1); return; }
  if (!strcmp(n, "resumeBackgroundMusic")) { cocos_audio_pause_background(0); return; }
  if (!strcmp(n, "rewindBackgroundMusic")) { cocos_audio_rewind_background(); return; }
  if (!strcmp(n, "setBackgroundMusicVolume")) {
    cocos_audio_set_background_volume((float)va_arg(va, double)); return;
  }
  if (!strcmp(n, "setBoolForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int v = va_arg(va, int);
    pref_set(k, v ? "1" : "0"); prefs_apply_async(); return;
  }
  if (!strcmp(n, "setIntegerForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int v = va_arg(va, int);
    char b[32]; snprintf(b, sizeof(b), "%d", v); pref_set(k, b); prefs_apply_async(); return;
  }
  if (!strcmp(n, "setFloatForKey") || !strcmp(n, "setDoubleForKey")) {
    const char *k = obj_str(va_arg(va, void *)); double v = va_arg(va, double);
    char b[64]; snprintf(b, sizeof(b), "%g", v); pref_set(k, b); prefs_apply_async(); return;
  }
  if (!strcmp(n, "setStringForKey") || !strcmp(n, "setSettingString") || !strcmp(n, "saveDefaultsString")) {
    const char *k = obj_str(va_arg(va, void *)); const char *v = obj_str(va_arg(va, void *));
    pref_set(k, v); prefs_apply_async(); return;
  }
  if (!strcmp(n, "setLongForKey")) {
    const char *k = obj_str(va_arg(va, void *)); int64_t v = va_arg(va, int64_t);
    char b[32]; snprintf(b, sizeof(b), "%lld", (long long)v); pref_set(k, b); prefs_apply_async(); return;
  }
  if (!strcmp(n, "flush")) { prefs_commit_sync(); return; }
  if (!strcmp(n, "sendJsonRequestToUrl")) { hcr_http_enqueue_popup(obj_str(va_arg(va, void *))); return; }
  if (!strcmp(n, "sendMissionJsonRequestToUrl")) { hcr_http_enqueue_mission(obj_str(va_arg(va, void *))); return; }
  if (!strcmp(n, "resetIAP")) { freeshop_reset_pending(); return; }
  if (!strcmp(n, "requestInAppPurchaseGooglePlayNew")) { freeshop_apply_purchase(obj_str(va_arg(va, void *))); return; }
  if (!strcmp(n, "setPopupOfferId")) {
    const char *id_str = obj_str(va_arg(va, void *)); if (g_popup_offer_callback) g_popup_offer_callback(id_str); return;
  }
  if (!strcmp(n, "createTextBitmap")) {
    const char *t = obj_str(va_arg(va, void *)); (void)obj_str(va_arg(va, void *));
    (void)va_arg(va, int); (void)va_arg(va, int);
    int w = va_arg(va, int), h = va_arg(va, int);
    if (g_bitmap_callback) g_bitmap_callback(t, w, h); return;
  }
  if (!strcmp(n, "onFinishedAssetLoader")) {
    printf("[HCR2] Engine notificou: onFinishedAssetLoader() concluido com sucesso!\n");
    fflush(stdout); g_asset_loader_notificado = 1; return;
  }
  if (!strcmp(n, "onFinishedLaunching")) {
    printf("[HCR2] Engine notificou: onFinishedLaunching() concluido com sucesso!\n");
    fflush(stdout); g_finished_launching_notificado = 1; return;
  }
  if (!strcmp(n, "enterInGame")) { g_is_in_game = 1; return; }
  if (!strcmp(n, "leaveInGame")) { g_is_in_game = 0; prefs_commit_sync(); sync(); return; }
  if (!strcmp(n, "showConsentFormIfRequired")) { jni_consent_form_requested = 1; return; }
  if (!strcmp(n, "terminateProcessJNI") || !strcmp(n, "appTerminate") || !strcmp(n, "appEnd")) {
    jni_quit_requested = 1; return;
  }
}

#define CALL_VARIADIC(fn, ret_t, dispatch) \
  ret_t fn(void *env, void *recv, FakeID *id, ...) { \
    (void)env; (void)recv; va_list va; va_start(va, id); \
    ret_t r = (ret_t)dispatch(id, va); va_end(va); return r; } \
  ret_t fn##V(void *env, void *recv, FakeID *id, va_list va) { \
    (void)env; (void)recv; return (ret_t)dispatch(id, va); }

CALL_VARIADIC(j_CallObjectMethod, void *, call_object)
CALL_VARIADIC(j_CallIntMethod, uint64_t, call_int)
CALL_VARIADIC(j_CallBooleanMethod, uint64_t, call_int)
CALL_VARIADIC(j_CallLongMethod, uint64_t, call_long)
CALL_VARIADIC(j_CallFloatMethod, float, call_float)
CALL_VARIADIC(j_CallDoubleMethod, double, call_double)

void j_CallVoidMethod(void *env, void *recv, FakeID *id, ...) {
  (void)env; (void)recv; va_list va; va_start(va, id); call_void(id, va); va_end(va);
}
void j_CallVoidMethodV(void *env, void *recv, FakeID *id, va_list va) {
  (void)env; (void)recv; call_void(id, va);
}
