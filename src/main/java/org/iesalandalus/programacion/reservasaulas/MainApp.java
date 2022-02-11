package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

/*
 * @Author: José Antonio Del Rey Martínez, IES AL-ÁNDALUS, ALMERÍA
 * Tarea Online 5
 */
public class MainApp {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

}