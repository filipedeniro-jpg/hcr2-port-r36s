#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Procurar por 0x11f8072 ou 0x11f8070 (AnimatedSplash RTTI)
    alvo = (0x11f8070).to_bytes(8, "little")
    pos = 0
    while True:
        pos = dados.find(alvo, pos)
        if pos == -1:
            break
        print(f"Vtable/Typeinfo em: 0x{pos:x}")
        # Ler entradas da vtable
        print("Entradas da vtable:")
        for i in range(15):
            entry_ptr = pos + 8 + (i * 8)
            fn = int.from_bytes(dados[entry_ptr:entry_ptr+8], "little")
            print(f"  [{i}] vtable + 0x{i*8:02x} -> 0x{fn:07x}")
        pos += 8

if __name__ == "__main__":
    main()
