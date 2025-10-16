package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Escritor;
import modelo.Libro;
import principal.Consola;

public class AccesoLibro {

	public static Libro consultarLibroByCode(int codigoLibro) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
//		List<Libro> listaLibros = null;
		Libro libro = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT * FROM libro WHERE codigo = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			sentenciaPreparada.setInt(1, codigoLibro);
			ResultSet rs = sentenciaPreparada.executeQuery();
			if (rs.next()) {
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

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}
		return libro;
	}

	public static List<Escritor> consultarEscritorPorFecha(Date fecha1, Date fecha2)
			throws ClassNotFoundException, SQLException {
		List<Escritor> listaEscritores = new ArrayList<Escritor>();
		Connection conexion = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT * FROM escritor WHERE fecha_nacimiento BETWEEN ? AND ? "; // BETWEEN fecha1 AND
																									// fecha2
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			sentenciaPreparada.setString(1, fecha1.toString());
			sentenciaPreparada.setString(2, fecha2.toString());
			ResultSet rs = sentenciaPreparada.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("codigo");
				String nombre = rs.getString("nombre");
				String nacionalidad = rs.getString("nacionalidad");
				String cadenaFechaNacim = rs.getString("fecha_nacimiento");
				Date fechaNacimiento = Date.valueOf(cadenaFechaNacim);
				String cadenaFechaFall = rs.getString("fecha_fallecimiento");
				Date fechaFallecimiento = null;

				if (cadenaFechaFall != null) {
					fechaFallecimiento = Date.valueOf(cadenaFechaFall);
				}
				Escritor escritor = new Escritor(codigo, nombre, nacionalidad, fechaNacimiento, fechaFallecimiento);
				listaEscritores.add(escritor);

			}
			rs.close();
			sentenciaPreparada.close();

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}

		return listaEscritores;
	}

	public static List<Libro> consultarLibrosPorPrecio(int precioMin, int precioMax)
			throws ClassNotFoundException, SQLException {
		List<Libro> listaLibros = new ArrayList<Libro>();
		Connection conexion = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT titulo, anio_publicacion, precio FROM libro WHERE precio BETWEEN ? AND ? ORDER BY precio ASC";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			sentenciaPreparada.setInt(1, precioMin);
			sentenciaPreparada.setInt(2, precioMax);
			ResultSet rs = sentenciaPreparada.executeQuery();
			while (rs.next()) {
		
				String titulo = rs.getString("titulo");
				int anyoPublicacion = rs.getInt("anio_publicacion");
				double precio = rs.getDouble("precio");

				Libro libro = new Libro( titulo, anyoPublicacion, precio);
				listaLibros.add(libro);
			}
			rs.close();
			sentenciaPreparada.close();

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}

		return listaLibros;
	}

}
