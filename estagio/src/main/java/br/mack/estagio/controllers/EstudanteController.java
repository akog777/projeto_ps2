package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Estudante;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
    
    private List<Estudante> estudantes;
    private int idCount = 0;

    ListaEstudante() {
        estudantes = new ArrayList<>();
        estudantes.add(new Estudante(idCount++, "Ana", "123.456.789-00", "Medicina", "ana@email.com", "(11) 91234-5678", "Clínica Geral"));
        estudantes.add(new Estudante(idCount++, "Bia", "987.654.321-00", "Engenharia", "bia@email.com", "(11) 99876-5432", "Civil"));
    }
    
    //CREATE
    public Estudante adicionarEstudante(@RequestBody(required = true) Estudante e) {
        e.setId(idCount++);
        estudantes.add(e);
        return e;
    }  

    //READ
    @GetMapping()
    public List<Estudante> listarTodos() {
        return estudantes;
    }

    @GetMapping("/{id}")
    public Estudante listarPorID(@PathVariable int id) {
        for (Estudante e : estudantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Estudante atualizar(@PathVariable int id, @RequestBody(required = true) Estudante e) {
        if(id != e.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < estudantes.size(); i++) {
            Pessoa aux = estudantes.get(i);
            if(aux.getId() == id){
                estudantes.remove(aux);
                estudantes.add(e);
                return e;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Estudante apagar(@PathVariable int id) {
        Estudante e = null;
        for (Estudante aux: estudantes){
            if(aux.getId() == id){
                e = aux;
            }
        }
        pessoas.remove(e);
        return e;
    }
}