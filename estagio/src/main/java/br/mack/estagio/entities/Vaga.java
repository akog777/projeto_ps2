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
    private Long id;
    private String titulo;
    private String descricao;
    @ManyToOne
    private AreaInteresse areaInteresse;
    private String localizacao;
    private String modalidade;
    private String cargaHoraria;
    private String requisitos;

    @ManyToOne
    private Empresa empresa;

    public Object getArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArea'");
    }

    public void setArea(Object area) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setArea'");
    }


}
