package funcionalidad.excepciones;

/**
 * Se lanza cuando el alias no es correcto
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class UsuarioNoValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNoValidoException(String msg) {
		super(msg);
	}

}
