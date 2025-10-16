package principal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import acceso.AccesoLibro;
import entrada.Teclado;
import modelo.Escritor;
import modelo.Libro;

public class Prinipal {
	private static void escribirMenu() {
		System.out.println("0) - Salir");
		System.out.println("1) - Consultar Escritor por codigo");
		System.out.println("2) - Consultar Libro por codigo");
		System.out.println("3) - Consultar Escritores nacidos entre 2 fechas (AAAA-MM-DD).");
		System.out.println("4) - Consultar Libro valorados entre 2 fechas");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		Libro libro = null;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");
			switch (opcion) {
			case 0:
				System.out.println("Hasta Pronto!");
				break;
			case 1:
				System.err.println("¡SIN PROGRAMAR AUN!");
				break;
			case 2:
				int codigo = Teclado.leerEntero("Codigo de libro: ");
				try {
					libro = AccesoLibro.consultarLibroByCode(codigo);
					if (libro == null) {
						System.out.println("No se ha encontrado ningun libro con ese codigo. ");
					} else {
						System.out.println(libro.toString());
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				Date fecha1 = Consola.leerFecha("Fecha de nacimiento 1: ");
				Date fecha2 = Consola.leerFecha("Fecha de nacimiento 2: ");
				try {
					List<Escritor> listaEscritores = AccesoLibro.consultarEscritorPorFecha(fecha1, fecha2);
					if (listaEscritores.isEmpty()) {
						System.out.println("No se encontro ningun Escritor entre estas fechas");
					} else {
						Consola.escribirLista(listaEscritores);
						
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				int precio1 = Teclado.leerEntero("Precio minimo: ");
				int precio2 = Teclado.leerEntero("Precio maximo: ");
				
				try {
					List<Libro> listaLibros = AccesoLibro.consultarLibrosPorPrecio(precio1, precio2);
					if(listaLibros.isEmpty()) {
						System.out.println("No se encontro ningun libro entre este rango de precios.");
					}else {
						Consola.escribirListaMod(listaLibros);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				

			}
		} while (opcion != 0);

	}

}
