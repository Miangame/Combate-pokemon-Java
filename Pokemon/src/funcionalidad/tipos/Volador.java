package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo volador. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Volador extends Pokemon implements Serializable, Defensable {
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

	public Volador(String nombre, int vida) throws EnergiaNoValidaException {
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
	 * 
	 * @throws EnergiaNoValidaException
	 */
	@Override
	public int getAtaque(Ataque ataque) throws EnergiaNoValidaException {
		int danioAtaque = 0;
		switch (ataque) {
		case ACROBATA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case AIRE_AFILADO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case ATAQUE_AEREO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		case ATAQUE_ALA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case GOLPE_AEREO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case PICO_TALADRO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case PICOTAZO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 4;
			break;
		}
		setEnergia(getEnergia() - ataque.getEnergia());
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
