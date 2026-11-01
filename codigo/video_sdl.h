#ifndef VIDEO_SDL_H
#define VIDEO_SDL_H

#include <SDL2/SDL.h>

// Inicializa a janela SDL2, contexto OpenGL ES 2 e configuração de tela cheia
int sdl_gl_init(void);

// Finaliza o contexto GL, fecha a janela SDL e restaura o display do ArkOS
void sdl_gl_deinit(void);

// Restaura o framebuffer do ArkOS (/sys/class/graphics/fb0/blank)
void restaurar_framebuffer_arkos(void);

// Valida a presença dos binários nativos e do diretório de assets
void check_data(void);

// Ajusta a resolução de renderização da tela
void set_screen_size(int w, int h);

// Retorna o ponteiro da janela SDL ativa
SDL_Window *get_sdl_window(void);

// Retorna o contexto OpenGL ES ativo
SDL_GLContext get_sdl_gl_context(void);

#endif // VIDEO_SDL_H
