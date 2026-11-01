#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
for op in [0x8b0a0508, 0x1e222842]:
    for ins in md.disasm(op.to_bytes(4, "little"), 0):
        print(f"0x{op:08x}: {ins.mnemonic} {ins.op_str}")
