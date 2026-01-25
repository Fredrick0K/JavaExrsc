package acceso;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Cliente;
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

	// fechaNacim, domicilio, telefono, correo
	public static boolean actualizarClienteByCode(int code, Cliente clientData) {
		boolean updated = false;
		EntityManager conexion = null;
		EntityTransaction tran = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			tran = conexion.getTransaction();
			tran.begin();
			Cliente clientFound = conexion.find(Cliente.class, code);
			if (clientFound == null) {
				System.out.println("No se ha encontrado cliente con este codigo.");
			} else {// fecha de nacimiento, domicilio, teléfono correo
				clientFound.setFechaNacimiento(clientData.getFechaNacimiento());
				clientFound.setDomicilioResidencia(clientData.getDomicilioResidencia());
				clientFound.setTelefono(clientData.getTelefono());
				clientFound.setCorreo(clientData.getCorreo());
			}
			tran.commit();
			updated = true;
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

	public static int eliminarByYear(int minYear, int maxYear) {
		int slimed = 0;
		EntityManager conexion = null;
		EntityTransaction tran = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			tran = conexion.getTransaction();
			tran.begin();
			String jpql = "delete from Turismo where anioFabricacion between ?1 and ?2"; // including params
			Query qry = conexion.createQuery(jpql);
			qry.setParameter(1, minYear);
			qry.setParameter(2, maxYear);
			slimed = qry.executeUpdate();
			tran.commit();

		}catch(Exception e){
			if(tran != null && tran.isActive()){
				tran.rollback();
			}
			e.printStackTrace();
			throw e;
		} 
		finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}

		return slimed;
	}

}
