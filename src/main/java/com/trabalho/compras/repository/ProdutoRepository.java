package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContaining(@Param("nome") String nome);
    
    List<Produto> findByMarca(@Param("marca") String marca);
    
}