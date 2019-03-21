package accesoDato.memoria;

import java.util.ArrayList;
import java.util.List;

import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import modelo.SesionUsuario;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de Sesiones utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: SesionesDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class SesionesDAO implements OperacionesDAO{

	private static SesionesDAO instancia = null;
	private ArrayList<SesionUsuario> datosSesiones;

	public SesionesDAO() {
		datosSesiones = new ArrayList<>();
	}

	public static SesionesDAO get() {
		if (instancia == null) {
			instancia = new SesionesDAO();

		}
		return instancia;
	}

	public ArrayList<SesionUsuario> getSesionUsuario() {
		return datosSesiones;
	}

	@Override
	public Object obtener(String id) {
		assert id != null;
		int posicion = indexSortSesiones(id);
	}

	@Override
	public List<Object> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta(Object obj) throws DatosException {
		SesionUsuario sesion = (SesionUsuario)obj;
		assert sesion != null;
		int posicionInsercion = indexSortSesiones(sesion.getIdSesion());

		if (posicionInsercion < 0) {
			datosSesiones.add(Math.abs(posicionInsercion) - 1, sesion);
		} else {
			throw new DatosException("Alta Sesion: ya existe");
		}
		
	}

	@Override
	public Object baja(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		SesionUsuario sesion = (SesionUsuario)obj;
		int posicion = indexSortSesiones(sesion.getIdSesion());
		
		if (posicion > 0) {
			datosSesiones.set(posicion-1, sesion);
			
		} else {
			throw new DatosException("Sesiones : " + sesion.getIdSesion() + " no existe");
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
	
	/**
	 * Metodo get que obtiene el numero de sesiones registradas.
	 * @return Numero de sesionesRegistradas
	 */
	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion - Sesión a añadir al array de sesiones
	 * @throws DatosException 
	 */
	public void altaSesion(SesionUsuario sesion) throws DatosException {

	}

//	/**
//	 * Metodo que busca una sesion del array de sesiones
//	 * @param sesion - Sesion a buscar
//	 * @return Sesion si la encuentra, null si no
//	 */
//	public SesionUsuario buscarSesion(String sesion) {
//		assert sesion != null;
//		sesion = mapaEquivalencias.get(sesion);
//
//		if (sesion != null) {
//			int indice = indexSortUsuario(sesion) - 1;
//
//			if (indice >= 0) {
//				return datosSesiones.get(indice);
//			}
//		}
//		return null;
//
//	}

	/**
	 * Metodo de inserción binaria de sesiones
	 * @param idSesion - id de la sesion a insertar en el array de sesiones de usuario
	 * @return Indice a insertar
	 */
	private int indexSortSesiones(String idSesion) {
		int size = datosSesiones.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idSesion.compareTo(datosSesiones.get(puntoMedio).getIdSesion());

			if (comparacion == 0) {
				return puntoMedio + 1;
			}

			if (comparacion > 0) {
				limiteInferior = puntoMedio + 1;
			} else {
				limiteSuperior = puntoMedio - 1;
			}
		}
		return -(limiteInferior + 1);
	}

	public int size() {
		return datosSesiones.size();
	}

}
