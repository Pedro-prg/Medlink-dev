CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,
    link_sala VARCHAR(255),
    finalizado BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_agendamento_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_agendamento_medico FOREIGN KEY (medico_id) REFERENCES medico(id)
);