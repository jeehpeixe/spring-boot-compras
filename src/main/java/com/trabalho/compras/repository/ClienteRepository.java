package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Cliente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.rua = :rua")
    List<Cliente> findByRua(@Param("rua") String rua);

    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.cidade = :cidade")
    List<Cliente> findByCidade(@Param("cidade") String cidade);
    
    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.estado = :estado")
    List<Cliente> findByEstado(@Param("estado") String estado);

}