#include <stdio.h>
#include <stdint.h>
#include <string.h>

#include "carregador_atlas.h"
#include "so_util.h"

extern so_module game_mod;

// Estrutura de string C++ compativel com ABI libc++ (LLVM/Android NDK)
// Em modo SSO (Short String Optimization), strings com ate 22 bytes cabem diretamente
typedef struct {
  char dados[24];
} cpp_string;

static cpp_string criar_cpp_string(const char *str) {
  cpp_string s;
  memset(&s, 0, sizeof(s));
  size_t len = strlen(str);
  if (len < 23) {
    s.dados[0] = (char)(len << 1);
    memcpy(&s.dados[1], str, len + 1);
  }
  return s;
}

// Carrega um atlas .plist diretamente no SpriteFrameCache do Cocos2d-x
static void carregar_plist_no_cache(void *cache, const char *caminho_plist) {
  if (!cache || !caminho_plist)
    return;

  // Offsets confirmados por desmontagem no binario libcocos2dcpp.so:
  // 0x006a308c: SpriteFrameCache::addSpriteFramesWithFile(const std::string&)
  void (*adicionar_frames)(void *this_ptr, void *str_ptr) =
      (void *)((uintptr_t)game_mod.load_base + 0x006a308c);

  cpp_string str = criar_cpp_string(caminho_plist);
  adicionar_frames(cache, &str);

  printf("[ATLAS CARREGADO] SpriteFrameCache populado com sucesso: '%s'\n", caminho_plist);
  fflush(stdout);
}

void carregar_atlases_essenciais(void) {
  // 0x006a28e0: SpriteFrameCache::getInstance()
  void *(*obter_cache)(void) = (void *)((uintptr_t)game_mod.load_base + 0x006a28e0);
  void *cache = obter_cache ? obter_cache() : NULL;
  if (!cache) {
    printf("[ATLAS ERRO] Nao foi possivel obter instancia do SpriteFrameCache!\n");
    fflush(stdout);
    return;
  }

  printf("\n[ATLAS] >>> Populando SpriteFrameCache com todos os atlases essenciais... <<<\n");
  fflush(stdout);

  static const char *atlases[] = {
    "HD/game.plist",              // HUD completo: pedais de acelerador e freio, velocimetro, RPM, turbo boost
    "HD/flag_atlas.plist",        // Bandeiras nacionais dos oponentes e jogadores (SpriteFrameCache)
    "HD/collectibles.plist",      // Moedas de ouro e prata, diamantes e latas de combustivel
    "HD/particles.plist",         // Efeitos visuais de fumaça, faíscas e poeira
    "HD/tuning_parts.plist",      // Peças de aprimoramento e upgrades de veículos
    "HD/vehicles1.plist",         // Chassis, rodas e suspensões dos veículos principais (Jeep, etc.)
    "HD/vehicles2.plist",         // Veículos secundários e variantes
    "HD/vehicles3.plist",         // Veículos pesados e motos
    "HD/vehicles4.plist",         // Veículos especiais
    "HD/vehicles5.plist",         // Veículos de temporada
    "HD/drivers1.plist",          // Piloto padrão: torsos, pernas, braços e capacetes
    "HD/drivers2.plist",          // Variantes de pilotos e skins
    "HD/countryside_gfx_01.plist",// Cenário da primeira pista (Countryside/Tutorial)
    "HD/forest2_gfx.plist",       // Cenário da pista Forest
    "HD/city_gfx.plist",          // Cenário da pista City
    "HD/mountain_gfx_01.plist",   // Cenário da pista Mountain
    "HD/beach_gfx_01.plist",      // Cenário da pista Beach
    "HD/desert_gfx_01.plist",     // Cenário da pista Desert
    "HD/mine_gfx.plist",          // Cenário da pista Mine
    "HD/glacier_gfx_01.plist"     // Cenário da pista Glacier
  };

  for (size_t i = 0; i < sizeof(atlases) / sizeof(atlases[0]); i++) {
    carregar_plist_no_cache(cache, atlases[i]);
  }

  printf("[ATLAS] >>> Todos os %zu atlases populados com sucesso no SpriteFrameCache! <<<\n\n",
         sizeof(atlases) / sizeof(atlases[0]));
  fflush(stdout);
}
