package com.trabalho.compras.resource;

import com.trabalho.compras.model.Produto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ProdutoResource extends Resource<Produto> {
    
    public ProdutoResource(Produto content, Link... links) {
        super(content, links);
    }
    
}