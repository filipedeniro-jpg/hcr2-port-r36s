#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    for off in [0x00A1D978, 0x00A1D97C, 0x00A1DA64, 0x00A1DB50]:
        bloco = dados[off:off+4]
        op = int.from_bytes(bloco, "little")
        for ins in md.disasm(bloco, off):
            print(f"0x{off:x}: 0x{op:08x} -> {ins.mnemonic} {ins.op_str}")

if __name__ == "__main__":
    main()
