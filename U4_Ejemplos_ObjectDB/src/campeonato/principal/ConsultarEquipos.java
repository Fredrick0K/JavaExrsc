package campeonato.principal;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import campeonato.modelo.Equipo;

public class ConsultarEquipos {

	public static void main(String[] args) {
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		List<Equipo> listaEquipos = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			String jpql = "select e from Equipo e " +
			              "order by anioFundacion desc";
			TypedQuery<Equipo> consulta = conexion.createQuery(jpql, Equipo.class);
			listaEquipos = consulta.getResultList();
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			if (fabricaConexiones != null) {
				fabricaConexiones.close();
			}
		}
		if (listaEquipos.isEmpty()) {
			System.out.println("No se ha encontrado ningún equipo en la base de datos.");
		}
		else {
			for (Equipo equipo : listaEquipos) {
				System.out.println(equipo.toString());
			}
			System.out.println("Se han consultado " + listaEquipos.size() + " equipos de la base de datos");
		}
	}

}
