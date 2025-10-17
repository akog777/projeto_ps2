package br.mack.estagio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class AreaInteresse {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titulo;
    private String descricao;
}
