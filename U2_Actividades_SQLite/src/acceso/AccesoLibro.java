package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Escritor;
import modelo.Libro;
import modelo.Resultado;
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

	public static Escritor consultarEscritorByCode(int codigoLibro) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
//		List<Libro> listaLibros = null;
		Escritor escritor = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT * FROM escritor WHERE codigo = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			sentenciaPreparada.setInt(1, codigoLibro);
			ResultSet rs = sentenciaPreparada.executeQuery();
			if (rs.next()) {
				int codigo = rs.getInt("codigo");
				String nombre = rs.getString("nombre");
				String nacionalidad = rs.getString("nacionalidad");
				String cadenaFechaNacim = rs.getString("fecha_nacimiento");
				Date fechaNacimiento = Date.valueOf(cadenaFechaNacim);
				String cadenaFechaFall = rs.getString("fecha_fallecimiento");
				Date fechaFallecimiento = null;
				if (cadenaFechaFall != null) {
					fechaFallecimiento = Date.valueOf(cadenaFechaFall);
					escritor = new Escritor(codigo, nombre, nacionalidad, fechaNacimiento, fechaFallecimiento);
				} else {
					escritor = new Escritor(codigo, nombre, nacionalidad, fechaNacimiento);
				}

			}
			rs.close();
			sentenciaPreparada.close();

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}
		return escritor;
	}

	public static List<Escritor> consultarEscritorByDate(Date fecha1, Date fecha2)
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

	public static List<Libro> consultarLibrosByPrice(double precioMin, double precioMax)
			throws ClassNotFoundException, SQLException {
		List<Libro> listaLibros = new ArrayList<Libro>();
//		List<Object> listaValores = new ArrayList<Object>();
//
//		Map<String, List<Object>> mapaLibros = new HashMap<String, List<Object>>();

		Connection conexion = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sqlSentence = "SELECT titulo, anio_publicacion, precio FROM libro "
					+ "WHERE precio BETWEEN ? AND ? ORDER BY precio ASC";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
			int precioMin2 = (int) precioMin;
			int precioMax2 = (int) precioMax;

			sentenciaPreparada.setInt(1, precioMin2);
			sentenciaPreparada.setInt(2, precioMax2);
			ResultSet rs = sentenciaPreparada.executeQuery();
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				int anyoPublicacion = rs.getInt("anio_publicacion");
				double precio = rs.getDouble("precio");

//				listaValores.add(anyoPublicacion);
//				listaValores.add(precio);
//				mapaLibros.put(titulo, listaValores);
				Libro libro = new Libro(titulo, anyoPublicacion, precio);
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

	public static boolean insertarEscritor(String nombre, String nacionalidad, Date fechaNacimiento,
			Date fechaFallecimiento, boolean estaVivo) throws ClassNotFoundException, SQLException {
		boolean insertado = false;
		Connection conexion = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sentenciaSql = "INSERT INTO escritor (nombre, nacionalidad, fecha_nacimiento, fecha_fallecimiento)"
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, nombre);
			sentencia.setString(2, nacionalidad);
			sentencia.setString(3, fechaNacimiento.toString());
			if (estaVivo) {
				sentencia.setString(4, null);
			} else {
				sentencia.setString(4, fechaFallecimiento.toString());
			}

			sentencia.executeUpdate();
			ResultSet resultado = sentencia.getGeneratedKeys();

			if (resultado.next()) {
				insertado = true;
			}

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}

		return insertado;
	}

	public static boolean actualizarEscritorByCode(int codigo, String nombre, String nacionalidad, Date fechaNacimiento,
			Date fechaFallecimiento, boolean estaVivo) throws ClassNotFoundException, SQLException {

		boolean actualizado = false;
		Connection conexion = null;

		try {
			conexion = SQLiteUtil.abrirConexion();
			String SentenciaSql = "UPDATE escritor SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, fecha_fallecimiento = ?"
					+ " WHERE codigo = ? ";
			PreparedStatement sentencia = conexion.prepareStatement(SentenciaSql);
			sentencia.setString(1, nombre);
			sentencia.setString(2, nacionalidad);
			sentencia.setString(3, fechaNacimiento.toString());
			sentencia.setInt(5, codigo);
			if (estaVivo) {
				sentencia.setString(4, null);
			} else {
				sentencia.setString(4, fechaFallecimiento.toString());
			}
			if (sentencia.executeUpdate() > 0) {
				actualizado = true;
			}

		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}

		return actualizado;
	}

	public static int actualizarConDescuentoByAuth(int codigoEscritor, int descuento)
			throws ClassNotFoundException, SQLException {
		int actualizados = 0;
		// precio - (precio * "descuento") / 100
		Connection conexion = null;
		try {
			conexion = SQLiteUtil.abrirConexion();
			String sentenciaSql = "UPDATE libro SET precio = precio - (precio * ?) / 100 WHERE codigo_escritor = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, descuento);
			sentencia.setInt(2, codigoEscritor);
			actualizados = sentencia.executeUpdate();
			sentencia.close();
		} finally {
			if (conexion != null) {
				SQLiteUtil.cerrarConexion(conexion);
			}
		}
		return actualizados;
	}

//	public static List<Libro> consultarListLibrosPorPrecio(double precioMin, double precioMax)
//			throws ClassNotFoundException, SQLException {
//		List<Libro> listaLibros = new ArrayList<Libro>();
//
//		Resultado resultado = new Resultado();
//		
//		Connection conexion = null;
//		try {
//			conexion = SQLiteUtil.abrirConexion();
//			String sqlSentence = "SELECT titulo, anio_publicacion, precio FROM libro "
//					+ "WHERE precio BETWEEN ? AND ? ORDER BY precio ASC";
//			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sqlSentence);
//			int precioMin2 = (int) precioMin;
//			int precioMax2 = (int) precioMax;
//
//			sentenciaPreparada.setInt(1, precioMin2);
//			sentenciaPreparada.setInt(2, precioMax2);
//			ResultSet rs = sentenciaPreparada.executeQuery();
//			while (rs.next()) {
//				String titulo = rs.getString("titulo");
//				int anyoPublicacion = rs.getInt("anio_publicacion");
//				double precio = rs.getDouble("precio");
//
////				resultado.ponerCampo("Titulo", titulo);
////				resultado.ponerCampo("AnioPublicacion", String.format("%d", anyoPublicacion));
////				resultado.ponerCampo("Precio", String.format("%.2f", precio));
////				listaLibros.add(resultado);
//				
//			}
//			rs.close();
//			sentenciaPreparada.close();
//
//		} finally {
//			if (conexion != null) {
//				SQLiteUtil.cerrarConexion(conexion);
//			}
//		}
//
//		return listaLibros;
//	}

	/*
	 * While(){ String titulo = rs.get.. int anioPublicacion = rs.get.. double
	 * precio = rs.get... resultado.ponerCampo("Titulo", titulo)
	 * resultado.ponercampo("AnioPublicacion" , strinf.format("%d", anipublicacion))
	 * resultado.ponerCampo("Precio", Stringformat("%.2f", precio))
	 * resultado.add(resultado) <List> }
	 */

}
