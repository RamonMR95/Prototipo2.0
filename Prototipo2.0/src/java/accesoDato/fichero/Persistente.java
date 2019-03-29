package accesoDato.fichero;

import java.io.FileNotFoundException;

public interface Persistente {

	/**
	 * 
	 */
	void guardarDatos();
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	void recuperarDatos();
}
