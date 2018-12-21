package com.trabalho.compras;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.trabalho.compras.controller.ClienteRestController;
import com.trabalho.compras.model.Cliente;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ComprasApplication.class)
@WebMvcTest(ClienteRestController.class)
public class ClienteRestControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;
	
	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/clientesctrl"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].idCliente", equalTo(1)))
				.andExpect(jsonPath("$.[0].nome", equalTo("Jessica")));
	}
	
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/clientesctrl/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.idCliente", equalTo(1)))
				.andExpect(jsonPath("$.nome", equalTo("Jessica")));
	}

	@Test
	public void testCreate() throws Exception {
		Cliente cliente = new Cliente(2l,"Lucas","lucas@teste.com","765.098.287-11",null,1l);
		mockMvc.perform(post("/clientesctrl").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonParser.writeValueAsString(cliente)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.idCliente", equalTo(2)))
				.andExpect(jsonPath("$.nome", equalTo("Lucas")));
	}
	
}