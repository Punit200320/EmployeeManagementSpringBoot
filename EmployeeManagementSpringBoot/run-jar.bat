@echo off
REM Run built JAR file

echo ============================================
echo Employee Management System - Running JAR
echo ============================================
echo.

REM Change to project directory
cd /d "%~dp0"

if exist "target\employee-management-1.0.0.jar" (
    echo Starting application from JAR...
    java -jar target\employee-management-1.0.0.jar
) else (
    echo JAR file not found!
    echo Please run 'run.bat' first to build the project.
    echo.
    pause
    exit /b 1
)
