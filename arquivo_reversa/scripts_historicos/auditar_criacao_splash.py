# -*- coding: utf-8 -*-
"""
Busca por ADRP + ADD para 0x0a1ae80
"""
import os
import struct

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

target = 0x0a1ae80
target_page = target & ~0xfff
target_off = target & 0xfff

for addr in range(0, len(data) - 8, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    if (w & 0x9f000000) == 0x90000000:
        immlo = (w >> 29) & 3
        immhi = (w >> 5) & 0x7ffff
        imm = (immhi << 2) | immlo
        if imm & (1 << 20):
            imm -= (1 << 21)
        pc_page = addr & ~0xfff
        adrp_page = pc_page + (imm << 12)
        if adrp_page == target_page:
            w_next = struct.unpack("<I", data[addr+4:addr+8])[0]
            if (w_next & 0xffc00000) == 0x91000000:
                add_imm = (w_next >> 10) & 0xfff
                if add_imm == target_off:
                    print(f"Referencia ADRP+ADD a 0x{target:x} em: 0x{addr:x}")
