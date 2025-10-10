package br.mack.estagio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Estudante {
    private int id;
    private String CPF;
    private String curso;
    private String email;
    private String telefone;
    private String areaInteresse;
}
