package br.mack.estagio.entities;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String CNPJ;
    private String email;
    private String telefone;
    private String endereco;
    private String areaAtuacao;
    
    @OneToMany
    private List<Vaga> vagas = new ArrayList<>();
}
