package accesoDato.fichero;

public interface Persistente {

	/**
	 * Metodo que se encargará de serializar los bits de cada elemento del ArrayList de datos para enviarlo
	 *  a través de un flujo stream) de salida a un fichero.
	 */
	void guardarDatos();
	
	/**
	 * Metodo que se encargará de deserializar (recomponer) de nuevo cada objeto a partir de los bits recibidos 
	 * desde un flujo (stream) de entrada procedente del fichero de datos.
	 * 
	 */
	void recuperarDatos();
	
	/**
	 * Metodo que se encarga de cerrar los ficheros asociados.
	 */
	void cerrar();
}
