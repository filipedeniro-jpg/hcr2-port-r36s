#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import re

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DIR_DESCOMPILADO = os.path.join(DIR_RAIZ, "hcr2_classico", "descompilado_android")

print("=== BUSCA POR 50% OU PROGRESSO DE LOADING ===")
# Procura ocorrencias de 50 ou 0.5f em arquivos que mencionem loading ou splash
for r, _, files in os.walk(DIR_DESCOMPILADO):
    for f in files:
        if f.endswith(".java"):
            c = os.path.join(r, f)
            with open(c, "r", encoding="utf-8", errors="ignore") as jf:
                conteudo = jf.read()
                if "50" in conteudo and ("load" in conteudo.lower() or "splash" in conteudo.lower() or "progress" in conteudo.lower()):
                    for l in conteudo.splitlines():
                        if ("50" in l or "0.5" in l) and ("progress" in l.lower() or "percent" in l.lower() or "bar" in l.lower() or "loading" in l.lower()):
                            print(f"{f}: {l.strip()}")
