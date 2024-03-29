CREATE DATABASE IF NOT EXISTS POO;
USE POO;

CREATE TABLE IF NOT EXISTS JAZIGO (
	idjazigo INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	nomeFamilia TEXT NOT NULL,
	local TEXT NOT NULL,
	tamanho INTEGER NOT NULL,
	qtdSepulturas INTEGER NOT NULL,
	sepOcupadas INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS OBITO (
	idobito INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome TEXT NOT NULL,
	dataObito DATE NOT NULL,
	cpf VARCHAR(14) NOT NULL UNIQUE,
	cidade TEXT NOT NULL,
	cartorio TEXT NOT NULL,
	livro INTEGER NOT NULL,
	folha INTEGER NOT NULL,
	termo INTEGER NOT NULL,
	medico TEXT NOT NULL,
	crm INTEGER NOT NULL,
    idjazigo INTEGER NOT NULL,
    CONSTRAINT fk_JAZIGO FOREIGN KEY (idjazigo) REFERENCES JAZIGO (idjazigo)
);

CREATE TABLE IF NOT EXISTS SEPULTAMENTO (
	idsepultamento INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	dataSepultamento DATE NOT NULL,
	horarioEnterro TIME NOT NULL,
	funeraria TEXT NOT NULL,
	responsavel TEXT NOT NULL,
	local TEXT NOT NULL,
	idobito INT NOT NULL,
	CONSTRAINT fk_OBITO FOREIGN KEY (idobito) REFERENCES OBITO (idobito)
);

CREATE TABLE IF NOT EXISTS USUARIO (
	idusuario INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome TEXT NOT NULL,
	senha TEXT NOT NULL,
	nivel TEXT NOT NULL
);

INSERT INTO USUARIO (NOME, SENHA, NIVEL) VALUES ('tata', '09696fe2ffd2bfd3a97241b0dd8634168e486fec6748ae545be00e0a58814138', 'ADMINISTRADOR');