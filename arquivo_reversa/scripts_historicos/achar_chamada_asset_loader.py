#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import re
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

termo = b"onFinishedAssetLoader"
pos = dados.find(termo)
print(f"String '{termo.decode()}' em 0x{pos:X}")

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# No ARM64, adrp pagina = pos & ~0xFFF, add offset = pos & 0xFFF
pag = pos & ~0xFFF
off = pos & 0xFFF
print(f"Pagina: 0x{pag:X}, Offset: 0x{off:X}")

# Procura instrucoes que usem off
for p in range(0x365000, len(dados) - 4, 4):
    inst_bytes = dados[p:p+4]
    for insn in cs.disasm(inst_bytes, p):
        if insn.mnemonic == "add" and f"#{hex(off)}" in insn.op_str:
            print(f"Encontrado em 0x{insn.address:08X}: {insn.mnemonic} {insn.op_str}")
