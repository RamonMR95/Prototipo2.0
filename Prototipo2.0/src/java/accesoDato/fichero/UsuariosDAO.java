package accesoDato.fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accesoDato.IndexSort;
import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import config.Configuracion;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.Identificable;
import modelo.ModeloException;
import modelo.Nif;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de usuario utilizando 
 *  dos ficheros para el arraylist y un HashMap
 *  @since: prototipo2.0
 *  @source: UsuariosDAO.java 
 *  @version: 2.0 - 2019/03/28
 *  @author: Ramon Moñino
 */

public class UsuariosDAO extends IndexSort implements OperacionesDAO, Persistente {

	private static UsuariosDAO instancia = null;
	private ArrayList<Identificable> datosUsuarios;
	private HashMap<String, String> mapaEquivalencias;
	private File ficheroUsuarios;
	private File ficheroEquivalencias;

	private UsuariosDAO() {
		datosUsuarios = new ArrayList<Identificable>();
		mapaEquivalencias = new HashMap<String, String>();
		ficheroUsuarios = new File(Configuracion.get().getProperty("usuarios.nombreFichero"));
		ficheroEquivalencias = new File(Configuracion.get().getProperty("equivalenciasId.nombreFichero"));
		recuperarDatos();
		
	}

	public static UsuariosDAO get() {
		if (instancia == null) {
			instancia = new UsuariosDAO();
		}
		return instancia;
	}
	
	// Persistencia
	@SuppressWarnings("unchecked")
	public void recuperarDatos() {
		if (this.ficheroUsuarios.exists() && this.ficheroEquivalencias.exists()) {
			try {
				FileInputStream fis = new FileInputStream(ficheroUsuarios);
				ObjectInputStream ois = new ObjectInputStream(fis);
				datosUsuarios = (ArrayList<Identificable>) ois.readObject();

				FileInputStream fis2 = new FileInputStream(ficheroEquivalencias);
				ObjectInputStream ois2 = new ObjectInputStream(fis2);
				mapaEquivalencias = (HashMap<String, String>) ois2.readObject();
				return;

			} 
			catch (IOException | ClassNotFoundException e) {}
		} 
		borrarTodo();
	}

	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fos = new FileOutputStream(ficheroUsuarios);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(datosUsuarios);

			FileOutputStream fos2 = new FileOutputStream(ficheroEquivalencias);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(mapaEquivalencias);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEquivalenciaId(String clave) {
		return mapaEquivalencias.get(clave);
	}

	public int getSize() {
		return datosUsuarios.size();
	}
	
	@Override
	public Usuario obtener(String id) {
		assert id != null;
		id = mapaEquivalencias.get(id);

		if (id != null) {
			int indice = indexSort(id, datosUsuarios) - 1;

			if (indice >= 0) {
				return (Usuario) datosUsuarios.get(indice);
			}
		}
		return null;
	}

	@Override
	public Usuario obtener(Object obj) {
		return this.obtener(((Usuario)obj).getId());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List obtenerTodos() {
		return datosUsuarios;
	}

	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Usuario usr = (Usuario) obj;
		int posicionInsercion = indexSort(usr.getId(), datosUsuarios);

		if (posicionInsercion < 0) {
			datosUsuarios.add(Math.abs(posicionInsercion) - 1, usr);
			registrarEquivalenciasId(usr);
		} 
		else {
			if (!datosUsuarios.get(posicionInsercion - 1).equals(usr)) {
				producirVarianteIdUsr(usr);
			} 
			else {
				throw new DatosException("Error usr repetido");
			}
		}
	}

	@Override
	public Usuario baja(String id) throws DatosException {
		assert id != null;
		int posicion = indexSort(id, datosUsuarios);

		if (posicion > 0) {
			Usuario usrEliminado = (Usuario) datosUsuarios.remove(posicion - 1);
			mapaEquivalencias.remove(usrEliminado.getId());
			mapaEquivalencias.remove(usrEliminado.getNif().getNifTexto());
			mapaEquivalencias.remove(usrEliminado.getCorreo().getCorreoTexto());
			return usrEliminado;
		}
		else {
			throw new DatosException("Baja : " + id + " no existe");
		}
	}

	@Override
	public void borrarTodo() {
		datosUsuarios.clear();
		mapaEquivalencias.clear();
		cargarUsuariosPredeterminados();
		
	}
	
	@Override
	public void cerrar() {
		guardarDatos();
		
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Usuario usrAct = (Usuario) obj;
		int posicion = indexSort(usrAct.getId(), datosUsuarios);

		if (posicion > 0) {
			Usuario usrModificado = (Usuario) datosUsuarios.get(posicion - 1);
			mapaEquivalencias.remove(usrModificado.getNif().getNifTexto());
			mapaEquivalencias.remove(usrModificado.getCorreo().getCorreoTexto());
			mapaEquivalencias.put(usrAct.getNif().getNifTexto(), usrAct.getId());
			mapaEquivalencias.put(usrAct.getCorreo().getCorreoTexto(), usrAct.getId());
			datosUsuarios.set(posicion - 1, usrAct);

		} 
		else {
			throw new DatosException("Actualizar" + usrAct.getId() + "no existe");
		}
		
	}

	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();

		for (Identificable usuario : datosUsuarios) {
			sb.append(usuario);
			sb.append("\n");
		}
		return sb.toString();

	}

	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();

		for (Identificable usuario : datosUsuarios) {
			sb.append(usuario.getId());
		}
		return sb.toString();
	}

	/**
	 * Metodo que registra las equivalencias para poder acceder al programa con el nif,
	 * correo e identificador de usuario
	 * @param usr - Usuario a registrar en el mapa de equivalencias
	 */
	private void registrarEquivalenciasId(Usuario usr) {
		mapaEquivalencias.put(usr.getNif().getNifTexto(), usr.getId());
		mapaEquivalencias.put(usr.getCorreo().getCorreoTexto(), usr.getId());
		mapaEquivalencias.put(usr.getId(), usr.getId());
	}
	
	/**
	 * Metodo que produce un idUsr diferente en caso de coincidencia
	 * @param usr - Usuario en sesion
	 * @throws DatosException
	 */
	private void producirVarianteIdUsr(Usuario usr) throws DatosException {
		int posicionInsercion;
		int intentos = "BCDEFGHIJKLMNOPQRSTUVWXYZA".length() - 1;

		do {
			/* Coincidencia de ig generar variante */
			usr = new Usuario(usr);
			posicionInsercion = indexSort(usr.getId(), datosUsuarios);

			if (posicionInsercion < 0) {
				datosUsuarios.add(-posicionInsercion, usr);
				registrarEquivalenciasId(usr);
				return;
			}
			intentos--;

		} 
		while (intentos >= 0);

		throw new DatosException("Error imposible generar variante");

	}


	public void cargarUsuariosPredeterminados() {
		try {

			alta(new Usuario(new Nif("00000000T"), "Admin", "Admin Admin", new DireccionPostal(), new Correo(),
					new Fecha(0001, 01, 01), new Fecha(), new ClaveAcceso("admin"),
					RolUsuario.ADMINSTRADOR));

			alta(new Usuario(new Nif("00000001R"), "Invitado", "Invitado Invitado", new DireccionPostal(), new Correo(),
					new Fecha(0001, 01, 01), new Fecha(), new ClaveAcceso("Miau#0"), RolUsuario.INVITADO));
		} 
		catch (DatosException | ModeloException e) {}
	}

} // Class UsuariosDAO
