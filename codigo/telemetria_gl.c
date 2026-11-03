// telemetria_gl.c -- Rastreamento em tempo real do pipeline OpenGL ES 2.0
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <GLES2/gl2.h>
#include "telemetria_gl.h"

static GLuint s_programa_atual = 0;
static GLuint s_ultimo_prog_terreno = 100;
static int s_contador_draw_strip = 0;

void glCompileShader_telemetria(GLuint shader) {
    glCompileShader(shader);
    GLint status = 0;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &status);
    if (status == GL_FALSE) {
        char log_erro[1024] = {0};
        GLsizei len = 0;
        glGetShaderInfoLog(shader, sizeof(log_erro) - 1, &len, log_erro);
        printf("[OPENGL SHADER ERRO] Shader ID=%u falhou ao compilar:\n%s\n", shader, log_erro);
        fflush(stdout);
    } else {
        printf("[OPENGL SHADER OK] Shader ID=%u compilado com sucesso.\n", shader);
        fflush(stdout);
    }
}

void glLinkProgram_telemetria(GLuint program) {
    glLinkProgram(program);
    GLint status = 0;
    glGetProgramiv(program, GL_LINK_STATUS, &status);
    if (status == GL_FALSE) {
        char log_erro[1024] = {0};
        GLsizei len = 0;
        glGetProgramInfoLog(program, sizeof(log_erro) - 1, &len, log_erro);
        printf("[OPENGL PROGRAM ERRO] Program ID=%u falhou ao linkar:\n%s\n", program, log_erro);
        fflush(stdout);
    } else {
        printf("[OPENGL PROGRAM OK] Program ID=%u linkado com sucesso.\n", program);
        fflush(stdout);
    }
}

void glUseProgram_telemetria(GLuint program) {
    glUseProgram(program);
    s_programa_atual = program;
}

void glDrawArrays_telemetria(GLenum mode, GLint first, GLsizei count) {
    static int s_contador_malha_grande = 0;
    // Captura estritamente as malhas reais de estrada e solo subterrâneo (count >= 100)
    if (count >= 100) {
        glDisable(GL_CULL_FACE);
        GLint prog_ativo = 0;
        glGetIntegerv(GL_CURRENT_PROGRAM, &prog_ativo);
        if (prog_ativo == 0) {
            GLuint prog_recup = s_programa_atual ? s_programa_atual : s_ultimo_prog_terreno;
            glUseProgram(prog_recup);
            prog_ativo = (GLint)prog_recup;
        } else {
            s_ultimo_prog_terreno = (GLuint)prog_ativo;
        }

        s_contador_malha_grande++;
        if (s_contador_malha_grande <= 30 || s_contador_malha_grande % 60 == 0) {
            GLboolean blend_en = glIsEnabled(GL_BLEND);
            GLboolean depth_en = glIsEnabled(GL_DEPTH_TEST);
            GLboolean cull_en = glIsEnabled(GL_CULL_FACE);
            GLenum err = glGetError();
            printf("[TERRAIN MESH DRAW #%d] mode=%d, count=%d, prog=%d (cached=%u), blend=%d, depth=%d, cull=%d, err=0x%X\n",
                   s_contador_malha_grande, mode, count, prog_ativo, s_programa_atual, blend_en, depth_en, cull_en, err);
            fflush(stdout);
        }
    } else if (mode == GL_TRIANGLE_STRIP) {
        s_contador_draw_strip++;
        if (s_contador_draw_strip <= 5 || s_contador_draw_strip % 180 == 0) {
            GLenum err = glGetError();
            printf("[GL DRAW STRIP #%d] first=%d, count=%d, prog=%u, err=0x%X\n",
                   s_contador_draw_strip, first, count, s_programa_atual, err);
            fflush(stdout);
        }
    }
    glDrawArrays(mode, first, count);
}

void glDrawElements_telemetria(GLenum mode, GLsizei count, GLenum type, const void *indices) {
    static int s_contador_elem = 0;
    if (count > 4) {
        s_contador_elem++;
        if (s_contador_elem <= 20 || s_contador_elem % 60 == 0) {
            printf("[GL DRAW ELEMENTS #%d] mode=%d, count=%d, type=%d, prog=%u\n",
                   s_contador_elem, mode, count, type, s_programa_atual);
            fflush(stdout);
        }
    }
    glDrawElements(mode, count, type, indices);
}

