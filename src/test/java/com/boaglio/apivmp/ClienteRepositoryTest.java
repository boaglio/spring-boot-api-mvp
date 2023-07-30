package com.boaglio.apivmp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ClienteRepositoryTest {

	@Autowired
	ClienteRepository repository;

	@Test
	@DisplayName("Busca clientes cadastrados")
	public void buscaClientesCadastrados() {

		Page<Cliente> clientes = this.repository.findAll(PageRequest.of(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);
	}

	@Test
	@DisplayName("Busca um cliente cadastrado")
	public void buscaClienteMachadoDeAssis() {
		
		Optional<Cliente> clienteNaoEncontradoOpt = this.repository.findById(-1L);
		assertTrue(clienteNaoEncontradoOpt.isEmpty());

		Optional<Cliente> clienteOpt = this.repository.findById(1L);
		assertTrue(clienteOpt.isPresent());
		var cliente = clienteOpt.get();
		assertThat(cliente).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Machado de Assis");
		assertThat(cliente.getCidadeNascimento()).isEqualTo("Rio de Janeiro");
		
	}
 
}