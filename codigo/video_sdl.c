#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <SDL2/SDL.h>
#include <GLES2/gl2.h>

#include "config.h"
#include "error.h"
#include "sinais_crash.h"
#include "video_sdl.h"

// ---------------------------------------------------------------------------
// Variáveis estáticas de vídeo SDL2 & OpenGL ES 2
// ---------------------------------------------------------------------------
static SDL_Window *s_window = NULL;
static SDL_GLContext s_context = NULL;

SDL_Window *get_sdl_window(void) {
  return s_window;
}

SDL_GLContext get_sdl_gl_context(void) {
  return s_context;
}

void set_screen_size(int w, int h) {
  if (w <= 0 || h <= 0) {
    screen_width = 640;
    screen_height = 480;
  } else {
    screen_width = w;
    screen_height = h;
  }
}

void check_data(void) {
  struct stat st;
  const char *files[] = { SO_NAME };
  for (unsigned i = 0; i < sizeof(files) / sizeof(*files); i++) {
    if (stat(files[i], &st) < 0) {
      fatal_error("Nao foi possivel encontrar %s.\nVerifique os arquivos do jogo.", files[i]);
    }
  }
  if (stat(ASSETS_DIR, &st) < 0 || !S_ISDIR(st.st_mode)) {
    fatal_error("Nao foi possivel encontrar o diretorio \'%s\'.\nCertifique-se de que os assets foram extraidos.", ASSETS_DIR);
  }
}

int sdl_gl_init(void) {
  SDL_SetHint(SDL_HINT_NO_SIGNAL_HANDLERS, "1");
  if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO | SDL_INIT_JOYSTICK | SDL_INIT_GAMECONTROLLER | SDL_INIT_NOPARACHUTE) < 0) {
    fatal_error("Falha em SDL_Init: %s", SDL_GetError());
    return 0;
  }
  install_signal_handlers();

  SDL_GL_SetAttribute(SDL_GL_CONTEXT_PROFILE_MASK, SDL_GL_CONTEXT_PROFILE_ES);
  SDL_GL_SetAttribute(SDL_GL_CONTEXT_MAJOR_VERSION, 2);
  SDL_GL_SetAttribute(SDL_GL_CONTEXT_MINOR_VERSION, 0);
  SDL_GL_SetAttribute(SDL_GL_RED_SIZE, 8);
  SDL_GL_SetAttribute(SDL_GL_GREEN_SIZE, 8);
  SDL_GL_SetAttribute(SDL_GL_BLUE_SIZE, 8);
  SDL_GL_SetAttribute(SDL_GL_ALPHA_SIZE, 8);
  SDL_GL_SetAttribute(SDL_GL_DEPTH_SIZE, 24);
  SDL_GL_SetAttribute(SDL_GL_STENCIL_SIZE, 8);
  SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);

  s_window = SDL_CreateWindow(
    "Hill Climb Racing",
    SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
    screen_width, screen_height,
    SDL_WINDOW_OPENGL | SDL_WINDOW_FULLSCREEN
  );

  if (!s_window) {
    fatal_error("Falha em SDL_CreateWindow: %s", SDL_GetError());
    return 0;
  }

  s_context = SDL_GL_CreateContext(s_window);
  if (!s_context) {
    fatal_error("Falha em SDL_GL_CreateContext: %s", SDL_GetError());
    return 0;
  }

  SDL_GL_SetSwapInterval(1);
  SDL_ShowCursor(SDL_DISABLE);
  return 1;
}

void restaurar_framebuffer_arkos(void) {
  FILE *fb = fopen("/sys/class/graphics/fb0/blank", "w");
  if (fb) {
    fputs("0\n", fb);
    fclose(fb);
  }
}

void sdl_gl_deinit(void) {
  if (s_context) {
    SDL_GL_DeleteContext(s_context);
    s_context = NULL;
  }
  if (s_window) {
    SDL_DestroyWindow(s_window);
    s_window = NULL;
  }
  SDL_Quit();
  restaurar_framebuffer_arkos();
}
