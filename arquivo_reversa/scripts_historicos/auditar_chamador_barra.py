# -*- coding: utf-8 -*-
"""
Auditoria do chamador 0xa15a6c e 0xbbb4e8
"""
import os
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

for addr in [0xa15a6c, 0xbbb4e8]:
    print(f"\n--- Janela ao redor de 0x{addr:x} ---")
    start = max(0, addr - 0x30)
    end = min(len(data), addr + 0x30)
    for insn in cs.disasm(data[start:end], start):
        prefix = " => " if insn.address == addr else "    "
        print(f"{prefix}0x{insn.address:07x}: {insn.mnemonic:<8} {insn.op_str}")
