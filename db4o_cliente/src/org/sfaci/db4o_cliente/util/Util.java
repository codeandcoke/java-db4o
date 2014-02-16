package org.sfaci.db4o_cliente.util;

import com.db4o.ObjectContainer;

/**
 * Clase de utilidad
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Util {

	// La Base de Datos
	public static ObjectContainer db;
	
	public enum Accion {
		ACEPTAR, CANCELAR
	}
}
