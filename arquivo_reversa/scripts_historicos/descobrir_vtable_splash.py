#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

# O addend eh 0x016D8CD0
for addend in [0x016D8CD0]:
    ptr = struct.pack("<Q", addend)
    pos = [m for m in range(0, len(dados) - 8, 8) if dados[m:m+8] == ptr]
    print(f"Ponteiro/Addend para 0x{addend:08X}: {[hex(p) for p in pos]}")
    for p in pos:
        r_offset = struct.unpack("<Q", dados[p-16:p-8])[0]
        r_info = struct.unpack("<Q", dados[p-8:p])[0]
        print(f"  Entrada rela: r_offset=0x{r_offset:08X}, r_info=0x{r_info:08X}, r_addend=0x{addend:08X}")
        print(f"  ==> VTABLE DE AnimatedSplash ESTA EM 0x{r_offset:08X}!")
