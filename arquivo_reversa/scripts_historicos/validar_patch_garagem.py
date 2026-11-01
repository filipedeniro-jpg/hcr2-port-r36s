#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

addr = 0x00A1D834
raw = struct.unpack("<I", dados[addr:addr+4])[0]
print(f"Endereco: 0x{addr:08X}")
print(f"Opcode Atual: 0x{raw:08X}")

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
for insn in cs.disasm(dados[addr:addr+4], addr):
    print(f"Desmontagem: {insn.mnemonic} {insn.op_str}")
