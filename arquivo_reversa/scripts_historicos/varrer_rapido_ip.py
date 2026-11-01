#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import socket
from concurrent.futures import ThreadPoolExecutor

def testar(ip):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(0.3)
    res = s.connect_ex((ip, 22))
    s.close()
    if res == 0:
        return ip
    return None

def main():
    print("Varrendo faixa 10.0.0.1 a 10.0.0.50 na porta 22...")
    ips = [f"10.0.0.{i}" for i in range(1, 51)]
    with ThreadPoolExecutor(max_workers=30) as ex:
        resultados = [r for r in ex.map(testar, ips) if r]
    print("IPs com porta 22 aberta encontrados:", resultados)

if __name__ == "__main__":
    main()
