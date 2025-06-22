# Instruções para Executar Dados de Teste

## Arquivo SQL Criado
O arquivo `dados_teste.sql` contém inserts para testar a aplicação Medlink com dados básicos.

## Dados Incluídos

### 1. Médico de Teste
- **Nome**: Dr. João Silva Santos
- **Email**: joao.silva@medlink.com
- **Senha**: 123456
- **CRM**: CRM-SP 123456
- **Especialidades**: Cardiologia, Clínica Médica
- **Experiência**: 15 anos de experiência em cardiologia

### 2. Paciente de Teste
- **Nome**: Maria Oliveira Costa
- **Email**: maria.oliveira@email.com
- **Senha**: 123456
- **CPF**: 123.456.789-00

### 3. Horários Disponíveis do Médico
- **Segunda a Sexta**: 08:00-12:00 e 14:00-18:00
- **Sábado**: 08:00-12:00
- **Domingo**: Não disponível

## Como Executar

### Opção 1: Via MySQL Workbench ou phpMyAdmin
1. Abra o arquivo `dados_teste.sql`
2. Execute o script completo no seu banco de dados

### Opção 2: Via Linha de Comando MySQL
```bash
mysql -u seu_usuario -p sua_database < dados_teste.sql
```

### Opção 3: Via Spring Boot (H2 Console)
1. Inicie a aplicação Spring Boot
2. Acesse http://localhost:8080/h2-console
3. Conecte ao banco H2
4. Cole e execute o conteúdo do arquivo `dados_teste.sql`

## Testando a Aplicação

### Login como Médico
- **URL**: http://localhost:8080/login
- **Email**: joao.silva@medlink.com
- **Senha**: 123456

### Login como Paciente
- **URL**: http://localhost:8080/login
- **Email**: maria.oliveira@email.com
- **Senha**: 123456

## Funcionalidades para Testar

### Como Paciente:
1. **Agendar Consulta**: Acesse a página de agendamento
2. **Ver Histórico**: Visualize consultas anteriores
3. **Acessar Sala**: Entre na sala de consulta virtual

### Como Médico:
1. **Ver Disponibilidades**: Confirme os horários cadastrados
2. **Ver Consultas**: Acesse a lista de consultas agendadas
3. **Gerenciar Perfil**: Atualize informações profissionais

## Observações
- As senhas estão hasheadas com BCrypt
- Os horários seguem o padrão de dias da semana em português
- O médico tem ID 1 e o paciente tem ID 1
- Todos os horários são para o médico com ID 1 