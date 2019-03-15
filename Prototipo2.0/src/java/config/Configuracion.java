package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Proyecto: Juego de la vida.
 *  Gestión de la configuracion del proyecto mediante el uso de java properties 
 *  @since: prototipo2.0
 *  @source: Configuracion.java 
 *  @version: 1.0 - 2019/03/14 
 *  @author: Ramon Moñino
 */

public class Configuracion {

	private static Properties propiedades;

	private Configuracion() {
		propiedades = new Properties();
		FileInputStream is = null;
	

		try {
			is = new FileInputStream("config.cfg");
			propiedades.load(is);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Properties get() {
		if (propiedades == null) {
			new Configuracion();

		}
		return propiedades;
	}
}
