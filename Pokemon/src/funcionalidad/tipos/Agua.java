package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo agua. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Agua extends Pokemon implements Serializable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Array de ataques
	 */
	private Ataque[] ataquesAgua;

	private static final int MINIMO = 0;
	private static final int MAXIMO = 7;

	public Agua(String nombre, int vida) throws EnergiaNoValidaException {
		super(nombre, vida);
		danioBase = 15;
		defensa = 10;
		precision = 80;
		ataquesAgua = generarAtaques(MINIMO, MAXIMO);
	}

	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesAgua[indice];
		return null;
	}

	/**
	 * Obtiene un valor para cada ataque
	 * 
	 * @throws EnergiaNoValidaException
	 */
	@Override
	public int getAtaque(Ataque ataque) throws EnergiaNoValidaException {
		int danioAtaque = 0;
		switch (ataque) {
		case PISTOLA_AGUA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case HIDROBOMBA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 13;
			break;
		case RAYO_BURBUJA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		case SURF:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case HIDRO_CANON:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case ACUA_COLA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case CASCADA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;

		}
		setEnergia(getEnergia() - ataque.getEnergia());
		return danioAtaque;
	}

	/**
	 * Implementado de la interfaz Defensable. Obtiene una defensa según el tipo
	 * del atacante
	 * 
	 * @throws VidaNoValidaException
	 */
	@Override
	public String getDefensa(Pokemon enemigo, int ataqueEnemigo) {
		String cadena;
		int aleatorio = General.generarAleatorio(0, 100);

		if (aleatorio > precision) {
			return enemigo.getNombre() + " ha fallado el ataque";
		}

		if (enemigo.getClass() == Agua.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre() + "\n"
					+ getNombre() + ": " + getVida() + "PS";
		} else if (enemigo.getClass() == Fuego.class) {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Planta.class || enemigo.getClass() == Electrico.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}

}
