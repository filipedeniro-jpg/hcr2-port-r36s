#!/usr/bin/env python3
"""
Desmonta a funcao inteira entre 0x00C68E40 e 0x00C68FD0
"""
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")
START = 0x00C68E40
END   = 0x00C68FCC

def main():
    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    bloco = data[START:END]
    for insn in md.disasm(bloco, START):
        print(f"  0x{insn.address:08X}:  {insn.mnemonic:<8} {insn.op_str}")

if __name__ == "__main__":
    main()
