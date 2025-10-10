package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Admin;

@RestController
@RequestMapping("/admins")
public class ListaAdmin {

    private List<Admin> admins;
    private int idCount = 0;

    ListaAdmin() {
        admins = new ArrayList<>();
        admins.add(new Admin(idCount++, "Carlos", "111.222.333-44", "carlos@email.com", "(11) 93456-7890", "Recursos Humanos"));
        admins.add(new Admin(idCount++, "Mariana", "555.666.777-88", "mari@email.com", "(11) 94567-8901", "Tecnologia da Informação"));
    }
    
    //CREATE
    public Admin adicionarAdmin(@RequestBody(required = true) Admin a) {
        a.setId(idCount++);
        admins.add(a);
        return a;
    }  

    //READ
    @GetMapping()
    public List<Admin> listarTodos() {
        return admins;
    }

    @GetMapping("/{id}")
    public Admin listarPorID(@PathVariable int id) {
        for (Admin a : admins) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Admin atualizar(@PathVariable int id, @RequestBody(required = true) Admin a) {
        if(id != a.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < admins.size(); i++) {
            Pessoa aux = admins.get(i);
            if(aux.getId() == id){
                admins.remove(aux);
                admins.add(a);
                return a;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Admin apagar(@PathVariable int id) {
        Admin a = null;
        for (Admin aux: admins){
            if(aux.getId() == id){
                a = aux;
            }
        }
        admins.remove(a);
        return a;
    }
}