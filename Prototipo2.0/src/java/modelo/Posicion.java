package modelo;

import java.io.Serializable;

public class Posicion implements Serializable{
	private int x;
	private int y;

	public Posicion(int x, int y) {
		setX(x);
		setY(y);
	}

	public Posicion() {
		this(0, 0);
	}

	public Posicion(Posicion posicion) {
		this.x = posicion.x;
		this.y = posicion.y;
	}
 
	public void setX(int coordenadaX) {
		assert x > 0;
		this.x = coordenadaX;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int coordenadaY) {
		assert y > 0;
		this.y = coordenadaY;
	}

	public int getY() {
		return this.y;
	}
}
