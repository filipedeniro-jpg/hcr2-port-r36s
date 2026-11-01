#!/usr/bin/env python3
"""
Busca ponteiros de 64 bits para 0x11F8105 no libcocos2dcpp.so
"""
import struct
from pathlib import Path

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")
TARGET_ADDR = 0x11F8105

def main():
    data = SO_PATH.read_bytes()
    alvo = struct.pack("<Q", TARGET_ADDR)
    pos = 0
    print(f"[BUSCA] Buscando ponteiro 0x{TARGET_ADDR:X}...")
    while True:
        pos = data.find(alvo, pos)
        if pos == -1:
            break
        print(f"  Ponteiro em 0x{pos:X}")
        pos += 8

if __name__ == "__main__":
    main()
