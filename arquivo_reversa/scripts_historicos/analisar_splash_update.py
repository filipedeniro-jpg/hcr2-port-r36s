#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
bloco = dados[0xA1D600:0xA1DC00]

print("=== FUNCAO AnimatedSplash::update (0xA1D600 - 0xA1DC00) ===")
for insn in cs.disasm(bloco, 0xA1D600):
    if insn.mnemonic in ["bl", "blr", "b", "ret", "cbz", "cbnz", "tbz", "tbnz"] or insn.address >= 0xA1DAE0:
        print(f"0x{insn.address:08X}: {insn.mnemonic:6} {insn.op_str}")
