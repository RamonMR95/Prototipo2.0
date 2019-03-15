package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Proyecto: Juego de la vida.
 *  Gestión de la configuracion del proyecto mediante el uso de java properties 
 *  @since: prototipo2.0
 *  @source: ConfiguracionSingletonCanonico.java 
 *  @version: 1.0 - 2019/03/14 
 *  @author: Ramon Moñino
 */

public class ConfiguracionSingletonCanonico {

	private static ConfiguracionSingletonCanonico configuracion = null;
	private static Properties propiedades2;

	private ConfiguracionSingletonCanonico() {
		propiedades2 = new Properties();
		FileInputStream is = null;

		try {
			is = new FileInputStream("config.cfg");
			propiedades2.load(is);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ConfiguracionSingletonCanonico get() {
		if (configuracion == null) {
			configuracion = new ConfiguracionSingletonCanonico();

		}
		return configuracion;
	}

	public int getEdadMinima() {
		return Integer.parseInt(propiedades2.getProperty("usuario.EdadMinima"));
	}

	public Properties getPropiedades() {
		return propiedades2;
	}
}
