<<<<<<< HEAD
CREATE TABLE medico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    crm VARCHAR(30) NOT NULL
    -- Adicione outros campos específicos do médico se necessário
=======
CREATE TABLE medico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    crm VARCHAR(30) NOT NULL,
    especialidades VARCHAR(255),
    experiencias VARCHAR(500),
    trabalhos_passados VARCHAR(500)
    -- Adicione outros campos específicos do médico se necessário
>>>>>>> dev
);