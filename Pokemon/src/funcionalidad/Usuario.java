package funcionalidad;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import funcionalidad.excepciones.UsuarioNoValidoException;

/**
 * Clase que crea un usuario con nombre y una lista de pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Alias del usuario
	 */
	private String alias;

	/**
	 * Contraseña del usuario
	 */
	private String contrasenia;

	/**
	 * Correo del usuario
	 */
	private String correo;

	/**
	 * Lista de pokemons
	 */
	private EnvoltorioPokemons cinturon = new EnvoltorioPokemons();

	/**
	 * Fecha de registro del usuario
	 */
	protected Calendar fechaRegistro;

	/**
	 * Patrón para comprobar que el alias del usuario es correcto
	 */
	static final private Pattern patronUsuario = Pattern.compile("\\w{2,}[ ]?\\w{2,}");

	/**
	 * Patrón para comprobar que el alias del usuario es correcto
	 */
	static final private Pattern patronContrasenia = Pattern.compile(".*{2,}");

	/**
	 * Patrón para comprobar que el correo del usuario es correcto
	 */
	static final private Pattern patronCorreo = Pattern
			.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

	public Usuario(String nombre, String correo, String contrasenia)
			throws UsuarioNoValidoException, ContraseniaNoValidaException, CorreoNoValidoException {
		setAlias(nombre);
		setContrasenia(contrasenia);
		setCorreo(correo);
		setFechaRegistro(Calendar.getInstance());
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) throws UsuarioNoValidoException {
		if (!usuarioValido(alias))
			throw new UsuarioNoValidoException("El usuario no es válido");
		this.alias = alias;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) throws CorreoNoValidoException {
		if (!correoValido(correo))
			throw new CorreoNoValidoException("El correo no es válido");
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) throws ContraseniaNoValidaException {
		if (!contraseniaValida(contrasenia))
			throw new ContraseniaNoValidaException("La contraseña no es válida");

		this.contrasenia = contrasenia;
	}

	/**
	 * Comprueba que el correo sea válido
	 * 
	 * @param correo2
	 * @return
	 */
	private boolean correoValido(String correo2) {
		return patronCorreo.matcher(correo2).matches();
	}

	/**
	 * Comprueba que la contraseña sea válida
	 * 
	 * @param contrasenia2
	 * @return
	 */
	private boolean contraseniaValida(String contrasenia2) {
		return patronContrasenia.matcher(contrasenia2).matches();
	}

	/**
	 * Comprueba que el alias es valido
	 * 
	 * @return true si es válido, false si no lo es
	 */
	private boolean usuarioValido(String alias) {
		return patronUsuario.matcher(alias).matches();
	}

	public EnvoltorioPokemons getCinturon() {
		return cinturon;
	}

	public void setCinturon() {
		this.cinturon = new EnvoltorioPokemons();
	}

	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Calcula el tiempo que lleva registrado el usuario
	 * 
	 * @return Horas que lleva registrado
	 */
	public String calcularTiempoRegistrado() {
		Date diaActual = new Date();
		long fechaRegistro = getFechaRegistro().getTimeInMillis() / (1000 * 60 * 60);
		long fechaActual = (diaActual.getTime()) / (1000 * 60 * 60);
		long horas = fechaActual - fechaRegistro;

		return "Lleva registrado " + horas + " horas";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equalsIgnoreCase(other.alias))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [alias=" + alias + ", fechaRegistro=" + fechaRegistro + "]";
	}

}
