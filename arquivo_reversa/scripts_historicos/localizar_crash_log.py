#!/usr/bin/env python3
"""
Extrai o bloco do crash handler do log_atual_console.txt
"""
from pathlib import Path

LOG_PATH = Path("hcr2_classico/arquivo_reversa/log_atual_console.txt")

def main():
    if not LOG_PATH.exists():
        print("Arquivo nao existe")
        return
    linhas = LOG_PATH.read_text(encoding="utf-8", errors="replace").splitlines()
    print(f"Total de linhas: {len(linhas)}")
    
    idx_crash = -1
    for i, l in enumerate(linhas):
        if "CRASH" in l or "SIGSEGV" in l or "SINAL" in l or "FAULT" in l or "rel PC" in l:
            idx_crash = i
            break
            
    if idx_crash != -1:
        inicio = max(0, idx_crash - 10)
        fim = min(len(linhas), idx_crash + 50)
        print(f"Crash detectado na linha {idx_crash+1}. Mostrando {inicio+1} a {fim}:")
        print("=" * 60)
        for j in range(inicio, fim):
            print(linhas[j])
    else:
        print("Nenhum marcador de crash explicito encontrado. Mostrando ultimas 40 linhas:")
        for l in linhas[-40:]:
            print(l)

if __name__ == "__main__":
    main()
