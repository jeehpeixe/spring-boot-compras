package com.trabalho.compras;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.trabalho.compras.model.Cliente;
import com.trabalho.compras.model.Endereco;
import com.trabalho.compras.model.Item;
import com.trabalho.compras.model.Pedido;
import com.trabalho.compras.model.Produto;
import com.trabalho.compras.repository.ClienteRepository;
import com.trabalho.compras.repository.EnderecoRepository;
import com.trabalho.compras.repository.ItemRepository;
import com.trabalho.compras.repository.PedidoRepository;
import com.trabalho.compras.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@Import(SpringDataRestConfiguration.class)
public class ComprasApplication {

	@Autowired
	EnderecoRepository repositorioEndereco;

	@Autowired
	ClienteRepository repositorioCliente;
	
	@Autowired
	ProdutoRepository repositorioProduto;
	
	@Autowired
	PedidoRepository repositorioPedido;
	
	@Autowired
	ItemRepository repositorioItem;

	@PostConstruct
	public void init() throws ParseException {
		Endereco endereco = new Endereco(1l, "XV", "Rio do Sul", "SC", "89145-034");
		repositorioEndereco.save(endereco);

		Cliente cliente = new Cliente(1l, "Jessica", "jessica@peixe.com", "043.234.456-50", new Date(), endereco);
		repositorioCliente.save(cliente);

		Produto produto1 = new Produto(1l, "produto 1", "descr do produto aaa", "royal", 100.00);
		repositorioProduto.save(produto1);
		
		Produto produto2 = new Produto(2l, "produto 2", "descr do produto bbb", "royal", 120.00);
		repositorioProduto.save(produto2);

		Pedido pedido = new Pedido(1l, 220.00, new Date(), cliente, null);
		List<Item> itens = new ArrayList<>();
		itens.add(new Item(1l, 1, 100.00, produto1));
		itens.add(new Item(2l, 1, 120.00, produto2));
		pedido.setItens(itens);
		repositorioPedido.save(pedido);
	}

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

}

