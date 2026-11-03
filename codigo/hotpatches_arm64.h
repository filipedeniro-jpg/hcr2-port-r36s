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

// Telemetria factual do pipeline de criacao e renderizacao do TerrainBlock (0x6d2ee0 e 0x6d15b8)
void patch_instrumentacao_pipeline_terreno(void);

// Força o carregamento nativo da textura de solo anulando checagem de qualidade gráfica da Fingersoft
void patch_forcar_carregamento_solo_nativo(void);

#endif // HOTPATCHES_ARM64_H
