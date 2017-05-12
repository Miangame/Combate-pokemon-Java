package funcionalidad.excepciones;

/**
 * Se lanza cuando un elemento no existe
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class ElementoNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoNoExisteException(String msg) {
		super(msg);
	}
}
