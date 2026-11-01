#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)
bloco = dados[0xA18000:0xA1B000]

print("=== CHAMADAS (BL/B) EM AnimatedSplash (0xA18000 - 0xA1B000) ===")
destinos = set()
for insn in cs.disasm(bloco, 0xA18000):
    if insn.mnemonic in ["bl", "b"] and insn.op_str.startswith("#0x"):
        dest = int(insn.op_str[3:], 16)
        destinos.add((insn.address, insn.mnemonic, dest))

# Vamos ver quais funcoes sao chamadas
for addr, mnem, dest in sorted(destinos):
    # Procura se o destino eh funcao conhecida de cocos2d ou do jogo
    print(f"0x{addr:08X}: {mnem:3} 0x{dest:08X}")
