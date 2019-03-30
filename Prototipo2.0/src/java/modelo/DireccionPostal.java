/** Proyecto: Juego de la vida.
 *  Implementa el concepto de DireccionPostal del modelo  
 *  @since: prototipo1.2
 *  @source: DireccionPostal.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

import java.io.Serializable;

public class DireccionPostal implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * Atributos que van a formar nuestra direccion.
	 */
	private String calle;
	private String numero;
	private String CP;
	private String poblacion;

	/**
	 * Constructor convencional de la clase que usa los metodos set.
	 * @param calle - calle del usuario
	 * @param numero - numero del domicilio del usuario
	 * @param CP - Codigo postal del usuario
	 * @param poblacion - Población en la que reside el usuario
	 * @throws ModeloException 
	 */
	public DireccionPostal(String calle, String numero, String CP, String poblacion) throws ModeloException {
		setCalle(calle);
		setNumero(numero);
		setCP(CP);
		setPoblacion(poblacion);
	}

	/**
	 * Constructor por defecto de la clase.
	 * @throws ModeloException 
	 */
	public DireccionPostal() throws ModeloException {
		this("calle", "15", "30157", "poblacion");
	}

	/**
	 * Constructor copia de la clase.
	 * @param direccionpostal
	 */
	public DireccionPostal(DireccionPostal direccionpostal) {
		this.calle = new String(direccionpostal.calle);
		this.numero = new String(direccionpostal.numero);
		this.CP = new String(direccionpostal.CP);
		this.poblacion = new String(direccionpostal.poblacion);
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres que forma la calle.
	 * @return calle - calle del usuario
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Metodo set que establece la calle de la direccion postal
	 * @param calle - calle del usuario
	 * @throws ModeloException 
	 */
	public void setCalle(String calle) throws ModeloException {
		assert calle != null;
		
		if (calleValida(calle)) {
			this.calle = calle;
		}
		else {
			throw new ModeloException("DireccionPostal Calle: Formato no válido");
		}

	}

	/**
	 * Metodo que comprueba si nuestra calle es valida.
	 * @param calle - calle del usuario
	 * @return true, si es una calle valida.
	 */
	private boolean calleValida(String calle) {
		return calle.matches("[A-ZÑÁÉÍÓÚa-zñáéíóú\\/ ]+");
	}

	/**
	 * Metodo get que obtiene el numero que forma la direccion postal.
	 * @return numero - numero del domicilio del usuario
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Metodo set que establece el numero de la direccion postal
	 * @param numero - numero del domicilio del usuario
	 * @throws ModeloException 
	 */
	public void setNumero(String numero) throws ModeloException {
		assert numero != null;
		
		if (numeroValido(numero)) {
			this.numero = numero;
		}
		 else {
			throw new ModeloException("Direccionpostal Numero: Formato no válido");
		}
	}

	/**
	 * Metodo que comprueba si un numero es valido o no.
	 * @param numero - numero del domicilio del usuario
	 * @return true, si el numero es valido
	 */
	private boolean numeroValido(String numero) {
		return numero.matches("\\d{1,3}");
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres CP que forma el codigo postal la direccion postal.
	 * @return CP - Codigo postal del usuario
	 */
	public String getCP() {
		return CP;
	}

	/**
	 * Metodo set que establece el codigo postal de la direccion postal.
	 * @param CP - Codigo postal del usuario
	 * @throws ModeloException 
	 */
	public void setCP(String CP) throws ModeloException {
		assert CP != null;
		if (cpValido(CP)) {
			this.CP = CP;
		}
		 else {
			throw new ModeloException("Direccionpostal CP: Formato no válido");
		}
	}

	/**
	 * Metodo que comprueba si el CP es valido o no.
	 * @param CP - Codigo postal del usuario
	 * @return true, si el CP es valido.
	 */
	private boolean cpValido(String CP) {
		return CP.matches("\\d{5}");
	}

	/**
	 * Metodo get que obtiene la cadena de caracteres poblacion que forma la direccion postal.
	 * @return poblacion - Población en la que reside el usuario
	 */ 
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Metodo set que establece la poblacion de la direccion postal.
	 * @param poblacion - Población en la que reside el usuario
	 * @throws ModeloException 
	 */
	public void setPoblacion(String poblacion) throws ModeloException {
		assert poblacion != null;
		
		if (poblacionValido(poblacion)) {
			this.poblacion = poblacion;
		}
		 else {
			throw new ModeloException("Direccionpostal Población: Formato no válido");
		}

	}

	/**
	 * Metodo que comprueba si una poblacion es valida o no.
	 * @param poblacion - Población en la que reside el usuario
	 * @return true, si la poblacion es valida
	 */
	private boolean poblacionValido(String poblacion) {
		return !poblacion.matches("[ ]+");
	}

	/**
	 * Redefinicion del metodo hashCode de la clase Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CP == null) ? 0 : CP.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
		return result;
	}

	/**
	 * Redefinicion del metodo equals de la clase Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DireccionPostal other = (DireccionPostal) obj;
		if (CP == null) {
			if (other.CP != null)
				return false;
		} else if (!CP.equals(other.CP))
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		return true;
	}

	/**
	 * Redefinicion del metodo toString de la clase Object que da formato a los atributos de la clase DireccionPostal.
	 */
	@Override
	public String toString() {
		return calle + ", " + numero + ", " + CP + ", " + poblacion;
	}

	/**
	 * Redefinicion del metodo clone de la clase Object que utiliza el constructor de copia.
	 */
	@Override
	public DireccionPostal clone() {
		return new DireccionPostal(this);
	}
}
