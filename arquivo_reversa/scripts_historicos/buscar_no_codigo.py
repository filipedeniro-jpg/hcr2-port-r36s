#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DIR_CODIGO = os.path.join(DIR_RAIZ, "hcr2_classico", "codigo")

print("=== BUSCA POR onFinishedAssetLoader EM CODIGO ===")
for f in os.listdir(DIR_CODIGO):
    if f.endswith((".c", ".h")):
        c = os.path.join(DIR_CODIGO, f)
        with open(c, "r", encoding="utf-8", errors="ignore") as cf:
            linhas = cf.readlines()
            for i, l in enumerate(linhas):
                if "onFinishedAssetLoader" in l:
                    print(f"{f}:{i+1}: {l.strip()}")
