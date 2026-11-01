#ifndef SINAIS_CRASH_H
#define SINAIS_CRASH_H

#include <signal.h>
#include "so_util.h"

// Instala os tratadores de sinais POSIX para crash e encerramento seguro
void install_signal_handlers(void);

// Registra os callbacks para o encerramento seguro (graceful shutdown)
void configurar_shutdown_hooks(void *env, void *renderer, void (*on_pause)(void*, void*), void (*gl_deinit)(void));

#endif // SINAIS_CRASH_H
