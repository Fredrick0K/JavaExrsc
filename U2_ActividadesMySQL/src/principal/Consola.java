package principal;

import java.sql.Date;
import java.util.List;
import entrada.Teclado;
//import modelo.Fecha;


public class Consola {

	/**
	 * Genera y devuelve el plural de un singular.
	 */
	private static String generarPlural(String singular) {
		if (singular.endsWith("a") || singular.endsWith("e") || singular.endsWith("i") || singular.endsWith("o")
				|| singular.endsWith("u")) {
			return singular + "s";
		} else {
			return singular + "es";
		}
	}

	/**
	 * Escribe en consola una lista de objetos.
	 */
	public static <E> void escribirLista(List<E> lista) {
		String nombreClase = lista.getFirst().getClass().getSimpleName();
		String nombreSingular = nombreClase.toLowerCase();
		String nombrePlural = generarPlural(nombreSingular);
		for (E elemento : lista) {
			System.out.println(elemento.toString());
		}
		if (lista.size() == 1) {
			System.out.println("Se ha consultado 1 " + nombreSingular + " del fichero.");
		} else {
			System.out.println("Se han consultado " + lista.size() + " " + nombrePlural + " del fichero.");
		}
	}

//	public static <E> void escribirListaMod(List<Libro> lista) {
//		String nombreClase = lista.getFirst().getClass().getSimpleName();
//		String nombreSingular = nombreClase.toLowerCase();
//		String nombrePlural = generarPlural(nombreSingular);
//		for (Libro elemento : lista) {
//			System.out.println(elemento.toStringMod());
//		}
//		if (lista.size() == 1) {
//			System.out.println("Se ha consultado 1 " + nombreSingular + " del fichero.");
//		} else {
//			System.out.println("Se han consultado " + lista.size() + " " + nombrePlural + " del fichero.");
//		}
//	}
	
	/**
	 * Lee por teclado una fecha con formato espaniol DD/MM/AAAA. Devuelve la fecha
	 * leida.
	 */
	public static Date leerFecha(String mensaje) {
		boolean valida = false;
		String cadena = null;
		Date fecha = null;
		while (!valida) {
			try {
				cadena = Teclado.leerCadena(mensaje);
				fecha = Date.valueOf(cadena);
				valida = true;
			} catch (IllegalArgumentException excepcion) {
				System.out.println("La fecha debe tener formato espaniol DD/MM/AAAA.");
			}
		}
		return fecha;
	}

}
