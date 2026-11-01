#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    # Função 0x00bfe1c8 é a função que faz a transição (changeScene)!
    # Vamos ver quem chama 0x00bfe1c8 em todo o binário:
    alvo = 0x00bfe1c8
    print(f"Buscando todas as chamadas para 0x{alvo:x} no binário:")
    for pc in range(0, len(dados) - 4, 4):
        palavra = int.from_bytes(dados[pc:pc+4], "little")
        if (palavra & 0xfc000000) == 0x94000000: # BL
            imm26 = palavra & 0x03ffffff
            if imm26 & 0x02000000:
                imm26 -= 0x04000000
            dest = pc + (imm26 * 4)
            if dest == alvo:
                print(f"  BL para changeScene (0x{alvo:x}) em 0x{pc:x}")
                bloco = dados[pc-16:pc+16]
                for ins in md.disasm(bloco, pc-16):
                    marca = "=> " if ins.address == pc else "   "
                    print(f"    {marca}0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
