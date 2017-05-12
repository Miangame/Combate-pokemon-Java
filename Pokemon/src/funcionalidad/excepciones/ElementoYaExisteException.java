package funcionalidad.excepciones;

/**
 * Se lanza cuando un elemento ya existe
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class ElementoYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoYaExisteException(String msg) {
		super(msg);
	}

}
