@echo off
echo ========================================
echo    EduMatrix - Learning Management System
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 21 from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Check if port 8080 is in use
netstat -ano | findstr :8080 | findstr LISTENING >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [WARNING] Port 8080 is already in use
    echo [INFO] Killing process on port 8080...
    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
        taskkill /F /PID %%a >nul 2>nul
    )
    timeout /t 2 /nobreak >nul
    echo [SUCCESS] Port 8080 is now free
)

REM Check if MySQL service is running
sc query MySQL80 | find "RUNNING" >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [WARNING] MySQL service is not running
    echo [INFO] Attempting to start MySQL service...
    net start MySQL80 >nul 2>nul
    if %ERRORLEVEL% NEQ 0 (
        echo [ERROR] Failed to start MySQL. Please start it manually.
        pause
        exit /b 1
    )
    echo [SUCCESS] MySQL service started
)

echo [INFO] Starting EduMatrix...
echo.

REM Run the application
mvn spring-boot:run

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Application failed to start
    echo Check the error messages above
)

pause
