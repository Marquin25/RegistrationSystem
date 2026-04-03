package dev.java10x.registrationsystem.Usuarios;

import dev.java10x.registrationsystem.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String cpf;
    private MissoesModel missoes;

}
