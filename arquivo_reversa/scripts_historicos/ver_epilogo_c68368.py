#!/usr/bin/env python3
"""
Desmonta o epilogo de 0xC68368
"""
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")

def main():
    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    for insn in md.disasm(data[0xC68940:0xC68974], 0xC68940):
        print(f"  0x{insn.address:08X}:  {insn.mnemonic:<8} {insn.op_str}")

if __name__ == "__main__":
    main()
