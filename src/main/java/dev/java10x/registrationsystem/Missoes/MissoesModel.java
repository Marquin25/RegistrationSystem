package dev.java10x.registrationsystem.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.registrationsystem.Usuarios.UsuarioModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dificuldade;

    // Relações sobre as tabelas
    // Mappear essas tabelas e dar o nome da tabela que esta ligando no caso missioes
    @OneToMany(mappedBy = "missoes") // TODO:@OndeToMany - Uma missao pode ter varios usuarios
    @JsonIgnore
    private List<UsuarioModel> usuario;

    public MissoesModel() {
    }

    public MissoesModel(Long id, String nome, String descricao, UsuarioModel usuario) {
        this.id = id;
        this.nome = nome;
        this.dificuldade = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}
