#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import json
import os
import paramiko

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_CONFIG = os.path.join(DIR_RAIZ, "ferramentas", "configuracao_console.json")

ip = "10.0.0.14"
if os.path.exists(CAMINHO_CONFIG):
    with open(CAMINHO_CONFIG, "r", encoding="utf-8") as f:
        cfg = json.load(f)
        ip = cfg.get("ip", ip)

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(ip, port=22, username="ark", password="ark", timeout=5)

stdin, stdout, stderr = ssh.exec_command("ls -l /proc/$(pgrep hcr2.aarch64)/fd 2>/dev/null | grep -E 'dri|fb|input|event|snd'")
fds = stdout.read().decode("utf-8", errors="ignore")
print("=== DESCRITORES DE VIDEO / INPUT / AUDIO ABERTOS PELO JOGO ===")
print(fds.strip() if fds.strip() else "[NENHUM DESCRITOR DRI/FB ENCONTRADO]")

stdin, stdout, stderr = ssh.exec_command("cat /sys/class/graphics/fb0/blank")
fb_blank = stdout.read().decode("utf-8", errors="ignore").strip()
print(f"\nStatus do Framebuffer (fb0/blank): {fb_blank} (0=ACESO, 1=APAGADO)")

stdin, stdout, stderr = ssh.exec_command("sudo fgconsole 2>/dev/null || cat /sys/class/tty/tty0/active")
tty_ativo = stdout.read().decode("utf-8", errors="ignore").strip()
print(f"Terminal Virtual Ativo no Console: {tty_ativo}")

ssh.close()
