# -*- coding: utf-8 -*-
"""
Auditoria de 0x0a1dae0 a 0x0a1db70
"""
import os
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
trecho = data[0x0a1dae0:0x0a1db70]
print("\n--- Desmontagem de 0x0a1dae0 a 0x0a1db70 ---")
for insn in cs.disasm(trecho, 0x0a1dae0):
    print(f"0x{insn.address:07x}: {insn.mnemonic:<8} {insn.op_str}")
