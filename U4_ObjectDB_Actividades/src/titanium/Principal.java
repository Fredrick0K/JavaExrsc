package titanium;

import java.util.List;
import entrada.Teclado;
import acceso.AccesoConcesionario;
import acceso.ObjectDBUtil;
import modelo.Turismo;

public class Principal {
	private static void escribirMenu() {
		System.out.println("0 - Salir");
		System.out.println("1 - Consultar turismo por codigo.");
		System.out.println("2 - Consultar Turismos.");
		System.out.println("3 - Consultar turismos por anyo.");
		System.out.println("4 - Insertar turismo.");
	}

	public static void main(String[] args) {
		int opcion;
		List<Turismo> lista;

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
					lista = AccesoConcesionario.consultarAll();
					if (lista.isEmpty()) {
						System.out.println("No hay turismos registrados.");
					} else {
						for (Turismo t : lista) {
							System.out.println(t);
						}
					}
					break;
				case 3:
					int yearMin = Teclado.leerEntero("Anyo minimo: ");
					int yearMax = Teclado.leerEntero("Anyo Max:");
					lista = AccesoConcesionario.consultarByYear(yearMin, yearMax);
					if(lista.isEmpty()){
						System.out.println("No se encontraron turismos en este rango de anyos");
					}else{
						for(Turismo t : lista){
							System.out.println(t);
						}
					}
					break;

			}
		} while (opcion != 0);
	}

}
