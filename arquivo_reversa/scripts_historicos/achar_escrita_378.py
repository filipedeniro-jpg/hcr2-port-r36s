#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    print("Buscando escritas em [x19, #0x378] ou [rx, #0x378]:")
    for pc in range(0x0a1a000, 0x0a1e000, 4):
        palavra = int.from_bytes(dados[pc:pc+4], "little")
        # str sx, [rx, #0x378] ou str xx, [rx, #0x378]
        # str (immediate) em ARM64
        bloco = dados[pc:pc+4]
        for ins in md.disasm(bloco, pc):
            if "378" in ins.op_str and ("str" in ins.mnemonic):
                print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
