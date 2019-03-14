package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/** Proyecto: Juego de la vida.
 *  Gestión de la configuracion del proyecto mediante el uso de java properties 
 *  @since: prototipo2.0
 *  @source: Configuracion.java 
 *  @version: 1.0 - 2019/03/14 
 *  @author: Ramon Moñino
 */

public class Configuracion {

	private static Configuracion configuracion = null;
	private Properties properties;

	private Configuracion() {
		properties = new Properties();
		FileInputStream is = null;

		try {
			is = new FileInputStream("Config.cfg");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Configuracion getSingleton() {
		if (configuracion == null) {
			new Configuracion();

		}
		return configuracion;
	}
	
	public Properties getPropiedades() {
		return properties;
	}
}
