#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    print("Desmontando AnimatedSplash::update inicio (0x00a1d600 a 0x00a1d7b0):")
    bloco = dados[0x00a1d600:0x00a1d7b0]
    for ins in md.disasm(bloco, 0x00a1d600):
        print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
