package br.mack.estagio.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repository.AreaInteresseRepository;
import br.mack.estagio.entities.AreaInteresse;

@RestController
@RequestMapping("/areas")
public class AreaInteresseController {

    @Autowired
    private AreaInteresseRepository rep;
    
    //CREATE
    @PostMapping("")
    public AreaInteresse criarArea(@RequestBody AreaInteresse novaArea) {
        if( novaArea.getTitulo() == null || novaArea.getDescricao() == null ||
            novaArea.getTitulo().isEmpty() || novaArea.getDescricao().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novaArea);
    }

    //READ
    @GetMapping("")
    public List<AreaInteresse> listarTodos() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public AreaInteresse listarPeloId(@PathVariable Long id) {
        Optional<AreaInteresse> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de interesse não encontrada");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public AreaInteresse atualizarPeloId(@RequestBody AreaInteresse novosDados, @PathVariable Long id) {
        
        Optional<AreaInteresse> optional = rep.findById(id);
        if(optional.isPresent()) {
            AreaInteresse areaint = optional.get();
            areaint.setTitulo(novosDados.getTitulo());
            areaint.setDescricao(novosDados.getDescricao());
            return rep.save(areaint);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de interesse não encontrada");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public void apagarPeloId(@PathVariable Long id) {
         Optional<AreaInteresse> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}