#!/usr/bin/env python3
"""
Busca instrucoes adrp/ldr que referenciam 0x11f8105 (tabela vehicles*.plist)
"""
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")
TARGET_PAGE = 0x11F8000

def main():
    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    print(f"[BUSCA] Varrendo funcoes que referenciam a pagina 0x{TARGET_PAGE:X}...")
    
    # Varre a secao .text (0x500000 a 0xD00000)
    bloco = data[0x500000:0xD00000]
    for insn in md.disasm(bloco, 0x500000):
        if insn.mnemonic == "adrp":
            try:
                imm = int(insn.op_str.split()[-1].replace("#", ""), 16)
                if imm == TARGET_PAGE:
                    print(f"  0x{insn.address:08X}:  {insn.mnemonic:<8} {insn.op_str}")
            except Exception:
                pass

if __name__ == "__main__":
    main()
