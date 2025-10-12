package principal;

import acceso.AccesoProducto;
import entrada.Teclado;
import modelo.Fecha;
import modelo.Producto;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Principal {
	private static final String FICHERO_VS = "Actividad_U1_BinarioDirect\\data\\producto.dat";
	private static final String FICHERO = "data/productos.dat";

	private static void escribirMenu() {

		System.out.println();
		System.out.println("0 - Salir.");
		System.out.println("1 - Consultar todos los productos.");
		System.out.println("2 - Consultar producto por codigo.");
		System.out.println("3 - Consultar por precio.");
		System.out.println("4 - Insertar nuevo producto en el fichero.");
		System.out.println("5 - Actualizar precio, cantidad y fecha de modificacion.");
		System.out.println("6 - Eliminar producto por codigo.");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomAccessFile flujoEntrada = null;

		int opcion;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Introduzca una opcion: ");
			switch (opcion) {
			case 0:
				System.out.println("Saliendo...");
				break;
			case 1:
				List<Producto> listaProductos = new ArrayList<Producto>();
				try {
					File fichero = new File(FICHERO_VS);
					flujoEntrada = new RandomAccessFile(fichero, "r");
					flujoEntrada.seek(0);
					int contador = 0;

					while (flujoEntrada.getFilePointer() < flujoEntrada.length()) {
						Producto producto = AccesoProducto.leerFichero(flujoEntrada);
						if (producto.getCodigo() > 0) {
							listaProductos.add(producto);
							contador++;
						}
					}
					if (listaProductos.isEmpty()) {
						System.out.println("No hay productos en el fichero.");
					} else {
						Consola.escribirLista(listaProductos);
					}
					System.out.println("Total productos: " + contador);
				} catch (FileNotFoundException fnfe) {
					System.out.println("Archivo no encontrado");
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					System.out.println("Error al leer del fichero binario.");
					ioe.printStackTrace();
				} finally {
					try {
						if (flujoEntrada != null) {
							flujoEntrada.close();
						}
					} catch (IOException ioe) {
						System.out.println("Error al cerrar el fichero binario.");
						ioe.printStackTrace();
					}
				}
				break;
			case 2:
				int codigo = Teclado.leerEntero("Codigo: ");
				try {
					List<Producto> listaProducto = AccesoProducto.consultarPorCode(codigo);
					if (listaProducto == null || listaProducto.isEmpty()) {
						System.out.println("No se encontró ningún producto con ese código.");
					} else {
						Consola.escribirLista(listaProducto);
					}
				} catch (IOException e) {
					System.out.println("Error al consultar por código.");
					e.printStackTrace();
				}
				break;
			case 3:
				int precioMin = Teclado.leerEntero("Precio Minimo: ");
				int precioMax = Teclado.leerEntero("Precio Maximo: ");

				try {
					List<Producto> listaProducto = null;
					listaProducto = AccesoProducto.consultarPorPrecio(precioMin, precioMax);
					if (listaProducto.isEmpty()) {
						System.out.println("No se encontro ningun producto entre este rango de $$.");
					} else {
						System.out.println("Lista con toString()");
						System.out.println(listaProducto.toString());
						System.out.println("Lista con 'Consola'");
						Consola.escribirLista(listaProducto);
					}

				} catch (IOException ioe) {
					// TODO: handle exception
					System.out.println("Error en la Lectura. E/S");
					ioe.printStackTrace();
				}
				break;
			case 4:
				/* int codigo = Teclado.leerEntero("Introduzca el codigo del producto: "); */
				String nombre = Teclado.leerCadena("Introduzca el nombre del producto: ");
				String categoria = Teclado.leerCadena("Introduzca la categoria del producto: ");
				String fechaStr = Teclado.leerCadena("Introduzca la fecha de modificacion (dd/mm/aaaa): ");
				Fecha fechaModificacion = new Fecha(fechaStr);
				int cantidad = Teclado.leerEntero("Introduzca la cantidad del producto: ");
				double precio = Teclado.leerReal("Introduzca el precio del producto: ");
				Producto producto = new Producto(0, nombre, categoria, fechaModificacion, cantidad, precio);
				try {
					AccesoProducto.escribirProducto(flujoEntrada, producto);
					System.out.println("Se ha escrito un producto en el fichero binario.");
				} catch (IOException ioe) {
					System.out.println("Error al escribir en el fichero binario.");
					ioe.printStackTrace();
				} finally {
					if (flujoEntrada != null) {
						try {
							flujoEntrada.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 5:
				codigo = Teclado.leerEntero("");
				fechaModificacion = Consola.leerFecha("Fecha: ");
				cantidad = Teclado.leerEntero("");
				precio = Teclado.leerReal("");
				producto = new Producto(0, "", "", fechaModificacion, cantidad, precio);
				break;
			case 6:

				codigo = Teclado.leerEntero("Codigo: ");
				try {
					if (AccesoProducto.eliminarPorCode(codigo)) {
						System.out.println("Se ha eliminado el producto con codigo " + codigo);
					} else {
						System.out.println("Producto Invalido.");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}

		} while (opcion != 0);
	}
}
