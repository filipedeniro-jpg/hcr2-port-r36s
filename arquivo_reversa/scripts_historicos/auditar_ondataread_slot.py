#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auditar_ondataread_slot.py - Auditoria 100% offline para inspecionar os chamadores de
'onDataRead slot %d data %s' e identificar o que a função espera nos parâmetros de onDataRead.
"""

import os
import sys

DIR_SO = os.path.join(os.path.dirname(__file__), "..", "hcr2_classico", "binario_so", "libcocos2dcpp.so")

def auditar():
    with open(DIR_SO, "rb") as f:
        dados = f.read()

    # Offset da string 'onDataRead slot %d data %s'
    pos_str = dados.find(b"onDataRead slot %d data %s")
    print(f"[*] String 'onDataRead slot %d data %s' encontrada em 0x{pos_str:08x}")
    
    # Procura referências à string no binário (ponteiros de 64 bits ou literais)
    # No ARM64, strings em .rodata são referenciadas via ADRP + ADD
    # Vamos salvar o trecho em volta de 0x01184126 (BAD NEWS! Internal data corrupted!)
    pos_bad = 0x01184126
    print(f"\n[*] Strings vizinhas de 0x{pos_bad:08x}:")
    bloco = dados[pos_bad - 100 : pos_bad + 250]
    partes = bloco.split(b"\x00")
    for p in partes:
        if p.strip():
            print(f"   -> {p.decode('latin1', errors='ignore')}")

if __name__ == "__main__":
    auditar()
