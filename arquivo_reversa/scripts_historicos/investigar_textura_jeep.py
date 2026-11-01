#!/usr/bin/env python3
"""
Investiga onde estao os sprites e imagens do Jeep nos assets ou no APK
"""
from pathlib import Path
import zipfile

APK_PATH = Path("pacotes/com.fingersoft.hcr2_1.28.2-445_minAPI17(arm64-v8a)(nodpi)_apkmirror.com.apk")
ASSETS_DIR = Path("hcr2_classico/assets")

def main():
    print("[1] Buscando no APK original...")
    if APK_PATH.exists():
        with zipfile.ZipFile(APK_PATH, "r") as z:
            for n in z.namelist():
                if "jeep" in n.lower() or "chassis" in n.lower() or "tire" in n.lower():
                    if n.endswith((".png", ".jpg", ".webp", ".pvr", ".atlas", ".plist")):
                        print(f"  APK: {n}")
    else:
        print("APK nao encontrado")

    print("[2] Buscando atlases de carros em assets...")
    for p in ASSETS_DIR.rglob("*car*"):
        if p.suffix in (".png", ".plist", ".atlas"):
            print(f"  ASSETS: {p.relative_to(ASSETS_DIR)}")
            
    for p in ASSETS_DIR.rglob("*vehicle*"):
        if p.suffix in (".png", ".plist", ".atlas"):
            print(f"  ASSETS VEHICLE: {p.relative_to(ASSETS_DIR)}")

if __name__ == "__main__":
    main()
