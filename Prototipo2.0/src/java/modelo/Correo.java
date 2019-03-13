/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Correo del modelo  
 *  @since: prototipo1.2
 *  @source: Correo.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

public class Correo {
	/**
	 * Cadena de caracteres que va a formar nuestro correo
	 */
	private String texto;

	/**
	 * Constructor convencional de la clase Correo
	 * @param texto - Cadena de caracteres que forma el nombre del correo
	 * @throws ModeloException 
	 */
	public Correo(String texto) throws ModeloException {
		setCorreo(texto);
	}

	/**
	 * Constructor por defecto de la clase que le da un valor por defecto a nuestro
	 * correo
	 * @throws ModeloException 
	 */
	public Correo() throws ModeloException {
		setCorreo("correo@correo.es");
		
	}

	/**
	 * Constructor de copia de la clase
	 * @param correo - Cadena de caracteres que forma el nombre del correo
	 */
	public Correo(Correo correo) {
		this.texto = new String(correo.texto);
	}

	/**
	 * Metodo get que obtiene el texto que forma el correo del usuario
	 * @return texto - Cadena de caracteres que forma el nombre del correo
	 */
	public String getCorreoTexto() {
		return texto;
	}

	/**
	 * Metodo set que establece el texto que forma el correo del usuario
	 * @param texto - Cadena de caracteres que forma el nombre del correo
	 * @throws ModeloException 
	 */
	public void setCorreo(String texto) throws ModeloException {
		assert texto != null; 
		
		if (correoValido(texto)) {
			this.texto = texto;
		}
		 else {
			throw new ModeloException("Correo : Formato no válido");
		}

	}

	/**
	 * Metodo que comprueba si un correo es valido o no
	 * @param texto - Cadena de caracteres que forma el nombre del correo
	 * @return true, si el correo es valido
	 */
	private boolean correoValido(String texto) {
		return texto.matches("^[\\w-\\+]+(\\.[\\w-\\+]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	}

	/**
	 * Redefinicion del metodo hashCode de la clase Object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	/**
	 * Redefinicion del metodo equals de la clase Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Correo other = (Correo) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	/**
	 * Redefinicion del metodo clone de la clase Object usando el constructor copia
	 */
	@Override
	public Correo clone() {
		return new Correo(this);
	}

	/**
	 * Redefinicion del metodo toString de la clase Object que muestra el texto de
	 * nuestro correo
	 */
	@Override
	public String toString() {
		return texto;
	}

}
