/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Datos del accesoDato  
 *  @since: prototipo1.1
 *  @source: Datos.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */

package accesoDato;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {
	/**
	 * Atributos del almacen de datos del prototipo.
	 */
	private static ArrayList<Usuario> datosUsuarios = new ArrayList<Usuario>();
	private static HashMap<String, String> mapaEquivalencias = new HashMap<String, String>();
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();
	private static ArrayList<Mundo> datosMundos = new ArrayList<Mundo>();

	// USUARIO

	/**
	 * @param clave Key del mapa de equivalencias entre NIF, correo e idUsr
	 * @return El valor que le corresponde a dicha clave
	 */
	public String getEquivalenciaId(String clave) {
		return mapaEquivalencias.get(clave);
	}

	/**
	 * Metodo get que obtiene el número de usuarios registrados.
	 * @return Numero de Usuarios
	 */
	public int getUsuariosRegistrados() {
		return datosUsuarios.size();
	}

	/**
	 * Metodo que muestra todos los Usuarios.
	 */
	public void mostrarTodosUsuarios() {
		for (Usuario usr : datosUsuarios) {
			System.out.println("\n" + usr);
		}
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
	 * Metodo que busca el usuario en el array de usuarios
	 * @param idUsr - id del usuario
	 * @return Usuario si lo encuentra o null si no
	 */
	public Usuario buscarUsuario(String idUsr) {
		assert idUsr != null;
		idUsr = mapaEquivalencias.get(idUsr);

		if (idUsr != null) {
			int indice = indexSortUsuario(idUsr) - 1;

			if (indice >= 0) {
				return datosUsuarios.get(indice);
			}
		}
		return null;

	}

	/**
	 * Metodo que registra el usuario en el almacen de usuarios
	 * @param usr - Usuario en sesion
	 * @throws Exception 
	 */
	public void altaUsuario(Usuario usr) throws DatosException {
		assert usr != null;
		int posicionInsercion = indexSortUsuario(usr.getIdUsr());

		if (posicionInsercion < 0) {
			datosUsuarios.add(Math.abs(posicionInsercion - 1), usr);
			registrarEquivalenciasId(usr);
		} else {
			if (!datosUsuarios.get(posicionInsercion - 1).equals(usr)) {
				producirVarianteIdUsr(usr);
			} else {
				throw new DatosException("Error usr repetido");
			}

		}
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

		} while (intentos > 0);

		if (intentos == 0) {
			throw new DatosException("Error imposible generar variante");
		}
	}

	/**
	 * Metodo que realiza una carga los Usuarios de prueba que se van a almacenar en
	 * nuestro programa.
	 */
	public void cargarUsuariosPrueba() {
		for (int i = 0; i < 10; i++) {
			try {
				altaUsuario(new Usuario(new Nif("0000000" + i + "TRWAGMYFPDXBNJZSQVHLCKE".charAt(i)), "Pepe",
						"López Pérez", new DireccionPostal("C/ Luna", "2" + i, "3013" + i, "Murcia"),
						new Correo("pepe" + i + "@gmail.com"), new Fecha(1999, 11, 12), new Fecha(2018, 01, 03),
						new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));

			} catch (DatosException | ModeloException e) {

			}
		}

	}

	// SESIONES

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
		assert sesion != null;
		int posicionInsercion = indexSortSesiones(sesion.getIdSesion());

		if (posicionInsercion < 0) {
			datosSesiones.add(Math.abs(posicionInsercion - 1), sesion);
		} else {
			throw new DatosException("Alta Sesion: ya existe");
		}

	}

	/**
	 * Metodo que busca una sesion del array de sesiones
	 * @param sesion - Sesion a buscar
	 * @return Sesion si la encuentra, null si no
	 */
	public SesionUsuario buscarSesion(String sesion) {
		assert sesion != null;
		sesion = mapaEquivalencias.get(sesion);

		if (sesion != null) {
			int indice = indexSortUsuario(sesion) - 1;

			if (indice >= 0) {
				return datosSesiones.get(indice);
			}
		}
		return null;

	}

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

	// SIMULACIONES

	/**
	 * Metodo que busca una simulacion en el array de simulaciones
	 * @param idSimulacion - Identificador de simulacion
	 * @return simulación si la encuentra o null si no la encuentra.
	 */
	public Simulacion buscarSimulacion(String idSimulacion) {
		for (Simulacion simulacion : datosSimulaciones) {
			if (simulacion != null && simulacion.getIdSimulacion().equalsIgnoreCase(idSimulacion)) {
				return simulacion;
			}
		}
		return null;
	}

	/**
	 * Metodo que registra la simulacion en el almacen de simulaciones
	 * @param simulacion - Simulacion a dar de alta
	 * @throws DatosException 
	 */
	public void altaSimulacion(Simulacion simulacion) throws DatosException {
		assert simulacion != null;
		int posicionInsercion = indexSortSimulacion(simulacion.getIdSimulacion());

		if (posicionInsercion < 0) {
			datosSimulaciones.add(Math.abs(posicionInsercion - 1), simulacion);

		} else {
			throw new DatosException("Alta Simulacion: ya existe");
		}

	}

	/**
	 * Metodo que inserta en el array de simulaciones
	 * @param idSimulacion - id de la simulacion a insertar
	 * @return posicion en la que insertar la simulacion
	 */
	private int indexSortSimulacion(String idSimulacion) {
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
			} else {
				limiteSuperior = puntoMedio - 1;
			}
		}
		return -(limiteInferior + 1);
	}

	/**
	 * Metodo get que obtiene el numero de simulaciones registradas.
	 * @return Numero de simulaciones
	 */
	public int getSimulacionesRegistradas() {
		return datosSimulaciones.size();
	}

	/**
	 * Metodo que realiza un volcado con los datos de los usuarios.
	 * @return String volcadoUsuarios -
	 */
	public static String volcarDatosUsuariosTexto() {
		StringBuilder sb = new StringBuilder();
		String delimitadorUsrApertura = "<usr>";
		String delimitadorUsrCierre = "</usr>";
		String delimitadorAtribUsrApertura = "<attrib>";
		String delimitadorAtribUsrCierre = "</attrib>";

		for (Usuario usr : datosUsuarios) {
			sb.append(delimitadorUsrApertura);
			sb.append(delimitadorAtribUsrApertura).append(usr.getNif().getNifTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getNombre()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getApellidos()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getDireccionPostal().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getCorreo().getCorreoTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaNacimiento().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaAlta().toString()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getClaveAcceso().getTexto()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorAtribUsrApertura).append(usr.getRol()).append(delimitadorAtribUsrCierre);
			sb.append(delimitadorUsrCierre);

		}
		return sb.toString();
	}

	/**
	 * Metodo que realiza un volcado de los datos de las sesiones de texto.
	 * @return String volcadoSesiones
	 */
	public static String volcarDatosSesionesTexto() {
		StringBuilder sb = new StringBuilder();
		String delimitadorSesionApertura = "<sesion>";
		String delimitadorSesionCierre = "</sesion>";

		for (SesionUsuario sesionUsr : datosSesiones) {
			if (sesionUsr != null) {
				sb.append(delimitadorSesionApertura);
				sb.append(sesionUsr.getIdSesion());
				sb.append(delimitadorSesionCierre);
			}
			break;

		}
		return sb.toString();
	}

	// MUNDO

	/**
	 * Metodo get que obtiene el numero de mundos registrados
	 * que coincide con el tamaño del arraylist datosMundos
	 * @return Numero de mundos registrados
	 */
	public int getMundosRegistrados() {
		return datosMundos.size();
	}

	/**
	 * Metodo que busca un mundo en el array de mundos
	 * @param id - id del mundo a buscar
	 * @return El mundo a buscar o null si no lo encuentra
	 */
	public Mundo buscarMundo(String id) {
		for (Mundo mundo : datosMundos) {
			if (mundo != null && mundo.getId().equalsIgnoreCase(id)) {
				return mundo;
			}
		}
		return null;
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public int indexSortMundo(String idUsr) {
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

	/**
	 * Metodo que inserta en el arraylist de mundos de forma binaria un nuevo mundo
	 * @param mundo - mundo que va a ser dado de alta
	 * @throws DatosException
	 */
	public void altaMundo(Mundo mundo) throws DatosException {
		assert mundo != null;
		int posicionInsercion = indexSortMundo(mundo.getId());

		if (posicionInsercion < 0) {
			datosMundos.add(Math.abs(posicionInsercion - 1), mundo);
		} else {
			throw new DatosException("Error Mundo: nombre repetido");

		}

	}

	/**
	 * Carga datos demo en la matriz que representa el mundo.
	 * @throws DatosException 
	 */
	public void cargarMundoDemo() throws DatosException {
		Mundo mundo = new Mundo();
		mundo.setEspacio(new byte[][]
			  { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } });
		
		altaMundo(mundo);
	}

} // Class
