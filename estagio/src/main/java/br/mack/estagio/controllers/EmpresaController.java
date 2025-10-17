package br.mack.estagio.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.estagio.entities.Empresa;

@RestController
public class EmpresaController {

    @Autowired
    private EmpresaRepository rep;
    
    //CREATE
    @PostMapping("/empresas")
    public Empresa criarEmpresa(@RequestBody Empresa novaEmpresa) {
        if( novaEmpresa.getNome() == null || novaEmpresa.getCNPJ() == null || novaEmpresa.getEmail() == null || 
            novaEmpresa.getTelefone() == null || novaEmpresa.getEndereco() == null || novaEmpresa.getAreaAtuacao() == null ||
            novaEmpresa.getNome().isEmpty() || novaEmpresa.getCNPJ().isEmpty() || novaEmpresa.getEmail().isEmpty() || 
            novaEmpresa.getTelefone().isEmpty() || novaEmpresa.getEndereco().isEmpty() || 
            novaEmpresa.getAreaAtuacao().isEmpty()) {
                
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return rep.save(novaEmpresa);
    }

    //READ
    @GetMapping()
    public List<Empresa> listarTodos() {
        return Empresa;
    }

    @GetMapping("/empresas/{id}")
    public Empresa listarPorID(@PathVariable int id) {
        Optional<Empresa> optional = rep.findById(id);
        
        if(optional.isPresent()) return optional.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
    }

    
    //UPDATE
    @PutMapping("/empresas/{id}")
    public Empresa atualizarEmpresaPeloId(@RequestBody Empresa novosDados, @PathVariable int id) {
        
        Optional<Empresa> optional = rep.findById(id);
        if(optional.isPresent()) {
            Empresa emp = optional.get();
            emp.setNome(novosDados.getNome());
            emp.setCNPJ(novosDados.getCNPJ());
            emp.setEmail(novosDados.getEmail());
            emp.setTelefone(novosDados.getTelefone());
            emp.setEndereco(novosDados.getEndereco());
            emp.setAreaAtuacao(novosDados.getAreaAtuacao());
            return rep.save(u);
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");

    }

    //DELETE
    @DeleteMapping("/empresas/{id}")
    public void apagarPeloId(@PathVariable int id) {
         Optional<Empresa> optional = rep.findById(id);
        
        if(optional.isPresent()) rep.delete(optional.get());
    }
}