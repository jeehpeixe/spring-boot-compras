package com.trabalho.compras.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.trabalho.compras.controller.PedidoRestController;
import com.trabalho.compras.model.Pedido;
import com.trabalho.compras.resource.PedidoResource;

public class PedidoResourceAssembler extends ResourceAssemblerSupport<Pedido, PedidoResource> {

    public PedidoResourceAssembler() {
        super(Pedido.class, PedidoResource.class);
    }   

    @Override
    protected PedidoResource instantiateResource(Pedido pedido) {
        return new PedidoResource(pedido);
    }

    @Override
    public PedidoResource toResource(Pedido pedido) {
        return new PedidoResource(pedido, linkTo(methodOn(PedidoRestController.class).get(pedido.getIdPedido())).withSelfRel());
    }
    
}