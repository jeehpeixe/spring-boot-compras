package com.trabalho.compras.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import com.trabalho.compras.model.Cliente;
import com.trabalho.compras.model.Endereco;
import com.trabalho.compras.repository.ClienteRepository;
import com.trabalho.compras.resource.ClienteResource;
import com.trabalho.compras.assembler.ClienteResourceAssembler;

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
@RequestMapping("/clientesctrl")
public class ClienteRestController {
    
    @Autowired
    ClienteRepository repository;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();
    
    @Secured("ROLE_USER")
    @ApiOperation("Retorna a lista de clientes")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ClienteResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @ApiOperation("Busca o cliente do ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);

        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Adiciona um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
        cliente = repository.save(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera o cliente do ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (cliente != null) {
            cliente.setIdCliente(id);
            cliente = repository.save(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Exclui o cliente do ID informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            repository.delete(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os clientes pela rua do endereco")
    @GetMapping("/endereco/rua/{rua}")
    public ResponseEntity<List<ClienteResource>> findByRua(@PathVariable String rua) {
        return new ResponseEntity<>(assembler.toResources(repository.findByEndereco_Rua(rua)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os clientes pela cidade do endereco")
    @GetMapping("/endereco/cidade/{cidade}")
    public ResponseEntity<List<ClienteResource>> findByCidade(@PathVariable String cidade) {
        return new ResponseEntity<>(assembler.toResources(repository.findByEndereco_Cidade(cidade)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Busca os clientes pelo estado do endereco")
    @GetMapping("/endereco/estado/{estado}")
    public ResponseEntity<List<ClienteResource>> findByEstado(@PathVariable String estado) {
        return new ResponseEntity<>(assembler.toResources(repository.findByEndereco_Estado(estado)), HttpStatus.OK);
    }
}