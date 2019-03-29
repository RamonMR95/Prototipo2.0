package modelo;

import java.io.Serializable;

import config.ConfiguracionSingletonCanonico;
import util.Fecha;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.
 *  En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *  	- Clase demasiado grande.
 *  	- Clase acaparadora, que delega poca responsabilidad.  
 *  @since: prototipo1.2
 *  @source: Usuario.java 
 *  @version: 1.2 - 2018/11/21 
 *  @author: Ramon Moñino
 */

public class Usuario extends Persona implements Identificable, Serializable {

	private String id;
	private Fecha fechaAlta;
	private ClaveAcceso claveAcceso;
	private RolUsuario rol;
	public enum RolUsuario { INVITADO, NORMAL, ADMINSTRADOR };
	private static ConfiguracionSingletonCanonico config = ConfiguracionSingletonCanonico.get();

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param nif - NIF del usuario
	 * @param nombre - Nombre del usuario
	 * @param apellidos - Apellidos del usuario
	 * @param domicilio - Domicilio en el que el usuario reside
	 * @param correo - Correo del Usuario 
	 * @param fechaNacimiento - Fecha de nacimiento del usuario
	 * @param fechaAlta - Fecha de alta del usuario en el programa
	 * @param claveAcceso - Clave de acceso del usuario en nuestro sistema
	 * @param rol - Rol al que pertenece el usuario
	 * @throws ModeloException 
	 */
	public Usuario(Nif nif, String nombre, String apellidos, DireccionPostal direccionPostal, Correo correo,
			Fecha fechaNacimiento, Fecha fechaAlta, ClaveAcceso claveAcceso, RolUsuario rol) throws ModeloException {
		
		super(nif, nombre, apellidos, direccionPostal, correo, fechaNacimiento);
		generarIdUsr();
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 * @throws ModeloException 
	 */
	public Usuario() throws ModeloException {
		this(new Nif(), "Nombre", "Apellido Apellido", new DireccionPostal(), new Correo(),
				new Fecha().addAños(-config.getEdadMinima()),
				new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);

	}

	/**
	 * Constructor copia de la clase.
	 * @param usr , usuario a copiar
	 */
	public Usuario(Usuario usr) {
		super(usr);
		this.id = new String(usr.id);
		this.fechaAlta = new Fecha(usr.fechaAlta.getYear(), usr.fechaAlta.getMonth(), usr.fechaAlta.getDay());
		this.claveAcceso = new ClaveAcceso(usr.claveAcceso);
		this.rol = usr.rol;
		generarVarianteIdUsr();
		
	}
	
	/**
	 * Devuelve el id del usuario
	 * @return isUsr - id del usuario
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que genera un ID de usuario
	 * @param idUsr - id del usuario
	 * @return id - id de usuario generado por el programa
	 */
	private String generarIdUsr() {
		StringBuilder id = new StringBuilder();
		id.append(this.nombre.substring(0, 1).toUpperCase());
		String[]divApellidos = this.apellidos.split("\\s+");
		id.append(divApellidos[0].substring(0, 1).toUpperCase());
		id.append(divApellidos[1].substring(0, 1).toUpperCase());
		id.append(this.nif.getNifTexto().substring(7, 9));
		this.id = id.toString();
		return id.toString();
	}

	/**
	 * Metodo que genera una variante de idUsr para utilizarlo en caso 
	 * de repetición de idUsr en nuestra base de datos
	 */
	private void generarVarianteIdUsr() {
		String alfabetoNif = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alfabetoDesplazado = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
		this.id = this.id.substring(0, 4) + alfabetoDesplazado.charAt(alfabetoNif.indexOf(id.charAt(4)));
	}

	
	/**
	 * Metodo get que obtiene el objeto fecha de alta
	 * @return fechaAlta - Fecha de alta del usuario en nuestro programa.
	 */
	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Metodo set que establece un objeto fecha de alta dado por parametro
	 * @param fechaAlta - Fecha de alta del usuario en nuestro programa.
	 * @throws ModeloException 
	 */
	public void setFechaAlta(Fecha fechaAlta) throws ModeloException {
		assert fechaAlta != null;
		
		if (fechaAltaValida(fechaAlta)) {
			this.fechaAlta = fechaAlta;
		}
		else {
			throw new ModeloException("Usuario FechaAlta: Formato de fecha de alta no válido");
		}
	}

	/**
	 * Metodo que comprueba si una fecha de alta es valida o no
	 * @param fechaAlta - Fecha de alta del usuario en nuestro programa.
	 * @return true, si la fecha de alta es valida
	 */
	private boolean fechaAltaValida(Fecha fechaAlta) {
		return !fechaAlta.after(new Fecha());
	}

	
	@Override
	protected boolean fechaNacimientoValida(Fecha fechaNacimiento) {
		return !fechaNacimiento.after(new Fecha().addAños(-config.getEdadMinima()));
	}
	
	/**
	 * Metodo get que obtiene el objeto clave de acceso del usuario
	 * @return claveAcceso - Clave de acceso de usuario
	 */
	public ClaveAcceso getClaveAcceso() {
		return claveAcceso;
	}

	/**
	 * Metodo set que establece la clave de acceso pasada por parametro
	 * @param claveAcceso - Clave de acceso de usuario
	 */
	public void setClaveAcceso(ClaveAcceso claveAcceso) {
		assert claveAcceso != null;
		this.claveAcceso = claveAcceso;
	}

	/**
	 * Metodo get que obtiene el rol del usuario
	 * @return rol - rol del usuario dentro de nuestra aplicacion
	 */
	public RolUsuario getRol() {
		return rol;
	}

	/**
	 * Metodo set que establece el rol de nuestro usuario pasado por parametro
	 * @param rol - Rol del usuario
	 */
	public void setRol(RolUsuario rol) {
		assert rol != null;
			this.rol = rol;
	}

	/**
	 * Redefinicion del metodo toString.
	 * @return String con los valores de los atributos de la clase
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" ,
				"idUsr:", this.id, 	
				"fechaAlta:", this.fechaAlta.getYear() + "." + 
							this.fechaAlta.getMonth() + "." + 
							this.fechaAlta.getDay(),
				"claveAcceso:", this.claveAcceso, 
				"rol:", this.rol);
	}
	
	@Override
	protected Usuario clone() throws CloneNotSupportedException {
		return new Usuario(this);
	}

} // class
