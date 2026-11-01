#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Procurar quem usa a string "garage" (ASCII 'garage')
    alvo = b"garage\x00"
    p = 0
    while True:
        p = dados.find(alvo, p)
        if p == -1:
            break
        print(f"String 'garage' encontrada em 0x{p:x}")
        p += len(alvo)

if __name__ == "__main__":
    main()
