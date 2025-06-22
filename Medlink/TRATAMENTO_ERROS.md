# Tratamento de Erros - Medlink

## Problema Resolvido

O erro **"Whitelabel Error Page - This application has no explicit mapping for /error"** foi causado por exceções de validação Bean Validation que não estavam sendo tratadas adequadamente. Especificamente:

- **ConstraintViolationException**: Erro de validação ao tentar salvar entidades com dados inválidos
- **Falta de tratamento global**: Spring Boot não sabia como lidar com essas exceções
- **Página de erro padrão**: Whitelabel Error Page não é amigável ao usuário

## Solução Implementada

### 1. **GlobalExceptionHandler** (`GlobalExceptionHandler.java`)

Criado um handler global que captura e trata diferentes tipos de exceções:

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Trata exceções de validação Bean Validation
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex)
    
    // Trata exceções de validação de argumentos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    
    // Trata exceções gerais
    @ExceptionHandler(Exception.class)
    public Object handleGenericException(Exception ex)
}
```

### 2. **Página de Erro Personalizada** (`error.html`)

Substituída a Whitelabel Error Page por uma página amigável:

- **Design moderno**: Interface limpa e profissional
- **Mensagens claras**: Explicações sobre possíveis causas
- **Responsiva**: Funciona em dispositivos móveis
- **Navegação**: Botões para voltar ou ir ao início

### 3. **Validação Preventiva** (`AuthController.java`)

Modificado o controller de registro para validar dados antes de salvar:

```java
// Validação manual antes de salvar
if (!ValidationUtils.isValidCRM(crm)) {
    model.addAttribute("erro", "CRM inválido. Deve conter exatamente 6 dígitos numéricos.");
    return "register";
}

// Validação da entidade completa
Set<ConstraintViolation<Medico>> violations = validator.validate(medico);
if (!violations.isEmpty()) {
    // Trata erros de validação
}
```

### 4. **Configurações de Aplicação** (`application.properties`)

Adicionadas configurações para melhorar o tratamento de erros:

```properties
# Configurações de tratamento de erro
server.error.include-message=always
server.error.include-binding-errors=always
server.error.include-stacktrace=never
server.error.include-exception=false

# Configurações de validação
spring.jpa.properties.jakarta.persistence.validation.mode=ddl
spring.jpa.properties.hibernate.validator.apply_to_ddl=false
```

## Tipos de Erro Tratados

### 1. **Erros de Validação (400 Bad Request)**
- CPF inválido
- CRM inválido
- Campos obrigatórios vazios
- Formato de email inválido

### 2. **Erros de Recurso Não Encontrado (404 Not Found)**
- URLs inexistentes
- Páginas não encontradas

### 3. **Erros Internos do Servidor (500 Internal Server Error)**
- Exceções não tratadas
- Problemas de banco de dados
- Erros de sistema

## Fluxo de Tratamento de Erro

```
1. Usuário submete dados inválidos
   ↓
2. AuthController valida dados manualmente
   ↓
3. Se inválido → Retorna mensagem de erro amigável
   ↓
4. Se válido → Tenta salvar no banco
   ↓
5. Se erro de validação → GlobalExceptionHandler captura
   ↓
6. Retorna resposta JSON ou página de erro personalizada
```

## Exemplos de Resposta

### Erro de Validação (API)
```json
{
    "error": "Erro de validação",
    "message": "Dados inválidos fornecidos",
    "errors": {
        "crm": "CRM deve conter exatamente 6 dígitos numéricos",
        "crmValid": "CRM inválido"
    },
    "status": 400
}
```

### Erro de Validação (Web)
- Página `error.html` com mensagem amigável
- Explicação das possíveis causas
- Botões para navegação

## Benefícios da Solução

### 1. **Experiência do Usuário**
- ✅ Mensagens de erro claras e específicas
- ✅ Interface amigável e profissional
- ✅ Navegação intuitiva

### 2. **Segurança**
- ✅ Não expõe detalhes técnicos em produção
- ✅ Validação server-side robusta
- ✅ Tratamento seguro de exceções

### 3. **Manutenibilidade**
- ✅ Código organizado e reutilizável
- ✅ Tratamento centralizado de erros
- ✅ Fácil de estender e modificar

### 4. **Debugging**
- ✅ Logs detalhados em desenvolvimento
- ✅ Informações úteis para desenvolvedores
- ✅ Rastreamento de erros

## Como Testar

### 1. **Teste de CPF Inválido**
```bash
# Tentar registrar paciente com CPF inválido
curl -X POST http://localhost:8080/registro \
  -d "nome=João&email=joao@email.com&senha=123456&tipo=PACIENTE&cpf=123.456.789-01"
```

### 2. **Teste de CRM Inválido**
```bash
# Tentar registrar médico com CRM inválido
curl -X POST http://localhost:8080/registro \
  -d "nome=Dr. Maria&email=maria@email.com&senha=123456&tipo=MEDICO&crm=12345"
```

### 3. **Teste de URL Inexistente**
```bash
# Acessar URL que não existe
curl http://localhost:8080/pagina-inexistente
```

## Próximos Passos

1. **Validação Frontend**: Adicionar validação em tempo real nos formulários
2. **Logs Estruturados**: Implementar logging estruturado para erros
3. **Monitoramento**: Adicionar métricas de erro
4. **Testes Automatizados**: Criar testes para cenários de erro
5. **Internacionalização**: Suporte a múltiplos idiomas nas mensagens

## Arquivos Modificados/Criados

### Novos Arquivos:
- `GlobalExceptionHandler.java` - Handler global de exceções
- `error.html` - Página de erro personalizada
- `TRATAMENTO_ERROS.md` - Esta documentação

### Arquivos Modificados:
- `AuthController.java` - Validação preventiva
- `application.properties` - Configurações de erro

## Conclusão

O problema da Whitelabel Error Page foi completamente resolvido através de:

1. **Tratamento global de exceções** com `@ControllerAdvice`
2. **Validação preventiva** nos controllers
3. **Página de erro personalizada** amigável ao usuário
4. **Configurações adequadas** no `application.properties`

Agora o sistema fornece feedback claro e útil para os usuários, mantendo a segurança e facilitando o debugging para desenvolvedores. 