package br.mack.estagio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private int id;
    private String nome;
    private String CPF;
    private String email;
    private String telefone;
    private String areaAtuacao;
}
