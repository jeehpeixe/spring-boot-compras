package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Pedido;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findByDataCriacao(@Param("dataCriacao") String dataCriacao);
        
}