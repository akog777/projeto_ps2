package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repositories.EstudanteRepository;
import br.mack.estagio.entities.Estudante;

@RestController
public class EstudanteController {
    
    @Autowired
    private EstudanteRepository rep;

    //CREATE
    @PostMapping("/estudantes")
    public Estudante criarEstudante(@RequestBody(required = true) Estudante novoEstudante) {
        if( novoEstudante.getNome() == null || novoEstudante.getCPF() == null || novoEstudante.getCurso() == null || 
            novoEstudante.getEmail() == null || novoEstudante.getTelefone() == null || novoEstudante.getAreaInteresse() == null ||
            novoEstudante.getNome().isEmpty() || novoEstudante.getCPF().isEmpty() || novoEstudante.getCurso().isEmpty() || 
            novoEstudante.getEmail().isEmpty() || novoEstudante.getTelefone().isEmpty() || novoEstudante.getAreaInteresse().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novoEstudante);
    }

    //READ
    @GetMapping()
    public List<Estudante> listarTodos() {
        return rep.findAll();
    }

   @GetMapping("/estudantes/{id}")
    public Estudante listarPeloId(@PathVariable int id) {
        Optional<Estudante> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    
    //UPDATE
    @PutMapping("/estudantes/{id}")
    public Estudante atualizarEstudante(@PathVariable int id, @RequestBody(required = true) Estudante e) {
        if(id != e.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        Optional<Empresa> optional = rep.findById(id);
        if(optional.isPresent()) {
            Estudante est = optional.get();
            est.setNome(novosDados.getNome());
            est.setCPF(novosDados.getCPF());
            est.setCurso(novosDados.getCurso());
            est.setEmail(novosDados.getEmail());
            est.setTelefone(novosDados.getTelefone());
            est.setAreaInteresse(novosDados.getAreaInteressa());
            return rep.save(est);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");

    }

    //DELETE
    @DeleteMapping("/estudantes/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<Estudante> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}