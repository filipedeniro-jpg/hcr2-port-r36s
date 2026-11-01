#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    alvos = [0x16827b0, 0x1682808, 0x1682860, 0x16828b8]
    print("Buscando adrp + add para 0x1682000:")
    for pc in range(0, len(dados) - 8, 4):
        palavra = int.from_bytes(dados[pc:pc+4], "little")
        # adrp rx, #0x1682000
        if (palavra & 0x9f000000) == 0x90000000:
            immhi = (palavra >> 5) & 0x7ffff
            immlo = (palavra >> 29) & 3
            imm = (immhi << 2) | immlo
            if imm & 0x100000:
                imm -= 0x200000
            page = (pc & ~0xfff) + (imm << 12)
            if page == 0x1682000:
                p2 = int.from_bytes(dados[pc+4:pc+8], "little")
                # add rx, rx, #offset
                if (p2 & 0xff800000) == 0x91000000:
                    off12 = (p2 >> 10) & 0xfff
                    end_total = page + off12
                    if end_total in alvos:
                        print(f"  Acesso a 0x{end_total:x} em 0x{pc:x}")
                        bloco = dados[pc:pc+24]
                        for ins in md.disasm(bloco, pc):
                            print(f"    0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
