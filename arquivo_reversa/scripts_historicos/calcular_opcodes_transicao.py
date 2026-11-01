#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    # 1. Opcode para add x8, x8, #0x8b8 (trocar cena para Garagem em 0x0a1da64)
    # add rd, rn, #imm12 -> 0x91000000 | (imm12 << 10) | (rn << 5) | rd
    # imm12 = 0x8b8, rn = 8, rd = 8
    op_garage = 0x91000000 | (0x8b8 << 10) | (8 << 5) | 8
    print(f"add x8, x8, #0x8b8 -> Opcode: 0x{op_garage:08x}")
    for ins in md.disasm(op_garage.to_bytes(4, "little"), 0x0a1da64):
        print(f"  Validado: {ins.mnemonic} {ins.op_str}")

    # 2. Dobrar o progresso em 0x0a1db50:
    # Original em 0x0a1db50: add x8, x8, x10 (0x8b0a0108)
    # Se dobrarmos: lsl x8, x8, #1
    # lsl xd, xn, #shift -> ubfm xd, xn, #(64-shift), #(63-shift)
    # lsl x8, x8, #1 -> 0xd37ff108
    # Ou add x8, x8, x8 -> 0x8b080108
    op_dobrar = 0x8b080108 # add x8, x8, x8
    print(f"\nadd x8, x8, x8 (dobra progresso para 100%) -> Opcode: 0x{op_dobrar:08x}")
    for ins in md.disasm(op_dobrar.to_bytes(4, "little"), 0x0a1db50):
        print(f"  Validado: {ins.mnemonic} {ins.op_str}")

if __name__ == "__main__":
    main()
