#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    # String "garage" que vimos em 0x0a1d860:
    # 0x0a1d858: mov w10, #0xc
    # 0x0a1d85c: mov w11, #0x6567 (ASCII 'ge')
    # 0x0a1d860: mov w12, #0x61720000 (ASCII 'ra')
    # 0x0a1d864: movk w12, #0x6167 (ASCII 'ga')
    # Isso monta a string "garage"!
    # E chama: 0x0a1d898: bl #0xbff89c
    print("Analisando chamadas para 0x00bff89c:")
    # Nós já vimos os chamadores: 0xa1d898, 0xa9960c, 0xbc51c8
    for chamador in [0x00a1d898, 0x00a9960c, 0x00bc51c8]:
        print(f"\n--- Chamador 0x{chamador:x} ---")
        bloco = dados[chamador - 32:chamador + 32]
        for ins in md.disasm(bloco, chamador - 32):
            marca = "=> " if ins.address == chamador else "   "
            print(f"{marca}0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
