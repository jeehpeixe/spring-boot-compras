package com.trabalho.compras.controller;

import java.util.List;

import com.trabalho.compras.model.Produto;
import com.trabalho.compras.repository.ProdutoRepository;
import com.trabalho.compras.resource.ProdutoResource;
import com.trabalho.compras.assembler.ProdutoResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/produtosctrl")
public class ProdutoRestController {
    
    @Autowired
    ProdutoRepository repository;

    ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();
    
    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os produtos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProdutoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca o produto do ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto produto = repository.findOne(id);

        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Adiciona um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
        produto = repository.save(produto);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera o produto do ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto != null) {
            produto.setIdProduto(id);
            produto = repository.save(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Exclui o produto do ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            repository.delete(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os produtos pelo nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repository.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os produtos pela marca")
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
        return new ResponseEntity<>(assembler.toResources(repository.findByMarca(marca)), HttpStatus.OK);
    }
    
}