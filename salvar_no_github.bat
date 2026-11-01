@echo off
chcp 65001 >nul
title Backup Hill Climb Racing 2 - GitHub

echo ========================================================
echo   BACKUP DO PORT HILL CLIMB RACING 2 (R36S) NO GITHUB
echo ========================================================
echo.

cd /d "%~dp0"

REM Localizacao prioritaria do Git oficial do Windows (evita conflito com devkitPro/MSYS2)
set "GIT_CMD="
if exist "D:\Program Files\Git\cmd\git.exe" (
    set "GIT_CMD=D:\Program Files\Git\cmd\git.exe"
) else if exist "C:\Program Files\Git\cmd\git.exe" (
    set "GIT_CMD=C:\Program Files\Git\cmd\git.exe"
) else (
    where git >nul 2>&1
    if %ERRORLEVEL% equ 0 (
        set "GIT_CMD=git"
    )
)

if "%GIT_CMD%"=="" (
    echo [ERRO] O Git oficial nao foi encontrado em D:\Program Files\Git ou C:\Program Files\Git!
    echo.
    pause
    exit /b 1
)

REM Remove qualquer index.lock residual de tentativas interrompidas
if exist ".git\index.lock" del /f /q ".git\index.lock" >nul 2>&1

REM Inicializa repositorio se necessario
if not exist ".git" (
    echo [1/4] Inicializando repositorio Git local...
    "%GIT_CMD%" init -b main
    "%GIT_CMD%" remote add origin https://github.com/filipedeniro-jpg/hcr2-port-r36s.git
) else (
    echo [1/4] Repositorio Git ja inicializado.
    "%GIT_CMD%" remote set-url origin https://github.com/filipedeniro-jpg/hcr2-port-r36s.git
)

REM Desativa avisos de quebra de linha LF/CRLF que poluem a tela
"%GIT_CMD%" config core.autocrlf false
"%GIT_CMD%" config core.safecrlf false

echo [2/4] Adicionando arquivos do port ao commit...
"%GIT_CMD%" add .

REM Solicita mensagem personalizada ou utiliza padrao v1.6.0
set "MENSAGEM=%~1"
if "%MENSAGEM%"=="" (
    echo.
    echo Mensagem padrao: "release: v1.6.0 - Calibracao dos controles analogicos, trava de clique anti-swipe e modularizacao SRP"
    set /p MENSAGEM="Digite uma mensagem ou pressione ENTER para aceitar a padrao: "
)
if "%MENSAGEM%"=="" (
    set "MENSAGEM=release: v1.6.0 - Calibracao dos controles analogicos, trava de clique anti-swipe e modularizacao SRP"
)

echo.
echo [3/4] Criando commit: "%MENSAGEM%"...
"%GIT_CMD%" commit -m "%MENSAGEM%"

echo.
echo [4/4] Enviando alteracoes para https://github.com/filipedeniro-jpg/hcr2-port-r36s.git...
"%GIT_CMD%" push -u origin main

if %ERRORLEVEL% equ 0 (
    echo.
    echo ========================================================
    echo   [SUCESSO] Codigo salvo com sucesso no GitHub!
    echo ========================================================
) else (
    echo.
    echo ========================================================
    echo   [AVISO] Se o push falhar por divergencia remota, tente:
    echo   "%GIT_CMD%" pull origin main --rebase
    echo   e execute este script novamente.
    echo ========================================================
)

echo.
pause
