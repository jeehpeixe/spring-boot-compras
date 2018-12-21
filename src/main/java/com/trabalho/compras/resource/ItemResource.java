package com.trabalho.compras.resource;

import com.trabalho.compras.model.Item;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ItemResource extends Resource<Item> {
    
    public ItemResource(Item content, Link... links) {
        super(content, links);
    }
    
}