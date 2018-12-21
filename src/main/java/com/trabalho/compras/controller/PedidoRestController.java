package com.trabalho.compras.controller;

import java.util.List;

import com.trabalho.compras.model.Pedido;
import com.trabalho.compras.repository.PedidoRepository;
import com.trabalho.compras.resource.PedidoResource;
import com.trabalho.compras.assembler.PedidoResourceAssembler;

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
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/pedidosctrl")
public class PedidoRestController {
    
    @Autowired
    PedidoRepository repository;

    PedidoResourceAssembler assembler = new PedidoResourceAssembler();
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca todos os pedidos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PedidoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca o pedido do ID informado")
    @ApiParam(name = "id", value = "ID do pedido", required = true)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResource> get(@PathVariable Long id) {
        Pedido pedido = repository.findOne(id);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Adiciona um novo pedido")
    @PostMapping
    public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {
        pedido = repository.save(pedido);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera o pedido do ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResource> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (pedido != null) {
            pedido.setIdPedido(id);
            pedido = repository.save(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Exclui o pedido do ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoResource> delete(@PathVariable Long id) {
        Pedido pedido = repository.findOne(id);
        if (pedido != null) {
            repository.delete(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os pedidos pela data de criação")
    @GetMapping("/dataCriacao/{dataCriacao}")
    public ResponseEntity<List<PedidoResource>> findByDataCriacao(@PathVariable String dataCriacao) {
        return new ResponseEntity<>(assembler.toResources(repository.findByDataCriacao(dataCriacao)), HttpStatus.OK);
    }
    
}