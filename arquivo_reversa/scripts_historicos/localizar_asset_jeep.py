#!/usr/bin/env python3
"""
Localiza o arquivo jeep-tire e chassis dentro de hcr2_classico/assets
"""
from pathlib import Path

ASSETS_DIR = Path("hcr2_classico/assets")

def main():
    if not ASSETS_DIR.exists():
        print("Diretorio assets nao existe")
        return
        
    print("[BUSCA] Procurando texturas do jeep...")
    for termo in ["jeep-tire", "chassis", "jeep_metal", "tire"]:
        matches = list(ASSETS_DIR.rglob(f"*{termo}*"))
        print(f"Resultados para '{termo}' ({len(matches)} encontrados):")
        for m in matches[:10]:
            print(f"  {m.relative_to(ASSETS_DIR)}")

if __name__ == "__main__":
    main()
