#ifndef JNI_ENV_BASICO_H
#define JNI_ENV_BASICO_H

#include <stdint.h>
#include <stddef.h>
#include "jni_interno.h"

// Variáveis globais de interface JNI
extern void *fake_vm;
extern void *fake_env;

// Inicializa a tabela JNIEnv e JavaVM com os métodos da especificação
void jni_env_basico_init(void);

// Utilitários de tipos e extração de strings
const char *obj_str(void *obj);
void *get_class(const char *name);
void *get_id(const char *cls, const char *name, const char *sig);

// Construtores de objetos simulados e strings JNI
void *jni_make_thiz(void);
void *jni_make_string(const char *utf);
void jni_delete_local_ref(void *ref);

// Manipulação de arrays primitivos persistentes (usados no input e shaders)
void *jni_make_input_array(int max_len, int elem_size);
void jni_input_array_set(void *arr, const void *src, int n);
void *new_pri_array(int len, int elem_size);
void *make_pri_array_adopt(void *data, int len, int elem_size);

#endif // JNI_ENV_BASICO_H
