package principal;

import java.sql.Date;
import java.util.List;

import acceso.AccesoFilmoteca;
import entrada.Teclado;
import modelo.Actor;
import modelo.Pelicula;

public class Principal {

	private static void escribirMenu() {
		// TODO Auto-generated method stub
		System.out.println("0) - Salir");
		System.out.println("1) - Consulatar pelicula por codigo");
		System.out.println("2) - Consulatar pelicula por codigo de diferente manera.");
		System.out.println("3) - Consulatar pelicula por titulo.");
		System.out.println("4) - Consultar actor por nacionalidad.");
		System.out.println("5) - Insertar actor.");
		System.out.println("6) - Actualizar pelicula.");
		System.out.println("7) - Eliminar pelicula por ranking.");

	}

	public static void main(String[] args) {
		int opcion;

		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Opcion: ");
			switch (opcion) {
			case 0:
				break;
			case 1:
				int codigo = Teclado.leerEntero("Codigo: ");
				Pelicula peli = AccesoFilmoteca.consultarPeliByCode(codigo);
				if(peli == null) {
					System.out.println("No se encontraron peliculas con este codigo.");
				}
				else {
					System.out.println(peli.toString());
				}
				break;
				
			case 2:
				codigo = Teclado.leerEntero("Codigo: ");
				peli = AccesoFilmoteca.consultarPeliByCode2(codigo);
				
				if(peli == null) {
					System.out.println("No se encontraron peliculas con este codigo.");
				}
				else {
					System.out.println(peli.toString());
				}
				break;
			case 3:
				
				String word = Teclado.leerCadena("Palabra: ");
				
				List<Pelicula> listaPeli = AccesoFilmoteca.consultarPeliByWord(word);
				
				if(listaPeli.isEmpty()) {
					System.out.println("No hay peliculas con la palabra: " + word);
				}else {
					for(Pelicula pelis : listaPeli) {
						System.out.println(pelis.toString());
					}	
				}			
				break;
			case 4:
				String nacionalidad = Teclado.leerCadena("Nacionalidad: ");
				
				List<Actor> listaActores = AccesoFilmoteca.consultarAuthorBynationality(nacionalidad);
				
				if(listaActores.isEmpty()) {
					System.out.println("No hay actor(es) con la nacionalidad: " + nacionalidad);
				}else {
					for(Actor actores : listaActores) {
						System.out.println(actores.toString());
					}	
				}
				
				break;
				
			case 5:
				Actor actor;
				String nombre = Teclado.leerCadena("Nombre: ");
				nacionalidad = Teclado.leerCadena("Nacionalidad: ");
				String fechaNacimiento = Teclado.leerCadena("Fecha de Nacimiento (AAAA-MM-DD): ");
				Date fechaNacim = Date.valueOf(fechaNacimiento);
				boolean estaVivo = Teclado.leerBooleano("Esta vivo? (true/false): ");
				String fechaFallecimiento;
				Date fechaFallecim;
				if(estaVivo) {
					fechaFallecimiento = null;
					actor = new Actor(nombre, nacionalidad, fechaNacim);
				}
				else {
					fechaFallecimiento = Teclado.leerCadena("Fecha de fallecimiento: ");
					fechaFallecim = Date.valueOf(fechaFallecimiento);
					actor = new Actor(nombre, nacionalidad, fechaNacim, fechaFallecim);
				}
				
				if(AccesoFilmoteca.insertarActor(actor)) {
					System.out.println("Saved!");
				}
				
				break;
			case 7:
				
				double rank1 = Teclado.leerReal("Rank 1 minimo: ");
				double rank2 = Teclado.leerReal("Rank 2 minimo: ");
				
				int result = AccesoFilmoteca.eliminarPeliByRanking(rank1, rank2);
				
				System.out.println("Se han eliminado " + result + " Pelis");
				
				break;
				
				
			}
		} while (opcion != 0);

	}

}
