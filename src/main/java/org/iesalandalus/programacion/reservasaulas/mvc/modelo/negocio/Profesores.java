package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	// -----> coleccionProfesores (0...*)
	private List<Profesor> coleccionProfesores;

	public Profesores() {
		coleccionProfesores = new ArrayList<>();
	}

	public Profesores(Profesores profesores) {
		setProfesores(profesores);
	}

	private void setProfesores(Profesores profesores) {
		if (profesores == null) {
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		}
		coleccionProfesores = copiaProfundaProfesores(profesores.coleccionProfesores);
	}

	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}

	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {

		List<Profesor> otrosProfesores = new ArrayList<>();
		// Recorremos mientras queden elementos
		for (Iterator<Profesor> it = profesores.iterator(); it.hasNext();) {

			Profesor profesor = it.next();
			otrosProfesores.add(new Profesor(profesor));

		}

		return otrosProfesores;

	}

	public int getNumProfesores() {
		return coleccionProfesores.size();
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		// Si la colección no contiene el profesor a introducir
		if (!coleccionProfesores.contains(profesor)) {
			// lo insertamos
			coleccionProfesores.add(new Profesor(profesor));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}

	}

	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		// Si la colección contiene el profesor
		if (coleccionProfesores.contains(profesor)) {
			// La retornamos
			return new Profesor(profesor);

		} else {
			return null;
		}
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		// Si la colección contiene el profesor
		if (coleccionProfesores.contains(profesor)) {
			// La eliminamos
			coleccionProfesores.remove(profesor);

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();

		for (Iterator<Profesor> it = getProfesores().iterator(); it.hasNext();) {

			representacion.add(it.next().toString());
		}
		return representacion;
	}

}
