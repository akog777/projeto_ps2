package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Estudante;

@RestController
@RequestMapping("/pessoas")
public class ListaEstudante {
    private List<Estudante> alunos;
    private int idCount = 0;

    ListaEstudante() {
        alunos = new ArrayList<>();
        alunos.add(new Estudante(idCount++, "Ana", "123.456.789-00", "Medicina", "ana@email.com", "(11) 91234-5678", "Clínica Geral"));
        alunos.add(new Estudante(idCount++, "Bia", "987.654.321-00", "Engenharia", "bia@email.com", "(11) 99876-5432", "Civil"));
    }
    //CREATE
    @GetMapping()
    public List<Estudante> todosOsAlunos() {
        return alunos;
    }



    @GetMapping("/{id}")
    public Pessoa lerPorID(@PathVariable int id) {
        for (int i=0; i<pessoas.size(); i++) {
            if (pessoas.get(i).getId() == id) {
                return pessoas.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
    }

    //READ
    @PostMapping()
    public Pessoa adicionarPessoa(@RequestBody(required = true) Pessoa p) {
        p.setId(idCount++);
        pessoas.add(p);
        return p;
    }  

    //UPDATE
    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable int id, @RequestBody(required = true) Pessoa p) {
        if(id != p.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa aux = pessoas.get(i);
            if(aux.getId() == id){
                pessoas.remove(aux);
                pessoas.add(p);
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Estudante apagar(@PathVariable int id) {
        Estudante e = null;
        for (Estudante aux: pessoas){
            if(aux.getId() == id){
                p = aux;
            }
        }
        pessoas.remove(p);
        return p;
    }
}