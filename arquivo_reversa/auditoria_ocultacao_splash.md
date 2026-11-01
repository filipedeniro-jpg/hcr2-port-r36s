# Dossiê de Auditoria Técnica: Ocultação do Nó de Splash e Bypass do Redesenho da Barra

**Data**: 29/10/2026  
**Módulo Alvo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Classe**: `AnimatedSplash`  

---

## 1. Causa Raiz do Congelamento Visual nos 50%

Embora a cena da Garagem (`GarageScene`) e os veículos (`vehicles1` a `vehicles4`) tenham sido completamente carregados e o loop SDL2/EGL esteja rodando a 60 FPS, a tela física permaneceu congelada visualmente nos 50% pelos seguintes motivos arquiteturais do Cocos2d-x:

1. **Camada do Splash Ativa e Forçada como Visível**:
   Em `0x00A1D758`:
   ```arm64
   0x0a1d74c: ldr  x0, [x19, #0x368]       ; x0 = ponteiro do no do layout assetloader.csb
   0x0a1d750: ldr  x8, [x0]
   0x0a1d754: ldr  x8, [x8, #0x160]        ; vtable[44] -> Node::setVisible(bool)
   0x0a1d758: mov  w1, #1                  ; w1 = true (FORÇA VISIBILIDADE ATIVA!)
   0x0a1d75c: blr  x8
   ```
   Isso força o nó do `assetloader.csb` (o deserto e a barra de progresso) a ser desenhado na tela por cima da Garagem a cada quadro!

2. **Loop de Redesenho da Barra de Loading**:
   Em `0x00A1D83C`:
   ```arm64
   0x0a1d838: ldrb w8, [x19, #0x384]       ; w8 = flag de transição iniciada
   0x0a1d83c: cbnz w8, #0xa1db20           ; SE w8 != 0, SALTA PARA O REDESENHO DA BARRA!
   ```
   A cada quadro subsequente ao carregamento dos veículos, o método `AnimatedSplash::update` salta para `0x00A1DB20`, recalculando e redesenhando a barra nos 50% via `0x00A1DB68: bl #0xd93c58`.

---

## 2. Solução em Lote por Engenharia Reversa (Hotpatches ARM64)

1. **Ocultação do Nó de Splash (`assetloader.csb`)**:
   - **Endereço**: `0x00A1D758`
   - **Original**: `mov w1, #1` (Opcode: `0x320003e1`)
   - **Corrigido**: `mov w1, wzr` (Opcode: `0x2a1f03e1` - `setVisible(false)`)
   - **Efeito**: Torna o layout de loading invisível, permitindo que a `GarageScene` apareça imediatamente na tela.

2. **Corte do Redesenho Perpétuo da Barra**:
   - **Endereço**: `0x00A1D83C`
   - **Original**: `cbnz w8, #0xa1db20` (Opcode: `0x35001728`)
   - **Corrigido**: `cbnz w8, #0xa1db84` (Opcode: `0x35001a48`)
   - **Efeito**: Assim que a transição para a Garagem é acionada (`w8 == 1`), o fluxo desvia diretamente para o epílogo (`0x00A1DB84`) e retorna (`ret`), encerrando qualquer atualização na barra de progresso.
