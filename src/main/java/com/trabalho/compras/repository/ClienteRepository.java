package com.trabalho.compras.repository;

import java.util.List;

import com.trabalho.compras.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEndereco_Rua(@Param("rua") String rua);

    List<Cliente> findByEndereco_Cidade(@Param("cidade") String cidade);
    
    List<Cliente> findByEndereco_Estado(@Param("estado") String estado);

}