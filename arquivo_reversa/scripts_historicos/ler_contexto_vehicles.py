#!/usr/bin/env python3
"""
Le strings e contexto ao redor de 0x11f8105
"""
from pathlib import Path

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")

def main():
    data = SO_PATH.read_bytes()
    inicio = max(0, 0x11F8105 - 100)
    fim = min(len(data), 0x11F8105 + 200)
    print(f"Contexto entre 0x{inicio:X} e 0x{fim:X}:")
    trecho = data[inicio:fim]
    print(trecho)

if __name__ == "__main__":
    main()
