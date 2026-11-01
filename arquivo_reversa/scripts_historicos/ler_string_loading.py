# -*- coding: utf-8 -*-
import os

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

off = 0x1232871
fim = data.find(b'\x00', off)
print(f"String em 0x{off:x}: '{data[off:fim].decode('ascii', errors='replace')}'")
