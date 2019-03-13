package modelo;
/** 
 * Proyecto: Juego de la vida.
 *  Prueba Junit5 del paquete modelo según el modelo1.
 *  @since: prototipo1.0
 *  @source: AllTest.java 
 *  @version: 1.0 - 2018/11/14
 *  @author: ajp
 */


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	UsuarioTest.class,
	SesionUsuarioTest.class,
	SimulacionTest.class

})

public class AllTests {

}
