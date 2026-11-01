# -*- coding: utf-8 -*-
"""
Mapeia as sub-rotinas de AnimatedSplash (0x00A1B000 a 0x00A1DC00)
"""
import os
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# Detectar prologues de funcoes ARM64 (stp x29, x30 ou stp ..., [sp, #-...]!)
funcoes = []
for addr in range(0x00A1B000, 0x00A1DC00, 4):
    w = int.from_bytes(data[addr:addr+4], 'little')
    # stp x29, x30, [sp, #-...]! -> 0xa9b... or similar
    # stp ..., ..., [sp, #-...]!
    if (w & 0xffc003e0) == 0xa98003e0 or (w & 0xffc003e0) == 0x298003e0:
        funcoes.append(addr)
    elif (w & 0xffe003ff) == 0xd10003ff: # sub sp, sp, #...
        funcoes.append(addr)

print(f"Prologues provaveis de funcoes em AnimatedSplash (0x00A1B000 a 0x00A1DC00): {len(funcoes)}")
for fn in funcoes:
    print(f"  Funcao em 0x{fn:07x}")
