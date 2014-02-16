package org.sfaci.db4o_cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.sfaci.db4o_cliente.base.Tienda;
import org.sfaci.db4o_cliente.util.Constantes;
import org.sfaci.db4o_cliente.util.Util;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

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
		
		// Lista todos los objetos del tipo que se pasa como par�metro
		List<Tienda> tiendas = Util.db.query(Tienda.class);
		cargarFilas(tiendas);
	}
	
	public void listar(final String filtro, int campo) {
		
		Tienda tienda = null;
		List<Tienda> tiendas = null;
		
		switch (campo) {
		case Constantes.C_TODOS:
			tiendas = Util.db.query(new Predicate<Tienda>() {
				@Override
				public boolean match(Tienda tienda) {
					
					// Ver la parte '4.2.6 Native Query Performance' en el tutorial
					// No se puede utilizar 'c�digo arbitrario' con implementanciones cliente-servidor a trav�s de la red
					if (tienda.getNombre().contains(filtro) || tienda.getDescripcion().contains(filtro))
						return true;
					
					return false;
				}
			});
			break;
		case Constantes.C_NOMBRE:
			tienda = new Tienda();
			tienda.setNombre(filtro);
			tiendas = Util.db.queryByExample(tienda);
			break;
		case Constantes.C_DESCRIPCION:
			tienda = new Tienda();
			tienda.setDescripcion(filtro);
			tiendas = Util.db.queryByExample(tienda);
			break;
		case Constantes.C_NUMERO_LOCAL:
			tienda = new Tienda();
			tienda.setNumeroLocal(Integer.parseInt(filtro));
			tiendas = Util.db.queryByExample(tienda);
			break;
		case Constantes.C_FECHA_APERTURA:
			try {
				tienda = new Tienda();
				tienda.setFechaApertura(new SimpleDateFormat().parse(filtro));
				tiendas = Util.db.queryByExample(tienda);
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
			break;
		default:
		}
		
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
	
	public Tienda getTiendaSeleccionada() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Tienda tienda = new Tienda();
		tienda.setNombre(nombre);
		// Se asume que no existen dos tiendas con el mismo nombre.
		// As� se puede contar con que la consulta s�lo devuelve un resultado
		ObjectSet<Tienda> resultado = Util.db.queryByExample(tienda);
		tienda = resultado.next();
		
		return tienda;
	}

	/**
	* Elimina todo el contenido del control JTable
	*/
	public void vaciar() {

		modeloDatos.setNumRows(0);
	}

}
