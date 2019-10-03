package com.springboot.app.clientes.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase que representa la tabla Cliente
 * 
 * @author msalinas
 *
 */
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "fecha_nac")
	private Date fechaNac;
	@Transient
	private Integer edad;
	@Transient
	private Date fechaMuerteProbable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Integer getEdad() {
		//LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = new Date(Calendar.getInstance().getTime().getTime()).toLocalDate();
		if(fechaNac != null) {
			LocalDate birthDate = fechaNac.toLocalDate();
			//LocalDate birthDate = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			edad = Period.between(birthDate, currentDate).getYears();
		}		
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaMuerteProbable() {
		return fechaMuerteProbable;
	}

	public void setFechaMuerteProbable(Date fechaMuerteProbable) {
		this.fechaMuerteProbable = fechaMuerteProbable;
	}

	private static final long serialVersionUID = 1L;
}
