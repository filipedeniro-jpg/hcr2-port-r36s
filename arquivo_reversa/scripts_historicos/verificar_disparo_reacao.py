#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import paramiko

def main():
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect("10.0.0.16", port=22, username="ark", password="ark", timeout=10)
    stdin, stdout, stderr = ssh.exec_command("grep -E 'onFinishedAssetLoader|onFinishedLaunching|REACAO IMEDIATA' /roms/ports/hcr2/log.txt")
    saida = stdout.read().decode("utf-8", errors="replace")
    print("Ocorrências no log do R36S:")
    print(saida if saida.strip() else "(NENHUMA OCORRÊNCIA ENCONTRADA)")
    ssh.close()

if __name__ == "__main__":
    main()
