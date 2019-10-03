package com.springboot.app.clientes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.clientes.models.entity.Cliente;

/**
 * Clase DAO que hereda los métodos CRUD de Cliente
 * 
 * @author msalinas
 *
 */
public interface ClienteDao extends CrudRepository<Cliente, Long> {

}
