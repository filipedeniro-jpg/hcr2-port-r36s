#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DIR_DESCOMPILADO = os.path.join(DIR_RAIZ, "hcr2_classico", "descompilado_android")

for r, _, files in os.walk(DIR_DESCOMPILADO):
    for f in files:
        if f in ["FingerAccount.java", "AccountManager.java"]:
            c = os.path.join(r, f)
            print(f"=== {f} ===")
            with open(c, "r", encoding="utf-8", errors="ignore") as jf:
                linhas = jf.readlines()
            for i, l in enumerate(linhas):
                if any(k in l for k in ["onDataRead", "onLoginSuccess", "onLoginError", "onLoginCancel", "native", "init("]):
                    print(f"L{i+1}: {l.strip()}")
