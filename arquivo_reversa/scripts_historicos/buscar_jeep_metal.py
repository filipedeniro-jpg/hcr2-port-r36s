#!/usr/bin/env python3
"""
Busca por jeep_metal em todos os plists e json de assets
"""
from pathlib import Path

ASSETS_DIR = Path("hcr2_classico/assets")

def main():
    print("[BUSCA] Procurando 'jeep_metal' nos assets...")
    for ext in ["*.plist", "*.json", "*.xml"]:
        for p in ASSETS_DIR.rglob(ext):
            try:
                t = p.read_text(encoding="utf-8", errors="replace")
                if "jeep_metal" in t:
                    print(f"  Encontrado em: {p.relative_to(ASSETS_DIR)}")
            except Exception:
                pass

if __name__ == "__main__":
    main()
