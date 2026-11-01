# Mapa Especializado: Hill Climb Racing 2 Clássico (v1.28.2 ARM64)

## 📌 Sumário Executivo
Este documento cataloga a engenharia reversa e a arquitetura 100% offline do port do **Hill Climb Racing 2 (v1.28.2 ARM64)** para o console R36S (ArkOS Linux).
- O port inicia do **absoluto zero**, sem nenhuma dependência ou código da versão 1.35.0.
- A única referência arquitetural aprovada é o port estável do **Hill Climb Racing 1** (`hcr1/`).
- **Backup Funcional Consolidado**: [backup_functional/hcr2 - 31-10-2026 20h09](file:///d:/Documentos/Ports%20R36S/backup_functional/hcr2%20-%2031-10-2026%2020h09) (Marco 1.5.0: 61,3 FPS sustentados, blindagem universal de glifos 0x972404, pedais de aceleração/freio destravados e veículos autênticos).

---

## 📚 Stack Técnica e Dados Binários (v1.28.2)
- **APK Oficial**: `pacotes/com.fingersoft.hcr2_1.28.2-445_minAPI17(arm64-v8a)(nodpi)_apkmirror.com.apk`
- **Binário Nativo**: `libcocos2dcpp.so` (24.592.272 bytes, pura AArch64 / ARM64-v8a)
- **Total de Assets Extraídos**: 5.080 arquivos (layouts `.csb`, `.json`, texturas `.png`)
- **Engine**: Cocos2d-x 3.x
- **Target OS**: Linux aarch64 (ArkOS / R36S)
- **Gráficos**: SDL2 + Mali-G31 (OpenGLESv2 / EGL)
- **Áudio**: OpenSL ES 1.0.1 emulado via SDL2 Audio

---

## 🧩 Métodos Nativos Declarados em AppActivity.java
Mapeados 100% offline via JADX (`hcr2_classico/descompilado_android/`):
- `onConfigurationCompleted()`: Confirmação de inicialização do jogo
- `onDataRead(boolean, int, int, String, String, int)`: Callback de leitura de dados do jogador
- `onDataWrite(boolean)`: Callback de gravação de progresso
- `onNetworkConnectivityChanged(boolean)`: Notifica estado offline/online
- `onAllIapPricesUpdated()`: Notifica preços de loja
- `getTimeStampNow()`: Retorna timestamp UNIX local

---

## 🗂️ Estrutura de Arquivos do Novo Port (`hcr2_classico/`)
- [README.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/README.md): Documentação geral do port e instruções do repositório
- [CHANGELOG.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/CHANGELOG.md): Histórico oficial de versões (Versão atual: 1.6.0)
- `salvar_no_github.bat`: Script em lote para commit e push automático do port para o GitHub
- `binario_so/libcocos2dcpp.so`: Binário ELF original intocado
- `assets/`: 5.080 arquivos de assets originais descompactados
- [arquivo_reversa/auditoria_transicao_garagem.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/auditoria_transicao_garagem.md): Dossiê da classe `AnimatedSplash` e destravamento dos 50% de loading
- [arquivo_reversa/auditoria_bloqueio_assetloader_node.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/auditoria_bloqueio_assetloader_node.md): Supressão visual de assetloader.csb em 0x00A1B0E4
- [arquivo_reversa/auditoria_bypass_update_splash.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/auditoria_bypass_update_splash.md): Bypass de desreferência nula em AnimatedSplash::update (0x00A1D4FC)
- [arquivo_reversa/auditoria_montagem_veiculo_garagem.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/auditoria_montagem_veiculo_garagem.md): Montagem de peças do veículo na GarageScene e blindagem de sprite nulo (0x00C68EEC)
- [arquivo_reversa/relatorio_auditoria_corrupcao_dados.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/relatorio_auditoria_corrupcao_dados.md): Hotpatch de validação de savegame offline
- [arquivo_reversa/mapeamento_referencia_hcr1.md](file:///d:/Documentos/Ports%20R36S/hcr2_classico/arquivo_reversa/mapeamento_referencia_hcr1.md): Guia de referência cruzada com o motor do HCR1
- `arquivo_reversa/inventario_v1_28.json`: Catálogo de 143 símbolos exportados
- `arquivo_reversa/metodos_nativos_java.json`: 43 métodos JNI identificados
- `descompilado_android/`: Código Java original extraído com JADX

---

## 🛠️ Módulos de Código C/H Traduzidos e Padronizados (pt-BR)
- [hcr2_classico/codigo/main.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/main.c#L1-L45): Cabeçalhos modulares, estruturas e instanciação do módulo game_mod
- [hcr2_classico/codigo/main.c #L46-L95](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/main.c#L46-L95): Inicialização do ambiente, configuração de tela e carregamento do binário ELF
- [hcr2_classico/codigo/main.c #L96-L145](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/main.c#L96-L145): Construtores .init_array, setup JNI, shutdown hooks e Cocos2dxRenderer.nativeInit
- [hcr2_classico/codigo/main.c #L146-L195](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/main.c#L146-L195): Loop principal do jogo, overlay menu e handshake pós-asset loader
- [hcr2_classico/codigo/main.c #L196-L260](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/main.c#L196-L260): Toques de transição contínuos, telemetria de FPS a cada 300 quadros e encerramento limpo
- [hcr2_classico/codigo/sinais_crash.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/sinais_crash.c#L1-L45): Tratador de crash AArch64 e dump de registradores x0-x30, PC, LR, SP, FP
- [hcr2_classico/codigo/sinais_crash.c #L46-L95](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/sinais_crash.c#L46-L95): Desmontagem de instruções no crash, frame pointer walk e dump de pilha
- [hcr2_classico/codigo/sinais_crash.c #L96-L145](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/sinais_crash.c#L96-L145): Encerramento seguro (graceful shutdown), flush de prefs e instalação de sinais POSIX
- [hcr2_classico/codigo/video_sdl.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/video_sdl.c#L1-L50): Variáveis de contexto GL/SDL, set_screen_size e verificação de integridade check_data
- [hcr2_classico/codigo/video_sdl.c #L51-L115](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/video_sdl.c#L51-L115): Inicialização gráfica SDL2, perfil OpenGL ES 2.0, restauração do framebuffer e deinit
- [hcr2_classico/codigo/entradas_cocos.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/entradas_cocos.c#L1-L50): Declaração e inicialização de ponteiros de funções nativas Cocos2d-x / JNI
- [hcr2_classico/codigo/entradas_cocos.c #L51-L95](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/entradas_cocos.c#L51-L95): Resolução dinâmica de símbolos JNI (resolve_entry_points)
- [hcr2_classico/codigo/entradas_cocos.c #L96-L120](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/entradas_cocos.c#L96-L120): Criação de bitmaps e envio ao Cocos2d-x (on_bitmap_create)
- [hcr2_classico/codigo/catalogo_loja.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/catalogo_loja.c#L1-L50): Catálogo de itens da loja (moedas e diamantes) e ofertas popup offline
- [hcr2_classico/codigo/catalogo_loja.c #L51-L95](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/catalogo_loja.c#L51-L95): Tabela de tintas, ofertas especiais e bundles da loja original
- [hcr2_classico/codigo/catalogo_loja.c #L96-L126](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/catalogo_loja.c#L96-L126): Consulta de recompensas (hcr_lookup_shop_reward) e inicialização da loja offline
- [hcr2_classico/codigo/hotpatches_arm64.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/hotpatches_arm64.c#L1-L45): Trampolim de glifos `s_trampolim_glyph`, hook `hook_get_glyph_name` e bypass de savegame (0x533440)
- [hcr2_classico/codigo/hotpatches_arm64.c #L46-L83](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/hotpatches_arm64.c#L46-L83): Blindagem de veículos na Garagem (0xC68368, 0xC68D00, 0xC68EEC) e loop de fontes (0xA7CE34)
- [hcr2_classico/codigo/hotpatches_arm64.c #L84-L137](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/hotpatches_arm64.c#L84-L137): Instalação do gancho universal de glifos (`patch_blindagem_universal_glifos`) e transição imediata para Garagem
- [hcr2_classico/codigo/carregador_atlas.c #L1-L84](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/carregador_atlas.c#L1-L84): Pré-carregamento síncrono de atlases e spritesheets no SpriteFrameCache (game.plist, flag_atlas, vehicles, drivers, collectibles, pistas)
- [hcr2_classico/codigo/controles_r36s.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/controles_r36s.c#L1-L45): Variáveis de estado do gamepad e controles_r36s_init com auditoria de mapeamento SDL
- [hcr2_classico/codigo/controles_r36s.c #L46-L100](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/controles_r36s.c#L46-L100): Tratamento de atalhos universais SELECT + START, overlay menu e botões do gamepad
- [hcr2_classico/codigo/controles_r36s.c #L101-L168](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/controles_r36s.c#L101-L168): Calibração com deadzone (0.25), eixos analógicos, mouse relativo e teclado
- [hcr2_classico/codigo/toque_virtual.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/toque_virtual.c#L1-L45): Estruturas multitouch, fade de transparência e toque_virtual_init
- [hcr2_classico/codigo/toque_virtual.c #L46-L85](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/toque_virtual.c#L46-L85): Trava de clique anti-swipe do cursor, limites da tela e mapeamento de pedais
- [hcr2_classico/codigo/toque_virtual.c #L86-L128](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/toque_virtual.c#L86-L128): Despacho de eventos multitouch Cocos2d-x (nativeTouchesBegin/Move/End) com telemetria




- [hcr2_classico/codigo/jni_fake.c #L1-L32](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_fake.c#L1-L32): Inicializador raiz `jni_init()`, preferências padrão e orquestração limpa
- [hcr2_classico/codigo/jni_despachante.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L1-L50): Callbacks de UI, consent form e resolução de caminho gravável `data_dir`
- [hcr2_classico/codigo/jni_despachante.c #L51-L95](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L51-L95): Filtro de de-duplicação consecutiva e telemetria inteligente de chamadas JNI
- [hcr2_classico/codigo/jni_despachante.c #L96-L150](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L96-L150): Despachante de métodos Java de retorno de objetos (`call_object`, Context, ClassLoader, Strings)
- [hcr2_classico/codigo/jni_despachante.c #L151-L200](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L151-L200): Despachante de métodos inteiros e permissões de storage/OBB (`call_int`)
- [hcr2_classico/codigo/jni_despachante.c #L201-L255](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L201-L255): Despachante de métodos de áudio (`playEffect`, `setEffectVolume`, `setEffectRate`), handshake de assets e estado in-game (`call_void`)
- [hcr2_classico/codigo/jni_despachante.c #L256-L300](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_despachante.c#L256-L300): Implementações variádicas exportadas para a interface JNI (`CALL_VARIADIC`)
- [hcr2_classico/codigo/jni_env_basico.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L1-L50): Tabela de referências locais e frames (`reg_local`, `free_ref`, `delete_local`)
- [hcr2_classico/codigo/jni_env_basico.c #L51-L100](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L51-L100): Construtores de strings, pooling de IDs/Classes e arrays primitivos persistentes
- [hcr2_classico/codigo/jni_env_basico.c #L101-L150](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L101-L150): Implementações da especificação JNIEnv (`j_FindClass`, `j_GetMethodID`, `j_GetStringUTFChars`)
- [hcr2_classico/codigo/jni_env_basico.c #L151-L200](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L151-L200): Manipulação de arrays primitivos (byte, int, float, long) e regiões de memória
- [hcr2_classico/codigo/jni_env_basico.c #L201-L250](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L201-L250): Mapeamento completo dos slots JNIEnv na tabela `env_table`
- [hcr2_classico/codigo/jni_env_basico.c #L251-L298](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_env_basico.c#L251-L298): Mapeamento dos slots JavaVM na tabela `vm_table` e inicialização (`jni_env_basico_init`)
- [hcr2_classico/codigo/jni_preferencias.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias.c#L1-L50): Hash table O(1) de preferências em memória (`pref_find_nolock`, `pref_key_hash`)
- [hcr2_classico/codigo/jni_preferencias.c #L51-L100](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias.c#L51-L100): Carregamento e sincronização de snapshot em memória
- [hcr2_classico/codigo/jni_preferencias.c #L101-L150](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias.c#L101-L150): Thread assíncrona de debounce de escrita (`prefs_worker_thread`)
- [hcr2_classico/codigo/jni_preferencias.c #L151-L200](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias.c#L151-L200): Getters e setters (`pref_dup`, `pref_has`, `pref_set`, `pref_make_jni_string`)
- [hcr2_classico/codigo/jni_preferencias.c #L201-L243](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias.c#L201-L243): Controle de concorrência e flush seguro
- [hcr2_classico/codigo/jni_preferencias_disco.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias_disco.c#L1-L50): Estruturas de cabeçalho V2 e cálculo de checksum CRC32
- [hcr2_classico/codigo/jni_preferencias_disco.c #L51-L100](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias_disco.c#L51-L100): Parsing de arquivos de savegame formato V2 e V1 legados
- [hcr2_classico/codigo/jni_preferencias_disco.c #L101-L150](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias_disco.c#L101-L150): Serialização binária atômica com validação pós-escrita
- [hcr2_classico/codigo/jni_preferencias_disco.c #L151-L232](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_preferencias_disco.c#L151-L232): Transação atômica em disco (.tmp -> rename -> .bak)
- [hcr2_classico/codigo/jni_iap_billing.c #L1-L52](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/jni_iap_billing.c#L1-L52): Emulação de loja offline, freeshop e getters de saldo
- [hcr2_classico/codigo/so_util.c](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/so_util.c): Carregador ELF de baixo nível, resolução de relocações e execução da .init_array.
- [hcr2_classico/codigo/imports.c #L1-L50](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/imports.c#L1-L50): Inclusões de cabeçalhos POSIX, Bionic, EGL/GLES2
- [hcr2_classico/codigo/imports.c #L75-L100](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/imports.c#L75-L100): Stack canary global e instrumentação de telemetria em __stack_chk_fail_fake
- [hcr2_classico/codigo/asset_shim.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/asset_shim.c#L1-L45): Inclusão de cabeçalhos, variáveis globais e caminhos de assets
- [hcr2_classico/codigo/asset_shim.c #L46-L85](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/asset_shim.c#L46-L85): Busca de arquivos (`asset_path`), desconcatenação de níveis e mapeamento
- [hcr2_classico/codigo/asset_shim.c #L86-L156](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/asset_shim.c#L86-L156): Resolução de atlases (.plist), texturas em assets/HD e fallback exclusivo de veículos
- [hcr2_classico/codigo/asset_shim.c #L157-L218](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/asset_shim.c#L157-L218): Emulação da API Android NDK AAsset (`AAsset_read`, `AAsset_seek`, `AAsset_close`)
- [hcr2_classico/codigo/asset_shim.c #L219-L295](file:///d:/Documentos/Ports%20R36S/hcr2_classico/codigo/asset_shim.c#L219-L295): Suporte a 64 bits (`AAsset_getLength64`, `AAsset_openFileDescriptor64`) e AAssetDir
---

## 🏆 Marcos Históricos e Backups Blindados
- **[31/10/2026] Restauração Plena do Áudio e Efeitos no R36S**:
  - **Status Atestado**: Subsistema de áudio conectado 100% via OpenSL ES + SDL Audio ALSA (44100 Hz, 2 canais). Despacho real de `playEffect` (desempacotamento de 5 parâmetros: pitch, pan, gain), controle contínuo de volume/taxa do motor (`setEffectVolume` e `setEffectRate`), volume master e resolução universal de `.ogg` em `Common/audio/` e `Common/music/`.
- **[25/10/2026] Primeiro Vídeo, Áudio, Controles e Fechamento Seguro (R36S)**:
  - **Diretório do Backup**: `backup_functional/hcr2_classico_marco_video_e_controles/`

  - **Componentes Preservados**: Código-fonte C/H completo traduzido em pt-BR com atalho universal **SELECT + START**, binário compilado `hcr2_classico.aarch64`, catálogo de engenharia reversa e os arquivos sincronizados baixados do console (`hcr2.aarch64`, `hcr2.gptk`, `log.txt`).
  - **Status Atestado**: Execução da `.init_array` 100% superada, motor Cocos2d-x 3.x inicializado, áudio/vídeo da vinheta Fingersoft reproduzidos e retorno perfeito ao EmulationStation via SELECT + START.
---

## 🏛️ Acervo e Arquitetura de Referência Estável 1:1 (`hcr1/codigo/`)
Este índice mapeia 100% dos módulos, cabeçalhos e rotinas estáveis do **Hill Climb Racing 1** em blocos cirúrgicos de 30 a 50 linhas (#L) para servir de espelho e fundamentação técnica direta ao HCR2 Clássico sem necessidade de leituras cegas de código:


### Arquivo `asset_shim.c` (Total: 182 linhas)
- [hcr1/codigo/asset_shim.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L1-L45) - Definições e suporte
- [hcr1/codigo/asset_shim.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L46-L90) - Funções: asset_path
- [hcr1/codigo/asset_shim.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L91-L135) - Funções: AAsset_read_fake, AAsset_seek_fake, AAsset_close_fake, AAsset_getLength_fake, AAsset_getLength64_fake, AAsset_openFileDescriptor64_fake
- [hcr1/codigo/asset_shim.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L136-L180) - Funções: AAssetDir_close_fake
- [hcr1/codigo/asset_shim.c #L181-L182](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.c#L181-L182) - Definições e suporte

### Arquivo `asset_shim.h` (Total: 34 linhas)
- [hcr1/codigo/asset_shim.h #L1-L34](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/asset_shim.h#L1-L34) - Definições e suporte

### Arquivo `config.c` (Total: 130 linhas)
- [hcr1/codigo/config.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L1-L45) - Funções: parse_var, read_config
- [hcr1/codigo/config.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L46-L90) - Definições e suporte
- [hcr1/codigo/config.c #L91-L130](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.c#L91-L130) - Funções: write_config

### Arquivo `config.h` (Total: 71 linhas)
- [hcr1/codigo/config.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.h#L1-L45) - Definições e suporte
- [hcr1/codigo/config.h #L46-L71](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/config.h#L46-L71) - Definições e suporte

### Arquivo `error.c` (Total: 18 linhas)
- [hcr1/codigo/error.c #L1-L18](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/error.c#L1-L18) - Funções: fatal_error

### Arquivo `error.h` (Total: 14 linhas)
- [hcr1/codigo/error.h #L1-L14](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/error.h#L1-L14) - Definições e suporte

### Arquivo `hcr_http.c` (Total: 58 linhas)
- [hcr1/codigo/hcr_http.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.c#L1-L45) - Funções: hcr_http_init, hcr_http_network_available, hcr_http_set_callbacks, hcr_http_enqueue_popup, hcr_http_enqueue_mission
- [hcr1/codigo/hcr_http.c #L46-L58](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.c#L46-L58) - Funções: hcr_http_pump, hcr_http_exit

### Arquivo `hcr_http.h` (Total: 14 linhas)
- [hcr1/codigo/hcr_http.h #L1-L14](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_http.h#L1-L14) - Definições e suporte

### Arquivo `hcr_log.c` (Total: 182 linhas)
- [hcr1/codigo/hcr_log.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L1-L45) - Funções: hcr_time_us, write_line
- [hcr1/codigo/hcr_log.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L46-L90) - Funções: hcr_log_init
- [hcr1/codigo/hcr_log.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L91-L135) - Funções: hcr_logf
- [hcr1/codigo/hcr_log.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L136-L180) - Funções: hcr_log_flush, hcr_log_shutdown
- [hcr1/codigo/hcr_log.c #L181-L182](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.c#L181-L182) - Definições e suporte

### Arquivo `hcr_log.h` (Total: 29 linhas)
- [hcr1/codigo/hcr_log.h #L1-L29](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/hcr_log.h#L1-L29) - Definições e suporte

### Arquivo `imports.c` (Total: 1009 linhas)
- [hcr1/codigo/imports.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L1-L45) - Definições e suporte
- [hcr1/codigo/imports.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L46-L90) - Funções: __stack_chk_fail_fake, ExecuteProgram_stub, android_log_print_fake
- [hcr1/codigo/imports.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L91-L135) - Funções: android_log_write_fake, android_log_assert_fake, dlclose_fake, rng_next, is_urandom, open_fake
- [hcr1/codigo/imports.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L136-L180) - Funções: read_fake, close_fake, __open_2_fake, __read_chk_fake
- [hcr1/codigo/imports.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L181-L225) - Funções: init_bionic_ctype, __ctype_get_mb_cur_max_fake, socket_fake, bind_fake, connect_fake, listen_fake
- [hcr1/codigo/imports.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L226-L270) - Funções: send_fake, recv_fake, getsockname_fake, getsockopt_fake, select_fake, isdigit_l_fake, islower_l_fake, isupper_l_fake, isxdigit_l_fake, iswalpha_l_fake, iswblank_l_fake, iswcntrl_l_fake, iswdigit_l_fake, iswlower_l_fake, iswprint_l_fake, iswpunct_l_fake, iswspace_l_fake, iswupper_l_fake, iswxdigit_l_fake, strcoll_l_fake, strftime_l_fake, strxfrm_l_fake, tolower_l_fake, toupper_l_fake
- [hcr1/codigo/imports.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L271-L315) - Funções: towlower_l_fake, towupper_l_fake, wcscoll_l_fake, wcsxfrm_l_fake, abort_fake, getpid_fake, sched_yield_fake, system_fake, setpriority_fake, getpriority_fake, pthread_attr_setschedpolicy_fake, pthread_getschedparam_fake, clock_nanosleep_fake, clock_gettime_fake, gettimeofday_fake, sincos_fake
- [hcr1/codigo/imports.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L316-L360) - Funções: sysconf_fake
- [hcr1/codigo/imports.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L361-L405) - Funções: bionic_mutex_destroy, pthread_mutex_init_fake, pthread_mutex_lock_fake
- [hcr1/codigo/imports.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L406-L450) - Funções: pthread_mutex_trylock_fake, pthread_mutex_unlock_fake, pthread_mutex_destroy_fake
- [hcr1/codigo/imports.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L451-L495) - Funções: bionic_cond_destroy, pthread_cond_init_fake, pthread_cond_signal_fake, pthread_cond_broadcast_fake
- [hcr1/codigo/imports.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L496-L540) - Funções: pthread_cond_destroy_fake, vpl_runtime_ready, cond_wait_slice, cond_wait_startup_tick, pthread_cond_wait_fake, pthread_cond_timedwait_fake
- [hcr1/codigo/imports.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L541-L585) - Funções: pthread_mutexattr_init_fake, pthread_mutexattr_settype_fake, pthread_mutexattr_destroy_fake, bionic_rwlock_destroy
- [hcr1/codigo/imports.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L586-L630) - Funções: pthread_rwlock_init_fake, pthread_rwlock_rdlock_fake, pthread_rwlock_tryrdlock_fake, pthread_rwlock_wrlock_fake, pthread_rwlock_trywrlock_fake, pthread_rwlock_unlock_fake
- [hcr1/codigo/imports.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L631-L675) - Funções: pthread_rwlock_destroy_fake
- [hcr1/codigo/imports.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L676-L720) - Funções: bionic_sem_destroy, sem_init_fake, sem_destroy_fake, sem_post_fake, sem_wait_fake
- [hcr1/codigo/imports.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L721-L765) - Funções: sem_trywait_fake, sem_timedwait_fake, pthread_detach_fake
- [hcr1/codigo/imports.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L766-L810) - Funções: resolve_egl_proc, glUnmapBufferOES_wrap, glBindFramebuffer_wrap, glViewport_wrap
- [hcr1/codigo/imports.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L811-L855) - Definições e suporte
- [hcr1/codigo/imports.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L856-L900) - Definições e suporte
- [hcr1/codigo/imports.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L901-L945) - Definições e suporte
- [hcr1/codigo/imports.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L946-L990) - Definições e suporte
- [hcr1/codigo/imports.c #L991-L1009](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.c#L991-L1009) - Funções: vpl_resolve_imports

### Arquivo `imports.h` (Total: 20 linhas)
- [hcr1/codigo/imports.h #L1-L20](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports.h#L1-L20) - Definições e suporte

### Arquivo `imports_cxx.h` (Total: 59 linhas)
- [hcr1/codigo/imports_cxx.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_cxx.h#L1-L45) - Definições e suporte
- [hcr1/codigo/imports_cxx.h #L46-L59](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_cxx.h#L46-L59) - Definições e suporte

### Arquivo `imports_direct.h` (Total: 233 linhas)
- [hcr1/codigo/imports_direct.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L1-L45) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L46-L90) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L91-L135) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L136-L180) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L181-L225) - Definições e suporte
- [hcr1/codigo/imports_direct.h #L226-L233](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/imports_direct.h#L226-L233) - Definições e suporte

### Arquivo `installer.c` (Total: 25 linhas)
- [hcr1/codigo/installer.c #L1-L25](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/installer.c#L1-L25) - Funções: installer_prepare_game_files

### Arquivo `installer.h` (Total: 8 linhas)
- [hcr1/codigo/installer.h #L1-L8](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/installer.h#L1-L8) - Definições e suporte

### Arquivo `jni_fake.c` (Total: 1480 linhas)
- [hcr1/codigo/jni_fake.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1-L45) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L46-L90) - Funções: jni_set_popup_offer_callback, jni_set_bitmap_callback
- [hcr1/codigo/jni_fake.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L91-L135) - Funções: free_ref
- [hcr1/codigo/jni_fake.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L136-L180) - Funções: delete_local, jni_delete_local_ref, jni_input_array_set
- [hcr1/codigo/jni_fake.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L181-L225) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L226-L270) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L271-L315) - Funções: prefs_checksum, pref_key_hash, pref_find_nolock, pref_hash_insert_nolock, pref_hash_rebuild, prefs_free_array
- [hcr1/codigo/jni_fake.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L316-L360) - Funções: prefs_read_file, prefs_parse_v2
- [hcr1/codigo/jni_fake.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L361-L405) - Funções: prefs_parse_v1
- [hcr1/codigo/jni_fake.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L406-L450) - Funções: prefs_load_candidate, prefs_load
- [hcr1/codigo/jni_fake.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L451-L495) - Funções: prefs_serialize_locked
- [hcr1/codigo/jni_fake.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L496-L540) - Funções: prefs_validate_v2_file, prefs_write_transaction
- [hcr1/codigo/jni_fake.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L541-L585) - Funções: prefs_write_snapshot
- [hcr1/codigo/jni_fake.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L586-L630) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L631-L675) - Funções: prefs_start_worker, prefs_apply_async, prefs_commit_sync
- [hcr1/codigo/jni_fake.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L676-L720) - Funções: pref_has, pref_set
- [hcr1/codigo/jni_fake.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L721-L765) - Funções: freeshop_reset_pending, freeshop_apply_purchase
- [hcr1/codigo/jni_fake.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L766-L810) - Funções: freeshop_add_cheat_money
- [hcr1/codigo/jni_fake.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L811-L855) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L856-L900) - Funções: call_int
- [hcr1/codigo/jni_fake.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L901-L945) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L946-L990) - Funções: call_long
- [hcr1/codigo/jni_fake.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L991-L1035) - Funções: call_double, call_float, call_void
- [hcr1/codigo/jni_fake.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1036-L1080) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1081-L1125) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1126-L1170](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1126-L1170) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1171-L1215](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1171-L1215) - Funções: jni_take_consent_form_request, j_GetVersion
- [hcr1/codigo/jni_fake.c #L1216-L1260](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1216-L1260) - Funções: j_DeleteGlobalRef, j_DeleteLocalRef, j_IsSameObject, j_EnsureLocalCapacity, j_PushLocalFrame
- [hcr1/codigo/jni_fake.c #L1261-L1305](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1261-L1305) - Funções: j_CallVoidMethod, j_CallVoidMethodV, j_ReleaseStringUTFChars, j_GetStringUTFLength, j_GetStringLength
- [hcr1/codigo/jni_fake.c #L1306-L1350](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1306-L1350) - Funções: j_ReleaseStringChars, j_GetArrayLength, j_ReleasePriArrayElements, j_GetPriArrayRegion, j_SetPriArrayRegion, j_RegisterNatives, j_GetJavaVM, j_ExceptionCheck, j_void1
- [hcr1/codigo/jni_fake.c #L1351-L1395](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1351-L1395) - Funções: j_unimplemented, vm_DestroyJavaVM, vm_AttachCurrentThread, vm_DetachCurrentThread, vm_GetEnv, jni_init
- [hcr1/codigo/jni_fake.c #L1396-L1440](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1396-L1440) - Definições e suporte
- [hcr1/codigo/jni_fake.c #L1441-L1480](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.c#L1441-L1480) - Funções: jni_flush_prefs

### Arquivo `jni_fake.h` (Total: 62 linhas)
- [hcr1/codigo/jni_fake.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.h#L1-L45) - Definições e suporte
- [hcr1/codigo/jni_fake.h #L46-L62](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/jni_fake.h#L46-L62) - Definições e suporte

### Arquivo `libc_shim.c` (Total: 558 linhas)
- [hcr1/codigo/libc_shim.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L1-L45) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L46-L90) - Funções: __strlen_chk_fake, __vsnprintf_chk_fake, __vsprintf_chk_fake, __system_property_get_fake, getauxval_fake, gettid_fake, syscall_fake
- [hcr1/codigo/libc_shim.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L91-L135) - Funções: sincosf_fake, android_set_abort_message_fake, posix_memalign_fake
- [hcr1/codigo/libc_shim.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L136-L180) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L181-L225) - Funções: apk_tail_prepare
- [hcr1/codigo/libc_shim.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L226-L270) - Funções: forget_apk_stream, apk_cache_fill
- [hcr1/codigo/libc_shim.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L271-L315) - Definições e suporte
- [hcr1/codigo/libc_shim.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L316-L360) - Funções: is_fake_file
- [hcr1/codigo/libc_shim.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L361-L405) - Funções: fwrite_fake, fread_fake
- [hcr1/codigo/libc_shim.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L406-L450) - Funções: fputc_fake, fputs_fake, ferror_fake
- [hcr1/codigo/libc_shim.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L451-L495) - Funções: clearerr_fake, fileno_fake, fflush_fake, fclose_fake, fseek_fake
- [hcr1/codigo/libc_shim.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L496-L540) - Funções: ftell_fake, rewind_fake, fprintf_fake
- [hcr1/codigo/libc_shim.c #L541-L558](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.c#L541-L558) - Funções: vfprintf_fake, getc_fake, ungetc_fake

### Arquivo `libc_shim.h` (Total: 56 linhas)
- [hcr1/codigo/libc_shim.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.h#L1-L45) - Definições e suporte
- [hcr1/codigo/libc_shim.h #L46-L56](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/libc_shim.h#L46-L56) - Definições e suporte

### Arquivo `main.c` (Total: 1337 linhas)
- [hcr1/codigo/main.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1-L45) - Funções: crash_handler
- [hcr1/codigo/main.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L46-L90) - Definições e suporte
- [hcr1/codigo/main.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L91-L135) - Funções: graceful_shutdown_handler
- [hcr1/codigo/main.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L136-L180) - Funções: install_signal_handlers, check_data, set_screen_size
- [hcr1/codigo/main.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L181-L225) - Funções: sdl_gl_init
- [hcr1/codigo/main.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L226-L270) - Funções: sdl_gl_deinit, load_module
- [hcr1/codigo/main.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L271-L315) - Funções: resolve_function, resolve_entry_points
- [hcr1/codigo/main.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L316-L360) - Funções: on_bitmap_create, initialize_offline_popup_offer
- [hcr1/codigo/main.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L361-L405) - Definições e suporte
- [hcr1/codigo/main.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L406-L450) - Definições e suporte
- [hcr1/codigo/main.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L451-L495) - Funções: hcr_lookup_shop_reward, initialize_original_shop_catalog
- [hcr1/codigo/main.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L496-L540) - Funções: patch_optional_daily_event_node, patch_all_stack_checks
- [hcr1/codigo/main.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L541-L585) - Funções: patch_eula_screen, patch_vehicle_select
- [hcr1/codigo/main.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L586-L630) - Definições e suporte
- [hcr1/codigo/main.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L631-L675) - Definições e suporte
- [hcr1/codigo/main.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L676-L720) - Funções: clampf
- [hcr1/codigo/main.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L721-L765) - Funções: update_input
- [hcr1/codigo/main.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L766-L810) - Definições e suporte
- [hcr1/codigo/main.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L811-L855) - Definições e suporte
- [hcr1/codigo/main.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L856-L900) - Definições e suporte
- [hcr1/codigo/main.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L901-L945) - Definições e suporte
- [hcr1/codigo/main.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L946-L990) - Definições e suporte
- [hcr1/codigo/main.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L991-L1035) - Definições e suporte
- [hcr1/codigo/main.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1036-L1080) - Definições e suporte
- [hcr1/codigo/main.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1081-L1125) - Definições e suporte
- [hcr1/codigo/main.c #L1126-L1170](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1126-L1170) - Definições e suporte
- [hcr1/codigo/main.c #L1171-L1215](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1171-L1215) - Funções: main
- [hcr1/codigo/main.c #L1216-L1260](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1216-L1260) - Definições e suporte
- [hcr1/codigo/main.c #L1261-L1305](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1261-L1305) - Definições e suporte
- [hcr1/codigo/main.c #L1306-L1337](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/main.c#L1306-L1337) - Definições e suporte

### Arquivo `opensles.c` (Total: 1131 linhas)
- [hcr1/codigo/opensles.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1-L45) - Definições e suporte
- [hcr1/codigo/opensles.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L46-L90) - Definições e suporte
- [hcr1/codigo/opensles.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L91-L135) - Definições e suporte
- [hcr1/codigo/opensles.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L136-L180) - Definições e suporte
- [hcr1/codigo/opensles.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L181-L225) - Definições e suporte
- [hcr1/codigo/opensles.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L226-L270) - Definições e suporte
- [hcr1/codigo/opensles.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L271-L315) - Funções: mb_to_linear, mix_player
- [hcr1/codigo/opensles.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L316-L360) - Funções: mix_cocos_voice
- [hcr1/codigo/opensles.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L361-L405) - Funções: mix_cocos_audio, audio_callback
- [hcr1/codigo/opensles.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L406-L450) - Funções: opensles_pump, ensure_device
- [hcr1/codigo/opensles.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L451-L495) - Definições e suporte
- [hcr1/codigo/opensles.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L496-L540) - Funções: cocos_preload_find_locked, cocos_attach_pending_sound
- [hcr1/codigo/opensles.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L541-L585) - Funções: cocos_preload_worker
- [hcr1/codigo/opensles.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L586-L630) - Funções: cocos_preload_start, cocos_audio_preload
- [hcr1/codigo/opensles.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L631-L675) - Funções: cocos_audio_play_effect
- [hcr1/codigo/opensles.c #L676-L720](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L676-L720) - Funções: cocos_audio_stop_effect, cocos_audio_pause_effect, cocos_audio_stop_all_effects, cocos_audio_pause_all_effects
- [hcr1/codigo/opensles.c #L721-L765](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L721-L765) - Funções: cocos_audio_set_effects_volume, cocos_audio_set_effect_volume, cocos_audio_set_effect_rate, cocos_audio_play_background
- [hcr1/codigo/opensles.c #L766-L810](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L766-L810) - Funções: cocos_audio_stop_background, cocos_audio_pause_background, cocos_audio_rewind_background, cocos_audio_background_playing, cocos_audio_set_background_volume, cocos_audio_background_volume, cocos_audio_effects_volume, bq_Enqueue
- [hcr1/codigo/opensles.c #L811-L855](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L811-L855) - Funções: bq_Clear, bq_GetState, bq_RegisterCallback
- [hcr1/codigo/opensles.c #L856-L900](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L856-L900) - Funções: play_SetPlayState, play_GetPlayState, play_ret0_u32, play_ok_u32, play_ok, play_RegisterCallback, vol_SetVolumeLevel, vol_GetVolumeLevel, vol_GetMaxVolumeLevel, vol_SetMute
- [hcr1/codigo/opensles.c #L901-L945](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L901-L945) - Funções: vol_GetMute, vol_enable, vol_isenabled, vol_setpos, vol_getpos, obj_Realize, obj_Resume, obj_GetState, obj_RegisterCallback, obj_Abort, obj_SetPriority, obj_GetPriority, obj_SetLOC, mix_GetInterface, simple_Destroy, player_GetInterface
- [hcr1/codigo/opensles.c #L946-L990](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L946-L990) - Funções: player_Destroy, eng_CreateAudioPlayer
- [hcr1/codigo/opensles.c #L991-L1035](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L991-L1035) - Funções: eng_CreateOutputMix
- [hcr1/codigo/opensles.c #L1036-L1080](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1036-L1080) - Funções: eng_unsupported, engine_GetInterface
- [hcr1/codigo/opensles.c #L1081-L1125](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1081-L1125) - Funções: opensles_shutdown
- [hcr1/codigo/opensles.c #L1126-L1131](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.c#L1126-L1131) - Definições e suporte

### Arquivo `opensles.h` (Total: 64 linhas)
- [hcr1/codigo/opensles.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.h#L1-L45) - Definições e suporte
- [hcr1/codigo/opensles.h #L46-L64](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/opensles.h#L46-L64) - Definições e suporte

### Arquivo `overlay.c` (Total: 169 linhas)
- [hcr1/codigo/overlay.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L1-L45) - Funções: compile, lazy_init
- [hcr1/codigo/overlay.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L46-L90) - Funções: push_pt, push_tri, build_arrow
- [hcr1/codigo/overlay.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L91-L135) - Funções: overlay_draw_cursor
- [hcr1/codigo/overlay.c #L136-L169](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.c#L136-L169) - Definições e suporte

### Arquivo `overlay.h` (Total: 11 linhas)
- [hcr1/codigo/overlay.h #L1-L11](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay.h#L1-L11) - Definições e suporte

### Arquivo `overlay_menu.c` (Total: 697 linhas)
- [hcr1/codigo/overlay_menu.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L1-L45) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L46-L90) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L91-L135) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L136-L180) - Funções: compile_shader, init_font_texture
- [hcr1/codigo/overlay_menu.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L181-L225) - Funções: overlay_menu_init
- [hcr1/codigo/overlay_menu.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L226-L270) - Funções: overlay_menu_is_open, overlay_menu_open, overlay_menu_close, overlay_menu_toggle
- [hcr1/codigo/overlay_menu.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L271-L315) - Funções: apply_remap, overlay_menu_handle_event
- [hcr1/codigo/overlay_menu.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L316-L360) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L361-L405) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L406-L450](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L406-L450) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L451-L495](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L451-L495) - Funções: draw_box, draw_string
- [hcr1/codigo/overlay_menu.c #L496-L540](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L496-L540) - Funções: overlay_menu_render
- [hcr1/codigo/overlay_menu.c #L541-L585](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L541-L585) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L586-L630](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L586-L630) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L631-L675](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L631-L675) - Definições e suporte
- [hcr1/codigo/overlay_menu.c #L676-L697](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.c#L676-L697) - Definições e suporte

### Arquivo `overlay_menu.h` (Total: 27 linhas)
- [hcr1/codigo/overlay_menu.h #L1-L27](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/overlay_menu.h#L1-L27) - Definições e suporte

### Arquivo `so_util.c` (Total: 405 linhas)
- [hcr1/codigo/so_util.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L1-L45) - Funções: so_flush_caches, so_free_temp
- [hcr1/codigo/so_util.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L46-L90) - Funções: so_finalize, so_load
- [hcr1/codigo/so_util.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L91-L135) - Definições e suporte
- [hcr1/codigo/so_util.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L136-L180) - Definições e suporte
- [hcr1/codigo/so_util.c #L181-L225](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L181-L225) - Funções: so_dynamic_tag, so_process_relr, so_relocate
- [hcr1/codigo/so_util.c #L226-L270](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L226-L270) - Definições e suporte
- [hcr1/codigo/so_util.c #L271-L315](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L271-L315) - Funções: so_lookup_export, so_resolve_symbol, so_resolve
- [hcr1/codigo/so_util.c #L316-L360](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L316-L360) - Funções: so_execute_init_array
- [hcr1/codigo/so_util.c #L361-L405](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.c#L361-L405) - Funções: so_find_addr_rx, so_try_find_addr_rx

### Arquivo `so_util.h` (Total: 67 linhas)
- [hcr1/codigo/so_util.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.h#L1-L45) - Definições e suporte
- [hcr1/codigo/so_util.h #L46-L67](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/so_util.h#L46-L67) - Definições e suporte

### Arquivo `switch.h` (Total: 55 linhas)
- [hcr1/codigo/switch.h #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/switch.h#L1-L45) - Funções: armGetSystemTick, armTicksToNs, armDCacheFlush, armICacheInvalidate, consoleInit, consoleClear, consoleUpdate, consoleExit
- [hcr1/codigo/switch.h #L46-L55](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/switch.h#L46-L55) - Definições e suporte

### Arquivo `text_render.c` (Total: 194 linhas)
- [hcr1/codigo/text_render.c #L1-L45](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L1-L45) - Funções: text_render_init
- [hcr1/codigo/text_render.c #L46-L90](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L46-L90) - Funções: next_utf8_char
- [hcr1/codigo/text_render.c #L91-L135](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L91-L135) - Definições e suporte
- [hcr1/codigo/text_render.c #L136-L180](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L136-L180) - Definições e suporte
- [hcr1/codigo/text_render.c #L181-L194](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.c#L181-L194) - Definições e suporte

### Arquivo `text_render.h` (Total: 10 linhas)
- [hcr1/codigo/text_render.h #L1-L10](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/text_render.h#L1-L10) - Definições e suporte

### Arquivo `util.c` (Total: 18 linhas)
- [hcr1/codigo/util.c #L1-L18](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/util.c#L1-L18) - Funções: tls_setup_guard, ret0

### Arquivo `util.h` (Total: 31 linhas)
- [hcr1/codigo/util.h #L1-L31](file:///d:/Documentos/Ports%20R36S/hcr1/codigo/util.h#L1-L31) - Funções: armGetTlsRw, armSetTlsRw
