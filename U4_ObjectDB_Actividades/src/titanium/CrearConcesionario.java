package titanium;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modelo.Cliente;
import modelo.Domicilio;
import modelo.Turismo;
import modelo.Venta;

public class CrearConcesionario {

    private static final String DB_URL = "data/concesionario.odb";

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // -----------------------------------------------------------
        // 1) Crear 10 turismos
        // -----------------------------------------------------------
        List<Turismo> turismos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Turismo t = new Turismo();
            t.setFabricante("Marca" + i);
            t.setModelo("Modelo" + i);
            t.setAnioFabricacion(2010 + i);
            t.setNumeroPlazas(4 + (i % 2));
            t.setCapacidadMaletero(300 + i * 10);
            t.setPrecio(15000 + i * 1000);
            em.persist(t);
            turismos.add(t);
        }

        // -----------------------------------------------------------
        // 2) Crear 10 clientes
        // -----------------------------------------------------------
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {

            Cliente c = new Cliente();
            c.setNombre("Cliente " + i);

            // Fecha de nacimiento: YYYY-MM-DD
            Date fechaNac = Date.valueOf((1980 + i) + "-" + ((i % 12) + 1) + "-" + ((i % 28) + 1));
            c.setFechaNacimiento(fechaNac);

            Domicilio d = new Domicilio();
            d.setVia("Calle " + i);
            d.setNumero(10 + i);
            d.setCodigoPostal(28000 + i);
            d.setLocalidad("Ciudad " + i);
            c.setDomicilioResidencia(d);

            c.setTelefono("60000000" + i);
            c.setCorreo("cliente" + i + "@correo.com");

            em.persist(c);
            clientes.add(c);
        }

        // -----------------------------------------------------------
        // 3) Crear 20 ventas
        // -----------------------------------------------------------
        em.getTransaction().commit();
        em.getTransaction().begin();

        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {

            Cliente cliente = clientes.get(rnd.nextInt(clientes.size()));
            Turismo turismo = turismos.get(rnd.nextInt(turismos.size()));

            // Fecha aleatoria entre 2020 y 2024
            int year = 2020 + rnd.nextInt(5);
            int month = 1 + rnd.nextInt(12);
            int day = 1 + rnd.nextInt(28);

            Date fechaVenta = Date.valueOf(year + "-" + month + "-" + day);

            Venta venta = new Venta();
            venta.setCliente(cliente);
            venta.setTurismo(turismo);
            venta.setFecha(fechaVenta);
            venta.setImporte(turismo.getPrecio());
            venta.setMetodoPago(i % 2 == 0 ? "Tarjeta" : "Transferencia");
            venta.setMatricula("ABC" + (100 + i));

            em.persist(venta);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

        System.out.println("Base de datos creada con 10 turismos, 10 clientes y 20 ventas.");
    }
}
