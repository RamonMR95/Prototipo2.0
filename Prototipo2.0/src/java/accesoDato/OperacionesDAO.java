package accesoDato;

import java.util.List;

/**
 * Interfaz que da nombre a las operaciones de las clases DAO
 * @author Ramon Moñino
 */
public interface OperacionesDAO {
	
	/**
	 * Metodo que recibe un argumento que representa 
	 * el id por el que se ordenan los objetos de un mismo DAO. 
	 * Devuelve el objeto buscado o null si no lo encuentra.
	 * @param id - id del DTO
	 * @return Objeto encontrado o null
	 */
	Object obtener(String id);
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	Object obtener(Object obj);
	/**
	 * Devuelve en un objeto List con todos los el objeto 
	 * del mismo tipo asociados al DAO.
	 * @return Lista de objectos del DAO.
	 */
	@SuppressWarnings("rawtypes")
	List obtenerTodos();
	
	/**
	 * Metodo que recibe un argumento que representa el nuevo objeto 
	 * a almacenar. Lanza DatosException si ya existe.
	 * @param obj - Objeto a almacenar
	 */
	void alta(Object obj) throws DatosException;
	
	/**
	 * Metodo que recibe un argumento que representa el id por el 
	 * que se identifica el objeto que se quiere borrar. 
	 * Devuelve el objeto borrado. 
	 * Lanza DatosException si no existe.
	 * @param id - id del DTO
	 * @return Objecto dado de baja
	 */
	Object baja(String id) throws DatosException;
	
	/**
	 * Elimina todos los datos y restaura predeterminados
	 */
	void borrarTodo();
	
	/**
	 * Metodo que recibe un argumento que representa el objeto con 
	 * las modificaciones a actualizar. 
	 * Lanza DatosException si no existe.
	 * @param obj - Objeto a actualizar
	 */
	void actualizar(Object obj) throws DatosException;;
	
	/**
	 * Metodo que muestra todos los almacenados en el sistema asociados 
	 * al mismo DAO. 
	 * @return Texto - Texto con los datos
	 */
	String listarDatos();
	
	/**
	 * Metodo que muestra todos los identificadores de los objetos almacenados en el sistema asociados al mismo DAO.
	 * @return Texto - texto con los datos.
	 */
	String listarId();
	
}
