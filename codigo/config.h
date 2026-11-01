/* config.h -- Configuracao do Port de Hill Climb Racing 2 (R36S / Linux ARM64)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

#ifndef __CONFIG_H__
#define __CONFIG_H__

// Limite de memoria heap alocada (MB). Cobre as alocacoes de texturas,
// audio e estado nativo da cena do Cocos2d-x na memoria fisica.
#define MEMORY_MB 1024

// Modulo nativo Cocos2d-x do Hill Climb Racing 2 (v1.28.2 ARM64).
#define SO_NAME "libcocos2dcpp.so"
#define DATA_DIR "."
#define GAME_APK "base.apk"
#define OBB_NAME "unused.obb"

// Diretorio onde a arvore de assets extraida do APK reside no console.
#define ASSETS_DIR "assets"

#define CONFIG_NAME "config.txt"
// Chamadas de audio decodificadas e mixadas pelo OpenSL ES e enviadas via SDL2.
#define HCR_ENABLE_COCOS_AUDIO 1
// The Switch shop is a local free-shop. Waiting on legacy live popup-offer
// endpoints only delays shop readiness; mission/event HTTP stays available.
#define HCR_ENABLE_REMOTE_POPUPS 0

// pairip: libMyGame.so is NEEDED-linked against libpairipcore.so and imports
// ExecuteProgram, but the native game code never routes through the pairip VM
// (no call sites; JNI_OnLoad is the clean Cocos2d-x one). We satisfy the import
// with a simulacao (stub) so the loader resolves cleanly.
#define PAIRIP_STUB 0

extern int screen_width;
extern int screen_height;

// Indice de idioma do Cocos2d-x (Application::LanguageType).
// Retornado no callback JNI getCurrentLanguage.
#define LANG_EN 0
#define LANG_COUNT 20

typedef struct {
  int screen_width;
  int screen_height;
  int language;
  int cursor_speed; // virtual pointer speed (docked / no-touch play)
  int audio_rate;   // CRIWARE master-mix output rate (Hz); its content is ~32000,
                    // not the 44100 it requests -> playing at 44100 = fast-forward
  int music_volume;   // background music volume (0-100, padrao 35)
  int effects_volume; // coins/UI sound effects volume (0-100, padrao 50)
  int engine_volume;  // vehicle engine sound volume (0-200, padrao 100)
  int btn_gas;        // SDL_GameControllerButton (padrao 0 = Button A)
  int btn_brake;      // (padrao 3 = Button Y)
  int btn_click;      // (padrao 1 = Button B)
  int btn_boost;      // (padrao 2 = Button X)
  int btn_pause;      // (padrao 6 = Button Start)
} Config;

extern Config config;

int read_config(const char *file);
int write_config(const char *file);

#endif
