package br.mack.estagio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titulo;
    private String descricao;
    private String area;
    private String localizacao;
    private String modalidade;
    private String cargaHoraria;
    private String requisitos;
}
