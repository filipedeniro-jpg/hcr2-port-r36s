#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Raiz de 0x0a1cf14
    # Vamos ver quem chama a função que contém 0x0a1cf14
    # Vamos achar o prólogo antes de 0x0a1cf14:
    prologo = None
    for p in range(0x0a1ce00, 0x0a1cf00, 4):
        palavra = int.from_bytes(dados[p:p+4], "little")
        if (palavra & 0xffc003e0) == 0xa9bf03e0 or (palavra & 0xffc003e0) == 0xa98003e0:
            prologo = p
            break
    print(f"Prologo da função encontrado em 0x{prologo:x}" if prologo else "Não encontrado")

    if prologo:
        alvo = prologo
        print(f"Buscando chamadas para 0x{alvo:x}:")
        for pc in range(0, len(dados) - 4, 4):
            palavra = int.from_bytes(dados[pc:pc+4], "little")
            if (palavra & 0xfc000000) == 0x94000000: # BL
                imm26 = palavra & 0x03ffffff
                if imm26 & 0x02000000:
                    imm26 -= 0x04000000
                dest = pc + (imm26 * 4)
                if dest == alvo:
                    print(f"  BL para 0x{alvo:x} em 0x{pc:x}")

if __name__ == "__main__":
    main()
