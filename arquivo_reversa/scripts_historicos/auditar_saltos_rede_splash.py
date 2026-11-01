# -*- coding: utf-8 -*-
"""
Auditoria em lote das checagens de rede em AnimatedSplash::update (0x0a1d920 a 0x0a1d9d8)
"""
import os
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# Desmonta exatamente de 0x0a1d920 a 0x0a1d9e0
trecho = data[0x0a1d920:0x0a1d9e0]
print("--- Janela Critica de Checagem de Rede (0x0a1d920 a 0x0a1d9e0) ---")
for insn in cs.disasm(trecho, 0x0a1d920):
    print(f"0x{insn.address:07x}: 0x{int.from_bytes(insn.bytes, 'little'):08x} | {insn.mnemonic:<8} {insn.op_str}")

# O objetivo e:
# Se estamos em 0x0a1d928 (apos bl 0x6c0cf4):
# Em vez de esperar pelo servidor (Waiting for challenge response) ou checar flags de rede em 0x95c/0x968:
# Desviar DIRETO incondicionalmente para 0x0a1d9d8 (a execucao da transicao)!
# Vamos calcular o branch incondicional B de 0x0a1d928 para 0x0a1d9d8:
diff = 0x0a1d9d8 - 0x0a1d928 # +0xb0
imm26 = (diff >> 2) & 0x03ffffff
b_opcode = 0x14000000 | imm26
print(f"\n[HOTPATCH PROPOSTO]:")
print(f"Substituir em 0x0a1d928: 'tbz w20, #0, #0xa1d93c' -> 'b #0xa1d9d8' (Opcode: 0x{b_opcode:08x})")
print(f"Isso pula: 'Waiting for challenge response...', todas as checagens de pendencias de rede,")
print(f"e cai DIRETO no bloco executivo de transicao (0x0a1d9d8) que troca de cena no Cocos2d-x!")
