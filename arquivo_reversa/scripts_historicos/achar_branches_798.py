#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    alvo = 0x0a1d798
    print(f"Buscando quem salta para 0x{alvo:x} dentro de AnimatedSplash::update (0xa1d000 a 0xa1e000):")
    for pc in range(0x0a1d000, 0x0a1e000, 4):
        palavra = int.from_bytes(dados[pc:pc+4], "little")
        # b ou bl
        if (palavra & 0x7c000000) == 0x14000000:
            imm26 = palavra & 0x03ffffff
            if imm26 & 0x02000000:
                imm26 -= 0x04000000
            dest = pc + (imm26 * 4)
            if dest == alvo:
                print(f"  Branch incondicional para 0x{alvo:x} em 0x{pc:x}")
        # b.cond
        elif (palavra & 0xff000010) == 0x54000000:
            imm19 = (palavra >> 5) & 0x7ffff
            if imm19 & 0x40000:
                imm19 -= 0x80000
            dest = pc + (imm19 * 4)
            if dest == alvo:
                print(f"  b.cond para 0x{alvo:x} em 0x{pc:x}")
        # cbz / cbnz
        elif (palavra & 0x7e000000) == 0x34000000:
            imm19 = (palavra >> 5) & 0x7ffff
            if imm19 & 0x40000:
                imm19 -= 0x80000
            dest = pc + (imm19 * 4)
            if dest == alvo:
                print(f"  cbz/cbnz para 0x{alvo:x} em 0x{pc:x}")
        # tbz / tbnz
        elif (palavra & 0x7e000000) == 0x36000000:
            imm14 = (palavra >> 5) & 0x3fff
            if imm14 & 0x2000:
                imm14 -= 0x4000
            dest = pc + (imm14 * 4)
            if dest == alvo:
                print(f"  tbz/tbnz para 0x{alvo:x} em 0x{pc:x}")

if __name__ == "__main__":
    main()
