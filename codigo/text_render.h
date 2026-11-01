/* text_render.h -- TrueType font rasterizer for Cocos2dxBitmap labels */
#ifndef __TEXT_RENDER_H__
#define __TEXT_RENDER_H__

#include <stddef.h>

int text_render_init(const char *font_path);
void *text_render_create_bitmap(const char *text, int req_w, int req_h, int *out_w, int *out_h);

#endif
