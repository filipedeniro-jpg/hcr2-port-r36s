#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
caminho = os.path.join(DIR_RAIZ, "hcr2_classico", "arquivo_reversa", "primeiro_log_console.txt")

if os.path.exists(caminho):
    with open(caminho, "r", encoding="utf-8", errors="ignore") as f:
        linhas = f.readlines()
    print(f"Total de linhas no log: {len(linhas)}")
    print("=== ULTIMAS 50 LINHAS DO LOG ===")
    for l in linhas[-50:]:
        print(l.rstrip())
else:
    print("Log nao encontrado")
