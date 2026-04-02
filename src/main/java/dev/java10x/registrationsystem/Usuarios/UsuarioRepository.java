package dev.java10x.registrationsystem.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

// Essa extensão (JpaRepository) é um simplificador, quando a gnt usa os bancos de dados
// Qual classe que o JPA vai querer q fique escaneando as nossas entidades e criando tabelas no nosso banco de dados:
// MissoesModel e o Long é por causa do id
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
//JPA vai dar umas Query


}
