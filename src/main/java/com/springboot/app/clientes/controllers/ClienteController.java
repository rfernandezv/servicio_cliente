package com.springboot.app.clientes.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.clientes.models.entity.Cliente;
import com.springboot.app.clientes.models.service.IClienteService;
import com.springboot.app.clientes.resources.vo.EstadisticasVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el microservicio de Cliente
 * 
 * @author msalinas
 *
 */
@RestController
@RequestMapping("/api/cliente")
@Api(tags = "cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	/**
	 * Servicio que devuelve el listado de clientes incluyendo su fecha probable de
	 * muerte
	 * 
	 * @return
	 */
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Cliente", notes = "Servicio para listar todos los clientes")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Clientes encontrados"),
			@ApiResponse(code = 404, message = "Clientes no encontrados") })
	public List<Cliente> listar() {
		return clienteService.findAll().stream().map(p -> {
			// calcular años restantes menor a 100 años de edad
			Random random = new Random();
			int tiempoRestante = random.nextInt(100 - p.getEdad());
			LocalDate fechaMuerteProb = p.getFechaNac().toLocalDate().plusYears(tiempoRestante);
			p.setFechaMuerteProbable(Date.valueOf(fechaMuerteProb));
			return p;
		}).collect(Collectors.toList());
	}

	/**
	 * Servicio que devuelve la media y desviación estándar de la edad de todos los
	 * clientes
	 * 
	 * @return
	 */
	@GetMapping("/estadisticas")
	@ApiOperation(value = "Calcular Estadisticas", notes = "Servicio para calcular la media y desviación estandar de la edad de todos los clientes")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cálculo exitoso"),
			@ApiResponse(code = 404, message = "Error en el cálculo") })
	public EstadisticasVO calcularEstadisticas() {
		Double media = clienteService.findAll().stream().collect(Collectors.averagingDouble(Cliente::getEdad));
		Double sumatoria = clienteService.findAll().stream().mapToDouble((v) -> Math.pow(v.getEdad() - media, 2)).sum();
		Long count = clienteService.findAll().stream().count();
		Double desviacionEstandar = Math.sqrt(sumatoria / count);

		EstadisticasVO estVO = new EstadisticasVO();
		estVO.setMedia(media);
		if(count > 0) {
			estVO.setDesviacionEstandar(desviacionEstandar);
		}

		return estVO;
	}

	/**
	 * Servicio para agregar nuevos clientes
	 * 
	 * @param cliente
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/crear")
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida") })
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) throws Exception {
		// VALIDAR MAYORIA DE EDAD
		if (cliente.getEdad() >= 18) {
			return clienteService.save(cliente);
		} else {
			throw new Exception("El cliente debe ser mayor de edad");
		}
	}
}
