package com.trabalho.compras.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.trabalho.compras.controller.ItemRestController;
import com.trabalho.compras.model.Item;
import com.trabalho.compras.resource.ItemResource;

public class ItemResourceAssembler extends ResourceAssemblerSupport<Item, ItemResource> {

    public ItemResourceAssembler() {
        super(Item.class, ItemResource.class);
    }   

    @Override
    protected ItemResource instantiateResource(Item item) {
        return new ItemResource(item);
    }

    @Override
    public ItemResource toResource(Item item) {
        return new ItemResource(item, linkTo(methodOn(ItemRestController.class).get(item.getIdItem())).withSelfRel());
    }
    
}