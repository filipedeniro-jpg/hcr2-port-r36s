#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
caminho = os.path.join(DIR_RAIZ, "hcr2_classico", "assets", "Common", "ui", "cocosstudio", "assetloader.csb")

with open(caminho, "rb") as f:
    dados = f.read()

print(f"Tamanho de assetloader.csb: {len(dados)} bytes")
# Extrai strings ASCII de 3 ou mais caracteres
import re
strings = re.findall(b"[A-Za-z0-9_/.-]{3,}", dados)
print("=== STRINGS NO ASSETLOADER.CSB ===")
for s in strings:
    print(s.decode('latin1'))
