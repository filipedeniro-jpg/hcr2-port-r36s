# -*- coding: utf-8 -*-
"""
Acha Node::setVisible e Node::removeFromParent no libcocos2dcpp.so
"""
import os
import struct

SO_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(SO_PATH, "rb") as f:
    data = f.read()

# _ZN7cocos2d4Node10setVisibleEb
# _ZN7cocos2d4Node16removeFromParentEv
for sym in [b"_ZN7cocos2d4Node10setVisibleEb", b"_ZN7cocos2d4Node16removeFromParentEv", b"_ZN7cocos2d4Node26removeFromParentAndCleanupEb"]:
    pos = data.find(sym)
    if pos != -1:
        print(f"Simbolo {sym.decode()} em: 0x{pos:x}")
    else:
        print(f"{sym.decode()} nao encontrado na tabela de strings")

# Vamos ver na vtable de cocos2d::Node:
# Onde fica a vtable de cocos2d::Node?
pos = data.find(b"N7cocos2d4NodeE")
if pos != -1:
    print(f"RTTI Node em: 0x{pos:x}")
