#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script de diagnóstico e recuperação imediata dos botões e interface do R36S.
"""

import json
import os
import sys

try:
    import paramiko
except ImportError:
    sys.exit(1)


def carregar_configuracao():
    caminho = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    with open(caminho, "r", encoding="utf-8") as f:
        return json.load(f)


def main():
    config = carregar_configuracao()
    ip = config.get("ip", "10.0.0.14")
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(ip, port=22, username="ark", password="ark", timeout=5)

    print("=" * 60)
    print(" [DIAGNÓSTICO E RECUPERAÇÃO DE ENTRADA DO R36S]")
    print("=" * 60)

    # 1. Checar processos ativos
    cmd_ps = "ps -ef | grep -i -E 'emulationstation|retroarch|gptokeyb|evdev' | grep -v grep"
    stdin, stdout, stderr = ssh.exec_command(cmd_ps)
    print("1. Processos de interface ativos:")
    for l in stdout.read().decode().splitlines():
        print("   " + l)

    # 2. Verificar se /dev/input/event3 (odroidgo3-keys) está acessível
    cmd_dev = "ls -l /dev/input/"
    stdin, stdout, stderr = ssh.exec_command(cmd_dev)
    print("\n2. Dispositivos /dev/input:")
    for l in stdout.read().decode().splitlines():
        if "event" in l or "js" in l:
            print("   " + l)

    # 3. Reiniciar totalmente o serviço do EmulationStation e tty1
    print("\n3. Forçando reinicialização limpa do serviço do EmulationStation...")
    cmd_restart = "echo ark | sudo -S systemctl restart emulationstation.service 2>/dev/null || (killall -9 emulationstation; systemctl restart autologin@tty1)"
    stdin, stdout, stderr = ssh.exec_command(cmd_restart)
    stdout.read()

    # 4. Checar se o EmulationStation voltou
    stdin, stdout, stderr = ssh.exec_command("pgrep -l emulationstat")
    print("   Status do EmulationStation após reinício: " + stdout.read().decode().strip())

    ssh.close()
    print("=" * 60)


if __name__ == "__main__":
    main()
