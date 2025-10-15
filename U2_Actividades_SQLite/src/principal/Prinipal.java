package principal;


import java.sql.SQLException;

import acceso.AccesoLibro;
import entrada.Teclado;
import modelo.Libro;

public class Prinipal {
	private static void escribirMenu() {
		System.out.println("0) - Salir");
		System.out.println("1) - Consultar Escritor por codigo");
		System.out.println("2) - Consultar Libro por codigo");
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
				break;
			case 2:
				int codigo = Teclado.leerEntero("Codigo de libro: ");
				try {
					libro = AccesoLibro.consultarLibroByCode(codigo);
					if(libro == null) {
						System.out.println("No se ha encontrado ningun libro con ese codigo. ");
					}else {
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

			}
		}while(opcion != 0);

		
	}

}
