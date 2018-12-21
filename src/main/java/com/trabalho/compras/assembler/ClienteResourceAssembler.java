package com.trabalho.compras.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.trabalho.compras.controller.ClienteRestController;
import com.trabalho.compras.model.Cliente;
import com.trabalho.compras.resource.ClienteResource;

public class ClienteResourceAssembler extends ResourceAssemblerSupport<Cliente, ClienteResource> {

    public ClienteResourceAssembler() {
        super(Cliente.class, ClienteResource.class);
    }   

    @Override
    protected ClienteResource instantiateResource(Cliente cliente) {
        return new ClienteResource(cliente);
    }

    @Override
    public ClienteResource toResource(Cliente cliente) {
        return new ClienteResource(cliente, linkTo(methodOn(ClienteRestController.class).get(cliente.getIdCliente())).withSelfRel());
    }
    
}