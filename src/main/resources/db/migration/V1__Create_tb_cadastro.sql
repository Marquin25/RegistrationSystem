CREATE TABLE tb_cadastro (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(100),
                             idade INT,
                             email VARCHAR(100),
                             cpf VARCHAR(11) UNIQUE
);