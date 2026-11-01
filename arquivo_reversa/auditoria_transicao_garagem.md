# Relatório de Auditoria Técnica: Transição de Tela de AnimatedSplash para GarageScene

**Data**: 29/10/2026  
**Módulo Alvo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Classe**: `AnimatedSplash` (RTTI `14AnimatedSplash` em `0x011F8070`)  
**Função Auditada**: `AnimatedSplash::update(float dt)` (`0x00A1D600` a `0x00A1DC00`)  

---

## 1. Causa Raiz do Congelamento em 50% de Loading

A classe `AnimatedSplash` gerencia a tela de introdução e o layout `assetloader.csb` (`backgrounds/desertX.png` + `loadingBar`).  
A cada quadro, o método `AnimatedSplash::update(float dt)` verifica se o carregamento de assets atingiu o limiar para transicionar para a garagem (`GarageScene`):

```arm64
0x0a1d820: adrp x9, #0x1838000
0x0a1d824: add  x9, x9, #0x7b0
0x0a1d828: ldp  x10, x9, [x9]           ; x9 = ponteiro final, x10 = ponteiro inicial do std::vector de assets
0x0a1d82c: sub  x9, x9, x10             ; x9 = tamanho em bytes do vetor
0x0a1d830: cmp  x8, x9, asr #3          ; x8 = [x19, #0x380] (contador de assets carregados) vs total de assets
0x0a1d834: b.lo #0xa1db20               ; SE x8 < total, SALTA FORA PARA O FINAL DO LOOP (NÃO ENTRA NA GARAGEM!)
0x0a1d838: ldrb w8, [x19, #0x384]       ; Checa se a transição já foi iniciada
0x0a1d83c: cbnz w8, #0xa1db20           ; Se já iniciada, salta fora
0x0a1d840: mov  w8, #1                  ; Marca transição iniciada
0x0a1d844: strb w8, [x19, #0x384]       ; Salva flag
0x0a1d848: bl   #0x52fd8c               ; Inicializa Scene Builder
0x0a1d858: mov  w10, #0xc
0x0a1d85c: mov  w11, #0x6567            ; ASCII 'ge'
0x0a1d860: mov  w12, #0x61720000        ; ASCII 'ra'
0x0a1d864: movk w12, #0x6167            ; ASCII 'ga' -> "garage"
0x0a1d898: bl   #0xbff89c               ; Carrega GarageScene!
```

---

## 2. Diagnóstico da Falha

Como o jogo roda offline no R36S, determinados assets de variantes de resolução tablet/HD (`HD/json/achievements.json`, `HD/json/worlds.json`) são redirecionados para a pasta `Common/` ou não completam a contagem bruta do vetor no momento da checagem.  
A instrução condicional em `0x0A1D834` (`b.lo #0xa1db20`) trava o fluxo antes do bloco de montagem da cena `"garage"`, desviando a execução diretamente para o cálculo de porcentagem da barra (`0x0A1DB68: bl #0xd93c58`), que fica congelada nos 50%.

---

## 3. Solução em Lote por Engenharia Reversa (Hotpatch ARM64)

- **Instrução Original em `0x00A1D834`**:
  `b.lo #0xa1db20` -> Opcode: `0x54001763`
- **Instrução Corrigida**:
  `nop` -> Opcode: `0x1F2003D5`
- **Instrução Original em `0x00A1D9D4`** (Bloqueio secundário de requisição web):
  `b.eq #0xa1db20` -> Opcode: `0x54000C60`
- **Instrução Corrigida**:
  `nop` -> Opcode: `0x1F2003D5`
- **Efeito Imediato**:
  Elimina o bloqueio artificial de contagem de assets e dependências de rede, permitindo que a sub-rotina avance imediatamente para a inicialização da `"garage"` (`GarageScene`) e troca de cena no `cocos2d::Director`.
