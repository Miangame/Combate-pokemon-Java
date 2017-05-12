package funcionalidad;

/**
 * Clase que tiene metodos comunes que se utilizarÃ¡n en algunas clases
 * 
 * @author Miguel Ã�ngel GavilÃ¡n Merino
 *
 */
public class General {

	/**
	 * Genera un nÃºmero aleatorio entre dos nÃºmeros
	 * 
	 * @param min
	 *            NÃºmero mÃ­nimo
	 * @param max
	 *            NÃºmero mÃ¡ximo
	 * @return NÃºmero aleatorio
	 */
	public static int generarAleatorio(int min, int max) {
		return (int) (Math.round(Math.random() * (max - min) + min));
	}

}