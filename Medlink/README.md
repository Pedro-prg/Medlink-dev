# Medlink - Sistema de Agendamento Médico

Sistema web para agendamento de consultas médicas desenvolvido em Spring Boot.

## 🚀 Funcionalidades

- ✅ Cadastro e gerenciamento de médicos
- ✅ Cadastro e gerenciamento de pacientes  
- ✅ Agendamento de consultas
- ✅ Verificação de disponibilidade de horários
- ✅ Interface web responsiva
- ✅ Validação de dados
- ✅ Banco de dados MySQL com Flyway

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🛠️ Configuração

### 1. Banco de Dados

Certifique-se de que o MySQL está rodando e crie um usuário:

```sql
CREATE USER 'pedro'@'localhost' IDENTIFIED BY 'pedro14345';
GRANT ALL PRIVILEGES ON *.* TO 'pedro'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Configuração da Aplicação

O arquivo `application.properties` já está configurado com:
- Banco de dados: `medlink` (será criado automaticamente)
- Usuário: `pedro`
- Senha: `pedro14345`

## 🚀 Como Executar

### Opção 1: Via IDE
1. Abra o projeto na sua IDE
2. Execute a classe `MedlinkApplication.java`
3. Acesse: http://localhost:8080

### Opção 2: Via Maven
```bash
cd Medlink
mvn spring-boot:run
```

### Opção 3: Via JAR
```bash
cd Medlink
mvn clean package
java -jar target/Medlink-0.0.1-SNAPSHOT.jar
```

## 📱 Endpoints Principais

- **Página Inicial**: http://localhost:8080/
- **Agendar Consulta**: http://localhost:8080/reuniao/agendar
- **Gerenciar Médicos**: http://localhost:8080/medico
- **Gerenciar Pacientes**: http://localhost:8080/paciente
- **Debug Disponibilidade**: http://localhost:8080/reuniao/debug/disponibilidade?medicoId=1&data=2024-12-20

## 🗄️ Estrutura do Banco

O sistema usa Flyway para migração automática das tabelas:

- `paciente` - Dados dos pacientes
- `medico` - Dados dos médicos
- `disponibilidade_medico` - Horários disponíveis dos médicos
- `agendamentos` - Consultas agendadas

## 🧪 Dados de Teste

O sistema já possui dados de teste:

### Médicos:
- Dr. João Silva (Cardiologia)
- Dra. Maria Santos (Dermatologia)
- Dr. Pedro Oliveira (Ortopedia)

### Pacientes:
- João Silva (CPF: 123.456.789-00)
- Maria Santos (CPF: 987.654.321-00)
- Pedro Oliveira (CPF: 456.789.123-00)

## 🔧 Debug

Para testar a funcionalidade de agendamento:

1. Acesse: http://localhost:8080/reuniao/agendar
2. Selecione um médico
3. Escolha uma data futura
4. Os horários disponíveis serão carregados automaticamente
5. Selecione um horário e agende a consulta

## 📝 Logs

A aplicação está configurada com logs detalhados para debug:
- Logs do Spring Boot
- Logs do Hibernate (SQL)
- Logs personalizados da aplicação

## 🐛 Solução de Problemas

### Erro de Conexão com Banco
- Verifique se o MySQL está rodando
- Confirme as credenciais no `application.properties`
- Verifique se o usuário tem permissões

### Erro de Porta
- A aplicação usa a porta 8080 por padrão
- Se estiver ocupada, altere em `application.properties`:
  ```properties
  server.port=8081
  ```

### Erro de Compilação
- Verifique se o Java 17 está instalado
- Execute `mvn clean install` para limpar e recompilar

## 📞 Suporte

Para dúvidas ou problemas, consulte os arquivos de documentação:
- `TRATAMENTO_ERROS.md` - Tratamento de erros
- `VALIDACAO_CPF_CRM.md` - Validações implementadas

---

**Desenvolvido com ❤️ para facilitar o agendamento médico** 