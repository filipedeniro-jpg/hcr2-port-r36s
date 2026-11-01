#ifndef ENTRADAS_COCOS_H
#define ENTRADAS_COCOS_H

#include <stdint.h>
#include <stddef.h>
#include "so_util.h"

// Instância Java do renderer Cocos2d-x passada às funções nativas
extern void *renderer_cls;

// Ponteiros de funções de ciclo de vida Cocos2d-x / JNI
extern int  (*JNI_OnLoad)(void *vm, void *reserved);
extern void (*nativeInit)(void *env, void *cls, int w, int h);
extern void (*nativeRender)(void *env, void *cls);
extern void (*nativeOnResume)(void *env, void *cls);
extern void (*nativeOnPause)(void *env, void *cls);
extern void (*nativeResize)(void *env, void *cls, int w, int h);
extern void (*nativeTouchesBegin)(void *env, void *cls, int id, float x, float y);
extern void (*nativeTouchesEnd)(void *env, void *cls, int id, float x, float y);
extern void (*nativeTouchesMove)(void *env, void *cls, void *ids, void *xs, void *ys);
extern void (*nativeSetApkPath)(void *env, void *cls, void *caminho_apk);
extern void (*nativeSetContext)(void *env, void *cls, void *contexto, void *asset_manager);
extern void (*nativeKeyDown)(void *env, void *cls, int keycode);
extern void (*nativeOnRemoteConfigRead)(void *env, void *cls, int success);
extern void (*nativeConsentInfoFinished)(void *env, void *cls);
extern void (*nativeConsentFlowFinished)(void *env, void *cls);
extern void (*nativeControllerConnected)(void *env, void *cls, void *vendorName, int ctrl);
extern void *g_ctrl_name;
extern void (*nativeReturnPopupOfferJson)(void *env, void *cls, void *json);
extern void (*nativeReturnMissionJson)(void *env, void *cls, void *json);
extern void (*nativeSetInAppItem)(void *env, void *cls, void *id, void *price, void *amount, void *bonus, void *icon, uint8_t ad_free, uint8_t unknown, uint8_t special, uint8_t gems, uint8_t paints, int bundle);
extern void (*nativeSetInAppItemPrice)(void *env, void *cls, void *id, void *price);
extern void (*nativeSetInAppItemCurrencyCode)(void *env, void *cls, void *id, void *currency);
extern void (*nativeHcrControllerKeyEvent)(void *env, void *cls, int keycode, int pressed);
extern void (*nativeEulaHasBeenAccepted)(void *env, void *cls);
extern void (*nativeSetNetworkAvailable)(void *env, void *cls, int available);
extern void (*nativeReturnCountryCode)(void *env, void *cls, void *code);
extern void (*nativeInitBitmapDC)(void *env, void *cls, int width, int height, void *pixels);
extern void (*nativeOnConfigurationCompleted)(void *env, void *cls);
extern void (*nativeOnFinishedAssetLoader)(void *env, void *cls);
extern void (*nativeOnNetworkConnectivityChanged)(void *env, void *cls, uint8_t connected);
extern void (*nativeOnAdSettingsReceived)(void *env, void *cls);
extern void (*nativeOnDataRead)(void *env, void *cls, uint8_t success, int code, int subcode, void *key, void *data, int tag);
extern void (*nativeOnFirebaseRemoteConfigReceived)(void *env, void *cls);
extern void (*nativeOnAllIapPricesUpdated)(void *env, void *cls);
extern void (*nativeOnLoginSuccess)(void *env, void *cls, int player_id);
extern void (*nativeOnGameServicesConnected)(void *env, void *cls);
extern void (*nativeOnJsonReceived)(void *env, void *cls, void *url, void *json);
extern void (*nativeNotifyVideoAdCompleted)(void *env, void *cls);
extern void (*nativeNotifyInterstitialDismissed)(void *env, void *cls);
extern void (*nativeOnNewsDataAvailable)(void *env, void *cls, void *popup_id, int new_items);

// Símbolos nativos de introspecção do Cocos2d-x Director
// Resolução dos símbolos nativos Cocos2d-x / JNI na libcocos2dcpp.so
void resolve_entry_points(so_module *mod);

// Callback nativo para renderização de bitmaps de fontes/textos do Cocos2d-x
void on_bitmap_create(const char *text, int width, int height);

// Introspecção segura de RTTI C++ (nome da classe do objeto Cocos2d-x)
const char *cocos_obter_nome_classe_rtti(void *obj);

#endif // ENTRADAS_COCOS_H
