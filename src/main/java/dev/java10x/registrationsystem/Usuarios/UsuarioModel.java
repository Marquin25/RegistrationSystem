package dev.java10x.registrationsystem.Usuarios;

import dev.java10x.registrationsystem.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

// Teste do git log --oneline e do git commit --amend
// JPA = Java Persistence API
// Entity ele transforma uma classe em uma entidade do banco da dados
@Entity // TODO: Eu vou criar no meu banco de dados com a tabela com as colunas nome, email, idade e id
@Table(name = "tb_cadastro") // TODO: Tabela de cadastro de usuarios
@NoArgsConstructor // TODO: Esta criando um construtor sem argumento (No Args). Ele vem do LOMBOK
@AllArgsConstructor // TODO: Esta criando um construtor com todos argumentos (AllArgs). Ele vem do LOMBOK
@Data // TODO: Ela cria todos os Getters e Setters. Ela vem do LOMBOK
public class UsuarioModel {

    // TODO: Para gerar o Id automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column(unique = true) // TODO: Essa coluna vai ser unica (Não pode ter email repetidos)
    private String email;

    @Column (name = "idade")
    private int idade;

    @Column(unique = true) // TODO: Essa coluna vai ser unica (Não pode ter CPF repetidos)
    private String cpf;

    // Relações sobre as tabelas
    @ManyToOne //TODO:@ManyToOne - O usuario pode pegar apenas uma unica missao
    @JoinColumn(name = "missoes_id") //TODO: Foreing Key ou chave estrangeira
    private MissoesModel missoes;

}