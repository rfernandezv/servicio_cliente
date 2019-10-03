package com.springboot.app.clientes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.app.clientes.controllers.ClienteController;
import com.springboot.app.clientes.models.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClienteController.class)
public class ServicioClientesApplicationTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClienteController clienteController;

	@Test
	public void whenFindAll() {
		// given
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Mario");
		cliente1.setApellidos("Salinas");
		entityManager.persist(cliente1);
		entityManager.flush();
		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Alex");
		cliente2.setApellidos("Gamboa");
		entityManager.persist(cliente2);
		entityManager.flush();
		// when
		List<Cliente> clientes = clienteController.listar();
		// then
		assertThat(clientes.get(0)).isEqualTo(cliente1);
		assertThat(clientes.get(1)).isEqualTo(cliente2);
	}

}
