package com.trabalho.compras.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.trabalho.compras.controller.EnderecoRestController;
import com.trabalho.compras.model.Endereco;
import com.trabalho.compras.resource.EnderecoResource;

public class EnderecoResourceAssembler extends ResourceAssemblerSupport<Endereco, EnderecoResource> {

    public EnderecoResourceAssembler() {
        super(Endereco.class, EnderecoResource.class);
    }   

    @Override
    protected EnderecoResource instantiateResource(Endereco endereco) {
        return new EnderecoResource(endereco);
    }

    @Override
    public EnderecoResource toResource(Endereco endereco) {
        return new EnderecoResource(endereco, linkTo(methodOn(EnderecoRestController.class).get(endereco.getIdEndereco())).withSelfRel());
    }
    
}