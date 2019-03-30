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
 *  Clase especializada en el acceso a datos de mundos utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: MundosDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class MundosDAO extends IndexSort implements OperacionesDAO, Persistente {

	private static MundosDAO instancia = null;
	private ArrayList<Identificable> datosMundos;
	private File ficheroMundos;

	private MundosDAO() throws DatosException {
		datosMundos = new ArrayList<>();
		ficheroMundos = new File(Configuracion.get().getProperty("mundos.nombreFichero"));
		cargarPredeterminados();
	}

	public static MundosDAO get() throws DatosException {
		if (instancia == null) {
			instancia = new MundosDAO();
		}
		return instancia;
	}

	@SuppressWarnings("rawtypes")
	public List getMundos(){
		return datosMundos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void recuperarDatos() {
		if (this.ficheroMundos.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroMundos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosMundos = (ArrayList<Identificable>) ois.readObject();
				ois.close();
				return;
			} 
			catch (IOException | ClassNotFoundException e) { }
		}
		
	}
	
	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fos = new FileOutputStream(ficheroMundos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datosMundos);
			oos.close();
		} 
		catch (IOException e) { }
	}


	@Override
	public Mundo obtener(String id) {
		for (Identificable mundo : datosMundos) {
			if (mundo != null && mundo.getId().equalsIgnoreCase(id)) {
				return (Mundo) mundo;
			}
		}
		return null;
	}

	@Override
	public Mundo obtener(Object obj) {
		assert obj != null;
		return this.obtener(((Mundo)obj).getId());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List obtenerTodos() {
		return datosMundos;
	}

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


	@Override
	public void borrarTodo() throws DatosException {
		datosMundos.clear();
		cargarPredeterminados();
		
	}
	
	@Override
	public void actualizar(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable mundos : datosMundos) {
			sb.append(mundos + "\n");
		}
		return sb.toString();
	}

	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();
		
		for (Identificable mundo : datosMundos) {
			sb.append(mundo.getId());
		}
		return sb.toString();
	}
	

	public int size() {
		return datosMundos.size();
	}
	
	
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

}
