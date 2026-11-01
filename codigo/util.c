/* util.c -- Funcoes utilitarias diversas do port
 *
 * Copyright (C) 2021 fgsfds, Andy Nguyen
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <switch.h>
#include <stdlib.h>

#include "util.h"

void tls_setup_guard(void) {
  // On Linux ARM64, glibc manages TPIDR_EL0 for pthreads automatically.
}

int ret0(void) { return 0; }
