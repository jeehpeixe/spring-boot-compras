package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.rua = :rua")
    List<Cliente> findByRua(@Param("rua") String rua);

    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.cidade = :cidade")
    List<Cliente> findByCidade(@Param("cidade") String cidade);
    
    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.idEndereco = e.idEndereco and e.estado = :estado")
    List<Cliente> findByEstado(@Param("estado") String estado);

}