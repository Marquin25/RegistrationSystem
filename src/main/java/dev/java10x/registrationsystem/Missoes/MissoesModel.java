package dev.java10x.registrationsystem.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.registrationsystem.Usuarios.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@NoArgsConstructor // TODO: Esta criando um construtor sem argumento (No Args). Ele vem do LOMBOK
@AllArgsConstructor // TODO: Esta criando um construtor com todos argumentos (AllArgs). Ele vem do LOMBOK
@Data // TODO: Ela cria todos os Getters e Setters. Ela vem do LOMBOK
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
}
