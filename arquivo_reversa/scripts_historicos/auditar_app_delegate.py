# -*- coding: utf-8 -*-
import os

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

print(f"Tamanho do SO: {len(data)} (hex: 0x{len(data):x})")
off = 0x11842d0
trecho = data[off:off+32]
print(f"Bytes em 0x{off:x}: {trecho.hex()}")
