package com.trabalho.compras.repository;

import com.trabalho.compras.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ItemRepository extends JpaRepository<Item, Long> {

}