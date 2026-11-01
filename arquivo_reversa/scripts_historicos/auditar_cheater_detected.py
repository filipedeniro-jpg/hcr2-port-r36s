#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auditar_cheater_detected.py - Auditoria 100% offline para rastrear onde a string
'Internal data corrupted!' e 'cheater_detected' sao referenciadas no codigo nativo (.text).
Usa ADRP / ADD decoding em ARM64.
"""

import os
import struct

DIR_SO = os.path.join(os.path.dirname(__file__), "..", "hcr2_classico", "binario_so", "libcocos2dcpp.so")

def decodificar_adrp_add(d1, d2, pc):
    # ADRP
    immlo = (d1 >> 29) & 0x3
    immhi = (d1 >> 5) & 0x7FFFF
    imm = (immhi << 2) | immlo
    if imm & 0x100000:
        imm -= 0x200000
    base = (pc & ~0xFFF) + (imm << 12)
    # ADD
    imm12 = (d2 >> 10) & 0xFFF
    return base + imm12

def auditar():
    with open(DIR_SO, "rb") as f:
        dados = f.read()

    alvo = 0x01184134 # Internal data corrupted!
    alvo_page = alvo & ~0xFFF
    alvo_offset = alvo & 0xFFF

    print(f"[*] Buscando referencias ARM64 para 0x{alvo:08x} (.rodata)...")
    
    # Varre a secao .text (instrucoes de 4 bytes)
    for pos in range(0, min(len(dados) - 8, 0x01100000), 4):
        i1 = struct.unpack("<I", dados[pos:pos+4])[0]
        # Verifica se i1 eh ADRP (bits 31:24 == 1x010000)
        if (i1 & 0x9F000000) == 0x90000000:
            rd1 = i1 & 0x1F
            i2 = struct.unpack("<I", dados[pos+4:pos+8])[0]
            # Verifica se i2 eh ADD imediato 64-bit com mesmo registrador
            if (i2 & 0xFF800000) == 0x91000000:
                rd2 = i2 & 0x1F
                rn2 = (i2 >> 5) & 0x1F
                if rn2 == rd1:
                    endereco = decodificar_adrp_add(i1, i2, pos)
                    if abs(endereco - alvo) < 64:
                        print(f"[ACHOU!] Referencia em 0x{pos:08x} aponta para 0x{endereco:08x}")

if __name__ == "__main__":
    auditar()
