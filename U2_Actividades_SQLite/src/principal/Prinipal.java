package principal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
		System.out.println("4) - Consultar Libro valorados entre 2 precios");
		System.out.println("5) - Insertar un nuevo escritor");
		System.out.println("6) - Actualizar un escritor por codigo.");
		System.out.println("7) - Borrar libros entre dos extensiones");
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
					List<Escritor> listaEscritores = AccesoLibro.consultarEscritorByDate(fecha1, fecha2);
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
				double precio1 = Teclado.leerReal("Precio minimo: ");
				double precio2 = Teclado.leerReal("Precio maximo: ");

				try {
//					Map<String, List<Object>> mapaLibros = AccesoLibro.consultarLibrosPorPrecio(precio1, precio2);
//					if (mapaLibros.isEmpty()) {
//						System.out.println("No se encontro ningun libro entre este rango de precios.");
//					} else {
//						for (Map.Entry<String, List<Object>> entrada : mapaLibros.entrySet()) {
//							//Sacar la Clave
//							String clave = entrada.getKey();
//
//							//Sacar el Valor 
//							List<Object> valores = entrada.getValue();
//							for(Object val : valores) {
//								System.out.println(val.toString());
//							}
//
//							// Sacar y casting
//							int anyoPublicacion = (int) valores.get(0); //anyo Publicacion
////							double precio = (Double) valores.get(1); // precio
////
////							System.out.println("Libro: " + clave + " (anyo de publicacion: " + anyoPublicacion 
////									+ ", Precio: " + precio + "e )");
//						}

					List<Libro> listaLibros = AccesoLibro.consultarLibrosByPrice(precio1, precio2);
					if (listaLibros.isEmpty()) {
						System.out.println("No se encontro ningun libro entre este rango de precios.");
					} else {
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
			case 5:
				// pedir datos, crear objeto escritor, preguntar si esta vivo o no.

				String nombre = Teclado.leerCadena("Nombre del Escritor: ");
				String nacionalidad = Teclado.leerCadena("Nacionalidad del escritor: ");
				Date fechaNacimiento = Consola.leerFecha("Fecha de nacionalidad: ");
				Date fechaFallecimiento;

				boolean estaVivo = Teclado.leerBooleano("Esta vivo? (true/false) : ");
				if (estaVivo) {
					fechaFallecimiento = null;
				} else {
					fechaFallecimiento = Consola.leerFecha("Fecha de fallecimiento");
				}
				try {
					if (AccesoLibro.insertarEscritor(nombre, nacionalidad, fechaNacimiento, fechaFallecimiento,
							estaVivo)) {
						System.out.println("Se ha insertado correctamente! ");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 6:
				// nombre, la nacionalidad, la fecha de nacimiento y
				// la fecha de fallecimiento
				codigo = Teclado.leerEntero("Codigo del escritor: ");
				nombre = Teclado.leerCadena("Nombre: ");
				nacionalidad = Teclado.leerCadena("Nacionalidad: ");
				fechaNacimiento = Consola.leerFecha("Fecha de Nacimiento: ");
				fechaFallecimiento = null;

				estaVivo = Teclado.leerBooleano("Esta vivo? (true/false) : ");
				if (estaVivo) {
					fechaFallecimiento = null;
				} else {
					fechaFallecimiento = Consola.leerFecha("Fecha de fallecimiento: ");
				}
				try {
					if (AccesoLibro.actualizarEscritorByCode(codigo, nombre, nacionalidad, fechaNacimiento,
							fechaFallecimiento, estaVivo)) {
						System.out.println("Se ha actualizado correctamente el escritor codigo " + codigo + "! ");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
				
			case 7:
				break;

			}
		} while (opcion != 0);
	}
}
