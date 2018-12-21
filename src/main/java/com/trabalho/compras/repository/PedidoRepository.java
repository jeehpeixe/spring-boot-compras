package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataCriacao(@Param("dataCriacao") String dataCriacao);
        
}