package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

public class SQLiteUtil {

	// conector de SQLite
	private static final String CONECTOR_SQLITE = "org.sqlite.JDBC";
	
	// fichero de la base de datos "gaming"
	private static final String FICHERO_BD = "data/libreria.db";
	
	// URL de conexión con la base de datos SQLite "gaming"
	private static final String URL_SQLITE_BD = "jdbc:sqlite:" + FICHERO_BD;
	
	/**
	 * Abre una conexión con la base de datos relacional SQLite "gaming".
	 * Devuelve la conexión creada.
	 */
	public static Connection abrirConexion() 
	throws ClassNotFoundException, SQLException {
		Class.forName(CONECTOR_SQLITE);
		SQLiteConfig config = new SQLiteConfig();  
        config.enforceForeignKeys(true);
        Connection conexion = DriverManager.getConnection(URL_SQLITE_BD, config.toProperties());
        return conexion;
	}
	
	/**
	 * Cierra una conexión con la base de datos relacional SQLite "gaming".
	 */
	public static void cerrarConexion(Connection conexion) 
	throws SQLException {
		if (conexion != null && !conexion.isClosed()) {
			conexion.close();
		}
	}
	
}
