#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
reiniciar_console.py - Executa reboot limpo no ArkOS via SSH caso a interface de video fique desincronizada.
"""

import paramiko
import sys

import json
import os
import paramiko
import sys

def carregar_configuracao():
    caminho = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    if os.path.exists(caminho):
        with open(caminho, "r", encoding="utf-8") as f:
            return json.load(f)
    return {"ip": "10.0.0.14", "porta": 22, "usuario": "ark", "senha": "ark"}

def reiniciar_console():
    config = carregar_configuracao()
    ip = config.get("ip", "10.0.0.14")
    porta = config.get("porta", 22)
    usuario = config.get("usuario", "ark")
    senha = config.get("senha", "ark")

    print(f"[*] Conectando via SSH em {ip}:{porta} para reiniciar o console R36S...")
    cliente = paramiko.SSHClient()
    cliente.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    try:
        cliente.connect(ip, port=porta, username=usuario, password=senha, timeout=5)
    except Exception as e:
        print("[ERRO] Falha ao conectar:", e)
        sys.exit(1)

    print("[INFO] Enviando comando de reboot...")
    cliente.exec_command(f"echo '{senha}' | sudo -S systemctl reboot")
    cliente.close()
    print("[SUCESSO] Comando de reboot enviado. O console vai reiniciar em poucos segundos.")

if __name__ == '__main__':
    reiniciar_console()
