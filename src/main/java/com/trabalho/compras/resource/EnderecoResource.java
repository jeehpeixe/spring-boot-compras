package com.trabalho.compras.resource;

import com.trabalho.compras.model.Endereco;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class EnderecoResource extends Resource<Endereco> {
    
    public EnderecoResource(Endereco content, Link... links) {
        super(content, links);
    }
    
}