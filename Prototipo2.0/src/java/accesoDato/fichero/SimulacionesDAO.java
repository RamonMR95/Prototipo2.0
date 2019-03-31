package accesoDato.fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import accesoDato.DatosException;
import accesoDato.IndexSort;
import accesoDato.OperacionesDAO;
import config.Configuracion;
import modelo.Mundo;
import modelo.Identificable;
import modelo.Simulacion;
import modelo.Usuario;
import util.Fecha;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de simulaciones utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: SimulacionesDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class SimulacionesDAO extends IndexSort implements OperacionesDAO, Persistente {

	/* Atributos de la clase SimulacionesDAO */
	private static SimulacionesDAO instancia = null;
	private ArrayList<Identificable> datosSimulaciones;
	private File ficheroSimulaciones;
	
	/**
	 * Constructor por defecto de la clase que inicializar los valores de los atributos y genera 
	 * el fichero de guardado y recuperacion de datos de simulaciones
	 */
	private SimulacionesDAO() {
		datosSimulaciones = new ArrayList<Identificable>();
		ficheroSimulaciones = new File(Configuracion.get().getProperty("simulaciones.nombreFichero"));
		recuperarDatos();
		
	}

	/**
	 * Metodo propio del patron singleton que se usa para controlar que solo se crea una instancia de la clase SimulacionesDAO solo una vez.
	 * @return instancia - Variable usada por el patron singleton.
	 */
	public static SimulacionesDAO get() {
		if (instancia == null) {
			instancia = new SimulacionesDAO();
			
		}
		return instancia;
	}
	
	/**
	 * Metodo que obtiene el arraylist de simulaciones
	 * @return datosSimulaciones - Arraylist de simulaciones
	 */
	public ArrayList<Identificable> getSimulaciones() {
		return datosSimulaciones;
	}
	
	/**
	 * Metodo que busca una simulacion dado su identificador de simulacion
	 * @param id - Identificador de simulacion
	 * @return Simulacion - Si la encuentra o null si no.
	 */
	@Override
	public Simulacion obtener(String id) {
		for (Identificable simulacion : datosSimulaciones) {
			Simulacion sim = (Simulacion)simulacion;
			if (sim != null && sim.getId().equalsIgnoreCase(id)) {
				return sim;
			}
		}
		return null;
	}

	/**
	 * Metodo que busca si un simulacion se encuentra en el arraylist de simulaciones utilizando la sobrecarga de metodos
	 * @param obj - Simulacion a buscar
	 * @return obj - Si la simulacion se encuentra o null si no.
	 */
	@Override
	public Simulacion obtener(Object obj) {
		assert obj != null;
		return this.obtener(((Simulacion)obj).getId());
	}
	
	/**
	 * Metodo que obtiene el arraylist que contiene todos los simulaciones del programa
	 * @return datosSimulaciones - Arraylist que contiene todos los simulaciones del programa
	 */
	@Override
	public ArrayList<Identificable> obtenerTodos() {
		return datosSimulaciones;
	}

	/**
	 * Metodo que da de alta un mundo en el arraylist de mundos
	 * @param obj - Simulacion a dar de alta
	 * @throws DatosException - Si la simulacion ya estaba dado de alta en nuestra aplicación
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulacion = (Simulacion) obj;
		
		int posicionInsercion = indexSort(simulacion.getId(), datosSimulaciones);

		if (posicionInsercion < 0) {
			datosSimulaciones.add(Math.abs(posicionInsercion) - 1,  simulacion);
		} 
		else {
			throw new DatosException("Alta Simulacion: ya existe");
		}
		
	}

	/**
	 * Metodo que elimina un simulacion  del arraylist de simulaciones dado su id de simulacion 
	 * @param id - Identificador del simulacion a borrar
	 * @throws DatosException - Si la simulacion no existe
	 */
	@Override
	public Simulacion baja(String id) throws DatosException {
		assert id != null;
		Simulacion simBaja = null;
		
		for (Identificable simulacion : datosSimulaciones) {
			if (((Simulacion) simulacion).getId() == id) {
				simBaja =  (Simulacion) simulacion;
				datosSimulaciones.remove(simulacion);
				return simBaja;
			}
		}
		throw new DatosException("Baja : " + id + " no existe");
	}
	
	/**
	 * Metodo que borra el contenido del arraylist simulaciones y carga 
	 * predeterminados
	 */
	@Override
	public void borrarTodo() {
		datosSimulaciones.clear();
		
		try {
			cargarPredeterminados();
		} 
		catch (DatosException e) { }
	}

	/**
	 * Metodo que actualiza datos en el array de simulaciones
	 * @param obj - Objeto a actualizar en el programa
	 * @throws DatosException - Excepción si la simulacion no existe
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulActualizada = (Simulacion)obj;
		int posicion = indexSort(simulActualizada.getId(), datosSimulaciones);
		
		if (posicion > 0) {
			datosSimulaciones.set(posicion - 1, simulActualizada);
		} 
		else {
			throw new DatosException("Actualizar simulación : No existe la simulación a actualizar");
		}
		
	}

	/**
	 * Metodo que genera una cadena de caracteres con 
	 * los atributos de todas las simulaciones del programa
	 * @return texto - Cadena de caracteres con los datos de todos las simulaciones
	 */
	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable simulacion : datosSimulaciones) {
			sb.append(simulacion);
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo que genera una cadena de caracteres que contiene los 
	 * identificadores de todas las simulaciones del arraylist de simulaciones
	 * @return Id - Ids de los simulaciones
	 */
	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable simulacion : datosSimulaciones) {
			sb.append(simulacion + "\n");
			
		}
		return sb.toString();
	}
	
	/**
	 * Metodo get que obtiene el tamaño del arraylist de simulaciones
	 * @return arraylist size - Tamaño del arraylist de simulaciones
	 */
	public int size() {
		return datosSimulaciones.size();
	}

	/**
	 * Metodo que carga la simulación por defecto del programa
	 * @throws DatosException - Si la simulacion no se puede dar de alta 
	 * porque ya existia con anterioridad
	 */
	public void cargarPredeterminados() throws DatosException {
		try {
			Usuario usrDemo = UsuariosDAO.get().obtener("III1R");
			Mundo mundoDemo = MundosDAO.get().obtener("Demo1");
			alta(new Simulacion(usrDemo, new Fecha(0001,01,01,01,01,01), mundoDemo));
		} 
		catch (DatosException e) {
		}
	}

	
	/* PERSISTENCIA */
	
	/**
	 * Metodo que se encargará de deserializar (recomponer) de nuevo cada objeto a partir de los bits
	 * recibidos desde un flujo (stream) de entrada procedente del fichero de datos.
	 */
	@Override
	public void recuperarDatos() {
		if (this.ficheroSimulaciones.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroSimulaciones);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosSimulaciones = (ArrayList<Identificable>) ois.readObject();
				return;
			} 
			catch (IOException | ClassNotFoundException e) { }
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
			FileOutputStream fos = new FileOutputStream(ficheroSimulaciones);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datosSimulaciones);
			oos.close();
			return;
		} 
		catch (IOException e) { }
	}

	@Override
	public void cerrar() {
		guardarDatos();
		
	}

} // Class SimulacionesDAO
