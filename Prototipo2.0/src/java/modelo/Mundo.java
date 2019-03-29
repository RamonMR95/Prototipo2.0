package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Nif del modelo  
 *  @since: prototipo1.2
 *  @source: Nif.java 
 *  @version: 1.2 - 2019/01/22 
 *  @author: Ramon Moñino
 */

public class Mundo implements Serializable{
	/**
	 * Atributos de la clase Mundo 
	 */
	private String nombre;
	private static final int TAMAÑO_MUNDO = 18;
	private byte[][] espacio = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];
	private List<Posicion> distribucion;
	private HashMap<String, int[]> constantes;
	
	enum FormaEspacio { PLANO, ESFERICO }
	private static final FormaEspacio TIPO_MUNDO = FormaEspacio.PLANO;

	/**
	 * Constructor convencional de la clase Mundo.
	 * @param nombre - Nombre de nuestro mundo.
	 * @param espacio - Array que define nuestro mundo.
	 * @param distribucion - Lista enlazada que contiene las células vivas
	 * @param constantes - Mapa de constantes disponibles para configurar las leyes de nuestro mundo.
	 */
	public Mundo(String nombre, byte [][] espacio,  List<Posicion> distribucion, HashMap<String, int[]> constantes) {
		this.espacio = espacio;
		this.nombre = nombre;
		this.distribucion = distribucion;
		this.constantes = constantes;
		
	}
	
	/**
	 * Constructor por defecto de la clase Mundo.
	 */
	public Mundo() {
		this("Demo1", new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO], new LinkedList<Posicion>(), new HashMap<String, int[]>());
		aplicarLeyes();
	}
	
	/**
	 * Contructor copia de la clase mundo
	 * @param mundo - Mundo que va a ser copiado
	 */
	public Mundo(Mundo mundo) {
		this.nombre = new String(mundo.nombre);
		this.espacio = mundo.espacio.clone();
		this.distribucion = new LinkedList<Posicion>(mundo.distribucion);
		this.constantes = new HashMap<String, int[]>(mundo.constantes);
	}
	
	/**
	 * Metodo get que obtiene el valor del tamaño del mundo.
	 * @return TAMAÑO_MUNDO - tamaño de nuestro mundo.
	 */
	public int getTamañoMundo() {
		return TAMAÑO_MUNDO;
	}
	
	/**
	 * Metodo get que obtiene el nombre del mundo.
	 * @return nombre es el nombre de nuestro mundo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo set que establece el 
	 * array 2d que forma nuestro Mundo.
	 * @param espacio - array 2d que forma nuestro mundo.
	 */
	public void setEspacio(byte[][] espacio) {
		assert espacio != null;
		this.espacio = espacio;
	}
	
	/**
	 * Metodo get que obtiene el array 2d que define nuestro mundo.
	 * @return espacio array que define el mundo.
	 */
	public byte[][] getEspacio() {
		return espacio;
	}

	/**
	 * Metodo get que obtiene la lista que contiene los celulas vivas del mundo.
	 * @return distribucion - lista que contiene las celulas vivas
	 */
	public List<Posicion> getDistribucion() {
		return distribucion;
	}

	/**
	 * Metodo get que obtiene el mapa que contiene las leyes de nuestro mundo
	 * @return contantes - mapa que contiene las leyes del mundo
	 */
	public HashMap<String, int[]> getConstantes() {
		return constantes;
	}

	/**
	 * Metodo que obtiene el tipo de mundo esférico / plano
	 * @return TIPO_MUNDO - forma del mundo
	 */
	public static FormaEspacio getTipoMundo() {
		return TIPO_MUNDO;
	}

	public String getId() {
		return nombre;
	}

	/**
	 * Metodo que inserta nuestras leyes estandar en el mapa constantes
	 */
	private void aplicarLeyes() {
		constantes.put("ValoresSobrevivir", new int[] { 2, 3 });
		constantes.put("ValoresRenacer", new int[] { 3 });
	}
	
	
	/**
	 * Actualiza el estado del Juego de la Vida. Actualiza según la configuración
	 * establecida para la forma del espacio.
	 */
	public void actualizarMundo() {
		if (TIPO_MUNDO == FormaEspacio.PLANO) {
			actualizarMundoPlano();
		}
		if (TIPO_MUNDO == FormaEspacio.ESFERICO) {
			actualizarMundoEsferico();
		}
	}

	/**
	 * Actualiza el estado almacenado del Juego de la Vida. Las celdas periféricas
	 * son adyacentes con las del lado contrario. El mundo representado sería
	 * esférico cerrado sin límites para células de dos dimensiones.
	 */
	private void actualizarMundoEsferico() {
		// Pendiente de implementar.
	}

	/**
	 * Actualiza el estado de cada celda almacenada del Juego de la Vida. Las celdas
	 * periféricas son los límites absolutos. El mundo representado sería plano,
	 * cerrado y con límites para células de dos dimensiones.
	 */
	private void actualizarMundoPlano() {
		byte[][] nuevoEstado = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];

		for (int i = 0; i < TAMAÑO_MUNDO; i++) {
			for (int j = 0; j < TAMAÑO_MUNDO; j++) {
				int vecinas = 0;
				vecinas += visitarCeldaNoroeste(i, j);
				vecinas += visitarCeldaNorte(i, j);
				vecinas += visitarCeldaNoreste(i, j);
				vecinas += visitarCeldaEste(i, j);
				vecinas += visitarCeldaSureste(i, j);
				vecinas += visitarCeldaSur(i, j);
				vecinas += visitarCeldaSuroeste(i, j);
				vecinas += visitarCeldaOeste(i, j);

				actualizarCelda(nuevoEstado, i, j, vecinas);
			}
		}
		espacio = nuevoEstado;
	}

	/**
	 * Aplica las leyes del mundo a la celda indicada dada la cantidad de células
	 * adyacentes vivas.
	 * @param nuevoEstado
	 * @param fila
	 * @param col
	 * @param vecinas
	 */
	private void actualizarCelda(byte[][] nuevoEstado, int fila, int col, int vecinas) {

		for (int valor : constantes.get("ValoresRenacer")) {
			if (vecinas == valor) { // Pasa a estar viva.
				nuevoEstado[fila][col] = 1;
				return;
			}
		}


			for (int valor : constantes.get("ValoresSobrevivir")) {
				if (vecinas == valor && espacio[fila][col] == 1) { // Permanece viva, si lo estaba.
					nuevoEstado[fila][col] = 1;
					return;
				}
			}
		}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Oeste de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Oeste.
	 */
	private byte visitarCeldaOeste(int fila, int col) {
		if (col - 1 >= 0) {
			return espacio[fila][col - 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Suroeste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Suroeste.
	 */
	private byte visitarCeldaSuroeste(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO && col - 1 >= 0) {
			return espacio[fila + 1][col - 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sur de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Sur.
	 */
	private byte visitarCeldaSur(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO) {
			return espacio[fila + 1][col];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sureste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Sureste.
	 */
	private byte visitarCeldaSureste(int fila, int col) {
		if (fila + 1 < TAMAÑO_MUNDO && col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila + 1][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Este de la indicada
	 * por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Este.
	 */
	private byte visitarCeldaEste(int fila, int col) {
		if (col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noreste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Noreste.
	 */
	private byte visitarCeldaNoreste(int fila, int col) {
		if (fila - 1 >= 0 && col + 1 < TAMAÑO_MUNDO) {
			return espacio[fila - 1][col + 1];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al NO de la indicada por
	 * la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda NO.
	 */
	private byte visitarCeldaNorte(int fila, int col) {
		if (fila - 1 >= 0) {
			return espacio[fila - 1][col];
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noroeste de la
	 * indicada por la coordenada.
	 * @param fila de la celda evaluada.
	 * @param col  de la celda evaluada.
	 * @return el estado o valor de la celda Noroeste.
	 */
	private byte visitarCeldaNoroeste(int fila, int col) {
		if (fila - 1 >= 0 && col - 1 >= 0) {
			return espacio[fila - 1][col - 1];
		}
		return 0;
	}

	public String toStringEstadoMundo() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < espacio.length; i++) {
			for (int j = 0; j < espacio.length; j++) {
				if (espacio[i][j] == 1) {
					sb.append("|o");
				} 
				else {
					sb.append("| ");
				}
			}
			sb.append("|\n");
		}
		return sb.toString();
	}
	
}
