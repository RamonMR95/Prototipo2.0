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

	private static SimulacionesDAO instancia = null;
	private ArrayList<Identificable> datosSimulaciones;
	private File ficheroSimulaciones;
	
	private SimulacionesDAO() {
		datosSimulaciones = new ArrayList<Identificable>();
		ficheroSimulaciones = new File(Configuracion.get().getProperty("simulaciones.nombreFichero"));
		recuperarDatos();
		
	}
	
	public static SimulacionesDAO get() {
		if (instancia == null) {
			instancia = new SimulacionesDAO();
			
		}
		return instancia;
	}
	
	
	public ArrayList<Identificable> getSimulaciones() {
		return datosSimulaciones;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void recuperarDatos() {
		if (this.ficheroSimulaciones.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroSimulaciones);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosSimulaciones = (ArrayList<Identificable>) ois.readObject();
				ois.close();
				return;
			} 
			catch (IOException | ClassNotFoundException e) { }
		}
		borrarTodo();
	}
	
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
	public Simulacion obtener(String id) {
		for (Identificable simulacion : datosSimulaciones) {
			Simulacion sim = (Simulacion)simulacion;
			if (sim != null && sim.getId().equalsIgnoreCase(id)) {
				return sim;
			}
		}
		return null;
	}

	@Override
	public Simulacion obtener(Object obj) {
		assert obj != null;
		return this.obtener(((Simulacion)obj).getId());
	}
	
	@Override
	public ArrayList<Identificable> obtenerTodos() {
		return datosSimulaciones;
	}

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

	@Override
	public Object baja(String id) throws DatosException {
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
	

	@Override
	public void borrarTodo() {
		datosSimulaciones.clear();
		
		try {
			cargarPredeterminados();
		} 
		catch (DatosException e) {}
	}

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

	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable simulacion : datosSimulaciones) {
			sb.append(simulacion);
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable simulacion : datosSimulaciones) {
			sb.append(simulacion + "\n");
			
		}
		return sb.toString();
	}
	

	public int size() {
		return datosSimulaciones.size();
	}

	public void cargarPredeterminados() throws DatosException {
		try {
			Usuario usrDemo = UsuariosDAO.get().obtener("III1R");
			Mundo mundoDemo = MundosDAO.get().obtener("Demo1");
			alta(new Simulacion(usrDemo, new Fecha(0001,01,01,01,01,01), mundoDemo));
		} 
		catch (DatosException e) {
		}
	}

}
