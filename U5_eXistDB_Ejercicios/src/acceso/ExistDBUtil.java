package acceso;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class ExistDBUtil {

	private static final String CONECTOR_EXIST = "org.exist.xmldb.DatabaseImpl";
	private static final String RUTA_BD = "/db/tienda";
	private static final String URL_EXIST_BD = "xmldb:exist://localhost:8081/exist/xmlrpc" + RUTA_BD;
	private static final String USUARIO = "admin";
	private static final String CONTRASENIA = "admin";

	public static Collection abrirColeccion()
			throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		Class clase = Class.forName(CONECTOR_EXIST);
		Database baseDatos = (Database) clase.newInstance();
		DatabaseManager.registerDatabase(baseDatos);
		Collection coleccion = DatabaseManager.getCollection(URL_EXIST_BD, USUARIO, CONTRASENIA);
		return coleccion;
	}

	public static void cerrarColeccion(Collection coleccion)
			throws XMLDBException {
		if (coleccion != null && coleccion.isOpen()) {
			coleccion.close();
		}
	}

}
