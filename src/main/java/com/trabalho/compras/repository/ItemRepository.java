package com.trabalho.compras.repository;

import com.trabalho.compras.model.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}