<<<<<<< HEAD
CREATE TABLE paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL
    -- Adicione outros campos específicos do paciente se necessário
=======
CREATE TABLE paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL
    -- Adicione outros campos específicos do paciente se necessário
>>>>>>> dev
);