#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    print("Procurando prologo de AnimatedSplash::update (antes de 0x0a1d600):")
    # Buscar stp x29, x30, [sp, ...]
    for p in range(0x0a1d200, 0x0a1d600, 4):
        palavra = int.from_bytes(dados[p:p+4], "little")
        # stp x29, x30, [sp, -xxx]!
        if (palavra & 0xffc003e0) == 0xa98003e0 or (palavra & 0xffc003e0) == 0xa9bf03e0:
            print(f"Possivel prologo em: 0x{p:x}")
            bloco = dados[p:p+64]
            for ins in md.disasm(bloco, p):
                print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")
            break

if __name__ == "__main__":
    main()
