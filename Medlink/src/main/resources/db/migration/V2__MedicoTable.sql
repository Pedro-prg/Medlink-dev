create table medicos (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    crm varchar(20) not null unique,
    telefone varchar(15),
    email varchar(255),
    senha varchar(255),
    primary key (id)
);