package org.sfaci.holadb4o.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un Centro Comercial
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class CentroComercial {

	private String nombre;
	private String direccion;
	private float extension;
	private Date fechaConstruccion;
	private List<Tienda> tiendas;
	
	public CentroComercial() {
		tiendas = new ArrayList<Tienda>();
	}
	
	public CentroComercial(String nombre, String direccion, float extension,
			Date fechaConstruccion, List<Tienda> tiendas) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.extension = extension;
		this.fechaConstruccion = fechaConstruccion;
		this.tiendas = tiendas;

	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public float getExtension() {
		return extension;
	}
	public void setExtension(float extension) {
		this.extension = extension;
	}
	public Date getFechaConstruccion() {
		return fechaConstruccion;
	}
	public void setFechaConstruccion(Date fechaConstruccion) {
		this.fechaConstruccion = fechaConstruccion;
	}
	public List<Tienda> getTiendas() {
		return tiendas;
	}
	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
	
	
}
