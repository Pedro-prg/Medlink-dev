@echo off
echo ========================================
echo    TESTES COMPLETOS DO PROJETO MEDLINK
echo ========================================
echo.

echo [1/4] Compilando o projeto...
call mvn clean compile -q
if %errorlevel% neq 0 (
    echo ERRO: Falha na compilacao
    pause
    exit /b 1
)
echo ✓ Compilacao concluida com sucesso
echo.

echo [2/4] Executando testes unitarios...
call mvn test -q
if %errorlevel% neq 0 (
    echo ERRO: Falha nos testes unitarios
    pause
    exit /b 1
)
echo ✓ Testes unitarios concluidos com sucesso
echo.

echo [3/4] Executando testes de integracao...
call mvn verify -q
if %errorlevel% neq 0 (
    echo ERRO: Falha nos testes de integracao
    pause
    exit /b 1
)
echo ✓ Testes de integracao concluidos com sucesso
echo.

echo [4/4] Gerando relatorio de cobertura...
call mvn jacoco:report -q
if %errorlevel% neq 0 (
    echo AVISO: Falha na geracao do relatorio de cobertura
) else (
    echo ✓ Relatorio de cobertura gerado
)
echo.

echo ========================================
echo    TODOS OS TESTES CONCLUIDOS COM SUCESSO!
echo ========================================
echo.
echo Resumo dos testes executados:
echo - Testes de Controllers (Reuniao, Medico, Auth, etc.)
echo - Testes de Services (Agendamento, Medico, Paciente)
echo - Testes de Repositories (Agendamento, Medico, Paciente)
echo - Testes de Validacao (CPF, CRM, Email)
echo - Testes de Integracao
echo.
echo Para ver os relatorios detalhados, acesse:
echo target/site/jacoco/index.html
echo.
pause 