package br.mack.estagio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Estudante {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String CPF;
    private String curso;
    private String email;
    private String telefone;
    private String areaInteresse;
}
