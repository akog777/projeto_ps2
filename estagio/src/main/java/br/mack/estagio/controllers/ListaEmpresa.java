package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Empresa;

@RestController
@RequestMapping("/empresas")
public class ListaEmpresa {
    private List<Empresa> empresas;
    private int idCount = 0;

    ListaEmpresa() {
        empresas = new ArrayList<>();
        empresas.add(new Empresa(idCount++, "Tech Solutions", "12.345.678/0001-99", "tech@solution.com", "(11) 98765-4321", "Av. Paulista, 1000, São Paulo, SP", "Tecnologia"));
        empresas.add(new Empresa(idCount++, "Green Energy", "98.765.432/0001-55", "green@nrg.com", "(21) 91234-5678", "Rua das Flores, 200, Rio de Janeiro, RJ", "Energia Renovável"));
    }
   
    //CREATE
    public Empresa adicionarEmpresa(@RequestBody(required = true) Empresa e) {
        e.setId(idCount++);
        empresas.add(e);
        return e;
    }  

    //READ
    @GetMapping()
    public List<Empresa> listarTodos() {
        return empresas;
    }

    @GetMapping("/{id}")
    public Empresa listarPorID(@PathVariable int id) {
        for (Empresa e : empresas) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Empresa atualizar(@PathVariable int id, @RequestBody(required = true) Empresa e) {
        if(id != e.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < empresas.size(); i++) {
            Pessoa aux = empresas.get(i);
            if(aux.getId() == id){
                empresas.remove(aux);
                empresas.add(e);
                return e;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Empresa apagar(@PathVariable int id) {
        Empresa e = null;
        for (Empresa aux: empresas){
            if(aux.getId() == id){
                e = aux;
            }
        }
        pessoas.remove(e);
        return e;
    }
}