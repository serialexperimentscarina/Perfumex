CREATE DATABASE perfumex
GO
USE perfumex
GO
CREATE TABLE usuario(
id						INT				NOT NULL,
nome					VARCHAR(50)		NOT NULL,
sobrenome				VARCHAR(50)		NOT NULL,
email					VARCHAR(100)	NOT NULL	UNIQUE,
senha					VARCHAR(50)		NOT NULL	CHECK(LEN(senha) >= 5),
telefone				VARCHAR(14)		NOT NULL	CHECK(LEN(telefone) >= 8),
status					VARCHAR(10)		NOT NULL,
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE()),
PRIMARY KEY(id),
CHECK(data_ultima_modificacao >= data_criacao)
)
GO
CREATE TABLE endereco(
usuarioid				INT				NOT NULL,
rua						VARCHAR(100)	NOT NULL,
numero					INT				NOT NULL	CHECK(numero > 0),
cep						CHAR(8)			NOT NULL	CHECK(LEN(cep) = 8),
complemento				VARCHAR(50)		NULL,
estado					VARCHAR(20)		NOT NULL,
cidade					VARCHAR(50)		NOT NULL,
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE())
PRIMARY KEY(usuarioid, rua, numero),
FOREIGN KEY(usuarioid) REFERENCES usuario(id),
CHECK(data_ultima_modificacao >= data_criacao)
)
GO
CREATE TABLE cliente(
usuarioid				INT				NOT NULL,
cpf						CHAR(11)		NOT NULL	CHECK(LEN(cpf) = 11)
PRIMARY KEY(usuarioid),
FOREIGN KEY(usuarioid) REFERENCES usuario(id)
)
GO
CREATE TABLE lojista(
usuarioid				INT				NOT NULL,
cnpj					CHAR(14)		NOT NULL	CHECK(LEN(cnpj) = 14)
PRIMARY KEY(usuarioid),
FOREIGN KEY(usuarioid) REFERENCES usuario(id)
)
GO
CREATE TABLE carrinho(
id						INT				NOT NULL,
quantidade_itens		INT				NOT NULL	CHECK(quantidade_itens >= 0),
total					DECIMAL(13, 2)	NOT NULL	CHECK(total >= 0),
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE()),
clienteusuarioid		INT				NOT NULL
PRIMARY KEY(id)
FOREIGN KEY(clienteusuarioid) REFERENCES cliente(usuarioid),
CHECK(data_ultima_modificacao >= data_criacao)
)
GO
CREATE TABLE pedido(
carrinhoid				INT				NOT NULL,
tipo_pagto				VARCHAR(6)		NOT NULL,
status					VARCHAR(20)		NOT NULL,
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE())
PRIMARY KEY(carrinhoid),
FOREIGN KEY(carrinhoid) REFERENCES carrinho(id),
CHECK(data_ultima_modificacao >= data_criacao)
)
GO
CREATE TABLE produto(
id						INT				NOT NULL,
nome					VARCHAR(50)		NOT NULL,
preco					NUMERIC(13, 2)	NOT NULL	CHECK(preco > 0),
percentual_desconto		NUMERIC(3, 2)	NOT NULL	CHECK(percentual_desconto >= 0),
descricao				VARCHAR(255)	NULL,
marca					VARCHAR(50)		NULL,
fornecedor				VARCHAR(50)		NULL,
quantidade_atual		INT				NOT NULL	CHECK(quantidade_atual >= 0),
quantidade_minima		INT				NOT NULL	CHECK(quantidade_minima >= 0),
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE()),
lojistaid				INT				NOT NULL
PRIMARY KEY(id)
FOREIGN KEY (lojistaid) REFERENCES lojista(usuarioid),
CHECK(quantidade_atual >= quantidade_minima),
CHECK(data_ultima_modificacao >= data_criacao)
)
GO
CREATE TABLE item(
carrinhoid				INT				NOT NULL,
produtoid				INT				NOT NULL,
quantidade_itens		INT				NOT NULL	CHECK(quantidade_itens > 0),
subtotal				DECIMAL(13, 2)	NOT NULL	CHECK(subtotal > 0),
data_criacao			DATE			NOT NULL	DEFAULT(GETDATE()),
data_ultima_modificacao	DATE			NOT NULL	DEFAULT(GETDATE())
PRIMARY KEY(carrinhoid, produtoid)
FOREIGN KEY(carrinhoid) REFERENCES carrinho(id),
FOREIGN KEY(produtoid) REFERENCES produto(id),
CHECK(data_ultima_modificacao >= data_criacao)
)

