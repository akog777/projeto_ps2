package br.mack.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mack.estagio.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {

}