package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.interfaces.Atacable;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo agua. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Agua extends Pokemon implements Serializable, Atacable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Daño
	 */
	private int danioBase;

	/**
	 * Defensa
	 */
	private int defensa;

	/**
	 * Precisión del pokemon
	 */
	private int precision;

	/**
	 * Array de ataques
	 */
	private Ataque[] ataquesAgua = new Ataque[4];

	public Agua(String nombre, int vida) {
		super(nombre, vida);
		this.danioBase = 15;
		this.defensa = 10;
		this.precision = 80;
		generarAtaquesAgua();
	}

	public int getDanio() {
		return danioBase;
	}

	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesAgua[indice];
		return null;
	}

	/**
	 * Genera 4 ataques para el pokemon de forma aleatoria
	 */
	private void generarAtaquesAgua() {
		int i = 0;

		ataquesAgua[i] = Ataque.values()[General.generarAleatorio(0, 7)];
		for (i = 1; i < ataquesAgua.length; i++) {
			ataquesAgua[i] = Ataque.values()[General.generarAleatorio(0, 7)];
			for (int j = 0; j < i; j++) {
				if (ataquesAgua[i] == ataquesAgua[j]) {
					i--;
				}
			}
		}
	}

	/**
	 * Implementado de la interfaz Atacable. Obtiene un valor para cada ataque
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getAtaque(Ataque ataque) {
		int danioAtaque = 0;
		switch (ataque) {
		case PISTOLA_AGUA:
		case HIDROBOMBA:
		case RAYO_BURBUJA:
		case SURF:
		case HIDRO_CANON:
		case ACUA_COLA:
		case CASCADA:
		case HIDROPULSO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90);

		}
		return danioAtaque;
	}

	/**
	 * Implementado de la interfaz Atacable. Obtiene una defensa segun el tipo
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
