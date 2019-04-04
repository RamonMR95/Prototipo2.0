package accesoDato;

import java.util.List;
import accesoDato.fichero.*;
//import accesoDato.memoria.MundosDAO;
//import accesoDato.memoria.SesionesDAO;
//import accesoDato.memoria.SimulacionesDAO;
import modelo.*;

/** Proyecto: Juego de la vida.
 *  Implementa la fachada de datos 
 *  @since: prototipo2.0
 *  @source: Datos.java 
 *  @version: 2.0 - 2019/03/21
 *  @author: Ramon Moñino
 */

public class Datos {
	
	/* Atributos de la clase DATOS */
	private UsuariosDAO usuariosDAO;
	private SesionesDAO sesionesDAO;
	private SimulacionesDAO simulacionesDAO;
	private MundosDAO mundosDAO;

	/**
	 * Constructor por defecto de la clase DATOS
	 * @throws DatosException
	 */
	public Datos() throws DatosException {
		usuariosDAO = UsuariosDAO.get();
		sesionesDAO = SesionesDAO.get();
		simulacionesDAO = SimulacionesDAO.get();
		mundosDAO = MundosDAO.get();
	}
	
	/**
	 * Metodo que cierra las conexiones de las clases DAO
	 */
	public void cerrar() {
		usuariosDAO.cerrar();
		sesionesDAO.cerrar();
		mundosDAO.cerrar();
		simulacionesDAO.cerrar();
	}
	
	// USUARIO

	/**
	 * Metodo que obtiene el tamaño del array de usuariosDAO
	 * @return size - Tamaño del array de usuariosDAO
	 */
	public int getUsuariosRegistrados() {
		return usuariosDAO.getSize();
	}

	/**
	 * Metodo que obtiene el objeto usuariosDAO
	 * @return usuariosDAO - Objeto usuariosDAO
	 */
	public UsuariosDAO getUsuariosDAO() {
		return usuariosDAO;
	}
	
	/**
	 * Metodo que busca un usuario y lo devuelve
	 * @param idUsr - Id del usuario a buscar
	 * @return Usuario - Si lo encuentra o null si no.
	 */
	public Usuario obtenerUsuario(String idUsr) {
		return usuariosDAO.obtener(idUsr);

	}

	/**
	 * Metodo que obtiene todos los usuarios de usuariosDAO
	 * @return lista de usuarios - Lista de Usuarios del programa
	 */
	public List obtenerTodosUsuarios() {
		return usuariosDAO.obtenerTodos();
	}
	
	/**
	 * Metodo que da de alta a un usuario en el almacen de usuariosDAO
	 * @param usr - Usuario a dar de alta
	 * @throws DatosException - Si el usuario ya existia en el almacen usuariosDAO
	 */
	public void altaUsuario(Usuario usr) throws DatosException {
		usuariosDAO.alta(usr);
	}
	
	/**
	 * Metodo que da de baja a un usuario del almacen de usuariosDAO
	 * @param idUsr - Id de usuario
	 * @return Usuario - Usuario dado de baja
	 * @throws DatosException - Si no encuentra el usuario
	 */
	public Usuario bajaUsuario(String idUsr) throws DatosException {
		return usuariosDAO.baja(idUsr);
	}
	
	/**
	 * Metodo que actualiza los datos de un usuario
	 * @param obj - Usuario a actualizar
	 * @throws DatosException - Si el usuario no existe
	 */
	public void actualizarUsuario(Object obj) throws DatosException {
		usuariosDAO.actualizar(obj);
	}
	
	/**
	 * Metodo que muestra todos los usuarios del almacen de usuarios
	 * @return datos usuarios - Cadena de texto con todos los usuarios del almacen de usuarios
	 */
	public String toStringDatosUsuarios() {
		return usuariosDAO.listarDatos();
	}
	
	/**
	 * Metodo que muestra todos los IDS de usuarios del almacen de usuarios
	 * @return ids de usuarios - Cadena de texto con todos los ids de usuarios del almacen de usuarios
	 */
	public String toStringIdUsuarios() {
		return usuariosDAO.listarId();
	}

	/**
	 * Metodo que borra todos los usuarios del almacen de usuariosDAO
	 */
	public void borrarTodosUsuarios() {
		usuariosDAO.borrarTodo();
	}
	

	// SESIONES
	
	/**
	 * Metodo que obtiene el objeto sesionesDAO
	 * @return sesionesDAO - Objeto sesionesDAO
	 */
	public SesionesDAO getSesionesDAO() {
		return sesionesDAO;
	}

	/**
	 * Metodo que obtiene el tamaño del array de sesionesDAO
	 * @return size - Tamaño del array de sesionesDAO
	 */
	public int getSesionesRegistradas() {
		return sesionesDAO.size();
	}
	
	/**
	 * Metodo que busca una sesion de usuario y la devuelve
	 * @param id - Id de la sesion a buscar
	 * @return Sesion de usuario - Si lo encuentra o null si no.
	 */
	public SesionUsuario obtenerSesiones(String id) {
		return sesionesDAO.obtener(id);
	}
	
	/**
	 * Metodo que obtiene todas las sesiones de usuario de sesionesDAO
	 * @return lista de sesiones de usuario - Lista de sesiones de usuario del programa
	 */
	public List obtenerTodasSesiones() {
		return sesionesDAO.obtenerTodos();
	}
	
	/**
	 * Metodo que da de alta a una sesion de usuario en el almacen de sesionesDAO
	 * @param sesion - Sesion de usuario a dar de alta
	 * @throws DatosException - Si la sesion de usuario ya existia en el almacen sesionesDAO
	 */
	public void altaSesion(SesionUsuario sesion) throws DatosException {
		sesionesDAO.alta(sesion);
	}
	
	/**
	 * Metodo que da de baja a una sesion de usuario del almacen de sesionesDAO
	 * @param id - Id de la sesion de usuario
	 * @return Sesion de Usuario - Sesion de Usuario dada de baja
	 * @throws DatosException - Si no encuentra la sesion de usuario
	 */
	public SesionUsuario bajaSesion(String id) throws DatosException {
		return sesionesDAO.baja(id);
	}

	/**
	 * Metodo que actualiza los datos de una sesion de usuario
	 * @param obj - Sesion de usuario a actualizar
	 * @throws DatosException - Si la sesion de usuario no existe
	 */
	public void actualizarSesion(Object obj) throws DatosException {
		sesionesDAO.actualizar(obj);
	}
	
	/**
	 * Metodo que muestra todas las sesiones de usuario del almacen sesionesDAO
	 * @return datos sesiones - Cadena de texto con todas las sesiones de usuario del almacen sesionesDAO
	 */
	public String toStringDatosSesiones() {
		return sesionesDAO.listarDatos();
	}
	
	/**
	 * Metodo que muestra todos los IDS de sesiones de usuarios  del almacen sesionesDAO
	 * @return ids de sesiones de usuarios - Cadena de texto con todos los ids de sesiones de usuarios del almacen sesionesDAO
	 */
	public String toStringIdSesiones() {
		return sesionesDAO.listarId();
	}
	
	/**
	 * Metodo que borra todos los usuarios del almacen de usuariosDAO
	 */
	public void borrarTodasSesiones() {
		sesionesDAO.borrarTodo();
	}
	
	public List<Identificable> obtenerSesionesUsuario(String idUsr) throws ModeloException{
		return sesionesDAO.obtenerTodasMismoUsr(idUsr);
	}

	// SIMULACIONES


	/**
	 * Metodo que obtiene el objeto simulacionesDAO
	 * @return simulacionesDAO - Objeto simulacionesDAO
	 */
	public SimulacionesDAO getSimulacionesDAO() {
		return simulacionesDAO;
	}
	
	/**
	 * Metodo que obtiene el tamaño del array de simulacionesDAO
	 * @return size - Tamaño del array de simulacionesDAO
	 */
	public int getSimulacionesRegistradas() {
		return simulacionesDAO.size();
	}

	/**
	 * Metodo que busca una simulacion y la devuelve
	 * @param id - Id de la simulacion a buscar
	 * @return Simulacion - Si lo encuentra o null si no.
	 */
	public Simulacion obtenerSimulacion(String id) {
		return simulacionesDAO.obtener(id);
	}
	
	/**
	 * Metodo que obtiene todas las simulaciones de simulacionesDAO
	 * @return lista Simulaciones - Lista de simulaciones del programa
	 */
	public List obtenerTodasSimulaciones() {
		return simulacionesDAO.obtenerTodos();
	}

	/**
	 * Metodo que da de alta a una simulacion en el almacen de simulacionesDAO
	 * @param simulacion - Simulacion a dar de alta
	 * @throws DatosException - Si la simulacion ya existia en el almacen simulacionesDAO
	 */
	public void altaSimulaciones(Simulacion simulacion) throws DatosException {
		simulacionesDAO.alta(simulacion);
	}
	
	/**
	 * Metodo que da de baja a una simulacion del almacen de simulacionesDAO
	 * @param id - Id de la simulacion
	 * @return Simulacion - Simulacion dada de baja
	 * @throws DatosException - Si no encuentra la simulacion
	 */
	public Simulacion bajaSimulaciones(String id) throws DatosException {
		return simulacionesDAO.baja(id);
	}
	
	/**
	 * Metodo que actualiza los datos de una simulacion
	 * @param obj - Simulacion a actualizar
	 * @throws DatosException - Si la simulacion no existe
	 */
	public void actualizarSimulacion(Object obj) throws DatosException {
		simulacionesDAO.actualizar(obj);
	}
	
	/**
	 * Metodo que muestra todas las simulaciones del almacen simulacionesDAO
	 * @return datos simulaciones - Cadena de texto con todas las simulaciones del almacen simulacionesDAO
	 */
	public String toStringDatosSimulaciones() {
		return simulacionesDAO.listarDatos();
	}
	
	/**
	 * Metodo que muestra todos los IDS de simulaciones  del almacen simulacionesDAO
	 * @return ids de simulaciones - Cadena de texto con todos los ids de simulaciones del almacen simulacionesDAO
	 */
	public String toStringIdSimulaciones() {
		return simulacionesDAO.listarId();
	}
	
	/**
	 * Metodo que borra todas las simulaciones del almacen de simulacionesDAO
	 */
	public void borrarTodasSimulaciones() {
		simulacionesDAO.borrarTodo();
	}

	// MUNDOS

	/**
	 * Metodo que obtiene el objeto mundosDAO
	 * @return mundosDAO - Objeto mundosDAO
	 */
	public MundosDAO getMundosDAO() {
		return mundosDAO;
	}
	
	/**
	 * Metodo que obtiene el tamaño del array de mundosDAODAO
	 * @return size - Tamaño del array de mundosDAODAO
	 */
	public int getMundosRegistrados() {
		return mundosDAO.size();
	}

	/**
	 * Metodo que busca un mundo  y lo devuelve
	 * @param id - Id del mundo a buscar
	 * @return Mundo - Si lo encuentra o null si no.
	 */
	public Mundo obtenerMundo(String id) {
		return mundosDAO.obtener(id);
	}
	
	/**
	 * Metodo que obtiene todos los mundos de mundosDAO
	 * @return lista mundos - Lista de mundos del programa
	 */
	public List obtenerTodosMundos() {
		return mundosDAO.obtenerTodos();
	}

	/**
	 * Metodo que da de alta a un mundo en el almacen de mundosDAO
	 * @param mundo - Mundo a dar de alta
	 * @throws DatosException - Si el mundo ya existia en el almacen mundosDAO
	 */
	public void altaMundos(Object obj) throws DatosException {
		mundosDAO.alta(obj);
	}
	
	/**
	 * Metodo que da de baja a un mundo del almacen de mundosDAO
	 * @param id - Id del mundo
	 * @return Mundo - Mundo dada de baja
	 * @throws DatosException - Si no encuentra el mundo
	 */
	public Mundo bajaMundos(String id) throws DatosException {
		return mundosDAO.baja(id);
	}
	
	/**
	 * Metodo que actualiza los datos de un mundo
	 * @param obj - Mundo a actualizar
	 * @throws DatosException - Si el mundo no existe
	 */
	public void actualizarMundos(Object obj) {
		mundosDAO.actualizar(obj);
	}
	
	/**
	 * Metodo que muestra todos los mundos del almacen mundosDAO
	 * @return datos mundos - Cadena de texto con todos los mundos del almacen mundosDAO
	 */
	public String toStringDatosMundos() {
		return mundosDAO.listarDatos();
	}
	
	/**
	 * Metodo que muestra todos los IDS de mundos del almacen mundosDAO
	 * @return ids de mundos - Cadena de texto con todos los ids de mundos del almacen mundosDAO
	 */
	public String toStringIdMundos() {
		return mundosDAO.listarId();
	}
	
	/**
	 * Metodo que borra todas las simulaciones del almacen de simulacionesDAO
	 * @throws DatosException
	 */
	public void borrarTodosMundos() throws DatosException {
		mundosDAO.borrarTodo();
	}

} // Class DATOS
