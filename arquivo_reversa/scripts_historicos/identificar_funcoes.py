#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import struct

DIR_RAIZ = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CAMINHO_SO = os.path.join(DIR_RAIZ, "hcr2_classico", "binario_so", "libcocos2dcpp.so")

with open(CAMINHO_SO, "rb") as f:
    hdr = f.read(64)
    e_shoff = struct.unpack("<Q", hdr[40:48])[0]
    e_shentsize = struct.unpack("<H", hdr[58:60])[0]
    e_shnum = struct.unpack("<H", hdr[60:62])[0]
    e_shstrndx = struct.unpack("<H", hdr[62:64])[0]

    f.seek(e_shoff + e_shstrndx * e_shentsize)
    shstr_hdr = f.read(e_shentsize)
    shstr_offset = struct.unpack("<Q", shstr_hdr[24:32])[0]
    f.seek(shstr_offset)
    shstrtab = f.read(struct.unpack("<Q", shstr_hdr[32:40])[0])

    dynsym_off, dynsym_sz, dynstr_off = 0, 0, 0
    for i in range(e_shnum):
        f.seek(e_shoff + i * e_shentsize)
        sh = f.read(e_shentsize)
        nome = shstrtab[struct.unpack("<I", sh[0:4])[0]:].split(b"\x00")[0].decode('latin1')
        if nome == ".dynsym":
            dynsym_off = struct.unpack("<Q", sh[24:32])[0]
            dynsym_sz = struct.unpack("<Q", sh[32:40])[0]
        elif nome == ".dynstr":
            dynstr_off = struct.unpack("<Q", sh[24:32])[0]

    f.seek(dynstr_off)
    dynstr = f.read(0x100000)

    # Varre dynsym
    f.seek(dynsym_off)
    dados_sym = f.read(dynsym_sz)

alvos = [0x006CC668, 0x006CC3B4, 0x00E53A90, 0x00C5A46C, 0x00D97854, 0x00D7D528, 0x00E4F3DC]

for p in range(0, len(dados_sym), 24):
    st_name, st_info, st_other, st_shndx, st_value, st_size = struct.unpack("<IBBHQQ", dados_sym[p:p+24])
    for a in alvos:
        if st_value == a or (st_value <= a < st_value + st_size and st_size > 0):
            nome_sym = dynstr[st_name:].split(b"\x00")[0].decode('latin1')
            print(f"Alvo 0x{a:08X} -> Simbolo: {nome_sym} (st_value=0x{st_value:08X})")
