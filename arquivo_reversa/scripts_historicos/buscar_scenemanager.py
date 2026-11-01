#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import re

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

for t in [b"12SceneManager", b"SceneManager", b"_ZN12SceneManager"]:
    ocorr = [m.start() for m in re.finditer(re.escape(t), dados)]
    print(f"Termo '{t.decode()}': {len(ocorr)} ocorrencias")
    for p in ocorr[:10]:
        snippet = dados[p:p+70].split(b"\x00")[0]
        print(f"  0x{p:X}: {snippet}")
