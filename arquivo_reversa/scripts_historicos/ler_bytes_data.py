#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    for off in [0x16827b0, 0x1682808, 0x1682860, 0x16828b8]:
        bloco = dados[off:off+32]
        print(f"0x{off:x}: {bloco.hex()}")
        # Se contiver ponteiro
        qwords = [int.from_bytes(bloco[i:i+8], "little") for i in range(0, len(bloco), 8)]
        print(f"  QWORDS: {[hex(q) for q in qwords]}")

if __name__ == "__main__":
    main()
