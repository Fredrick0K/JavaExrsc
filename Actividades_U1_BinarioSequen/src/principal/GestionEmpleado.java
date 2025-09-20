package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

import acceso.AccesoEmpleado;
import entrada.Teclado;
import modelo.Empleado;

public class GestionEmpleado {

	private static void escribirMenu() {

		System.out.println();
		System.out.println("0 - Salir.");
		System.out.println("1 - Consultar todos los empleados.");
		System.out.println("2 - Insertar nuevo empleado en el fichero.");

	}

	public static void main(String[] args) {
		int opcion;

		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");

			try {
				switch (opcion) {
				case 0: // Salir
					System.out.println("Hasta Pronto!");
					break;

				case 1: // Consultar empleados

					int contador = 0;
					try {
						for (String i : AccesoEmpleado.leerFichero()) {
							System.out.print(i + " ");
							contador++;
						}
						int size = AccesoEmpleado.leerFichero().size();
						System.out.println(size);
						System.out.print("\n");
						System.out.println("Se ha(n) consultado " + contador + " empleados");
					} catch (StreamCorruptedException sce) {
						System.out.println("Se ha encontrado información inconsistente en el fichero binario.");
					} catch (FileNotFoundException fnfe) {
						System.out.println("Archivo no creado!");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					break;
				case 2: // Insertar empleado
					try {
						int codigo = Teclado.leerEntero("Codigo:");
						String nombre = Teclado.leerCadena("Nombre: ");
						String apellido = Teclado.leerCadena("Apellido: ");
						String fechaDeAlta = Teclado.leerCadena("Fecha De Alta: ");
						String departamento = Teclado.leerCadena("Departamento: ");
						double salario = Teclado.leerReal("Salario: ");
						Empleado empleado = new Empleado(codigo, nombre, apellido, fechaDeAlta, departamento, salario);

						AccesoEmpleado.escribirEmpleado(empleado);
						System.out.println("\n");
						System.out.println("Se ha añadido al fichero!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// no olvidar finally cerrando flujos
		} while (opcion != 0);
	}
}
