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
import modelo.SesionUsuario;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de Sesiones utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: SesionesDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class SesionesDAO extends IndexSort implements OperacionesDAO, Persistente {

	private static SesionesDAO instancia = null;
	private ArrayList<Identificable> datosSesiones;
	private File ficheroSesiones;

	public SesionesDAO() {
		datosSesiones = new ArrayList<>();
		ficheroSesiones = new File(Configuracion.get().getProperty("sesiones.nombreFichero"));
		recuperarDatos();
	}

	public static SesionesDAO get() {
		if (instancia == null) {
			instancia = new SesionesDAO();

		}
		return instancia;
	}

	public List<Identificable> getSesionUsuario() {
		return datosSesiones;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void recuperarDatos() {
		if (this.ficheroSesiones.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroSesiones);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosSesiones = (ArrayList<Identificable>) ois.readObject();
				ois.close();
				return;
			}
			catch (IOException |ClassNotFoundException e) { }
		}
		borrarTodo();
	}

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


	@Override
	public Object obtener(String id) {
		assert id != null;
		int indice = indexSort(id, datosSesiones) - 1;

		if (indice >= 0) {
			return datosSesiones.get(indice);
		}

		return null;
	}
	
	@Override
	public Object obtener(Object obj) {
		assert obj != null;
		int indice = indexSort(((SesionUsuario) obj).getId(), datosSesiones) - 1;

		if (indice >= 0) {
			return datosSesiones.get(indice);
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List obtenerTodos() {
		return datosSesiones;
	}

	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion - Sesión a añadir al array de sesiones
	 * @throws DatosException 
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

	@Override
	public Object baja(String id) throws DatosException {
		assert id != null;
		int posicion = indexSort(id, datosSesiones);
		
		if (posicion > 0) {
			return datosSesiones.remove(posicion-1);
		}
		else {
			throw new DatosException("Baja : El usuario no existe");
		}
			
	}
	

	@Override
	public void borrarTodo() {
		datosSesiones.clear();;
		
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		SesionUsuario sesion = (SesionUsuario)obj;
		int posicion = indexSort(sesion.getId(), datosSesiones);
		
		if (posicion > 0) {
			datosSesiones.set(posicion-1, (Identificable) sesion);
			
		}
		else {
			throw new DatosException("Sesiones : " + sesion.getId() + " no existe");
		}
		
	}

	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();

		for (Identificable sesiones : datosSesiones) {
			sb.append("\n" + sesiones);
		}
		return sb.toString();
	}

	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable sesiones : datosSesiones) {
			sb.append(sesiones + "\n");
		
		}
		return sb.toString();
	}
	
	public int size() {
		return datosSesiones.size();
	}

}
