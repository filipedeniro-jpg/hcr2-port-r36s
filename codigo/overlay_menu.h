/* overlay_menu.h -- In-Configuracoes do jogo overlay menu for R36S / Linux
 *
 * Provides real-time volume adjustment, cursor speed, button remapping,
 * and 100% physics/rendering game freeze while open.
 */
#ifndef __OVERLAY_MENU_H__
#define __OVERLAY_MENU_H__

#include <SDL2/SDL.h>

void overlay_menu_init(int screen_w, int screen_h);
int  overlay_menu_is_open(void);
void overlay_menu_open(void);
void overlay_menu_close(void);
void overlay_menu_toggle(void);

// Handles input events when the menu is open.
// Returns 1 if event was consumed by the menu, 0 otherwise.
int  overlay_menu_handle_event(const SDL_Event *ev);

// Renders the overlay menu on top of the screen before eglSwapBuffers.
void overlay_menu_render(int screen_w, int screen_h);

// Helper to convert SDL_GameControllerButton to readable string
const char *overlay_menu_button_name(int button);

#endif /* __OVERLAY_MENU_H__ */
