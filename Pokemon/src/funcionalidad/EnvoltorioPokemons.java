package funcionalidad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.*;

/**
 * Clase que maneja un envoltorio de pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class EnvoltorioPokemons implements Serializable, Iterable<Pokemon> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lista de pokemons
	 */
	private ArrayList<Pokemon> lista = new ArrayList<Pokemon>();

	/**
	 * Archivo donde se almacenarán los pokemons
	 */
	private File file = new File("pokemons.obj");

	/**
	 * Obtiene un elemento según su índice
	 * 
	 * @param index
	 *            indice por el que se va a buscar en la lista.
	 * @return elemento encontrado
	 * @throws ElementoNoExisteException
	 */
	public Pokemon get(int index) throws PokemonNoExisteException {
		try {
			return lista.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw new PokemonNoExisteException("El elemento no existe");
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
	public Pokemon getElemento(Pokemon elemento) throws PokemonNoExisteException {
		try {
			return lista.get(obtenerIndice(elemento));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new PokemonNoExisteException("El elemento no existe");
		}
	}

	public Pokemon getPokemonNombre(String nombre) throws PokemonNoExisteException {
		Pokemon pokemonEncontrado = null;
		for (Pokemon pokemon : lista) {
			if (pokemon.getNombre().equalsIgnoreCase(nombre)) {
				pokemonEncontrado = pokemon;
			}
		}
		if (pokemonEncontrado != null) {
			return pokemonEncontrado;
		} else {
			throw new PokemonNoExisteException("El pokemon no existe");
		}
	}

	public ArrayList<Pokemon> getLista() {
		Collections.sort(lista);
		return lista;
	}

	/**
	 * Devuelve el índice de un elemento dado.
	 * 
	 * @param elemento
	 *            elemento del que se quiere obtener el �ndice.
	 * @return Posici�n del elemento a buscar.
	 */
	public int obtenerIndice(Pokemon elemento) {
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
	 * @throws PokemonYaExisteException
	 * 
	 */
	public void annadir(Pokemon elemento) throws PokemonYaExisteException {
		if (lista.contains(elemento))
			throw new PokemonYaExisteException("Pokemons repetidos.");

		lista.add(elemento);
	}

	/**
	 * Elimina un elemento del arraylist.
	 * 
	 * @param elemento
	 *            elemento que se quiere eliminar
	 * @throws PokemonNoExisteException
	 */
	public void eliminar(Pokemon elemento) throws PokemonNoExisteException {
		if (!lista.remove(elemento))
			throw new PokemonNoExisteException("No existe el pokemon");
	}

	/**
	 * Escribe el arrayList de pokemons en un fichero
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void escribir() throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(lista);
		}
	}

	/**
	 * Lee de un fichero los pokemons y los almacena en un arrayList
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void leer() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			lista = (ArrayList<Pokemon>) in.readObject();
		}
	}

	/**
	 * Dice si la lista está o no vacía
	 * 
	 * @return true si la lista está vacía o false si no lo está
	 */
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	/**
	 * Devuelve los pokemons de un tipo determinado
	 * 
	 * @param tipo
	 *            Tipo de los pokemons
	 * @return ArrayList con los pokemons
	 * @throws PokemonNoExisteException
	 */
	public ArrayList<Pokemon> getPokemonsTipo(Object tipo) throws PokemonNoExisteException {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (Pokemon pokemon : lista) {
			if (pokemon.getClass().getSimpleName().equals(tipo)) {
				pokemons.add(pokemon);
			}
		}
		if (pokemons.size() == 0) {
			throw new PokemonNoExisteException("No se ha encontrado ningún pokemon");
		}
		return pokemons;
	}

	@Override
	public Iterator<Pokemon> iterator() {
		return lista.iterator();
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (Pokemon pokemon : lista) {
			cadena.append(pokemon + "\n");
		}

		return cadena.toString();
	}

}
