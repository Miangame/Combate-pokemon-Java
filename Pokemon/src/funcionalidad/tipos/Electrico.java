package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo electrico. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Electrico extends Pokemon implements Serializable, Defensable {
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
	private Ataque[] ataquesElectrico = new Ataque[4];

	public Electrico(String nombre, int vida) throws EnergiaNoValidaException {
		super(nombre, vida);
		this.danioBase = 14;
		this.defensa = 11;
		this.precision = 83;
		generarAtaquesElectricos();
	}

	public int getDanio() {
		return danioBase;
	}

	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesElectrico[indice];
		return null;
	}

	/**
	 * Genera 4 ataques para el pokemon de forma aleatoria
	 */
	private void generarAtaquesElectricos() {
		int i = 0;

		ataquesElectrico[i] = Ataque.values()[General.generarAleatorio(8, 15)];
		for (i = 1; i < ataquesElectrico.length; i++) {
			ataquesElectrico[i] = Ataque.values()[General.generarAleatorio(8, 15)];
			for (int j = 0; j < i; j++) {
				if (ataquesElectrico[i] == ataquesElectrico[j]) {
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
		case BOLA_VOLTIO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case CHISPA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		case CHISPAZO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case ELECTROCANION:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case IMPACTRUENO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case ONDA_VOLTIO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case RAYO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		}
		setEnergia(getEnergia() - ataque.getEnergia());
		return danioAtaque;
	}

	/**
	 * Implementado de la interfaz Atacable. Obtiene una defensa segun el tipo
	 * del atacante
	 * 
	 * @return
	 * @throws VidaNoValidaException
	 */
	@Override
	public String getDefensa(Pokemon enemigo, int ataqueEnemigo) {
		String cadena;
		int aleatorio = General.generarAleatorio(0, 100);

		if (aleatorio > precision) {
			return enemigo.getNombre() + " ha fallado el ataque";
		}

		if (enemigo.getClass() == Agua.class || enemigo.getClass() == Volador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Fuego.class || enemigo.getClass() == Electrico.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}
}
