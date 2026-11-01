#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import subprocess
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

# Usa llvm-readelf ou busca direta de strings
import re

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

termo = b"AnimatedSplash"
ocorrencias = [m.start() for m in re.finditer(re.escape(termo), dados)]
print(f"Ocorrencias de '{termo.decode()}': {len(ocorrencias)}")
for p in ocorrencias:
    snippet = dados[max(0, p-20):p+80].split(b"\x00")[0]
    print(f"  0x{p:X}: {snippet}")
