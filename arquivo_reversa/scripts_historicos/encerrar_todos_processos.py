#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
encerrar_todos_processos.py - Encerra imediatamente processos de jogos e restaura a interface do R36S.
"""

import json
import os
import sys
import time
import paramiko

def obter_config():
    caminho = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    if os.path.exists(caminho):
        try:
            with open(caminho, "r", encoding="utf-8") as f:
                return json.load(f)
        except Exception:
            pass
    return {"ip": "10.0.0.14", "porta": 22, "usuario": "ark", "senha": "ark"}

def encerrar_processos(reiniciar_es=False):
    cfg = obter_config()
    ip = cfg.get("ip", cfg.get("ip_console", "10.0.0.14"))
    porta = int(cfg.get("porta", 22))
    usuario = cfg.get("usuario", "ark")
    senha = cfg.get("senha", "ark")

    print(f"[*] Conectando via SSH em {ip}:{porta} para gerenciar o console R36S...")
    cliente = paramiko.SSHClient()
    cliente.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    try:
        cliente.connect(ip, port=porta, username=usuario, password=senha, timeout=6)
    except Exception as e:
        print(f"[ERRO] Falha ao conectar ao R36S: {e}")
        sys.exit(1)

    print("[*] Encerrando jogos e liberando nós de hardware (DRM/KMS e Framebuffer)...")
    comandos_kill = f"""bash -c "
echo '{senha}' | sudo -S killall -9 hcr2.aarch64 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 hcr.aarch64 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 hcr_classico 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 cartman 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 gptokeyb 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 gptokeyb.armhf 2>/dev/null || true
echo '{senha}' | sudo -S killall -9 gptokeyb.aarch64 2>/dev/null || true
echo '{senha}' | sudo -S pkill -9 -f 'hcr2' 2>/dev/null || true
echo '{senha}' | sudo -S pkill -9 -f 'hcr' 2>/dev/null || true
echo '{senha}' | sudo -S pkill -9 -f 'ports' 2>/dev/null || true
echo '{senha}' | sudo -S pkill -9 -f 'gptk' 2>/dev/null || true
echo 0 | sudo tee /sys/class/graphics/fb0/blank 2>/dev/null || true
echo 0 | sudo tee /sys/class/graphics/fb1/blank 2>/dev/null || true
echo 100 | sudo tee /sys/class/backlight/*/brightness 2>/dev/null || true
echo '{senha}' | sudo -S chvt 1 2>/dev/null || true
" """

    try:
        stdin, stdout, stderr = cliente.exec_command(comandos_kill, timeout=5)
        stdout.read()
    except Exception:
        pass

    if reiniciar_es:
        print("[*] Reiniciando EmulationStation de forma assincrona (--no-block)...")
        comando_restart = f"echo '{senha}' | sudo -S systemctl restart emulationstation --no-block"
        try:
            stdin, stdout, stderr = cliente.exec_command(comando_restart, timeout=5)
            stdout.read()
        except Exception:
            pass

        time.sleep(1)
        try:
            stdin, stdout, stderr = cliente.exec_command("systemctl is-active emulationstation", timeout=3)
            status = stdout.read().decode("utf-8", errors="ignore").strip()
            print(f"[INFO] Status do servico EmulationStation: {status}")
        except Exception:
            print("[INFO] Comando de reinicio enviado.")

    # Restaura o foco do terminal virtual no console físico
    try:
        cliente.exec_command(f"echo '{senha}' | sudo -S chvt 1")
    except Exception:
        pass

    cliente.close()
    print("[SUCESSO] Processos encerrados e sinal de video restaurado com sucesso!")

if __name__ == "__main__":
    reiniciar = "--reiniciar-es" in sys.argv
    encerrar_processos(reiniciar_es=reiniciar)
