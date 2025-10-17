package br.mack.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mack.estagio.entities.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}