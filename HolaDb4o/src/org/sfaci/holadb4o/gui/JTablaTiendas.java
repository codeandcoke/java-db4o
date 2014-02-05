package org.sfaci.holadb4o.gui;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.sfaci.holadb4o.base.Tienda;
import org.sfaci.holadb4o.util.Constantes;
import org.sfaci.holadb4o.util.Util;

/**
 * Tabla que lista datos de Tiendas
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class JTablaTiendas extends JTable {

	private DefaultTableModel modeloDatos;

	public JTablaTiendas() {
		super();
	
		inicializar();
	}

	/**
	* Inicializa la tabla, creando las columnas
	*/
	private void inicializar() {

		modeloDatos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
	
		modeloDatos.addColumn(Constantes.NOMBRE);
		modeloDatos.addColumn(Constantes.DESCRIPCION);
		modeloDatos.addColumn(Constantes.NUMERO_LOCAL);
		modeloDatos.addColumn(Constantes.FECHA_APERTURA);
	
		setModel(modeloDatos);
	}
	
	/**
	 * Lista todas las tiendas en la tabla
	 */
	public void listar() {
		
		List<Tienda> tiendas = Util.db.query(Tienda.class);
		cargarFilas(tiendas);
	}

	/*
	* 'Pinta' los datos en el JTable
	*/
	private void cargarFilas(List<Tienda> tiendas) {

		modeloDatos.setNumRows(0);
	
		for (Tienda tienda : tiendas) {
			Object[] fila = new Object[]{
					tienda.getNombre(),
					tienda.getDescripcion(),
					String.valueOf(tienda.getNumeroLocal()),
					tienda.getFechaApertura().toString()};
			
			modeloDatos.addRow(fila);
		}
	}

	/**
	* Elimina todo el contenido del control JTable
	*/
	public void vaciar() {

		modeloDatos.setNumRows(0);
	}

}
