package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo fuego. Hijo que hereda de Pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Fuego extends Pokemon implements Serializable, Defensable {
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
	private Ataque[] ataquesFuego = new Ataque[4];

	public Fuego(String nombre, int vida) throws EnergiaNoValidaException {
		super(nombre, vida);
		this.danioBase = 16;
		this.defensa = 9;
		this.precision = 82;
		generarAtaquesFuego();
	}

	public int getDanio() {
		return danioBase;
	}

	@Override
	public Ataque getAtaques(int indice) {
		if (indice >= 0 && indice <= 4)
			return ataquesFuego[indice];
		return null;
	}

	/**
	 * Genera 4 ataques para el pokemon
	 */
	private void generarAtaquesFuego() {
		int i = 0;

		ataquesFuego[i] = Ataque.values()[General.generarAleatorio(16, 23)];
		for (i = 1; i < ataquesFuego.length; i++) {
			ataquesFuego[i] = Ataque.values()[General.generarAleatorio(16, 23)];
			for (int j = 0; j < i; j++) {
				if (ataquesFuego[i] == ataquesFuego[j]) {
					i--;
				}
			}
		}
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
		case ASCUAS:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case GIRO_FUEGO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case LANZALLAMAS:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		case LLAMARADA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case ONDA_IGNEA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case RUEDA_FUEGO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90);
			break;
		case HUMAREDA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
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
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Fuego.class || enemigo.getClass() == Electrico.class
				|| enemigo.getClass() == Volador.class) {
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
