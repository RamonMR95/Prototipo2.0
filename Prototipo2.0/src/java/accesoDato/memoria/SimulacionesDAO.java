package accesoDato.memoria;

import java.util.ArrayList;
import java.util.List;

import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import modelo.Simulacion;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de simulaciones utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: SimulacionesDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class SimulacionesDAO implements OperacionesDAO{

	private static SimulacionesDAO instancia = null;
	private static ArrayList<Simulacion> datosSimulaciones;
	
	private SimulacionesDAO() {
		datosSimulaciones = new ArrayList<>();
	}
	
	public static SimulacionesDAO get() {
		if (instancia == null) {
			instancia = new SimulacionesDAO();
			
		}
		return instancia;
	}
	
	public ArrayList<Simulacion> getSimulaciones() {
		return datosSimulaciones;
	}
	@Override
	public Object obtener(String id) {
		for (Simulacion simulacion : datosSimulaciones) {
			if (simulacion != null && simulacion.getIdSimulacion().equalsIgnoreCase(id)) {
				return simulacion;
			}
		}
		return null;
	}

	@Override
	public List<Object> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulacion = (Simulacion)obj;
		int posicionInsercion = indexSort(simulacion.getIdSimulacion());

		if (posicionInsercion < 0) {
			datosSimulaciones.add(Math.abs(posicionInsercion) - 1, simulacion);
		} 
		else {
			throw new DatosException("Alta Simulacion: ya existe");
		}
		
	}

	@Override
	public Object baja(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Simulacion simulActualizada = (Simulacion)obj;
		int posicion = indexSort(simulActualizada.getIdSimulacion());
		
		if (posicion > 0) {
			datosSimulaciones.set(posicion-1, simulActualizada);
		} 
		else {
			throw new DatosException("Actualizar simulación : No existe la simulación a actualizar");
		}
		
	}

	@Override
	public String listarDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarId() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public int size() {
		return datosSimulaciones.size();
	}
	/**
	 * Metodo que inserta en el array de simulaciones
	 * @param idSimulacion - id de la simulacion a insertar
	 * @return posicion en la que insertar la simulacion
	 */
	private int indexSort(String idSimulacion) {
		int size = datosSimulaciones.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idSimulacion.compareTo(datosSimulaciones.get(puntoMedio).getIdSimulacion());

			if (comparacion == 0) {
				return puntoMedio + 1;
			}

			if (comparacion > 0) {
				limiteInferior = puntoMedio + 1;
			} 
			else {
				limiteSuperior = puntoMedio - 1;
			}
		}
		return -(limiteInferior + 1);
	}

	@Override
	public Object obtener(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
