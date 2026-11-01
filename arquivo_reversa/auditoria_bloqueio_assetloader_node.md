# Dossiê de Auditoria Técnica: Supressão Definitiva do Nó Visual assetloader.csb

**Data**: 29/10/2026  
**Módulo Alvo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Classe**: `AnimatedSplash`  
**Método**: `AnimatedSplash::init(const std::string &path)`  

---

## 1. Causa Raiz da Persistência da Barra de 50%

A imagem de fundo do deserto e a barra de progresso de 50% pertencem ao layout `assetloader.csb`.  
Dentro do método de inicialização `AnimatedSplash::init`, o nó compilado do `assetloader.csb` é instanciado em `0x00A1B0A0` e armazenado no ponteiro de membro `[x19, #0x348]`.

Logo em seguida, a instrução em `0x00A1B0E4` anexa permanentemente o layout à árvore de nós visíveis da engine:

```arm64
0x0a1b0d4: ldr  x8, [x19]
0x0a1b0d8: ldr  x8, [x8, #0x1f0]        ; vtable[0x1f0/8] -> cocos2d::Node::addChild
0x0a1b0dc: ldr  x1, [x19, #0x348]       ; x1 = nó do layout assetloader.csb
0x0a1b0e0: mov  x0, x19                 ; x0 = AnimatedSplash (this)
0x0a1b0e4: blr  x8                      ; this->addChild(assetloader.csb)
```

Mesmo que os métodos de atualização (`update`) cessem ou desviem para o epílogo, todo nó anexado à árvore visual de cena (`Scene`) é processado e desenhado pelo pipeline do Cocos2d-x / EGL em todas as chamadas de `drawScene()`, sobrepondo a Garagem e a frota de veículos já carregada.

---

## 2. Solução em Lote por Engenharia Reversa (Hotpatch ARM64)

- **Endereço**: `0x00A1B0E4`
- **Instrução Original**: `blr x8` (Opcode: `0xD63F0100`)
- **Nova Instrução**: `nop` (Opcode: `0xD503201F`)
- **Efeito Imediato**:
  O layout `assetloader.csb` é suprimido da árvore de renderização. O motor Cocos2d-x deixa de desenhar o fundo estático do deserto e a barra de carregamento, permitindo que a `GarageScene` (que já carrega com sucesso os modelos `vehicle_formula_model_premium.json`, `vehicle_superbike_model.json`, etc.) seja exibida sem obstrução no LCD do R36S.
