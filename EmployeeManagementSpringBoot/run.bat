@echo off
REM Build and Run Employee Management System

echo ============================================
echo Employee Management System - Spring Boot
echo ============================================
echo.

REM Change to project directory
cd /d "%~dp0"

echo Building the project...
call mvn clean install

if errorlevel 1 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo ============================================
echo Build completed successfully!
echo Starting application...
echo ============================================
echo.

REM Run the application
call mvn spring-boot:run

pause
