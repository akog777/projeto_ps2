package br.mack.estagio.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Vaga {
    private int id;
    private String titulo;
    private String descricao;
    private String area;
    private String localizacao;
    private String modalidade;
    private String cargaHoraria;
    private String requisitos;
}
