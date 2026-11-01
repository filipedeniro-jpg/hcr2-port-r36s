#ifndef HOTPATCHES_ARM64_H
#define HOTPATCHES_ARM64_H

#include "so_util.h"

// Aplica todos os hotpatches em lote na memória da libcocos2dcpp.so do HCR2
void aplicar_hotpatches_arm64(void);

// Hotpatch contra validação offline de savegame ("Internal data corrupted")
void patch_validacao_savegame_offline(void);

// Hotpatch estático da barra de loading e cálculo de porcentagem
void patch_transicao_garagem_splash(void);

// Destravamento dinâmico imediato da transição para a Garagem (elimina trava de 50%)
void patch_ativar_transicao_garagem_imediata(void);

#endif // HOTPATCHES_ARM64_H
