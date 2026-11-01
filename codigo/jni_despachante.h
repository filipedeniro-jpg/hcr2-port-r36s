#ifndef JNI_DESPACHANTE_H
#define JNI_DESPACHANTE_H

#include <stdint.h>
#include <stddef.h>
#include <stdarg.h>
#include "jni_interno.h"

// Flags globais e estado da aplicação
extern volatile int g_asset_loader_notificado;
extern volatile int g_finished_launching_notificado;
extern volatile int jni_quit_requested;
extern int g_is_in_game;

// Callbacks da UI e popups
typedef void (*jni_popup_offer_callback)(const char *id);
typedef void (*jni_bitmap_callback)(const char *text, int width, int height);

void jni_set_popup_offer_callback(jni_popup_offer_callback callback);
void jni_set_bitmap_callback(jni_bitmap_callback callback);
int  jni_take_consent_form_request(void);

// Handlers da tabela JNIEnv implementados pelo despachante
void *j_CallObjectMethod(void *env, void *recv, FakeID *id, ...);
void *j_CallObjectMethodV(void *env, void *recv, FakeID *id, va_list va);
uint64_t j_CallIntMethod(void *env, void *recv, FakeID *id, ...);
uint64_t j_CallIntMethodV(void *env, void *recv, FakeID *id, va_list va);
uint64_t j_CallBooleanMethod(void *env, void *recv, FakeID *id, ...);
uint64_t j_CallBooleanMethodV(void *env, void *recv, FakeID *id, va_list va);
uint64_t j_CallLongMethod(void *env, void *recv, FakeID *id, ...);
uint64_t j_CallLongMethodV(void *env, void *recv, FakeID *id, va_list va);
float j_CallFloatMethod(void *env, void *recv, FakeID *id, ...);
float j_CallFloatMethodV(void *env, void *recv, FakeID *id, va_list va);
double j_CallDoubleMethod(void *env, void *recv, FakeID *id, ...);
double j_CallDoubleMethodV(void *env, void *recv, FakeID *id, va_list va);
void j_CallVoidMethod(void *env, void *recv, FakeID *id, ...);
void j_CallVoidMethodV(void *env, void *recv, FakeID *id, va_list va);

#endif // JNI_DESPACHANTE_H
