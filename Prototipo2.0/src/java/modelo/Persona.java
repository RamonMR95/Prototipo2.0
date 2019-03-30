package modelo;

import java.io.Serializable;

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

public abstract class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Nif nif;
	protected String nombre;
	protected String apellidos;
	protected DireccionPostal direccionPostal;
	protected Correo correo;
	protected Fecha fechaNacimiento;
	

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param nif - NIF del usuario
	 * @param nombre - Nombre del usuario
	 * @param apellidos - Apellidos del usuario
	 * @param domicilio - Domicilio en el que el usuario reside
	 * @param correo - Correo del Usuario 
	 * @param fechaNacimiento - Fecha de nacimiento del usuario
	 * @throws ModeloException 
	 */
	public Persona(Nif nif, String nombre, String apellidos, DireccionPostal direccionPostal, Correo correo,
			Fecha fechaNacimiento) throws ModeloException {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccionPostal(direccionPostal);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);

	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 * @throws ModeloException 
	 */
	public Persona() throws ModeloException {
		this(new Nif(), "Nombre", "Apellido Apellido", new DireccionPostal(), new Correo(), new Fecha());
	}

	/**
	 * Constructor copia de la clase.
	 * @param usr , usuario a copiar
	 */
	public Persona(Persona persona) {
		this.nif = new Nif(persona.nif);
		this.nombre = new String(persona.nombre);
		this.apellidos = new String(persona.apellidos);
		this.direccionPostal = new DireccionPostal(persona.direccionPostal);
		this.correo = new Correo(persona.correo);
		this.fechaNacimiento = new Fecha(persona.fechaNacimiento.getYear(), persona.fechaNacimiento.getMonth(),
				persona.fechaNacimiento.getDay());
		
	}
	
	/**
	 * Metodo de get que obtiene el objeto nif de la clase Nif
	 * @return nif - Nif de usuario
	 */
	public Nif getNif() {
		return nif;
	}

	/**
	 * Metodo set que establece un objeto Nif dado por parametro
	 * @param nif - Nif de usuario
	 */
	public void setNif(Nif nif) {
		assert nif != null;
		this.nif = nif;
	}

	/**
	 * Metodo get que obtiene el nombre del usuario en forma de cadena de texto
	 * @return nombre - Nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo set que establece un nombre de usuario dado por parametro
	 * @param nombre - Nombre del usuario
	 * @throws ModeloException 
	 */
	public void setNombre(String nombre) throws ModeloException {
		assert nombre != null;
		
		if (nombreValido(nombre)) {
			this.nombre = nombre;
		}
		 else {
			throw new ModeloException("Usuario: Formato del nombre no válido");
		}
	}

	/**
	 * Metodo que comprueba la validez de un nombre.
	 * @param nombre - Nombre del usuario
	 * @return true si cumple.
	 */
	private boolean nombreValido(String nombre) {
		return nombre.matches("^([A-ZÁÉÍÓÚ][a-záéíóú]+)");
	}

	/**
	 * Metodo get que obtiene los apellidos en forma de cadena de texto
	 * @return apellidos - Apellidos del usuarios
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo set que establece los apellidos de un usuario dado por parametro
	 * @param apellidos - Apellidos del usuarios
	 * @throws ModeloException 
	 */
	public void setApellidos(String apellidos) throws ModeloException {
		assert apellidos != null;
		
		if (apellidosValidos(apellidos)) {
			this.apellidos = apellidos;
		}
		 else {
			 throw new ModeloException("Usuario: Formato apellidos no válido");
		}

	}

	/**
	 * Comprueba validez de los apellidos.
	 * @param apellidos - Apellidos del usuarios
	 * @return true si cumple.
	 */
	private boolean apellidosValidos(String apellidos) {
		return apellidos.matches("(^[A-Z][a-záéíóú]+)(\\s)([A-Z][a-záéíóú]+)");
	}
	

	/**
	 * Metodo get que obtiene el objeto direccionpostal
	 * @return direccionPostal - Direccion postal del usuario
	 */
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	/**
	 * Metodo set que establece la direccion postal de un usuario dada por parametro
	 * @param direccionPostal - Direccion postal del usuario
	 */
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		assert direccionPostal != null;
		this.direccionPostal = direccionPostal;

	}

	/**
	 * Metodo get que obtiene el Objeto correo
	 * @return correo - Correo del usuario
	 */
	public Correo getCorreo() {
		return correo;
	}

	/**
	 * Metodo set que establece el correo de un usuario que se pasado por parametro
	 * @param correo - Correo del usuario
	 */
	public void setCorreo(Correo correo) {
		assert correo != null;
		this.correo = correo;
	}

	/**
	 * Metodo set que obtiene el objeto fechadenacimiento de un usuario
	 * @return fechaNacimiento - Fecha de nacimiento del usuario
	 */
	public Fecha getFechaNacimiento() {
		return fechaNacimiento;

	}

	/**
	 * Metodo set que establece la fecha de nacimiento de un usuario dada por parametro.
	 * @param fechaNacimiento - Fecha de nacimiento del usuario
	 * @throws ModeloException 
	 */
	public void setFechaNacimiento(Fecha fechaNacimiento) throws ModeloException {
		assert fechaNacimiento != null;
		
		if (fechaNacimientoValida(fechaNacimiento)) {
			this.fechaNacimiento = fechaNacimiento;
		}
		 else {
			throw new ModeloException("Usuario FechaNacimiento: Formato de fecha de nacimiento no válido");
		}
	}

	/**
	 * Metodo que comprueba si una fecha de nacimiento es valida
	 * @param fechaNacimiento - Fecha de nacimiento del usuario				
	 * @return true si es valida
	 */
	protected abstract boolean fechaNacimientoValida(Fecha fechaNacimiento);


	/**
	 * Redefinicion del metodo toString.
	 * @return String con los valores de los atributos de la clase
	 */
	@Override
	public String toString() {
		return String.format(
				"%-16s %s\n" +
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n" + 
				"%-16s %s\n",
				"nif:", this.nif, 
				"nombre:", this.nombre, 
				"apellidos:", this.apellidos,
				"domicilio:", this.direccionPostal,
				"correo:", this.correo, 
				"fechaNacimiento:",
				this.fechaNacimiento.getYear() + "." + this.fechaNacimiento.getMonth() + "."
						+ this.fechaNacimiento.getDay());
	}

} // class
