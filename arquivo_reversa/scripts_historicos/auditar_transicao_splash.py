#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auditar_transicao_splash.py - Investiga o gatilho de transicao de cena em AnimatedSplash.
"""

import os
import capstone

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    dados = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# Desmonta AnimatedSplash procurando instrucoes que chamem replaceScene ou pushScene
# Vamos achar o endereco de TransitionManager::replaceScene primeiro
termo = b"TransitionManager12replaceScene"
pos = dados.find(termo)
print(f"Mangled name de replaceScene em 0x{pos:X}")

# Procura o endereco das funcoes de TransitionManager
import re
tm_matches = [m.start() for m in re.finditer(b"TransitionManager", dados)]
print(f"Ocorrencias de TransitionManager: {len(tm_matches)}")
for p in tm_matches[:5]:
    snippet = dados[p:p+60].split(b"\x00")[0]
    print(f"  0x{p:X}: {snippet}")
