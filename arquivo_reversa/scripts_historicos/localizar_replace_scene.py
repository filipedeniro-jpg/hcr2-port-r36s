# -*- coding: utf-8 -*-
"""
Acha chamadores de 0xc039dc
"""
import os
import struct

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

target = 0xc039dc
for addr in range(0x350000, 0xd00000, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    # Se e BL
    if (w & 0xfc000000) == 0x94000000:
        imm = w & 0x03ffffff
        if imm & (1 << 25):
            imm -= (1 << 26)
        dest = addr + (imm << 2)
        if dest == target:
            print(f"Chamador de 0xc039dc em: 0x{addr:x}")

# Vamos ver tambem onde a string "TransitionManager forcing to main menu" ou a flag [x19, #0x99] e setada
# Quem escreve [x19, #0x99]?
# strb w..., [x..., #0x99]
for addr in range(0x350000, 0xd00000, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    # strb wn, [xm, #0x99] -> opcode 0x39000000 | (0x99 << 10)
    if (w & 0xffc00000) == 0x39026400: # 0x99 = 153 -> 153 << 10 = 0x26400
        print(f"STRB [reg, #0x99] em: 0x{addr:x} (instrucao 0x{w:08x})")
