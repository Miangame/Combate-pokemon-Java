package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;

/**
 * Clase padre de todos los pokemon
 * 
 * @author Miguel Ã�ngel GavilÃ¡n Merino
 *
 */
public abstract class Pokemon implements Serializable, Comparable<Pokemon> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nombre del pokemmon
	 */
	private String nombre;

	/**
	 * Vida actual
	 */
	private int vida;

	/**
	 * Energía para realizar los ataques
	 */
	private int energia;

	public Pokemon(String nombre, int vida) throws EnergiaNoValidaException {
		super();
		setNombre(nombre);
		setVida(vida);
		setEnergia(100);
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) throws EnergiaNoValidaException {
		if (energia < 0)
			throw new EnergiaNoValidaException("El pokemon no tiene suficiente energía");

		this.energia = energia;
	}

	public String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	void setVida(int vidaActual) {
		this.vida = vidaActual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Pokemon other = (Pokemon) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equalsIgnoreCase(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Pokemon o) {
		return getNombre().compareTo(o.getNombre());
	}

	public abstract Ataque getAtaques(int indice);

	public abstract int getAtaque(Ataque ataque) throws EnergiaNoValidaException;

}