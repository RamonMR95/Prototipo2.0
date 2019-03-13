# Prototipo2.0 :computer:

## 1. Revisión de los [Documentos de Proyecto](https://moodle.iescierva.net/mod/folder/view.php?id=26072) para conocer las características establecidas para el proyecto "JVida" en su cuarta iteración
- Corresponde al cuarto prototipo en el que se va a abordar una nueva implementación de la capa de acceso a datos con una clase fachada y varias clases <b>DAO</b> para implementar una primera versión con persistencia basadas en ficheros.
- El control de acceso del programa y la ejecución de una simulación de demostración del Juego de la Vida no cambiarán esencialmente.
- Estarán afectados  los casos de uso "Controlar Acceso" y "Gestionar Simulación". 

## 2. Preparación del entorno de trabajo

- Importa, si es necesario como se hizo en la práctica anterior,
- Sincroniza el repositorio maestro local del proyecto JV2018 desde el repositorio remoto del proyecto: https://github.com/PyED2018/Proyecto.git
- A partir de la replica maestra local, copia el proyecto con el explorador de paquetes de eclipse.
- Asigna un nombre de trabajo adecuado para el prototipo2.0

## 3. Creación de la clase <b>Persona</b> y modificación de <b>Usuario</b> según se especifican en el modelo 2

- <b>Identificar cada la nueva clase y enumerados</b> que aparecen en el diagrama del modelo2.0. Gestionar Acceso. Ver: [Documentos de Proyecto](https://moodle.iescierva.net/mod/folder/view.php?id=26072)
- <b>Crear la clase Persona</b> en el paquete modelo.
- <b>Hacer efectiva la relación de especialización</b> (herencia) entre la nueva clase Persona y la clase Usuario. En la nueva clase:

  - <b>Definir, los métodos de acceso</b>.
  - <b>Definir un constructor convencional</b> que reciba argumentos para el valor inicial de cada atributo teniendo en cuenta la herencia.
  - <b>Redefinir</b> adecuadamente los métodos toString(), equals(), ... de la Usuario teniendo en cuenta la herencia.

## 4. Creación de la clase <b>Configuracion</b> y modificación de clases del proyecto
- Crear el paquete <b>.config</b>
- Crear la clase Configuracion en el paquete .config.
- La clase Configuración utiliza un objeto java.util.<b>Properties</b> para gestionar todos los parámetros configurables de una aplicación típica:
    - Cuentas de usuario y contraseñas predeterminadas.
    - Nombres de ficheros de datos.
- La clase <b>Configuracion</b> debe tener asociado un fichero de texto plano con todos los parámetros de configuración.

## 5. Implementación de clases DAO para resolver el almacenamiento en ArrayList

- Crear el subpaquete <b>.accesoDatos.memoria</b>
- Crear las clases <b>DAO</b>
- Las clases especializadas en el acceso a datos DAO (Data Access Object) deben organizarse en un subpaquete específico; además, los DAO deben ser Singleton:
  - <b>UsuariosDAO.</b> Resuelve el acceso a los datos de los usuarios incluyendo los mecanismos necesarios para almacenarlos en un <b>ArrayList.</b>
  - <b>SesionesDAO.</b> Resuelve el acceso a los datos de las sesiones de usuarios incluyendo los mecanismos necesarios para almacenarlas en un <b>ArrayList.</b>
  - <b>SimulacionesDAO.</b> Resuelve el acceso a los datos de las simulaciones del juego de la vida realizadas por los usuarios incluyendo los mecanismos necesarios para almacenarlas en un <b>ArrayList.</b>
  - <b>MundosDAO.</b> Resuelve el acceso a los datos de los mundos utilizados en las simulaciones, incluyendo los mecanismos necesarios para almacenarlos en un <b>ArrayList.</b>
  
- En el subpaquete <b>test.accesoDatos</b> hay que crear una clase llamada <b>DatosTest</b> donde se ubicarán todos los métodos relacionados con la preparación y configuración de los datos necesarios para las pruebas durante el desarrollo del proyecto en su prototipo2. Varios métodos de estos ya existían desde el prototipo1 en la clase Datos, pero ahora quedan mejor organizados
- Cada clase <b>DAO</b> deberán implementar la <b>interfaceOperacionesDAO</b> que especifica las operaciones básicas del DAO con los siguientes métodos: 
 - Método <b>obtener(String id)</b> recibe un argumento que representa el id por el que se ordenan los objetos de un mismo DAO. Devuelve el objeto buscado o <b>null</b> si no lo encuentra.
 - Método <b>obtenerTodos()</b> Devuelve en un objeto List con todos los el objeto del mismo tipo asociados al DAO.
 - Método <b>alta(Object obj)</b> recibe un argumento que representa el nuevo objeto a almacenar. Lanza DatosException si ya existe.
 - Método <b>baja(String id)</b> recibe un argumento que representa el id por el que se identifica el objeto que se quiere borrar. Devuelve el objeto borrado. Lanza DatosException si no existe.
 - Método <b>actualizar(Object obj)</b> recibe un argumento que representa el objeto con las modificaciones a actualizar. Lanza DatosException si no existe.
 - Método <b>listarDatos()</b> para poder mostrar todos los almacenados en el sistema asociados al mismo DAO. Devuelve el texto con los datos.
 - Método <b>listarId()</b> para poder mostrar todos los identificadores de los objetos almacenados en el sistema asociados al mismo DAO. Devuelve el texto con los datos.

Hay que tener en cuenta que:

- Los DAO que ya dispongan de métodos equivalentes deben adaptar su nombre a nuevo establecido por la interface.
- Los DAO que no tengan los nuevos métodos, deben proporcionar una implementación completa y operativa. 

## 6. Modificación de la clase <b>Datos</b> para convertirla en la fachada de datos.

- Ubicar las clases implicadas en el paquete <b>.accesoDatos</b>
- La clase principal del paquete <b>.accesoDatos</b> es la clase <b>Datos</b>. Esta clase se refactorizará y desglosará, como se indica más adelante, para tener una mejor organización de la capa de datos de la aplicación.
- La Clase Datos implementará los siguientes métodos básicos de fachada llamando la los correspondientes en las clases DAO:

  - Método <b>obtenerUsuario()</b> que recibe un argumento que representa el id del usuario. Devuelve el objeto encontrado o null si no existe.
  - Método <b>obtenerTodosUsuarios()</b> que devuelve una lista (List) con los objetos encontrados.
  - Método <b>altaUsuario()</b> recibe un argumento que representa el nuevo usuario. El método DAO lanza una excepción <b>DatosException</b> si ya existe.
  - Método <b>bajaUsuario()</b> recibe un argumento que representa el id del usuario que se quiere borrar. El método DAO lanza una excepción DatosException si el usuario no existe.
  - Método <b>actualizarUsuario()</b> recibe un argumento que representa el usuario con las modificaciones. El método DAO lanza una excepción DatosException si el usuario recibido no existe o no concuerda su id.
  - Método <b>toStringDatosUsuarios()</b> que obtiene, en forma de texto, los datos de todos los usuarios almacenados en el sistema.
  - Método <b>toStringIdUsuarios()</b> que obtiene, en forma de texto, los identificadores de todos los usuarios almacenados en el sistema.
  - Método <b>borrarTodosUsuarios()</b> que quita todos los usuarios almacenados en el sistema.

  - Método <b>obtenerSesiones()</b> recibe un argumento que representa el idSesion de la sesión. Devuelve el objeto encontrado o null si no existe.
  - Método <b>obtenerTodasSesiones()</b> que devuelve una List con los objetos encontrados.
  - Método <b>altaSesion()</b> recibe un argumento que representa la nueva sesión. El método DAO lanza una excepción DatosException si ya existe.
  - Método <b>bajaSesion()</b> recibe un argumento que representa el idSesion de la sesión que se quiere borrar. El método DAO lanza una excepción DatosException si la sesión no existe.
  - Método <b>actualizarSesion()</b> recibe un argumento que representa la sesión con las modificaciones. El método DAO lanza una excepción DatosException si la sesión recibida no existe o no concuerda su id.
  - Método <b>toStringDatosSesiones()</b> que obtiene, en forma de texto, los datos de todos las sesiones de usuario almacenadas en el sistema.
  - Método <b>toStringIdSesiones()</b> que obtiene, en forma de texto, los identificadores de todas las sesiones almacenadas en el sistema.
  - Método <b>borrarTodosUsuarios()</b> que quita todos las sesiones de usuario almacenadas en el sistema.

  - Método <b>obtenerSimulacion()</b>  recibe un argumento que representa el id del usuario y la fecha. Devuelve el objeto encontrado o null si no existe.
  - Método <b>obtenerTodasSimulaciones()</b>  que devuelve una List con los objetos encontrados.
  - Método <b>altaSimulacion()</b>  recibe un argumento que representa la nueva simulación. El método DAO lanza una excepción DatosException si ya existe.
  - Método <b>bajaSimulacion()</b>  recibe un argumento que representa el id del usuario y la fecha de la simulación que se quiere borrar. El método DAO lanza una excepción DatosException si simulación no existe.
  - Método <b>actualizarSimulacion()</b>  recibe un objeto Simulacion con las modificaciones. El método DAO lanza una excepción DatosException si la simulación recibida no existe o no concuerda su id.
  - Método <b>toStringDatosSimulaciones()</b>  que obtiene, en forma de texto, los datos de todas las simulaciones almacenadas en el sistema.
  - Método <b>toStringIdUsuarios()</b>  que obtiene, en forma de texto, los identificadores de todas las simulaciones almacenadas en el sistema.
  - Método <b>borrarTodasSimulaciones()</b>  que quita todas las simulaciones almacenadas en el sistema.

  - Método <b>obtenerMundo()</b>  recibe un argumento que representa el id de un mundo. Devuelve el objeto encontrado o null si no existe.
  - Método <b>obtenerTodosMundos()</b>  que devuelve una List con los objetos encontrados.
  - Método <b>altaMundo()</b>  recibe un argumento que representa el nuevo mundo. El método DAO lanza una excepción DatosException si ya existe.
  - Método <b>bajaMundo()</b>  recibe un argumento que representa el id del mundo que se quiere borrar. El método DAO lanza una excepción DatosException si el mundo no existe.
  - Método <b>actualizarMundo()</b>  recibe un objeto Mundo con las modificaciones. El método DAO lanza una excepción DatosException si el mundo recibido no existe o no concuerda su id.
  - Método <b>toStringDatosMundos()</b>  que obtiene, en forma de texto, los datos de todos los mundos almacenados en el sistema.
  - Método <b>toStringIdMundos()</b>  que obtiene, en forma de texto, los identificadores de todos los mundos almacenados en el sistema.  - 
  - Método <b>borrarTodosMundos()</b>  que quita todos los mundos almacenados en el sistema.

## 7. Implementación de nuevas clases DAO para proporcionar persistencia básica en ficheros.

- Crear una copia completa del subpaquete .accesoDatos.memoria y renombrarlo como .accesoDatos.fichero. En cada una de las clases xxxDAO se implementarán métodos adicionales para poder hacer persistente el ArrayList que corresponda:
  - <b>guardarDatos()</b> que se encargará de serializar los bits de cada elemento del ArrayList de datos para enviarlo a través de un flujo (stream) de salida a un fichero.
  - <b>recuperarDatos()</b> que se encargará de deserializar (recomponer) de nuevo cada objeto a partir de los bits recibidos desde un flujo (stream) de entrada procedente del fichero de datos.
  - <b>cerrar()</b> que se encarga de cerrar los ficheros asociados.
 - Adicionalmente, para la persistencia se deben serializar los objetos implicados en los DAO; osea los correspondientes DTO (Data Transfer  Object) del modelo. Para la serialización hay que tener en cuenta los siguiente requisitos:

  - Las clases de los objetos a serializar deben implementar la <b>interface Serializable</b>.
  - Los atributos no primitivos también deben ser serializables; y por tanto, sus clases, también deben implementar el interface Serializable.
  - En cada una de las clases del modelo se debe implementar la <b>interface Serializable</b>.

## 8. Pruebas básicas de las clases del modelo2

Hay que completar los siguientes detalles:

- Crear un nuevo sub-paquete del proyecto que se llame <b>test.modelo</b>, donde se ubicarán una clase de prueba asociada a cada una de las clases del modelo según el formato de nombre <b>xxxxxxTest</b>   (xxxxxx es el nombre de la clase probada). 
- Dentro de cada clase <b>xxxxxxTest</b>,preparar métodos especializados para las pruebas de toda la funcionalidad de la correspondiente clase  utilizando JUnit5.:
    - <b>testSet...()</b>
    - <b>testGet...()</b>
    - <b>test...Constructores()</b>
    - <b>testToString()</b>
    - <b>test...()</b>
    
- Dentro del sub-paquete <b>modelo.test</b>, crear las clases ...test para cada clase del <b>.modelo</b>, de <b>.accesoDatos</b>, <b>.accesoUsr</b> y <b>.util</b>.

## UML
![alt_text](https://github.com/RamonMR95/Prototipo2.0/blob/master/img/modelo2.0-Dise%C3%B1oClases%20(ControlarAcceso)(Simulacion).jpg)
