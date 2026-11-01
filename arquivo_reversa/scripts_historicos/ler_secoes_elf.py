#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    hdr = f.read(64)
    # ELF64 header
    e_shoff = struct.unpack("<Q", hdr[40:48])[0]
    e_shentsize = struct.unpack("<H", hdr[58:60])[0]
    e_shnum = struct.unpack("<H", hdr[60:62])[0]
    e_shstrndx = struct.unpack("<H", hdr[62:64])[0]

    # Le a tabela de nomes das secoes
    f.seek(e_shoff + e_shstrndx * e_shentsize)
    shstr_hdr = f.read(e_shentsize)
    shstr_offset = struct.unpack("<Q", shstr_hdr[24:32])[0]
    shstr_size = struct.unpack("<Q", shstr_hdr[32:40])[0]
    f.seek(shstr_offset)
    shstrtab = f.read(shstr_size)

    # Varre as secoes
    print(f"Total de secoes: {e_shnum}")
    for i in range(e_shnum):
        f.seek(e_shoff + i * e_shentsize)
        sh = f.read(e_shentsize)
        sh_name_idx = struct.unpack("<I", sh[0:4])[0]
        sh_type = struct.unpack("<I", sh[4:8])[0]
        sh_addr = struct.unpack("<Q", sh[16:24])[0]
        sh_offset = struct.unpack("<Q", sh[24:32])[0]
        sh_size = struct.unpack("<Q", sh[32:40])[0]

        nome = shstrtab[sh_name_idx:].split(b"\x00")[0].decode('latin1')
        if nome in [".text", ".rodata", ".data.rel.ro", ".data", ".bss"]:
            print(f"Secao {nome:15}: VAddr=0x{sh_addr:08X} Offset=0x{sh_offset:08X} Size=0x{sh_size:08X}")
