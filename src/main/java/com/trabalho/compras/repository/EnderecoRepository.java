package com.trabalho.compras.repository;

import com.trabalho.compras.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}