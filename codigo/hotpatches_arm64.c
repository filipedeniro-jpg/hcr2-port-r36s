#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <sys/mman.h>
#include <GLES2/gl2.h>

#include "hotpatches_arm64.h"
#include "so_util.h"

extern so_module game_mod;

// Trampolins de 8 instruções ARM64 (32 bytes)
static uint32_t s_trampolim_glyph[8] __attribute__((aligned(8)));
static uint32_t s_trampolim_terrain_draw[8] __attribute__((aligned(8)));
static uint32_t s_trampolim_terrain_alloc[8] __attribute__((aligned(8)));
static uint32_t s_trampolim_terrain_upload[8] __attribute__((aligned(8)));

typedef struct {
  char bytes[24];
} StdString24;

typedef StdString24 (*fn_glyph_name_orig)(void *glyph, int w1, int w2);
typedef void (*fn_terrain_draw_orig)(void *self, void *arg1, void *arg2);
typedef void *(*fn_terrain_alloc_orig)(void *self, int count, int arg2);
typedef void (*fn_terrain_upload_orig)(void *self);

static void instalar_gancho_arm64(uint32_t offset, void *hook_fn, uint32_t *trampolim) {
  if (game_mod.load_size < offset + 16) return;
  uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + offset);
  for (int i = 0; i < 4; i++) trampolim[i] = site[i];
  trampolim[4] = 0x58000050u; // ldr x16, [pc, #8]
  trampolim[5] = 0xd61f0200u; // br x16
  *(uint64_t *)(&trampolim[6]) = (uint64_t)((uintptr_t)game_mod.load_base + offset + 16);
  uintptr_t pag = (uintptr_t)trampolim & ~(uintptr_t)0xfff;
  mprotect((void *)pag, 0x2000, PROT_READ | PROT_WRITE | PROT_EXEC);
  __builtin___clear_cache((char *)trampolim, (char *)trampolim + 32);

  site[0] = 0x58000050u; site[1] = 0xd61f0200u;
  *(uint64_t *)(&site[2]) = (uint64_t)(uintptr_t)hook_fn;
  __builtin___clear_cache((char *)site, (char *)site + 16);
}

static StdString24 hook_get_glyph_name(void *glyph, int w1, int w2) {
  StdString24 vazio;
  memset(&vazio, 0, sizeof(vazio));
  if (!glyph) return vazio;
  fn_glyph_name_orig fn_orig = (fn_glyph_name_orig)(void *)s_trampolim_glyph;
  return fn_orig(glyph, w1, w2);
}

// Intercepta upload nativo dos buffers (0x6D28A4) e imprime telemetria real dos vértices
static void hook_terrain_upload(void *self) {
  static int s_up_cont = 0;
  s_up_cont++;
  void *buf_358 = *(void **)((char *)self + 0x358);
  void *buf_360 = *(void **)((char *)self + 0x360);
  if (s_up_cont <= 3 && buf_358 && buf_360) {
    int cnt_road = *(int *)((char *)buf_358 + 0x34);
    int cnt_soil = *(int *)((char *)buf_360 + 0x34);
    float *vtx_road = *(float **)((char *)buf_358 + 0x28);
    float *vtx_soil = *(float **)((char *)buf_360 + 0x28);
    printf("[TERRAIN UPLOAD #%d] self=%p, cnt_road=%d, cnt_soil=%d\n", s_up_cont, self, cnt_road, cnt_soil);
    if (vtx_road && cnt_road >= 2) {
      printf("  ROAD VTX0: pos=(%.1f, %.1f, %.1f, %.1f) uv=(%.3f, %.3f, %.3f, %.3f)\n",
             vtx_road[0], vtx_road[1], vtx_road[2], vtx_road[3], vtx_road[4], vtx_road[5], vtx_road[6], vtx_road[7]);
      printf("  ROAD VTX1: pos=(%.1f, %.1f, %.1f, %.1f) uv=(%.3f, %.3f, %.3f, %.3f)\n",
             vtx_road[8], vtx_road[9], vtx_road[10], vtx_road[11], vtx_road[12], vtx_road[13], vtx_road[14], vtx_road[15]);
    }
    if (vtx_soil && cnt_soil >= 2) {
      printf("  SOIL VTX0: pos=(%.1f, %.1f, %.1f, %.1f) uv=(%.3f, %.3f, %.3f, %.3f)\n",
             vtx_soil[0], vtx_soil[1], vtx_soil[2], vtx_soil[3], vtx_soil[4], vtx_soil[5], vtx_soil[6], vtx_soil[7]);
      printf("  SOIL VTX1: pos=(%.1f, %.1f, %.1f, %.1f) uv=(%.3f, %.3f, %.3f, %.3f)\n",
             vtx_soil[8], vtx_soil[9], vtx_soil[10], vtx_soil[11], vtx_soil[12], vtx_soil[13], vtx_soil[14], vtx_soil[15]);
    }
    fflush(stdout);
  }
  fn_terrain_upload_orig fn_orig = (fn_terrain_upload_orig)(void *)s_trampolim_terrain_upload;
  fn_orig(self);
}

static void hook_terrain_draw(void *self, void *arg1, void *arg2) {
  static int s_cont = 0;
  s_cont++;
  void *tex_678 = *(void **)((char *)self + 0x678);
  void *tex_688 = *(void **)((char *)self + 0x688);
  void **p_tex_690 = (void **)((char *)self + 0x690);
  void **p_tex_698 = (void **)((char *)self + 0x698);
  int solo_nativo_carregado = (*p_tex_698 != NULL);

  // Ativa a textura de solo nativa verde-escuro (terrain-ground.png) no Passe 2
  if (*(void **)((char *)self + 0x688) == NULL && *p_tex_698 != NULL) {
    *(void **)((char *)self + 0x688) = *p_tex_698;
  }
  if (*p_tex_690 == NULL && *p_tex_698 != NULL) {
    *p_tex_690 = *p_tex_698;
  }

  void **p_prog_road = (void **)((char *)self + 0x6a0);
  void *prog_soil = *(void **)((char *)self + 0x6b8);
  if (!*p_prog_road && prog_soil) {
    *p_prog_road = prog_soil;
  }

  void *prog_road = *p_prog_road;
  void *prog_soil2 = *(void **)((char *)self + 0x6c0);
  void *tex_698 = *p_tex_698;

  if (s_cont <= 5 || s_cont % 60 == 0) {
    void *buf_358 = *(void **)((char *)self + 0x358);
    void *buf_360 = *(void **)((char *)self + 0x360);
    int cnt_road = buf_358 ? *(int *)((char *)buf_358 + 0x34) : -1;
    int cnt_soil = buf_360 ? *(int *)((char *)buf_360 + 0x34) : -1;
    uint32_t gl_road = prog_road ? *(uint32_t *)((char *)prog_road + 0x20) : 0;
    uint32_t gl_soil = prog_soil ? *(uint32_t *)((char *)prog_soil + 0x20) : 0;
    uint32_t gl_soil2 = prog_soil2 ? *(uint32_t *)((char *)prog_soil2 + 0x20) : 0;
    void *ptr_3b8 = *(void **)((char *)self + 0x3b8);
    float depth_3b8 = ptr_3b8 ? *(float *)((char *)ptr_3b8 + 0x11c) : -9999.0f;
    int u_loc_params = *(int *)((char *)self + 0x6c8);
    printf("[TERRAIN DRAW #%d] 3b8=%p (depth=%.1f) nativo_soil=%d u_loc=%d prog_gl=(%u, %u, %u) vts=(%d, %d)\n",
           s_cont, ptr_3b8, depth_3b8, solo_nativo_carregado, u_loc_params, gl_road, gl_soil, gl_soil2, cnt_road, cnt_soil);
    if (s_cont <= 2 && buf_360) {
      float *v = *(float **)((char *)buf_360 + 0x28);
      if (v && cnt_soil >= 4) {
        printf("  SOIL V0: pos=(%.1f,%.1f,%.1f,%.1f) uv=(%.2f,%.2f,%.2f,%.2f)\n",
               v[0], v[1], v[2], v[3], v[4], v[5], v[6], v[7]);
        printf("  SOIL V1: pos=(%.1f,%.1f,%.1f,%.1f) uv=(%.2f,%.2f,%.2f,%.2f)\n",
               v[8], v[9], v[10], v[11], v[12], v[13], v[14], v[15]);
        printf("  SOIL V2: pos=(%.1f,%.1f,%.1f,%.1f) uv=(%.2f,%.2f,%.2f,%.2f)\n",
               v[16], v[17], v[18], v[19], v[20], v[21], v[22], v[23]);
        printf("  SOIL V3: pos=(%.1f,%.1f,%.1f,%.1f) uv=(%.2f,%.2f,%.2f,%.2f)\n",
               v[24], v[25], v[26], v[27], v[28], v[29], v[30], v[31]);
      }
    }
    fflush(stdout);
  }
  fn_terrain_draw_orig fn_orig = (fn_terrain_draw_orig)(void *)s_trampolim_terrain_draw;
  fn_orig(self, arg1, arg2);
}

static void *hook_terrain_alloc(void *self, int count, int arg2) {
  fn_terrain_alloc_orig fn_orig = (fn_terrain_alloc_orig)(void *)s_trampolim_terrain_alloc;
  return fn_orig(self, count, arg2);
}

void patch_validacao_savegame_offline(void) {
  enum { OFFSET_VALIDACAO = 0x533440 };
  if (game_mod.load_size >= OFFSET_VALIDACAO + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_VALIDACAO);
    *site = 0x140002dau; // b #0x533fa8
  }
  static const uint32_t checagens_servidor[] = { 0x53c680, 0x53c6a0 };
  for (size_t i = 0; i < sizeof(checagens_servidor)/sizeof(checagens_servidor[0]); i++) {
    uint32_t off = checagens_servidor[i];
    if (game_mod.load_size >= off + 4) {
      uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + off);
      *site = 0xd503201fu; // NOP
    }
  }
}

void patch_blindagem_veiculos_garagem(void) {
  if (game_mod.load_size >= 0x00C68368 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68368);
    *site = 0xb4002ee0u; // cbz x0, #0xc68944
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= 0x00C68D00 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68D00);
    *site = 0xb4000720u; // cbz x0, #0xc68de4
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= 0x00C68EEC + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00C68EEC);
    *site = 0xb4000640u; // cbz x0, #0xc68fb4
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
}

void patch_blindagem_texto_fontes(void) {
  if (game_mod.load_size >= 0x00A7CE34 + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + 0x00A7CE34);
    *site = 0xb40001e0u; // cbz x0, #0xa7ce70
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
}

void patch_blindagem_universal_glifos(void) {
  instalar_gancho_arm64(0x00972404, (void *)&hook_get_glyph_name, s_trampolim_glyph);
}

void patch_instrumentacao_pipeline_terreno(void) {
  enum {
    OFFSET_TERRAIN_DRAW   = 0x6D3368,
    OFFSET_TERRAIN_ALLOC  = 0x6D15B8,
    OFFSET_TERRAIN_UPLOAD = 0x6D28A4
  };
  instalar_gancho_arm64(OFFSET_TERRAIN_DRAW, (void *)&hook_terrain_draw, s_trampolim_terrain_draw);
  instalar_gancho_arm64(OFFSET_TERRAIN_ALLOC, (void *)&hook_terrain_alloc, s_trampolim_terrain_alloc);
  instalar_gancho_arm64(OFFSET_TERRAIN_UPLOAD, (void *)&hook_terrain_upload, s_trampolim_terrain_upload);
  printf("[HCR2] Hooks de terreno instalados (draw=0x%X, alloc=0x%X, upload=0x%X)!\n",
         OFFSET_TERRAIN_DRAW, OFFSET_TERRAIN_ALLOC, OFFSET_TERRAIN_UPLOAD);
  fflush(stdout);
}

void patch_ativar_transicao_garagem_imediata(void) {
  enum {
    OFFSET_SPLASH_TIMER     = 0x00A1D794,
    OFFSET_SPLASH_GARAGEM   = 0x00A1D834,
    OFFSET_SPLASH_CHALLENGE = 0x00A1D938,
    OFFSET_SPLASH_PENDING   = 0x00A1D9D4
  };
  if (game_mod.load_size >= OFFSET_SPLASH_TIMER + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_TIMER);
    *site = 0xd503201fu; // NOP
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= OFFSET_SPLASH_GARAGEM + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_GARAGEM);
    *site = 0xd503201fu; // NOP
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= OFFSET_SPLASH_CHALLENGE + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_CHALLENGE);
    *site = 0x14000060u; // b #0xa1dab8
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= OFFSET_SPLASH_PENDING + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_SPLASH_PENDING);
    *site = 0xd503201fu; // NOP
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
}

void patch_forcar_carregamento_solo_nativo(void) {
  enum {
    OFFSET_QUALIDADE_GRAFICA = 0x006D12F0, // b.ne #0x6d1364 (pula se getGraphicQuality != High)
    OFFSET_FCMP_PROFUNDIDADE = 0x006D1320  // b.le #0x6d1364 (pula se profundidade <= threshold)
  };
  if (game_mod.load_size >= OFFSET_QUALIDADE_GRAFICA + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_QUALIDADE_GRAFICA);
    *site = 0xd503201fu; // NOP
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  if (game_mod.load_size >= OFFSET_FCMP_PROFUNDIDADE + 4) {
    uint32_t *site = (uint32_t *)((uintptr_t)game_mod.load_base + OFFSET_FCMP_PROFUNDIDADE);
    *site = 0xd503201fu; // NOP
    __builtin___clear_cache((char *)site, (char *)site + 4);
  }
  printf("[HCR2] Hotpatch de carregamento de solo nativo aplicado (0x6D12F0 e 0x6D1320 NOPs)!\n");
  fflush(stdout);
}

void aplicar_hotpatches_arm64(void) {
  patch_validacao_savegame_offline();
  patch_blindagem_veiculos_garagem();
  patch_blindagem_texto_fontes();
  patch_blindagem_universal_glifos();
  patch_instrumentacao_pipeline_terreno();
  patch_forcar_carregamento_solo_nativo();
}
