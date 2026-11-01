#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import re

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

for t in [b"loadingBar", b"loading_bar", b"setPercent"]:
    ocorr = [m.start() for m in re.finditer(re.escape(t), dados)]
    print(f"Termo '{t.decode()}': {len(ocorr)} ocorrencias")
    for p in ocorr:
        snippet = dados[p:p+50].split(b"\x00")[0]
        print(f"  0x{p:X}: {snippet}")
