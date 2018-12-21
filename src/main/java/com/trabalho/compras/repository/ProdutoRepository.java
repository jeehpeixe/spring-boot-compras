package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Produto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    List<Produto> findByNomeContaining(@Param("nome") String nome);
    
    List<Produto> findByMarca(@Param("marca") String marca);
    
}