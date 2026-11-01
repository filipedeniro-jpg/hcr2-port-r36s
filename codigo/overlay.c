/* overlay.c -- minimal GLES2 overlay for the docked/no-touch virtual cursor.
 *
 * Drives a visible mouse arrow pointer (↖) with black outline and white fill,
 * drawn directly on top of the scene before swapping window buffers.
 */
#include <stddef.h>
#include <GLES2/gl2.h>
#include "overlay.h"

static GLuint s_prog, s_vbo, s_loc_pos, s_loc_color;
static int s_ready = 0;

static GLuint compile(GLenum type, const char *src) {
  GLuint s = glCreateShader(type);
  glShaderSource(s, 1, &src, NULL);
  glCompileShader(s);
  return s;
}

static void lazy_init(void) {
  if (s_ready) return;
  s_ready = 1;

  static const char *vs =
    "attribute vec2 a_pos;\n"
    "void main(){ gl_Position = vec4(a_pos, 0.0, 1.0); }\n";
  static const char *fs =
    "precision mediump float;\n"
    "uniform vec4 u_color;\n"
    "void main(){ gl_FragColor = u_color; }\n";

  s_prog = glCreateProgram();
  GLuint v = compile(GL_VERTEX_SHADER, vs);
  GLuint f = compile(GL_FRAGMENT_SHADER, fs);
  glAttachShader(s_prog, v);
  glAttachShader(s_prog, f);
  glBindAttribLocation(s_prog, 0, "a_pos");
  glLinkProgram(s_prog);
  glDeleteShader(v);
  glDeleteShader(f);

  s_loc_pos = 0;
  s_loc_color = glGetUniformLocation(s_prog, "u_color");
  glGenBuffers(1, &s_vbo);
}

static inline void push_pt(float *buf, int *n, float px, float py, int sw, int sh) {
  buf[(*n) * 2 + 0] = (px / (float)sw) * 2.0f - 1.0f;
  buf[(*n) * 2 + 1] = 1.0f - (py / (float)sh) * 2.0f;
  (*n)++;
}

static inline void push_tri(float *buf, int *n, float x1, float y1, float x2, float y2, float x3, float y3, int sw, int sh) {
  push_pt(buf, n, x1, y1, sw, sh);
  push_pt(buf, n, x2, y2, sw, sh);
  push_pt(buf, n, x3, y3, sw, sh);
}

// Build mouse arrow cursor (tip exactly at cx, cy)
static int build_arrow(float *buf, float cx, float cy, float scale, float b, int sw, int sh) {
  int n = 0;
  float s = scale;

  // Triangle 1: main tip & left edge
  push_tri(buf, &n,
           cx - b,               cy - b,
           cx - b,               cy + (24.0f * s) + b,
           cx + (7.0f * s),      cy + (17.0f * s),
           sw, sh);

  // Triangle 2: main body
  push_tri(buf, &n,
           cx - b,               cy - b,
           cx + (7.0f * s),      cy + (17.0f * s),
           cx + (18.0f * s) + b, cy + (17.0f * s) + b,
           sw, sh);

  // Triangle 3: stem (part A)
  push_tri(buf, &n,
           cx + (6.0f * s) - b,  cy + (16.0f * s),
           cx + (11.0f * s) - b, cy + (27.0f * s) + b,
           cx + (16.0f * s) + b, cy + (25.0f * s) + b,
           sw, sh);

  // Triangle 4: stem (part B)
  push_tri(buf, &n,
           cx + (6.0f * s) - b,  cy + (16.0f * s),
           cx + (16.0f * s) + b, cy + (25.0f * s) + b,
           cx + (11.0f * s),     cy + (14.0f * s),
           sw, sh);

  return n; // 12 verts
}

void overlay_draw_cursor(float x, float y, float alpha, int sw, int sh) {
  if (alpha <= 0.01f) return;
  if (alpha > 1.0f) alpha = 1.0f;
  lazy_init();
  if (!s_prog) return;

  // Save full GL state to prevent Cocos2d-x state cache corruption
  GLint prev_prog = 0;
  GLint prev_array_buf = 0;
  GLint prev_elem_buf = 0;
  GLint prev_fbo = 0;
  GLint prev_viewport[4];
  GLint prev_blend_src_rgb = 0, prev_blend_dst_rgb = 0;
  GLint prev_blend_src_alpha = 0, prev_blend_dst_alpha = 0;
  GLboolean prev_depth = glIsEnabled(GL_DEPTH_TEST);
  GLboolean prev_cull = glIsEnabled(GL_CULL_FACE);
  GLboolean prev_scissor = glIsEnabled(GL_SCISSOR_TEST);
  GLboolean prev_blend = glIsEnabled(GL_BLEND);
  GLint prev_attrib0_enabled = 0;

  glGetIntegerv(GL_CURRENT_PROGRAM, &prev_prog);
  glGetIntegerv(GL_ARRAY_BUFFER_BINDING, &prev_array_buf);
  glGetIntegerv(GL_ELEMENT_ARRAY_BUFFER_BINDING, &prev_elem_buf);
  glGetIntegerv(GL_FRAMEBUFFER_BINDING, &prev_fbo);
  glGetIntegerv(GL_VIEWPORT, prev_viewport);
  glGetIntegerv(GL_BLEND_SRC_RGB, &prev_blend_src_rgb);
  glGetIntegerv(GL_BLEND_DST_RGB, &prev_blend_dst_rgb);
  glGetIntegerv(GL_BLEND_SRC_ALPHA, &prev_blend_src_alpha);
  glGetIntegerv(GL_BLEND_DST_ALPHA, &prev_blend_dst_alpha);
  glGetVertexAttribiv(0, GL_VERTEX_ATTRIB_ARRAY_ENABLED, &prev_attrib0_enabled);

  float verts[32 * 2];

  // Set GL state for UI overlay
  glBindFramebuffer(GL_FRAMEBUFFER, 0);
  glViewport(0, 0, sw, sh);
  glDisable(GL_DEPTH_TEST);
  glDisable(GL_CULL_FACE);
  glDisable(GL_SCISSOR_TEST);
  glEnable(GL_BLEND);
  glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
  glUseProgram(s_prog);
  glBindBuffer(GL_ARRAY_BUFFER, s_vbo);
  glEnableVertexAttribArray(0);
  glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 0, 0);

  // 1. Dark outline (expanded border)
  int n_outline = build_arrow(verts, x, y, 1.0f, 2.5f, sw, sh);
  glBufferData(GL_ARRAY_BUFFER, n_outline * 2 * sizeof(float), verts, GL_DYNAMIC_DRAW);
  glUniform4f(s_loc_color, 0.0f, 0.0f, 0.0f, alpha * 0.9f);
  glDrawArrays(GL_TRIANGLES, 0, n_outline);

  // 2. Crisp white interior
  int n_fill = build_arrow(verts, x, y, 1.0f, 0.0f, sw, sh);
  glBufferData(GL_ARRAY_BUFFER, n_fill * 2 * sizeof(float), verts, GL_DYNAMIC_DRAW);
  glUniform4f(s_loc_color, 1.0f, 1.0f, 1.0f, alpha);
  glDrawArrays(GL_TRIANGLES, 0, n_fill);

  // Restore GL state completely
  if (prev_attrib0_enabled) {
    glEnableVertexAttribArray(0);
  } else {
    glDisableVertexAttribArray(0);
  }
  glBindBuffer(GL_ARRAY_BUFFER, prev_array_buf);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, prev_elem_buf);
  glUseProgram(prev_prog);
  glBindFramebuffer(GL_FRAMEBUFFER, prev_fbo);
  glViewport(prev_viewport[0], prev_viewport[1], prev_viewport[2], prev_viewport[3]);
  glBlendFuncSeparate(prev_blend_src_rgb, prev_blend_dst_rgb, prev_blend_src_alpha, prev_blend_dst_alpha);
  if (prev_blend) glEnable(GL_BLEND); else glDisable(GL_BLEND);
  if (prev_depth) glEnable(GL_DEPTH_TEST); else glDisable(GL_DEPTH_TEST);
  if (prev_cull) glEnable(GL_CULL_FACE); else glDisable(GL_CULL_FACE);
  if (prev_scissor) glEnable(GL_SCISSOR_TEST); else glDisable(GL_SCISSOR_TEST);
}
