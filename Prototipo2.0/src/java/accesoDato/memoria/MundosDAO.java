package accesoDato.memoria;

import java.util.ArrayList;
import java.util.List;

import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import modelo.Mundo;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de mundos utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: MundosDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class MundosDAO implements OperacionesDAO{

	private static MundosDAO instancia = null;
	private ArrayList<Mundo> datosMundos;

	private MundosDAO() {
		datosMundos = new ArrayList<>();
	}

	public static MundosDAO get() {
		if (instancia == null) {
			instancia = new MundosDAO();
		}
		return instancia;
	}

	public ArrayList<Mundo> getMundos(){
		return datosMundos;
	}

	@Override
	public Object obtener(String id) {
		for (Mundo mundo : datosMundos) {
			if (mundo != null && mundo.getId().equalsIgnoreCase(id)) {
				return mundo;
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
		Mundo mundo = (Mundo)obj;
		int posicionInsercion = indexSort(mundo.getId());

		if (posicionInsercion < 0) {
			datosMundos.add(Math.abs(posicionInsercion) - 1, mundo);
		} else {
			throw new DatosException("Error Mundo: nombre repetido");

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
	public void actualizar(Object obj) {
		// TODO Auto-generated method stub
		
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
		return datosMundos.size();
	}
	
	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	private int indexSort(String idUsr) {
		int size = datosMundos.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idUsr.compareTo(datosMundos.get(puntoMedio).getId());

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

	@Override
	public Object obtener(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
