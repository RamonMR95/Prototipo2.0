package accesoDato.fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import accesoDato.DatosException;
import accesoDato.IndexSort;
import accesoDato.OperacionesDAO;
import config.Configuracion;
import modelo.Identificable;
import modelo.Mundo;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de mundos utilizando un fichero 
 *  para el arraylist de datosMundos
 *  @since: prototipo2.0
 *  @source: MundosDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class MundosDAO extends IndexSort implements OperacionesDAO, Persistente {
	
	/* Atributos de la clase MundosDAO */
	private static MundosDAO instancia = null;
	private ArrayList<Identificable> datosMundos;
	private File ficheroMundos;

	/**
	 * Constructor por defecto de la clase que inicializa los valores de los atributos y genera el 
	 * fichero de guardado y recuperacion de datos de mundos
	 * @throws DatosException - Si datosPredeterminados genera excepcion por no poder dar de alta el mundo
	 */
	private MundosDAO() throws DatosException {
		datosMundos = new ArrayList<>();
		ficheroMundos = new File(Configuracion.get().getProperty("mundos.nombreFichero"));
		cargarPredeterminados();
	}

	/**
	 * Metodo propio del patron singleton que se usa para controlar que solo se crea una instancia de la clase MundosDAO solo una vez.
	 * @return instancia - Variable usada por el patron singleton.
	 */
	public static MundosDAO get() throws DatosException {
		if (instancia == null) {
			instancia = new MundosDAO();
		}
		return instancia;
	}

	/**
	 * Metodo que obtiene el arraylist de mundos
	 * @return datosMundos - Arraylist de mundos
	 */
	public List getMundos(){
		return datosMundos;
	}

	/**
	 * Metodo que busca un mundo dado su identificador de mundo
	 * @param id - Identificador de mundo
	 * @return Mundo - Si lo encuentra o null si no.
	 */
	@Override
	public Mundo obtener(String id) {
		for (Identificable mundo : datosMundos) {
			if (mundo != null && mundo.getId().equalsIgnoreCase(id)) {
				return (Mundo) mundo;
			}
		}
		return null;
	}

	/**
	 * Metodo que busca si un mundo se encuentra en el arraylist de mundos utilizando la sobrecarga de metodos
	 * @param obj - Mundo a buscar
	 * @return obj - Si el mundo se encuentra o null si no.
	 */
	@Override
	public Mundo obtener(Object obj) {
		assert obj != null;
		return this.obtener(((Mundo)obj).getId());
	}
	
	/**
	 * Metodo que obtiene el arraylist que contiene todos los mundos del programa
	 * @return datosMundos - Arraylist que contiene todos los mundos del programa
	 */
	@Override
	public List obtenerTodos() {
		return datosMundos;
	}

	/**
	 * Metodo que da de alta un mundo en el arraylist de mundos
	 * @param obj - Mundo a dar de alta
	 * @throws DatosException - Si el mundo ya estaba dado de alta en nuestra aplicación
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Mundo mundo = (Mundo)obj;
		int posicionInsercion = indexSort(mundo.getId(), datosMundos);

		if (posicionInsercion < 0) {
			datosMundos.add(Math.abs(posicionInsercion) - 1, mundo);
		} 
		else {
			throw new DatosException("Error Mundo: nombre repetido");

		}
	}

	/**
	 * Metodo que elimina un mundo del arraylist de mundos dado su id de mundo
	 * @param id - Identificador del mundo a borrar
	 * @throws DatosException - Si el mundo no existe
	 */
	@Override
	public Mundo baja(String id) throws DatosException {
		assert id != null;
		int posicion = indexSort(id, datosMundos);
		
		if (posicion > 0) {
			Mundo mundoEliminado = (Mundo)datosMundos.remove(posicion - 1);
			return mundoEliminado;
		}
		else {
			throw new DatosException("Baja : " + id + " no existe");
		}
	}


	/**
	 * Metodo que borra el contenido del arraylist mundos y carga 
	 * los datos del mundo predeterminado del programa
	 */
	@Override
	public void borrarTodo() throws DatosException {
		datosMundos.clear();
		cargarPredeterminados();
		
	}
	
	/**
	 * Metodo que actualiza datos en el array de Mundos
	 * @param obj - Objeto a actualizar en el programa
	 * @throws DatosException - Excepción si el mundo no existe
	 */
	@Override
	public void actualizar(Object obj) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo que genera una cadena de caracteres con 
	 * los atributos de todos los mundos del programa
	 * @return texto - Cadena de caracteres con los datos de todos los mundos
	 */
	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable mundos : datosMundos) {
			sb.append(mundos + "\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo que genera una cadena de caracteres que contiene los 
	 * identificadores de todos los mundos del arraylist de mundos
	 * @return Id - Ids de los mundos
	 */
	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable mundo : datosMundos) {
			sb.append(mundo.getId());
		}
		return sb.toString();
	}
	
	/**
	 * Metodo que carga un mundo predeterminado.
	 * @throws DatosException - Si el mundo no se puede dar de alta porque ya existe.
	 */
	public void cargarPredeterminados() throws DatosException {
		
		byte [][] espacioDemo = new byte[][] { 
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		Mundo mundo = new Mundo();
		mundo.setEspacio(espacioDemo);
		alta(mundo);
	}
	
	/**
	 * Metodo get que obtiene el tamaño del arraylist de mundos
	 * @return arraylist size - Tamaño del arraylist de mundos
	 */
	public int size() {
		return datosMundos.size();
	}
	
/* PERSISTENCIA */
	
	/**
	 * Metodo que se encargará de deserializar (recomponer) de nuevo cada objeto a partir de los bits
	 * recibidos desde un flujo (stream) de entrada procedente del fichero de datos.
	 */
	@Override
	public void recuperarDatos() {
		if (this.ficheroMundos.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroMundos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosMundos = (ArrayList<Identificable>) ois.readObject();
				return;
			} 
			catch (IOException | ClassNotFoundException e) { }
		}
		
	}
	
	/**
	 * Metodo que se encargará de serializar los bits de cada elemento del ArrayList 
	 * de datos para enviarlo a través de un flujo (stream) de salida a un fichero
	 */
	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fos = new FileOutputStream(ficheroMundos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datosMundos);
		} 
		catch (IOException e) { }
	}

	/**
	 * Metodo que cierra las conexiones y guarda las nuevas configuraciones
	 */
	@Override
	public void cerrar() {
		guardarDatos();
	}
	

}  // Class MundosDAO
