#!/usr/bin/env python3
"""
Audita a sub-rotina AnimatedSplash::init (0x00A1B074) do prologo ao epilogo
para identificar todas as adicoes de nos visuais (addChild), criacoes de sprites
e camadas de splash que possam estar sendo desenhadas sobre o jogo.
"""
import sys
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")
INIT_ADDR = 0x00A1B074
INIT_LEN  = 0x180

def main():
    if not SO_PATH.exists():
        print(f"Erro: Binario nao encontrado em {SO_PATH}")
        sys.exit(1)

    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    md.detail = True

    bloco = data[INIT_ADDR : INIT_ADDR + INIT_LEN]
    print(f"[AUDITORIA] Desmontando AnimatedSplash::init (0x{INIT_ADDR:X} .. 0x{INIT_ADDR+INIT_LEN:X}):")
    print("-" * 75)

    for insn in md.disasm(bloco, INIT_ADDR):
        op_str = insn.op_str
        comentario = ""
        # Verifica chamadas e branches
        if insn.mnemonic in ("blr", "bl"):
            if "x8" in op_str:
                comentario = " <- [CHAMADA VIRTUAL VTABLE]"
        elif insn.mnemonic == "ret":
            comentario = " <- [EPILOGO]"

        print(f"  0x{insn.address:08X}:  {insn.mnemonic:<8} {op_str:<30} {comentario}")
        if insn.mnemonic == "ret":
            break

if __name__ == "__main__":
    main()
