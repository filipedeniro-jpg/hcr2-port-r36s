#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    for off in [0x16827b0, 0x1682808, 0x1682860, 0x16828b8]:
        # Ler string em off + 0x10 (ou off)
        ptr = off + 0x10
        fim = dados.find(b"\x00", ptr)
        s = dados[ptr:fim].decode("utf-8", errors="replace")
        print(f"String em 0x{off:x} (+0x10 = 0x{ptr:x}): '{s}'")

if __name__ == "__main__":
    main()
