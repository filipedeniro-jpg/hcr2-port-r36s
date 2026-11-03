#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <SDL2/SDL.h>

#include "config.h"
#include "controles_r36s.h"
#include "entradas_cocos.h"
#include "jni_fake.h"
#include "overlay_menu.h"
#include "toque_virtual.h"

#define CODIGO_BOTAO_X 99
#define CODIGO_BOTAO_Y 100
#define CODIGO_BOTAO_VOLTAR 4

static int s_gas_pressionado = 0;
static int s_freio_pressionado = 0;
static int s_clique_pressionado = 0;
static float s_rstick_x = 0.0f;
static float s_rstick_y = 0.0f;
static int s_tem_gamecontroller = 0;
static int s_tecla_fn_ativa = 0;

extern int g_is_in_game;

static void pausar_jogo_start(void) {
  static uint32_t s_ultimo_start = 0;
  uint32_t agora = SDL_GetTicks();
  if (agora - s_ultimo_start > 350) {
    s_ultimo_start = agora;
    printf("[CONTROLES] START acionado! Despachando nativeKeyEvent(AKEYCODE_BACK=4) e toque no botao Pause...\n");
    fflush(stdout);
    if (nativeKeyEvent) {
      nativeKeyEvent(fake_env, renderer_cls, CODIGO_BOTAO_VOLTAR, 1);
      nativeKeyEvent(fake_env, renderer_cls, CODIGO_BOTAO_VOLTAR, 0);
    } else if (nativeKeyDown) {
      nativeKeyDown(fake_env, renderer_cls, CODIGO_BOTAO_VOLTAR);
    }
    toque_virtual_acionar_pause();
  }
}

void controles_r36s_init(void) {
  if (SDL_GameControllerAddMappingsFromFile("gamecontrollerdb.txt") > 0) {
    printf("[HCR] Base gamecontrollerdb.txt carregada com sucesso.\n");
  }
  int qtd_joysticks = SDL_NumJoysticks();
  printf("[HCR] Detectados %d joystick(s) no sistema.\n", qtd_joysticks);
  for (int i = 0; i < qtd_joysticks; i++) {
    if (SDL_IsGameController(i)) {
      SDL_GameController *ctrl = SDL_GameControllerOpen(i);
      if (ctrl) {
        s_tem_gamecontroller = 1;
        char *mapa = SDL_GameControllerMapping(ctrl);
        printf("[HCR] GameController %d aberto: '%s'\n", i, SDL_GameControllerName(ctrl));
        printf("[HCR] Mapeamento ativo: %s\n", mapa ? mapa : "padrao SDL");
        if (mapa) SDL_free(mapa);
      }
    } else {
      SDL_Joystick *joy = SDL_JoystickOpen(i);
      if (joy) {
        printf("[HCR] Joystick %d aberto (raw): '%s' (eixos: %d, botoes: %d)\n",
               i, SDL_JoystickNameForIndex(i), SDL_JoystickNumAxes(joy), SDL_JoystickNumButtons(joy));
      }
    }
  }
  fflush(stdout);
}

void update_input(void) {
  SDL_Event ev;
  float mouse_dx = 0.0f;
  float mouse_dy = 0.0f;

  while (SDL_PollEvent(&ev)) {
    // 1. Verificacao de Atalhos Universais (Hotkeys): SELECT + START encerra
    if (ev.type == SDL_CONTROLLERBUTTONDOWN) {
      if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_BACK || ev.cbutton.button == SDL_CONTROLLER_BUTTON_GUIDE) {
        s_tecla_fn_ativa = 1;
      }
      if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_START && s_tecla_fn_ativa) {
        printf("[HCR] SELECT + START acionado! Encerrando jogo...\n");
        fflush(stdout);
        jni_quit_requested = 1;
        continue;
      }
      if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_X && s_tecla_fn_ativa) {
        overlay_menu_toggle();
        continue;
      }

      int btn = ev.cbutton.button;
      if (btn == config.btn_gas || btn == SDL_CONTROLLER_BUTTON_A || btn == SDL_CONTROLLER_BUTTON_RIGHTSHOULDER) {
        s_gas_pressionado = 1;
      }
      if (btn == config.btn_click || btn == SDL_CONTROLLER_BUTTON_B) {
        s_clique_pressionado = 1;
      }
      if (btn == config.btn_brake || btn == SDL_CONTROLLER_BUTTON_Y || btn == SDL_CONTROLLER_BUTTON_LEFTSHOULDER) {
        s_freio_pressionado = 1;
      }
      if (btn == SDL_CONTROLLER_BUTTON_DPAD_RIGHT) s_gas_pressionado = 1;
      if (btn == SDL_CONTROLLER_BUTTON_DPAD_LEFT)  s_freio_pressionado = 1;

      if (btn == config.btn_boost && nativeHcrControllerKeyEvent) {
        nativeHcrControllerKeyEvent(fake_env, renderer_cls, CODIGO_BOTAO_X, 1);
      }
      if ((btn == config.btn_pause || btn == SDL_CONTROLLER_BUTTON_START) && !s_tecla_fn_ativa) {
        pausar_jogo_start();
      }
      if (btn == SDL_CONTROLLER_BUTTON_BACK) freeshop_add_cheat_money();
    } else if (ev.type == SDL_CONTROLLERBUTTONUP) {
      if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_BACK || ev.cbutton.button == SDL_CONTROLLER_BUTTON_GUIDE) {
        s_tecla_fn_ativa = 0;
      }
      int btn = ev.cbutton.button;
      if (btn == config.btn_gas || btn == SDL_CONTROLLER_BUTTON_A || btn == SDL_CONTROLLER_BUTTON_RIGHTSHOULDER) {
        s_gas_pressionado = 0;
      }
      if (btn == config.btn_click || btn == SDL_CONTROLLER_BUTTON_B) {
        s_clique_pressionado = 0;
      }
      if (btn == config.btn_brake || btn == SDL_CONTROLLER_BUTTON_Y || btn == SDL_CONTROLLER_BUTTON_LEFTSHOULDER) {
        s_freio_pressionado = 0;
      }
      if (btn == SDL_CONTROLLER_BUTTON_DPAD_RIGHT) s_gas_pressionado = 0;
      if (btn == SDL_CONTROLLER_BUTTON_DPAD_LEFT)  s_freio_pressionado = 0;

      if (btn == config.btn_boost && nativeHcrControllerKeyEvent) {
        nativeHcrControllerKeyEvent(fake_env, renderer_cls, CODIGO_BOTAO_X, 0);
      }
    } else if (ev.type == SDL_CONTROLLERAXISMOTION) {
      if (ev.caxis.axis == SDL_CONTROLLER_AXIS_TRIGGERRIGHT) {
        s_gas_pressionado = (ev.caxis.value > 8000);
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_TRIGGERLEFT) {
        s_freio_pressionado = (ev.caxis.value > 8000);
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_LEFTX) {
        float lx = (float)ev.caxis.value / 32767.0f;
        if (lx > 0.40f) s_gas_pressionado = 1;
        else if (lx < -0.40f) s_freio_pressionado = 1;
        else { s_gas_pressionado = 0; s_freio_pressionado = 0; }
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_RIGHTX) {
        float val = (float)ev.caxis.value / 32767.0f;
        s_rstick_x = (fabsf(val) > 0.25f) ? val : 0.0f;
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_RIGHTY) {
        float val = (float)ev.caxis.value / 32767.0f;
        s_rstick_y = (fabsf(val) > 0.25f) ? val : 0.0f;
      }
    } else if (ev.type == SDL_JOYHATMOTION) {
      if (ev.jhat.value & SDL_HAT_RIGHT) {
        s_gas_pressionado = 1;
      } else if (ev.jhat.value & SDL_HAT_LEFT) {
        s_freio_pressionado = 1;
      } else {
        s_gas_pressionado = 0;
        s_freio_pressionado = 0;
      }
    } else if (ev.type == SDL_JOYBUTTONDOWN) {
      if (s_tem_gamecontroller) continue;
      if (ev.jbutton.button == 6 || ev.jbutton.button == 10) s_tecla_fn_ativa = 1;
      if ((ev.jbutton.button == 7 || ev.jbutton.button == 9) && s_tecla_fn_ativa) {
        printf("[HCR] SELECT + START acionado via joystick raw! Encerrando...\n");
        fflush(stdout);
        jni_quit_requested = 1;
        continue;
      }
      int btn = ev.jbutton.button;
      if (btn == config.btn_gas || btn == 0 || btn == 5 || btn == 9 || btn == 14) s_gas_pressionado = 1;
      if (btn == config.btn_click || btn == 1)                                       s_clique_pressionado = 1;
      if (btn == config.btn_brake || btn == 3 || btn == 4 || btn == 8 || btn == 13) s_freio_pressionado = 1;
      if ((btn == config.btn_pause || btn == 7 || btn == 9) && !s_tecla_fn_ativa) {
        pausar_jogo_start();
      }
      if (btn == 6) freeshop_add_cheat_money();
    } else if (ev.type == SDL_JOYBUTTONUP) {
      if (s_tem_gamecontroller) continue;
      if (ev.jbutton.button == 6 || ev.jbutton.button == 10) s_tecla_fn_ativa = 0;
      int btn = ev.jbutton.button;
      if (btn == config.btn_gas || btn == 0 || btn == 5 || btn == 9 || btn == 14) s_gas_pressionado = 0;
      if (btn == config.btn_click || btn == 1)                                       s_clique_pressionado = 0;
      if (btn == config.btn_brake || btn == 3 || btn == 4 || btn == 8 || btn == 13) s_freio_pressionado = 0;
    } else if (ev.type == SDL_JOYAXISMOTION) {
      if (s_tem_gamecontroller) continue;
      if (ev.jaxis.axis == 0) {
        float lx = (float)ev.jaxis.value / 32767.0f;
        if (lx > 0.40f) s_gas_pressionado = 1;
        else if (lx < -0.40f) s_freio_pressionado = 1;
        else { s_gas_pressionado = 0; s_freio_pressionado = 0; }
      } else if (ev.jaxis.axis == 2) {
        float val = (float)ev.jaxis.value / 32767.0f;
        s_rstick_x = (fabsf(val) > 0.25f) ? val : 0.0f;
      } else if (ev.jaxis.axis == 3) {
        float val = (float)ev.jaxis.value / 32767.0f;
        s_rstick_y = (fabsf(val) > 0.25f) ? val : 0.0f;
      }
    } else if (ev.type == SDL_MOUSEBUTTONDOWN) {
      s_clique_pressionado = 1;
      toque_virtual_definir_posicao((float)ev.button.x, (float)ev.button.y);
    } else if (ev.type == SDL_MOUSEBUTTONUP) {
      s_clique_pressionado = 0;
    } else if (ev.type == SDL_MOUSEMOTION) {
      if (ev.motion.which == SDL_TOUCH_MOUSEID) {
        toque_virtual_definir_posicao((float)ev.motion.x, (float)ev.motion.y);
      } else {
        float fator = (config.cursor_speed > 0) ? ((float)config.cursor_speed / 32.0f) : 1.0f;
        mouse_dx += (float)ev.motion.xrel * fator;
        mouse_dy += (float)ev.motion.yrel * fator;
      }
    } else if (ev.type == SDL_KEYDOWN) {
      if (ev.key.repeat) break;
      if (ev.key.keysym.sym == SDLK_RIGHT || ev.key.keysym.sym == SDLK_d) s_gas_pressionado = 1;
      if (ev.key.keysym.sym == SDLK_LEFT  || ev.key.keysym.sym == SDLK_a) s_freio_pressionado = 1;
      if (ev.key.keysym.sym == SDLK_SPACE || ev.key.keysym.sym == SDLK_RETURN ||
          ev.key.keysym.sym == SDLK_z     || ev.key.keysym.sym == SDLK_LCTRL) {
        s_clique_pressionado = 1;
      }
      if (ev.key.keysym.sym == SDLK_c) freeshop_add_cheat_money();
      if (ev.key.keysym.sym == SDLK_ESCAPE || ev.key.keysym.sym == SDLK_p || ev.key.keysym.sym == SDLK_PAUSE) {
        pausar_jogo_start();
      }
    } else if (ev.type == SDL_KEYUP) {
      if (ev.key.keysym.sym == SDLK_RIGHT || ev.key.keysym.sym == SDLK_d) s_gas_pressionado = 0;
      if (ev.key.keysym.sym == SDLK_LEFT  || ev.key.keysym.sym == SDLK_a) s_freio_pressionado = 0;
      if (ev.key.keysym.sym == SDLK_SPACE || ev.key.keysym.sym == SDLK_RETURN ||
          ev.key.keysym.sym == SDLK_z     || ev.key.keysym.sym == SDLK_LCTRL) {
        s_clique_pressionado = 0;
      }
    }
  }

  // Calculo de deslocamento pelo analogico direito e mouse
  float vel_cursor = (config.cursor_speed > 0) ? ((float)config.cursor_speed * 0.28f) : 9.0f;
  float dx = (s_rstick_x * vel_cursor) + mouse_dx;
  float dy = (s_rstick_y * vel_cursor) + mouse_dy;

  toque_virtual_processar(dx, dy, s_clique_pressionado, s_freio_pressionado, s_gas_pressionado);
}
