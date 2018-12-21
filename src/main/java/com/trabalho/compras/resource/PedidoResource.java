package com.trabalho.compras.resource;

import com.trabalho.compras.model.Pedido;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class PedidoResource extends Resource<Pedido> {
    
    public PedidoResource(Pedido content, Link... links) {
        super(content, links);
    }
    
}