#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import json
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

# Checa se hcr2.aarch64 ainda esta rodando
stdin, stdout, stderr = ssh.exec_command("ps -ef | grep 'hcr2.aarch64' | grep -v grep")
processo = stdout.read().decode("utf-8", errors="ignore").strip()
print(f"Status do Processo: {processo if processo else 'NAO ESTA RODANDO'}")

# Le as ultimas 40 linhas do log.txt atual no console
stdin, stdout, stderr = ssh.exec_command("tail -n 40 /roms/ports/hcr2/log.txt 2>/dev/null")
ultimas = stdout.read().decode("utf-8", errors="ignore")
print("\n=== ULTIMAS LINHAS DO LOG ATUAL ===")
print(ultimas.strip())

ssh.close()
