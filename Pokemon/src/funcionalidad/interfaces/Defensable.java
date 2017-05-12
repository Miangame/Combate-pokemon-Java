package funcionalidad.interfaces;

import funcionalidad.tipos.Pokemon;

/**
 * Interfaz que calcula la defensa
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public interface Defensable {
	String getDefensa(Pokemon enemigo, int danoAtaqueEnemigo);
}
