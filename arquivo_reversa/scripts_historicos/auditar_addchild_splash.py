# -*- coding: utf-8 -*-
"""
Inspeciona exatamente o addChild do assetloader.csb em 0x0a1b0d0 a 0x0a1b100
"""
import os
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
trecho = data[0x0a1b0d0:0x0a1b100]

print("--- Desmontagem de 0x0a1b0d0 a 0x0a1b100 ---")
for insn in cs.disasm(trecho, 0x0a1b0d0):
    print(f"0x{insn.address:07x}: 0x{int.from_bytes(insn.bytes, 'little'):08x} | {insn.mnemonic:<8} {insn.op_str}")
