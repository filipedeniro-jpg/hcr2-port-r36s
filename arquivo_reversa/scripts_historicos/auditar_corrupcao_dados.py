#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auditar_corrupcao_dados.py - Auditoria 100% offline no binario libcocos2dcpp.so
para identificar as strings de erro 'corrupted', 'internal data' e as funcoes que disparam a mensagem.
"""

import os
import sys

DIR_SO = os.path.join(os.path.dirname(__file__), "..", "hcr2_classico", "binario_so", "libcocos2dcpp.so")

def auditar():
    if not os.path.exists(DIR_SO):
        print(f"[ERRO] Binario nao encontrado: {DIR_SO}")
        sys.exit(1)
        
    print(f"[*] Carregando binario ELF para auditoria offline: {DIR_SO}")
    with open(DIR_SO, "rb") as f:
        dados = f.read()

    termos = [b"corrupt", b"internal data", b"data corrupted", b"Internal data", b"onDataRead"]
    print("=== OCORRENCIAS ENCONTRADAS ===")
    for termo in termos:
        inicio = 0
        contador = 0
        while True:
            pos = dados.find(termo, inicio)
            if pos == -1:
                break
            # Captura o contexto da string em volta (ate 60 bytes)
            trecho = dados[max(0, pos - 20) : min(len(dados), pos + len(termo) + 40)]
            trecho_limpo = "".join([chr(b) if 32 <= b <= 126 else "." for b in trecho])
            print(f"[0x{pos:08x}] ({termo.decode('latin1', errors='ignore')}): {trecho_limpo}")
            contador += 1
            inicio = pos + len(termo)
            if contador >= 5:
                print("   ... (mais ocorrencias suprimidas)")
                break

if __name__ == "__main__":
    auditar()
