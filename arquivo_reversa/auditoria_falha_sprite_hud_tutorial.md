# Relatório de Auditoria em Lote: Falha de Sprite::create no HUD / Tutorial

## 1. Identificação do Ponto de Crash
- **Endereço do Crash**: `0x0055b334` em `libcocos2dcpp.so`
- **Contexto**: Entrada na cena de gameplay / tutorial da primeira corrida (após vencer o loading splash de 50%).
- **Causa Raiz**: Desreferência nula de ponteiro `x23` (`0x0000000000000000`) ao acessar o slot da vtable:
  ```assembly
  0x055b324: mov  x0, x24        ; std::string com caminho da textura
  0x055b328: bl   #0xe31038      ; cocos2d::Sprite::create(std::string const&)
  0x055b32c: mov  x23, x0        ; x23 recebe retorno (NULL se falhar)
  0x055b330: str  x23, [x27]     ; grava no membro do objeto
  0x055b334: ldr  x8, [x23]      ; CRASH: SIGSEGV (x23 == 0)
  0x055b338: ldr  x24, [x8, #0x80] ; vtable->setScale
  0x055b33c: ldr  x8, [x8, #0x158] ; vtable->getContentSize
  ```

---

## 2. Anatomia da Raiz: `cocos2d::Sprite::create` (`0x00e31038`)
Ao desmontar `0x00e31038`:
- `0x00e31038` chama internamente `0x00e30fa0`.
- Em `0x00e30fa0`:
  1. Aloca `0x510` bytes via `operator new`.
  2. Executa o construtor `0x00e316f0`.
  3. Chama `initWithFile` (`vtable[0x5c0]`).
  4. Se `initWithFile` falhar (retornar 0 / bit 0 de w0 == 0):
     - Executa o destrutor do objeto alocado (`0x00e30ff8`).
     - Define `x19 = 0` (NULL).
     - Retorna `0` em `x0` (`0x00e31008 - 0x00e31018`).

---

## 3. Mapeamento de Ocorrências Semelhantes (Árvore de Chamadas em Lote)
No subsistema de Gameplay/HUD (`0x00559000` a `0x0055c000`), existem três rotinas irmãs que invocam `Sprite::create` sem checagem de retorno nulo:
1. **`0x00559764`** (em `0x00559494`):
   - Invoca `0x00e31038`.
   - Em `0x0055976c`: `ldr x8, [x21]` sem checar se `x21 == 0`.
2. **`0x0055ad1c`** (em `0x0055ab04`):
   - Invoca `0x00e31038`.
   - Em `0x0055ad34`: `ldr x8, [x21]` sem checar se `x21 == 0`.
3. **`0x0055b328`** (em `0x0055b130`):
   - Invoca `0x00e31038`.
   - Em `0x0055b334`: `ldr x8, [x23]` sem checar se `x23 == 0` (ponto do crash atual).

---

## 4. Estratégia de Solução em Lote
Em vez de aplicar múltiplos remendos pontuais em `0x55976c`, `0x55ad34` e `0x55b334`:
1. **Blindagem na Raiz ou Hotpatch Coordenado**:
   - Se `Sprite::create` (`0x00e31038`) falhar em encontrar a textura, fornecer um Sprite dummy válido (com vtable e tamanho unitário), ou blindar as 3 saídas com desvio condicional (`cbz`).
   - Se o retorno for um `Node` válido de Sprite (mesmo transparente), todo o pipeline de Cocos2d-x (`setScale`, `getContentSize`, `addChild`) executa normalmente sem corrupção de registradores e sem SIGSEGV.
2. **Telemetria de Identificação da String Ausente**:
   - Instrumentar a chamada para capturar o nome exato do arquivo que está faltando no pacote de assets para adicioná-lo ou redirecioná-lo se necessário.
