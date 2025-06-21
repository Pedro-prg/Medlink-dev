# Validação de CPF e CRM - Medlink

Este documento descreve as validações implementadas para CPF (Cadastro de Pessoa Física) e CRM (Conselho Regional de Medicina) no sistema Medlink.

## Validações Implementadas

### CPF (Cadastro de Pessoa Física)

**Regras de Validação:**
- Deve conter exatamente **11 dígitos numéricos**
- Não pode ser uma sequência de números iguais (ex: 11111111111)
- Deve ter dígitos verificadores válidos (algoritmo oficial do governo brasileiro)
- Aceita formatação (pontos e hífen) que será removida automaticamente

**Exemplos de CPFs Válidos:**
- `12345678909` (sem formatação)
- `123.456.789-09` (com formatação)
- `111.444.777-35` (com formatação)

**Exemplos de CPFs Inválidos:**
- `1234567890` (menos de 11 dígitos)
- `123456789012` (mais de 11 dígitos)
- `11111111111` (todos os dígitos iguais)
- `12345678901` (dígitos verificadores incorretos)

### CRM (Conselho Regional de Medicina)

**Regras de Validação:**
- Deve conter exatamente **6 dígitos numéricos**
- Aceita formatação que será removida automaticamente

**Exemplos de CRMs Válidos:**
- `123456` (sem formatação)
- `12.345.6` (com formatação)

**Exemplos de CRMs Inválidos:**
- `12345` (menos de 6 dígitos)
- `1234567` (mais de 6 dígitos)
- `abc123` (contém letras)

## Como Usar

### 1. Validação via API REST

#### Validar CPF
```bash
POST /api/validation/cpf
Content-Type: application/json

{
    "cpf": "123.456.789-09"
}
```

**Resposta de Sucesso:**
```json
{
    "valid": true,
    "cpf": "123.456.789-09",
    "cpfFormatado": "123.456.789-09",
    "message": "CPF válido"
}
```

#### Validar CRM
```bash
POST /api/validation/crm
Content-Type: application/json

{
    "crm": "123456"
}
```

**Resposta de Sucesso:**
```json
{
    "valid": true,
    "crm": "123456",
    "crmFormatado": "123456",
    "message": "CRM válido"
}
```

### 2. Validação via Bean Validation (JSR-303)

#### Para Pacientes
```java
@Valid
@RequestBody RegisterPacienteDTO pacienteDTO
```

#### Para Médicos
```java
@Valid
@RequestBody RegisterMedicoDTO medicoDTO
```

### 3. Validação Programática

```java
import phenrique.com.A3.Medlink.api.common.ValidationUtils;

// Validar CPF
boolean cpfValido = ValidationUtils.isValidCPF("123.456.789-09");

// Validar CRM
boolean crmValido = ValidationUtils.isValidCRM("123456");

// Formatar CPF para exibição
String cpfFormatado = ValidationUtils.formatCPF("12345678909");

// Remover formatação
String cpfLimpo = ValidationUtils.removeFormatting("123.456.789-09");
```

## Endpoints de Teste

### 1. Validação Individual
- `POST /api/validation/cpf` - Valida CPF
- `POST /api/validation/crm` - Valida CRM

### 2. Validação Completa
- `POST /api/validation/paciente` - Valida dados completos do paciente
- `POST /api/validation/medico` - Valida dados completos do médico

### 3. Utilitários
- `POST /api/validation/remove-formatting` - Remove formatação de CPF/CRM

## Exemplos de Teste

### Teste de CPF Válido
```bash
curl -X POST http://localhost:8080/api/validation/cpf \
  -H "Content-Type: application/json" \
  -d '{"cpf": "123.456.789-09"}'
```

### Teste de CPF Inválido
```bash
curl -X POST http://localhost:8080/api/validation/cpf \
  -H "Content-Type: application/json" \
  -d '{"cpf": "123.456.789-01"}'
```

### Teste de CRM Válido
```bash
curl -X POST http://localhost:8080/api/validation/crm \
  -H "Content-Type: application/json" \
  -d '{"crm": "123456"}'
```

### Teste de Registro de Paciente
```bash
curl -X POST http://localhost:8080/api/validation/paciente \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456",
    "cpf": "123.456.789-09"
  }'
```

## Mensagens de Erro

### CPF
- `"CPF deve conter exatamente 11 dígitos numéricos"` - Formato incorreto
- `"CPF inválido"` - Dígitos verificadores incorretos

### CRM
- `"CRM deve conter exatamente 6 dígitos numéricos"` - Formato incorreto
- `"CRM inválido"` - Validação falhou

### Campos Gerais
- `"Nome é obrigatório"` - Campo nome vazio
- `"Email deve ter formato válido"` - Email inválido
- `"Senha deve ter pelo menos 6 caracteres"` - Senha muito curta

## Implementação Técnica

### Classes Principais

1. **ValidationUtils.java** - Classe utilitária com métodos de validação
2. **RegisterPacienteDTO.java** - DTO com validações para registro de paciente
3. **RegisterMedicoDTO.java** - DTO com validações para registro de médico
4. **ValidationController.java** - Controller REST para testes de validação

### Anotações de Validação Usadas

- `@NotBlank` - Campo obrigatório e não vazio
- `@Size` - Tamanho mínimo/máximo
- `@Email` - Formato de email válido
- `@Pattern` - Expressão regular
- `@AssertTrue` - Validação customizada

### Segurança

- Todas as validações são executadas no servidor
- Validações client-side são apenas para UX, não para segurança
- Dados inválidos são rejeitados antes de serem salvos no banco
- Mensagens de erro não expõem informações sensíveis

## Próximos Passos

1. Integrar validações nos formulários HTML
2. Adicionar validação em tempo real no frontend
3. Implementar cache de CPFs válidos para performance
4. Adicionar validação de unicidade (CPF/CRM único por usuário) 