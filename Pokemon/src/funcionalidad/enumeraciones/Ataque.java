package funcionalidad.enumeraciones;

/**
 * Enumeración con ataques
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public enum Ataque {

	/**
	 * Ataques de tipo agua
	 */
	PISTOLA_AGUA(15), HIDROBOMBA(30), RAYO_BURBUJA(28), SURF(29), HIDRO_CANON(30), ACUA_COLA(20), CASCADA(25), HIDROPULSO(27),

	/**
	 * Ataques de tipo eléctrico
	 */
	BOLA_VOLTIO(25), CHISPA(15), CHISPAZO(20), ELECTROCANION(30), IMPACTRUENO(29), ONDA_VOLTIO(28), RAYO(30), TRUENO(29),

	/**
	 * Ataques de tipo fuego
	 */
	ASCUAS(15), GIRO_FUEGO(23), LANZALLAMAS(26), LLAMARADA(21), ONDA_IGNEA(20), RUEDA_FUEGO(28), HUMAREDA(27), PIROTECNIA(30),

	/**
	 * Ataques de tipo planta
	 */
	HOJA_AFILADA(25), LATIGAZO(15), RAYO_SOLAR(30), FOGONAZO(26), ENERGIBOLA(27), CUCHILLA_SOLAR(23), DANZA_PETALO(20), HOJA_AGUDA(21),

	/**
	 * Ataques de tipo volador
	 */
	ACROBATA(30), AIRE_AFILADO(23), ATAQUE_AEREO(15), ATAQUE_ALA(22), GOLPE_AEREO(25), PICO_TALADRO(24), PICOTAZO(28), TORNADO(21);

	private int energia;

	private Ataque(int energia) {
		this.energia = energia;
	}

	public int getEnergia() {
		return energia;
	}
}
