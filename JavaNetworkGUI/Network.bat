@ECHO OFF:: BatchGotAdmin
:-------------------------------------
REM  --> Check for permissions
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"

REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
	echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
	set params = %*:"=""
    echo UAC.ShellExecute "cmd.exe", "/c %~s0 %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:------------------

ECHO Resetting IP Address and Subnet Mask 

:SET /P _inputIP= "Please input new IP Address: "
:SET /P _inputSM= "Please input new Subnet Mask: "
:SET /P _inputGW= "Please input new Gate Way: "

ECHO - - - - - - - - - - - -
:ECHO The IP - %1
:ECHO The Mask - %2
:ECHO The Gateway - %3
ECHO - - - - - - - - - - - -

netsh int ipv4 set address name="Ethernet 2" source=static address=192.168.1.200 mask=255.255.255.0 gateway=none

ECHO Here are the new settings for %computername%
netsh int ip show config
pause
