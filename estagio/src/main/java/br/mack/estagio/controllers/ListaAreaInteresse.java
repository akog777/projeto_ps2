package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.AreaInteresse;

@RestController
@RequestMapping("/areas")
public class ListaAreaInteresse {

    private List<AreaInteresse> AreaInteresses;
    private int idCount = 0;

    ListaAreaInteresse() {
        AreaInteresses = new ArrayList<>();
        AreaInteresses.add(new AreaInteresse(idCount++, "Clínica Geral"));
        
    }
    
    //CREATE
    public AreaInteresse adicionarAreaInteresse(@RequestBody(required = true) AreaInteresse a) {
        a.setId(idCount++);
        AreaInteresses.add(a);
        return a;
    }  

    //READ
    @GetMapping()
    public List<AreaInteresse> listarTodos() {
        return AreaInteresses;
    }

    @GetMapping("/{id}")
    public AreaInteresse listarPorID(@PathVariable int id) {
        for (AreaInteresse a : AreaInteresses) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AreaInteresse não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public AreaInteresse atualizar(@PathVariable int id, @RequestBody(required = true) AreaInteresse a) {
        if(id != a.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < AreaInteresses.size(); i++) {
            Pessoa aux = AreaInteresses.get(i);
            if(aux.getId() == id){
                AreaInteresses.remove(aux);
                AreaInteresses.add(a);
                return a;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AreaInteresse não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public AreaInteresse apagar(@PathVariable int id) {
        AreaInteresse a = null;
        for (AreaInteresse aux: AreaInteresses){
            if(aux.getId() == id){
                a = aux;
            }
        }
        AreaInteresses.remove(a);
        return a;
    }
}