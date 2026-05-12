package campeonato.principal;

import java.util.List;
import campeonato.modelo.Jugador;
import entrada.Teclado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConsultarJugadoresPorSalario {

	public static void main(String[] args) {
		double salarioMinimo = Teclado.leerReal("Salario Mínimo: ");
		double salarioMaximo = Teclado.leerReal("Salario Máximo: ");
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		List<Jugador> listaJugadores = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			String jpql = "select j from Jugador j " +
			              "where salario >= ?1 and salario <= ?2";
			TypedQuery<Jugador> consulta = conexion.createQuery(jpql, Jugador.class);
			consulta.setParameter(1, salarioMinimo);
			consulta.setParameter(2, salarioMaximo);
			listaJugadores = consulta.getResultList();
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			if (fabricaConexiones != null) {
				fabricaConexiones.close();
			}
		}
		if (listaJugadores.isEmpty()) {
			System.out.println("No se ha encontrado ningún jugador entre los dos salarios en la base de datos.");
		}
		else {
			for (Jugador jugador : listaJugadores) {
				System.out.println(jugador.toString());
			}
			System.out.println("Se han consultado " + listaJugadores.size() + " jugadores de la base de datos");
		}
	}

}
