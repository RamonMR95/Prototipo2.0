package accesoDato;

/**
 * Excepción para la gestión de errores de la clase Datos.
 * @since prototipo1.2
 * @source DatosException.java
 * @version 1.2 - 5-03-2019
 * @author Ramon Moñino Rubio
 *
 */
public class DatosException extends Exception {

	private static final long serialVersionUID = 2L;

	public DatosException(String mensaje) {
		super(mensaje);
	}

	public DatosException() {
		super();
	}

} // Class
