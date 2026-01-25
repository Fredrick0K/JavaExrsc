package titanium;

import java.sql.Date;
import java.util.List;
import entrada.Teclado;
import acceso.AccesoConcesionario;
import acceso.ObjectDBUtil;
import modelo.Cliente;
import modelo.Domicilio;
import modelo.Turismo;

public class Principal {
	private static void escribirMenu() {
		System.out.println("0 - Salir");
		System.out.println("1 - Consultar turismo por codigo.");
		System.out.println("2 - Consultar Turismos");
		System.out.println("3 - Actualizar la fecha de nacimiento, el domicilio de residencia,"
		+ "el teléfono y el correo de un cliente de la base de datospor código.");
		System.out.println("4 - Insertar Turismo");
		System.out.println("5 - Eliminar Turismos por rango de anyos.");
	}

	public static void main(String[] args) {
		int opcion;

		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");

			switch (opcion) {
				case 0:
					System.out.println("Cya1!");
					ObjectDBUtil.cerrarFabricaConexiones();
					break;
				case 1:

					int codig = Teclado.leerEntero("Codigo: ");
					System.out.println("Codigo: " + codig);
					Turismo turismo = AccesoConcesionario.consultarByCode(codig);
					if (turismo == null) {
						System.out.println("No se ha encontrado ningun vehiculo");
					} else {
						System.out.println(turismo.toString());
					}
					break;

				case 2:
					List<Turismo> lista = AccesoConcesionario.consultarAll();
					if (lista.isEmpty()) {
						System.out.println("No hay turismos registrados.");
					} else {
						for (Turismo t : lista) {
							System.out.println(t);
						}
					}
					break;
				case 3:
					int code = Teclado.leerEntero("Codigo: ");
					// fecha de nacimiento, domicilio, teléfono correo
					System.out.println("----Datos del Domicilio----");
					String via = Teclado.leerCadena("Via: ");
					int numero = Teclado.leerEntero("Numero: ");
					int cp = Teclado.leerEntero("Codigo Postal: ");
					String localidad = Teclado.leerCadena("Localidad: ");
					Domicilio home = new Domicilio(via, numero, cp, localidad);

					System.out.println("----Datos del Cliente----");
					String fechaNacim = Teclado.leerCadena("Fecha de nacimiento ");
					Date nacim = Date.valueOf(fechaNacim);
					String telefono = Teclado.leerCadena("Telefono: ");
					String correo = Teclado.leerCadena("Correo: ");
					Cliente cliente = new Cliente(nacim, home, telefono, correo);

					boolean updated = AccesoConcesionario.actualizarClienteByCode(code, cliente);
					
					if(updated){
						System.out.println("Se ha actualizado correctamente!");
					}else{
						System.err.println("No se ha encontrado un cliente con este codigo.");
					}					
					break;
				case 4:
					String made = Teclado.leerCadena("Fabricante: ");
					String model = Teclado.leerCadena("Modelo: ");
					int anioFabricacion = Teclado.leerEntero("Anyo de fabricacion: ");
					int seatsNum = Teclado.leerEntero("Numero de Plazas: ");
					int loadCap = Teclado.leerEntero("Capacidad de maletero (L): ");
					double precio = Teclado.leerReal("Precio: ");
					Turismo car = new Turismo(made, model, anioFabricacion, seatsNum, loadCap, precio);
					boolean inserted = AccesoConcesionario.insertarTurismo(car);

					if (inserted) {
						System.out.println("Insertado!");
					} else {
						System.out.println("Error en guardado en la base de datos.");
					}

					break;
					case 5:
						int minYear = Teclado.leerEntero("Anyo minimo de fabricacion: ");
						int maxyear = Teclado.leerEntero("Anyo maximo de fabricacion: ");
						int slimed = AccesoConcesionario.eliminarByYear(minYear, maxyear);
						if(slimed == 0){
							System.out.println("No cars where found between these years");
						}else{
							System.out.println(slimed + " cars where absolute ringed, slimed, banged.");
						}
						break;
			}
		} while (opcion != 0);
	}
}
