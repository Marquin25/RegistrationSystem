package dev.java10x.registrationsystem.Missoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissoesDTO {

    private Long id;
    private String nome;
    private String dificuldade;

}