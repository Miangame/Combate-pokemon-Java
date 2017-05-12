package funcionalidad;

import java.io.Serializable;
import java.util.ArrayList;

import funcionalidad.excepciones.ElementoNoExisteException;
import funcionalidad.excepciones.ElementoYaExisteException;

/**
 * Clase que maneja una lista de tipo genérico
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 * @param <T>
 */
public class Lista<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lista genérica de elementos
	 */
	private ArrayList<T> lista = new ArrayList<T>();

	/**
	 * Obtiene un elemento según su índice
	 * 
	 * @param index
	 *            indice por el que se va a buscar en la lista.
	 * @return elemento encontrado
	 * @throws ElementoNoExisteException
	 */
	public T get(int index) throws ElementoNoExisteException {
		try {
			return lista.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw new ElementoNoExisteException("El elemento no existe");
		}
	}

	/**
	 * Obtiene un elemento
	 * 
	 * @param elemento
	 *            elemento que se va a buscar
	 * @return elemento encontrado
	 * @throws ElementoNoExisteException
	 *             elemento no existe excepci�n.
	 */
	public T getElemento(T elemento) throws ElementoNoExisteException {
		try {
			return lista.get(obtenerIndice(elemento));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ElementoNoExisteException("El elemento no existe");
		}
	}

	/**
	 * Devuelve el índice de un elemento dado.
	 * 
	 * @param elemento
	 *            elemento del que se quiere obtener el �ndice.
	 * @return Posici�n del elemento a buscar.
	 */
	public int obtenerIndice(T elemento) {
		return lista.indexOf(elemento);
	}

	/**
	 * Devuelve el tama�o de la lista.
	 * 
	 * @return tama�o de la lista.
	 */
	public int size() {
		return lista.size();
	}

	/**
	 * A�ade un elemento al arraylist.
	 * 
	 * @param elemento
	 *            elemento que se quiere a�adir.
	 * @throws ElementoYaExistenteException
	 *             si ya existe, lanza la excepci�n.
	 */
	public void annadir(T elemento) throws ElementoYaExisteException {
		if (lista.contains(elemento))
			throw new ElementoYaExisteException("El elemento ya existe.");
		lista.add(elemento);
	}

	/**
	 * Elimina un elemento del arraylist.
	 * 
	 * @param elemento
	 *            elemento que se quiere eliminar
	 * @return true si se ha eliminado el elemento. False si no se ha eliminado
	 */
	public boolean eliminar(T elemento) {
		return lista.remove(elemento);
	}

}
