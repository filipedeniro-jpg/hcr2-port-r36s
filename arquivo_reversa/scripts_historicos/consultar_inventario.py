# -*- coding: utf-8 -*-
"""
Consulta os simbolos do inventario_v1_28.json
"""
import os
import json

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_INV = os.path.join(DIR_RAIZ, "hcr2_classico", "arquivo_reversa", "inventario_v1_28.json")

with open(CAMINHO_INV, "r", encoding="utf-8") as f:
    inv = json.load(f)

print(f"Total de itens no inventario: {len(inv)}")
for k, v in inv.items():
    if any(x in str(k).lower() or x in str(v).lower() for x in ["scene", "delegate", "director", "splash", "garage", "render"]):
        print(f"  {k} -> {v}")
