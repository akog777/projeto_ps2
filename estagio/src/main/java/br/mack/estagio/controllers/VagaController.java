package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Vaga;

@RestController
public class VagaController {
    @Autowired
    private VagaRepository rep;

    //CREATE
    @PostMapping("/vagas")
    public Vaga adicionarVaga(@RequestBody(required = true) Vaga v) {
        if( v.getTitulo() == null || v.getDescricao() == null || v.getRequisitos() == null || v.getBeneficios() == null ||
            v.getTitulo().isEmpty() || v.getDescricao().isEmpty() || v.getRequisitos().isEmpty() || v.getBeneficios().isEmpty()) {
                
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(v);
    }
    
    //READ
    @GetMapping()
    public List<Vaga> listarTodos() {
        return vaga;
    }

    @GetMapping("/vagas/{id}")
    public Vaga listarPorID(@PathVariable int id) {
        Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
    }
    
    //UPDATE
    @PutMapping("/vagas/{id}")
    public Vaga atualizar(@PathVariable int id, @RequestBody(required = true) Vaga v) {
        if(id != v.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }

        Optional<Empresa> optional = rep.findById(id);
        if(optional.isPresent()) {
            Vagas vag = optional.get();
            vag.setTitulo(novosDados.getTitulo());
            vag.setDescricao(novosDados.getDescricao());
            vag.setArea(novosDados.getArea());
            vag.setEmail(novosDados.getEmail());
            vag.setLocalizacao(novosDados.getLocalizacao());
            vag.setModalidade(novosDados.getModalidade());
            vag.setCargaHoraria(novosDados.getCargaHoraria());
            vag.setRequisitos(novosDados.getRequisitos());
            return rep.save(u);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");

    }

    //DELETE
    @DeleteMapping("/vagas/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<Vaga> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}