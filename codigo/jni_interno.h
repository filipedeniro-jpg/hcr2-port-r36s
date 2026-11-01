#ifndef JNI_INTERNO_H
#define JNI_INTERNO_H

#include <stdint.h>
#include <stddef.h>
#include <stdarg.h>

enum {
  TAG_OBJECT = 0x4f424a31, // 'OBJ1'
  TAG_STRING = 0x53545231, // 'STR1'
  TAG_OBJARR = 0x4f415231, // 'OAR1'
  TAG_PRIARR = 0x50415231, // 'PAR1'
  TAG_ID     = 0x4d494431, // 'MID1'  pooled
  TAG_CLASS  = 0x434c5331, // 'CLS1'  singleton per class name
};

typedef struct { uint32_t tag; char label[96]; } FakeObject;
typedef FakeObject FakeClass;
typedef struct { uint32_t tag; char *utf; } FakeString;
typedef struct { uint32_t tag; int len; void **items; } FakeObjArray;
typedef struct { uint32_t tag; int len; int elem_size; void *data; } FakePriArray;
typedef struct {
  uint32_t tag;
  char cls[96];
  char name[96];
  char sig[128];
} FakeID;

// Gerenciamento de referências locais
void *reg_local(void *ref);
void free_ref(void *ref);
void delete_local(void *ref);

// Diretório de persistência e dados
const char *data_dir(void);

// Telemetria inteligente e de-duplicação de chamadas JNI
void log_inteligente_jni(const char *categoria, const char *classe, const char *nome, const char *sig);

// Despachantes de métodos nativos Java
void *call_object(FakeID *id, va_list va);
int   call_boolean(FakeID *id, va_list va);
int   call_byte(FakeID *id, va_list va);
int   call_char(FakeID *id, va_list va);
int   call_short(FakeID *id, va_list va);
int   call_int(FakeID *id, va_list va);
int64_t call_long(FakeID *id, va_list va);
float call_float(FakeID *id, va_list va);
double call_double(FakeID *id, va_list va);
void  call_void(FakeID *id, va_list va);

#endif // JNI_INTERNO_H
