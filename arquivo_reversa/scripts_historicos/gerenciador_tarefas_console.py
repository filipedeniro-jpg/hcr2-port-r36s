#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
gerenciador_tarefas_console.py - Acessa o gerenciador de tarefas do R36S e inspeciona processos ativos
"""

import paramiko
import json
import os

ip = "10.0.0.12"
caminho_config = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
if os.path.exists(caminho_config):
    try:
        with open(caminho_config, "r", encoding="utf-8") as f:
            cfg = json.load(f)
            ip = cfg.get("ip", ip)
    except Exception:
        pass

def inspecionar():
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    try:
        ssh.connect(ip, port=22, username="ark", password="ark", timeout=5)
        
        # 1. Lista todos os processos concorrentes com a tela
        cmd1 = "ps -eo pid,ppid,user,stat,%cpu,%mem,comm,args --sort=-%cpu | head -n 25"
        stdin, stdout, stderr = ssh.exec_command(cmd1)
        out1 = stdout.read().decode('utf-8', errors='ignore').encode('ascii', errors='replace').decode('ascii')
        print("=== GERENCIADOR DE TAREFAS DO CONSOLE (TOP PROCESSOS) ===")
        print(out1)
        
        # 2. Inspeciona se tem scripts shell em loop (ex: .sh, bash, sleep, etc.)
        cmd2 = "ps -ef | grep -E 'hcr|sh|bash|emulation|ports' | grep -v grep"
        stdin, stdout, stderr = ssh.exec_command(cmd2)
        out2 = stdout.read().decode('utf-8', errors='ignore').encode('ascii', errors='replace').decode('ascii')
        print("\n=== ARVORE DE PROCESSOS DE JOGOS E SHELLS ===")
        print(out2)
        
        ssh.close()
    except Exception as e:
        print(f"[ERRO]: {e}")

if __name__ == "__main__":
    inspecionar()
