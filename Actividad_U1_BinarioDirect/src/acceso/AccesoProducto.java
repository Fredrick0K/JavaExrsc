package acceso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import modelo.Fecha;
import modelo.Producto;

public class AccesoProducto {
//Insertar, Actualizar con codigo, leer TODO, Leer con codigo, eliminar

	public static Producto leerProducto(RandomAccessFile flujoEntrada) throws IOException {

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

	public static boolean escribirProducto(RandomAccessFile flujoSalida, Producto producto) throws IOException {

		if (producto.getCodigo() <= 0) {
			return false;
		}

		File file = new File("Actividad_U1_BinarioDirect\\data\\producto.dat");
		flujoSalida = new RandomAccessFile(file, "rw");
		int tamanyoFichero = (int) flujoSalida.length();

		flujoSalida.seek(tamanyoFichero);
		flujoSalida.writeInt(producto.getCodigo());

		String nombre = producto.getNombre();
		for (int i = 0; i < Producto.LONGITUD_NOMBRE; i++) {
			char c = i < nombre.length() ? nombre.charAt(i) : ' ';
			flujoSalida.writeChar(c);
		}

		String categoria = producto.getCategoria();
		for (int i = 0; i < Producto.LONGITUD_CATEGORIA; i++) {
			char c = i < categoria.length() ? categoria.charAt(i) : ' ';
			flujoSalida.writeChar(c);
		}

		flujoSalida.writeBytes(producto.getFechaModificacion().toString() + "\n");
		flujoSalida.writeInt(producto.getCantidad());
		flujoSalida.writeDouble(producto.getPrecio());

		return true;
	}

   
}
