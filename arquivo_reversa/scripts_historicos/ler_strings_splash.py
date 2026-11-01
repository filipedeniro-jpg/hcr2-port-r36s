#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Offset 0x11f8000 + 0x365 = 0x11f8365
    off = 0x11f8365
    fim = dados.find(b"\x00", off)
    s1 = dados[off:fim].decode("utf-8", errors="replace")
    print(f"String em 0x{off:x}: '{s1}'")

    # Ler outras strings em volta de 0x11f8300 a 0x11f8500
    p = 0x11f8300
    while p < 0x11f8500:
        fim_p = dados.find(b"\x00", p)
        if fim_p == -1 or fim_p == p:
            p += 1
            continue
        try:
            texto = dados[p:fim_p].decode("utf-8")
            if len(texto) >= 3 and all(32 <= ord(c) < 127 for c in texto):
                print(f"  0x{p:x}: '{texto}'")
            p = fim_p + 1
        except:
            p += 1

if __name__ == "__main__":
    main()
