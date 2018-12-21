package com.trabalho.compras.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Cliente {

@Id @GeneratedValue
    Long idCliente;
    String nome;
    String email;
    String cpf;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataNascimento;

    Long idEndereco;

}