#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    f.seek(0x1241B00)
    bloco = f.read(512)

for pedaco in bloco.split(b"\x00"):
    if pedaco:
        try:
            print(pedaco.decode('ascii'))
        except Exception:
            pass
