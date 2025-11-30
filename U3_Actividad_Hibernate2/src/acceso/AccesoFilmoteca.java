package acceso;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.SelectionQuery;
import modelo.Actor;
import modelo.Pelicula;

public class AccesoFilmoteca {
	public static Pelicula consultarPeliByCode(int codigo) {
		Session sesion = null;
		Transaction tran = null;
		Pelicula peli;
		try {
			sesion = HibernateUtil.abrirSesion();
			peli = sesion.find(Pelicula.class, codigo);

		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return peli;

	}

	public static Pelicula consultarPeliByCode2(int codigo) {
		Session sesion = null;
		Transaction tran = null;
		Pelicula peli;
		try {
			sesion = HibernateUtil.abrirSesion();

			String hql = "select pe from Pelicula pe where codigo = ?1";
			SelectionQuery<Pelicula> sentencia = sesion.createSelectionQuery(hql, Pelicula.class);

			sentencia.setParameter(1, codigo);
			peli = sentencia.getSingleResultOrNull();

		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return peli;

	}

	public static List<Pelicula> consultarPeliByWord(String word) {
		Session sesion = null;
		Transaction tran = null;
		List<Pelicula> listaPelis;

		try {
			sesion = HibernateUtil.abrirSesion();

			String hql = "select pe from Pelicula pe where titulo like ?1";
			SelectionQuery<Pelicula> sentencia = sesion.createSelectionQuery(hql, Pelicula.class);
			sentencia.setParameter(1, "%" + word + "%");
			listaPelis = sentencia.getResultList();

		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return listaPelis;

	}

	public static List<Actor> consultarAuthorBynationality(String nacionalidad) {
		Session sesion = null;
		Transaction tran = null;
		List<Actor> listaActor;

		try {
			sesion = HibernateUtil.abrirSesion();

			String hql = "select ac from Actor ac where nacionalidad like ?1 " + "	order by fechaNacimiento desc";
			SelectionQuery<Actor> sentencia = sesion.createSelectionQuery(hql, Actor.class);
			sentencia.setParameter(1, nacionalidad);
			listaActor = sentencia.getResultList();

		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return listaActor;

	}

	public static List<Resultado> consultarAuthorBynationalityMap(String nacionalidad) {
		Session sesion = null;
		Transaction tran = null;
		List<Resultado> listaRes = new ArrayList<>();

		try {
			sesion = HibernateUtil.abrirSesion();

			String hql = "select ac from Actor ac where nacionalidad like ?1 " + "	order by fechaNacimiento desc";
			SelectionQuery<Object[]> sentencia = sesion.createSelectionQuery(hql, Object[].class);
			sentencia.setParameter(1, nacionalidad);
			List<Object[]> lista = sentencia.getResultList();
			for (Object[] objects : lista) {
				Resultado res = new Resultado();
				res.ponerCampo("Nombre", objects[0].toString());
				res.ponerCampo("FechaNacimiento", objects[1].toString());
				listaRes.add(res);
			}

		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return listaRes;

	}

	public static boolean insertarActor(Actor actor) {
		Session sesion = null;
		Transaction tran = null;
		boolean saved = false;

		try {
			sesion = HibernateUtil.abrirSesion();
			tran = sesion.beginTransaction();
			sesion.persist(actor);
			tran.commit();
			saved = true;
		} catch (HibernateException he) {
			if (tran != null && tran.isActive()) {
				tran.rollback();
			}
			throw he;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return saved;

	}

	public static boolean actualizarPeliBycode(int code, Pelicula peli) {
		boolean updated = false;
		Session sesion = null;
		Transaction tran = null;

		sesion = HibernateUtil.abrirSesion();

		try {
			tran = sesion.beginTransaction();
			String hql = "update Pelicula as pel "
					+ "set titulo = ?1, director = ?2, anioEstreno = ?3, duracion = ?4, recaudacion = ?5, puntuacion = ?6 "
					+ "where codigo = ?7";
			MutationQuery qry = sesion.createMutationQuery(hql);
			qry.setParameter(1, peli.getTitulo());
			qry.setParameter(2, peli.getDirector());
			qry.setParameter(3, peli.getAnioEstreno());
			qry.setParameter(4, peli.getDuracion());
			qry.setParameter(5, peli.getRecaudacion());
			qry.setParameter(6, peli.getPuntuacion());
			qry.setParameter(7, code);

			qry.executeUpdate();

			tran.commit();

			updated = true;
		} catch (HibernateException he) {
			if (tran != null && tran.isActive()) {
				tran.rollback();
			}
			throw he;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}

		return updated;
	}

	public static int eliminarPeliByRanking(double rank1, double rank2) {
		int elim;
		Session sesion = null;
		Transaction tran = null;

		try {
			sesion = HibernateUtil.abrirSesion();
			tran = sesion.beginTransaction();
			String hql = "delete from Pelicula where puntuacion BETWEEN ?1 and ?2";
			MutationQuery qry = sesion.createMutationQuery(hql);
			qry.setParameter(1, rank1);
			qry.setParameter(2, rank2);

			elim = qry.executeUpdate();

			tran.commit();
		} catch (HibernateException he) {
			if (tran != null && tran.isActive()) {
				tran.rollback();
			}
			throw he;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return elim;
	}

}
