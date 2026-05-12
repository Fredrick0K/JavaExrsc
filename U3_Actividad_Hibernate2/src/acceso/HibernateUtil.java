package acceso;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		// Source - https://stackoverflow.com/a
		// Posted by acdcjunior
		// Retrieved 2025-11-26, License - CC BY-SA 3.0

		Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		try {
			// create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().configure().build()
			);
		}
		catch (Throwable ex) {
			// make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		getSessionFactory().close();
	}
	
	public static Session abrirSesion() {
		SessionFactory fabricaSesiones = getSessionFactory();
		Session sesion = fabricaSesiones.openSession();
		
		return sesion;
	}
	
	public static void cerrarSesion(Session sesion) {
		if (sesion != null && sesion.isOpen()) {
			sesion.close();
		}
	}
	
}
