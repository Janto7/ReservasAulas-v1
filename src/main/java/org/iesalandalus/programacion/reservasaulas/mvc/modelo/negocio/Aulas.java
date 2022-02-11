package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	// -----> coleccionAulas (0...*)
	private List<Aula> coleccionAulas;

	public Aulas() {
		coleccionAulas = new ArrayList<>();
	}

	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
	}

	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {

		List<Aula> otrasAulas = new ArrayList<>();
		// Recorremos mientras queden elementos
		for (Iterator<Aula> it = aulas.iterator(); it.hasNext();) {

			Aula aula = it.next();
			otrasAulas.add(new Aula(aula));

		}

		return otrasAulas;

	}

	public int getNumAulas() {
		return coleccionAulas.size();
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		// Si la colección no contiene el aula a introducir
		if (!coleccionAulas.contains(aula)) {
			// la insertamos
			coleccionAulas.add(new Aula(aula));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}

	}

	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		// Si la colección contiene el aula
		if (coleccionAulas.contains(aula)) {
			// La retornamos
			return new Aula(aula);

		} else {
			return null;
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		// Si la colección contiene el aula
		if (coleccionAulas.contains(aula)) {
			// La eliminamos
			coleccionAulas.remove(aula);

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();

		for (Iterator<Aula> it = getAulas().iterator(); it.hasNext();) {

			representacion.add(it.next().toString());
		}
		return representacion;
	}

}
