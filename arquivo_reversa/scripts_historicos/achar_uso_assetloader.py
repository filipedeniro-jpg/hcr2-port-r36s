#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# Desmonta AnimatedSplash (0xA18000 a 0xA1B000) procurando instrucoes que referenciem 0x11F8090
# 0x11F8090 -> adrp xN, 0x11F8000; add xN, xN, 0x90
bloco = dados[0xA18000:0xA1B000]

for insn in cs.disasm(bloco, 0xA18000):
    if insn.mnemonic == "add" and ("0x90" in insn.op_str or "#0x90" in insn.op_str):
        print(f"0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
