CREATE TABLE medico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    crm VARCHAR(30) NOT NULL
    -- Adicione outros campos específicos do médico se necessário
);