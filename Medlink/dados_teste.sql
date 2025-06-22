-- Dados de teste para a aplicação Medlink
-- Inserir um médico de teste
INSERT INTO medico (nome, email, senha, crm, especialidades, experiencias, trabalhos_passados) 
VALUES (
    'Dr. João Silva Santos',
    'joao.silva@medlink.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', -- senha: 123456
    'CRM-SP 123456',
    'Cardiologia, Clínica Médica',
    '15 anos de experiência em cardiologia, especialista em arritmias cardíacas',
    'Hospital Albert Einstein (2010-2015), Hospital Sírio-Libanês (2015-2020)'
);

-- Inserir um paciente de teste
INSERT INTO paciente (nome, email, senha, cpf) 
VALUES (
    'Maria Oliveira Costa',
    'maria.oliveira@email.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', -- senha: 123456
    '123.456.789-00'
);

-- Inserir horários disponíveis do médico para a semana
-- Segunda-feira
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'SEGUNDA', '08:00:00', '12:00:00');

INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'SEGUNDA', '14:00:00', '18:00:00');

-- Terça-feira
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'TERCA', '08:00:00', '12:00:00');

INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'TERCA', '14:00:00', '18:00:00');

-- Quarta-feira
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'QUARTA', '08:00:00', '12:00:00');

INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'QUARTA', '14:00:00', '18:00:00');

-- Quinta-feira
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'QUINTA', '08:00:00', '12:00:00');

INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'QUINTA', '14:00:00', '18:00:00');

-- Sexta-feira
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'SEXTA', '08:00:00', '12:00:00');

INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'SEXTA', '14:00:00', '18:00:00');

-- Sábado (meio período)
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) 
VALUES (1, 'SABADO', '08:00:00', '12:00:00');

-- Comentários sobre os dados:
-- Médico: Dr. João Silva Santos (ID: 1)
-- Email: joao.silva@medlink.com
-- Senha: 123456 (hash bcrypt)
-- CRM: CRM-SP 123456
-- Especialidades: Cardiologia, Clínica Médica
--
-- Paciente: Maria Oliveira Costa (ID: 1)
-- Email: maria.oliveira@email.com
-- Senha: 123456 (hash bcrypt)
-- CPF: 123.456.789-00
--
-- Horários disponíveis:
-- Segunda a Sexta: 08:00-12:00 e 14:00-18:00
-- Sábado: 08:00-12:00
-- Domingo: Não disponível 