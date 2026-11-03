#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "config.h"
#include "entradas_cocos.h"
#include "jni_fake.h"
#include "toque_virtual.h"

extern int g_is_in_game;

// Estrutura de rastreamento de toques multitouch
typedef struct {
  int ativo;
  float x;
  float y;
} PonteiroToque;

float g_cursor_x = 320.0f;
float g_cursor_y = 240.0f;
float g_cursor_alpha = 1.0f;
static int s_temporizador_cursor = 300;

static PonteiroToque s_toques_atuais[4] = {{0}};
static void *s_array_ids = NULL;
static void *s_array_xs  = NULL;
static void *s_array_ys  = NULL;

static float limitar_valor(float v, float min, float max) {
  return v < min ? min : (v > max ? max : v);
}

void toque_virtual_init(void) {
  if (!s_array_ids) {
    s_array_ids = jni_make_input_array(4, 4);
    s_array_xs  = jni_make_input_array(4, 4);
    s_array_ys  = jni_make_input_array(4, 4);
  }
}

void toque_virtual_definir_posicao(float x, float y) {
  g_cursor_x = limitar_valor(x, 0.0f, (float)screen_width);
  g_cursor_y = limitar_valor(y, 0.0f, (float)screen_height);
  s_temporizador_cursor = 240;
  g_cursor_alpha = 1.0f;
}

void toque_virtual_processar(float dx, float dy, int clique, int freio, int gas) {
  toque_virtual_init();

  // Movimenta o cursor apenas se nao houver clique em andamento
  // Trava de clique: impede que o drift transforme cliques em gestos de arrasto (swipes)
  if (!clique && (dx != 0.0f || dy != 0.0f)) {
    g_cursor_x += dx;
    g_cursor_y += dy;
    s_temporizador_cursor = 240;
    g_cursor_alpha = 1.0f;
  }

  g_cursor_x = limitar_valor(g_cursor_x, 0.0f, (float)screen_width);
  g_cursor_y = limitar_valor(g_cursor_y, 0.0f, (float)screen_height);

  if (clique) {
    s_temporizador_cursor = 240;
    g_cursor_alpha = 1.0f;
  } else if (s_temporizador_cursor > 0) {
    s_temporizador_cursor--;
    g_cursor_alpha = 1.0f;
  } else if (g_cursor_alpha > 0.0f) {
    g_cursor_alpha -= 0.02f;
    if (g_cursor_alpha < 0.0f) g_cursor_alpha = 0.0f;
  }

  // Prepara novos estados dos 4 pontos de toque
  PonteiroToque novos_toques[4] = {{0}};

  // [0] Cursor virtual de navegacao
  if (clique) {
    novos_toques[0].ativo = 1;
    novos_toques[0].x = g_cursor_x;
    novos_toques[0].y = g_cursor_y;
  }

  // [1] Pedal de freio (canto inferior esquerdo) - ativo SOMENTE em corrida
  if (g_is_in_game && freio) {
    novos_toques[1].ativo = 1;
    novos_toques[1].x = screen_width * 0.16f;
    novos_toques[1].y = screen_height * 0.82f;
  }

  // [2] Pedal de acelerador (canto inferior direito) - ativo SOMENTE em corrida
  if (g_is_in_game && gas) {
    novos_toques[2].ativo = 1;
    novos_toques[2].x = screen_width * 0.84f;
    novos_toques[2].y = screen_height * 0.82f;
  }

  // Despacho de inicios de toque (nativeTouchesBegin)
  for (int i = 0; i < 4; i++) {
    if (novos_toques[i].ativo && !s_toques_atuais[i].ativo && nativeTouchesBegin) {
      if (i == 0) {
        printf("[CONTROLES CLIQUE] Toque cursor Begin: x=%.1f, y=%.1f (Tela: %dx%d)\n",
               novos_toques[i].x, novos_toques[i].y, screen_width, screen_height);
        fflush(stdout);
      }
      nativeTouchesBegin(fake_env, renderer_cls, i, novos_toques[i].x, novos_toques[i].y);
    }
  }

  // Despacho de movimentos de toque (nativeTouchesMove)
  int ids[4]; float xs[4], ys[4]; int qtd_mov = 0;
  for (int i = 0; i < 4; i++) {
    if (novos_toques[i].ativo && s_toques_atuais[i].ativo &&
        (novos_toques[i].x != s_toques_atuais[i].x || novos_toques[i].y != s_toques_atuais[i].y)) {
      ids[qtd_mov] = i;
      xs[qtd_mov] = novos_toques[i].x;
      ys[qtd_mov] = novos_toques[i].y;
      qtd_mov++;
    }
  }
  if (qtd_mov > 0 && nativeTouchesMove) {
    jni_input_array_set(s_array_ids, ids, qtd_mov);
    jni_input_array_set(s_array_xs, xs, qtd_mov);
    jni_input_array_set(s_array_ys, ys, qtd_mov);
    nativeTouchesMove(fake_env, renderer_cls, s_array_ids, s_array_xs, s_array_ys);
  }

  // Despacho de fins de toque (nativeTouchesEnd)
  for (int i = 0; i < 4; i++) {
    if (!novos_toques[i].ativo && s_toques_atuais[i].ativo && nativeTouchesEnd) {
      if (i == 0) {
        printf("[CONTROLES CLIQUE] Toque cursor End: x=%.1f, y=%.1f\n",
               s_toques_atuais[i].x, s_toques_atuais[i].y);
        fflush(stdout);
      }
      nativeTouchesEnd(fake_env, renderer_cls, i, s_toques_atuais[i].x, s_toques_atuais[i].y);
    }
  }

  memcpy(s_toques_atuais, novos_toques, sizeof(s_toques_atuais));
}

