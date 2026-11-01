#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# No ARM64: adrp xD, #0x11f8000; add xD, xD, #0x35a
# Vamos escanear instrucoes que carregam o offset 0x35a
import struct

alvo_off = 0x35a
# Opcode de add xD, xN, #0x35a: bits [21:10] contem imm12 = 0x35a = 0011 0101 1010
for p in range(0x365000, 0x117F000, 4):
    w = struct.unpack("<I", dados[p:p+4])[0]
    # add reg64 imm: 0x91000000 | (imm12 << 10)
    if (w & 0xFFC00000) == 0x91000000:
        imm12 = (w >> 10) & 0xFFF
        if imm12 == alvo_off:
            # Verifica se a instrucao anterior eh adrp para 0x11F8000
            w_ant = struct.unpack("<I", dados[p-4:p])[0]
            for insn in cs.disasm(dados[p-4:p+4], p-4):
                print(f"0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
