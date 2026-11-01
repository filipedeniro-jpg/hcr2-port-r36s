# -*- coding: utf-8 -*-
import os

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LOG_PATH = os.path.join(DIR_RAIZ, "hcr2_classico", "arquivo_reversa", "primeiro_log_console.txt")

with open(LOG_PATH, "r", encoding="utf-8", errors="replace") as f:
    linhas = f.readlines()

print(f"Total de linhas no log do novo IP: {len(linhas)}")
print("--- Primeiras 35 linhas ---")
for l in linhas[:35]:
    print(l.strip())
