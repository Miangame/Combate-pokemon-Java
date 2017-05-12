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

	public Usuario(String nombre) throws UsuarioNoValidoException {
		setAlias(nombre);
		setFechaRegistro(Calendar.getInstance());
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) throws UsuarioNoValidoException {
		if (!usuarioValido(alias))
			throw new UsuarioNoValidoException("El usuario no es valido");
		this.alias = alias;
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
