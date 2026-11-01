#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <pthread.h>
#include <switch.h>

#include "jni_interno.h"
#include "jni_env_basico.h"
#include "jni_despachante.h"

#define JNI_OK 0
#define JNI_VERSION_1_6 0x00010006

#define MAX_LOCALS 16384
#define MAX_FRAMES 64
static void *locals[MAX_LOCALS];
static int locals_top = 0;
static int frames[MAX_FRAMES];
static int frame_top = 0;
static Mutex locals_lock;

void *reg_local(void *ref) {
  if (ref) {
    mutexLock(&locals_lock);
    if (locals_top < MAX_LOCALS) locals[locals_top++] = ref;
    mutexUnlock(&locals_lock);
  }
  return ref;
}

void free_ref(void *ref) {
  if (!ref) return;
  uint32_t tag = *(uint32_t *)ref;
  if (tag == TAG_STRING) { free(((FakeString *)ref)->utf); free(ref); }
  else if (tag == TAG_OBJARR) { free(((FakeObjArray *)ref)->items); free(ref); }
  else if (tag == TAG_PRIARR) { free(((FakePriArray *)ref)->data); free(ref); }
  else if (tag == TAG_OBJECT) { free(ref); }
}

void delete_local(void *ref) {
  if (!ref) return;
  mutexLock(&locals_lock);
  for (int i = locals_top - 1; i >= 0; i--) {
    if (locals[i] == ref) { locals[i] = locals[--locals_top]; break; }
  }
  mutexUnlock(&locals_lock);
  free_ref(ref);
}

void jni_delete_local_ref(void *ref) { delete_local(ref); }

const char *obj_str(void *obj) {
  if (!obj) return "";
  FakeString *s = obj;
  return (s->tag == TAG_STRING && s->utf) ? s->utf : "";
}

void *jni_make_thiz(void) {
  FakeObject *o = malloc(sizeof(FakeObject));
  if (!o) return NULL;
  o->tag = TAG_OBJECT;
  snprintf(o->label, sizeof(o->label), "AppActivity");
  return reg_local(o);
}

void *jni_make_string(const char *utf) {
  FakeString *s = malloc(sizeof(FakeString));
  if (!s) return NULL;
  s->tag = TAG_STRING;
  s->utf = strdup(utf ? utf : "");
  return reg_local(s);
}

void *get_class(const char *name) {
  static FakeClass cls_pool[64];
  static int cls_count = 0;
  for (int i = 0; i < cls_count; i++) {
    if (!strcmp(cls_pool[i].label, name)) return &cls_pool[i];
  }
  if (cls_count < 64) {
    FakeClass *c = &cls_pool[cls_count++];
    c->tag = TAG_CLASS;
    snprintf(c->label, sizeof(c->label), "%s", name);
    return c;
  }
  return NULL;
}

void *get_id(const char *cls, const char *name, const char *sig) {
  static FakeID id_pool[512];
  static int id_count = 0;
  for (int i = 0; i < id_count; i++) {
    if (!strcmp(id_pool[i].cls, cls) && !strcmp(id_pool[i].name, name)) return &id_pool[i];
  }
  if (id_count < 512) {
    FakeID *id = &id_pool[id_count++];
    id->tag = TAG_ID;
    snprintf(id->cls, sizeof(id->cls), "%s", cls ? cls : "");
    snprintf(id->name, sizeof(id->name), "%s", name ? name : "");
    snprintf(id->sig, sizeof(id->sig), "%s", sig ? sig : "");
    return id;
  }
  return NULL;
}

void *new_pri_array(int len, int elem_size) {
  FakePriArray *a = malloc(sizeof(FakePriArray));
  if (!a) return NULL;
  a->tag = TAG_PRIARR;
  a->len = len;
  a->elem_size = elem_size;
  a->data = calloc(len > 0 ? (size_t)len : 1, (size_t)elem_size);
  return reg_local(a);
}

void *make_pri_array_adopt(void *data, int len, int elem_size) {
  FakePriArray *a = malloc(sizeof(FakePriArray));
  if (!a) { free(data); return NULL; }
  a->tag = TAG_PRIARR;
  a->len = len;
  a->elem_size = elem_size;
  a->data = data;
  return reg_local(a);
}

void *jni_make_input_array(int max_len, int elem_size) { return new_pri_array(max_len, elem_size); }

void jni_input_array_set(void *arr, const void *src, int n) {
  FakePriArray *a = arr;
  if (a && a->tag == TAG_PRIARR && a->data && n <= a->len) memcpy(a->data, src, (size_t)n * a->elem_size);
}

static uint64_t j_GetVersion(void *env) { (void)env; return JNI_VERSION_1_6; }
static void *j_FindClass(void *env, const char *name) { (void)env; log_inteligente_jni("JNI-FindClass", "", name, NULL); return get_class(name); }
static void *j_GetObjectClass(void *env, void *obj) { (void)env; (void)obj; return get_class("?"); }
static void *j_GetMethodID(void *env, void *cls, const char *name, const char *sig) {
  (void)env; FakeObject *c = cls;
  const char *label = (c && c->tag == TAG_CLASS) ? c->label : "";
  log_inteligente_jni("JNI-GetMethodID", label, name, sig);
  return get_id(label, name, sig);
}
static void *j_NewGlobalRef(void *env, void *obj) {
  (void)env; mutexLock(&locals_lock);
  for (int i = locals_top - 1; i >= 0; i--) if (locals[i] == obj) { locals[i] = locals[--locals_top]; break; }
  mutexUnlock(&locals_lock); return obj;
}
static void j_DeleteGlobalRef(void *env, void *obj) { (void)env; free_ref(obj); }
static void j_DeleteLocalRef(void *env, void *obj) { (void)env; delete_local(obj); }
static void *j_NewLocalRef(void *env, void *obj) { (void)env; return obj; }
static uint64_t j_IsSameObject(void *env, void *a, void *b) { (void)env; return a == b; }
static uint64_t j_EnsureLocalCapacity(void *env, int cap) { (void)env; (void)cap; return 0; }
static uint64_t j_PushLocalFrame(void *env, int cap) {
  (void)env; (void)cap; mutexLock(&locals_lock);
  if (frame_top < MAX_FRAMES) frames[frame_top++] = locals_top;
  mutexUnlock(&locals_lock); return 0;
}
static void *j_PopLocalFrame(void *env, void *result) {
  (void)env; mutexLock(&locals_lock);
  const int mark = frame_top > 0 ? frames[--frame_top] : 0;
  for (int i = mark; i < locals_top; i++) if (locals[i] != result) free_ref(locals[i]);
  locals_top = mark;
  if (result && locals_top < MAX_LOCALS) locals[locals_top++] = result;
  mutexUnlock(&locals_lock); return result;
}
static void *j_NewStringUTF(void *env, const char *utf) { (void)env; return jni_make_string(utf); }
static const char *j_GetStringUTFChars(void *env, void *jstr, uint8_t *is_copy) { (void)env; if (is_copy) *is_copy = 0; return obj_str(jstr); }
static void j_ReleaseStringUTFChars(void *env, void *jstr, const char *utf) { (void)env; (void)jstr; (void)utf; }
static uint64_t j_GetStringUTFLength(void *env, void *jstr) { (void)env; return strlen(obj_str(jstr)); }
static uint64_t j_GetStringLength(void *env, void *jstr) { (void)env; return strlen(obj_str(jstr)); }
static const uint16_t *j_GetStringChars(void *env, void *jstr, uint8_t *is_copy) {
  (void)env; if (is_copy) *is_copy = 1; const char *s = obj_str(jstr);
  size_t n = strlen(s); uint16_t *buf = malloc((n + 1) * sizeof(uint16_t));
  if (!buf) return NULL;
  for (size_t i = 0; i < n; i++) buf[i] = (uint8_t)s[i];
  buf[n] = 0; return buf;
}
static void j_ReleaseStringChars(void *env, void *jstr, const uint16_t *chars) { (void)env; (void)jstr; free((void *)chars); }
static uint64_t j_GetArrayLength(void *env, void *arr) {
  (void)env; FakeObjArray *a = arr; return (a && (a->tag == TAG_PRIARR || a->tag == TAG_OBJARR)) ? (uint64_t)a->len : 0;
}
static void *j_NewByteArray(void *env, int len) { (void)env; return new_pri_array(len, 1); }
static void *j_NewIntArray(void *env, int len) { (void)env; return new_pri_array(len, 4); }
static void *j_NewFloatArray(void *env, int len) { (void)env; return new_pri_array(len, 4); }
static void *j_NewLongArray(void *env, int len) { (void)env; return new_pri_array(len, 8); }
static void *j_GetPriArrayElements(void *env, void *arr, uint8_t *is_copy) {
  (void)env; if (is_copy) *is_copy = 0; FakePriArray *a = arr; return (a && a->tag == TAG_PRIARR) ? a->data : NULL;
}
static void j_ReleasePriArrayElements(void *env, void *arr, void *elems, int mode) { (void)env; (void)arr; (void)elems; (void)mode; }
static void j_GetPriArrayRegion(void *env, void *arr, int start, int len, void *buf) {
  (void)env; FakePriArray *a = arr;
  if (a && a->tag == TAG_PRIARR && start >= 0 && start + len <= a->len)
    memcpy(buf, (char *)a->data + (size_t)start * a->elem_size, (size_t)len * a->elem_size);
}
static void j_SetPriArrayRegion(void *env, void *arr, int start, int len, const void *buf) {
  (void)env; FakePriArray *a = arr;
  if (a && a->tag == TAG_PRIARR && start >= 0 && start + len <= a->len)
    memcpy((char *)a->data + (size_t)start * a->elem_size, buf, (size_t)len * a->elem_size);
}
static uint64_t j_RegisterNatives(void *env, void *cls, void *methods, int n) { (void)env; (void)cls; (void)methods; (void)n; return 0; }
static uint64_t j_GetJavaVM(void *env, void **vm) { (void)env; *vm = fake_vm; return JNI_OK; }
static uint64_t j_ExceptionCheck(void *env) { (void)env; return 0; }
static void *j_ExceptionOccurred(void *env) { (void)env; return NULL; }
static void j_void1(void *env) { (void)env; }
static uint64_t j_unimplemented(void) { return 0; }

static uintptr_t env_table[233];
static uintptr_t *env_table_ptr = env_table;
void *fake_env = &env_table_ptr;

static uint64_t vm_DestroyJavaVM(void *vm) { (void)vm; return JNI_OK; }
static uint64_t vm_AttachCurrentThread(void *vm, void **env, void *args) { (void)vm; (void)args; if (env) *env = fake_env; return JNI_OK; }
static uint64_t vm_DetachCurrentThread(void *vm) { (void)vm; return JNI_OK; }
static uint64_t vm_GetEnv(void *vm, void **env, int version) { (void)vm; (void)version; if (env) *env = fake_env; return JNI_OK; }

static uintptr_t vm_table[8];
static uintptr_t *vm_table_ptr = vm_table;
void *fake_vm = &vm_table_ptr;

void jni_env_basico_init(void) {
  mutexInit(&locals_lock);
  for (int i = 0; i < 233; i++) env_table[i] = (uintptr_t)j_unimplemented;

  env_table[4] = (uintptr_t)j_GetVersion;
  env_table[6] = (uintptr_t)j_FindClass;
  env_table[15] = (uintptr_t)j_ExceptionOccurred;
  env_table[16] = (uintptr_t)j_void1;
  env_table[17] = (uintptr_t)j_void1;
  env_table[19] = (uintptr_t)j_PushLocalFrame;
  env_table[20] = (uintptr_t)j_PopLocalFrame;
  env_table[21] = (uintptr_t)j_NewGlobalRef;
  env_table[22] = (uintptr_t)j_DeleteGlobalRef;
  env_table[23] = (uintptr_t)j_DeleteLocalRef;
  env_table[24] = (uintptr_t)j_IsSameObject;
  env_table[25] = (uintptr_t)j_NewLocalRef;
  env_table[26] = (uintptr_t)j_EnsureLocalCapacity;
  env_table[31] = (uintptr_t)j_GetObjectClass;
  env_table[33] = (uintptr_t)j_GetMethodID;
  env_table[34] = (uintptr_t)j_CallObjectMethod;
  env_table[35] = (uintptr_t)j_CallObjectMethodV;
  env_table[37] = (uintptr_t)j_CallBooleanMethod;
  env_table[38] = (uintptr_t)j_CallBooleanMethodV;
  env_table[49] = (uintptr_t)j_CallIntMethod;
  env_table[50] = (uintptr_t)j_CallIntMethodV;
  env_table[53] = (uintptr_t)j_CallLongMethod;
  env_table[54] = (uintptr_t)j_CallLongMethodV;
  env_table[57] = (uintptr_t)j_CallFloatMethod;
  env_table[58] = (uintptr_t)j_CallFloatMethodV;
  env_table[59] = (uintptr_t)j_CallDoubleMethod;
  env_table[60] = (uintptr_t)j_CallDoubleMethodV;
  env_table[61] = (uintptr_t)j_CallVoidMethod;
  env_table[62] = (uintptr_t)j_CallVoidMethodV;
  env_table[113] = (uintptr_t)j_GetMethodID;
  env_table[114] = (uintptr_t)j_CallObjectMethod;
  env_table[115] = (uintptr_t)j_CallObjectMethodV;
  env_table[117] = (uintptr_t)j_CallBooleanMethod;
  env_table[118] = (uintptr_t)j_CallBooleanMethodV;
  env_table[129] = (uintptr_t)j_CallIntMethod;
  env_table[130] = (uintptr_t)j_CallIntMethodV;
  env_table[132] = (uintptr_t)j_CallLongMethod;
  env_table[133] = (uintptr_t)j_CallLongMethodV;
  env_table[135] = (uintptr_t)j_CallFloatMethod;
  env_table[136] = (uintptr_t)j_CallFloatMethodV;
  env_table[138] = (uintptr_t)j_CallDoubleMethod;
  env_table[139] = (uintptr_t)j_CallDoubleMethodV;
  env_table[141] = (uintptr_t)j_CallVoidMethod;
  env_table[142] = (uintptr_t)j_CallVoidMethodV;
  env_table[144] = (uintptr_t)j_GetMethodID;
  env_table[164] = (uintptr_t)j_GetStringLength;
  env_table[165] = (uintptr_t)j_GetStringChars;
  env_table[166] = (uintptr_t)j_ReleaseStringChars;
  env_table[167] = (uintptr_t)j_NewStringUTF;
  env_table[168] = (uintptr_t)j_GetStringUTFLength;
  env_table[169] = (uintptr_t)j_GetStringUTFChars;
  env_table[170] = (uintptr_t)j_ReleaseStringUTFChars;
  env_table[171] = (uintptr_t)j_GetArrayLength;
  env_table[176] = (uintptr_t)j_NewByteArray;
  env_table[179] = (uintptr_t)j_NewIntArray;
  env_table[180] = (uintptr_t)j_NewLongArray;
  env_table[181] = (uintptr_t)j_NewFloatArray;
  for (int i = 183; i <= 190; i++) env_table[i] = (uintptr_t)j_GetPriArrayElements;
  for (int i = 191; i <= 198; i++) env_table[i] = (uintptr_t)j_ReleasePriArrayElements;
  for (int i = 199; i <= 206; i++) env_table[i] = (uintptr_t)j_GetPriArrayRegion;
  for (int i = 207; i <= 214; i++) env_table[i] = (uintptr_t)j_SetPriArrayRegion;
  env_table[215] = (uintptr_t)j_RegisterNatives;
  env_table[219] = (uintptr_t)j_GetJavaVM;
  env_table[222] = (uintptr_t)j_GetPriArrayElements;
  env_table[223] = (uintptr_t)j_ReleasePriArrayElements;
  env_table[226] = (uintptr_t)j_NewGlobalRef;
  env_table[227] = (uintptr_t)j_DeleteGlobalRef;
  env_table[228] = (uintptr_t)j_ExceptionCheck;

  vm_table[3] = (uintptr_t)vm_DestroyJavaVM;
  vm_table[4] = (uintptr_t)vm_AttachCurrentThread;
  vm_table[5] = (uintptr_t)vm_DetachCurrentThread;
  vm_table[6] = (uintptr_t)vm_GetEnv;
  vm_table[7] = (uintptr_t)vm_AttachCurrentThread;
}
