package br.mack.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mack.estagio.entities.Estudante;


public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

}