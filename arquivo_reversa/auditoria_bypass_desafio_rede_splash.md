# Dossiê de Auditoria Técnica: Neutralização do Bloqueio de Desafio de Rede no Splash

**Data**: 29/10/2026  
**Módulo Alvo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Classe**: `AnimatedSplash`  
**Método**: `AnimatedSplash::update(float dt)`  

---

## 1. Contexto e Diagnóstico

Após liberar o carregamento dos assets da Garagem em `0x00A1D834`, o jogo carregou todas as texturas de veículos (`vehicles1.png` a `vehicles4.png`) e estabilizou em 60 FPS, porém a tela física permaneceu congelada visualmente nos 50% de loading.

A investigação da janela de instruções ARM64 entre `0x0A1D910` e `0x0A1D9E0` revelou a causa raiz exata:

```arm64
0x0a1d910: bl       #0x6c0cf4               ; Checa resposta do servidor Fingersoft
0x0a1d914: mov      w20, w0                 ; w20 = status do desafio online
0x0a1d918: ldrb     w8, [sp, #0xe0]
0x0a1d91c: tbz      w8, #0, #0xa1d928
0x0a1d920: ldr      x0, [sp, #0xf0]
0x0a1d924: bl       #0x35e1c0
0x0a1d928: tbz      w20, #0, #0xa1d93c       ; SE w20 != 0 (esperando rede), NÃO SALTA!
0x0a1d92c: adrp     x0, #0x11f8000
0x0a1d930: add      x0, x0, #0x365          ; String: "Waiting for challenge response..."
0x0a1d934: bl       #0xe53a90               ; Imprime log
0x0a1d938: b        #0xa1db20               ; SALTA DIRETO PARA A BARRA EM 50%!
```

Além disso, caso avançasse por `0x0a1d93c`, as checagens subsequentes em `0x0a1d95c` e `0x0a1d968` desviavam para `0x0a1d978`:
```arm64
0x0a1d978: strb     wzr, [x19, #0x384]      ; ZERA A FLAG DE TRANSIÇÃO INICIADA!
0x0a1d97c: b        #0xa1db20               ; RETORNA AO LOOP DOS 50%!
```

---

## 2. Solução em Lote por Hotpatch ARM64

Para permitir o jogo 100% offline no R36S sem depender de servidores:

- **Endereço**: `0x00A1D928`
- **Instrução Original**: `tbz w20, #0, #0xa1d93c` (Opcode: `0x360000b4`)
- **Nova Instrução**: `b #0x00A1D93C` (Opcode: `0x14000005`)
- **Efeito Técnico**:
  - Pula a mensagem e o travamento de "Waiting for challenge response...".
  - Executa a sub-rotina em `0x00A1D93C` que inicializa o registrador essencial `x23` (`adrp x23, #0x176a000; ldr x23, [x23, #0x8f8]`), evitando o crash SIGSEGV posterior em `0x00A1DA38` (`ldr x0, [x24, #0x48]`).
  - Permite a transição limpa para a `GarageScene` sem falhas de ponteiro inválido.
