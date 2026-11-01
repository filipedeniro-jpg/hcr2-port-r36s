#!/usr/bin/env python3
"""
Le trecho especifico do log_atual_console.txt
"""
from pathlib import Path

LOG_PATH = Path("hcr2_classico/arquivo_reversa/log_atual_console.txt")

def main():
    if not LOG_PATH.exists():
        print("Log nao encontrado")
        return
    linhas = LOG_PATH.read_text(encoding="utf-8", errors="replace").splitlines()
    total = len(linhas)
    print(f"Total de linhas: {total}")
    # Encontra onde comeca Quadro
    primeiro_quadro = -1
    for i, l in enumerate(linhas):
        if "Quadro" in l:
            primeiro_quadro = i
            break
    
    inicio = max(0, primeiro_quadro - 60) if primeiro_quadro != -1 else total - 60
    fim = min(total, (primeiro_quadro + 10) if primeiro_quadro != -1 else total)
    print(f"Exibindo linhas {inicio} a {fim}:")
    print("-" * 60)
    for idx in range(inicio, fim):
        print(f"{idx+1}: {linhas[idx]}")

if __name__ == "__main__":
    main()
