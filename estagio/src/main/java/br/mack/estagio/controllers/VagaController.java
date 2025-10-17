package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Vaga;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private List<Vaga> vagas;
    private int idCount = 0;

    ListaVaga() {
        vagas = new ArrayList<>();
        vagas.add(new Vaga(idCount++, "Desenvolvedor Java", "Vaga para desenvolvedor Java com experiência em Spring Boot", "Tecnologia", "São Paulo, SP", "Presencial", "40 horas semanais", "Java, Spring Boot, SQL"));
        vagas.add(new Vaga(idCount++, "Analista de Marketing", "Vaga para analista de marketing digital", "Marketing", "Remoto", "Remoto", "30 horas semanais", "SEO, Google Ads, Redes Sociais"));
    }
    
    //CREATE
    public Vaga adicionarVaga(@RequestBody(required = true) Vaga v) {
        v.setId(idCount++);
        vagas.add(v);
        return v;
    }  

    //READ
    @GetMapping()
    public List<Vaga> listarTodos() {
        return vagas;
    }

    @GetMapping("/{id}")
    public Vaga listarPorID(@PathVariable int id) {
        for (Vaga v : vagas) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Vaga atualizar(@PathVariable int id, @RequestBody(required = true) Vaga v) {
        if(id != v.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < vagas.size(); i++) {
            Pessoa aux = vagas.get(i);
            if(aux.getId() == id){
                vagas.remove(aux);
                vagas.add();
                return v;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Vaga apagar(@PathVariable int id) {
        Vaga v = null;
        for (Vaga aux: vagas){
            if(aux.getId() == id){
                v = aux;
            }
        }
        vagas.remove(v);
        return v;
    }
}