/* installer.c -- verificacao simples de arquivos para o port Linux */

#include <stdio.h>
#include <sys/stat.h>
#include "installer.h"
#include "config.h"

static char g_error[256];

int installer_prepare_game_files(void) {
  struct stat st;
  if (stat(SO_NAME, &st) != 0) {
    snprintf(g_error, sizeof(g_error), "Nao foi possivel encontrar " SO_NAME " no diretorio do jogo.");
    return -1;
  }
  if (stat(ASSETS_DIR, &st) != 0) {
    snprintf(g_error, sizeof(g_error), "Nao foi possivel encontrar a pasta " ASSETS_DIR " no diretorio do jogo.");
    return -1;
  }
  return 0;
}

const char *installer_last_error(void) {
  return g_error[0] ? g_error : "Erro desconhecido do instalador.";
}
