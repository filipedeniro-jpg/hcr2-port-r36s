# Dossiê de Auditoria Técnica em Lote: Montagem do Veículo na GarageScene

**Data**: 29/10/2026  
**Módulo**: `libcocos2dcpp.so` (HCR2 v1.28.2 ARM64)  
**Cena**: `GarageScene`  
**Escopo**: Auditoria exaustiva em lote de desreferências de Sprite nulo na montagem de veículos  

---

## 1. Mapeamento das 3 Sub-rotinas Irmãs de Montagem de Peças

A varredura completa do binário identificou exatamente **3 pontos vulneráveis** onde o ponteiro do Sprite em `[xXX, #0x38]` é desreferenciado diretamente via vtable sem checagem de nulidade:

### Ocorrência 1: 0x00C68368 (Chassis / Base)
```arm64
0x00C68364: ldr x0, [x21, #0x38]
0x00C68368: ldr x8, [x0]           ; VULNERÁVEL: crash se x0 == NULL (Fault Address: nil)
0x00C6836C: ldr x8, [x8, #0xa8]
0x00C68370: blr x8
```

### Ocorrência 2: 0x00C68D00 (Suspensão / Rodas)
```arm64
0x00C68CFC: ldr x0, [x21, #0x38]
0x00C68D00: ldr x8, [x0]           ; VULNERÁVEL: crash se x0 == NULL (Fault Address: nil)
0x00C68D04: ldr x8, [x8, #0xa8]
0x00C68D08: blr x8
```

### Ocorrência 3: 0x00C68EEC (Acessórios / Tuning)
```arm64
0x00C68EE8: ldr x0, [x22, #0x38]
0x00C68EEC: ldr x8, [x0]           ; VULNERÁVEL: crash se x0 == NULL (Fault Address: nil)
0x00C68EF0: ldr x8, [x8, #0xa8]
0x00C68EF4: blr x8
```

---

## 2. Blindagem em Lote por Hotpatch ARM64

Para cada uma das 3 ocorrências:
Se o Sprite não for carregado (`x0 == NULL`), a chamada virtual é ignorada com segurança via desvio condicional (`cbz x0`) para o epílogo da respectiva sub-rotina, impedindo qualquer encerramento abrupto do jogo.

---

## 3. Fallback no Asset Shim
No `asset_shim.c`, quando uma textura `.png` de carro não existir no disco (por estar originalmente apenas dentro do atlas compilado), redirecionar automaticamente para uma textura compatível existente (`HD/textures/cars/newcar_chassis.png`) ou retornar imagem dummy, garantindo que o Cocos2d-x sempre instancie um `Sprite` válido em memória.
