#!/usr/bin/env python3
"""
Encontra os ret de 0x00C68368, 0x00C68D00 e 0x00C68EEC
"""
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")

def main():
    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    for alvo in [0x00C68368, 0x00C68D00, 0x00C68EEC]:
        bloco = data[alvo : alvo + 0x900]
        for insn in md.disasm(bloco, alvo):
            if insn.mnemonic == "ret":
                print(f"Alvo 0x{alvo:X} -> Epilogo ret em 0x{insn.address:X} (delta: {insn.address - alvo} bytes)")
                break

if __name__ == "__main__":
    main()
