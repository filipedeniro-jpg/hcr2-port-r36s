#!/usr/bin/env python3
"""
Busca texturas existentes de drivers em assets
"""
from pathlib import Path

ASSETS_DIR = Path("hcr2_classico/assets")

def main():
    print("[BUSCA] Procurando PNGs existentes de drivers...")
    for p in ASSETS_DIR.rglob("*driver*"):
        if p.suffix == ".png":
            print(f"  {p.relative_to(ASSETS_DIR)}")

if __name__ == "__main__":
    main()
