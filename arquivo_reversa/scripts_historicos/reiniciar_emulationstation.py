#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
reiniciar_emulationstation.py - Reinicia o EmulationStation e recupera o sinal de video no R36S.
"""

import os
import sys

caminho_encerrador = os.path.join(os.path.dirname(__file__), "encerrar_todos_processos.py")
if os.path.exists(caminho_encerrador):
    from encerrar_todos_processos import encerrar_processos
    encerrar_processos(reiniciar_es=True)
else:
    print("[ERRO] encerrar_todos_processos.py nao encontrado.")
    sys.exit(1)
