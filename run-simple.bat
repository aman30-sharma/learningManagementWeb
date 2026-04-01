@echo off
echo Starting EduMatrix...
cd /d "%~dp0"
mvn spring-boot:run
pause
