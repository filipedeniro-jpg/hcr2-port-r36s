#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Procurar referências ao typeinfo em 0x1bb7a0 ou 0x1bb7a8
    for alvo in [0x1bb7a0, 0x1bb7a8]:
        ref_bytes = alvo.to_bytes(8, "little")
        pos = 0
        while True:
            pos = dados.find(ref_bytes, pos)
            if pos == -1:
                break
            print(f"Referência a 0x{alvo:x} em: 0x{pos:x}")
            pos += 8

if __name__ == "__main__":
    main()
