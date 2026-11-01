#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    for alvo in [0x00a1ae60, 0x00bff89c, 0x00bfe1c8]:
        ptr_bytes = alvo.to_bytes(8, "little")
        pos = 0
        while True:
            pos = dados.find(ptr_bytes, pos)
            if pos == -1:
                break
            print(f"Ponteiro 0x{alvo:x} encontrado em: 0x{pos:x}")
            pos += 8

if __name__ == "__main__":
    main()
