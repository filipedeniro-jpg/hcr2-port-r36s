/* hcr_http.c -- offline HTTP simulacao (stub) for Hill Climb Racing Linux port */

#include <stdlib.h>
#include <string.h>

#include "hcr_http.h"
#include "jni_fake.h"

static void *callback_env;
static void *callback_cls;
static HcrHttpJsonCallback popup_callback;
static HcrHttpJsonCallback mission_callback;
static int local_popup_pending;

int hcr_http_init(void) {
  local_popup_pending = 1;
  return 0;
}

int hcr_http_network_available(void) {
  return 0; // Offline mode for console
}

void hcr_http_set_callbacks(void *env, void *cls, HcrHttpJsonCallback popup,
                            HcrHttpJsonCallback mission) {
  callback_env = env;
  callback_cls = cls;
  popup_callback = popup;
  mission_callback = mission;
}

void hcr_http_enqueue_popup(const char *url) {
  (void)url;
  if (popup_callback && callback_env) {
    popup_callback(callback_env, callback_cls, jni_make_string(""));
  }
}

void hcr_http_enqueue_mission(const char *url) {
  (void)url;
  if (mission_callback && callback_env) {
    mission_callback(callback_env, callback_cls, jni_make_string(""));
  }
}

void hcr_http_pump(void) {
  if (local_popup_pending && popup_callback && callback_env) {
    local_popup_pending = 0;
    popup_callback(callback_env, callback_cls, jni_make_string(""));
  }
}

void hcr_http_exit(void) {
  callback_env = NULL;
  callback_cls = NULL;
  popup_callback = NULL;
  mission_callback = NULL;
}
