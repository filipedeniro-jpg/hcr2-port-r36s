#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script de diagnóstico remoto dos analógicos do R36S (ArkOS).
Conecta via SSH, identifica os dispositivos de controle (/dev/input/js0 e /dev/input/event*)
e monitora os valores dos eixos em repouso para detectar se há Stick Drift (Hardware)
ou se é apenas configuração de zona morta (Software).
"""

import json
import os
import sys
import time

try:
    import paramiko
except ImportError:
    print("[ERRO] Biblioteca 'paramiko' não encontrada. Instale com: pip install paramiko")
    sys.exit(1)


def carregar_configuracao():
    caminho_config = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    if not os.path.exists(caminho_config):
        return {"ip": "10.0.0.12", "porta": 22, "usuario": "ark", "senha": "ark"}
    with open(caminho_config, "r", encoding="utf-8") as f:
        return json.load(f)


def main():
    config = carregar_configuracao()
    ip = config.get("ip", "10.0.0.12")
    porta = config.get("porta", 22)
    usuario = config.get("usuario", "ark")
    senha = config.get("senha", "ark")

    print("=" * 60)
    print(" [DIAGNOSTICO REMOTO DOS ANALOGICOS DO R36S (ARKOS)]")
    print(f" Conectando ao console em {ip}:{porta} como '{usuario}'...")
    print("=" * 60)

    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    try:
        ssh.connect(ip, port=porta, username=usuario, password=senha, timeout=5)
    except Exception as e:
        print(f"[ERRO] Falha ao conectar via SSH: {e}")
        return

    print("[OK] Conexão SSH estabelecida com sucesso!")
    print("\n1. Identificando dispositivos de entrada no console...")

    # Identificar dispositivos no /proc/bus/input/devices
    stdin, stdout, stderr = ssh.exec_command("cat /proc/bus/input/devices")
    saida_dispositivos = stdout.read().decode("utf-8", errors="ignore")

    print("--- Dispositivos de Entrada Detectados ---")
    linhas = saida_dispositivos.splitlines()
    nome_atual = ""
    handlers_atual = ""
    dispositivos_joystick = []

    for linha in linhas:
        if linha.startswith("N: Name="):
            nome_atual = linha.split("=", 1)[1].strip('"')
        elif linha.startswith("H: Handlers="):
            handlers_atual = linha.split("=", 1)[1]
            if "js" in handlers_atual or "event" in handlers_atual:
                dispositivos_joystick.append((nome_atual, handlers_atual))
                print(f"  * {nome_atual} -> Handlers: {handlers_atual}")

    print("\n2. Capturando valores em repouso dos analógicos...")
    print("   [IMPORTANTE] NÃO toque nos analógicos durante este teste de 4 segundos!\n")

    # Script Python embutido executado remotamente no ArkOS para ler struct js_event de /dev/input/js0
    # struct js_event { __u32 time; __s16 value; __u8 type; __u8 number; } -> format 'IhBB' (8 bytes)
    comando_remoto = (
        "python3 - << 'EOF'\n"
        "import struct, os, select, time\n"
        "caminho = '/dev/input/js0'\n"
        "if not os.path.exists(caminho):\n"
        "    print('ERRO_JS0_NAO_ENCONTRADO')\n"
        "    exit(1)\n"
        "\n"
        "fd = os.open(caminho, os.O_RDONLY | os.O_NONBLOCK)\n"
        "eixos = {}\n"
        "eventos_iniciais = 0\n"
        "\n"
        "# Leitura da calibracao inicial de inicializacao do joystick\n"
        "tempo_inicio = time.time()\n"
        "while time.time() - tempo_inicio < 1.0:\n"
        "    r, _, _ = select.select([fd], [], [], 0.05)\n"
        "    if r:\n"
        "        dado = os.read(fd, 8)\n"
        "        if len(dado) == 8:\n"
        "            t, val, tipo, num = struct.unpack('IhBB', dado)\n"
        "            if tipo & 0x02: # JS_EVENT_AXIS\n"
        "                eixos[num] = val\n"
        "                eventos_iniciais += 1\n"
        "\n"
        "print('LEITURA_INICIAL:', eixos)\n"
        "\n"
        "# Agora monitora por 3 segundos sem tocar para ver se há flutuação / eventos fantasma\n"
        "eventos_drift = []\n"
        "tempo_monitor = time.time()\n"
        "while time.time() - tempo_monitor < 3.0:\n"
        "    r, _, _ = select.select([fd], [], [], 0.1)\n"
        "    if r:\n"
        "        dado = os.read(fd, 8)\n"
        "        if len(dado) == 8:\n"
        "            t, val, tipo, num = struct.unpack('IhBB', dado)\n"
        "            if tipo & 0x02:\n"
        "                eixos[num] = val\n"
        "                eventos_drift.append((num, val))\n"
        "\n"
        "os.close(fd)\n"
        "print('LEITURA_FINAL:', eixos)\n"
        "print('QTD_EVENTOS_SEM_TOQUE:', len(eventos_drift))\n"
        "if eventos_drift:\n"
        "    print('AMOSTRA_EVENTOS_DRIFT:', eventos_drift[:10])\n"
        "EOF"
    )

    stdin, stdout, stderr = ssh.exec_command(comando_remoto)
    resultado = stdout.read().decode("utf-8", errors="ignore")
    erros = stderr.read().decode("utf-8", errors="ignore")
    ssh.close()

    print("--- Resultado da Inspeção Remota ---")
    print(resultado)
    if erros:
        print(f"[Avisos/Stderr]: {erros}")

    # Analisar o resultado
    if "LEITURA_FINAL:" in resultado:
        for linha in resultado.splitlines():
            if linha.startswith("LEITURA_FINAL:"):
                # Avaliar os valores
                try:
                    mapa_eixos = eval(linha.replace("LEITURA_FINAL:", "").strip())
                    print("=" * 60)
                    print(" [ANALISE DETALHADA DOS EIXOS]")
                    eixo_nomes = {
                        0: "Analogico Esquerdo (X - Horizontal)",
                        1: "Analogico Esquerdo (Y - Vertical)",
                        2: "Analogico Direito (X - Horizontal)",
                        3: "Analogico Direito (Y - Vertical)",
                        4: "Gatilho / D-Pad X",
                        5: "Gatilho / D-Pad Y"
                    }
                    drift_detectado = False
                    for num, val in sorted(mapa_eixos.items()):
                        nome = eixo_nomes.get(num, f"Eixo {num}")
                        porcentagem = (val / 32767.0) * 100.0
                        status = "NORMAL (Centro)"
                        if abs(val) > 4000:
                            status = "[AVISO] DESLOCAMENTO / DRIFT SIGNIFICATIVO"
                            drift_detectado = True
                        if abs(val) > 15000:
                            status = "[ALERTA] TRAVADO / DRIFT SEVERO"
                            drift_detectado = True

                        print(f"  * {nome}: Valor bruto = {val:+6d} ({porcentagem:+.1f}%) -> {status}")

                    print("=" * 60)
                    if drift_detectado:
                        print(" [RESULTADO] STICK DRIFT FISICO DETECTADO (HARDWARE)")
                        print("    O analogico esta enviando um valor constante fora do centro em repouso.")
                        print("    O sistema operacional esta recebendo o sinal de movimento continuo.")
                    else:
                        print(" [RESULTADO] ANALOGICO CENTRALIZADO NORMALMENTE (SOFTWARE/DEADZONE)")
                        print("    Os eixos estao proximos de zero em repouso.")
                except Exception as err:
                    print(f"Erro ao processar eixos: {err}")


if __name__ == "__main__":
    main()
