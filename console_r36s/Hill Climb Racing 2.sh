#!/bin/bash
# ==============================================================================
# Script de Lancamento Oficial: Hill Climb Racing 2 (Port Nativo ARM64)
# Console: R36S / ArkOS Linux (Compativel com PortMaster e Lancamento Direto)
# ==============================================================================

# Determinacao dinamica do diretorio de roms (SD1 vs SD2)
if [ -f "/opt/system/Advanced/Switch to main SD for Roms.sh" ]; then
    GAMEDIR="/roms/ports/hcr2"
else
    GAMEDIR="/roms2/ports/hcr2"
fi

if [ ! -d "$GAMEDIR" ]; then
    GAMEDIR="/roms/ports/hcr2"
fi

cd "$GAMEDIR" || exit 1
BINARY="hcr2.aarch64"

# Carrega integracao com PortMaster e configuracao de controles se disponivel
XDG_DATA_HOME=${XDG_DATA_HOME:-$HOME/.local/share}
if [ -d "/opt/system/Tools/PortMaster/" ]; then
  controlfolder="/opt/system/Tools/PortMaster"
elif [ -d "/opt/tools/PortMaster/" ]; then
  controlfolder="/opt/tools/PortMaster"
elif [ -d "$XDG_DATA_HOME/PortMaster/" ]; then
  controlfolder="$XDG_DATA_HOME/PortMaster"
else
  controlfolder="/roms/ports/PortMaster"
fi

if [ -f "$controlfolder/control.txt" ]; then
  source "$controlfolder/control.txt"
  [ -f "${controlfolder}/mod_${CFW_NAME}.txt" ] && source "${controlfolder}/mod_${CFW_NAME}.txt"
  get_controls
fi

[ -n "$sdl_controllerconfig" ] && export SDL_GAMECONTROLLERCONFIG="$sdl_controllerconfig"

# Variaveis de ambiente essenciais para aceleracao por hardware Mali / DRM no ArkOS
export SDL_VIDEODRIVER="kmsdrm"
export SDL_RENDER_DRIVER="opengles2"
export SDL_AUDIODRIVER="alsa"
export ALSOFT_DRIVERS="alsa"
unset PULSE_SERVER
export PULSE_RUNTIME_PATH="/tmp/pulse"

# Oculta cursor piscando do terminal virtual
printf "\033[?25l"
clear

# Prepara ambiente temporario e diretorios locais
mkdir -p /tmp/pulse "$GAMEDIR/json_cache" "$GAMEDIR/save" 2>/dev/null || true

# Configura libc no tmpfs para contornar limitacao de links simbolicos em particao exFAT
mkdir -p /tmp/hcr2_lib && ln -sf /lib/aarch64-linux-gnu/libc.so.6 /tmp/hcr2_lib/libc.so 2>/dev/null || true
export LD_LIBRARY_PATH="/tmp/hcr2_lib:$GAMEDIR/libs:$LD_LIBRARY_PATH"

chmod +x "$GAMEDIR/$BINARY"

# Executa o jogo registrando saida no log.txt local
./$BINARY > "$GAMEDIR/log.txt" 2>&1

# Restaura o cursor do terminal e limpa o display ao encerrar
printf "\033[?25h"
clear
