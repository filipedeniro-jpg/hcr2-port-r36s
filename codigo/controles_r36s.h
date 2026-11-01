#ifndef CONTROLES_R36S_H
#define CONTROLES_R36S_H

#include "toque_virtual.h"

#ifdef __cplusplus
extern "C" {
#endif

// Inicializa joysticks e mapeamentos SDL GameController
void controles_r36s_init(void);

// Processa eventos de entrada, atalhos universais e despacha ao toque virtual
void update_input(void);

#ifdef __cplusplus
}
#endif

#endif // CONTROLES_R36S_H
