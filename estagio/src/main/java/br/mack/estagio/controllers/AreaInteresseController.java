package br.mack.estagio.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.AreaInteresse;

@RestController
public class AreaInteresseController {

    @Autowired
    private AreaInteresseRepository rep;
    
    //CREATE
    @PostMapping("/areas")
    public AreaInteresse criarAreaInteresse(@RequestBody AreaInteresse novaArea) {
        if(novaArea.getTitulo() == null || novaArea.getTitulo().isEmpty() || 
            novaArea.getDescricao() == null || novaArea.getDescricao().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novaArea);
    }

    //READ
    @GetMapping()
    public List<AreaInteresse> listarTodos() {
        return areaInteresse;
    }

    @GetMapping("/areas/{id}")
    public AreaInteressa listarPeloId(@PathVariable int id) {
        Optional<AreaInteresse> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de interesse não encontrada");
    }

    
    //UPDATE
    @PutMapping("/areas/{id}")
    public User atualizarAreaPeloId(@RequestBody AreaInteresse novosDados, @PathVariable int id) {
        
        Optional<AreaInteresse> optional = rep.findById(id);
        if(optional.isPresent()) {
            AreaInteresse arm = optional.get();
            arm.setTitulo(novosDados.getTitulo());
            arm.setDescricao(novosDados.getDescricao);
            return rep.save(u);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de intersse não encontrada");

    }

    //DELETE
    @DeleteMapping("/areas/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<AreaInteresse> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}