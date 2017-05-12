package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.interfaces.Atacable;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo volador. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Volador extends Pokemon implements Serializable, Atacable, Defensable {
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
	 * Precisión
	 */
	private int precision;

	/**
	 * Array de ataques
	 */
	private Ataque[] ataquesVolador = new Ataque[4];

	public Volador(String nombre, int vida) {
		super(nombre, vida);
		this.danioBase = 16;
		this.defensa = 9;
		this.precision = 81;
		generarAtaquesVolador();
	}

	public int getDanio() {
		return danioBase;
	}

	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesVolador[indice];
		return null;
	}

	/**
	 * Genera 4 ataques para el pokemon
	 */
	private void generarAtaquesVolador() {
		int i = 0;

		ataquesVolador[i] = Ataque.values()[General.generarAleatorio(32, 39)];
		for (i = 1; i < ataquesVolador.length; i++) {
			ataquesVolador[i] = Ataque.values()[General.generarAleatorio(32, 39)];
			for (int j = 0; j < i; j++) {
				if (ataquesVolador[i] == ataquesVolador[j]) {
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
		case ACROBATA:
		case AIRE_AFILADO:
		case ATAQUE_AEREO:
		case ATAQUE_ALA:
		case GOLPE_AEREO:
		case PICO_TALADRO:
		case PICOTAZO:
		case TORNADO:
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
		if (enemigo.getClass() == Agua.class || enemigo.getClass() == Fuego.class
				|| enemigo.getClass() == Volador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		} else if (enemigo.getClass() == Electrico.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}
}
