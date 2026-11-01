#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

def main():
    raiz = Path(__file__).resolve().parent.parent
    caminho_so = raiz / "hcr2_classico" / "binario_so" / "libcocos2dcpp.so"
    dados = caminho_so.read_bytes()

    # Procurar string manglada de replaceScene:
    # _ZN7cocos2d8Director12replaceSceneEPNS_5SceneE
    alvo = b"_ZN7cocos2d8Director12replaceSceneEPNS_5SceneE"
    pos = dados.find(alvo)
    print(f"String '{alvo.decode()}' encontrada em: 0x{pos:x}" if pos != -1 else "Não encontrada")

    # Procurar por Director::replaceScene nos exports ou imports
    # Ou buscar por strings de cena como 'GarageScene', 'SelectLevel', 'MainMenu'
    for cena in [b"GarageScene", b"MainMenu", b"AnimatedSplash", b"SceneDirector"]:
        p = 0
        while True:
            p = dados.find(cena, p)
            if p == -1:
                break
            print(f"String '{cena.decode()}' em: 0x{p:x}")
            p += len(cena)

if __name__ == "__main__":
    main()
