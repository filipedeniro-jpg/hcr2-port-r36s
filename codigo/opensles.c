/* opensles.c -- minimal OpenSL ES shim backed by SDL2 audio
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 *
 * Implements the slice of OpenSL ES 1.0.1 the SQEX "Sd" sound driver uses:
 * the Object interface (Realize/GetInterface/Destroy), the Engine interface
 * (CreateOutputMix/CreateAudioPlayer), and on each player the Play, Volume and
 * AndroidSimpleBufferQueue interfaces. Players are software-mixed into one SDL2
 * audio output; the buffer-queue completion callback is fired while the
 * Switch audio service releases each finished buffer.
 */

#include <stdlib.h>
#include <malloc.h>
#include <stdint.h>
#include <string.h>
#include <stddef.h>
#include <math.h>
#include <switch.h>
#include <SDL2/SDL.h>

#include "opensles.h"
#include "asset_shim.h"
#include "util.h"
#include "config.h"

// HCR's Android SimpleAudioEngine feeds OGG assets through Java/SoundPool;
// this wrapper decodes the same assets directly. Prefer a vendored decoder in
// this project, but retain the old reference-project path for existing trees.
#define STB_VORBIS_NO_STDIO
#if defined(__GNUC__)
#  pragma GCC diagnostic push
#  pragma GCC diagnostic ignored "-Wunused-function"
#  pragma GCC diagnostic ignored "-Wunused-parameter"
#  pragma GCC diagnostic ignored "-Wsign-compare"
/*
 * GCC can report -Wmaybe-uninitialized inside upstream stb_vorbis at -O2
 * (notably get_seek_page_info's stack header buffer). Keep -Werror ativado for
 * the port itself, but do not make a third-party optimizer false-positive fatal.
 */
#  if !defined(__clang__)
#    pragma GCC diagnostic ignored "-Wmaybe-uninitialized"
#  endif
#endif
#include "stb_vorbis.c"
#if defined(__GNUC__)
#  pragma GCC diagnostic pop
#endif

// --- OpenSL ES constants ----------------------------------------------------

#define SL_RESULT_SUCCESS              0
#define SL_RESULT_PARAMETER_INVALID    0x0D
#define SL_RESULT_FEATURE_UNSUPPORTED  0x0C

#define SL_BOOLEAN_FALSE 0
#define SL_BOOLEAN_TRUE  1

#define SL_PLAYSTATE_STOPPED 1
#define SL_PLAYSTATE_PAUSED  2
#define SL_PLAYSTATE_PLAYING 3

#define SL_OBJECT_STATE_REALIZED 2

typedef uint32_t SLuint32;
typedef int32_t  SLint32;
typedef uint32_t SLresult;
typedef uint32_t SLboolean;
typedef int32_t  SLmillibel;

// PCM data format (samplesPerSec is in milliHz per the spec)
typedef struct {
  SLuint32 formatType;
  SLuint32 numChannels;
  SLuint32 samplesPerSec;
  SLuint32 bitsPerSample;
  SLuint32 containerSize;
  SLuint32 channelMask;
  SLuint32 endianness;
} SLDataFormat_PCM;

typedef struct {
  SLuint32 locatorType;
  SLuint32 numBuffers;
} SLDataLocator_BufferQueue;

typedef struct {
  void *pLocator;
  void *pFormat;
} SLDataSource;

typedef struct {
  void *pLocator;
  void *pFormat;
} SLDataSink;

typedef void *SLObjectItf;       // -> &obj->obj_vt
typedef void *SLInterfaceID;

// callback: (SLAndroidSimpleBufferQueueItf caller, void *pContext)
typedef void (*slBufferQueueCallback)(void *caller, void *context);

// --- interface-id sentinels -------------------------------------------------

#define DEF_IID(n) void *SL_IID_##n = &SL_IID_##n
DEF_IID(3DCOMMIT); DEF_IID(3DDOPPLER); DEF_IID(3DGROUPING); DEF_IID(3DLOCATION);
DEF_IID(3DMACROSCOPIC); DEF_IID(3DSOURCE); DEF_IID(ANDROIDCONFIGURATION);
DEF_IID(ANDROIDEFFECT); DEF_IID(ANDROIDEFFECTCAPABILITIES); DEF_IID(ANDROIDEFFECTSEND);
DEF_IID(ANDROIDSIMPLEBUFFERQUEUE); DEF_IID(AUDIODECODERCAPABILITIES); DEF_IID(AUDIOENCODER);
DEF_IID(AUDIOENCODERCAPABILITIES); DEF_IID(AUDIOIODEVICECAPABILITIES); DEF_IID(BASSBOOST);
DEF_IID(BUFFERQUEUE); DEF_IID(DEVICEVOLUME); DEF_IID(DYNAMICINTERFACEMANAGEMENT);
DEF_IID(DYNAMICSOURCE); DEF_IID(EFFECTSEND); DEF_IID(ENGINE); DEF_IID(ENGINECAPABILITIES);
DEF_IID(ENVIRONMENTALREVERB); DEF_IID(EQUALIZER); DEF_IID(LED); DEF_IID(METADATAEXTRACTION);
DEF_IID(METADATATRAVERSAL); DEF_IID(MIDIMESSAGE); DEF_IID(MIDIMUTESOLO); DEF_IID(MIDITEMPO);
DEF_IID(MIDITIME); DEF_IID(MUTESOLO); DEF_IID(NULL); DEF_IID(OBJECT); DEF_IID(OUTPUTMIX);
DEF_IID(PITCH); DEF_IID(PLAY); DEF_IID(PLAYBACKRATE); DEF_IID(PREFETCHSTATUS);
DEF_IID(PRESETREVERB); DEF_IID(RATEPITCH); DEF_IID(RECORD); DEF_IID(SEEK); DEF_IID(THREADSYNC);
DEF_IID(VIBRA); DEF_IID(VIRTUALIZER); DEF_IID(VISUALIZATION); DEF_IID(VOLUME);
#undef DEF_IID

// --- vtable structs (method order matches the OpenSL ES 1.0.1 spec) ---------

typedef struct {
  SLresult (*Realize)(void *self, SLboolean async);
  SLresult (*Resume)(void *self, SLboolean async);
  SLresult (*GetState)(void *self, SLuint32 *pState);
  SLresult (*GetInterface)(void *self, const SLInterfaceID iid, void *pInterface);
  SLresult (*RegisterCallback)(void *self, void *cb, void *ctx);
  SLresult (*AbortAsyncOperation)(void *self);
  void     (*Destroy)(void *self);
  SLresult (*SetPriority)(void *self, SLint32 priority, SLboolean preemptable);
  SLresult (*GetPriority)(void *self, SLint32 *pPriority);
  SLresult (*SetLossOfControlInterfaces)(void *self, SLint32 n, SLInterfaceID *ids, SLboolean enabled);
} SLObjectItf_;

// Only CreateAudioPlayer (slot 2) and CreateOutputMix (slot 7) are used. The
// remaining slots retain their ABI positions through one variadic unsupported
// function type, avoiding object/function-pointer casts.
typedef SLresult (*SLEngineUnsupportedFn)(void *self, ...);
typedef struct {
  SLEngineUnsupportedFn CreateLEDDevice;
  SLEngineUnsupportedFn CreateVibraDevice;
  SLresult (*CreateAudioPlayer)(void *self, SLObjectItf *pPlayer, SLDataSource *src, SLDataSink *snk,
                                SLuint32 numIfaces, const SLInterfaceID *ids, const SLboolean *req);
  SLEngineUnsupportedFn CreateAudioRecorder;
  SLEngineUnsupportedFn CreateMidiPlayer;
  SLEngineUnsupportedFn CreateListener;
  SLEngineUnsupportedFn Create3DGroup;
  SLresult (*CreateOutputMix)(void *self, SLObjectItf *pMix, SLuint32 numIfaces, const SLInterfaceID *ids, const SLboolean *req);
  SLEngineUnsupportedFn CreateMetadataExtractor;
  SLEngineUnsupportedFn CreateExtensionObject;
  SLEngineUnsupportedFn QueryNumSupportedInterfaces;
  SLEngineUnsupportedFn QuerySupportedInterfaces;
  SLEngineUnsupportedFn QueryNumSupportedExtensions;
  SLEngineUnsupportedFn QuerySupportedExtension;
  SLEngineUnsupportedFn IsExtensionSupported;
} SLEngineItf_;

typedef struct {
  SLresult (*SetPlayState)(void *self, SLuint32 state);
  SLresult (*GetPlayState)(void *self, SLuint32 *pState);
  SLresult (*GetDuration)(void *self, SLuint32 *pMsec);
  SLresult (*GetPosition)(void *self, SLuint32 *pMsec);
  SLresult (*RegisterCallback)(void *self, void *cb, void *ctx);
  SLresult (*SetCallbackEventsMask)(void *self, SLuint32 mask);
  SLresult (*GetCallbackEventsMask)(void *self, SLuint32 *pMask);
  SLresult (*SetMarkerPosition)(void *self, SLuint32 m);
  SLresult (*ClearMarkerPosition)(void *self);
  SLresult (*GetMarkerPosition)(void *self, SLuint32 *p);
  SLresult (*SetPositionUpdatePeriod)(void *self, SLuint32 m);
  SLresult (*GetPositionUpdatePeriod)(void *self, SLuint32 *p);
} SLPlayItf_;

typedef struct {
  SLresult (*Enqueue)(void *self, const void *pBuffer, SLuint32 size);
  SLresult (*Clear)(void *self);
  SLresult (*GetState)(void *self, void *pState);
  SLresult (*RegisterCallback)(void *self, slBufferQueueCallback cb, void *ctx);
} SLBufferQueueItf_;

typedef struct {
  SLresult (*SetVolumeLevel)(void *self, SLmillibel level);
  SLresult (*GetVolumeLevel)(void *self, SLmillibel *p);
  SLresult (*GetMaxVolumeLevel)(void *self, SLmillibel *p);
  SLresult (*SetMute)(void *self, SLboolean mute);
  SLresult (*GetMute)(void *self, SLboolean *p);
  SLresult (*EnableStereoPosition)(void *self, SLboolean enable);
  SLresult (*IsEnabledStereoPosition)(void *self, SLboolean *p);
  SLresult (*SetStereoPosition)(void *self, SLint32 perMille);
  SLresult (*GetStereoPosition)(void *self, SLint32 *p);
} SLVolumeItf_;

// --- objects ----------------------------------------------------------------

#define MAX_PLAYERS 32
// Keep enough queued buffers to cover several output callbacks without rejecting
// normal streaming writes.
#define BQ_SLOTS 64

typedef struct {
  const void *data;
  SLuint32 size;
} BQBuffer;

typedef struct Player {
  const SLObjectItf_ *obj_vt;
  const SLPlayItf_   *play_vt;
  const SLBufferQueueItf_ *bq_vt;
  const SLVolumeItf_ *vol_vt;

  int in_use;
  int channels;
  int rate;
  int playing;
  float gain; // linear, from SetVolumeLevel (millibels)

  slBufferQueueCallback cb;
  void *cb_ctx;

  // FIFO of enqueued buffers
  BQBuffer q[BQ_SLOTS];
  int q_head, q_tail; // count = (tail - head + N) % N
  // currently draining buffer
  const uint8_t *cur;
  SLuint32 cur_size, cur_pos;

  SDL_mutex *lock;

} Player;

typedef struct {
  const SLObjectItf_ *obj_vt;
} OutputMix;

typedef struct {
  const SLObjectItf_ *obj_vt;
  const SLEngineItf_ *eng_vt;
} Engine;

#define CONTAINER(ptr, type, member) \
  ((type *)((char *)(ptr) - offsetof(type, member)))

// --- global Linux audio output + player registry ---------------------------

#define OUT_BUFS 6
#define OUT_FRAMES 1024
#define OUT_BYTES (OUT_FRAMES * 2 * sizeof(int16_t))

static int g_dev = 0;
static int g_dev_rate = 44100;
static SDL_AudioDeviceID g_audio_device = 0;
static Player *g_players[MAX_PLAYERS];
static int g_player_count = 0;
static SDL_mutex *g_reg_lock = NULL;

// Cocos2d-x SimpleAudioEngine normally crosses JNI into Android's
// Cocos2dxActivity. Keep decoded samples in the existing SDL mixer instead.
#define MAX_COCOS_SOUNDS 128
#define MAX_COCOS_EFFECTS 24
typedef struct {
  char path[256];
  int16_t *pcm;                 // interleaved S16 stereo at g_dev_rate
  uint32_t frames;
} CocosSound;
typedef struct {
  CocosSound *sound;
  float frame;
  unsigned int id;
  int loop;
  int playing;
  int paused;
  float left;
  float right;
  float volume;
  float rate;
  // SoundPool play() returns immediately even when load() is still pending.
  // Keep the requested path on the voice so the preload worker can attach the
  // decoded sample later without ever blocking the game/render thread.
  char pending_path[256];
} CocosVoice;
static CocosSound g_cocos_sounds[MAX_COCOS_SOUNDS];
static int g_cocos_sound_count = 0;
static CocosVoice g_cocos_bgm;
static CocosVoice g_cocos_effects[MAX_COCOS_EFFECTS];
static unsigned int g_cocos_next_id = 1;
static int g_cocos_effects_paused = 0;
static float g_cocos_bgm_volume = 1.0f;
static float g_cocos_effects_volume = 1.0f;

// Android SoundPool.load() schedules effect decoding asynchronously. Doing the
// full OGG decode/resample inside preloadEffect() made every scene serialize
// that CPU work on the render thread.
#define MAX_COCOS_PRELOADS MAX_COCOS_SOUNDS
typedef struct { char path[256]; int state; } CocosPreloadJob; // 0 free, 1 queued, 2 loading
static CocosPreloadJob g_cocos_preloads[MAX_COCOS_PRELOADS];
static SDL_mutex *g_cocos_preload_lock;
static SDL_cond *g_cocos_preload_cond;
static SDL_Thread *g_cocos_preload_thread;
static int g_cocos_preload_running;


static float mb_to_linear(SLmillibel mb) {
  if (mb <= -9600) return 0.0f;
  return powf(10.0f, (float)mb / 2000.0f); // 100 mB = 1 dB
}

// mix one playing player into the S16 stereo accumulator (int32 to avoid clip).
// cur/cur_pos are touched only by this (audio) thread, so they need no lock;
// only the buffer queue is shared with Enqueue. Critically, the engine's
// completion callback is fired WITHOUT our lock held -- Android's contract --
// otherwise the engine's mixer thread (holding its own mutex, calling Enqueue
// which wants our lock) deadlocks against us.
static void mix_player(Player *p, int32_t *acc, int frames) {
  if (!p->playing)
    return;

  float eng_factor = (config.engine_volume > 0) ? ((float)config.engine_volume / 100.0f) : 1.0f;
  const float g = p->gain * eng_factor;
  for (int i = 0; i < frames; i++) {
    if (!p->cur || p->cur_pos >= p->cur_size) {
      // current buffer finished: notify the engine so it enqueues the next
      if (p->cur) {
        p->cur = NULL;
        if (p->cb) p->cb(&p->bq_vt, p->cb_ctx);
      }
      // pop the next buffer (queue access is the only thing that needs the lock)
      SDL_LockMutex(p->lock);
      const int have = (p->q_head != p->q_tail);
      BQBuffer b = { NULL, 0 };
      if (have) {
        b = p->q[p->q_head];
        p->q_head = (p->q_head + 1) % BQ_SLOTS;
      }
      SDL_UnlockMutex(p->lock);
      if (!have) {
        break; // underrun: rest is silence
      }
      p->cur = b.data;
      p->cur_size = b.size;
      p->cur_pos = 0;
    }

    const int16_t *s = (const int16_t *)(p->cur + p->cur_pos);
    int32_t l, r;
    if (p->channels >= 2) {
      l = s[0]; r = s[1];
      p->cur_pos += 4;
    } else {
      l = r = s[0];
      p->cur_pos += 2;
    }
    acc[i * 2 + 0] += (int32_t)(l * g);
    acc[i * 2 + 1] += (int32_t)(r * g);
  }
}

static void mix_cocos_voice(CocosVoice *voice, int32_t *acc, int frames, float volume) {
  if (!voice->playing || voice->paused || !voice->sound || !voice->sound->pcm || !voice->sound->frames)
    return;
  for (int i = 0; i < frames; i++) {
    if (voice->frame >= (float)voice->sound->frames) {
      if (!voice->loop) { voice->playing = 0; break; }
      voice->frame = fmodf(voice->frame, (float)voice->sound->frames);
    }
    const int16_t *s = voice->sound->pcm + (size_t)voice->frame * 2;
    acc[i * 2] += (int32_t)(s[0] * voice->left * voice->volume * volume);
    acc[i * 2 + 1] += (int32_t)(s[1] * voice->right * voice->volume * volume);
    voice->frame += voice->rate;
  }
}

static void mix_cocos_audio(int32_t *acc, int frames) {
  float bgm_factor = (config.music_volume >= 0) ? ((float)config.music_volume / 100.0f) : 0.35f;
  float sfx_factor = (config.effects_volume >= 0) ? ((float)config.effects_volume / 100.0f) : 0.50f;
  float eng_factor = (config.engine_volume >= 0) ? ((float)config.engine_volume / 100.0f) : 1.00f;
  mix_cocos_voice(&g_cocos_bgm, acc, frames, g_cocos_bgm_volume * bgm_factor);
  if (g_cocos_effects_paused)
    return;
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++) {
    CocosVoice *v = &g_cocos_effects[i];
    int is_engine = v->loop || (v->sound && (strstr(v->sound->path, "engine") ||
                                            strstr(v->sound->path, "motor") ||
                                            strstr(v->sound->path, "car") ||
                                            strstr(v->sound->path, "jeep") ||
                                            strstr(v->sound->path, "bike") ||
                                            strstr(v->sound->path, "truck") ||
                                            strstr(v->sound->path, "tank") ||
                                            strstr(v->sound->path, "tractor") ||
                                            strstr(v->sound->path, "vehicle")));
    float factor = is_engine ? eng_factor : sfx_factor;
    mix_cocos_voice(v, acc, frames, g_cocos_effects_volume * factor);
  }
}

static void SDLCALL audio_callback(void *ud, Uint8 *stream, int len) {
  (void)ud;

  const int frames = len / 4; // S16 stereo
  static int32_t acc[8192 * 2];
  if (frames > 8192) { memset(stream, 0, len); return; }
  memset(acc, 0, frames * 2 * sizeof(int32_t));

  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < g_player_count; i++)
    if (g_players[i] && g_players[i]->in_use)
      mix_player(g_players[i], acc, frames);
  mix_cocos_audio(acc, frames);
  SDL_UnlockMutex(g_reg_lock);


  int16_t *out = (int16_t *)stream;
  for (int i = 0; i < frames * 2; i++) {
    int32_t v = acc[i];
    if (v > 32767) v = 32767;
    else if (v < -32768) v = -32768;
    out[i] = (int16_t)v;
  }

}

void opensles_pump(void) {
  // Automatically driven by SDL audio callback
}

static void ensure_device(int rate) {
  if (!g_reg_lock)
    g_reg_lock = SDL_CreateMutex();
  if (g_dev)
    return;

  SDL_AudioSpec wanted, obtained;
  memset(&wanted, 0, sizeof(wanted));
  wanted.freq = rate > 0 ? rate : 44100;
  wanted.format = AUDIO_S16SYS;
  wanted.channels = 2;
  wanted.samples = OUT_FRAMES;
  wanted.callback = audio_callback;
  wanted.userdata = NULL;

  const char *driver_audio = SDL_GetCurrentAudioDriver();
  printf("[AUDIO] Inicializando SDL Audio (driver: '%s', frequencia solicitada: %d Hz)...\n",
         driver_audio ? driver_audio : "padrao", wanted.freq);
  fflush(stdout);

  g_audio_device = SDL_OpenAudioDevice(NULL, 0, &wanted, &obtained, 0);
  if (g_audio_device > 0) {
    g_dev_rate = obtained.freq;
    g_dev = 1;
    SDL_PauseAudioDevice(g_audio_device, 0);
    printf("[AUDIO] SDL_OpenAudioDevice SUCESSO: ID=%d, Freq=%d Hz, Canais=%d, Amostras=%d\n",
           g_audio_device, obtained.freq, obtained.channels, obtained.samples);
  } else {
    printf("[AUDIO] SDL_OpenAudioDevice falhou (%s). Tentando SDL_OpenAudio legado...\n", SDL_GetError());
    if (SDL_OpenAudio(&wanted, &obtained) >= 0) {
      g_dev_rate = obtained.freq;
      g_dev = 1;
      SDL_PauseAudio(0);
      printf("[AUDIO] SDL_OpenAudio legado SUCESSO: Freq=%d Hz\n", obtained.freq);
    } else {
      printf("[AUDIO ERRO] Falha total ao abrir dispositivo de audio: %s\n", SDL_GetError());
    }
  }
  fflush(stdout);
}

void opensles_init(void) {
  ensure_device(44100);
}

static CocosSound *cocos_find_sound(const char *path) {
  for (int i = 0; i < g_cocos_sound_count; i++)
    if (!strcmp(g_cocos_sounds[i].path, path))
      return &g_cocos_sounds[i];
  return NULL;
}

static CocosSound *cocos_load_sound(const char *path) {
  if (!path || !path[0])
    return NULL;
  ensure_device(44100);
  if (!g_reg_lock)
    return NULL;

  SDL_LockMutex(g_reg_lock);
  CocosSound *cached = cocos_find_sound(path);
  SDL_UnlockMutex(g_reg_lock);
  if (cached)
    return cached;

  AAsset *asset = AAssetManager_open_fake(asset_manager(), path, 0);
  int64_t size = AAsset_getLength64_fake(asset);
  if (!asset || size <= 0 || size > 64 * 1024 * 1024) {
    if (asset) AAsset_close_fake(asset);
    return NULL;
  }
  uint8_t *encoded = malloc((size_t)size);
  if (!encoded || AAsset_read_fake(asset, encoded, (size_t)size) != size) {
    AAsset_close_fake(asset);
    free(encoded);
    return NULL;
  }
  AAsset_close_fake(asset);

  int channels = 0, rate = 0;
  short *decoded = NULL;
  int raw_frames = stb_vorbis_decode_memory(encoded, (int)size, &channels, &rate, &decoded);
  free(encoded);
  if (raw_frames <= 0 || !decoded || channels <= 0 || rate <= 0) {
    free(decoded);
    return NULL;
  }

  uint64_t output_frames = ((uint64_t)raw_frames * (uint64_t)g_dev_rate) / (uint64_t)rate;
  if (!output_frames || output_frames > 16 * 1024 * 1024) {
    free(decoded);
    return NULL;
  }
  int16_t *pcm = malloc((size_t)output_frames * 2 * sizeof(*pcm));
  if (!pcm) {
    free(decoded);
    return NULL;
  }
  for (uint64_t i = 0; i < output_frames; i++) {
    uint64_t source = (i * (uint64_t)rate) / (uint64_t)g_dev_rate;
    if (source >= (uint64_t)raw_frames) source = (uint64_t)raw_frames - 1;
    pcm[i * 2] = decoded[source * (uint64_t)channels];
    pcm[i * 2 + 1] = decoded[source * (uint64_t)channels + (channels > 1 ? 1 : 0)];
  }
  free(decoded);

  SDL_LockMutex(g_reg_lock);
  cached = cocos_find_sound(path);
  if (cached) {
    SDL_UnlockMutex(g_reg_lock);
    free(pcm);
    return cached;
  }
  if (g_cocos_sound_count == MAX_COCOS_SOUNDS) {
    SDL_UnlockMutex(g_reg_lock);
    free(pcm);
    return NULL;
  }
  CocosSound *sound = &g_cocos_sounds[g_cocos_sound_count++];
  snprintf(sound->path, sizeof(sound->path), "%s", path);
  sound->pcm = pcm;
  sound->frames = (uint32_t)output_frames;
  SDL_UnlockMutex(g_reg_lock);
  return sound;
}

static int cocos_preload_find_locked(const char *path) {
  for (int i = 0; i < MAX_COCOS_PRELOADS; ++i)
    if (g_cocos_preloads[i].state && !strcmp(g_cocos_preloads[i].path, path)) return i;
  return -1;
}

static void cocos_attach_pending_sound(const char *path, CocosSound *loaded) {
  if (!path || !loaded || !g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; ++i) {
    CocosVoice *voice = &g_cocos_effects[i];
    if (voice->playing && voice->pending_path[0] &&
        !strcmp(voice->pending_path, path)) {
      voice->sound = loaded;
      voice->frame = 0.0f;
      voice->pending_path[0] = 0;
    }
  }
  if (g_cocos_bgm.playing && g_cocos_bgm.pending_path[0] &&
      !strcmp(g_cocos_bgm.pending_path, path)) {
    g_cocos_bgm.sound = loaded;
    g_cocos_bgm.frame = 0.0f;
    g_cocos_bgm.pending_path[0] = 0;
  }
  SDL_UnlockMutex(g_reg_lock);
}

static int cocos_preload_worker(void *unused) {
  (void)unused;
  for (;;) {
    char path[256] = {0};
    int slot = -1;
    SDL_LockMutex(g_cocos_preload_lock);
    while (g_cocos_preload_running) {
      for (int i = 0; i < MAX_COCOS_PRELOADS; ++i) {
        if (g_cocos_preloads[i].state == 1) { slot = i; break; }
      }
      if (slot >= 0) break;
      SDL_CondWait(g_cocos_preload_cond, g_cocos_preload_lock);
    }
    if (!g_cocos_preload_running) {
      SDL_UnlockMutex(g_cocos_preload_lock);
      break;
    }
    g_cocos_preloads[slot].state = 2;
    snprintf(path, sizeof(path), "%s", g_cocos_preloads[slot].path);
    SDL_UnlockMutex(g_cocos_preload_lock);

    CocosSound *loaded = cocos_load_sound(path);

    // Attach the newly decoded sample to any play() calls that arrived while
    // SoundPool-style loading was in progress. The voice ID/volume/rate were
    // already returned/updated on the game thread, so playback can begin here
    // with no synchronous wait in cocos_audio_play_effect().
    cocos_attach_pending_sound(path, loaded);

    SDL_LockMutex(g_cocos_preload_lock);
    if (slot >= 0 && g_cocos_preloads[slot].state == 2 &&
        !strcmp(g_cocos_preloads[slot].path, path)) {
      g_cocos_preloads[slot].state = 0;
      g_cocos_preloads[slot].path[0] = 0;
    }
    SDL_CondBroadcast(g_cocos_preload_cond);
    SDL_UnlockMutex(g_cocos_preload_lock);
  }
  return 0;
}

static int cocos_preload_start(void) {
  if (g_cocos_preload_thread) return 1;
  if (!g_cocos_preload_lock) g_cocos_preload_lock = SDL_CreateMutex();
  if (!g_cocos_preload_cond) g_cocos_preload_cond = SDL_CreateCond();
  if (!g_cocos_preload_lock || !g_cocos_preload_cond) return 0;
  g_cocos_preload_running = 1;
  g_cocos_preload_thread = SDL_CreateThread(cocos_preload_worker, "hcr-audio-preload", NULL);
  if (!g_cocos_preload_thread) {
    g_cocos_preload_running = 0;
    return 0;
  }
  return 1;
}

void cocos_audio_preload(const char *path) {
  if (!path || !path[0]) return;
  ensure_device(44100);
  if (!g_dev || !g_reg_lock) return;

  SDL_LockMutex(g_reg_lock);
  const int cached = cocos_find_sound(path) != NULL;
  SDL_UnlockMutex(g_reg_lock);
  if (cached) return;

  if (!cocos_preload_start()) {
    CocosSound *loaded = cocos_load_sound(path);
    cocos_attach_pending_sound(path, loaded);
    return;
  }
  SDL_LockMutex(g_cocos_preload_lock);
  if (cocos_preload_find_locked(path) < 0) {
    for (int i = 0; i < MAX_COCOS_PRELOADS; ++i) {
      if (!g_cocos_preloads[i].state) {
        snprintf(g_cocos_preloads[i].path, sizeof(g_cocos_preloads[i].path), "%s", path);
        g_cocos_preloads[i].state = 1;
        SDL_CondSignal(g_cocos_preload_cond);
        break;
      }
    }
  }
  SDL_UnlockMutex(g_cocos_preload_lock);
}

int cocos_audio_play_effect(const char *path, int loop, float left, float right) {
  if (!path || !path[0]) return 0;
  printf("[AUDIO EFFECT] Solicitado efeito: '%s' (loop=%d, L=%.2f, R=%.2f)\n", path, loop, left, right);
  fflush(stdout);
  ensure_device(44100);
  if (!g_dev || !g_reg_lock) return 0;

  // Android SoundPool.play() does not block the caller waiting for load().
  // Reserve the effect ID immediately. If the sample is not cached yet, the
  // preload worker will attach it to this voice once decoding completes.
  SDL_LockMutex(g_reg_lock);
  CocosSound *sound = cocos_find_sound(path);
  int slot = 0;
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++)
    if (!g_cocos_effects[i].playing) { slot = i; break; }
  CocosVoice *voice = &g_cocos_effects[slot];
  memset(voice, 0, sizeof(*voice));
  voice->sound = sound;
  voice->frame = 0.0f;
  voice->id = g_cocos_next_id++;
  if (!voice->id) voice->id = g_cocos_next_id++;
  voice->loop = loop != 0;
  voice->playing = 1;
  voice->paused = 0;
  voice->left = left;
  voice->right = right;
  // Persistent vehicle sounds (engine, turbo/booster, supercharger, etc.) are
  // started while a level is still loading. The game sends their real volume
  // through setEffectVolume once simulation is ready, so every loop must begin
  // silent. One-shot UI/gameplay effects still begin at normal gain.
  voice->volume = loop ? 0.0f : 1.0f;
  voice->rate = 1.0f;
  if (!sound) snprintf(voice->pending_path, sizeof(voice->pending_path), "%s", path);
  const unsigned int id = voice->id;
  SDL_UnlockMutex(g_reg_lock);

  if (!sound) {
    cocos_audio_preload(path);
    SDL_LockMutex(g_reg_lock);
    CocosSound *now_cached = cocos_find_sound(path);
    SDL_UnlockMutex(g_reg_lock);
    cocos_attach_pending_sound(path, now_cached);
  }
  return (int)id;
}

void cocos_audio_stop_effect(int id) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++)
    if (g_cocos_effects[i].id == (unsigned int)id) {
      g_cocos_effects[i].playing = g_cocos_effects[i].paused = 0;
      g_cocos_effects[i].pending_path[0] = 0;
    }
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_pause_effect(int id, int paused) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++)
    if (g_cocos_effects[i].id == (unsigned int)id && g_cocos_effects[i].playing)
      g_cocos_effects[i].paused = paused != 0;
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_stop_all_effects(void) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++) {
    g_cocos_effects[i].playing = g_cocos_effects[i].paused = 0;
    g_cocos_effects[i].pending_path[0] = 0;
  }
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_pause_all_effects(int paused) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  g_cocos_effects_paused = paused != 0;
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_set_effects_volume(float volume) {
  g_cocos_effects_volume = volume;
  printf("[AUDIO] Master effects volume ajustado para: %.2f\n", volume);
  fflush(stdout);
}

void cocos_audio_set_effect_volume(int id, float volume) {
  if (!g_reg_lock) return;
  static int s_logged = 0;
  if (s_logged < 4) {
    printf("[AUDIO] set_effect_volume: ID=%d, vol=%.2f\n", id, volume);
    fflush(stdout);
    s_logged++;
  }
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++)
    if (g_cocos_effects[i].id == (unsigned int)id)
      g_cocos_effects[i].volume = volume;
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_set_effect_rate(int id, float rate) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < MAX_COCOS_EFFECTS; i++)
    if (g_cocos_effects[i].id == (unsigned int)id)
      g_cocos_effects[i].rate = rate < 0.5f ? 0.5f : (rate > 2.0f ? 2.0f : rate);
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_play_background(const char *path, int loop) {
  if (!path || !path[0]) return;
  printf("[AUDIO BGM] Solicitada musica de fundo: '%s' (loop=%d)\n", path, loop);
  fflush(stdout);
  ensure_device(44100);
  if (!g_dev || !g_reg_lock) return;

  /* Android's MediaPlayer preparation is asynchronous. Start a pending voice
   * immediately and let the preload worker attach the decoded sample. */
  SDL_LockMutex(g_reg_lock);
  CocosSound *sound = cocos_find_sound(path);
  memset(&g_cocos_bgm, 0, sizeof(g_cocos_bgm));
  g_cocos_bgm.sound = sound;
  g_cocos_bgm.frame = 0;
  g_cocos_bgm.loop = loop != 0;
  g_cocos_bgm.playing = 1;
  g_cocos_bgm.paused = 0;
  g_cocos_bgm.left = g_cocos_bgm.right = 1.0f;
  g_cocos_bgm.volume = g_cocos_bgm.rate = 1.0f;
  if (!sound) snprintf(g_cocos_bgm.pending_path, sizeof(g_cocos_bgm.pending_path), "%s", path);
  SDL_UnlockMutex(g_reg_lock);

  if (!sound) {
    cocos_audio_preload(path);
    SDL_LockMutex(g_reg_lock);
    CocosSound *now_cached = cocos_find_sound(path);
    SDL_UnlockMutex(g_reg_lock);
    cocos_attach_pending_sound(path, now_cached);
  }
}

void cocos_audio_stop_background(void) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  g_cocos_bgm.playing = g_cocos_bgm.paused = 0;
  g_cocos_bgm.frame = 0;
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_pause_background(int paused) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  if (g_cocos_bgm.playing) g_cocos_bgm.paused = paused != 0;
  SDL_UnlockMutex(g_reg_lock);
}

void cocos_audio_rewind_background(void) {
  if (!g_reg_lock) return;
  SDL_LockMutex(g_reg_lock);
  g_cocos_bgm.frame = 0;
  SDL_UnlockMutex(g_reg_lock);
}

int cocos_audio_background_playing(void) {
  return g_cocos_bgm.playing && !g_cocos_bgm.paused;
}

void cocos_audio_set_background_volume(float volume) { g_cocos_bgm_volume = volume; }
float cocos_audio_background_volume(void) { return g_cocos_bgm_volume; }
float cocos_audio_effects_volume(void) { return g_cocos_effects_volume; }

// --- buffer queue interface -------------------------------------------------

static SLresult bq_Enqueue(void *self, const void *pBuffer, SLuint32 size) {
  Player *p = CONTAINER(self, Player, bq_vt);
  SDL_LockMutex(p->lock);
  const int next = (p->q_tail + 1) % BQ_SLOTS;
  if (next == p->q_head) { // full
    SDL_UnlockMutex(p->lock);
    return SL_RESULT_PARAMETER_INVALID;
  }
  p->q[p->q_tail].data = pBuffer;
  p->q[p->q_tail].size = size;
  p->q_tail = next;
  SDL_UnlockMutex(p->lock);
  return SL_RESULT_SUCCESS;
}

static SLresult bq_Clear(void *self) {
  Player *p = CONTAINER(self, Player, bq_vt);
  SDL_LockMutex(p->lock);
  p->q_head = p->q_tail = 0;
  p->cur = NULL;
  p->cur_pos = p->cur_size = 0;
  SDL_UnlockMutex(p->lock);
  return SL_RESULT_SUCCESS;
}

typedef struct { SLuint32 count; SLuint32 index; } SLBufferQueueState;

static SLresult bq_GetState(void *self, void *pState) {
  Player *p = CONTAINER(self, Player, bq_vt);
  if (pState) {
    SLBufferQueueState *st = pState;
    SDL_LockMutex(p->lock);
    st->count = (p->q_tail - p->q_head + BQ_SLOTS) % BQ_SLOTS + (p->cur ? 1 : 0);
    st->index = 0;
    SDL_UnlockMutex(p->lock);
  }
  return SL_RESULT_SUCCESS;
}

static SLresult bq_RegisterCallback(void *self, slBufferQueueCallback cb, void *ctx) {
  Player *p = CONTAINER(self, Player, bq_vt);
  p->cb = cb;
  p->cb_ctx = ctx;
  return SL_RESULT_SUCCESS;
}

static const SLBufferQueueItf_ bq_vtable = {
  bq_Enqueue, bq_Clear, bq_GetState, bq_RegisterCallback,
};

// --- play interface ---------------------------------------------------------

static SLresult play_SetPlayState(void *self, SLuint32 state) {
  Player *p = CONTAINER(self, Player, play_vt);
  // CRIWARE streams the master mix continuously and, in this build, only ever
  // calls SetPlayState(STOPPED) through our Play interface -- never PLAYING.
  // Honoring STOP kills the drain (queue fills -> Enqueue returns 13 -> silence).
  // Keep the player draining always: enqueued buffers play and the completion
  // callback fires so CRIWARE recycles buffers. (Draining an vazio queue just
  // emits silence, so this is safe when nothing is actually playing.)
  SDL_LockMutex(p->lock);
  p->playing = 1;
  SDL_UnlockMutex(p->lock);
  (void)state;
  return SL_RESULT_SUCCESS;
}
static SLresult play_GetPlayState(void *self, SLuint32 *pState) {
  Player *p = CONTAINER(self, Player, play_vt);
  if (pState) *pState = p->playing ? SL_PLAYSTATE_PLAYING : SL_PLAYSTATE_STOPPED;
  return SL_RESULT_SUCCESS;
}
static SLresult play_ret0_u32(void *self, SLuint32 *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult play_ok_u32(void *self, SLuint32 v) { (void)self; (void)v; return SL_RESULT_SUCCESS; }
static SLresult play_ok(void *self) { (void)self; return SL_RESULT_SUCCESS; }
static SLresult play_RegisterCallback(void *self, void *cb, void *ctx) { (void)self; (void)cb; (void)ctx; return SL_RESULT_SUCCESS; }

static const SLPlayItf_ play_vtable = {
  play_SetPlayState, play_GetPlayState, play_ret0_u32, play_ret0_u32,
  play_RegisterCallback, play_ok_u32, play_ret0_u32, play_ok_u32,
  play_ok, play_ret0_u32, play_ok_u32, play_ret0_u32,
};

// --- volume interface -------------------------------------------------------

static SLresult vol_SetVolumeLevel(void *self, SLmillibel level) {
  Player *p = CONTAINER(self, Player, vol_vt);
  p->gain = mb_to_linear(level);
  return SL_RESULT_SUCCESS;
}
static SLresult vol_GetVolumeLevel(void *self, SLmillibel *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult vol_GetMaxVolumeLevel(void *self, SLmillibel *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult vol_SetMute(void *self, SLboolean m) {
  Player *p = CONTAINER(self, Player, vol_vt);
  if (m) p->gain = 0.0f;
  return SL_RESULT_SUCCESS;
}
static SLresult vol_GetMute(void *self, SLboolean *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult vol_enable(void *self, SLboolean e) { (void)self; (void)e; return SL_RESULT_SUCCESS; }
static SLresult vol_isenabled(void *self, SLboolean *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult vol_setpos(void *self, SLint32 v) { (void)self; (void)v; return SL_RESULT_SUCCESS; }
static SLresult vol_getpos(void *self, SLint32 *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }

static const SLVolumeItf_ vol_vtable = {
  vol_SetVolumeLevel, vol_GetVolumeLevel, vol_GetMaxVolumeLevel, vol_SetMute,
  vol_GetMute, vol_enable, vol_isenabled, vol_setpos, vol_getpos,
};

// --- player object ----------------------------------------------------------

static SLresult player_GetInterface(void *self, const SLInterfaceID iid, void *pInterface);
static void player_Destroy(void *self);

static SLresult obj_Realize(void *self, SLboolean async) { (void)self; (void)async; return SL_RESULT_SUCCESS; }
static SLresult obj_Resume(void *self, SLboolean async) { (void)self; (void)async; return SL_RESULT_SUCCESS; }
static SLresult obj_GetState(void *self, SLuint32 *pState) { (void)self; if (pState) *pState = SL_OBJECT_STATE_REALIZED; return SL_RESULT_SUCCESS; }
static SLresult obj_RegisterCallback(void *self, void *cb, void *ctx) { (void)self; (void)cb; (void)ctx; return SL_RESULT_SUCCESS; }
static SLresult obj_Abort(void *self) { (void)self; return SL_RESULT_SUCCESS; }
static SLresult obj_SetPriority(void *self, SLint32 a, SLboolean b) { (void)self; (void)a; (void)b; return SL_RESULT_SUCCESS; }
static SLresult obj_GetPriority(void *self, SLint32 *p) { (void)self; if (p) *p = 0; return SL_RESULT_SUCCESS; }
static SLresult obj_SetLOC(void *self, SLint32 a, SLInterfaceID *b, SLboolean c) { (void)self; (void)a; (void)b; (void)c; return SL_RESULT_SUCCESS; }

static SLresult mix_GetInterface(void *self, const SLInterfaceID iid, void *pInterface) {
  (void)self; (void)iid;
  if (pInterface) *(void **)pInterface = NULL;
  return SL_RESULT_FEATURE_UNSUPPORTED;
}
static void simple_Destroy(void *self) { free(self); }

static const SLObjectItf_ player_obj_vtable = {
  obj_Realize, obj_Resume, obj_GetState, player_GetInterface, obj_RegisterCallback,
  obj_Abort, player_Destroy, obj_SetPriority, obj_GetPriority, obj_SetLOC,
};
static const SLObjectItf_ mix_obj_vtable = {
  obj_Realize, obj_Resume, obj_GetState, mix_GetInterface, obj_RegisterCallback,
  obj_Abort, simple_Destroy, obj_SetPriority, obj_GetPriority, obj_SetLOC,
};

static SLresult player_GetInterface(void *self, const SLInterfaceID iid, void *pInterface) {
  Player *p = CONTAINER(self, Player, obj_vt);
  if (!pInterface)
    return SL_RESULT_PARAMETER_INVALID;
  if (iid == SL_IID_PLAY) {
    *(void **)pInterface = &p->play_vt;
  } else if (iid == SL_IID_BUFFERQUEUE || iid == SL_IID_ANDROIDSIMPLEBUFFERQUEUE) {
    *(void **)pInterface = &p->bq_vt;
  } else if (iid == SL_IID_VOLUME) {
    *(void **)pInterface = &p->vol_vt;
  } else {
    *(void **)pInterface = NULL;
    return SL_RESULT_FEATURE_UNSUPPORTED;
  }
  return SL_RESULT_SUCCESS;
}

static void player_Destroy(void *self) {
  Player *p = CONTAINER(self, Player, obj_vt);
  SDL_LockMutex(g_reg_lock);
  for (int i = 0; i < g_player_count; i++)
    if (g_players[i] == p) g_players[i] = NULL;
  SDL_UnlockMutex(g_reg_lock);
  if (p->lock) SDL_DestroyMutex(p->lock);
  free(p);
}

// --- engine interface -------------------------------------------------------

static SLresult eng_CreateAudioPlayer(void *self, SLObjectItf *pPlayer, SLDataSource *src, SLDataSink *snk,
                                      SLuint32 numIfaces, const SLInterfaceID *ids, const SLboolean *req) {
  (void)self; (void)snk; (void)numIfaces; (void)ids; (void)req;
  if (!pPlayer)
    return SL_RESULT_PARAMETER_INVALID;

  Player *p = calloc(1, sizeof(*p));
  if (!p)
    return SL_RESULT_PARAMETER_INVALID;
  p->obj_vt = &player_obj_vtable;
  p->play_vt = &play_vtable;
  p->bq_vt = &bq_vtable;
  p->vol_vt = &vol_vtable;
  p->in_use = 1;
  p->gain = 1.0f;
  p->channels = 2;
  p->rate = 44100;
  // Drain from creation: CRIWARE fills the buffer queue but (in this build)
  // never calls SetPlayState(PLAYING) through our Play interface, so without
  // this the queue fills and every Enqueue returns 13. Draining lets the
  // completion callback fire so CRIWARE recycles buffers normally.
  p->playing = 1;
  p->lock = SDL_CreateMutex();

  if (src && src->pFormat) {
    const SLDataFormat_PCM *fmt = src->pFormat;
    if (fmt->formatType == 2 /* SL_DATAFORMAT_PCM */) {
      p->channels = fmt->numChannels ? (int)fmt->numChannels : 2;
      p->rate = fmt->samplesPerSec ? (int)(fmt->samplesPerSec / 1000) : 44100;
    }
  }
  // CRIWARE requests a 44100 player but its master mix actually outputs ~32000 Hz
  // (measured via the enqueue rate) -> playing at 44100 is fast-forward. Override
  // the device rate to the real content rate (config.audio_rate, padrao 32000).
  if (config.audio_rate > 0)
    p->rate = config.audio_rate;

  printf("[OPENSLES] CreateAudioPlayer instanciado (rate=%d, channels=%d)\n", p->rate, p->channels);
  fflush(stdout);
  ensure_device(p->rate);

  SDL_LockMutex(g_reg_lock);
  int slot = -1;
  for (int i = 0; i < g_player_count; i++)
    if (g_players[i] == NULL) { slot = i; break; }
  if (slot < 0 && g_player_count < MAX_PLAYERS)
    slot = g_player_count++;
  if (slot >= 0)
    g_players[slot] = p;
  SDL_UnlockMutex(g_reg_lock);

  *pPlayer = &p->obj_vt;
  return SL_RESULT_SUCCESS;
}

static SLresult eng_CreateOutputMix(void *self, SLObjectItf *pMix, SLuint32 numIfaces,
                                    const SLInterfaceID *ids, const SLboolean *req) {
  (void)self; (void)numIfaces; (void)ids; (void)req;
  OutputMix *m = calloc(1, sizeof(*m));
  if (!m)
    return SL_RESULT_PARAMETER_INVALID;
  m->obj_vt = &mix_obj_vtable;
  if (pMix) *pMix = &m->obj_vt;
  return SL_RESULT_SUCCESS;
}

static SLresult eng_unsupported(void *self, ...) {
  (void)self;
  return SL_RESULT_FEATURE_UNSUPPORTED;
}

static const SLEngineItf_ engine_vtable = {
  .CreateLEDDevice = eng_unsupported,
  .CreateVibraDevice = eng_unsupported,
  .CreateAudioPlayer = eng_CreateAudioPlayer,
  .CreateAudioRecorder = eng_unsupported,
  .CreateMidiPlayer = eng_unsupported,
  .CreateListener = eng_unsupported,
  .Create3DGroup = eng_unsupported,
  .CreateOutputMix = eng_CreateOutputMix,
  .CreateMetadataExtractor = eng_unsupported,
  .CreateExtensionObject = eng_unsupported,
  .QueryNumSupportedInterfaces = eng_unsupported,
  .QuerySupportedInterfaces = eng_unsupported,
  .QueryNumSupportedExtensions = eng_unsupported,
  .QuerySupportedExtension = eng_unsupported,
  .IsExtensionSupported = eng_unsupported,
};

static SLresult engine_GetInterface(void *self, const SLInterfaceID iid, void *pInterface) {
  Engine *e = CONTAINER(self, Engine, obj_vt);
  if (!pInterface)
    return SL_RESULT_PARAMETER_INVALID;
  if (iid == SL_IID_ENGINE) {
    *(void **)pInterface = &e->eng_vt;
    return SL_RESULT_SUCCESS;
  }
  *(void **)pInterface = NULL;
  return SL_RESULT_FEATURE_UNSUPPORTED;
}

static const SLObjectItf_ engine_obj_vtable = {
  obj_Realize, obj_Resume, obj_GetState, engine_GetInterface, obj_RegisterCallback,
  obj_Abort, simple_Destroy, obj_SetPriority, obj_GetPriority, obj_SetLOC,
};

// --- entry point ------------------------------------------------------------

uint32_t slCreateEngine(void **pEngine, uint32_t numOptions, const void *pEngineOptions,
                        uint32_t numInterfaces, const void *pInterfaceIds,
                        const void *pInterfaceRequired) {
  (void)numOptions; (void)pEngineOptions; (void)numInterfaces;
  (void)pInterfaceIds; (void)pInterfaceRequired;
  printf("[OPENSLES] slCreateEngine invocado pelo Cocos2d-x\n");
  fflush(stdout);
  if (!g_reg_lock)
    g_reg_lock = SDL_CreateMutex();
  if (!pEngine)
    return SL_RESULT_PARAMETER_INVALID;
  Engine *e = calloc(1, sizeof(*e));
  if (!e)
    return SL_RESULT_PARAMETER_INVALID;
  e->obj_vt = &engine_obj_vtable;
  e->eng_vt = &engine_vtable;
  *pEngine = &e->obj_vt;
  return SL_RESULT_SUCCESS;
}

void opensles_shutdown(void) {
  if (g_cocos_preload_thread && g_cocos_preload_lock) {
    SDL_LockMutex(g_cocos_preload_lock);
    g_cocos_preload_running = 0;
    SDL_CondBroadcast(g_cocos_preload_cond);
    SDL_UnlockMutex(g_cocos_preload_lock);
    SDL_WaitThread(g_cocos_preload_thread, NULL);
    g_cocos_preload_thread = NULL;
  }
  memset(g_cocos_preloads, 0, sizeof(g_cocos_preloads));
  if (g_cocos_preload_cond) {
    SDL_DestroyCond(g_cocos_preload_cond);
    g_cocos_preload_cond = NULL;
  }
  if (g_cocos_preload_lock) {
    SDL_DestroyMutex(g_cocos_preload_lock);
    g_cocos_preload_lock = NULL;
  }
  if (g_dev) {
    if (g_audio_device > 0) {
      SDL_CloseAudioDevice(g_audio_device);
      g_audio_device = 0;
    } else {
      SDL_CloseAudio();
    }
    g_dev = 0;
  }
  for (int i = 0; i < g_cocos_sound_count; i++) {
    free(g_cocos_sounds[i].pcm);
    g_cocos_sounds[i].pcm = NULL;
  }
  g_cocos_sound_count = 0;
  memset(&g_cocos_bgm, 0, sizeof(g_cocos_bgm));
  memset(g_cocos_effects, 0, sizeof(g_cocos_effects));
}
