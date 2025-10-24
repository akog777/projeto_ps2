package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repository.*;
import br.mack.estagio.entities.*;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
    
    @Autowired
    private EstudanteRepository rep;

    @Autowired
    private AreaInteresseRepository areaRep;

    //CREATE
    @PostMapping("")
    public Estudante criarEstudante(@RequestBody(required = true) Estudante novoEstudante) {
        if( novoEstudante.getNome() == null || novoEstudante.getCPF() == null || novoEstudante.getCurso() == null || 
            novoEstudante.getEmail() == null || novoEstudante.getTelefone() == null ||
            novoEstudante.getNome().isEmpty() || novoEstudante.getCPF().isEmpty() || novoEstudante.getCurso().isEmpty() || 
            novoEstudante.getEmail().isEmpty() || novoEstudante.getTelefone().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novoEstudante);
    }

    //READ
    @GetMapping("")
    public List<Estudante> listarTodos() {
        return rep.findAll();
    }

    @PostMapping("/{id}/areas-interesse/{areaId}")
    public ResponseEntity<?> adicionarAreaInteresse(@PathVariable Long id, @PathVariable Long areaId) {
        Optional<Estudante> estudanteOpt = rep.findById(id);
        Optional<AreaInteresse> areaOpt = areaRep.findById(areaId);

        if (estudanteOpt.isEmpty() || areaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Estudante estudante = estudanteOpt.get();
        AreaInteresse area = areaOpt.get();

        estudante.getAreasInteresse().add(area);
        rep.save(estudante);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/areas-interesse/{areaId}")
    public ResponseEntity<?> removerAreaInteresse(@PathVariable Long id, @PathVariable Long areaId) {
        Optional<Estudante> estudanteOpt = rep.findById(id);
        Optional<AreaInteresse> areaOpt = areaRep.findById(areaId);

        if (estudanteOpt.isEmpty() || areaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Estudante estudante = estudanteOpt.get();
        AreaInteresse area = areaOpt.get();

        estudante.getAreasInteresse().remove(area);
        rep.save(estudante);

        return ResponseEntity.ok().build();
    }

   @GetMapping("/{id}")
    public Estudante listarPeloId(@PathVariable Long id) {
        Optional<Estudante> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    
    //UPDATE
    @PutMapping("/{id}")
    public Estudante atualizarPeloId(@RequestBody Estudante novosDados, @PathVariable Long id) {
        if(novosDados.getId() == null || !novosDados.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        Optional<Estudante> optional = rep.findById(id);
        if(optional.isPresent()) {
            Estudante est = optional.get();
            est.setNome(novosDados.getNome());
            est.setCPF(novosDados.getCPF());
            est.setCurso(novosDados.getCurso());
            est.setEmail(novosDados.getEmail());
            est.setTelefone(novosDados.getTelefone());
            est.setAreasInteresse(novosDados.getAreasInteresse());
            return rep.save(est);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public void apagarPeloId(@PathVariable Long id) {
         Optional<Estudante> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}