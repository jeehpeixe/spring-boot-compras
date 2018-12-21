package com.trabalho.compras.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.trabalho.compras.controller.ProdutoRestController;
import com.trabalho.compras.model.Produto;
import com.trabalho.compras.resource.ProdutoResource;

public class ProdutoResourceAssembler extends ResourceAssemblerSupport<Produto, ProdutoResource> {

    public ProdutoResourceAssembler() {
        super(Produto.class, ProdutoResource.class);
    }   

    @Override
    protected ProdutoResource instantiateResource(Produto produto) {
        return new ProdutoResource(produto);
    }

    @Override
    public ProdutoResource toResource(Produto produto) {
        return new ProdutoResource(produto, linkTo(methodOn(ProdutoRestController.class).get(produto.getIdProduto())).withSelfRel());
    }
    
}