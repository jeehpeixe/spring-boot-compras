package com.trabalho.compras.controller;

import java.util.List;

import com.trabalho.compras.model.Item;
import com.trabalho.compras.repository.ItemRepository;
import com.trabalho.compras.resource.ItemResource;
import com.trabalho.compras.assembler.ItemResourceAssembler;

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
@RequestMapping("/itensctrl")
public class ItemRestController {
    
    @Autowired
    ItemRepository repository;

    ItemResourceAssembler assembler = new ItemResourceAssembler();
    
    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os itens")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ItemResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca o item do ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<ItemResource> get(@PathVariable Long id) {
        Item item = repository.findOne(id);

        if (item != null) {
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Adiciona um novo item ao pedido")
    @PostMapping
    public ResponseEntity<ItemResource> create(@RequestBody Item item) {
        item = repository.save(item);
        if (item != null) {
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera o item do ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<ItemResource> update(@PathVariable Long id, @RequestBody Item item) {
        if (item != null) {
            item.setIdItem(id);
            item = repository.save(item);
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Exclui o item do ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResource> delete(@PathVariable Long id) {
        Item item = repository.findOne(id);
        if (item != null) {
            repository.delete(item);
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}