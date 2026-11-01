# Dossiê de Engenharia Reversa: Bypass Integral de Checagens em AnimatedSplash::update

**Data**: 29/10/2026  
**Módulo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Classe**: `AnimatedSplash`  
**Função**: `AnimatedSplash::update(float dt)`  

---

## 1. Causa do Crash em 0x00A1D508

Ao suprimir a adição do layout `assetloader.csb` como nó filho em `0x00A1B0E4`, o ponteiro armazenado em `[x19, #0x368]` (rótulo de texto filho) não apontava para um objeto válido na memória do Cocos2d-x.  
No início de `AnimatedSplash::update`:
```arm64
0x0a1d4fc: ldr  x0, [x19, #0x368]
0x0a1d500: ldr  x8, [x0]
0x0a1d504: ldr  x8, [x8, #0x168]   ; cocos2d::Node::isVisible
0x0a1d508: blr  x8                 ; CRASH: desreferência em vtable inválida
```

---

## 2. Solução em Lote por Salto Incondicional

Em vez de permitir a execução da verificação visual de sub-nós e animação do splash (`0x00A1D4FC` a `0x00A1D834`), a instrução em `0x00A1D4FC` é substituída por um salto incondicional direto para `0x00A1D838`:

- **Endereço**: `0x00A1D4FC`
- **Instrução Original**: `ldr x0, [x19, #0x368]` (`0xF941B660`)
- **Nova Instrução**: `b #0x00A1D838` (Opcode: `0x140000CF`)
- **Delta**: +828 bytes

---

## 3. Comportamento do Fluxo

1. **Primeiro Quadro (`[x19, #0x384] == 0`)**:
   - Salta direto para `0x00A1D838`.
   - Como `[x19, #0x384]` é 0, prossegue para `0x00A1D840` (`mov w8, #1`) e `strb w8, [x19, #0x384]`.
   - Chama `0x00A1D848` (`bl #0x52fd8c`).
   - Salta em `0x00A1D928` (`b #0xa1d9d8`) para a transição imediata para a Garagem (`GarageScene`).

2. **Quadros Subsequentes (`[x19, #0x384] == 1`)**:
   - Salta direto para `0x00A1D838`.
   - `0x00A1D83C` (`cbnz w8, #0xa1db84`) detecta que `w8 == 1` e salta instantaneamente para o epílogo `ret` (`0x00A1DB84`).
   - Custo computacional nulo, sem redesenho da barra de loading e sem desreferências nulas.
