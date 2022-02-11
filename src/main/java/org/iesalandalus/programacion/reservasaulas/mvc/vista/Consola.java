package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {
	}

	public static void mostrarMenu() {
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {
		
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Aula leerAula() {
		System.out.print("Introduce el nombre del aula: ");
		String nombre = Entrada.cadena();
		return new Aula(nombre);
	}

	public static String leerNombreAula() {
	
		System.out.print("Introduce el nombre del aula: ");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

	public static Profesor leerProfesor() {
		
			System.out.print("Introduce el nombre del profesor: ");
			String nombre = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce el teléfono del profesor: ");
		String telefono = Entrada.cadena();
		Profesor profesor = (telefono == null || telefono.trim().equals(""))
				? new Profesor(nombre, correo)
				: new Profesor(nombre, correo, telefono);

		return profesor;
	}

	public static String leerNombreProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;
	}

	public static Tramo leerTramo() {
		System.out.print("Introduce el tramo de la reserva (0.- Mañana, 1.- Tarde): ");
		int tramoReserva = Entrada.entero();
		Tramo tramo = null;
		if (tramoReserva < 0 || tramoReserva >= Tramo.values().length) {
			System.out.println("ERROR: La opción elegida no corresponde con ningún tramo.");
		} else {
			tramo = Tramo.values()[tramoReserva];
		}
		return tramo;
	}

	public static LocalDate leerDia() {

		LocalDate dia = null;

		System.out.printf("Introduza una fecha(dd/MM/yyyy):");
		String fechaStr = Entrada.cadena();
		try {
			dia = LocalDate.parse(fechaStr, FORMATO_DIA);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es correcto. Formato correcto (dd/MM/yyyy)");
		}

		return dia;
	}

}