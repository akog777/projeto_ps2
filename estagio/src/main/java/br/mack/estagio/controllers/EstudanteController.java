package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Estudante;

@RestController
public class EstudanteController {
    
    @Autowired
    private EstudanteRepository est;

    //CREATE
    @PostMapping("/estudantes")
    public Estudante criarEstudante(@RequestBody(required = true) Estudante e) {
        if( e.getNome() == null || e.getCPF() == null || e.getEmail() == null || e.getTelefone() == null || e.getCurso() == null ||
            e.getNome().isEmpty() || e.getCPF().isEmpty() || e.getEmail().isEmpty() || e.getTelefone().isEmpty() || e.getCurso().isEmpty()) {
                
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return est.save(e);
    }

    //READ
    @GetMapping()
    public List<Estudante> listarTodos() {
        return estudantes;
    }

   @GetMapping("/estudantes/{id}")
    public Estudante listarPeloId(@PathVariable int id) {
        Optional<Estudante> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    
    //UPDATE
    @PutMapping("/estudantes/{id}")
    public Estudante atualizar(@PathVariable int id, @RequestBody(required = true) Estudante e) {
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
            return rep.save(u);
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