package principal;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.List;
import acceso.AccesoEmpleado;
import entrada.Teclado;
import modelo.Empleado;
import modelo.Fecha;

public class GestionEmpleado {

	private static void escribirMenu() {
		System.out.println();
		System.out.println("0 - Salir.");
		System.out.println("1 - Consultar todos los empleados.");
		System.out.println("2 - Insertar nuevo empleado en el fichero.");
		System.out.println("3 - Actualizar Nombre, apellido y fecha de alta.");
		System.out.println("4 - Eliminar empleado por codigo.");
	}

	public static void escribirListaEmp(List<String> listaEmpleados) {
		int contador = 0;
		for (String empleado : listaEmpleados) {
			System.out.println(empleado);
			contador++;
		}
		System.out.println("Se han consultado " + contador + " empleados");
	}

	public static void main(String[] args) {
		int opcion, codigo;
		Empleado empleado;
		Fecha fechaDeAlta1 = null;

		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");

			try {
				switch (opcion) {
				case 0: // Salir
					System.out.println("Hasta Pronto!");
					break;

				case 1: // Consultar empleados
					try {
						List<String> empleados = AccesoEmpleado.leerFichero();
						if (empleados.isEmpty()) {
							System.out.println("No hay empleados en el fichero.");
						} else {
							escribirListaEmp(empleados);
						}
					} catch (StreamCorruptedException sce) {
						System.out.println("Se ha encontrado informacion inconsistente en el fichero binario.");
					} catch (FileNotFoundException fnfe) {
						System.out.println("Archivo no creado!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 2: // Insertar empleado
					codigo = Teclado.leerEntero("Codigo: ");
					try {
						if (AccesoEmpleado.buscarPorCodigo(codigo) != null) {
							System.out.println("Ya existe un empleado con este codigo.");
							break;
						}
					} catch (ClassNotFoundException cnfe) {
						System.out.println("Clase no encontrada");
						break;
					} catch (IOException ioe) {
						System.out.println("Error de E/S al buscar el empleado.");
						ioe.printStackTrace();

						break;
					}

					String nombre = Teclado.leerCadena("Nombre: ");
					String apellido = Teclado.leerCadena("Apellido: ");
					Fecha fechaDeAlta = Consola.leerFecha("Fecha de alta: ");
					String departamento = Teclado.leerCadena("Departamento: ");
					double salario = Teclado.leerReal("Salario: ");
					empleado = new Empleado(codigo, nombre, apellido, fechaDeAlta, departamento, salario);

					try {
						AccesoEmpleado.escribirEmpleado(empleado);
						System.out.println("Empleado insertado correctamente.");
					} catch (IOException ioe) {
						System.out.println("Ha ocurrido un error durante la E/S al escribir el empleado.");
						ioe.printStackTrace();
					}
					break;
				case 3:

//					Declarar codigo, nombre fechaAlta empleado
					codigo = Teclado.leerEntero("");
					nombre = Teclado.leerCadena("");
					apellido = Teclado.leerCadena("");
					fechaDeAlta1 = Consola.leerFecha("Fecha de alta: ");
					empleado = new Empleado(0, nombre, apellido, fechaDeAlta1, null, 0.0);
					break;
				case 4:
					codigo = Teclado.leerEntero("Codigo: ");
					AccesoEmpleado.eliminarPorCodigo(codigo);
					break;

				}
			} catch (Exception e) {
				System.out.println("Error inesperado: " + e.getMessage());
			}
		} while (opcion != 0);
	}
}
