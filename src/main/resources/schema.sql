DROP  TABLE IF EXISTS CLIENTE;

CREATE TABLE CLIENTE (
  ID number(10),
  NOME varchar(100),
  CPF  number(11),
  DATA_NASCIMENTO  date,
  CIDADE_NASCIMENTO  varchar(100)
);