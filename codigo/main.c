/* main.c -- Ponto de Entrada Principal Modular do Hill Climb Racing 2 (ARM64 / R36S)
 *
 * Arquitetura Modular Estrita (SRP) - Responsabilidades delegadas para:
 *   - sinais_crash.c    : Sinais POSIX, crash handler e encerramento limpo
 *   - video_sdl.c       : Janela SDL2, contexto OpenGL ES 2 e framebuffer ArkOS
 *   - entradas_cocos.c  : Resolução de métodos JNI do Cocos2d-x e criação de bitmaps
 *   - catalogo_loja.c   : Catálogo de compras offline e ofertas IAP
 *   - hotpatches_arm64.c: Hotpatches binários ARM64 em memória e destravamento
 *   - controles_r36s.c  : Gamepad físico R36S, atalho SELECT + START e toques virtuais
 */

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>

#include <SDL2/SDL.h>
#include <GLES2/gl2.h>

#include "config.h"
#include "error.h"
#include "so_util.h"
#include "imports.h"
#include "jni_fake.h"
#include "opensles.h"
#include "installer.h"
#include "hcr_http.h"
#include "overlay.h"
#include "overlay_menu.h"
#include "text_render.h"

#include "sinais_crash.h"
#include "video_sdl.h"
#include "entradas_cocos.h"
#include "catalogo_loja.h"
#include "hotpatches_arm64.h"
#include "controles_r36s.h"
#include "carregador_atlas.h"

so_module game_mod;
static void *thiz = NULL;
static void *helper_cls = NULL;

int main(int argc, char *argv[]) {
  (void)argc; (void)argv;

  // Diretório de persistência e buffers imediatos
  chdir("/roms/ports/hcr2");
  setvbuf(stdout, NULL, _IONBF, 0);
  setvbuf(stderr, NULL, _IONBF, 0);
  install_signal_handlers();

  printf("[HCR2] Iniciando Port Nativo ARM64 do Hill Climb Racing 2 (v1.28.2)...\n");

  if (read_config(CONFIG_NAME) != 0) {
    write_config(CONFIG_NAME);
  }
  printf("[HCR2] Configuracao de tela: %dx%d\n", config.screen_width, config.screen_height);

  if (installer_prepare_game_files() != 0) {
    fatal_error("%s", installer_last_error());
  }

  check_data();
  set_screen_size(config.screen_width, config.screen_height);
  hcr_http_init();

  // Subsistema gráfico SDL2 e EGL/GLES2
  if (!sdl_gl_init()) {
    fatal_error("Falha ao inicializar contexto SDL2 e GLES2.");
  }
  printf("[HCR2] SDL2 e OpenGL ES 2 inicializados com sucesso.\n");
  restaurar_framebuffer_arkos();

  // Inicializacao proativa do subsistema de audio SDL / OpenSL ES
  printf("[HCR2] Inicializando subsistema de audio...\n");
  opensles_init();

  // Configuração dos controles físicos e atalhos do R36S
  controles_r36s_init();

  // Carregamento do binário nativo Cocos2d-x ARM64
  printf("[HCR2] Carregando modulo ELF %s...\n", SO_NAME);
  if (so_load(&game_mod, SO_NAME, NULL, 0) < 0) {
    fatal_error("Nao foi possivel carregar o binario %s.", SO_NAME);
  }
  printf("[HCR2] %s carregado em %p (tamanho: 0x%zx).\n", SO_NAME, game_mod.load_base, game_mod.load_size);

  printf("[HCR2] Resolvendo importacoes Bionic e libc...\n");
  if (vpl_resolve_imports(&game_mod) != 0) {
    fatal_error("Simbolos Bionic nao resolvidos em %s.", SO_NAME);
  }

  printf("[HCR2] Resolvendo pontos de entrada Cocos2d-x e JNI...\n");
  resolve_entry_points(&game_mod);
  if (!JNI_OnLoad || !nativeInit || !nativeRender) {
    fatal_error("Nao foi possivel resolver os pontos de entrada essenciais do Cocos2d-x.");
  }

  // Aplicação dos hotpatches em lote na memória da libcocos2dcpp.so
  printf("[HCR2] Aplicando hotpatches binários ARM64 offline...\n");
  aplicar_hotpatches_arm64();

  so_finalize(&game_mod);
  so_flush_caches(&game_mod);

  printf("[HCR2] Executando construtores da .init_array...\n");
  so_execute_init_array(&game_mod);
  printf("[HCR2] Construtores concluidos com sucesso.\n");
  so_free_temp(&game_mod);

  // Inicialização do ambiente JNI simulado
  mkdir("save", 0777);
  jni_init();
  jni_set_popup_offer_callback(initialize_offline_popup_offer);
  jni_set_bitmap_callback(on_bitmap_create);
  thiz = jni_make_thiz();
  helper_cls = thiz;
  renderer_cls = thiz;

  // Registro de hooks de encerramento seguro
  configurar_shutdown_hooks(fake_env, renderer_cls, nativeOnPause, sdl_gl_deinit);

  printf("[HCR2] Invocando JNI_OnLoad...\n");
  JNI_OnLoad(fake_vm, NULL);

  hcr_http_set_callbacks(fake_env, renderer_cls, nativeReturnPopupOfferJson, nativeReturnMissionJson);

  if (nativeSetApkPath) {
    nativeSetApkPath(fake_env, helper_cls, jni_make_string(DATA_DIR "/" GAME_APK));
  }
  if (nativeSetContext) {
    nativeSetContext(fake_env, helper_cls, thiz, NULL);
  }

  text_render_init(NULL);
  initialize_original_shop_catalog();

  printf("[HCR2] Invocando Cocos2dxRenderer.nativeInit (%dx%d)...\n", screen_width, screen_height);
  nativeInit(fake_env, renderer_cls, screen_width, screen_height);
  carregar_atlases_essenciais();

  if (nativeEulaHasBeenAccepted) {
    nativeEulaHasBeenAccepted(fake_env, renderer_cls);
  }
  if (nativeSetNetworkAvailable) {
    nativeSetNetworkAvailable(fake_env, renderer_cls, hcr_http_network_available());
  }
  if (nativeReturnCountryCode) {
    nativeReturnCountryCode(fake_env, renderer_cls, jni_make_string("BR"));
  }
  if (nativeOnRemoteConfigRead) {
    nativeOnRemoteConfigRead(fake_env, renderer_cls, 0);
  }
  if (nativeConsentInfoFinished) nativeConsentInfoFinished(fake_env, renderer_cls);
  if (nativeConsentFlowFinished) nativeConsentFlowFinished(fake_env, renderer_cls);
  if (nativeResize) {
    nativeResize(fake_env, renderer_cls, screen_width, screen_height);
  }
  if (nativeOnResume) {
    nativeOnResume(fake_env, renderer_cls);
  }
  if (nativeControllerConnected) {
    g_ctrl_name = jni_make_string("Standard Gamepad");
    nativeControllerConnected(fake_env, renderer_cls, g_ctrl_name, 0);
  }

  vpl_runtime_ready();
  printf("[HCR2] Loop principal do jogo iniciado a 60 FPS!\n");

  int s_frame_count = 0;
  int s_asset_loader_disparado = 0;
  int s_toques_pos_loader = 0;

  while (!jni_quit_requested) {
    update_input();

    static int s_last_menu_state = 0;
    int cur_menu_open = overlay_menu_is_open();
    if (cur_menu_open != s_last_menu_state) {
      s_last_menu_state = cur_menu_open;
      if (cur_menu_open) {
        if (nativeOnPause) nativeOnPause(fake_env, renderer_cls);
      } else {
        if (nativeOnResume) nativeOnResume(fake_env, renderer_cls);
      }
    }

    if (cur_menu_open) {
      opensles_pump();
      overlay_menu_render(screen_width, screen_height);
      SDL_GL_SwapWindow(get_sdl_window());
      SDL_Delay(16);
      continue;
    }

    hcr_http_pump();
    nativeRender(fake_env, renderer_cls);
    s_frame_count++;

    // Permite que os 12 plists da fila do AnimatedSplash sejam carregados antes de destravar a Garagem
    if ((g_asset_loader_notificado || s_frame_count >= 18) && !s_asset_loader_disparado) {
      s_asset_loader_disparado = 1;
      s_toques_pos_loader = 25;
      printf("\n[HCR2] >>> GATILHO EXPRESSO: Quadro %d atingido! Fila de atlas carregada. Entregando handshake para Garagem... <<<\n",
             s_frame_count);
      fflush(stdout);

      if (nativeOnAdSettingsReceived)           nativeOnAdSettingsReceived(fake_env, renderer_cls);
      if (nativeOnFirebaseRemoteConfigReceived) nativeOnFirebaseRemoteConfigReceived(fake_env, renderer_cls);
      if (nativeOnNewsDataAvailable)            nativeOnNewsDataAvailable(fake_env, renderer_cls, jni_make_string(""), 0);
      if (nativeOnNetworkConnectivityChanged)   nativeOnNetworkConnectivityChanged(fake_env, renderer_cls, 1);
      if (nativeNotifyVideoAdCompleted)         nativeNotifyVideoAdCompleted(fake_env, renderer_cls);
      if (nativeNotifyInterstitialDismissed)    nativeNotifyInterstitialDismissed(fake_env, renderer_cls);
      if (nativeOnAllIapPricesUpdated)          nativeOnAllIapPricesUpdated(fake_env, renderer_cls);
      if (nativeOnConfigurationCompleted)       nativeOnConfigurationCompleted(fake_env, renderer_cls);
      if (nativeOnGameServicesConnected)        nativeOnGameServicesConnected(fake_env, renderer_cls);
      if (nativeOnLoginSuccess)                 nativeOnLoginSuccess(fake_env, renderer_cls, 0);

      if (nativeOnDataRead) {
        void *chave_perfil = jni_make_string("player_data");
        void *dados_perfil = jni_make_string("{\"version\":1,\"coins\":1000,\"gems\":100,\"playerName\":\"Driver\",\"hasAcceptedTerms\":true,\"tutorialFinished\":true}");
        nativeOnDataRead(fake_env, renderer_cls, 1, 0, 0, chave_perfil, dados_perfil, 0);
        void *chave_cfg = jni_make_string("settings");
        void *dados_cfg = jni_make_string("{}");
        nativeOnDataRead(fake_env, renderer_cls, 1, 1, 0, chave_cfg, dados_cfg, 0);
      }
      // Ativa o destravamento imediato da transição para a Garagem
      patch_ativar_transicao_garagem_imediata();
      printf("[HCR2] >>> Handshake 100%% entregue e transicao para Garagem ativada com sucesso! <<<\n\n");
      fflush(stdout);
    }



    if (s_frame_count == 10) {
      if (nativeOnConfigurationCompleted)     nativeOnConfigurationCompleted(fake_env, renderer_cls);
      if (nativeOnNetworkConnectivityChanged) nativeOnNetworkConnectivityChanged(fake_env, renderer_cls, 1);
    } else if (s_frame_count == 25) {
      if (nativeOnJsonReceived) {
        nativeOnJsonReceived(fake_env, renderer_cls, jni_make_string("content_file"), jni_make_string("{\"version\":1,\"items\":[]}"));
      }
    }

    opensles_pump();
    if (jni_take_consent_form_request() && nativeConsentFlowFinished) {
      nativeConsentFlowFinished(fake_env, renderer_cls);
    }
    overlay_draw_cursor(g_cursor_x, g_cursor_y, g_cursor_alpha, screen_width, screen_height);
    SDL_GL_SwapWindow(get_sdl_window());

    // Telemetria inteligente de desempenho e cena a cada 30 quadros
    static uint32_t s_ultimo_tempo = 0;
    if (s_frame_count % 30 == 0 || s_frame_count == 1 || s_frame_count == 15) {
      uint32_t agora = SDL_GetTicks();
      uint32_t delta = agora - s_ultimo_tempo;
      s_ultimo_tempo = agora;
      float fps_estimado = (delta > 0 && s_frame_count > 1) ? (30.0f * 1000.0f / (float)delta) : 60.0f;

      typedef void *(*fn_director_getInstance)(void);
      fn_director_getInstance fn_dir = (fn_director_getInstance)((uintptr_t)game_mod.load_base + 0x00e590d4);
      void *dir = fn_dir ? fn_dir() : NULL;
      void *glview = dir ? *(void **)((char *)dir + 0x128) : NULL;
      const char *nome_view = cocos_obter_nome_classe_rtti(glview);

      printf("[TELEMETRIA LOOP] Quadro=%d | FPS=%.1f | Director=%p | GLView=%p [%s] | InGame=%d\n",
             s_frame_count, fps_estimado, dir, glview, nome_view, g_is_in_game);
      fflush(stdout);
    }
  }

  if (nativeOnPause) nativeOnPause(fake_env, renderer_cls);
  jni_flush_prefs();
  opensles_shutdown();
  sdl_gl_deinit();
  hcr_http_exit();

  printf("[HCR2] Jogo encerrado com sucesso. Retornando ao ArkOS...\n");
  fflush(stdout);
  sync();
  _exit(0);
}
