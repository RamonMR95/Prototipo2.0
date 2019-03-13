package modelo;

/**
 * Excepción para la gestión de errores de la clase modelo.
 * @since prototipo1.2
 * @source ModeloException.java
 * @version 1.2 - 5-03-2019
 * @author Ramon Moñino Rubio
 *
 */
public class ModeloException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ModeloException(String mensaje) {
		super(mensaje);
	}
	
	public ModeloException() {
		super();
	}

} // Class
