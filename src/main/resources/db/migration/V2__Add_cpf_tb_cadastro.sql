-- V2: Adicionando a coluna CPF na tabela tb_cadastro

ALTER TABLE tb_cadastro
ADD COLUMN cpf VARCHAR(11) UNIQUE;