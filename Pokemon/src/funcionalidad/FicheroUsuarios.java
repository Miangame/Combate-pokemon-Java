package funcionalidad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Clase que opera con ficheros de usuarios
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class FicheroUsuarios implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lee una lista cargada de un fichero
	 * 
	 * @param file
	 *            Fichero del que va a leer la lista
	 * @return Lista cargada
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static Lista<Usuario> leer(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		file = aniadirExtension(file);

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return (Lista<Usuario>) in.readObject();
		}

	}

	/**
	 * Escribe una lista de usuarios en un fichero
	 * 
	 * @param listaUsuarios
	 *            Lista que escribirá en el fichero
	 * @param file
	 *            Fichero donde escribirá la lista
	 * @throws IOException
	 */
	public static void escribir(Lista<Usuario> listaUsuarios, File file) throws IOException {
		file = aniadirExtension(file);
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(listaUsuarios);
		}

	}

	/**
	 * Añade una extensión al fichero
	 * 
	 * @param archivo
	 * @return fichero con la extensión
	 */
	public static File aniadirExtension(File archivo) {
		String extension = archivo.getPath();
		if (!extension.endsWith(".obj"))
			return new File(archivo + ".obj");
		return archivo;
	}

}