-- Inserir médicos de teste
INSERT INTO medico (nome, email, senha, crm) VALUES 
('Dr. João Silva', 'joao.silva@medlink.com', 'senha123', 'CRM12345'),
('Dra. Maria Santos', 'maria.santos@medlink.com', 'senha123', 'CRM67890'),
('Dr. Pedro Oliveira', 'pedro.oliveira@medlink.com', 'senha123', 'CRM11111');

-- Inserir disponibilidades para o Dr. João Silva (ID 1)
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) VALUES 
(1, 'SEGUNDA', '08:00', '17:00'),
(1, 'TERCA', '08:00', '17:00'),
(1, 'QUARTA', '08:00', '17:00'),
(1, 'QUINTA', '08:00', '17:00'),
(1, 'SEXTA', '08:00', '17:00');

-- Inserir disponibilidades para a Dra. Maria Santos (ID 2)
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) VALUES 
(2, 'SEGUNDA', '09:00', '18:00'),
(2, 'TERCA', '09:00', '18:00'),
(2, 'QUARTA', '09:00', '18:00'),
(2, 'QUINTA', '09:00', '18:00'),
(2, 'SEXTA', '09:00', '18:00'),
(2, 'SABADO', '09:00', '12:00');

-- Inserir disponibilidades para o Dr. Pedro Oliveira (ID 3)
INSERT INTO disponibilidade_medico (medico_id, dia_semana, hora_inicio, hora_fim) VALUES 
(3, 'SEGUNDA', '14:00', '22:00'),
(3, 'TERCA', '14:00', '22:00'),
(3, 'QUARTA', '14:00', '22:00'),
(3, 'QUINTA', '14:00', '22:00'),
(3, 'SEXTA', '14:00', '22:00'); 