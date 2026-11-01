# -*- coding: utf-8 -*-
"""
Coleta o log.txt completo atual e o status do processo no R36S via SSH.
"""

import paramiko
import json
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_CONFIG = os.path.join(DIR_RAIZ, "ferramentas", "configuracao_console.json")
LOG_LOCAL = os.path.join(DIR_RAIZ, "hcr2_classico", "arquivo_reversa", "log_atual_console.txt")

ip = "10.0.0.14"
if os.path.exists(CAMINHO_CONFIG):
    try:
        with open(CAMINHO_CONFIG, "r", encoding="utf-8") as f:
            cfg = json.load(f)
            ip = cfg.get("ip", cfg.get("ip_console", ip))
    except Exception:
        pass

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
try:
    ssh.connect(ip, port=22, username="ark", password="ark", timeout=8)
except Exception as e:
    print(f"[ERRO SSH]: {e}")
    exit(1)

# Checa se processo esta ativo
stdin, stdout, stderr = ssh.exec_command("pgrep -a hcr2.aarch64")
processo_ativo = stdout.read().decode('utf-8', errors='replace').strip()
print(f"[*] Processo hcr2.aarch64 ativo: {processo_ativo if processo_ativo else 'NAO (encerrado)'}")

# Checa dmesg para verificar se houve SIGSEGV/Kernel crash
stdin, stdout, stderr = ssh.exec_command("dmesg | grep -iE 'hcr2|segfault|oom|killed' | tail -n 10")
dmesg_saida = stdout.read().decode('utf-8', errors='replace').strip()
if dmesg_saida:
    print(f"[*] Dmesg relevante:\n{dmesg_saida}")

# Baixa log.txt
sftp = ssh.open_sftp()
try:
    with sftp.open("/roms/ports/hcr2/log.txt", "r") as f:
        conteudo = f.read().decode('utf-8', errors='replace')
    with open(LOG_LOCAL, "w", encoding="utf-8") as lf:
        lf.write(conteudo)
    linhas = conteudo.strip().split("\n")
    print(f"[*] Total de linhas no log: {len(linhas)}")
    print("--- Ultimas 25 linhas do log ---")
    for l in linhas[-25:]:
        print(l)
except Exception as e:
    print(f"[ERRO SFTP]: {e}")

sftp.close()
ssh.close()
