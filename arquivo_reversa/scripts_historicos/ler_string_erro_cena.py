#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    off = 0x1241463
    fim = dados.find(b"\x00", off)
    s = dados[off:fim].decode("utf-8", errors="replace")
    print(f"String em 0x{off:x}: '{s}'")

if __name__ == "__main__":
    main()
