package acceso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import modelo.Fecha;
import modelo.Producto;

public class AccesoProducto {
//Insertar, Actualizar con codigo, leer TODO, Leer con codigo, eliminar

	private static final String FICHERO_VS = "Actividad_U1_BinarioDirect\\data\\producto.dat";
	private static final String FICHERO = "data/productos.dat";

	/*
	 * int dia = flujoLectura.readInt fecha new fecha(dia, mes, anyo)
	 */
	public static Producto leerFichero(RandomAccessFile flujoEntrada) throws IOException {

		int codigo = flujoEntrada.readInt();
		char[] vectorCaracteres = new char[Producto.LONGITUD_NOMBRE];
		for (int posicion = 0; posicion < vectorCaracteres.length; posicion++) {
			vectorCaracteres[posicion] = flujoEntrada.readChar();
		}

		String nombre = new String(vectorCaracteres);

		vectorCaracteres = new char[Producto.LONGITUD_CATEGORIA];
		for (int posicion = 0; posicion < vectorCaracteres.length; posicion++) {
			vectorCaracteres[posicion] = flujoEntrada.readChar();
		}

		String categoria = new String(vectorCaracteres);
		String fechaStr = flujoEntrada.readLine();
		Fecha fechaModificacion = new Fecha(fechaStr);
		int cantidad = flujoEntrada.readInt();
		double precio = flujoEntrada.readDouble();
		Producto producto = new Producto(codigo, nombre, categoria, fechaModificacion, cantidad, precio);
		return producto;

	}

	public static List<Producto> leerTodos() throws IOException {
		RandomAccessFile flujoLectura = null;
		List<Producto> listaProductos = new ArrayList<Producto>();
		try {
			flujoLectura = new RandomAccessFile(FICHERO_VS, "r");
			flujoLectura.seek(0);
			while (flujoLectura.getFilePointer() < flujoLectura.length()) {
				Producto producto = leerFichero(flujoLectura);
				if (producto.getCodigo() > 0) {
					listaProductos.add(producto);
				}
			}
		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return listaProductos;
	}

	public static boolean escribirProducto(RandomAccessFile flujoSalida, Producto producto) throws IOException {

		if (producto.getCodigo() <= 0) {
			return false;
		}

		File file = new File(FICHERO_VS);
		flujoSalida = new RandomAccessFile(file, "rw");
		int tamanyoFichero = (int) flujoSalida.length();

		int codigo = (int) (tamanyoFichero / Producto.TAMANYO_REGISTRO) + 1;
		producto.setCodigo(codigo);

		flujoSalida.seek(tamanyoFichero);
		flujoSalida.writeInt(producto.getCodigo());
		escribeProducto(producto, flujoSalida);
		return true;
	}

	private static void escribeProducto(Producto producto, RandomAccessFile flujoEscritura) throws IOException {
		flujoEscritura.writeInt(producto.getCodigo());
		flujoEscritura.writeChars(producto.getNombre());
		flujoEscritura.writeChars(producto.getCategoria());
		escribirFecha(producto.getFechaModificacion(), flujoEscritura);
		flujoEscritura.writeInt(producto.getCantidad());
		flujoEscritura.writeDouble(producto.getPrecio());

	}

	private static void escribirFecha(Fecha fecha, RandomAccessFile flujoEscritura) throws IOException {
		flujoEscritura.writeInt(fecha.getDia());
		flujoEscritura.writeInt(fecha.getMes());
		flujoEscritura.writeInt(fecha.getAnio());

	}

	public static List<Producto> consultarPorCode(int codigo) throws IOException {
		RandomAccessFile flujoLectura = null;
		List<Producto> listaProductos = new ArrayList<Producto>();
		try {
			flujoLectura = new RandomAccessFile(FICHERO_VS, "r");
			int posicio = (codigo - 1) * Producto.TAMANYO_REGISTRO;
			flujoLectura.seek(0);
			if (posicio >= 0 && posicio < flujoLectura.length()) {
				flujoLectura.seek(posicio);
				Producto prod = leerFichero(flujoLectura);
				if (prod.getCodigo() > 0) {
					listaProductos.add(prod); // <-- Aquí agregas el producto a la lista
				}
			}

		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return listaProductos;
	}

	public static List<Producto> consultarPorPrecio(int precioMin, int precioMax) throws IOException {
		RandomAccessFile flujoLectura = null;
		List<Producto> listaProductos = new ArrayList<Producto>();

		try {

			flujoLectura = new RandomAccessFile(FICHERO_VS, "r");
			flujoLectura.seek(0);
			while (flujoLectura.getFilePointer() < flujoLectura.length()) {
				Producto prod = leerFichero(flujoLectura);
				if (prod.getCodigo() > 0) {
					if (prod.getPrecio() <= precioMax && prod.getPrecio() >= precioMin) {

						listaProductos.add(prod);
					}
				}
			}

		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}

		return listaProductos;
	}

	public static boolean eliminarPorCode(int codigo) throws IOException {
		boolean eliminado = false;
		RandomAccessFile flujoReadyWrite = null;

		try {
			flujoReadyWrite = new RandomAccessFile(FICHERO_VS, "rw");
			int posicion = (codigo - 1) * Producto.TAMANYO_REGISTRO;
			if (posicion >= 0 && posicion < flujoReadyWrite.length()) {
				flujoReadyWrite.seek(posicion);
				Producto producto = leerFichero(flujoReadyWrite);
				if (producto.getCodigo() > 0) {
					flujoReadyWrite.seek(posicion);
					flujoReadyWrite.writeInt(0);
					eliminado = true;
				}
			}

		} finally {
			if (flujoReadyWrite != null) {
				flujoReadyWrite.close();
			}
		}

		return eliminado;
	}

	public static boolean actualizarPorCode(int codigo, Producto nuevoProducto) throws IOException {
		boolean actualizado = false;
		RandomAccessFile flujoReadNWrite = null;
		try {
			flujoReadNWrite = new RandomAccessFile(FICHERO_VS, "rw");
			int posicion = (codigo - 1) * Producto.TAMANYO_REGISTRO;
			if (posicion >= 0 && posicion < flujoReadNWrite.length()) {
				flujoReadNWrite.seek(posicion);
				Producto producto = leerFichero(flujoReadNWrite);
				if (producto.getCodigo() > 0) {
					producto.setFechaModificacion(nuevoProducto.getFechaModificacion());
					producto.setCantidad(nuevoProducto.getCantidad());
					producto.setPrecio(nuevoProducto.getPrecio());
					escribeProducto(producto, flujoReadNWrite);
					actualizado = true;
				}

			}
		} finally {
			if (flujoReadNWrite != null) {
				flujoReadNWrite.close();
			}
		}
		return actualizado;
	}

}

//String nombre = producto.getNombre();
//for (int i = 0; i < Producto.LONGITUD_NOMBRE; i++) {
//	char c = i < nombre.length() ? nombre.charAt(i) : ' ';
//	flujoSalida.writeChar(c);
//}
//
//String categoria = producto.getCategoria();
//for (int i = 0; i < Producto.LONGITUD_CATEGORIA; i++) {
//	char c = i < categoria.length() ? categoria.charAt(i) : ' ';
//	flujoSalida.writeChar(c);
//}
//
//flujoSalida.writeBytes(producto.getFechaModificacion().toString() + "\n");
//flujoSalida.writeInt(producto.getCantidad());
//flujoSalida.writeDouble(producto.getPrecio());

/* 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
