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
    private Long id;
    private String nome;
    private String CPF;
    private String curso;
    private String email;
    private String telefone;
    @ManyToMany
    @JoinTable(name = "estudante_area",
        joinColumns = @JoinColumn(name = "estudante_id"),
        inverseJoinColumns = @JoinColumn(name = "area_id"))
    private java.util.Set<AreaInteresse> areasInteresse = new java.util.HashSet<>();
}


