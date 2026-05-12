package campeonato.principal;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import campeonato.modelo.Equipo;
import campeonato.modelo.Jugador;
import campeonato.modelo.Partido;
import campeonato.modelo.Resultado;

public class CrearCampeonato {

	private static String[] NOMBRES = {
		"Juan", "María", "Pedro", "Isabel", 
		"Pablo", "Pilar", "Jorge", "Elena",
		"Francisco", "Marta", "Roberto", "Teresa", 
		"Rubén", "Lucía", "Ricardo", "Alicia"
	};
	
	private static String[] POSICIONES = {
		"Portero", "Defensa", "Centrocampista", "Delantero"
	};
	
	private static Date generarFecha(int anioMinimo, int anioMaximo) {
		Random aleatorio = new Random();
		int anio = aleatorio.nextInt(anioMaximo - anioMinimo + 1) + anioMinimo;
		int mes = aleatorio.nextInt(12) + 1;
		int dia = aleatorio.nextInt(30) + 1;
		String cadena = String.format("%04d-%02d-%02d", anio, mes, dia);
		return Date.valueOf(cadena);
	}
	
	public static void main(String[] args) {
		Random aleatorio = new Random();
		List<Jugador> listaJugadores = new LinkedList<Jugador>();
		for (int pos = 0 ; pos < NOMBRES.length ; pos++) {
			Date fechaNacimiento = generarFecha(1980, 2000);
			double salario = aleatorio.nextDouble() * (2800 - 1400) + 1400;
			int numeroCamiseta = aleatorio.nextInt(11) + 1;
			Jugador jugador = new Jugador(0, NOMBRES[pos], fechaNacimiento, salario, numeroCamiseta, POSICIONES[pos % 4]);
			listaJugadores.add(jugador);
		}
		Equipo equipo1 = new Equipo("Rojo", "Zaragoza", "La Romareda", 1923, 12876.55);
		equipo1.insertar(listaJugadores.get(0));
		equipo1.insertar(listaJugadores.get(1));
		equipo1.insertar(listaJugadores.get(2));
		equipo1.insertar(listaJugadores.get(3));
		Equipo equipo2 = new Equipo("Verde", "Huesca", "El Alcoraz", 1955, 5186.77);
		equipo2.insertar(listaJugadores.get(4));
		equipo2.insertar(listaJugadores.get(5));
		equipo2.insertar(listaJugadores.get(6));
		equipo2.insertar(listaJugadores.get(7));
		Equipo equipo3 = new Equipo("Azul", "Teruel", "Pinilla", 1960, 4932.33);
		equipo3.insertar(listaJugadores.get(8));
		equipo3.insertar(listaJugadores.get(9));
		equipo3.insertar(listaJugadores.get(10));
		equipo3.insertar(listaJugadores.get(11));
		Equipo equipo4 = new Equipo("Amarillo", "Calatayud", "San Iñigo", 1972, 2108.99);
		equipo4.insertar(listaJugadores.get(12));
		equipo4.insertar(listaJugadores.get(13));
		equipo4.insertar(listaJugadores.get(14));
		equipo4.insertar(listaJugadores.get(15));
		Partido partido01 = new Partido(equipo1, equipo2, Date.valueOf("2022-06-04"), new Resultado(3,0));
		Partido partido02 = new Partido(equipo3, equipo4, Date.valueOf("2022-06-04"), new Resultado(2,2));
		Partido partido03 = new Partido(equipo1, equipo3, Date.valueOf("2022-10-18"), new Resultado(1,2));
		Partido partido04 = new Partido(equipo2, equipo4, Date.valueOf("2022-10-18"), new Resultado(2,0));
		Partido partido05 = new Partido(equipo1, equipo4, Date.valueOf("2023-03-02"), new Resultado(0,0));
		Partido partido06 = new Partido(equipo2, equipo3, Date.valueOf("2023-03-02"), new Resultado(1,1));
		Partido partido07 = new Partido(equipo2, equipo1, Date.valueOf("2023-07-16"), new Resultado(1,0));
		Partido partido08 = new Partido(equipo4, equipo3, Date.valueOf("2023-07-16"), new Resultado(2,3));
		Partido partido09 = new Partido(equipo3, equipo1, Date.valueOf("2024-05-30"), new Resultado(0,1));
		Partido partido10 = new Partido(equipo4, equipo2, Date.valueOf("2024-05-30"), new Resultado(3,3));
		Partido partido11 = new Partido(equipo4, equipo1, Date.valueOf("2024-12-13"), new Resultado(0,2));
		Partido partido12 = new Partido(equipo3, equipo2, Date.valueOf("2024-12-13"), new Resultado(2,1));
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();
			for (Jugador jugador : listaJugadores) {
				conexion.persist(jugador);
			}
			conexion.persist(equipo1);
			conexion.persist(equipo2);
			conexion.persist(equipo3);
			conexion.persist(equipo4);
			conexion.persist(partido01);
			conexion.persist(partido02);
			conexion.persist(partido03);
			conexion.persist(partido04);
			conexion.persist(partido05);
			conexion.persist(partido06);
			conexion.persist(partido07);
			conexion.persist(partido08);
			conexion.persist(partido09);
			conexion.persist(partido10);
			conexion.persist(partido11);
			conexion.persist(partido12);
			transaccion.commit();
		}
		catch (Exception excepcion) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw excepcion;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			if (fabricaConexiones != null) {
				fabricaConexiones.close();
			}
		}
		System.out.println("Se ha creado una base de datos campeonato con");
		System.out.println("16 jugadores, 4 equipos y 12 partidos.");
	}

}
