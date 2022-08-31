create database autenticacao;

create table usuario (
	id uuid primary key,
	email varchar(50) not null,
	senha varchar(500) not null,
	tipo_usuario varchar(15),
	saga uuid
)
