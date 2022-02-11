package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido";
	private static final String CORREO_VALIDO = "Correo válido";

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		controlador.terminar();
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
			/*
			 * Capturamos las excepciones de la clase Aula y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente.");
			/*
			 * Capturamos las excepciones de la clase Aula y las del método borrar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAula());
			String mensaje = (aula != null) ? aula.toString() : "Aula no registrada en el sistema.";
			System.out.println(mensaje);
			/*
			 * Capturamos las excepciones de la clase Aula y la del método buscar.
			 */
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");

		List<String> aulas = controlador.representarAulas();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (aulas.size() > 0) {
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				String aula = it.next();
				System.out.println(aula);
			}

		} else {
			System.out.println(ERROR + "No hay aulas que listar.Debe insertar primero un aula en el sistema.");
		}

	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
			/*
			 * Capturamos las excepciones de la clase Profesor y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Profesor borrado correctamente.");
			/*
			 * Capturamos las excepciones de la clase Profesor y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje = (profesor != null) ? profesor.toString()
					: ERROR + "El profesor no está registrado en el sistema.";
			System.out.println(mensaje);
			/*
			 * Capturamos las excepciones de la clase Profesor y la del método buscar.
			 */
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de Profesores");

		List<String> profesores = controlador.representarProfesores();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (profesores.size() > 0) {
			for (Iterator<String> it = profesores.iterator(); it.hasNext();) {
				String profesor = it.next();
				System.out.println(profesor);
			}
		} else {
			System.out
					.println(ERROR + "No hay profesores que listar. Debe insertar primero un profesor en el sistema.");
		}
	}

	public void realizarReserva() {

		try {

			Profesor profesor = null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("Reserva insertada correctamente, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO + ".");
			// Capturamos todas las posibles excepciones al hacer una reserva
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Utilizo el método leerNombreProfesor de la clase Consola para realizar una
	 * lectura de reserva limpia, donde solo se pidan los datos concretos para
	 * realizar la reserva.(Omitimos datos innecesarios como tener que introducir su
	 * correo y teléfono, que ya estan registrados en el sistema)
	 */
	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Realizar Reserva");

		String nombreAula;
		String nombreProfesor;
		List<String> profesores = controlador.representarProfesores();
		List<String> aulas = controlador.representarAulas();
		String correoProfesor = new String();
		String correoProfesorLimpio = new String();

		Reserva reserva = null;
		Aula aula = null;
		Permanencia permanencia = null;
		boolean aulaRegistrada = false;
		boolean profesorRegistrado = false;
		try {
			nombreProfesor = Consola.leerNombreProfesor();
			nombreAula = Consola.leerNombreAula();

			for (Iterator<String> it = profesores.iterator(); it.hasNext();) {
				String datosProfesores = it.next();

				/*
				 * Comparo el nombre introducido por teclado, con el toString de profesores,
				 * valiendome de los métodos indexof(), que me extraen la cadena exacta necesito
				 * comparar.
				 */
				if (nombreProfesor.equalsIgnoreCase(
						datosProfesores.substring(datosProfesores.indexOf('=') + 1, datosProfesores.indexOf(',')))) {
					profesorRegistrado = true;

					/*
					 * Obtengo el correo del profesor valiendome de los métodos indexOf y
					 * lastIndexOf, que me extraen la cadena exacta del correo del profesor.
					 *
					 */
					correoProfesor = datosProfesores.substring(datosProfesores.indexOf('=') + 1,
							datosProfesores.lastIndexOf(','));
					/*
					 * elimino de la cadena los datos sobrantes para tener un correo limpio para
					 * poder pasarlo por parametro.
					 */
					correoProfesorLimpio = correoProfesor.replace(nombreProfesor + ", correo=", "");

				}
			}
			/*
			 * Repito el mismo proceso para validar el aula existe en el sistema, esta vez
			 * valiendome del método replace() para quedarme con la cadena deseada.
			 */
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				String nombresAulas = it.next();
				if (nombresAulas.toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aula = new Aula(nombreAula);
					aulaRegistrada = true;

				}
			}

			if (profesorRegistrado && aulaRegistrada) {
				permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

				Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorLimpio);

				reserva = new Reserva(profesorALeer, aula, permanencia);

			} else if (!profesorRegistrado) {
				System.out.println(ERROR + "No está registrado el profesor: " + nombreProfesor + " en el sistema.");

			} else if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula: " + nombreAula + " en el sistema.");

			}

		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return reserva;

	}

	public void anularReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {

			Profesor profesor = null;
			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Reserva anulada correctamente, " + NOMBRE_VALIDO + CORREO_VALIDO + ".");
			// Capturamos todas las posibles excepciones al anunlar la reserva
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {

		Consola.mostrarCabecera("Listado de Reservas");

		List<String> reservas = controlador.representarProfesores();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (reservas.size() > 0) {
			for (Iterator<String> it = reservas.iterator(); it.hasNext();) {
				String reserva = it.next();
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar. Debe insertar primero una reserva.");
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listado de Reservas por Aula");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAula());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();
			
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha aula.");
		}
	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesor());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();
			
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicho profesor.");
		}
	}

	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listado de Reservas por Permanencia");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		List<Reserva> reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();
			
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha permanencia.");
		}
	}

	public void consultarDisponibilidad() {

		Consola.mostrarCabecera("Consultar Disponibilidad");
		String nombreAula;
		String nombresAulas;
		List<String> aulas = controlador.representarAulas();
		LocalDate dia;
		Tramo tramo;

		boolean aulaRegistrada = false;

		try {

			nombreAula = Consola.leerNombreAula();
			// recorro el array
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				nombresAulas = it.next();
				/*
				 * Comparo el nombre del aula este registrado en el sistema, utilizo el método
				 * replace para conseguir la cadena deseada.
				 */
				if (nombresAulas.toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aulaRegistrada = true;

					Aula aulaAConsultar = new Aula(nombreAula);
					dia = Consola.leerDia();
					tramo = Consola.leerTramo();
					Permanencia permanencia = new Permanencia(dia, tramo);

					if (controlador.consultarDisponibilidad(aulaAConsultar, permanencia) == true) {
						System.out.println("Disponible el aula " + nombreAula + " para reservar el día " + dia
								+ " en el tramo de " + tramo + ".");
					} else {
						System.out.println("No Disponible el aula " + nombreAula + " para reservar el día " + dia
								+ " en el tramo de " + tramo + ".");
					}

				}
			}

			if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula " + nombreAula + " en el sistema.");

			}

		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());

		}

	}
}
