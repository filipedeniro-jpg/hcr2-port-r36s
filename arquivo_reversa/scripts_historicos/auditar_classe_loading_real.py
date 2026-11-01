# -*- coding: utf-8 -*-
"""
Audita todas as strings e chamadas de 0xa154b0 a 0xa15f00
"""
import os
import struct
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
trecho = data[0x0a154b0:0x0a15f00]

# Procura ADRP+ADD
for addr in range(0x0a154b0, 0x0a15f00, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    if (w & 0x9f000000) == 0x90000000:
        immlo = (w >> 29) & 3
        immhi = (w >> 5) & 0x7ffff
        imm = (immhi << 2) | immlo
        if imm & (1 << 20):
            imm -= (1 << 21)
        pc_page = addr & ~0xfff
        adrp_page = pc_page + (imm << 12)
        w_next = struct.unpack("<I", data[addr+4:addr+8])[0]
        if (w_next & 0xffc00000) == 0x91000000:
            add_imm = (w_next >> 10) & 0xfff
            target_str = adrp_page + add_imm
            if target_str > 0 and target_str < len(data):
                fim = data.find(b'\x00', target_str)
                s = data[target_str:fim].decode('ascii', errors='replace')
                if len(s) > 1 and len(s) < 80:
                    print(f"0x{addr:x} -> String em 0x{target_str:x}: '{s}'")
