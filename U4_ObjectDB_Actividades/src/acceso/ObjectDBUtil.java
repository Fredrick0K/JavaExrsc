package acceso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ObjectDBUtil {

	// fichero local de la base de datos campeonato
	private static final String FICHERO_BD = "data/concesionario.odb";
	
	// fábrica de conexiones de ObjectDB
	private static final EntityManagerFactory FABRICA_CONEXIONES = crearFabricaConexiones();

	private static EntityManagerFactory crearFabricaConexiones() {
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory(FICHERO_BD);
		return fabricaConexiones;
	}
	
	public static EntityManagerFactory obtenerFabricaConexiones() {
		return FABRICA_CONEXIONES;
	}
	
	public static void cerrarFabricaConexiones() {
		EntityManagerFactory fabricaConexiones = obtenerFabricaConexiones();
		if (fabricaConexiones != null && fabricaConexiones.isOpen()) {
			fabricaConexiones.close();
		}
	}
	
	public static EntityManager abrirConexion() {
		EntityManagerFactory fabricaConexiones = obtenerFabricaConexiones();
		EntityManager conexion = fabricaConexiones.createEntityManager();
		return conexion;
	}
	
	public static void cerrarConexion(EntityManager conexion) {
		if (conexion != null && conexion.isOpen()) {
			conexion.close();
		}
	}
	
}
