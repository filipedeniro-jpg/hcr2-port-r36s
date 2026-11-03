// telemetria_gl.h -- Interceptacao e telemetria de chamadas OpenGL ES 2.0
#ifndef __TELEMETRIA_GL_H__
#define __TELEMETRIA_GL_H__

#include <GLES2/gl2.h>

// Instrumentacao de compilacao e link de shaders
void glCompileShader_telemetria(GLuint shader);
void glLinkProgram_telemetria(GLuint program);
void glUseProgram_telemetria(GLuint program);

// Instrumentacao de despacho de geometria
void glDrawArrays_telemetria(GLenum mode, GLint first, GLsizei count);
void glDrawElements_telemetria(GLenum mode, GLsizei count, GLenum type, const void *indices);

#endif // __TELEMETRIA_GL_H__

