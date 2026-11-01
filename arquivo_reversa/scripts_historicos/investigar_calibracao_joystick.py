#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script para inspecionar a calibração e configuração de software do joystick no ArkOS.
Verifica:
- jscal e arquivos de calibração persistente (/var/lib/joystick/joystick.state)
- Mapeamento do EmulationStation (~/.emulationstation/es_input.cfg)
- Logs do kernel sobre o gamepad (dmesg)
- Dispositivo evdev e propriedades dos eixos (absinfo: min, max, fuzz, flat)
"""

import json
import os
import sys

try:
    import paramiko
except ImportError:
    print("[ERRO] Biblioteca 'paramiko' não instalada.")
    sys.exit(1)


def carregar_configuracao():
    caminho_config = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    with open(caminho_config, "r", encoding="utf-8") as f:
        return json.load(f)


def main():
    config = carregar_configuracao()
    ip = config.get("ip", "10.0.0.14")
    porta = config.get("porta", 22)
    usuario = config.get("usuario", "ark")
    senha = config.get("senha", "ark")

    print("=" * 60)
    print(" [INVESTIGACAO DE SOFTWARE: CALIBRACAO DO GAMEPAD NO ARKOS]")
    print(f" Conectando ao console em {ip}:{porta}...")
    print("=" * 60)

    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    try:
        ssh.connect(ip, port=porta, username=usuario, password=senha, timeout=5)
    except Exception as e:
        print(f"[ERRO] Falha ao conectar via SSH: {e}")
        return

    print("[OK] Conectado ao console!\n")

    # 1. Verificar jscal e calibração atual do joystick
    cmd_jscal = "which jscal && jscal -p /dev/input/js0 || echo 'jscal nao disponivel'"
    stdin, stdout, stderr = ssh.exec_command(cmd_jscal)
    out_jscal = stdout.read().decode("utf-8", errors="ignore").strip()
    print("1. Calibracao atual via jscal:")
    print(f"   {out_jscal}\n")

    # 2. Verificar se existe arquivo de estado persistente do joystick
    cmd_state = "ls -la /var/lib/joystick/ /etc/joystick* 2>/dev/null || echo 'Sem arquivos de estado'"
    stdin, stdout, stderr = ssh.exec_command(cmd_state)
    out_state = stdout.read().decode("utf-8", errors="ignore").strip()
    print("2. Arquivos de estado de joystick encontrados:")
    print(f"   {out_state}\n")

    # 3. Inspecionar propriedades absinfo de /dev/input/event2 (GO-Super Gamepad) via python remoto
    cmd_absinfo = (
        "python3 - << 'EOF'\n"
        "import fcntl, struct, glob\n"
        "# EVIOCGABS(axis) = 0x80184540 + axis\n"
        "# struct input_absinfo { __s32 value; __s32 minimum; __s32 maximum; __s32 fuzz; __s32 flat; __s32 resolution; }\n"
        "try:\n"
        "    with open('/dev/input/event2', 'rb') as f:\n"
        "        for axis in range(6):\n"
        "            try:\n"
        "                buf = bytearray(24)\n"
        "                fcntl.ioctl(f.fileno(), 0x80184540 + axis, buf)\n"
        "                val, vmin, vmax, fuzz, flat, res = struct.unpack('iiiiii', buf)\n"
        "                print(f'   Eixo {axis}: min={vmin}, max={vmax}, fuzz={fuzz}, flat={flat}, valor_atual={val}')\n"
        "            except Exception as e:\n"
        "                pass\n"
        "except Exception as e:\n"
        "    print(f'   Erro ao ler event2: {e}')\n"
        "EOF"
    )
    stdin, stdout, stderr = ssh.exec_command(cmd_absinfo)
    out_absinfo = stdout.read().decode("utf-8", errors="ignore").strip()
    print("3. Limites e propriedades fisicas dos eixos (input_absinfo do kernel):")
    print(out_absinfo if out_absinfo else "   Nao foi possivel obter absinfo\n")

    # 4. Inspecionar dmesg para entender o driver do controle
    cmd_dmesg = "dmesg | grep -i -E 'gamepad|joystick|saradc|odroidgo|rk_gamepad|adc' | tail -n 15"
    stdin, stdout, stderr = ssh.exec_command(cmd_dmesg)
    out_dmesg = stdout.read().decode("utf-8", errors="ignore").strip()
    print("\n4. Mensagens do Kernel sobre Gamepad/ADC (dmesg):")
    for linha in out_dmesg.splitlines():
        print(f"   {linha}")

    # 5. Inspecionar es_input.cfg do EmulationStation
    cmd_es = "cat /etc/emulationstation/es_input.cfg ~/.emulationstation/es_input.cfg 2>/dev/null | grep -A 25 -i 'GO-Super' | head -n 30"
    stdin, stdout, stderr = ssh.exec_command(cmd_es)
    out_es = stdout.read().decode("utf-8", errors="ignore").strip()
    print("\n5. Mapeamento do EmulationStation (es_input.cfg):")
    if out_es:
        for linha in out_es.splitlines()[:20]:
            print(f"   {linha}")
    else:
        print("   Nenhum mapeamento especifico encontrado")

    ssh.close()
    print("=" * 60)
    print(" [FIM DA INVESTIGACAO]")


if __name__ == "__main__":
    main()
