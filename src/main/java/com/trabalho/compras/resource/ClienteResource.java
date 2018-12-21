package com.trabalho.compras.resource;

import com.trabalho.compras.model.Cliente;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ClienteResource extends Resource<Cliente> {
    
    public ClienteResource(Cliente content, Link... links) {
        super(content, links);
    }
    
}