#!/usr/bin/env python3
"""
Filtra as ocorrencias do relatorio de sprites entre 0xC68000 e 0xC6A000
"""
from pathlib import Path

REL_PATH = Path("hcr2_classico/arquivo_reversa/auditoria_sprites_veiculo_lote.txt")

def main():
    linhas = REL_PATH.read_text(encoding="utf-8").splitlines()
    bloco_atual = []
    em_c68 = False
    
    print("[OCORRENCIAS EM 0xC68000..0xC6A000]:")
    for l in linhas:
        if l.startswith("--- Ocorrencia em 0x"):
            addr_str = l.split("0x")[1].split()[0]
            addr = int(addr_str, 16)
            if 0xC67000 <= addr <= 0xC6A000:
                em_c68 = True
                print(l)
            else:
                em_c68 = False
        elif em_c68:
            print(f"  {l}")

if __name__ == "__main__":
    main()
