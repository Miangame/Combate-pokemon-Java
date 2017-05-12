package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.enumeraciones.Ataque;

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

	public Pokemon(String nombre, int vida) {
		super();
		setNombre(nombre);
		setVida(vida);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	protected void setVida(int vidaActual) {
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

	public abstract int getAtaque(Ataque ataque);

}