# -*- coding: utf-8 -*-
import os
import struct
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

target = 0x125ef0b
page = target & ~0xfff
page_off = target & 0xfff

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

for addr in range(0xd00000, 0x1200000, 4):
    w = struct.unpack("<I", data[addr:addr+4])[0]
    if (w & 0x9f000000) == 0x90000000:
        immlo = (w >> 29) & 3
        immhi = (w >> 5) & 0x7ffff
        imm = (immhi << 2) | immlo
        if imm & (1 << 20):
            imm -= (1 << 21)
        pc_page = addr & ~0xfff
        adrp_page = pc_page + (imm << 12)
        if adrp_page == page:
            w_next = struct.unpack("<I", data[addr+4:addr+8])[0]
            if (w_next & 0xffc00000) == 0x91000000:
                add_imm = (w_next >> 10) & 0xfff
                if add_imm == page_off:
                    print(f"Referencia a DisplayLinkDirector em: 0x{addr:x}")
                    trecho = data[addr - 0x30 : addr + 0x60]
                    for insn in cs.disasm(trecho, addr - 0x30):
                        print(f"  0x{insn.address:07x}: {insn.mnemonic:<8} {insn.op_str}")
                    break
