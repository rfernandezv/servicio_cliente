package com.springboot.app.clientes.models.service;

import java.util.List;

import com.springboot.app.clientes.models.entity.Cliente;

/**
 * Interface para definir las operaciones de bd relacionadas con cliente
 * 
 * @author msalinas
 *
 */
public interface IClienteService {

	/**
	 * Definición de método para buscar todos los clientes
	 * 
	 * @return
	 */
	public List<Cliente> findAll();

	/**
	 * Definición de método para buscar los clientes por su id
	 * 
	 * @param id
	 * @return
	 */
	public Cliente findById(Long id);

	/**
	 * Definición de método para guardar un cliente
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente save(Cliente cliente);

	/**
	 * Definición de método para borrar un cliente por su id
	 * 
	 * @param id
	 * @return
	 */
	public void deleteById(Long id);
}
