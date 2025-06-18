create table pacientes (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    data_nascimento date not null,
    cpf varchar(11) not null unique,
    telefone varchar(15),
    email varchar(255),
    senha varchar(255),
    primary key (id)
);