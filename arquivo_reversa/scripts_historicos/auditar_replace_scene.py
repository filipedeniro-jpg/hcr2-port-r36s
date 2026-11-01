# -*- coding: utf-8 -*-
"""
Auditoria da classe que possui replaceScene
"""
import os
import struct

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

# Ver contexto ao redor de 0x1241b72
start = max(0, 0x1241b72 - 120)
end = min(len(data), 0x1241bf7 + 120)
print("Contexto ao redor:")
print(data[start:end])

# Buscar referencias de ADRP/ADD para 0x1241b72 ou a funcao correspondente
# Vamos buscar o símbolo da função no binário
pos = data.find(b"TransitionManager")
while pos != -1:
    print(f"TransitionManager em: 0x{pos:x}")
    pos = data.find(b"TransitionManager", pos + 17)

pos = data.find(b"SceneManager")
while pos != -1:
    print(f"SceneManager em: 0x{pos:x}")
    pos = data.find(b"SceneManager", pos + 12)
