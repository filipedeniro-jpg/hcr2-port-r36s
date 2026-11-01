#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
caminho = os.path.join(DIR_RAIZ, "hcr2_classico", "descompilado_android", "sources", "com", "fingersoft", "hcr2", "AppActivity.java")

with open(caminho, "r", encoding="utf-8", errors="ignore") as f:
    linhas = f.readlines()

print("".join(linhas[450:510]))
