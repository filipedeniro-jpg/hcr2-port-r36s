# Changelog Oficial: Hill Climb Racing 2 - Port Nativo ARM64 (R36S)

Todas as alterações notáveis deste projeto serão documentadas neste arquivo.
O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/) e adota o padrão de formatação pt-BR (Norma ABNT / ISO 80000).

---

## [1.7.0] - 03/11/2026 01:50

### 🌍 Extrusão do Solo Subterrâneo, Atalhos de Captura Manual e Referência Android Ground Truth

- **Novidades**:
  - **Captura Manual Instantânea (`capturar_tela_r36s.bat` e `scripts/03_capturar_tela_r36s.bat`)**: Utilitário em 1 clique para extração direta do framebuffer do R36S via SSH com abertura automática da imagem capturada no Windows.
  - **Ferramenta de Telemetria Android (`ferramentas/coletar_dados_android.py`)**: Script automatizado para conectar via ADB ao tablet físico, obter PID e despejar logcat para comparação de referência com o binário oficial.
  - **Ground Truth Oficial**: Captura e catalogação de referência visual e de shaders do APK oficial v1.28.2 rodando em hardware Android real.

- **Correções**:
  - **Correção da Projeção Vertical do Solo (`terrain_surface.vsh`)**: Identificado através dos logs de vértice (`pos.y = -0.4` colapsado em altura zero) que a malha do solo subterrâneo necessitava de projeção vertical descendente vinculada ao marcador `a_texCoord.y == 1.0`. A pista flutuante foi eliminada e o terreno agora preenche toda a base da tela atrás dos pedais.
  - **Sincronização Redundante de Shaders**: Pipeline de envio do `enviar_e_executar.py` atualizado para propagar os shaders GLSL corrigidos para `HD/shaders/`, `Common/shaders/` e `shaders/`.

- **Métricas Comprovadas no Hardware Físico**:
  - Mais de **2.910 quadros sustentados** a **61,3 FPS** estáveis na GPU Mali-G31 com a malha inferior de solo ativa no console R36S.

---

## [1.6.0] - 01/11/2026 12:26

### 🎮 Controles Calibrados, Trava de Clique Anti-Swipe e Modularização SRP

- **Novidades**:
  - **Script de Backup GitHub (`salvar_no_github.bat`)**: Utilitário em lote para sincronização, commit e push com 1 clique para o repositório remoto oficial (`https://github.com/filipedeniro-jpg/hcr2-port-r36s.git`).
  - **Filtro de Arquivos (`.gitignore`)**: Isolamento de artefatos de compilação (`*.o`, `*.d`, `*.tmp`, `*.bak`).
  - **Auditoria de Mapeamento**: Inicialização SDL agora registra em log o mapeamento real retornado por `SDL_GameControllerMapping`.
  - **Inclusão dos Launchers do Console**: Pasta `console_r36s/` integrada com `Hill Climb Racing 2.sh` e `hcr2.gptk`.

- **Correções**:
  - **Eliminação do Drift no Analógico Direito**: Implementada zona morta (*deadzone*) de 25% em `controles_r36s.c`, neutralizando ruídos e a puxada involuntária do cursor para a borda direita da tela.
  - **Trava de Clique Anti-Swipe no Botão B**: Em `toque_virtual.c`, as coordenadas do cursor agora são rigorosamente congeladas enquanto o botão de seleção estiver pressionado. Impede que o Cocos2d-x interprete o clique como um gesto de arrasto (swipe) sobre o veículo no centro da tela.
  - **Desmembramento do Monólito (Regra 11)**: O arquivo monolítico de 451 linhas foi dividido em dois submódulos de responsabilidade única:
    - `controles_r36s.c` (168 linhas): Leitura de eventos SDL, hotkeys `SELECT + START` e eixos.
    - `toque_virtual.c` (128 linhas): Gestão do cursor virtual, trava anti-swipe e despacho multitouch.

- **Métricas Comprovadas no Hardware Físico**:
  - Mais de **2.550 quadros sustentados** a **~60 FPS** (57,6 a 62,5 FPS) no console R36S sem nenhum crash handler.

---

## [1.5.0] - 31/10/2026 20:09

### 🏆 Marco Histórico: Estabilidade Sustentada a 61,3 FPS e Controles Físicos Plenos

- **Novidades**:
  - **Texturas Autênticas de Veículos**: Jeep vermelho oficial com pneus originais (`jeep-tire.png`) e rotação física perfeita (1.880 texturas HD extraídas).
  - **Bandeiras dos Oponentes**: `flag_atlas.plist` e `flag_atlas.png` pré-carregados no `SpriteFrameCache` via `carregador_atlas.c`.
  - **HUD de Corrida Desbloqueado**: Acelerador no botão A/R1 e freio no botão Y/L1 nos pontos do HUD (`0.84 * W`, `0.82 * H` e `0.16 * W`, `0.82 * H`).

- **Correções**:
  - **Blindagem Universal de Glifos (`0x972404`)**: Trampolim nativo ARM64 (`s_trampolim_glyph`) protegendo 100% dos 50 chamadores de `GetGlyphName` contra `Bus Error (Signal 7)`.
  - **Blindagem em Lote de Veículos na Garagem**: Neutralização de 3 desreferências nulas com `cbz x0` em `0x00C68368` (chassis), `0x00C68D00` (suspensão) e `0x00C68EEC` (tuning).
  - **Estabilidade do Botão START**: Removido `nativeKeyDown(AKEYCODE_BACK)` que forçava colisão com `EditBox` e teclados virtuais.

