#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

for alvo in [0x011F8070, 0x011F8072]:
    ptr = struct.pack("<Q", alvo)
    pos = [m for m in range(0, len(dados) - 8, 8) if dados[m:m+8] == ptr]
    print(f"Ponteiro para 0x{alvo:08X}: {[hex(p) for p in pos]}")
    for p in pos:
        # Mostra o contexto de 64 bytes ao redor
        print(f"Contexto em 0x{p:X}:")
        for i in range(-16, 48, 8):
            val = struct.unpack("<Q", dados[p+i:p+i+8])[0]
            print(f"  [0x{p+i:08X}] 0x{val:016X}")
