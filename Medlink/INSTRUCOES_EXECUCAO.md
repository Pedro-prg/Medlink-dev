# 🚀 Instruções Rápidas para Executar o Medlink

## ⚡ Execução Rápida

### 1. Pré-requisitos
- ✅ Java 17 instalado
- ✅ MySQL rodando
- ✅ Maven instalado

### 2. Configurar MySQL
```sql
CREATE USER 'pedro'@'localhost' IDENTIFIED BY 'pedro14345';
GRANT ALL PRIVILEGES ON *.* TO 'pedro'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Executar Aplicação
```bash
cd Medlink
mvn spring-boot:run
```

### 4. Acessar
- **URL**: http://localhost:8080
- **Página Inicial**: Sistema de agendamento médico

## 🧪 Teste Rápido

### 1. Agendar Consulta
1. Acesse: http://localhost:8080/reuniao/agendar
2. Selecione um médico da lista
3. Escolha um paciente (dados de teste já carregados)
4. Selecione uma data futura
5. Os horários disponíveis aparecerão automaticamente
6. Escolha um horário e clique em "Agendar Consulta"

### 2. Debug Disponibilidade
- Acesse: http://localhost:8080/reuniao/debug/disponibilidade?medicoId=1&data=2024-12-20
- Verá informações sobre disponibilidade do médico

### 3. Gerenciar Dados
- **Médicos**: http://localhost:8080/medico
- **Pacientes**: http://localhost:8080/paciente

## 📊 Dados de Teste Disponíveis

### Médicos:
- Dr. João Silva (Cardiologia) - ID: 1
- Dra. Maria Santos (Dermatologia) - ID: 2  
- Dr. Pedro Oliveira (Ortopedia) - ID: 3

### Pacientes:
- João Silva (CPF: 123.456.789-00) - ID: 1
- Maria Santos (CPF: 987.654.321-00) - ID: 2
- Pedro Oliveira (CPF: 456.789.123-00) - ID: 3

## 🔧 Funcionalidades Testadas

- ✅ Cadastro de médicos e pacientes
- ✅ Agendamento de consultas
- ✅ Verificação de disponibilidade
- ✅ Interface responsiva
- ✅ Validações de dados
- ✅ Mapeamento de dias da semana (corrigido)

## 🐛 Se Houver Problemas

### Erro de Conexão MySQL:
```bash
# Verificar se MySQL está rodando
mysql -u root -p
```

### Erro de Porta:
```properties
# Adicionar em application.properties
server.port=8081
```

### Erro de Compilação:
```bash
mvn clean install
```

## 📝 Logs Importantes

A aplicação está configurada com logs detalhados:
- Logs do Spring Boot
- Logs do Hibernate (SQL)
- Logs personalizados

Verifique o console para informações de debug.

---

**🎯 Objetivo**: Sistema funcionando para demonstração e gravação de vídeo 