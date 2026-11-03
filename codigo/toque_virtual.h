#ifndef TOQUE_VIRTUAL_H
#define TOQUE_VIRTUAL_H

#ifdef __cplusplus
extern "C" {
#endif

// Posicao e transparencia do cursor virtual
extern float g_cursor_x;
extern float g_cursor_y;
extern float g_cursor_alpha;

// Inicializa estruturas de ponteiros de toque JNI
void toque_virtual_init(void);

// Define posicao absoluta do cursor (ex: via mouse/touchpad)
void toque_virtual_definir_posicao(float x, float y);

// Aciona pulso de toque virtual no botao de Pause (HUD superior direito)
void toque_virtual_acionar_pause(void);

// Processa deslocamento relativo do cursor e despacha eventos de toque
void toque_virtual_processar(float dx, float dy, int clique, int freio, int gas);

#ifdef __cplusplus
}
#endif

#endif // TOQUE_VIRTUAL_H

