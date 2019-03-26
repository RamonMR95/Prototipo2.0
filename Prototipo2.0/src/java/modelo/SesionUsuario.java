/** Proyecto: Juego de la vida.
 *  Implementa el concepto de SesionUsuario del modelo  
 *  @since: prototipo1.2
 *  @source: SesionUsuario.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

import util.Fecha;

public class SesionUsuario {
	/**
	 * Atributos de la clase Sesion de usuario
	 */
	private Usuario usr;
	private Fecha fecha;

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param usr - usr en sesion
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, Fecha fecha) {
		setUsr(usr);
		setFecha(fecha);
	}

	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 * @throws ModeloException 
	 */
	public SesionUsuario() throws ModeloException {
		this(new Usuario(), new Fecha());
	}

	/**
	 * Constructor copia.
	 * @param sesion
	 */
	public SesionUsuario(SesionUsuario sesion) {
		this.usr = new Usuario(sesion.usr);
		this.fecha = new Fecha(sesion.fecha.getYear(), sesion.fecha.getMonth(), sesion.fecha.getDay());
	}

	/**
	 * Metodo get que obtiene el usuario que inicia sesion
	 * @return usr - usr en sesion
	 */
	public Usuario getUsr() {
		return usr;
	}

	/**
	 * Metodo set que establece el usuario que va a iniciar sesion
	 * @param usr - usr en sesion
	 */
	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}

	/**
	 * Metodo get que obtiene la fecha en la que el usuario inicia sesion
	 * @return fecha
	 */
	public Fecha getFecha() {
		return fecha;
	}

	/**
	 * Metodo set que establece la fecha de inicio de sesion de usuario
	 * @param fecha
	 */
	public void setFecha(Fecha fecha) {
		assert fecha != null;
		this.fecha = fecha;
	}

	/**
	 * Metodo que genera un id de sesion cuando un usuario inicia sesion
	 * @return idSesion - id de la sesión iniciada
	 */
	public String getId() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.usr.getId());
		sb.append(this.getFecha().getMarcaTiempoMilisegundos());
		return sb.toString();
	}

	/**
	 * Redefine el método heredado de la clase Object.
	 * @return el texto formateado del estado (valores de atributos) del objeto de
	 *         la clase SesionUsuario
	 */
	@Override
	public String toString() {
		return usr.toString() + String.format("%-16s %s\n", "fecha:", fecha);
	}

} // class
