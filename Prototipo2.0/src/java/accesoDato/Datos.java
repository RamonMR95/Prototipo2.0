/** Proyecto: Juego de la vida.
 *  Implementa la fachada de datos 
 *  @since: prototipo2.0
 *  @source: Datos.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

package accesoDato;

import accesoDato.memoria.*;
import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	private UsuariosDAO usuariosDAO;
	private SesionesDAO sesionesDAO;
	private SimulacionesDAO simulacionesDAO;
	private MundosDAO mundosDAO;

	public Datos() {
		usuariosDAO = UsuariosDAO.get();
		sesionesDAO = SesionesDAO.get();
		simulacionesDAO = SimulacionesDAO.get();
		mundosDAO = MundosDAO.get();
	}
	
	// USUARIO

	public int getUsuariosRegistrados() {
		return usuariosDAO.getSize();
	}

	public UsuariosDAO getUsuariosDAO() {
		return usuariosDAO;
	}

	public SesionesDAO getSesionesDAO() {
		return sesionesDAO;
	}

	public SimulacionesDAO getSimulacionesDAO() {
		return simulacionesDAO;
	}

	public MundosDAO getMundosDAO() {
		return mundosDAO;
	}

	public Usuario buscarUsuario(String idUsr) {
		return (Usuario) usuariosDAO.obtener(idUsr);

	}

	public String toStringTodosUsuarios() {
		return usuariosDAO.listarDatos();
	}

	public void altaUsuario(Usuario usr) throws DatosException {
		usuariosDAO.alta(usr);
	}

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
		return sesionesDAO.size();
	}

	/**
	 * Metodo que registra la sesion en el almacen de sesiones del programa.
	 * @param sesion - Sesión a añadir al array de sesiones
	 * @throws DatosException 
	 */
	public void altaSesion(SesionUsuario sesion) throws DatosException {
		sesionesDAO.alta(sesion);
	}

	/**
	 * Metodo que busca una sesion del array de sesiones
	 * @param sesion - Sesion a buscar
	 * @return Sesion si la encuentra, null si no
	 */
	public SesionUsuario buscarSesion(String sesion) {
		return (SesionUsuario) sesionesDAO.obtener(sesion);
	}

	// SIMULACIONES

	/**
	 * Metodo que busca una simulacion en el array de simulaciones
	 * @param idSimulacion - Identificador de simulacion
	 * @return simulación si la encuentra o null si no la encuentra.
	 */
	public Simulacion buscarSimulacion(String idSimulacion) {
		return (Simulacion) simulacionesDAO.obtener(idSimulacion);
	}

	/**
	 * Metodo que registra la simulacion en el almacen de simulaciones
	 * @param simulacion - Simulacion a dar de alta
	 * @throws DatosException 
	 */
	public void altaSimulacion(Simulacion simulacion) throws DatosException {
		simulacionesDAO.alta(simulacion);
	}

	/**
	 * Metodo get que obtiene el numero de simulaciones registradas.
	 * @return Numero de simulaciones
	 */
	public int getSimulacionesRegistradas() {
		return simulacionesDAO.size();
	}

//	/**
//	 * Metodo que realiza un volcado con los datos de los usuarios.
//	 * @return String volcadoUsuarios -
//	 */
//	public static String volcarDatosUsuariosTexto() {
//		StringBuilder sb = new StringBuilder();
//		String delimitadorUsrApertura = "<usr>";
//		String delimitadorUsrCierre = "</usr>";
//		String delimitadorAtribUsrApertura = "<attrib>";
//		String delimitadorAtribUsrCierre = "</attrib>";
//
//		for (Usuario usr : datosUsuarios) {
//			sb.append(delimitadorUsrApertura);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getNif().getNifTexto()).append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getNombre()).append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getApellidos()).append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getDireccionPostal().toString())
//					.append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getCorreo().getCorreoTexto())
//					.append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaNacimiento().toString())
//					.append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getFechaAlta().toString())
//					.append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getClaveAcceso().getTexto())
//					.append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorAtribUsrApertura).append(usr.getRol()).append(delimitadorAtribUsrCierre);
//			sb.append(delimitadorUsrCierre);
//
//		}
//		return sb.toString();
//	}
//
//	/**
//	 * Metodo que realiza un volcado de los datos de las sesiones de texto.
//	 * @return String volcadoSesiones
//	 */
//	public static String volcarDatosSesionesTexto() {
//		StringBuilder sb = new StringBuilder();
//		String delimitadorSesionApertura = "<sesion>";
//		String delimitadorSesionCierre = "</sesion>";
//
//		for (SesionUsuario sesionUsr : datosSesiones) {
//			if (sesionUsr != null) {
//				sb.append(delimitadorSesionApertura);
//				sb.append(sesionUsr.getIdSesion());
//				sb.append(delimitadorSesionCierre);
//			}
//			break;
//
//		}
//		return sb.toString();
//	}

	// MUNDO

	/**
	 * Metodo get que obtiene el numero de mundos registrados
	 * que coincide con el tamaño del arraylist datosMundos
	 * @return Numero de mundos registrados
	 */
	public int getMundosRegistrados() {
		return mundosDAO.size();
	}

	/**
	 * Metodo que busca un mundo en el array de mundos
	 * @param id - id del mundo a buscar
	 * @return El mundo a buscar o null si no lo encuentra
	 */
	public Mundo buscarMundo(String id) {
		return (Mundo) mundosDAO.obtener(id);
	}

	/**
	 * Metodo que inserta en el arraylist de mundos de forma binaria un nuevo mundo
	 * @param mundo - mundo que va a ser dado de alta
	 * @throws DatosException
	 */
	public void altaMundo(Mundo mundo) throws DatosException {
		mundosDAO.alta(mundo);
	}

	/**
	 * Carga datos demo en la matriz que representa el mundo.
	 * @throws DatosException 
	 */
	public void cargarMundoDemo() throws DatosException {
		Mundo mundo = new Mundo();
		mundo.setEspacio(new byte[][] { 
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
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } });

		altaMundo(mundo);
	}

} // Class
