package br.mack.estagio.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repository.AdminRepository;
import br.mack.estagio.entities.Admin;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository rep;
    
    //CREATE
    @PostMapping("")
    public Admin criarAdmin(@RequestBody(required = true) Admin adm) {
        if( adm.getNome() == null || adm.getCPF() == null || adm.getEmail() == null || 
            adm.getTelefone() == null || adm.getAreaAtuacao() == null ||
            adm.getNome().isEmpty() || adm.getCPF().isEmpty() || adm.getEmail().isEmpty() || 
            adm.getTelefone().isEmpty() || adm.getAreaAtuacao().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(adm);
    }

    //READ
    @GetMapping("")
    public List<Admin> listarTodos() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public Admin listarPeloId(@PathVariable Long id) {
        Optional<Admin> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Admin atualizarPeloId(@RequestBody Admin novosDados, @PathVariable Long id) {
        
        Optional<Admin> optional = rep.findById(id);
        if(optional.isPresent()) {
            Admin adm = optional.get();
            adm.setNome(novosDados.getNome());
            adm.setCPF(novosDados.getCPF());
            adm.setEmail(novosDados.getEmail());
            adm.setTelefone(novosDados.getTelefone());
            adm.setAreaAtuacao(novosDados.getAreaAtuacao());
            return rep.save(adm);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public void apagarPeloId(@PathVariable Long id) {
         Optional<Admin> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}