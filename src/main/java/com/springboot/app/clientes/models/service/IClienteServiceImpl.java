package com.springboot.app.clientes.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.clientes.models.dao.ClienteDao;
import com.springboot.app.clientes.models.entity.Cliente;

/**
 * Clase para implementar las operaciones de bd relacionadas con cliente
 * 
 * @author msalinas
 *
 */
@Service("serviceRestTemplate")
public class IClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteDao clienteDao;

	/**
	 * Método para realizar la operación de buscar todos los clientes
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	/**
	 * Método para realizar la operación de buscar los clientes por su id
	 */
	@Override
	public Cliente findById(Long id) {
		return (Cliente) clienteDao.findById(id).orElse(null);
	}

	/**
	 * Método para realizar la operación de guardar un cliente
	 */
	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	/**
	 * Método para realizar la operación de borrar un cliente por su id
	 */
	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}

}
