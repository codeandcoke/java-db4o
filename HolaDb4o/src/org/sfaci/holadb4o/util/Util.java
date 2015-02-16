package org.sfaci.holadb4o.util;

import com.db4o.ObjectContainer;

/**
 * Clase de utilidad
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class Util {

	// La Base de Datos
	public static ObjectContainer db;
	
	public enum Accion {
		ACEPTAR, CANCELAR
	}
}
