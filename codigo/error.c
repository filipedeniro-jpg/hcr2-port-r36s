/* erro.c -- erro handler for Linux ARM64 */

#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>

#include "error.h"

void fatal_error(const char *fmt, ...) {
  va_list list;
  va_start(list, fmt);
  fprintf(stderr, "\n[FATAL ERROR] ");
  vfprintf(stderr, fmt, list);
  fprintf(stderr, "\n\n");
  va_end(list);
  fflush(stderr);
  exit(1);
}
