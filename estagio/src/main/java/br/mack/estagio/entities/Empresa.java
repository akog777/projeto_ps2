package br.mack.estagio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empresa {
    private int id;
    private String nome;
    private int CNPJ;
    private String email;
    private String telefone;
    private String endereco;
    private String areaAtuacao;
}
