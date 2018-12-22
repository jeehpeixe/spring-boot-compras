package com.trabalho.compras.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "pedidos")
public class Pedido {

    @Id @GeneratedValue
    Long idPedido;
    
    Double total;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataCriacao;
    
    @ManyToOne
    Cliente cliente;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Item> itens;

}