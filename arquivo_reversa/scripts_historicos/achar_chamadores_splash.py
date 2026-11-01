#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    alvo = 0x0a1ae60
    # Opcodes de BL para 0x0a1ae60:
    # imm26 = (alvo - pc) // 4
    # opcode = 0x94000000 | (imm26 & 0x03ffffff)
    print(f"Buscando chamadas para 0x{alvo:x}...")
    for pc in range(0, len(dados) - 4, 4):
        palavra = int.from_bytes(dados[pc:pc+4], "little")
        if (palavra & 0xfc000000) == 0x94000000: # BL
            imm26 = palavra & 0x03ffffff
            if imm26 & 0x02000000:
                imm26 -= 0x04000000
            dest = pc + (imm26 * 4)
            if dest == alvo:
                print(f"  BL para 0x{alvo:x} encontrado em 0x{pc:x}")

if __name__ == "__main__":
    main()
