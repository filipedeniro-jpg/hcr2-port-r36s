#!/usr/bin/env python3
"""
Audita o binario libcocos2dcpp.so em busca de todas as ocorrencias do padrao
de desreferencia nula em pecas de veiculo:
  ldr x0, [..., #0x38]
  ldr x8, [x0]
  ldr x8, [x8, #0xa8]
  blr x8
"""
from pathlib import Path
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

SO_PATH = Path("hcr2_classico/binario_so/libcocos2dcpp.so")
RELATORIO = Path("hcr2_classico/arquivo_reversa/auditoria_sprites_veiculo_lote.txt")

def main():
    if not SO_PATH.exists():
        print("Binario nao encontrado")
        return
        
    data = SO_PATH.read_bytes()
    md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    md.detail = True
    
    # Padrao de bytes: ldr x8, [x0] (0xf9400008) seguido de ldr x8, [x8, #0xa8] (0xf9405508)
    alvo = b"\x08\x00\x40\xf9\x08\x55\x40\xf9"
    
    pos = 0
    ocorrencias = []
    print(f"[AUDITORIA] Buscando padrao de desreferencia de sprite em {len(data)} bytes...")
    
    while True:
        pos = data.find(alvo, pos)
        if pos == -1:
            break
        ocorrencias.append(pos)
        pos += 4
        
    print(f"[RESULTADO] Total de ocorrencias encontradas: {len(ocorrencias)}")
    linhas_relatorio = [f"Total de ocorrencias do padrao ldr x8, [x0] / ldr x8, [x8, #0xa8]: {len(ocorrencias)}\n"]
    
    for off in ocorrencias:
        # Desmonta 4 instrucoes antes e 4 depois
        inicio = max(0, off - 16)
        bloco = data[inicio : off + 24]
        desc = f"--- Ocorrencia em 0x{off:X} ---\n"
        print(desc.strip())
        linhas_relatorio.append(desc)
        for insn in md.disasm(bloco, inicio):
            marcador = " => " if insn.address == off else "    "
            linha = f"{marcador}0x{insn.address:08X}: {insn.mnemonic:<8} {insn.op_str}"
            print(linha)
            linhas_relatorio.append(linha + "\n")
        linhas_relatorio.append("\n")
        
    RELATORIO.write_text("".join(linhas_relatorio), encoding="utf-8")
    print(f"[SALVO] Relatorio gravado em: {RELATORIO}")

if __name__ == "__main__":
    main()
