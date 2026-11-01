/* config.c -- simple configuration parser
 *
 * Copyright (C) 2021 Andy Nguyen, fgsfds
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

#include "config.h"

#define CONFIG_VARS \
  CONFIG_VAR_INT(screen_width); \
  CONFIG_VAR_INT(screen_height); \
  CONFIG_VAR_INT(language); \
  CONFIG_VAR_INT(cursor_speed); \
  CONFIG_VAR_INT(audio_rate); \
  CONFIG_VAR_INT(music_volume); \
  CONFIG_VAR_INT(effects_volume); \
  CONFIG_VAR_INT(engine_volume); \
  CONFIG_VAR_INT(btn_gas); \
  CONFIG_VAR_INT(btn_brake); \
  CONFIG_VAR_INT(btn_click); \
  CONFIG_VAR_INT(btn_boost); \
  CONFIG_VAR_INT(btn_pause);

Config config;

// actual screen size that is in use right now
int screen_width = 1280;
int screen_height = 720;

static inline void parse_var(const char *name, const char *value) {
  #define CONFIG_VAR_INT(var) if (!strcmp(name, #var)) { config.var = atoi(value); return; }
  CONFIG_VARS
  #undef CONFIG_VAR_INT
}

int read_config(const char *file) {
  char line[1024] = { 0 };

  memset(&config, 0, sizeof(Config));
  config.screen_width = -1; // auto
  config.screen_height = -1;
  config.language = LANG_EN;
  config.cursor_speed = 32;
  config.audio_rate = 0; // 0 = honor CRIWARE's requested player rate (44100).
                         // Only set a value here if the audio speed still needs
                         // tuning after the clock_nanosleep fix.
  config.music_volume = 35;   // padrao 35%
  config.effects_volume = 50; // padrao 50%
  config.engine_volume = 100; // padrao 100%
  config.btn_gas = 0;         // SDL_CONTROLLER_BUTTON_A (0)
  config.btn_brake = 3;       // SDL_CONTROLLER_BUTTON_Y (3)
  config.btn_click = 1;       // SDL_CONTROLLER_BUTTON_B (1)
  config.btn_boost = 2;       // SDL_CONTROLLER_BUTTON_X (2)
  config.btn_pause = 6;       // SDL_CONTROLLER_BUTTON_START (6)

  FILE *f = fopen(file, "r");
  if (f == NULL) {
    f = fopen("/roms/ports/hcr/config.txt", "r");
    if (f == NULL)
      return -1;
  }

  // parse lines of the forms
  // <spaces> # <whatever> \n
  // <spaces> NAME <spaces> VALUE <spaces> \n
  do {
    char *name = NULL, *value = NULL, *tmp = NULL;
    if (fgets(line, sizeof(line), f) != NULL) {
      name = line;
      // trim name
      while (*name && isspace((unsigned char)*name)) ++name;
      if (name[0] == '#') continue; // skip comments
      for (tmp = name; *tmp && !isspace((unsigned char)*tmp); ++tmp);
      // if tmp points to the end of the string, there's no value to parse
      if (*tmp != 0) {
        *tmp = 0;
        // value is next; trim value
        for (value = tmp + 1; *value && isspace((unsigned char)*value); ++value);
        char *end = value + strlen(value);
        while (end > value && isspace((unsigned char)end[-1])) --end;
        *end = 0;
        // got key value pair
        parse_var(name, value);
      }
    }
  } while (!feof(f));

  fclose(f);
  return 0;
}

int write_config(const char *file) {
  FILE *f = fopen(file, "w");
  if (f == NULL) {
    f = fopen("/roms/ports/hcr/config.txt", "w");
    if (f == NULL)
      return -1;
  }

  #define CONFIG_VAR_INT(var) fprintf(f, "%s %d\n", #var, config.var)
  CONFIG_VARS
  #undef CONFIG_VAR_INT

  fflush(f);
  fsync(fileno(f));
  fclose(f);

  // Garantir espelho em /roms/ports/hcr/config.txt se file foi caminho relativo
  if (strcmp(file, "/roms/ports/hcr/config.txt") != 0) {
    FILE *f2 = fopen("/roms/ports/hcr/config.txt", "w");
    if (f2 != NULL) {
      #define CONFIG_VAR_INT(var) fprintf(f2, "%s %d\n", #var, config.var)
      CONFIG_VARS
      #undef CONFIG_VAR_INT
      fflush(f2);
      fsync(fileno(f2));
      fclose(f2);
    }
  }

  return 0;
}
