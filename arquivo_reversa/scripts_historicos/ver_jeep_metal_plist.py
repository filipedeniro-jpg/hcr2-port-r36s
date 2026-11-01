#!/usr/bin/env python3
"""
Mostra as chaves de jeep_metal no HD/vehicles4.plist
"""
from pathlib import Path

PLIST_PATH = Path("hcr2_classico/assets/HD/vehicles4.plist")

def main():
    if not PLIST_PATH.exists():
        print("Arquivo nao existe")
        return
    linhas = PLIST_PATH.read_text(encoding="utf-8", errors="replace").splitlines()
    for i, l in enumerate(linhas):
        if "jeep_metal" in l:
            inicio = max(0, i - 2)
            fim = min(len(linhas), i + 8)
            print(f"Linha {i+1}:")
            for j in range(inicio, fim):
                print(f"  {linhas[j]}")

if __name__ == "__main__":
    main()
