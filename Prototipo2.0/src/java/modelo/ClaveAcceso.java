/** Proyecto: Juego de la vida.
 *  Implementa el concepto de ClaveAcceso del modelo  
 *  @since: prototipo1.2
 *  @source: ClaveAcceso.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package modelo;

public class ClaveAcceso {
	/**
	 * Cadena de caracteres que va a formar el texto de la clave de acceso.
	 */
	private String texto;

	/**
	 * Constructor convencional de la clase.
	 * @param texto - Cadena de caracteres que formará la clave de acceso
	 * @throws ModeloException 
	 */
	public ClaveAcceso(String texto) throws ModeloException {
			setTexto(texto);
	}

	/**
	 * Constructor por defecto de la clase que establece una contraseña por defecto.
	 * @throws ModeloException 
	 */
	public ClaveAcceso() throws ModeloException {
		setTexto("Miau#0");
	}

	/**
	 * Constructor copia de la clase
	 * @param claveacceso - Clave de acceso del usuario que quiere entrar en sesión
	 */
	public ClaveAcceso(ClaveAcceso claveacceso) {
		this.texto = new String(claveacceso.texto);
	}

	/**
	 * Metodo get que obtiene el texto que forma la clave de acceso.
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Metodo set que establece el texto de nuestra contraseña encriptandola.
	 * @param texto - Cadena de caracteres que formará la clave de acceso
	 * @throws ModeloException 
	 */
	public void setTexto(String texto) throws ModeloException {
		assert texto != null;
		
		if (claveAccesoValida(texto)) {
			this.texto = encriptarCesar(texto);

		} 
		 else {
			throw new ModeloException("ClaveAcceso: no válida");
			
		}
	}

	/**
	 * Metodo que encripta la clave de acceso de usuario adquirida por teclado
	 * @param texto - Cadena de caracteres que formará la clave de acceso
	 * @return Clave de acceso encriptada
	 */
	private static String encriptarCesar(String texto) {
		StringBuilder result = new StringBuilder();
		int desplazamiento = 400;
		
		for (int i = 0; i < texto.length(); i++) {
			result.append((char)(texto.charAt(i) + desplazamiento));
		}
		return result.toString();
	}
	
	/**
	 * Metodo que comprueba si una clave de acceso es valida o no.
	 * @param texto - Cadena de caracteres que formará la clave de acceso
	 * @return true, si la clave de acceso es valida.
	 */
	private boolean claveAccesoValida(String texto) {
		return texto.matches(".{4,32}");

	}

	/**
	 * Redefinicion del metodo toString de la clase Object que forma un String con los atributos de la clase con un formato.
	 */
	@Override
	public String toString() {
		return texto;
	}

	/**
	 * Redefinicion del metodo hashCode de la clase Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		ClaveAcceso other = (ClaveAcceso) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	/**
	 * Redefinicion del metodo clone de la clase Object que utiliza el constructor copia de la clase.
	 */
	@Override
	public ClaveAcceso clone() {
		return new ClaveAcceso(this);

	}

}	// Class
