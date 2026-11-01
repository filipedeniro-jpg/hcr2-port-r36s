# Relatório de Auditoria Offline: Causa Raiz de "Internal Data Corrupted!"

## 1. Dados e Evidências Extraídas 100% Offline (0 Tokens da IA)
- **String Localizada em .rodata**: `0x01184134` (`"Internal data corrupted!"`).
- **Strings Vizinhas**: `"BAD NEWS!"`, `"CHEAT DETECTED"`, `"cheater_detected"`, `"too_much_gems"`, `"too_much_coins"`.
- **Rotina de Validação de Integridade do Save**:
  - **Início da Função**: `0x00533408`
  - **Ponto de Acionamento do Erro**: `0x00533940` (carrega string `"BAD NEWS! Internal data corrupted!"`)
  - **Epílogo de Sucesso da Validação**: `0x00533fa8` (retorna `ret` com status OK)

## 2. Diagnóstico Técnico
Ao carregar o perfil local offline ou sem assinatura criptográfica do Google Play Games / Fingersoft Cloud, a rotina em `0x00533408` falha na verificação de hash e desvia para o fluxo de erro `"BAD NEWS! Internal data corrupted!"`, impedindo a barra de progresso de avançar além dos ~50%.

## 3. Solução em Lote via Hotpatch ARM64
- Em vez de acionar o erro, redirecionar o início da rotina de validação diretamente para o epílogo de sucesso:
  - **Origem**: `0x00533408`
  - **Destino**: `0x00533fa8`
  - **Opcode Calculado**: `0x140002e8` (`b #0x533fa8`)
- Esse bypass em lote neutraliza a rejeição do save offline e permite que a tela de carregamento avance para 100% e abra o Menu Principal / Garagem.
