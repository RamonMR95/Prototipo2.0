package accesoDato.fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import accesoDato.IndexSort;
import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import config.Configuracion;
import modelo.Identificable;
import modelo.ModeloException;
import modelo.SesionUsuario;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de Sesiones utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: SesionesDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class SesionesDAO extends IndexSort implements OperacionesDAO, Persistente {

	/* Atributos de la clase SesionesDAO */
	private static SesionesDAO instancia = null;
	private ArrayList<Identificable> datosSesiones;
	private File ficheroSesiones;

	/**
	 * Constructor por defecto de la clase que inicializar los valores de los atributos de la clase 
	 * y genera el fichero para recuperar y guardar datos
	 */
	public SesionesDAO() {
		datosSesiones = new ArrayList<>();
		ficheroSesiones = new File(Configuracion.get().getProperty("sesiones.nombreFichero"));
		recuperarDatos();
	}

	/**
	 * Metodo propio del patron singleton que se usa para controlar que solo se crea una instancia de la clase SesionesDAO solo una vez.
	 * @return instancia - Variable usada por el patron singleton.
	 */
	public static SesionesDAO get() {
		if (instancia == null) {
			instancia = new SesionesDAO();

		}
		return instancia;
	}

	/**
	 * Metodo que obtiene el arraylist de sesiones de usuario
	 * @return datosSesiones - Arraylist de sesiones de usuario
	 */
	public List<Identificable> getSesionUsuario() {
		return datosSesiones;
	}
	
	/**
	 * Metodo que busca una sesion de usuario dado su identificador de sesion de usuario
	 * @param id - Identificador de sesion de usuario
	 * @return SesionUsuario - Si lo encuentra o null si no.
	 */
	@Override
	public SesionUsuario obtener(String id) {
		assert id != null;
		int indice = indexSort(id, datosSesiones) - 1;

		if (indice >= 0) {
			return (SesionUsuario) datosSesiones.get(indice);
		}

		return null;
	}
	
	/**
	 * Metodo que busca si una sesion de usuario se encuentra en el arraylist de sesion de usuario utilizando la sobrecarga de metodos
	 * @param obj - SesionUsuario a buscar
	 * @return sesion de usuario - Si el sesion de usuario se encuentra o null si no.
	 */
	@Override
	public SesionUsuario obtener(Object obj) {
		assert obj != null;
		int indice = indexSort(((SesionUsuario) obj).getId(), datosSesiones) - 1;

		if (indice >= 0) {
			return (SesionUsuario) datosSesiones.get(indice);
		}

		return null;
	}

	/**
	 * Metodo que obtiene el arraylist que contiene todas las sesiones de usuario
	 * @return datosMundos - Arraylist que contiene todas las sesiones de usuario del programa
	 */
	@Override
	public List obtenerTodos() {
		return datosSesiones;
	}

	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion - Sesión a añadir al array de sesiones
	 * @throws DatosException - Si la sesión ya existia en en el arraylist
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		SesionUsuario sesion = (SesionUsuario)obj;
		assert sesion != null;
		int posicionInsercion = indexSort(sesion.getId(), datosSesiones);

		if (posicionInsercion < 0) {
			datosSesiones.add(Math.abs(posicionInsercion) - 1, sesion);
		} 
		else {
			throw new DatosException("Alta Sesion: ya existe");
		}
		
	}

	/**
	 * Metodo que elimina una sesion de usuario del arraylist de sesiones de usuario dada su id
	 * @param id - Identificador de la sesion de usuario a borrar
	 * @throws DatosException - Si la sesion de usuario no existe
	 */
	@Override
	public SesionUsuario baja(String id) throws DatosException {
		assert id != null;
		int posicion = indexSort(id, datosSesiones);
		
		if (posicion > 0) {
			return (SesionUsuario) datosSesiones.remove(posicion-1);
		}
		else {
			throw new DatosException("Baja : El usuario no existe");
		}
			
	}
	
	/**
	 * Metodo que borra el contenido del arraylist de sesiones de usuario
	 */
	@Override
	public void borrarTodo() {
		datosSesiones.clear();
		
	}

	/**
	 * Metodo que actualiza datos en el array de sesiones de usuario
	 * @param obj - Objeto a actualizar en el programa
	 * @throws DatosException - Excepción si la sesion de usuario no existe
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		SesionUsuario sesion = (SesionUsuario)obj;
		int posicion = indexSort(sesion.getId(), datosSesiones);
		
		if (posicion > 0) {
			datosSesiones.set(posicion - 1, sesion);
			
		}
		else {
			throw new DatosException("Sesiones : " + sesion.getId() + " no existe");
		}
		
	}

	/**
	 * Metodo que genera una cadena de caracteres con 
	 * los atributos de todas las sesiones de usuario del programa
	 * @return texto - Cadena de caracteres con los datos de todas las sesiones de usuario
	 */
	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();

		for (Identificable sesiones : datosSesiones) {
			sb.append("\n" + sesiones);
		}
		return sb.toString();
	}

	/**
	 * Metodo que genera una cadena de caracteres que contiene los 
	 * identificadores de todas las sesiones de usuario del arraylist de sesiones de usuario
	 * @return Id - Ids de las sesiones de usuario
	 */
	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable sesiones : datosSesiones) {
			sb.append(sesiones + "\n");
		
		}
		return sb.toString();
	}
	
	/**
	 * Metodo get que obtiene el tamaño del arraylist de sesiones de usuario
	 * @return arraylist size - Tamaño del arraylist de sesiones de usuario
	 */
	public int size() {
		return datosSesiones.size();
	}


	/* PERSISTENCIA */
	
	/**
	 * Metodo que se encargará de deserializar (recomponer) de nuevo cada objeto a partir de los bits
	 * recibidos desde un flujo (stream) de entrada procedente del fichero de datos.
	 */
	@Override
	public void recuperarDatos() {
		if (this.ficheroSesiones.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroSesiones);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosSesiones = (ArrayList<Identificable>) ois.readObject();
				return;
			}
			catch (IOException |ClassNotFoundException e) { }
		}
		borrarTodo();
	}

	/**
	 * Metodo que se encargará de serializar los bits de cada elemento del ArrayList 
	 * de datos para enviarlo a través de un flujo (stream) de salida a un fichero
	 */
	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fos = new FileOutputStream(ficheroSesiones);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datosSesiones);
			oos.close();
		} 
		catch (Exception e) { }
	}


	/**
	 * Metodo que cierra las conexiones y guarda las nuevas configuraciones
	 */
	@Override
	public void cerrar() {
		guardarDatos();
	}
	

	private List<Identificable> separarSesionesUsr(int ultima) {
		String idUsr = ((SesionUsuario)datosSesiones.get(ultima)).getUsr().getId();
		int primera = ultima;
		
		for (int i = ultima; i >= 0 && ((SesionUsuario)datosSesiones.get(i)).getUsr().getId().equals(idUsr); i++) {
			primera = i;
		}
		return datosSesiones.subList(primera, ultima + 1);
	}
	
	public List<Identificable> obtenerTodasMismoUsr(String idUsr) throws ModeloException {
		assert idUsr != null;
		SesionUsuario aux = new SesionUsuario();
		aux.setUsr(UsuariosDAO.get().obtener(idUsr));
		return separarSesionesUsr(indexSort(aux.getId(), datosSesiones) -1 );
	}

} // Class SesionesDAO
