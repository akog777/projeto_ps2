package br.mack.estagio.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Admin;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository rep;
    
    //CREATE
    @PostMapping("/admins")
    public Admin criarAdmin(@RequestBody(required = true) Admin adm) { 
        if( adm.getNome() == null || adm.getCPF() == null || adm.getEmail() == null || adm.getTelefone() == null || adm.getAreaAtuacao() == null ||
            adm.getNome().isEmpty() || adm.getCPF().isEmpty() || adm.getEmail().isEmpty() || adm.getTelefone().isEmpty() || adm.getAreaAtuacao().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(adm);
    }

    //READ
    @GetMapping()
    public List<Admin> listarTodos() {
        return admins;
    }

    @GetMapping("/admins/{id}")
    public Admin listarPeloId(@PathVariable int id) {
        Optional<Admin> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");
    }

    
    //UPDATE
    @PutMapping("/admins/{id}")
    public Admin atualizarAdminPeloId(@RequestBody Admin novosDados, @PathVariable long id) {
        
        Optional<Admin> optional = rep.findById(id);
        if(optional.isPresent()) {
            Admin adm = optional.get();
            adm.setNome(novosDados.getNome());
            adm.setCPF(novosDados.getCPF());
            adm.setEmail(novosDados.getEmail());
            adm.setTelefone(novosDados.getTelefone());
            adm.setAreaAtuacao(novosDados.getAreaAtuacao());
            return rep.save(u);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");

    }

    //DELETE
    @DeleteMapping("/admins/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<Admin> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}