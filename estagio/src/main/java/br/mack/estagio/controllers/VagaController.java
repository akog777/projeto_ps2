package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.estagio.repository.*;
import br.mack.estagio.entities.*;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    
    @Autowired
    private VagaRepository rep;

    @Autowired
    private AreaInteresseRepository areaRep;

    @Autowired
    private EmpresaRepository empresaRep;

    //CREATE
    @PostMapping("")
    public Vaga criarVaga(@RequestBody(required = true) Vaga novaVaga) {
        if( novaVaga.getTitulo() == null || novaVaga.getDescricao() == null ||
            novaVaga.getLocalizacao() == null || novaVaga.getModalidade() == null ||
            novaVaga.getCargaHoraria() == null || novaVaga.getRequisitos() == null ||
            novaVaga.getTitulo().isEmpty() || novaVaga.getDescricao().isEmpty() ||
            novaVaga.getLocalizacao().isEmpty() || novaVaga.getModalidade().isEmpty() ||
            novaVaga.getCargaHoraria().isEmpty() || novaVaga.getRequisitos().isEmpty() ||
            novaVaga.getAreaInteresse() == null || novaVaga.getAreaInteresse().getId() == null ||
            novaVaga.getEmpresa() == null || novaVaga.getEmpresa().getId() == null) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        AreaInteresse area = areaRep.findById(novaVaga.getAreaInteresse().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área não encontrada"));
        Empresa emp = empresaRep.findById(novaVaga.getEmpresa().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        novaVaga.setAreaInteresse(area);
        novaVaga.setEmpresa(emp);

        return rep.save(novaVaga);
    }
    
    //READ
    @GetMapping("")
    public List<Vaga> listarTodos() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public Vaga listarPeloId(@PathVariable Long id) {
        Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
    }
    
    //UPDATE
    @PutMapping("/{id}")
    public Vaga atualizar(@RequestBody Vaga novosDados, @PathVariable Long id) {
        if(novosDados.getId() == null || !novosDados.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }

        Optional<Vaga> optional = rep.findById(id);
        if(optional.isPresent()) {
            Vaga vaga = optional.get();
            vaga.setTitulo(novosDados.getTitulo());
            vaga.setDescricao(novosDados.getDescricao());
            vaga.setArea(novosDados.getArea());
            vaga.setLocalizacao(novosDados.getLocalizacao());
            vaga.setModalidade(novosDados.getModalidade());
            vaga.setCargaHoraria(novosDados.getCargaHoraria());
            vaga.setRequisitos(novosDados.getRequisitos());
            return rep.save(vaga);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public void apagarPeloId(@PathVariable Long id) {
         Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}