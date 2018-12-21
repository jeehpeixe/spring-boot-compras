package com.trabalho.compras.controller;

import java.util.List;

import com.trabalho.compras.model.Endereco;
import com.trabalho.compras.repository.EnderecoRepository;
import com.trabalho.compras.resource.EnderecoResource;
import com.trabalho.compras.assembler.EnderecoResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/enderecosctrl")
public class EnderecoRestController {
    
    @Autowired
    EnderecoRepository repository;

    EnderecoResourceAssembler assembler = new EnderecoResourceAssembler();
    
    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os enderecos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EnderecoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca o endereco do ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);

        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Adiciona um novo endereco")
    @PostMapping
    public ResponseEntity<EnderecoResource> create(@RequestBody Endereco endereco) {
        endereco = repository.save(endereco);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera o endereco do ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResource> update(@PathVariable Long id, @RequestBody Endereco endereco) {
        if (endereco != null) {
            endereco.setIdEndereco(id);
            endereco = repository.save(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Exclui o endereco do ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResource> delete(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            repository.delete(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}