#!/usr/bin/env python3
"""
Busca por jeep, tire e chassis dentro dos arquivos vehicles*.plist
"""
from pathlib import Path

ASSETS_DIR = Path("hcr2_classico/assets")

def main():
    print("[BUSCA] Inspecionando plists de vehicles...")
    for plist in sorted(ASSETS_DIR.glob("HD/vehicles*.plist")):
        texto = plist.read_text(encoding="utf-8", errors="replace")
        print(f"\n--- {plist.name} (Tamanho: {len(texto)} chars) ---")
        for termo in ["jeep", "chassis", "tire", "wheel"]:
            ocorrencias = [linha.strip() for linha in texto.splitlines() if termo in linha.lower()]
            print(f"  Termo '{termo}': {len(ocorrencias)} ocorrencias")
            for oc in ocorrencias[:5]:
                print(f"    {oc}")

if __name__ == "__main__":
    main()
