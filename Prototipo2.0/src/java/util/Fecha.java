/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Fecha del modelo  
 *  @since: prototipo1.1
 *  @source: Fecha.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: Ramon Moñino
 */
package util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fecha {
	/**
	 * Atributo de tipo calendar que vamos a utilizar para definir objetos de tipo Fecha
	 */
	private Calendar tiempo;

	/**
	 * Constructor por defecto de la clase Fecha, que crea un nuevo objeto GregorianCalendar
	 */
	public Fecha() {
		this.tiempo = new GregorianCalendar();
	}

	/**
	 * Constructor convencional de la clase Fecha
	 * @param year 
	 * @param month
	 * @param day
	 */
	public Fecha(int year, int month, int day) {
		this.tiempo = new GregorianCalendar(year, month - 1, day);
	}

	/**
	 * Constructor ampliado de la clase Fecha
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public Fecha(int year, int month, int day, int hour, int minute, int second) {
		this.tiempo = new GregorianCalendar(year, month - 1, day, hour, minute, second);
	}

	/**
	 * Constructor de copia de la clase Fecha que usa el metodo clone.
	 * @param fecha
	 */
	public Fecha(Fecha fecha) {
		this.tiempo = (Calendar) fecha.tiempo.clone();
	}

	/**
	 * Metodo get que obtiene el año de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return year
	 */
	public int getYear() {
		return tiempo.get(Calendar.YEAR);
	}

	/**
	 * Metodo set que establece el año de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param year
	 */
	public void setYear(int year) {
		tiempo.set(Calendar.YEAR, year);
	}

	/**
	 * Metodo get que obtiene el mes de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return month
	 */
	public int getMonth() {
		return tiempo.get(Calendar.MONTH) + 1;
	}

	/**
	 * Metodo set que establece el mes de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param month
	 */
	public void setMonth(int month) {
		tiempo.set(Calendar.MONTH, month);
	}

	/**
	 * Metodo get que obtiene el dia de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return day
	 */
	public int getDay() {
		return tiempo.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Metodo set que establece el dia de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param day
	 */
	public void setDay(int day) {
		tiempo.set(Calendar.DAY_OF_MONTH, day);
	}

	/**
	 * Metodo get que obtiene los segundos de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return secs
	 */
	public int getSec() {
		return tiempo.get(Calendar.SECOND);
	}

	/**
	 * Metodo set que establece los segundos de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param second
	 */
	public void setSecs(int second) {
		tiempo.set(Calendar.SECOND, second);
	}

	/**
	 * Metodo get que obtiene los minutos de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return minute
	 */
	public int getMin() {
		return tiempo.get(Calendar.MINUTE);
	}

	/**
	 * Metodo set que establece los minutos de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param minute
	 */
	public void setMin(int minute) {
		this.tiempo.set(Calendar.MINUTE, minute);
	}

	/**
	 * Metodo get que obtiene las horas de nuestro atributo tiempo de tipo GregorianCalendar
	 * @return hour
	 */
	public int getHour() {
		return tiempo.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Metodo set que establece la hora de nuestro atributo tiempo de tipo GregorianCalendar
	 * @param hour
	 */
	public void setHour(int hour) {
		this.tiempo.set(Calendar.HOUR_OF_DAY, hour);
	}

	/**
	 * Metodo que calcula la diferencia de segundos entre una fecha y otra fecha dada por parametro
	 * @param fecha
	 * @return diferenciaDeSegundos
	 */
	public long difSegundos(Fecha fecha) {
		return ((this.tiempo.getTimeInMillis() - fecha.tiempo.getTimeInMillis()) / 1000);
	}

	/**
	 * Metodo que calcula la diferencia de horas entre una fecha y otra fecha dada por parametro
	 * @param fecha
	 * @return diferenciaDeHoras
	 */
	public long difHoras(Fecha fecha) {
		return ((this.tiempo.getTimeInMillis() - fecha.tiempo.getTimeInMillis()) / (60 * 60 * 1000));
	}

	/**
	 * Metodo que calcula la diferencia de dias entre una fecha y otra fecha dada por parametro
	 * @param fecha
	 * @return diferenciaDeDias
	 */
	public long difDias(Fecha fecha) {
		return ((this.tiempo.getTimeInMillis() - fecha.tiempo.getTimeInMillis()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * Metodo que añade una cantidad de segundos dada por parametro
	 * @param segundos
	 * @return Fecha
	 */
	public Fecha addSegundos(int segundos) {
		this.tiempo.add(Calendar.SECOND, segundos);
		return this;
	}

	/**
	 * Metodo que añade una cantidad de minutos dada por parametro
	 * @param minutos
	 * @return Fecha
	 */
	public Fecha addMinutos(int minutos) {
		this.tiempo.add(Calendar.MINUTE, minutos);
		return this;
	}

	/**
	 * Metodo que añade una cantidad de horas dada por parametro
	 * @param horas
	 * @return Fecha
	 */
	public Fecha addHoras(int horas) {
		this.tiempo.add(Calendar.HOUR_OF_DAY, horas);
		return this;
	}

	/**
	 * Metodo que añade una cantidad de dias dada por parametro
	 * @param dias
	 * @return Fecha
	 */
	public Fecha addDias(int dias) {
		this.tiempo.add(Calendar.DAY_OF_MONTH, dias);
		return this;
	}

	/**
	 * Metodo que añade una cantidad de meses dada por parametro
	 * @param meses
	 * @return Fecha
	 */
	public Fecha addMeses(int meses) {
		this.tiempo.add(Calendar.MONTH, meses);
		return this;
	}

	/**
	 * Metodo que añade una cantidad de años dada por parametro
	 * @param años
	 * @return Fecha
	 */
	public Fecha addAños(int años) {
		this.tiempo.add(Calendar.YEAR, años);
		return this; 	//resultado se devuelve asi mismo
	}

	/**
	 * Metodo conversor de Fecha a GregorianCalendar
	 * @return GregorianCalendar
	 */
	public GregorianCalendar toGregorianCalendar() {
		return (GregorianCalendar) tiempo;
	}

	/**
	 * Metodo conversor de Fecha a Date
	 * @return Date
	 */
	public Date toDate() {
		return tiempo.getTime();
	}

	/**
	 * Metodo que genera una marca de tiempo con precision de milisegundos
	 * @return marcaDeTiempo
	 */
	public long getMarcaTiempoMilisegundos() {
		return tiempo.getTimeInMillis();
	}

	/**
	 * Metodo toString que da un formato a la marca de tiempo
	 * @return stringMarcaDeTiempo
	 */
	public String toStringMarcaTiempo() {
		return String.format("%4%02%02%02%02%02", getYear(), getMonth(), getDay(), getHour(), getMin(), getSec());
	}

	/**
	 * Metodo que compara si una fecha es mas reciente que otra dada por parametro o viceversa
	 * @param fecha
	 * @return Entero
	 */
	public int compareTo(Fecha fecha) {
		return tiempo.compareTo(fecha.tiempo);
	}
	
	/**
	 * Metodo que comprueba si una fecha es más reciente que otra dada por parametro
	 * @param fecha
	 * @return boolean
	 */
	public boolean before(Fecha fecha) {
		return this.tiempo.before(fecha.tiempo);
	}
	
	/**
	 * Metodo que comprueba si una fecha es más antigua que otra dada por parametro
	 * @param fecha
	 * @return boolean
	 */
	public boolean after(Fecha fecha) {
		return this.tiempo.after(fecha.tiempo);
	}

	/**
	 * Implementacion del metodo hashCode de la clase
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tiempo == null) ? 0 : tiempo.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si. Son de la misma clase. Tienen los mismos valores
	 * en los atributos o son el mismo objeto.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (this.tiempo.getTimeInMillis() == ((Fecha) obj).tiempo.getTimeInMillis()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Implementacion del metodo toString para la clase Fecha
	 * @return toString
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		sb.append(String.valueOf(getYear()));
		sb.append(", ");
		sb.append(String.valueOf(getMonth()));
		sb.append(", ");
		sb.append(String.valueOf(getDay()));
		sb.append(" )");
		return sb.toString();
	}

	/**
	 * Metodo Clone de la clase
	 */
	@Override
	public Fecha clone() {
		return new Fecha(this);
	}
} // Class
