package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import modelo.Escritor;
import modelo.Libro;

public class AccesoLibro {
	
	public static Libro consultarLibroByCode(int codigoLibro) throws ClassNotFoundException, SQLException{
		Connection conexion = null;
//		List<Libro> listaLibros = null;
		Libro libro = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT * FROM libro WHERE codigo = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			sentenciaPreparada.setInt(1, codigoLibro);
			ResultSet rs = sentenciaPreparada.executeQuery();
			if(rs.next()) {
				int codigoEscritor = rs.getInt("codigo_escritor");
				String titulo = rs.getString("titulo");
				int anyoPublicacion = rs.getInt("anio_publicacion");
				int extension = rs.getInt("extension");
				double precio = rs.getDouble("precio");
				Escritor escritor = new Escritor(codigoEscritor);
				libro = new Libro(codigoLibro, escritor, titulo, anyoPublicacion, extension, precio);
//				listaLibros.add(libro);
			}
			rs.close();
			sentenciaPreparada.close();
			
		}finally {
			if(conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}
		return libro;
	}

}
