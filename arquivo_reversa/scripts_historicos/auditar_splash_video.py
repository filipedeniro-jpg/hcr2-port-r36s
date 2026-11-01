# -*- coding: utf-8 -*-
"""
Auditoria das strings splash.gif e splash-video no libcocos2dcpp.so
"""
import os
import struct
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

for term in [b"splash/splash.gif", b"splash-video", b"splash/"]:
    pos = 0
    while True:
        pos = data.find(term, pos)
        if pos == -1:
            break
        print(f"'{term.decode()}' encontrado em 0x{pos:x}")
        # Acha quem referencia pos via ADRP+ADD
        page = pos & ~0xfff
        off = pos & 0xfff
        for addr in range(0x350000, 0xd00000, 4):
            w = struct.unpack("<I", data[addr:addr+4])[0]
            if (w & 0x9f000000) == 0x90000000:
                immlo = (w >> 29) & 3
                immhi = (w >> 5) & 0x7ffff
                imm = (immhi << 2) | immlo
                if imm & (1 << 20):
                    imm -= (1 << 21)
                pc_page = addr & ~0xfff
                if pc_page + (imm << 12) == page:
                    w_next = struct.unpack("<I", data[addr+4:addr+8])[0]
                    if (w_next & 0xffc00000) == 0x91000000:
                        if ((w_next >> 10) & 0xfff) == off:
                            print(f"  Referenciado por ADRP+ADD em 0x{addr:x}")
                            for insn in cs.disasm(data[addr-0x10:addr+0x40], addr-0x10):
                                print(f"    0x{insn.address:07x}: {insn.mnemonic:<8} {insn.op_str}")
        pos += len(term)
