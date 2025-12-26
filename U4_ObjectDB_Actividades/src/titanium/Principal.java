package titanium;

import java.util.List;
import entrada.Teclado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import acceso.AccesoConcesionario;
import acceso.ObjectDBUtil;
import modelo.Cliente;
import modelo.Turismo;
import modelo.Venta;

public class Principal {
	private static void escribirMenu() {
		System.out.println("0) - Salir");
		System.out.println("1) - Consultar turismo por codigo.");
		System.out.println("2) - Consultar Turismos");
		System.out.println("3) - Consultar Ventas");
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
				break;

			}
		} while (opcion != 0);
	}

}
