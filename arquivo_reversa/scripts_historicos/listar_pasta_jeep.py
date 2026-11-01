#!/usr/bin/env python3
"""
Lista os arquivos da pasta jeep
"""
from pathlib import Path

DIR_JEEP = Path("hcr2_classico/assets/Common/textures/cars/jeep")

def main():
    if not DIR_JEEP.exists():
        print("Pasta nao existe")
        return
    for item in sorted(DIR_JEEP.rglob("*")):
        if item.is_file():
            print(f"  {item.relative_to(DIR_JEEP.parent)}")

if __name__ == "__main__":
    main()
