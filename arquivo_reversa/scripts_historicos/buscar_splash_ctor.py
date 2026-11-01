#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

# No construtor, ele carrega o endereco da vtable: adrp x8, #0x16d8000; add x8, x8, #0x6a8
# Vamos procurar quem faz adrp para 0x16D8000
import capstone

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# Desmonta a regiao de 0xA18000 a 0xA1A800 procurando funcoes de AnimatedSplash
bloco = dados[0xA18000:0xA1A800]
print("Buscando metodos de AnimatedSplash entre 0xA18000 e 0xA1A800...")

for insn in cs.disasm(bloco, 0xA18000):
    if insn.mnemonic == "adrp" and "0x16d8000" in insn.op_str:
        print(f"0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
    elif insn.mnemonic == "adrp" and "0x11f8000" in insn.op_str:
        print(f"0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
