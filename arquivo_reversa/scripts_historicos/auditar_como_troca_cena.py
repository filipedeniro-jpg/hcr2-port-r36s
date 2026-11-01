# -*- coding: utf-8 -*-
"""
Como o TransitionManager chama Director::replaceScene ou troca a cena ativa?
"""
import os
import struct
import capstone

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

cs = capstone.Cs(capstone.CS_ARCH_ARM64, capstone.CS_MODE_ARM)

# No Cocos2d-x v3:
# Director::replaceScene(Scene* pScene)
# Vamos buscar todas as chamadas virtuais a replaceScene no binario!
# Na vtable de cocos2d::Director:
# Em Cocos2d-x v3 ARM64, qual e o offset de replaceScene na vtable do Director?
# Vamos ver a vtable de DisplayLinkDirector (0x125ef0b)
pos = data.find(b"21DisplayLinkDirector")
print(f"RTTI DisplayLinkDirector em 0x{pos:x}" if pos != -1 else "Nao achou RTTI")

# Vamos ver onde TransitionManager::replaceScene (0x1241b72) tem seu codigo
# A string 0x1241b72 e o nome de uma lambda __funcIZN17TransitionManager12replaceScene...
# Vamos achar a vtable dessa lambda:
target = 0x1241b72
target_bytes = struct.pack("<Q", target)
pos = data.find(target_bytes)
print(f"Ponteiro de RTTI para TransitionManager::replaceScene em: 0x{pos:x}" if pos != -1 else "Nao achou")
if pos != -1:
    # A vtable da lambda esta em pos + 8 ou o typeinfo em pos - 8
    # Vamos achar a vtable que aponta para pos - 8
    ti = pos - 8
    p2 = data.find(struct.pack("<Q", ti))
    print(f"Vtable da lambda em: 0x{p2+8:x}" if p2 != -1 else "Vtable da lambda nao achada diretamente")
