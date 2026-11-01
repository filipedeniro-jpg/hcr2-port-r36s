/* overlay_menu.c -- In-Configuracoes do jogo overlay menu for R36S / Linux
 *
 * Implements full game freeze (100% physics/render stop), real-time volume
 * adjustment, cursor speed sensitivity, button remapping, and config.txt persistence.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <GLES2/gl2.h>
#include <SDL2/SDL.h>

#include "overlay_menu.h"
#include "config.h"
#include "hcr_log.h"

// Font 8x8 font matrix (ASCII 32 to 127)
static const unsigned char s_font8x8[96][8] = {
  {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00}, // ' ' (32)
  {0x18,0x3C,0x3C,0x18,0x18,0x00,0x18,0x00}, // '!'
  {0x66,0x66,0x24,0x00,0x00,0x00,0x00,0x00}, // '"'
  {0x6C,0x6C,0xFE,0x6C,0xFE,0x6C,0x6C,0x00}, // '#'
  {0x18,0x3E,0x60,0x3C,0x06,0x7C,0x18,0x00}, // '$'
  {0x00,0x63,0x66,0x0C,0x18,0x33,0x63,0x00}, // '%'
  {0x38,0x6C,0x38,0x76,0xDC,0xCC,0x76,0x00}, // '&'
  {0x18,0x18,0x30,0x00,0x00,0x00,0x00,0x00}, // '''
  {0x0C,0x18,0x30,0x30,0x30,0x18,0x0C,0x00}, // '('
  {0x30,0x18,0x0C,0x0C,0x0C,0x18,0x30,0x00}, // ')'
  {0x00,0x66,0x3C,0xFF,0x3C,0x66,0x00,0x00}, // '*'
  {0x00,0x18,0x18,0x7E,0x18,0x18,0x00,0x00}, // '+'
  {0x00,0x00,0x00,0x00,0x00,0x18,0x18,0x30}, // ','
  {0x00,0x00,0x00,0x7E,0x00,0x00,0x00,0x00}, // '-'
  {0x00,0x00,0x00,0x00,0x00,0x18,0x18,0x00}, // '.'
  {0x06,0x0C,0x18,0x30,0x60,0xC0,0x80,0x00}, // '/'
  {0x3C,0x66,0x6E,0x76,0x66,0x66,0x3C,0x00}, // '0'
  {0x18,0x38,0x18,0x18,0x18,0x18,0x7E,0x00}, // '1'
  {0x3C,0x66,0x06,0x0C,0x18,0x30,0x7E,0x00}, // '2'
  {0x3C,0x66,0x06,0x1C,0x06,0x66,0x3C,0x00}, // '3'
  {0x0C,0x1C,0x34,0x64,0x7E,0x04,0x0E,0x00}, // '4'
  {0x7E,0x60,0x7C,0x06,0x06,0x66,0x3C,0x00}, // '5'
  {0x1C,0x30,0x60,0x7C,0x66,0x66,0x3C,0x00}, // '6'
  {0x7E,0x06,0x0C,0x18,0x30,0x30,0x30,0x00}, // '7'
  {0x3C,0x66,0x66,0x3C,0x66,0x66,0x3C,0x00}, // '8'
  {0x3C,0x66,0x66,0x3E,0x06,0x0C,0x38,0x00}, // '9'
  {0x00,0x18,0x18,0x00,0x18,0x18,0x00,0x00}, // ':'
  {0x00,0x18,0x18,0x00,0x18,0x18,0x30,0x00}, // ';'
  {0x0C,0x18,0x30,0x60,0x30,0x18,0x0C,0x00}, // '<'
  {0x00,0x00,0x7E,0x00,0x7E,0x00,0x00,0x00}, // '='
  {0x30,0x18,0x0C,0x06,0x0C,0x18,0x30,0x00}, // '>'
  {0x3C,0x66,0x06,0x0C,0x18,0x00,0x18,0x00}, // '?'
  {0x3C,0x66,0x6E,0x6E,0x60,0x62,0x3C,0x00}, // '@'
  {0x18,0x3C,0x66,0x66,0x7E,0x66,0x66,0x00}, // 'A'
  {0x7C,0x66,0x66,0x7C,0x66,0x66,0x7C,0x00}, // 'B'
  {0x3C,0x66,0x60,0x60,0x60,0x66,0x3C,0x00}, // 'C'
  {0x78,0x6C,0x66,0x66,0x66,0x6C,0x78,0x00}, // 'D'
  {0x7E,0x60,0x60,0x7C,0x60,0x60,0x7E,0x00}, // 'E'
  {0x7E,0x60,0x60,0x7C,0x60,0x60,0x60,0x00}, // 'F'
  {0x3C,0x66,0x60,0x6E,0x66,0x66,0x3C,0x00}, // 'G'
  {0x66,0x66,0x66,0x7E,0x66,0x66,0x66,0x00}, // 'H'
  {0x3C,0x18,0x18,0x18,0x18,0x18,0x3C,0x00}, // 'I'
  {0x1E,0x0C,0x0C,0x0C,0x0C,0x6C,0x38,0x00}, // 'J'
  {0x66,0x6C,0x78,0x70,0x78,0x6C,0x66,0x00}, // 'K'
  {0x60,0x60,0x60,0x60,0x60,0x60,0x7E,0x00}, // 'L'
  {0x63,0x77,0x7F,0x6B,0x63,0x63,0x63,0x00}, // 'M'
  {0x66,0x76,0x7E,0x7E,0x6E,0x66,0x66,0x00}, // 'N'
  {0x3C,0x66,0x66,0x66,0x66,0x66,0x3C,0x00}, // 'O'
  {0x7C,0x66,0x66,0x7C,0x60,0x60,0x60,0x00}, // 'P'
  {0x3C,0x66,0x66,0x66,0x6E,0x3C,0x0E,0x00}, // 'Q'
  {0x7C,0x66,0x66,0x7C,0x78,0x6C,0x66,0x00}, // 'R'
  {0x3C,0x66,0x60,0x3C,0x06,0x66,0x3C,0x00}, // 'S'
  {0x7E,0x18,0x18,0x18,0x18,0x18,0x18,0x00}, // 'T'
  {0x66,0x66,0x66,0x66,0x66,0x66,0x3C,0x00}, // 'U'
  {0x66,0x66,0x66,0x66,0x66,0x3C,0x18,0x00}, // 'V'
  {0x63,0x63,0x63,0x6B,0x7F,0x77,0x63,0x00}, // 'W'
  {0x66,0x66,0x3C,0x18,0x3C,0x66,0x66,0x00}, // 'X'
  {0x66,0x66,0x66,0x3C,0x18,0x18,0x18,0x00}, // 'Y'
  {0x7E,0x06,0x0C,0x18,0x30,0x60,0x7E,0x00}, // 'Z'
  {0x3C,0x30,0x30,0x30,0x30,0x30,0x3C,0x00}, // '['
  {0xC0,0x60,0x30,0x18,0x0C,0x06,0x02,0x00}, // '\'
  {0x3C,0x0C,0x0C,0x0C,0x0C,0x0C,0x3C,0x00}, // ']'
  {0x10,0x38,0x6C,0xC6,0x00,0x00,0x00,0x00}, // '^'
  {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xFF}, // '_'
  {0x30,0x18,0x0C,0x00,0x00,0x00,0x00,0x00}, // '`'
  {0x00,0x00,0x3C,0x06,0x3E,0x66,0x3E,0x00}, // 'a'
  {0x60,0x60,0x7C,0x66,0x66,0x66,0x7C,0x00}, // 'b'
  {0x00,0x00,0x3C,0x66,0x60,0x66,0x3C,0x00}, // 'c'
  {0x06,0x06,0x3E,0x66,0x66,0x66,0x3E,0x00}, // 'd'
  {0x00,0x00,0x3C,0x66,0x7E,0x60,0x3C,0x00}, // 'e'
  {0x0E,0x18,0x7E,0x18,0x18,0x18,0x18,0x00}, // 'f'
  {0x00,0x00,0x3E,0x66,0x66,0x3E,0x06,0x7C}, // 'g'
  {0x60,0x60,0x7C,0x66,0x66,0x66,0x66,0x00}, // 'h'
  {0x18,0x00,0x38,0x18,0x18,0x18,0x3C,0x00}, // 'i'
  {0x06,0x00,0x0E,0x06,0x06,0x66,0x3C,0x00}, // 'j'
  {0x60,0x60,0x66,0x6C,0x78,0x6C,0x66,0x00}, // 'k'
  {0x38,0x18,0x18,0x18,0x18,0x18,0x3C,0x00}, // 'l'
  {0x00,0x00,0x66,0x7F,0x7F,0x6B,0x63,0x00}, // 'm'
  {0x00,0x00,0x7C,0x66,0x66,0x66,0x66,0x00}, // 'n'
  {0x00,0x00,0x3C,0x66,0x66,0x66,0x3C,0x00}, // 'o'
  {0x00,0x00,0x7C,0x66,0x66,0x7C,0x60,0x60}, // 'p'
  {0x00,0x00,0x3E,0x66,0x66,0x3E,0x06,0x06}, // 'q'
  {0x00,0x00,0x7C,0x66,0x60,0x60,0x60,0x00}, // 'r'
  {0x00,0x00,0x3E,0x60,0x3C,0x06,0x7C,0x00}, // 's'
  {0x18,0x18,0x7E,0x18,0x18,0x18,0x0E,0x00}, // 't'
  {0x00,0x00,0x66,0x66,0x66,0x66,0x3E,0x00}, // 'u'
  {0x00,0x00,0x66,0x66,0x66,0x3C,0x18,0x00}, // 'v'
  {0x00,0x00,0x63,0x6B,0x7F,0x7F,0x36,0x00}, // 'w'
  {0x00,0x00,0x66,0x3C,0x18,0x3C,0x66,0x00}, // 'x'
  {0x00,0x00,0x66,0x66,0x66,0x3E,0x06,0x7C}, // 'y'
  {0x00,0x00,0x7E,0x0C,0x18,0x30,0x7E,0x00}, // 'z'
  {0x0E,0x18,0x18,0x70,0x18,0x18,0x0E,0x00}, // '{'
  {0x18,0x18,0x18,0x00,0x18,0x18,0x18,0x00}, // '|'
  {0x70,0x18,0x18,0x0E,0x18,0x18,0x70,0x00}, // '}'
  {0x76,0xDC,0x00,0x00,0x00,0x00,0x00,0x00}, // '~'
  {0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF}  // 127
};

typedef enum {
  MENU_ITEM_MUSIC_VOL = 0,
  MENU_ITEM_FX_VOL,
  MENU_ITEM_ENGINE_VOL,
  MENU_ITEM_CURSOR_SPD,
  MENU_ITEM_BTN_GAS,
  MENU_ITEM_BTN_BRAKE,
  MENU_ITEM_BTN_CLICK,
  MENU_ITEM_BTN_BOOST,
  MENU_ITEM_BTN_PAUSE,
  MENU_ITEM_RESET_DEFAULTS,
  MENU_ITEM_SAVE_CLOSE,
  MENU_ITEM_COUNT
} MenuItem;

static int s_menu_open = 0;
static int s_selected_item = 0;
static int s_remap_mode = 0; // 1 = waiting for button press
static uint32_t s_remap_start_tick = 0; // timestamp when remap started
static uint32_t s_remap_min_tick = 0;   // ignore button events before this tick to prevent self-capture
static int s_initialized = 0;

static GLuint s_color_prog = 0;
static GLuint s_color_vbo = 0;
static GLint  s_color_u_col = -1;

static GLuint s_text_prog = 0;
static GLuint s_text_vbo = 0;
static GLuint s_font_tex = 0;
static GLint  s_text_u_col = -1;
static GLint  s_text_u_tex = -1;

static GLuint compile_shader(GLenum type, const char *src) {
  GLuint s = glCreateShader(type);
  glShaderSource(s, 1, &src, NULL);
  glCompileShader(s);
  return s;
}

static void init_font_texture(void) {
  // 16 columns x 6 rows of 8x8 glyphs = 128x48 texture
  unsigned char tex_data[128 * 64];
  memset(tex_data, 0, sizeof(tex_data));

  for (int ch = 0; ch < 96; ch++) {
    int col = ch % 16;
    int row = ch / 16;
    int base_x = col * 8;
    int base_y = row * 8;

    for (int y = 0; y < 8; y++) {
      unsigned char row_bits = s_font8x8[ch][y];
      for (int x = 0; x < 8; x++) {
        if (row_bits & (0x80 >> x)) {
          tex_data[(base_y + y) * 128 + (base_x + x)] = 255;
        }
      }
    }
  }

  glGenTextures(1, &s_font_tex);
  glBindTexture(GL_TEXTURE_2D, s_font_tex);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
  glTexImage2D(GL_TEXTURE_2D, 0, GL_ALPHA, 128, 64, 0, GL_ALPHA, GL_UNSIGNED_BYTE, tex_data);
}

void overlay_menu_init(int sw, int sh) {
  (void)sw; (void)sh;
  if (s_initialized) return;
  s_initialized = 1;

  // 1. Color shader for boxes/sliders
  const char *vs_col =
    "attribute vec2 a_pos;\n"
    "void main(){ gl_Position = vec4(a_pos, 0.0, 1.0); }\n";
  const char *fs_col =
    "precision mediump float;\n"
    "uniform vec4 u_color;\n"
    "void main(){ gl_FragColor = u_color; }\n";

  s_color_prog = glCreateProgram();
  GLuint v1 = compile_shader(GL_VERTEX_SHADER, vs_col);
  GLuint f1 = compile_shader(GL_FRAGMENT_SHADER, fs_col);
  glAttachShader(s_color_prog, v1);
  glAttachShader(s_color_prog, f1);
  glBindAttribLocation(s_color_prog, 0, "a_pos");
  glLinkProgram(s_color_prog);
  glDeleteShader(v1);
  glDeleteShader(f1);
  s_color_u_col = glGetUniformLocation(s_color_prog, "u_color");
  glGenBuffers(1, &s_color_vbo);

  // 2. Texture shader for bitmap fonts
  const char *vs_txt =
    "attribute vec4 a_pos_uv;\n"
    "varying vec2 v_uv;\n"
    "void main(){\n"
    "  gl_Position = vec4(a_pos_uv.xy, 0.0, 1.0);\n"
    "  v_uv = a_pos_uv.zw;\n"
    "}\n";
  const char *fs_txt =
    "precision mediump float;\n"
    "varying vec2 v_uv;\n"
    "uniform sampler2D u_tex;\n"
    "uniform vec4 u_color;\n"
    "void main(){\n"
    "  float a = texture2D(u_tex, v_uv).a;\n"
    "  gl_FragColor = vec4(u_color.rgb, u_color.a * a);\n"
    "}\n";

  s_text_prog = glCreateProgram();
  GLuint v2 = compile_shader(GL_VERTEX_SHADER, vs_txt);
  GLuint f2 = compile_shader(GL_FRAGMENT_SHADER, fs_txt);
  glAttachShader(s_text_prog, v2);
  glAttachShader(s_text_prog, f2);
  glBindAttribLocation(s_text_prog, 0, "a_pos_uv");
  glLinkProgram(s_text_prog);
  glDeleteShader(v2);
  glDeleteShader(f2);
  s_text_u_col = glGetUniformLocation(s_text_prog, "u_color");
  s_text_u_tex = glGetUniformLocation(s_text_prog, "u_tex");
  glGenBuffers(1, &s_text_vbo);

  init_font_texture();
}

int overlay_menu_is_open(void) {
  return s_menu_open;
}

void overlay_menu_open(void) {
  s_menu_open = 1;
  s_remap_mode = 0;
  HLOGI("OVERLAY", "Menu opened (Game frozen 100%%)");
}

void overlay_menu_close(void) {
  s_menu_open = 0;
  s_remap_mode = 0;
  write_config(CONFIG_NAME);
  HLOGI("OVERLAY", "Menu closed (Settings saved to %s, game resumed)", CONFIG_NAME);
}

void overlay_menu_toggle(void) {
  if (s_menu_open) overlay_menu_close();
  else overlay_menu_open();
}

const char *overlay_menu_button_name(int button) {
  switch (button) {
    case SDL_CONTROLLER_BUTTON_A: return "BOTAO A";
    case SDL_CONTROLLER_BUTTON_B: return "BOTAO B";
    case SDL_CONTROLLER_BUTTON_X: return "BOTAO X";
    case SDL_CONTROLLER_BUTTON_Y: return "BOTAO Y";
    case SDL_CONTROLLER_BUTTON_BACK: return "SELECT";
    case SDL_CONTROLLER_BUTTON_GUIDE: return "FN";
    case SDL_CONTROLLER_BUTTON_START: return "START";
    case SDL_CONTROLLER_BUTTON_LEFTSTICK: return "L3 (STICK ESQ)";
    case SDL_CONTROLLER_BUTTON_RIGHTSTICK: return "R3 (STICK DIR)";
    case SDL_CONTROLLER_BUTTON_LEFTSHOULDER: return "L1 / LB";
    case SDL_CONTROLLER_BUTTON_RIGHTSHOULDER: return "R1 / RB";
    case SDL_CONTROLLER_BUTTON_DPAD_UP: return "D-PAD CIMA";
    case SDL_CONTROLLER_BUTTON_DPAD_DOWN: return "D-PAD BAIXO";
    case SDL_CONTROLLER_BUTTON_DPAD_LEFT: return "D-PAD ESQUERDA";
    case SDL_CONTROLLER_BUTTON_DPAD_RIGHT: return "D-PAD DIREITA";
    default: return "DESCONHECIDO";
  }
}

static void apply_remap(int button) {
  switch (s_selected_item) {
    case MENU_ITEM_BTN_GAS:   config.btn_gas = button; break;
    case MENU_ITEM_BTN_BRAKE: config.btn_brake = button; break;
    case MENU_ITEM_BTN_CLICK: config.btn_click = button; break;
    case MENU_ITEM_BTN_BOOST: config.btn_boost = button; break;
    case MENU_ITEM_BTN_PAUSE: config.btn_pause = button; break;
  }
  s_remap_mode = 0;
  write_config(CONFIG_NAME);
  HLOGI("OVERLAY", "Remapped item %d to button %d (%s)",
        s_selected_item, button, overlay_menu_button_name(button));
}

int overlay_menu_handle_event(const SDL_Event *ev) {
  if (!s_menu_open) return 0;

  // Remap mode: wait for ANY joystick button press with 3s timeout
  if (s_remap_mode) {
    uint32_t now = SDL_GetTicks();
    if (now - s_remap_start_tick >= 3000) {
      s_remap_mode = 0; // 3 seconds timeout expired: cancel remap
      return 1;
    }
    // Ignore any events during the first 250ms to consume the triggering A press
    if (now < s_remap_min_tick) {
      return 1;
    }
    if (ev->type == SDL_CONTROLLERBUTTONDOWN) {
      apply_remap(ev->cbutton.button);
      return 1;
    }
    return 1; // Consume everything while remapping
  }

  // Normal navigation
  if (ev->type == SDL_CONTROLLERBUTTONDOWN) {
    int btn = ev->cbutton.button;

    if (btn == SDL_CONTROLLER_BUTTON_DPAD_UP) {
      if (s_selected_item > 0) s_selected_item--;
      else s_selected_item = MENU_ITEM_COUNT - 1;
      return 1;
    }
    if (btn == SDL_CONTROLLER_BUTTON_DPAD_DOWN) {
      if (s_selected_item < MENU_ITEM_COUNT - 1) s_selected_item++;
      else s_selected_item = 0;
      return 1;
    }

    if (btn == SDL_CONTROLLER_BUTTON_DPAD_LEFT) {
      switch (s_selected_item) {
        case MENU_ITEM_MUSIC_VOL:
          if (config.music_volume > 0) config.music_volume -= 5;
          break;
        case MENU_ITEM_FX_VOL:
          if (config.effects_volume > 0) config.effects_volume -= 5;
          break;
        case MENU_ITEM_ENGINE_VOL:
          if (config.engine_volume > 0) config.engine_volume -= 5;
          break;
        case MENU_ITEM_CURSOR_SPD:
          if (config.cursor_speed > 8) config.cursor_speed -= 4;
          break;
      }
      return 1;
    }

    if (btn == SDL_CONTROLLER_BUTTON_DPAD_RIGHT) {
      switch (s_selected_item) {
        case MENU_ITEM_MUSIC_VOL:
          if (config.music_volume < 100) config.music_volume += 5;
          break;
        case MENU_ITEM_FX_VOL:
          if (config.effects_volume < 100) config.effects_volume += 5;
          break;
        case MENU_ITEM_ENGINE_VOL:
          if (config.engine_volume < 200) config.engine_volume += 5;
          break;
        case MENU_ITEM_CURSOR_SPD:
          if (config.cursor_speed < 80) config.cursor_speed += 4;
          break;
      }
      return 1;
    }

    // Action button to enter remap or confirm: ALWAYS Button A
    if (btn == SDL_CONTROLLER_BUTTON_A) {
      if (s_selected_item >= MENU_ITEM_BTN_GAS && s_selected_item <= MENU_ITEM_BTN_PAUSE) {
        s_remap_mode = 1;
        s_remap_start_tick = SDL_GetTicks();
        s_remap_min_tick = s_remap_start_tick + 250; // ignore the current A press
        return 1;
      }
      if (s_selected_item == MENU_ITEM_RESET_DEFAULTS) {
        config.music_volume = 35;
        config.effects_volume = 50;
        config.engine_volume = 100;
        config.cursor_speed = 32;
        config.btn_gas = 0;   // Button A
        config.btn_click = 1; // Button B
        config.btn_boost = 2; // Button X
        config.btn_brake = 3; // Button Y
        config.btn_pause = 6; // Button Start
        write_config(CONFIG_NAME);
        HLOGI("OVERLAY", "Restored default settings and saved to %s", CONFIG_NAME);
        return 1;
      }
      if (s_selected_item == MENU_ITEM_SAVE_CLOSE) {
        overlay_menu_close();
        return 1;
      }
    }

    // B button closes menu
    if (btn == SDL_CONTROLLER_BUTTON_B) {
      overlay_menu_close();
      return 1;
    }

    return 1;
  }

  // Also handle keyboard navigation for testing/debugging
  if (ev->type == SDL_KEYDOWN) {
    SDL_Keycode k = ev->key.keysym.sym;
    if (k == SDLK_UP) {
      if (s_selected_item > 0) s_selected_item--;
      else s_selected_item = MENU_ITEM_COUNT - 1;
      return 1;
    }
    if (k == SDLK_DOWN) {
      if (s_selected_item < MENU_ITEM_COUNT - 1) s_selected_item++;
      else s_selected_item = 0;
      return 1;
    }
    if (k == SDLK_LEFT) {
      if (s_selected_item == MENU_ITEM_MUSIC_VOL && config.music_volume > 0) config.music_volume -= 5;
      if (s_selected_item == MENU_ITEM_FX_VOL && config.effects_volume > 0) config.effects_volume -= 5;
      if (s_selected_item == MENU_ITEM_ENGINE_VOL && config.engine_volume > 0) config.engine_volume -= 5;
      if (s_selected_item == MENU_ITEM_CURSOR_SPD && config.cursor_speed > 8) config.cursor_speed -= 4;
      return 1;
    }
    if (k == SDLK_RIGHT) {
      if (s_selected_item == MENU_ITEM_MUSIC_VOL && config.music_volume < 100) config.music_volume += 5;
      if (s_selected_item == MENU_ITEM_FX_VOL && config.effects_volume < 100) config.effects_volume += 5;
      if (s_selected_item == MENU_ITEM_ENGINE_VOL && config.engine_volume < 200) config.engine_volume += 5;
      if (s_selected_item == MENU_ITEM_CURSOR_SPD && config.cursor_speed < 80) config.cursor_speed += 4;
      return 1;
    }
    if (k == SDLK_RETURN || k == SDLK_SPACE) {
      if (s_selected_item >= MENU_ITEM_BTN_GAS && s_selected_item <= MENU_ITEM_BTN_PAUSE) {
        s_remap_mode = 1;
      } else if (s_selected_item == MENU_ITEM_SAVE_CLOSE) {
        overlay_menu_close();
      }
      return 1;
    }
    if (k == SDLK_ESCAPE) {
      overlay_menu_close();
      return 1;
    }
    return 1;
  }

  return 1; // Consume input while menu is open
}

// Helpers for drawing boxes and text in normalized device coordinates [-1, 1]
static void draw_box(float x, float y, float w, float h, float r, float g, float b, float a, int sw, int sh) {
  // Convert pixel coords (origin top-left) to NDC [-1, 1]
  float x0 = (x / (float)sw) * 2.0f - 1.0f;
  float y0 = 1.0f - (y / (float)sh) * 2.0f;
  float x1 = ((x + w) / (float)sw) * 2.0f - 1.0f;
  float y1 = 1.0f - ((y + h) / (float)sh) * 2.0f;

  float verts[6 * 2] = {
    x0, y0,   x1, y0,   x0, y1,
    x0, y1,   x1, y0,   x1, y1
  };

  glUseProgram(s_color_prog);
  glBindBuffer(GL_ARRAY_BUFFER, s_color_vbo);
  glBufferData(GL_ARRAY_BUFFER, sizeof(verts), verts, GL_DYNAMIC_DRAW);
  glEnableVertexAttribArray(0);
  glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 0, 0);
  glUniform4f(s_color_u_col, r, g, b, a);
  glDrawArrays(GL_TRIANGLES, 0, 6);
}

static void draw_string(const char *str, float px, float py, float scale, float r, float g, float b, float a, int sw, int sh) {
  int len = strlen(str);
  if (len <= 0) return;

  float char_w = 8.0f * scale;
  float char_h = 8.0f * scale;

  float verts[len * 6 * 4]; // x, y, u, v
  int v_idx = 0;

  float cur_x = px;
  for (int i = 0; i < len; i++) {
    unsigned char ch = (unsigned char)str[i];
    if (ch < 32 || ch > 127) ch = '?';
    int glyph = ch - 32;
    int col = glyph % 16;
    int row = glyph / 16;

    float u0 = (col * 8.0f) / 128.0f;
    float v0 = (row * 8.0f) / 64.0f;
    float u1 = ((col + 1) * 8.0f) / 128.0f;
    float v1 = ((row + 1) * 8.0f) / 64.0f;

    float x0 = (cur_x / (float)sw) * 2.0f - 1.0f;
    float y0 = 1.0f - (py / (float)sh) * 2.0f;
    float x1 = ((cur_x + char_w) / (float)sw) * 2.0f - 1.0f;
    float y1 = 1.0f - ((py + char_h) / (float)sh) * 2.0f;

    // Triangle 1
    verts[v_idx++] = x0; verts[v_idx++] = y0; verts[v_idx++] = u0; verts[v_idx++] = v0;
    verts[v_idx++] = x1; verts[v_idx++] = y0; verts[v_idx++] = u1; verts[v_idx++] = v0;
    verts[v_idx++] = x0; verts[v_idx++] = y1; verts[v_idx++] = u0; verts[v_idx++] = v1;

    // Triangle 2
    verts[v_idx++] = x0; verts[v_idx++] = y1; verts[v_idx++] = u0; verts[v_idx++] = v1;
    verts[v_idx++] = x1; verts[v_idx++] = y0; verts[v_idx++] = u1; verts[v_idx++] = v0;
    verts[v_idx++] = x1; verts[v_idx++] = y1; verts[v_idx++] = u1; verts[v_idx++] = v1;

    cur_x += char_w;
  }

  glUseProgram(s_text_prog);
  glBindBuffer(GL_ARRAY_BUFFER, s_text_vbo);
  glBufferData(GL_ARRAY_BUFFER, v_idx * sizeof(float), verts, GL_DYNAMIC_DRAW);
  glEnableVertexAttribArray(0);
  glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 0, 0);

  glActiveTexture(GL_TEXTURE0);
  glBindTexture(GL_TEXTURE_2D, s_font_tex);
  glUniform1i(s_text_u_tex, 0);
  glUniform4f(s_text_u_col, r, g, b, a);

  glDrawArrays(GL_TRIANGLES, 0, len * 6);
}

void overlay_menu_render(int sw, int sh) {
  if (!s_menu_open) return;
  overlay_menu_init(sw, sh);

  // Save GL State completely to protect Cocos2d-x engine
  GLint prev_prog = 0, prev_array_buf = 0, prev_elem_buf = 0, prev_fbo = 0;
  GLint prev_viewport[4];
  GLint prev_blend_src_rgb = 0, prev_blend_dst_rgb = 0;
  GLint prev_blend_src_alpha = 0, prev_blend_dst_alpha = 0;
  GLboolean prev_depth = glIsEnabled(GL_DEPTH_TEST);
  GLboolean prev_cull = glIsEnabled(GL_CULL_FACE);
  GLboolean prev_scissor = glIsEnabled(GL_SCISSOR_TEST);
  GLboolean prev_blend = glIsEnabled(GL_BLEND);
  GLint prev_attrib0 = 0;

  glGetIntegerv(GL_CURRENT_PROGRAM, &prev_prog);
  glGetIntegerv(GL_ARRAY_BUFFER_BINDING, &prev_array_buf);
  glGetIntegerv(GL_ELEMENT_ARRAY_BUFFER_BINDING, &prev_elem_buf);
  glGetIntegerv(GL_FRAMEBUFFER_BINDING, &prev_fbo);
  glGetIntegerv(GL_VIEWPORT, prev_viewport);
  glGetIntegerv(GL_BLEND_SRC_RGB, &prev_blend_src_rgb);
  glGetIntegerv(GL_BLEND_DST_RGB, &prev_blend_dst_rgb);
  glGetIntegerv(GL_BLEND_SRC_ALPHA, &prev_blend_src_alpha);
  glGetIntegerv(GL_BLEND_DST_ALPHA, &prev_blend_dst_alpha);
  glGetVertexAttribiv(0, GL_VERTEX_ATTRIB_ARRAY_ENABLED, &prev_attrib0);

  // Set 2D Overlay State
  glBindFramebuffer(GL_FRAMEBUFFER, 0);
  glViewport(0, 0, sw, sh);
  glDisable(GL_DEPTH_TEST);
  glDisable(GL_CULL_FACE);
  glDisable(GL_SCISSOR_TEST);
  glEnable(GL_BLEND);
  glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

  // 1. Semi-transparent backdrop across the whole screen
  draw_box(0, 0, sw, sh, 0.0f, 0.0f, 0.0f, 0.70f, sw, sh);

  // 2. Main dialog window box
  float box_w = (sw > 700) ? 580.0f : (sw * 0.90f);
  float box_h = (sh > 400) ? 410.0f : (sh * 0.94f);
  float box_x = (sw - box_w) * 0.5f;
  float box_y = (sh - box_h) * 0.5f;

  // Outer border & fill
  draw_box(box_x - 3, box_y - 3, box_w + 6, box_h + 6, 0.85f, 0.70f, 0.20f, 0.95f, sw, sh); // Gold border
  draw_box(box_x, box_y, box_w, box_h, 0.08f, 0.10f, 0.14f, 0.95f, sw, sh); // Dark Navy background

  // Title header bar
  draw_box(box_x, box_y, box_w, 36.0f, 0.15f, 0.20f, 0.28f, 1.0f, sw, sh);
  draw_string("CONFIGURACOES DO JOGO (R36S)", box_x + 16.0f, box_y + 10.0f, 2.0f, 1.0f, 0.90f, 0.30f, 1.0f, sw, sh);

  // Remap timeout check and countdown
  int remaining_remap_sec = 0;
  if (s_remap_mode) {
    uint32_t now = SDL_GetTicks();
    uint32_t elapsed = now - s_remap_start_tick;
    if (elapsed >= 3000) {
      s_remap_mode = 0;
    } else {
      remaining_remap_sec = 3 - (int)(elapsed / 1000);
      if (remaining_remap_sec < 1) remaining_remap_sec = 1;
    }
  }

  // Render items
  float start_y = box_y + 48.0f;
  float row_h = 28.0f;

  for (int i = 0; i < MENU_ITEM_COUNT; i++) {
    float y = start_y + i * row_h;
    int is_selected = (i == s_selected_item);

    if (is_selected) {
      // Highlight row
      draw_box(box_x + 6, y - 2, box_w - 12, row_h - 2, 0.25f, 0.45f, 0.75f, 0.85f, sw, sh);
    }

    char label[128];
    switch (i) {
      case MENU_ITEM_MUSIC_VOL:
        snprintf(label, sizeof(label), "%s Volume Musica:   [%3d%%]", is_selected ? ">" : " ", config.music_volume);
        break;
      case MENU_ITEM_FX_VOL:
        snprintf(label, sizeof(label), "%s Volume Efeitos:  [%3d%%]", is_selected ? ">" : " ", config.effects_volume);
        break;
      case MENU_ITEM_ENGINE_VOL:
        snprintf(label, sizeof(label), "%s Volume Motor:    [%3d%%]", is_selected ? ">" : " ", config.engine_volume);
        break;
      case MENU_ITEM_CURSOR_SPD:
        snprintf(label, sizeof(label), "%s Velocidade Mira: [%3d]", is_selected ? ">" : " ", config.cursor_speed);
        break;
      case MENU_ITEM_BTN_GAS:
        if (is_selected && s_remap_mode) {
          snprintf(label, sizeof(label), "> Acelerar (Gas):  [ AGUARDANDO: %ds ]", remaining_remap_sec);
        } else {
          snprintf(label, sizeof(label), "%s Acelerar (Gas):  [ %s ]", is_selected ? ">" : " ", overlay_menu_button_name(config.btn_gas));
        }
        break;
      case MENU_ITEM_BTN_BRAKE:
        if (is_selected && s_remap_mode) {
          snprintf(label, sizeof(label), "> Freio (Brake):   [ AGUARDANDO: %ds ]", remaining_remap_sec);
        } else {
          snprintf(label, sizeof(label), "%s Freio (Brake):   [ %s ]", is_selected ? ">" : " ", overlay_menu_button_name(config.btn_brake));
        }
        break;
      case MENU_ITEM_BTN_CLICK:
        if (is_selected && s_remap_mode) {
          snprintf(label, sizeof(label), "> Clique / Mira:   [ AGUARDANDO: %ds ]", remaining_remap_sec);
        } else {
          snprintf(label, sizeof(label), "%s Clique / Mira:   [ %s ]", is_selected ? ">" : " ", overlay_menu_button_name(config.btn_click));
        }
        break;
      case MENU_ITEM_BTN_BOOST:
        if (is_selected && s_remap_mode) {
          snprintf(label, sizeof(label), "> Turbo (Boost):   [ AGUARDANDO: %ds ]", remaining_remap_sec);
        } else {
          snprintf(label, sizeof(label), "%s Turbo (Boost):   [ %s ]", is_selected ? ">" : " ", overlay_menu_button_name(config.btn_boost));
        }
        break;
      case MENU_ITEM_BTN_PAUSE:
        if (is_selected && s_remap_mode) {
          snprintf(label, sizeof(label), "> Pausar Jogo:     [ AGUARDANDO: %ds ]", remaining_remap_sec);
        } else {
          snprintf(label, sizeof(label), "%s Pausar Jogo:     [ %s ]", is_selected ? ">" : " ", overlay_menu_button_name(config.btn_pause));
        }
        break;
      case MENU_ITEM_RESET_DEFAULTS:
        snprintf(label, sizeof(label), "%s RESTAURAR PADROES", is_selected ? ">" : " ");
        break;
      case MENU_ITEM_SAVE_CLOSE:
        snprintf(label, sizeof(label), "%s SALVAR E CONTINUAR", is_selected ? ">" : " ");
        break;
      default:
        label[0] = '\0';
        break;
    }

    float text_r = is_selected ? 1.0f : 0.85f;
    float text_g = is_selected ? 1.0f : 0.85f;
    float text_b = is_selected ? 0.3f : 0.85f;

    draw_string(label, box_x + 14.0f, y + 4.0f, 1.8f, text_r, text_g, text_b, 1.0f, sw, sh);
  }

  // Footer guidance hints
  char hint[128];
  if (s_remap_mode) {
    snprintf(hint, sizeof(hint), "Pressione a nova tecla em ate %ds (aguarde para cancelar)", remaining_remap_sec);
  } else {
    snprintf(hint, sizeof(hint), "D-PAD: Ajustar | A: Remapear | B: Fechar e Salvar");
  }
  draw_string(hint, box_x + 16.0f, box_y + box_h - 22.0f, 1.4f, 0.65f, 0.75f, 0.85f, 1.0f, sw, sh);

  // Restore GL State completely
  if (prev_attrib0) glEnableVertexAttribArray(0);
  else glDisableVertexAttribArray(0);

  glBindBuffer(GL_ARRAY_BUFFER, prev_array_buf);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, prev_elem_buf);
  glUseProgram(prev_prog);
  glBindFramebuffer(GL_FRAMEBUFFER, prev_fbo);
  glViewport(prev_viewport[0], prev_viewport[1], prev_viewport[2], prev_viewport[3]);
  glBlendFuncSeparate(prev_blend_src_rgb, prev_blend_dst_rgb, prev_blend_src_alpha, prev_blend_dst_alpha);
  if (prev_blend) glEnable(GL_BLEND); else glDisable(GL_BLEND);
}
