# -*- coding: utf-8 -*-
import paramiko
import json
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_CONFIG = os.path.join(DIR_RAIZ, "ferramentas", "configuracao_console.json")

ip = "10.0.0.16"
with open(CAMINHO_CONFIG, "r", encoding="utf-8") as f:
    ip = json.load(f).get("ip", ip)

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(ip, port=22, username="ark", password="ark", timeout=8)

stdin, stdout, stderr = ssh.exec_command("ps -fp 690 2>/dev/null; systemctl is-active emulationstation")
print(stdout.read().decode('utf-8', errors='replace').strip())

ssh.close()
