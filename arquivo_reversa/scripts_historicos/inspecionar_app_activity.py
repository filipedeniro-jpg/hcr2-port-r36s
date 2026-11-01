#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
inspecionar_app_activity.py - Extrai os métodos nativos e fluxo de boot de AppActivity.java.
"""

import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_APP_ACT = os.path.join(DIR_RAIZ, "hcr2_classico", "descompilado_android", "com", "fingersoft", "hcr2", "AppActivity.java")

def inspecionar():
    if not os.path.exists(CAMINHO_APP_ACT):
        # Busca recursiva caso esteja em outro caminho
        for r, _, files in os.walk(os.path.join(DIR_RAIZ, "hcr2_classico", "descompilado_android")):
            if "AppActivity.java" in files:
                caminho = os.path.join(r, "AppActivity.java")
                break
        else:
            print("[-] AppActivity.java nao encontrado.")
            return
    else:
        caminho = CAMINHO_APP_ACT

    with open(caminho, "r", encoding="utf-8", errors="ignore") as f:
        linhas = f.readlines()

    print(f"=== METODOS NATIVOS EM {os.path.basename(caminho)} ({len(linhas)} linhas) ===")
    for i, l in enumerate(linhas):
        if "native " in l:
            print(f"L{i+1}: {l.strip()}")

    print("\n=== CHAMADAS A onFinishedLaunching ou onConfigurationCompleted ===")
    for i, l in enumerate(linhas):
        if "onFinishedLaunching" in l or "onConfigurationCompleted" in l or "onDataRead" in l:
            print(f"L{i+1}: {l.strip()}")

if __name__ == "__main__":
    inspecionar()
