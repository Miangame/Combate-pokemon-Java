package funcionalidad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;

import funcionalidad.excepciones.ElementoNoExisteException;
import funcionalidad.excepciones.UsuarioNoValidoException;

/**
 * Envoltorio que maneja una lista de usuarios
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class ManejoUsuarios {
	/**
	 * Lista de usuarios totales del juego.
	 */
	public static Lista<Usuario> listaUsuarios = new Lista<Usuario>();

	/**
	 * Archivo d�nde se va a guardar los datos de todos los usuarios.
	 */
	public static File archivoUsuarios = new File("usuarios.obj");

	/**
	 * Obtiene los datos del usuario pasado por argumentos.
	 * 
	 * @param nombre
	 *            nombre del usuario a buscar.
	 * @return Datos del usuario.
	 * @throws ElementoNoExisteException
	 * @throws UsuarioNoValidoException
	 * @throws ContraseniaNoValidaException
	 * @throws CorreoNoValidoException
	 */
	public static Usuario getUsuario(String nombre, String contrasenia)
			throws ElementoNoExisteException, UsuarioNoValidoException, ContraseniaNoValidaException, CorreoNoValidoException {
		return listaUsuarios.getElemento(new Usuario(nombre, "111@111.com", contrasenia));
	}

	/**
	 * Guarda los datos del usuario en el archivo correspondiente.
	 */
	static void guardar() {

		try {
			FicheroUsuarios.escribir(ManejoUsuarios.listaUsuarios, ManejoUsuarios.archivoUsuarios);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Crea una lista con los usuarios del fichero
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void cargarLista() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoUsuarios))) {
			listaUsuarios = (Lista<Usuario>) in.readObject();
		}
	}
}
