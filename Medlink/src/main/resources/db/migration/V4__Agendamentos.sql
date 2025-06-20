CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,
    link_sala VARCHAR(255),
    CONSTRAINT fk_agendamento_paciente FOREIGN KEY (paciente_id) REFERENCES usuarios(id),
    CONSTRAINT fk_agendamento_medico FOREIGN KEY (medico_id) REFERENCES usuarios(id)
);