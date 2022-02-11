package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Reservas {

	// -----> coleccionReservas (0...*)
	private List<Reserva> coleccionReservas;

	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}

	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {

		List<Reserva> otrasReservas = new ArrayList<>();
		// Recorremos mientras queden elementos
		for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {

			Reserva reserva = it.next();
			otrasReservas.add(new Reserva(reserva));

		}

		return otrasReservas;

	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		}
		// Si la colección no contiene la reserva a introducir
		if (!coleccionReservas.contains(reserva)) {
			// la insertamos
			coleccionReservas.add(new Reserva(reserva));
		} else {
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		}

	}

	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		boolean esMesSiguienteOPosterior = false;

		int mesActual = LocalDate.now().getMonthValue();
		int mesReserva = reserva.getPermanencia().getDia().getMonthValue();
		int añoActual = LocalDate.now().getYear();
		int añoReserva = reserva.getPermanencia().getDia().getYear();

		if ((añoActual == añoReserva && mesActual - mesReserva == -1 || mesActual - mesReserva == -2)
				|| (añoActual - añoReserva == -1 && mesActual - mesReserva == 11 || mesActual - mesReserva == 10
						|| mesActual - mesReserva == 9)) {
			esMesSiguienteOPosterior = true;
		}

		return esMesSiguienteOPosterior;

	}

	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate fechaMes) {

		List<Reserva> reservasMes = new ArrayList<>();

		Month mesReserva = fechaMes.getMonth();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			Reserva reserva = it.next();

			if (reserva.getProfesor().equals(profesor)
					&& reserva.getPermanencia().getDia().getMonth().equals(mesReserva)) {

				reservasMes.add(new Reserva(reserva));
			}

		}

		return reservasMes;

	}

	private Reserva getReservaAulaDia(Aula aula, LocalDate fechaDia) {

		Reserva reserva = null;

		int diaReserva = fechaDia.getDayOfMonth();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			reserva = it.next();
		}
		if (reserva.getAula().equals(aula) && reserva.getPermanencia().getDia().getDayOfMonth() == diaReserva) {
			// La retornamos
			return new Reserva(reserva);

		} else {

			return null;
		}

	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		}
		// Si la colección contiene la reserva
		if (coleccionReservas.contains(reserva)) {
			// La retornamos
			return new Reserva(reserva);

		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		}
		// Si la colección contiene la reserva
		if (coleccionReservas.contains(reserva)) {
			// La eliminamos
			coleccionReservas.remove(reserva);

		} else {
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		}
	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			representacion.add(it.next().toString());
		}
		return representacion;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {

		List<Reserva> reservasProfesor = new ArrayList<>();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			Reserva reserva = it.next();

			if (reserva.getProfesor().equals(profesor)) {

				reservasProfesor.add(new Reserva(reserva));
			}

		}

		return reservasProfesor;

	}

	public List<Reserva> getReservasAula(Aula aula) {

		List<Reserva> reservasAula = new ArrayList<>();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			Reserva reserva = it.next();

			if (reserva.getAula().equals(aula)) {

				reservasAula.add(new Reserva(reserva));
			}

		}

		return reservasAula;

	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {

		List<Reserva> reservasPermanencia = new ArrayList<>();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			Reserva reserva = it.next();

			if (reserva.getPermanencia().equals(permanencia)) {

				reservasPermanencia.add(new Reserva(reserva));
			}

		}

		return reservasPermanencia;

	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		boolean disponible = true;

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			Reserva reserva = it.next();

			if (reserva.getAula().equals(aula) && reserva.getPermanencia().equals(permanencia)) {
				disponible = false;
			}

		}

		return disponible;
	}

}
