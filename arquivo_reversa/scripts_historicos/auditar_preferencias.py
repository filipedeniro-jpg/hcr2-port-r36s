#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auditar_preferencias.py -- Auditoria das chaves e valores persistidos em save/preferences.bin
"""

import sys
import os
import re

def auditar_preferencias(caminho_bin):
    if not os.path.exists(caminho_bin):
        print(f"[-] Arquivo nao encontrado: {caminho_bin}")
        return

    with open(caminho_bin, "rb") as f:
        dados = f.read()

    print(f"[*] Tamanho do preferences.bin: {len(dados)} bytes")
    tokens = re.findall(b"[a-zA-Z0-9_\\-\\.:/\\{\\}\\\"\\,]{2,}", dados)
    print("\n[+] Chaves e valores identificados no preferences.bin:")
    for t in tokens:
        try:
            s = t.decode("utf-8")
            if len(s) > 2:
                print(f"    - {s}")
        except:
            pass

if __name__ == "__main__":
    caminho = "save/preferences.bin"
    if len(sys.argv) > 1:
        caminho = sys.argv[1]
    auditar_preferencias(caminho)
