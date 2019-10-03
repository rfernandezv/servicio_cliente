package com.springboot.app.clientes.resources.vo;

/**
 * Clase que representa los resultados Estadisticos
 * 
 * @author msalinas
 *
 */
public class EstadisticasVO {
	private Double media;
	private Double desviacionEstandar;

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Double getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(Double desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

}
