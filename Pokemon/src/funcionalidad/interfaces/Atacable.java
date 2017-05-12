package funcionalidad.interfaces;

import funcionalidad.enumeraciones.Ataque;

/**
 * Interfaz que calcula el daño de ataque
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public interface Atacable {
	int getAtaque(Ataque ataque);
}
