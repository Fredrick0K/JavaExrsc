package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {

	// conector de MySQL
	private static final String CONECTOR_MYSQL = "com.mysql.cj.jdbc.Driver";

	// ubicación de la base de datos discografica
	private static final String UBICACION_BD = "//localhost:3306/banco";
	// URL de conexión con la base de datos MySQL discografica
	private static final String URL_MYSQL_BD = "jdbc:mysql:" + UBICACION_BD;

	// usuario y contraseña
	private static final String USUARIO = "root";
	private static final String CONTRASENIA = "root";

	/**
	 * Abre una conexión con la base de datos relacional MySQL discografica.
	 * Devuelve la conexión creada.
	 */
	public static Connection abrirConexion() throws ClassNotFoundException, SQLException {
		Class.forName(CONECTOR_MYSQL);
		Connection conexion = DriverManager.getConnection(URL_MYSQL_BD, USUARIO, CONTRASENIA);
		return conexion;
	}

	/**
	 * Cierra una conexión con la base de datos MySQL.
	 */
	public static void cerrarConexion(Connection conexion) throws SQLException {
		if (conexion != null && !conexion.isClosed()) {
			conexion.close();
		}
	}

}
