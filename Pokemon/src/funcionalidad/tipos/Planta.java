package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo planta. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Planta extends Pokemon implements Serializable, Defensable {
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
	@Override
	public int getAtaque(Ataque ataque) {
		int danioAtaque = 0;
		switch (ataque) {
		case HOJA_AFILADA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		case LATIGAZO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 4;
			break;
		case RAYO_SOLAR:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case FOGONAZO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90);
			break;
		case ENERGIBOLA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case CUCHILLA_SOLAR:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case DANZA_PETALO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
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
