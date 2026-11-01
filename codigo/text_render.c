/* text_render.c -- TrueType font rasterizer using stb_truetype for Cocos2dxBitmap */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define STB_TRUETYPE_IMPLEMENTATION
#include "stb_truetype.h"
#include "text_render.h"

static stbtt_fontinfo s_font;
static unsigned char *s_font_data = NULL;
static int s_font_ready = 0;

static unsigned char *read_file_bytes(const char *path, size_t *out_sz) {
  FILE *f = fopen(path, "rb");
  if (!f) return NULL;
  fseek(f, 0, SEEK_END);
  long sz = ftell(f);
  if (sz <= 0) { fclose(f); return NULL; }
  fseek(f, 0, SEEK_SET);
  unsigned char *buf = (unsigned char *)malloc((size_t)sz);
  if (!buf) { fclose(f); return NULL; }
  size_t n = fread(buf, 1, (size_t)sz, f);
  fclose(f);
  if (out_sz) *out_sz = n;
  return buf;
}

int text_render_init(const char *font_path) {
  if (s_font_ready) return 1;

  static const char *fallback_paths[] = {
    "/roms/ports/hcr/font.ttf",
    "font.ttf",
    "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf",
    "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf",
    "/usr/share/fonts/truetype/droid/DroidSansFallbackFull.ttf",
    NULL
  };

  if (font_path) {
    s_font_data = read_file_bytes(font_path, NULL);
    if (s_font_data && stbtt_InitFont(&s_font, s_font_data, stbtt_GetFontOffsetForIndex(s_font_data, 0))) {
      s_font_ready = 1;
      return 1;
    }
  }

  for (int i = 0; fallback_paths[i]; i++) {
    s_font_data = read_file_bytes(fallback_paths[i], NULL);
    if (s_font_data) {
      if (stbtt_InitFont(&s_font, s_font_data, stbtt_GetFontOffsetForIndex(s_font_data, 0))) {
        s_font_ready = 1;
        printf("[TextRender] Loaded TrueType font: %s\n", fallback_paths[i]);
        return 1;
      }
      free(s_font_data);
      s_font_data = NULL;
    }
  }

  printf("[TextRender] Warning: failed to load any TrueType font!\n");
  return 0;
}

static unsigned int next_utf8_char(const char **p) {
  unsigned char c = (unsigned char)**p;
  if (!c) return 0;
  (*p)++;
  if (c < 0x80) return c;
  if ((c & 0xE0) == 0xC0) {
    unsigned char c2 = (unsigned char)**p;
    if (c2) (*p)++;
    return ((c & 0x1F) << 6) | (c2 & 0x3F);
  }
  if ((c & 0xF0) == 0xE0) {
    unsigned char c2 = (unsigned char)**p; if (c2) (*p)++;
    unsigned char c3 = (unsigned char)**p; if (c3) (*p)++;
    return ((c & 0x0F) << 12) | ((c2 & 0x3F) << 6) | (c3 & 0x3F);
  }
  if ((c & 0xF8) == 0xF0) {
    unsigned char c2 = (unsigned char)**p; if (c2) (*p)++;
    unsigned char c3 = (unsigned char)**p; if (c3) (*p)++;
    unsigned char c4 = (unsigned char)**p; if (c4) (*p)++;
    return ((c & 0x07) << 18) | ((c2 & 0x3F) << 12) | ((c3 & 0x3F) << 6) | (c4 & 0x3F);
  }
  return c;
}

void *text_render_create_bitmap(const char *text, int req_w, int req_h, int *out_w, int *out_h) {
  if (!s_font_ready) text_render_init(NULL);
  if (!text || !*text) text = " ";

  // Choose pixel height
  float font_h = 24.0f;
  if (req_h > 0) {
    font_h = (float)req_h * 0.72f;
    if (font_h < 12.0f) font_h = 12.0f;
    if (font_h > 48.0f) font_h = 48.0f;
  }

  float scale = stbtt_ScaleForPixelHeight(&s_font, font_h);
  int ascent, descent, line_gap;
  stbtt_GetFontVMetrics(&s_font, &ascent, &descent, &line_gap);
  int baseline = (int)(ascent * scale + 0.5f);
  int line_step = (int)((ascent - descent + line_gap) * scale + 0.5f);
  if (line_step < (int)(font_h + 2.0f)) line_step = (int)(font_h + 2.0f);

  // Measure text
  int max_line_w = 0;
  int cur_line_w = 0;
  int line_count = 1;

  const char *p = text;
  while (*p) {
    if (*p == '\n') {
      p++;
      if (cur_line_w > max_line_w) max_line_w = cur_line_w;
      cur_line_w = 0;
      line_count++;
      continue;
    }
    unsigned int c = next_utf8_char(&p);
    int advance, lsb;
    stbtt_GetCodepointHMetrics(&s_font, (int)c, &advance, &lsb);
    cur_line_w += (int)(advance * scale + 0.5f);
  }
  if (cur_line_w > max_line_w) max_line_w = cur_line_w;

  int w = req_w > 0 ? req_w : (max_line_w + 4);
  int h = req_h > 0 ? req_h : (line_count * line_step + 4);
  if (w < 8) w = 8;
  if (h < 8) h = 8;

  size_t bytes = (size_t)w * (size_t)h * 4;
  unsigned char *pixels = (unsigned char *)calloc(1, bytes);
  if (!pixels) return NULL;

  // Render glyphs
  int pen_x = 2;
  int pen_y = baseline + 2;

  p = text;
  while (*p) {
    if (*p == '\n') {
      p++;
      pen_x = 2;
      pen_y += line_step;
      continue;
    }
    unsigned int c = next_utf8_char(&p);
    int advance, lsb;
    stbtt_GetCodepointHMetrics(&s_font, (int)c, &advance, &lsb);

    int ix0, iy0, ix1, iy1;
    stbtt_GetCodepointBitmapBox(&s_font, (int)c, scale, scale, &ix0, &iy0, &ix1, &iy1);
    int gw = ix1 - ix0;
    int gh = iy1 - iy0;

    if (gw > 0 && gh > 0) {
      unsigned char *glyph_bmp = (unsigned char *)malloc((size_t)gw * (size_t)gh);
      if (glyph_bmp) {
        stbtt_MakeCodepointBitmap(&s_font, glyph_bmp, gw, gh, gw, scale, scale, (int)c);
        int dest_x = pen_x + ix0;
        int dest_y = pen_y + iy0;

        for (int gy = 0; gy < gh; gy++) {
          int dy = dest_y + gy;
          if (dy < 0 || dy >= h) continue;
          for (int gx = 0; gx < gw; gx++) {
            int dx = dest_x + gx;
            if (dx < 0 || dx >= w) continue;
            unsigned char alpha = glyph_bmp[gy * gw + gx];
            if (alpha > 0) {
              size_t idx = ((size_t)dy * (size_t)w + (size_t)dx) * 4;
              // White text with font alpha modulation
              pixels[idx + 0] = 255;
              pixels[idx + 1] = 255;
              pixels[idx + 2] = 255;
              if (alpha > pixels[idx + 3]) pixels[idx + 3] = alpha;
            }
          }
        }
        free(glyph_bmp);
      }
    }
    pen_x += (int)(advance * scale + 0.5f);
  }

  if (out_w) *out_w = w;
  if (out_h) *out_h = h;
  return pixels;
}
