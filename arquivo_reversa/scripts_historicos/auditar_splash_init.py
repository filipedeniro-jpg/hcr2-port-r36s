# -*- coding: utf-8 -*-
import os

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

off = 0x11f8020
print("Bytes em 0x11f8020:", data[off:off+64])
# Ler string ASCII proxima
pos = data.find(b'\x00', off+8)
print("String apos 0x11f8028:", data[off+8:pos+30])
