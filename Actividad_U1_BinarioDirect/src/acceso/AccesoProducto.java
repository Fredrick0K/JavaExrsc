package acceso;

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

   
}
