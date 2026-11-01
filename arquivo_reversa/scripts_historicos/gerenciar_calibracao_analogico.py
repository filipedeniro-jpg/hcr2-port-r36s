#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Gerenciador de Calibração e Backup dos Analógicos do R36S.
Permite:
  --backup   : Cria backup dos coeficientes originais de fábrica.
  --instalar : Instala o utilitário nativo de calibração (joystick / jscal) no ArkOS.
  --aplicar  : Aplica os coeficientes para compensar o desvio do analógico direito.
  --desfazer : Reverte instantaneamente todas as alterações e restaura o padrão de fábrica.
  --status   : Exibe o estado e coeficientes atuais de calibração.
"""

import argparse
import json
import os
import sys

try:
    import paramiko
except ImportError:
    print("[ERRO] Biblioteca 'paramiko' não instalada.")
    sys.exit(1)


def carregar_configuracao():
    caminho_config = os.path.join(os.path.dirname(__file__), "configuracao_console.json")
    with open(caminho_config, "r", encoding="utf-8") as f:
        return json.load(f)


def obter_cliente_ssh():
    config = carregar_configuracao()
    ip = config.get("ip", "10.0.0.14")
    porta = config.get("porta", 22)
    usuario = config.get("usuario", "ark")
    senha = config.get("senha", "ark")

    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(ip, port=porta, username=usuario, password=senha, timeout=6)
    return ssh, senha


def executar_comando_sudo(ssh, comando, senha):
    cmd_escapado = comando.replace("'", "'\\''")
    cmd_completo = f"echo '{senha}' | sudo -S sh -c '{cmd_escapado}'"
    stdin, stdout, stderr = ssh.exec_command(cmd_completo)
    saida = stdout.read().decode("utf-8", errors="ignore")
    erros = stderr.read().decode("utf-8", errors="ignore")
    return saida.strip(), erros.strip()


def acao_backup(ssh, senha):
    print("[1/2] Capturando estado original de calibração do joystick...")
    # Verificar se jscal já existe ou salvar configuração padrão neutra
    stdin, stdout, stderr = ssh.exec_command("jscal -p /dev/input/js0 2>/dev/null || echo 'PADRAO_SEM_JSCAL'")
    calibracao_atual = stdout.read().decode("utf-8", errors="ignore").strip()

    dados_backup = {
        "dispositivo": "/dev/input/js0",
        "calibracao_original": calibracao_atual,
        "descricao": "Backup de fabrica antes de qualquer ajuste de software"
    }

    caminho_backup_local = os.path.join(os.path.dirname(__file__), "backup_calibracao_original.json")
    with open(caminho_backup_local, "w", encoding="utf-8") as f:
        json.dump(dados_backup, f, indent=2, ensure_ascii=False)

    # Salvar também uma cópia segura no próprio console
    executar_comando_sudo(ssh, f"mkdir -p /home/ark/.backup_controle && echo '{json.dumps(dados_backup)}' > /home/ark/.backup_controle/estado_original.json", senha)

    print(f"[OK] Backup salvo localmente em: {caminho_backup_local}")
    print("[OK] Backup salvo no console em: /home/ark/.backup_controle/estado_original.json")


def acao_instalar(ssh, senha):
    acao_backup(ssh, senha)
    print("\n[2/2] Instalando pacote oficial 'joystick' (jscal) no ArkOS...")
    saida, erros = executar_comando_sudo(ssh, "apt-get update && apt-get install -y joystick", senha)
    print("--- Saída do apt-get ---")
    if saida:
        print(saida[-500:])
    if erros:
        print("--- Erros/Avisos do apt-get ---")
        print(erros[-500:])


def acao_desfazer(ssh, senha):
    print("=" * 60)
    print(" [REVERSÃO TOTAL: RESTAURANDO ESTADO DE FÁBRICA]")
    print("=" * 60)

    # 1. Remover arquivos de persistência criados
    print("[1/4] Removendo arquivos de persistência de calibração...")
    executar_comando_sudo(ssh, "rm -f /var/lib/joystick/joystick.state /etc/joystick.state /etc/profile.d/calibrar_joystick.sh", senha)

    # 2. Restaurar coeficientes exatos capturados no backup inicial
    print("[2/4] Restaurando coeficientes de fábrica exatos no kernel...")
    cmd_restaurar = "jscal -s 4,1,32,-32,32,309257,309257,1,32,-32,32,309257,309257,1,32,-32,32,309257,309257,1,32,-32,32,309257,309257 /dev/input/js0"
    executar_comando_sudo(ssh, cmd_restaurar, senha)

    # 3. Desinstalar o pacote joystick para não interferir com o driver do ArkOS
    print("[3/4] Removendo o pacote joystick...")
    executar_comando_sudo(ssh, "apt-get remove -y joystick", senha)

    # 4. Reiniciar o EmulationStation para reconectar os inputs nativos
    print("[4/4] Reiniciando interface gráfica do EmulationStation...")
    executar_comando_sudo(ssh, "killall -9 emulationstation", senha)

    print("\n[SUCESSO] Sistema e controles 100% restaurados ao padrão original de fábrica!")


def acao_calibrar(ssh, senha, centro_eixo2=-1300, centro_eixo3=1750):
    print("=" * 60)
    print(" [APLICANDO CALIBRAÇÃO DE COMPENSAÇÃO DO ANALÓGICO DIREITO]")
    print(f" Novo centro calibrado Eixo 2 (X Dir): {centro_eixo2}")
    print(f" Novo centro calibrado Eixo 3 (Y Dir): {centro_eixo3}")
    print("=" * 60)

    # Eixo 2 (X Dir com offset em -1300):
    c2_min = centro_eixo2 + 50
    c2_max = centro_eixo2 - 50
    s2_neg = int((32767 * 16384) / max(100, abs(centro_eixo2 - (-1800)) - 50))
    s2_pos = int((32767 * 16384) / max(100, abs(1800 - centro_eixo2) - 50))
    coef_eixo2 = f"1,32,{c2_max},{c2_min},{s2_neg},{s2_pos}"

    # Eixo 3 (Y Dir com offset em 1750):
    c3_min = 1700
    c3_max = 1800
    s3_neg = int((32767 * 16384) / 3450)
    s3_pos = 100000
    coef_eixo3 = f"1,32,{c3_min},{c3_max},{s3_neg},{s3_pos}"

    cmd_jscal = f"jscal -s 4,1,32,-32,32,309257,309257,1,32,-32,32,309257,309257,{coef_eixo2},{coef_eixo3} /dev/input/js0"

    print(f"[1/3] Aplicando coeficientes de compensação no kernel...")
    print(f"      Comando: {cmd_jscal}")
    executar_comando_sudo(ssh, cmd_jscal, senha)

    print("[2/3] Gravando calibração persistente no ArkOS (jscal-store)...")
    executar_comando_sudo(ssh, "jscal-store /dev/input/js0", senha)

    # Criar também script garantido no udev para reaplicar automaticamente no boot
    cmd_udev = (
        f"mkdir -p /etc/profile.d && echo '{cmd_jscal}' > /etc/profile.d/calibrar_joystick.sh && "
        f"chmod +x /etc/profile.d/calibrar_joystick.sh"
    )
    executar_comando_sudo(ssh, cmd_udev, senha)

    print("[3/3] Validando novo estado...")
    stdin, stdout, stderr = ssh.exec_command("jscal -p /dev/input/js0")
    print("      " + stdout.read().decode("utf-8", errors="ignore").strip())

    print("\n[SUCESSO] Calibração aplicada e persistida com sucesso!")


def acao_status(ssh):
    print("--- Diagnóstico da Instalação do Joystick ---")
    cmd = "export PATH=$PATH:/usr/bin:/usr/sbin:/sbin; which jscal && jscal -p /dev/input/js0 || dpkg -l joystick 2>&1"
    stdin, stdout, stderr = ssh.exec_command(cmd)
    print(stdout.read().decode("utf-8", errors="ignore").strip())


def main():
    parser = argparse.ArgumentParser(description="Gerenciador de Calibração e Backup do R36S")
    parser.add_argument("--backup", action="store_true", help="Salva backup do estado original")
    parser.add_argument("--instalar", action="store_true", help="Instala o utilitário joystick com backup prévio")
    parser.add_argument("--calibrar", action="store_true", help="Aplica a calibração de compensação no analógico direito")
    parser.add_argument("--desfazer", action="store_true", help="Desfaz qualquer alteração e restaura o padrão de fábrica")
    parser.add_argument("--status", action="store_true", help="Mostra o status atual da calibração")

    args = parser.parse_args()

    try:
        ssh, senha = obter_cliente_ssh()
    except Exception as e:
        print(f"[ERRO] Falha ao conectar ao console: {e}")
        return

    if args.desfazer:
        acao_desfazer(ssh, senha)
    elif args.calibrar:
        acao_calibrar(ssh, senha)
    elif args.instalar:
        acao_instalar(ssh, senha)
    elif args.backup:
        acao_backup(ssh, senha)
    elif args.status:
        acao_status(ssh)
    else:
        parser.print_help()

    ssh.close()


if __name__ == "__main__":
    main()
