package org.sfaci.holadb4o.base;

import java.util.Date;

/**
 * Clase que representa una tienda
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Tienda {

	private String nombre;
	private String descripcion;
	private int numeroLocal;
	private Date fechaApertura;
	
	private CentroComercial centro;
	
	public Tienda() {}
	
	public Tienda(String nombre, String descripcion, int numeroLocal,
			Date fechaApertura, CentroComercial centro) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numeroLocal = numeroLocal;
		this.fechaApertura = fechaApertura;
		this.centro = centro;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getNumeroLocal() {
		return numeroLocal;
	}
	public void setNumeroLocal(int numeroLocal) {
		this.numeroLocal = numeroLocal;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public CentroComercial getCentro() {
		return centro;
	}
	public void setCentro(CentroComercial centro) {
		this.centro = centro;
	}
	
}
