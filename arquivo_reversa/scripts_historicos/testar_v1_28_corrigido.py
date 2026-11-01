# -*- coding: utf-8 -*-
"""
Deploy do executavel corrigido e execucao do teste remoto no R36S.
Para o EmulationStation antes da execucao para evitar conflito de tela/controles no ArkOS.
"""

import paramiko
import json
import os
import time

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
BINARIO_LOCAL = os.path.join(DIR_RAIZ, "binarios", "hcr2_classico.aarch64")
CAMINHO_CONFIG = os.path.join(DIR_RAIZ, "ferramentas", "configuracao_console.json")
DIR_CLASSICO = os.path.join(DIR_RAIZ, "hcr2_classico")
LOG_LOCAL = os.path.join(DIR_CLASSICO, "arquivo_reversa", "primeiro_log_console.txt")

ip = "10.0.0.16"
if os.path.exists(CAMINHO_CONFIG):
    try:
        with open(CAMINHO_CONFIG, "r", encoding="utf-8") as f:
            cfg = json.load(f)
            ip = cfg.get("ip", cfg.get("ip_console", ip))
    except Exception:
        pass

print(f"[*] Conectando via SSH em {ip}...")
ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(ip, port=22, username="ark", password="ark", timeout=10)

print(f"[*] Pausando EmulationStation para conceder exclusividade total de tela e controles ao jogo...")
ssh.exec_command("killall -9 hcr2.aarch64 gptokeyb 2>/dev/null; echo ark | sudo -S systemctl stop emulationstation")
time.sleep(1)

print(f"[*] Enviando binario atualizado hcr2_classico.aarch64 via SFTP...")
sftp = ssh.open_sftp()
sftp.put(BINARIO_LOCAL, "/roms/ports/hcr2/hcr2.aarch64")
ssh.exec_command("chmod +x /roms/ports/hcr2/hcr2.aarch64")
sftp.close()

print(f"[*] Disparando execucao nativa exclusiva no R36S...")
ssh.exec_command("echo 0 | sudo tee /sys/class/graphics/fb0/blank; echo 100 | sudo tee /sys/class/backlight/*/brightness; cd /roms/ports/hcr2 && ./hcr2.aarch64 > log.txt 2>&1")
time.sleep(22)


sftp = ssh.open_sftp()
try:
    with sftp.open("/roms/ports/hcr2/log.txt", "r") as f:
        log_conteudo = f.read().decode('utf-8', errors='replace')
        with open(LOG_LOCAL, "w", encoding="utf-8") as lf:
            lf.write(log_conteudo)
except Exception as e:
    log_conteudo = f"[ERRO]: {e}"

sftp.close()
ssh.close()

print("\n==================== LOG DO CONSOLE R36S ====================")
linhas = log_conteudo.strip().split("\n")
if len(linhas) > 40:
    for l in linhas[:15]:
        print(l)
    print(f"\n... [{len(linhas) - 30} linhas omitidas] ...\n")
    for l in linhas[-15:]:
        print(l)
else:
    print(log_conteudo.strip())
print("============================================================\n")
