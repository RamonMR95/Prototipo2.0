package accesoDato.memoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accesoDato.DatosException;
import accesoDato.OperacionesDAO;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.ModeloException;
import modelo.Nif;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

/** Proyecto: Juego de la vida.
 *  Clase especializada en el acceso a datos de usuario utilizando un arraylist
 *  @since: prototipo2.0
 *  @source: UsuariosDAO.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class UsuariosDAO implements OperacionesDAO {

	private static UsuariosDAO instancia = null;
	private static ArrayList<Usuario> datosUsuarios;
	private static HashMap<String, String> mapaEquivalencias;

	private UsuariosDAO() {
		datosUsuarios = new ArrayList<Usuario>();
		mapaEquivalencias = new HashMap<String, String>();
	}

	public static UsuariosDAO get() {
		if (instancia == null) {
			instancia = new UsuariosDAO();
		}
		return instancia;
	}

	/**
	 * @param clave - Key del mapa de equivalencias entre NIF, correo e idUsr
	 * @return El valor que le corresponde a dicha clave
	 */
	public String getEquivalenciaId(String clave) {
		return mapaEquivalencias.get(clave);
	}

	/**
	 * Metodo get que obtiene el número de usuarios registrados.
	 * @return Numero de Usuarios
	 */
	public int getSize() {
		return datosUsuarios.size();
	}

	@Override
	public Object obtener(String id) {
		assert id != null;
		id = mapaEquivalencias.get(id);

		if (id != null) {
			int indice = indexSortUsuario(id) - 1;

			if (indice >= 0) {
				return datosUsuarios.get(indice);
			}
		}
		return null;
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
		int posicionInsercion = indexSortUsuario(usr.getIdUsr());

		if (posicionInsercion < 0) {
			datosUsuarios.add(Math.abs(posicionInsercion) - 1, usr);
			registrarEquivalenciasId(usr);
		} else {
			if (!datosUsuarios.get(posicionInsercion - 1).equals(usr)) {
				producirVarianteIdUsr(usr);
			} else {
				throw new DatosException("Error usr repetido");
			}

		}

	}

	@Override
	public Object baja(String id) throws DatosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		assert obj != null;
		Usuario usrAct = (Usuario) obj;
		int posicion = indexSortUsuario(usrAct.getIdUsr());

		if (posicion > 0) {
			Usuario usrModificado = datosUsuarios.get(posicion - 1);
			mapaEquivalencias.remove(usrModificado.getNif().getNifTexto());
			mapaEquivalencias.remove(usrModificado.getCorreo().getCorreoTexto());
			mapaEquivalencias.put(usrAct.getNif().getNifTexto(), usrAct.getIdUsr());
			mapaEquivalencias.put(usrAct.getCorreo().getCorreoTexto(), usrAct.getIdUsr());

			datosUsuarios.set(posicion - 1, usrAct);

		} else {
			throw new DatosException("Actualizar" + usrAct.getIdUsr() + "no existe");
		}
	}

	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();

		for (Usuario usuario : datosUsuarios) {
			sb.append(usuario);
			sb.append("\n");
		}
		return sb.toString();

	}

	@Override
	public String listarId() {
		StringBuilder sb = new StringBuilder();

		for (Usuario usuario : datosUsuarios) {
			sb.append(usuario.getIdUsr());
		}
		return sb.toString();
	}

	/**
	 * Metodo que registra las equivalencias para poder acceder al programa con el nif,
	 * correo e identificador de usuario
	 * @param usr - Usuario a registrar en el mapa de equivalencias
	 */
	private void registrarEquivalenciasId(Usuario usr) {
		mapaEquivalencias.put(usr.getNif().getNifTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getCorreo().getCorreoTexto(), usr.getIdUsr());
		mapaEquivalencias.put(usr.getIdUsr(), usr.getIdUsr());
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public int indexSortUsuario(String idUsr) {
		int size = datosUsuarios.size();
		int puntoMedio;
		int limiteInferior = 0;
		int limiteSuperior = size - 1;

		while (limiteInferior <= limiteSuperior) {
			puntoMedio = (limiteSuperior + limiteInferior) / 2;
			int comparacion = idUsr.compareTo(datosUsuarios.get(puntoMedio).getIdUsr());

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
			posicionInsercion = indexSortUsuario(usr.getIdUsr());

			if (posicionInsercion < 0) {
				datosUsuarios.add(-posicionInsercion, usr);
				registrarEquivalenciasId(usr);
				return;
			}
			intentos--;

		} while (intentos >= 0);

		throw new DatosException("Error imposible generar variante");

	}

	/**
	 * Metodo que realiza una carga los Usuarios de prueba que se van a almacenar en
	 * nuestro programa.
	 */
	public void cargarUsuariosPrueba() {
		for (int i = 0; i < 10; i++) {
			try {
				alta(new Usuario(new Nif("0000000" + i + "TRWAGMYFPDXBNJZSQVHLCKE".charAt(i)), "Pepe", "López Pérez",
						new DireccionPostal("C/ Luna", "2" + i, "3013" + i, "Murcia"),
						new Correo("pepe" + i + "@gmail.com"), new Fecha(1999, 11, 12), new Fecha(2018, 01, 03),
						new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));

			} catch (DatosException | ModeloException e) {

			}
		}

	}

}