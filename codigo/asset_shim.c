// asset_shim.c -- Emulacao de AAssetManager do NDK sobre o sistema de arquivos local
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>

#include "config.h"
#include "asset_shim.h"

#define ASSET_READ_AHEAD (32 * 1024)

struct AAssetManager { int _unused; };
static AAssetManager g_manager;

struct AAsset { FILE *f; long size; char path[512]; };
struct AAssetDir { DIR *dir; char base[512]; char name[512]; };

AAssetManager *asset_manager(void) { return &g_manager; }

// Converte caminho relativo do AAssetManager para o sistema de arquivos local
static void asset_path(char *out, size_t out_size, const char *name) {
  if (name[0] == '/' || strstr(name, ":/")) {
    snprintf(out, out_size, "%s", name);
    return;
  }
  if (strncmp(name, "assets/", 7) == 0)
    name += 7;

  // Correcao de caminhos de pistas concatenados sem separador (ex: "...cup_04.jsontrailerpark_cup.rube")
  const char *json_pos = strstr(name, ".json");
  if (json_pos && strlen(json_pos) > 5) {
    const char *sub_nome = json_pos + 5;
    struct stat st;
    // Tenta primeiro em HD para plists e graficos de pistas
    snprintf(out, out_size, "%s/HD/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/Common/rube/levels/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/Common/rube/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/Common/json/levels/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/json/levels/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/Common/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
    snprintf(out, out_size, "%s/%s", ASSETS_DIR, sub_nome);
    if (stat(out, &st) == 0) return;
  }

  snprintf(out, out_size, "%s/%s", ASSETS_DIR, name);
}

AAssetManager *AAssetManager_fromJava_fake(void *env, void *obj) {
  (void)env; (void)obj;
  return &g_manager;
}

static char s_ultimo_asset_erro[256] = "";
static int s_contador_asset_erro = 0;

static FILE *tentar_abrir_hd(const char *filename, char *caminho_saida, size_t tam_saida) {
  char tentativa[512];
  const char *sem_hd = (strncmp(filename, "HD/", 3) == 0) ? filename + 3 : filename;
  const char *base = strrchr(filename, '/');
  const char *nome_limpo = base ? base + 1 : filename;
  FILE *f = NULL;

  // 1. Shaders (.vsh, .fsh, .psh) residentes em Common/shaders
  if (strstr(filename, ".vsh") || strstr(filename, ".fsh") || strstr(filename, "shaders/")) {
    snprintf(tentativa, sizeof(tentativa), "%s/Common/shaders/%s", ASSETS_DIR, nome_limpo);
    if ((f = fopen(tentativa, "rb"))) goto sucesso;
    snprintf(tentativa, sizeof(tentativa), "%s/HD/shaders/%s", ASSETS_DIR, nome_limpo);
    if ((f = fopen(tentativa, "rb"))) goto sucesso;
  }

  // 2. Texturas de terreno (terrain-ground.png, terrain-bottom-*.png, etc.)
  if (!strstr(filename, "background") && !strstr(nome_limpo, "background") &&
      (strstr(filename, "terrain") || strstr(nome_limpo, "terrain") ||
       strstr(filename, "textures/terrain/"))) {
    snprintf(tentativa, sizeof(tentativa), "%s/HD/textures/terrain/%s", ASSETS_DIR, nome_limpo);
    if ((f = fopen(tentativa, "rb"))) goto sucesso;
    snprintf(tentativa, sizeof(tentativa), "%s/textures/terrain/%s", ASSETS_DIR, nome_limpo);
    if ((f = fopen(tentativa, "rb"))) goto sucesso;
    snprintf(tentativa, sizeof(tentativa), "%s/Common/textures/terrain/%s", ASSETS_DIR, nome_limpo);
    if ((f = fopen(tentativa, "rb"))) goto sucesso;
  }

  // 3. Subpasta de texturas (evita duplicacao textures/textures/)
  const char *sem_tex = (strncmp(sem_hd, "textures/", 9) == 0) ? sem_hd + 9 : sem_hd;
  snprintf(tentativa, sizeof(tentativa), "%s/HD/textures/%s", ASSETS_DIR, sem_tex);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/textures/%s", ASSETS_DIR, sem_tex);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;

  // 4. Raiz de HD e subpastas tematicas (backgrounds, ui cocosstudio, elements)
  snprintf(tentativa, sizeof(tentativa), "%s/HD/%s", ASSETS_DIR, sem_hd);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/HD/%s", ASSETS_DIR, nome_limpo);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/HD/ui/cocosstudio/backgrounds/%s", ASSETS_DIR, nome_limpo);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/HD/ui/cocosstudio/elements/%s", ASSETS_DIR, nome_limpo);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/HD/ui/cocosstudio/leagues/%s", ASSETS_DIR, nome_limpo);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/Common/%s", ASSETS_DIR, sem_hd);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;
  snprintf(tentativa, sizeof(tentativa), "%s/Common/%s", ASSETS_DIR, nome_limpo);
  if ((f = fopen(tentativa, "rb"))) goto sucesso;

  return NULL;

sucesso:
  if (caminho_saida && tam_saida > 0)
    snprintf(caminho_saida, tam_saida, "%s", tentativa);
  return f;
}

AAsset *AAssetManager_open_fake(AAssetManager *mgr, const char *filename, int mode) {
  (void)mgr; (void)mode;
  if (!filename)
    return NULL;

  char path[512];
  asset_path(path, sizeof(path), filename);

  FILE *f = fopen(path, "rb");

  // Resolucao universal de atlases, texturas (.png) e shaders (.vsh, .fsh)
  if (!f && (strstr(filename, ".plist") || strstr(filename, ".png") ||
             strstr(filename, ".vsh") || strstr(filename, ".fsh") ||
             strstr(filename, "shaders/"))) {
    f = tentar_abrir_hd(filename, path, sizeof(path));
    if (f) {
      printf("[HD ASSET] Localizado '%s' -> '%s'\n", filename, path);
      fflush(stdout);
    }
  }
  if (!f && (strstr(filename, ".ogg") || strstr(filename, "audio/") || strstr(filename, "music/"))) {
    char audio_t[512];
    const char *base = strrchr(filename, '/');
    const char *nome_limpo = base ? base + 1 : filename;
    snprintf(audio_t, sizeof(audio_t), "%s/Common/audio/%s", ASSETS_DIR, nome_limpo);
    if (!(f = fopen(audio_t, "rb"))) {
      snprintf(audio_t, sizeof(audio_t), "%s/Common/music/%s", ASSETS_DIR, nome_limpo);
      if (!(f = fopen(audio_t, "rb"))) {
        snprintf(audio_t, sizeof(audio_t), "%s/Common/%s", ASSETS_DIR, filename);
        f = fopen(audio_t, "rb");
      }
    }
  }

  // Telemetria factual compulsoria de texturas e shaders de terreno (Regra 10)
  if (strstr(filename, "terrain") || (strstr(filename, "ground") && !strstr(filename, "background"))) {
    printf("[TERRAIN ASSET] Requisicao '%s' -> '%s' (sucesso=%d)\n", filename, path, f != NULL);
    fflush(stdout);
  }

  if (!f) {
    // Silencia apenas tentativas exploratorias genericas que nao sejam texturas criticas
    if ((strncmp(filename, "HD/", 3) == 0 || strncmp(filename, "tablet/", 7) == 0) &&
        !strstr(filename, ".png") && !strstr(filename, ".plist") && !strstr(filename, "terrain")) {
      return NULL;
    }
    // Filtro inteligente de de-duplicacao para blindar I/O e log enxuto (AGENTS.md Secao 6)
    if (!strcmp(s_ultimo_asset_erro, filename)) {
      s_contador_asset_erro++;
    } else {
      if (s_contador_asset_erro > 2) {
        printf("[ASSET RESUMO] Requisicao repetida %d vezes para '%s'\n", s_contador_asset_erro, s_ultimo_asset_erro);
      }
      snprintf(s_ultimo_asset_erro, sizeof(s_ultimo_asset_erro), "%s", filename);
      s_contador_asset_erro = 1;
      printf("[ASSET NAO ENCONTRADO] %s\n", filename);
      fflush(stdout);
    }
    return NULL;
  }

  if (s_contador_asset_erro > 2) {
    printf("[ASSET RESUMO] Requisicao repetida %d vezes para '%s'\n", s_contador_asset_erro, s_ultimo_asset_erro);
    s_contador_asset_erro = 0;
    s_ultimo_asset_erro[0] = '\0';
  }

  static int s_total_assets_abertos = 0;
  s_total_assets_abertos++;
  if (s_total_assets_abertos % 500 == 0 || s_total_assets_abertos == 1) {
    printf("[ASSETS STATUS] Carregados %d arquivos locais...\n", s_total_assets_abertos);
    fflush(stdout);
  }

  struct stat st;
  if (fstat(fileno(f), &st) != 0 || st.st_size < 0) {
    fclose(f);
    return NULL;
  }
  setvbuf(f, NULL, _IOFBF, ASSET_READ_AHEAD);

  AAsset *a = calloc(1, sizeof(*a));
  if (!a) { fclose(f); return NULL; }
  a->f = f;
  a->size = (long)st.st_size;
  snprintf(a->path, sizeof(a->path), "%s", path);
  return a;
}

int AAsset_read_fake(AAsset *a, void *buf, size_t count) {
  if (!a || !a->f)
    return -1;
  return (int)fread(buf, 1, count, a->f);
}

int64_t AAsset_seek_fake(AAsset *a, int64_t off, int whence) {
  if (!a || !a->f)
    return -1;
  if (fseek(a->f, (long)off, whence) != 0)
    return -1;
  return (int64_t)ftell(a->f);
}

void AAsset_close_fake(AAsset *a) {
  if (!a)
    return;
  if (a->f)
    fclose(a->f);
  free(a);
}

int64_t AAsset_getLength_fake(AAsset *a) { return a ? (int64_t)a->size : 0; }
int64_t AAsset_getLength64_fake(AAsset *a) { return a ? (int64_t)a->size : 0; }

// Hand back a real fd + [0, size) range. The engine reads the file itself.
int AAsset_openFileDescriptor64_fake(AAsset *a, int64_t *out_start, int64_t *out_length) {
  if (!a)
    return -1;
  int fd = open(a->path, O_RDONLY);
  if (fd < 0)
    return -1;
  if (out_start)  *out_start = 0;
  if (out_length) *out_length = (int64_t)a->size;
  return fd;
}

AAssetDir *AAssetManager_openDir_fake(AAssetManager *mgr, const char *dirName) {
  (void)mgr;
  char path[512];
  asset_path(path, sizeof(path), dirName ? dirName : "");

  DIR *d = opendir(path);
  if (!d)
    return NULL;

  AAssetDir *ad = calloc(1, sizeof(*ad));
  if (!ad) { closedir(d); return NULL; }
  ad->dir = d;
  // remember the assets-relative prefix so getNextFileName returns full paths
  const char *rel = dirName ? dirName : "";
  while (*rel == '/') rel++;
  if (strncmp(rel, "assets/", 7) == 0) rel += 7;
  if (*rel)
    snprintf(ad->base, sizeof(ad->base), "%s/", rel);
  else
    ad->base[0] = '\0';
  return ad;
}

const char *AAssetDir_getNextFileName_fake(AAssetDir *dir) {
  if (!dir || !dir->dir)
    return NULL;
  struct dirent *e;
  while ((e = readdir(dir->dir)) != NULL) {
    if (e->d_name[0] == '.') // skip "." / ".." / hidden
      continue;
    // AAssetDir only enumerates regular files, not subdirectories
    char full[1024];
    snprintf(full, sizeof(full), "%s/%s%s", ASSETS_DIR, dir->base, e->d_name);
    struct stat st;
    if (stat(full, &st) == 0 && S_ISDIR(st.st_mode))
      continue;
    snprintf(dir->name, sizeof(dir->name), "%.255s%.255s", dir->base, e->d_name);
    return dir->name;
  }
  return NULL;
}

void AAssetDir_close_fake(AAssetDir *dir) {
  if (!dir)
    return;
  if (dir->dir)
    closedir(dir->dir);
  free(dir);
}
