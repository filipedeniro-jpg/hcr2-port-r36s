#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script utilitário para listar processos ativos e gerenciar tarefas no console R36S.
Conecta via SSH e extrai ps aux formatado.
"""

import paramiko
import sys

def obter_cliente_ssh():
    cliente = paramiko.SSHClient()
    cliente.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    try:
        cliente.connect('10.0.0.12', port=22, username='ark', password='ark', timeout=5)
        return cliente
    except Exception as e:
        print("[ERRO] Falha ao conectar via SSH em 10.0.0.12:", e)
        return None

def listar_processos():
    cliente = obter_cliente_ssh()
    if not cliente:
        sys.exit(1)

    comando = "ps aux | grep -E 'emulationstation|hcr|ports|gptokeyb|bash|SDL|retroarch' | grep -v grep"
    stdin, stdout, stderr = cliente.exec_command(comando)
    saida = stdout.read().decode('utf-8', errors='replace')
    
    print("=== GERENCIADOR DE PROCESSOS ATIVOS (R36S) ===")
    if saida.strip():
        print(saida.strip())
    else:
        print("[INFO] Nenhum processo de jogo ou frontend detectado nos filtros.")

    # Detalhar uso de memória e carga geral
    stdin, stdout, stderr = cliente.exec_command("uptime; free -m")
    status = stdout.read().decode('utf-8', errors='replace')
    print("\n=== CARGA E MEMORIA ===")
    print(status.strip())

    cliente.close()

if __name__ == '__main__':
    listar_processos()
