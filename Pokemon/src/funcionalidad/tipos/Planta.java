package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.interfaces.Atacable;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo planta. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Planta extends Pokemon implements Serializable, Atacable, Defensable {
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
	private Ataque[] ataquesPlanta = new Ataque[4];

	public Planta(String nombre, int vida) {
		super(nombre, vida);
		this.danioBase = 13;
		this.defensa = 9;
		this.precision = 85;
		generarAtaquesPlanta();
	}

	public int getDanio() {
		return danioBase;
	}

	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesPlanta[indice];
		return null;
	}

	/**
	 * Genera 4 ataques para el pokemon
	 */
	private void generarAtaquesPlanta() {
		int i = 0;

		ataquesPlanta[i] = Ataque.values()[General.generarAleatorio(24, 31)];
		for (i = 1; i < ataquesPlanta.length; i++) {
			ataquesPlanta[i] = Ataque.values()[General.generarAleatorio(24, 31)];
			for (int j = 0; j < i; j++) {
				if (ataquesPlanta[i] == ataquesPlanta[j]) {
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
		case HOJA_AFILADA:
		case LATIGAZO:
		case RAYO_SOLAR:
		case FOGONAZO:
		case ENERGIBOLA:
		case CUCHILLA_SOLAR:
		case DANZA_PETALO:
		case HOJA_AGUDA:
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
		if (enemigo.getClass() == Fuego.class || enemigo.getClass() == Volador.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Planta.class || enemigo.getClass() == Electrico.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
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
