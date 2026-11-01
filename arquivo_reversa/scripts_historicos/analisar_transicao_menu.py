#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
analisar_transicao_menu.py - Analisa no Java descompilado como o HCR2 inicializa e carrega a tela principal.
"""

import os
import re

JAVA_DIR = r"d:\Documentos\Ports R36S\hcr2_classico\descompilado_android\sources"

def buscar_ocorrencias():
    palavras = ["onDataRead", "onConfigurationCompleted", "onFinishedAssetLoader", "MainMenu", "Garage", "LoadingScene", "SplashScene"]
    resultados = {p: [] for p in palavras}
    
    for raiz, _, arquivos in os.walk(JAVA_DIR):
        for arq in arquivos:
            if not arq.endswith(".java"):
                continue
            caminho = os.path.join(raiz, arq)
            try:
                with open(caminho, "r", encoding="utf-8", errors="ignore") as f:
                    linhas = f.readlines()
                for num, linha in enumerate(linhas, 1):
                    for p in palavras:
                        if p in linha:
                            resultados[p].append((arq, num, linha.strip()))
            except Exception as e:
                pass

    print("=== RESULTADOS DA PESQUISA JAVA HCR2 ===")
    for p, items in resultados.items():
        print(f"\n--- Termo: {p} ({len(items)} ocorrencias) ---")
        for arq, num, linha in items[:5]:
            print(f"  {arq}:{num}: {linha[:120]}")

if __name__ == "__main__":
    buscar_ocorrencias()
