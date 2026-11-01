#ifndef CARREGADOR_ATLAS_H
#define CARREGADOR_ATLAS_H

#ifdef __cplusplus
extern "C" {
#endif

// Inicializa e carrega diretamente no SpriteFrameCache do Cocos2d-x
// todos os atlas essenciais (.plist) de HUD, veículos, moedas e pistas
void carregar_atlases_essenciais(void);

#ifdef __cplusplus
}
#endif

#endif // CARREGADOR_ATLAS_H
