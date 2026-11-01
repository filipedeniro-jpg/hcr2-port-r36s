# -*- coding: utf-8 -*-
"""
Validação matemática do opcode de cbnz w8 para o epilogo de AnimatedSplash::update
"""
import capstone

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

orig_addr = 0x00A1D83C
dest_addr = 0x00A1DB84 # epilogo que desempilha registradores e da ret

diff = dest_addr - orig_addr
imm19 = (diff >> 2) & 0x7ffff
# cbnz w8, label -> 0x35000000 | (imm19 << 5) | reg (w8 = 8)
opcode = 0x35000008 | (imm19 << 5)

raw_bytes = opcode.to_bytes(4, 'little')
for insn in cs.disasm(raw_bytes, orig_addr):
    print(f"0x{insn.address:07x}: 0x{opcode:08x} -> {insn.mnemonic} {insn.op_str}")

# Alem disso, se quisermos forcar o nó do splash a ficar invisivel:
# Em 0x0a1d758:
# 0x0a1d758: mov w1, #1 ; blr x8 (setVisible(true))
# Se mudarmos para:
# 0x0a1d758: mov w1, wzr ; blr x8 (setVisible(false)!)
# Opcode de mov w1, wzr -> 0x2a1f03e1
# Isso torna a camada do assetloader invisivel!
mov_w1_wzr = 0x2a1f03e1
for insn in cs.disasm(mov_w1_wzr.to_bytes(4, 'little'), 0x0a1d758):
    print(f"0x{insn.address:07x}: 0x{mov_w1_wzr:08x} -> {insn.mnemonic} {insn.op_str}")
