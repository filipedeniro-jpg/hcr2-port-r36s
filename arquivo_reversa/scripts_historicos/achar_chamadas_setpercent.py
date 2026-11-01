#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
alvo = 0x00D93C58

print(f"=== CHAMADORES DE setPercent (0x{alvo:08X}) ===")
# Varre .text procurando bl alvo
for p in range(0x365000, 0x117F000, 4):
    inst = dados[p:p+4]
    for insn in cs.disasm(inst, p):
        if insn.mnemonic == "bl" and hex(alvo) in insn.op_str.lower():
            print(f"0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
