# -*- coding: utf-8 -*-
"""
Quem mais chama a função de atualizar a barra de loading (0xd93c58)?
"""
import os
import struct

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

target = 0xd93c58
chamadores = []
for addr in range(0, len(data) - 4, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    if (w & 0xfc000000) == 0x94000000:
        imm = w & 0x03ffffff
        if imm & (1 << 25):
            imm -= (1 << 26)
        dest = addr + (imm << 2)
        if dest == target:
            chamadores.append(addr)

print(f"Total de chamadores da barra (0x{target:x}): {len(chamadores)}")
for c in chamadores:
    print(f"  Chamador em: 0x{c:x}")
