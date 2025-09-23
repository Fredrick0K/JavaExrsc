package principal;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.List;

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

	public static void escribirListaEmp(List<Empleado> listaEmopleado)
			throws StreamCorruptedException, FileNotFoundException, EOFException, ClassNotFoundException, IOException {
		int contador = 0;
		for (String i : AccesoEmpleado.leerFichero()) {
			System.out.print(i + " ");
			contador++;
		}
		System.out.println("Se han consultado " + contador + " empleados");

	}

	public static void main(String[] args) {
		int opcion, codigo;
		Empleado empleado;

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
						if (AccesoEmpleado.leerFichero().isEmpty()) {
							System.out.println("No hay empleados en el fichero.");
						} else {

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
					codigo = Teclado.leerEntero("Codigo: ");
					
						int codigoEmp = Teclado.leerEntero("Codigo:");
						try {
							if(AccesoEmpleado.buscarPorCodigo(codigo) != null) {
								System.out.println("Ya existe un man con este codigo.");
							}	
						}catch (ClassNotFoundException cnfe) {
							System.out.println("Clase no encotrada");
						}
						
						String nombre = Teclado.leerCadena("Nombre: ");
						String apellido = Teclado.leerCadena("Apellido: ");
						String fechaDeAlta = Teclado.leerCadena("Fecha De Alta: ");
						String departamento = Teclado.leerCadena("Departamento: ");
						double salario = Teclado.leerReal("Salario: ");
						empleado = new Empleado(codigoEmp, nombre, apellido, fechaDeAlta, departamento, salario);

						AccesoEmpleado.escribirEmpleado(empleado);
					}

					break;

				}catch(IOException ioe) {
					System.out.println("Ha ocurrido un error durante la E/S");
				}
			// no olvidar finally cerrando flujos
		
		}while(opcion!=0);
	}
}
