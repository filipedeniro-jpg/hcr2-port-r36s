#!/usr/bin/env python3
"""
Busca referencias a vehicles*.plist no libcocos2dcpp.so
"""
from pathlib import Path

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")

def main():
    data = SO_PATH.read_bytes()
    for v in ["vehicles1", "vehicles2", "vehicles3", "vehicles4", "vehicles5"]:
        vb = v.encode("ascii")
        pos = 0
        print(f"Referencias a '{v}':")
        while True:
            pos = data.find(vb, pos)
            if pos == -1:
                break
            # Le string completa
            null_pos = data.find(b"\x00", pos)
            s = data[pos:null_pos].decode("ascii", errors="replace")
            print(f"  0x{pos:x}: {s}")
            pos += len(vb)

if __name__ == "__main__":
    main()
