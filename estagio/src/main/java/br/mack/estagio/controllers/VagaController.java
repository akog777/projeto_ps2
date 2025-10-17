package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repositories.VagaRepository;
import br.mack.estagio.entities.Vaga;

@RestController
public class VagaController {
    
    @Autowired
    private VagaRepository rep;

    //CREATE
    @PostMapping("/vagas")
    public Vaga criarVaga(@RequestBody(required = true) Vaga novaVaga) {
        if( novaVaga.getTitulo() == null || novaVaga.getDescricao() == null || novaVaga.getArea() == null || 
            novaVaga.getEmail() == null || novaVaga.getLocalizacao() == null || novaVaga.getModalidade() == null ||
            novaVaga.getCargaHoraria() == null || novaVaga.getRequisitos() == null ||
            novaVaga.getTitulo().isEmpty() || novaVaga.getDescricao().isEmpty() || novaVaga.getArea().isEmpty() || 
            novaVaga.getEmail().isEmpty() || novaVaga.getLocalizacao().isEmpty() || 
            novaVaga.getModalidade().isEmpty() || novaVaga.getCargaHoraria().isEmpty() || novaVaga.getRequisitos().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novaVaga);
    }
    
    //READ
    @GetMapping()
    public List<Vaga> listarTodos() {
        return rep.findAll();
    }

    @GetMapping("/vagas/{id}")
    public Vaga listarPeloId(@PathVariable int id) {
        Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
    }
    
    //UPDATE
    @PutMapping("/vagas/{id}")
    public Vaga atualizar(@PathVariable int id, @RequestBody(required = true) Vaga v) {
        if(id != v.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }

        Optional<Empresa> optional = rep.findById(id);
        if(optional.isPresent()) {
            Vagas vaga = optional.get();
            vaga.setTitulo(novosDados.getTitulo());
            vaga.setDescricao(novosDados.getDescricao());
            vaga.setArea(novosDados.getArea());
            vaga.setEmail(novosDados.getEmail());
            vaga.setLocalizacao(novosDados.getLocalizacao());
            vaga.setModalidade(novosDados.getModalidade());
            vaga.setCargaHoraria(novosDados.getCargaHoraria());
            vaga.setRequisitos(novosDados.getRequisitos());
            return rep.save(vaga);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");

    }

    //DELETE
    @DeleteMapping("/vagas/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}