package acceso;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Turismo;

public class AccesoConcesionario {

	public static Turismo consultarByCode(int codigo) {
		EntityManager conexion = ObjectDBUtil.abrirConexion();
		Turismo turismo;

		try {

			turismo = conexion.find(Turismo.class, codigo);
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return turismo;
	}

	public static List<Turismo> consultarAll() {
		List<Turismo> listaTurismo = new ArrayList<Turismo>();
		EntityManager conexion = null;

		try {
			conexion = ObjectDBUtil.abrirConexion();
			String jpql = "SELECT e FROM Turismo e";
			TypedQuery<Turismo> qry = conexion.createQuery(jpql, Turismo.class);
			listaTurismo = qry.getResultList();

		} finally {
			if (conexion != null) {
				ObjectDBUtil.cerrarConexion(conexion);
			}

		}
		return listaTurismo;
	}

	public static List<Turismo> consultarByYear(int yearMin, int yearMax) {
		List<Turismo> listaTurismo = new ArrayList<Turismo>();
		EntityManager conexion = null;

		try {
			conexion = ObjectDBUtil.abrirConexion();
			String jpql = "SELECT e FROM Turismo e WHERE e.anioFabricacion BETWEEN ?1 AND ?2";
			TypedQuery<Turismo> qry = conexion.createQuery(jpql, Turismo.class);
			qry.setParameter(1, yearMin);
			qry.setParameter(2, yearMax);			
			listaTurismo = qry.getResultList();

		} finally {
			if (conexion != null) {
				ObjectDBUtil.cerrarConexion(conexion);
			}

		}
		return listaTurismo;
	}

}
