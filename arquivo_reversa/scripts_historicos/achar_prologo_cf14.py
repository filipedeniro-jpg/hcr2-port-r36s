#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    for p in range(0x0a1c000, 0x0a1cf00, 4):
        palavra = int.from_bytes(dados[p:p+4], "little")
        # stp ..., [sp, -xxx]!
        if (palavra & 0xffc003e0) == 0xa9bf03e0:
            bloco = dados[p:p+32]
            for ins in md.disasm(bloco, p):
                if "stp" in ins.mnemonic and "sp, #-" in ins.op_str:
                    print(f"Possivel prologo em 0x{ins.address:07x}: {ins.mnemonic} {ins.op_str}")

if __name__ == "__main__":
    main()
