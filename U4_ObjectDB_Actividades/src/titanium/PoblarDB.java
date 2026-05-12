package titanium;

import java.sql.Date;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import modelo.Cliente;
import modelo.Domicilio;
import modelo.Turismo;
import modelo.Venta;

public class PoblarDB {
	private static final String FICHERO_BD = "data/concesionario.odb";

	// Helper method to generate a SQL Date
	private static Date generarFecha(int anio, int mes, int dia) {
		String cadena = String.format("%04d-%02d-%02d", anio, mes, dia);
		return Date.valueOf(cadena);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Define the location and name of the ObjectDB file

		// 1. Create Data Objects
		Domicilio dom1 = new Domicilio("Calle Mayor", 15, 28001, "Madrid");
		Domicilio dom2 = new Domicilio("Avenida de la Constitución", 45, 82, "Barcelona");

		Cliente cliente1 = new Cliente(1, "Ana García", generarFecha(1990, 5, 15), dom1, "600111222",
				"ana.garcia@email.com");
		Cliente cliente2 = new Cliente(2, "Juan Pérez", generarFecha(1985, 11, 20), dom2, "600333444",
				"juan.perez@email.com");

		Turismo turismo1 = new Turismo(10, "Seat", "Ibiza", 2022, 5, 355, 18500.00);
		Turismo turismo2 = new Turismo(20, "Ford", "Focus", 2021, 5, 375, 22150.50);
		Turismo turismo3 = new Turismo(30, "Audi", "A4", 2023, 5, 480, 45000.00);
		Turismo turismo1 = new Turismo(1, "Seat", "Ibiza", 2022, 5, 355, 18500.00);
		Turismo turismo2 = new Turismo(2, "Ford", "Focus", 2021, 5, 375, 22150.50);
		Turismo turismo3 = new Turismo(3, "Audi", "A4", 2023, 5, 480, 45000.00);

		Date fechaVenta1 = generarFecha(2024, 3, 1);
		Venta venta1 = new Venta(cliente1, turismo1, fechaVenta1, turismo1.getPrecio(), "Tarjeta", "M1234ABC");

		Date fechaVenta2 = generarFecha(2024, 3, 5);
		Venta venta2 = new Venta(cliente2, turismo2, fechaVenta2, turismo2.getPrecio() * 0.95, "Transferencia",
				"B5678DEF");

		Date fechaVenta3 = generarFecha(2024, 3, 10);
		Venta venta3 = new Venta(cliente1, turismo3, fechaVenta3, turismo3.getPrecio(), "Tarjeta", "C9101GHI");

		// Establish bidirectional relationships before persisting
		cliente1.insertar(venta1);
		turismo1.insertar(venta1);

		cliente2.insertar(venta2);
		turismo2.insertar(venta2);

		cliente1.insertar(venta3);
		turismo3.insertar(venta3);

		// 2. Persist Data
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory(FICHERO_BD);
		EntityManager conexion = null;
		EntityTransaction transaccion = null;

		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();

			// Persist Clients and Cars first
			conexion.persist(cliente1);
			conexion.persist(cliente2);
			conexion.persist(turismo1);
			conexion.persist(turismo2);
			conexion.persist(turismo3);

			// Persist Sales (Venta)
			conexion.persist(venta1);
			conexion.persist(venta2);
			conexion.persist(venta3);

			// Since Cliente and Turismo lists were modified, merge them
			conexion.merge(cliente1);
			conexion.merge(cliente2);
			conexion.merge(turismo1);
			conexion.merge(turismo2);
			conexion.merge(turismo3);

			transaccion.commit();
		} catch (PersistenceException excepcion) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			// Propagate the exception after rolling back
			System.err.println("❌ Error during persistence: " + excepcion.getMessage());
			throw excepcion;
		} finally {
			if (conexion != null) {
				conexion.close();
			}
			if (fabricaConexiones != null) {
				fabricaConexiones.close();
			}
		}

		System.out
				.println("✅ Se ha creado la base de datos 'concesionario.odb' con 2 clientes, 3 turismos y 3 ventas.");
	}
}