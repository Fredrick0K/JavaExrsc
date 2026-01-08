package acceso;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import modelo.Cliente;
import modelo.Domicilio;
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

	public static boolean insertarTurismo(Turismo turismo) {
		boolean inserted = false;
		EntityManager conexion = null;
		EntityTransaction tran = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			tran = conexion.getTransaction();
			tran.begin();
			conexion.persist(turismo);
			tran.commit();
			inserted = true;
		} catch (Exception e) {
			if (tran != null && tran.isActive()) {
				tran.rollback();
			}
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return inserted;
	}

	// By Object String fechaNacim, Domicilio domicilio, String telefono, String
	// correo
	public static boolean actualizarClienteByCode(int code) {
		boolean updated = false;
		EntityManager conexion = null;
		EntityTransaction tran = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			tran = conexion.getTransaction();
			tran.begin();
			Cliente cliente = conexion.find(Cliente.class, code);
			if(cliente == null){
				System.out.println("No se ha encontrado cliente con este codigo.");
			}else{
				
			}

			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tran != null && tran.isActive()) {
				tran.rollback();
			}

		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}

		return updated;
	}

}
