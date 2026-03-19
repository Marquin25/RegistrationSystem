package dev.java10x.registrationsystem.Usuarios;

import dev.java10x.registrationsystem.Missoes.MissoesModel;
import jakarta.persistence.*;

// Teste do git log --oneline e do git commit --amend
// JPA = Java Persistence API
// Entity ele transforma uma classe em uma entidade do banco da dados
@Entity // TODO: Eu vou criar no meu banco de dados com a tabela com as colunas nome, email, idade e id
@Table(name = "tb_cadastro") // TODO: Tabela de cadastro de usuarios
public class UsuarioModel {

    // TODO: Para gerar o Id automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private int idade;

    // Relações sobre as tabelas
    @ManyToOne //TODO:@ManyToOne - O usuario pode pegar apenas uma unica missao
    @JoinColumn(name = "missoes_id") //TODO: Foreing Key ou chave estrangeira
    private MissoesModel missoes;

    public UsuarioModel() {
    }

    public UsuarioModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    // getter e setter do ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters e setters restantes
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}