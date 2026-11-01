#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DIR_ASSETS = os.path.join(DIR_RAIZ, "hcr2_classico", "assets")

print("=== ARQUIVOS CSB RELACIONADOS A LOADING / SPLASH / INTRO ===")
for r, _, files in os.walk(DIR_ASSETS):
    for f in files:
        if f.endswith(".csb"):
            fn = f.lower()
            if any(k in fn for k in ["load", "splash", "intro", "progress", "wait", "start"]):
                rel = os.path.relpath(os.path.join(r, f), DIR_ASSETS)
                print(rel)
