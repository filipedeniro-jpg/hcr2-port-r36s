#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Auditoria Offline da Transição de Cena (AnimatedSplash -> GarageScene)
Analisa as funções chamadas por AnimatedSplash::update (0x00A1D600 a 0x00A1DC00)
e localiza a chamada de transição de cena no Cocos2d-x (replaceScene).
"""

import sys
import struct
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    if not caminho_so.exists():
        print(f"Erro: {caminho_so} nao encontrado.")
        return 1

    dados = caminho_so.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)

    # 1. Analisar 0x00bfe1c8 (chamado em 0x0a1d9b4, 0x0a1da84, 0x0a1dae4)
    print("=== [AUDITORIA] Função 0x00bfe1c8 ===")
    bloco = dados[0x00bfe1c8:0x00bfe1c8 + 256]
    for ins in md.disasm(bloco, 0x00bfe1c8):
        print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")
        if ins.mnemonic == "ret":
            break

    # 2. Analisar 0x00bff89c (chamado em 0x0a1d898)
    print("\n=== [AUDITORIA] Função 0x00bff89c ===")
    bloco_bff = dados[0x00bff89c:0x00bff89c + 256]
    for ins in md.disasm(bloco_bff, 0x00bff89c):
        print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")
        if ins.mnemonic == "ret":
            break

    # 3. Analisar AnimatedSplash::update (0x00a1d830 a 0x00a1d930) para ver onde ele desvia
    print("\n=== [AUDITORIA] Fluxo AnimatedSplash::update (0x00a1d830 a 0x00a1d940) ===")
    bloco_splash = dados[0x00a1d830:0x00a1d940]
    for ins in md.disasm(bloco_splash, 0x00a1d830):
        print(f"  0x{ins.address:07x}: {ins.mnemonic:<8} {ins.op_str}")

if __name__ == "__main__":
    main()
