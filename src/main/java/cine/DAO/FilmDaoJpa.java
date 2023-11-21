package cine.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cine.entites.Acteur;
import cine.entites.Film;

/**
 * @author Louis-Valentin CHARVET
 *
 */
public class FilmDaoJpa implements FilmDAO {

	/**
	 * Initialisation d'une connection
	 */
	private EntityManager em;

	/**
	 * Constructeur
	 * 
	 * @param em
	 */
	public FilmDaoJpa(EntityManager em) {
		this.em = em;
	}

	@Override
	public Film getFilmByNom(String nom) {
		// Requete Typé pour trouver le film
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom = :nom", Film.class);

		query.setParameter("nom", nom);

		// Stockage du résultat
		List<Film> films = query.getResultList();

		// Verification du resultat et retour
		if (films.isEmpty()) {
			return null;
		} else {
			return films.get(0);
		}
	}

	@Override
	public List<Film> getFilmByActeur(Acteur acteur) {
		// Requete Typé pour trouver la liste de film
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a = :acteur", Film.class);

		query.setParameter("acteur", acteur);

		// Stockage du résultat
		List<Film> listFilmByActeur = query.getResultList();

		// Retour du resultat
		return listFilmByActeur;
	}

	@Override
	public List<Film> getFilmByAnnee(String debut, String fin) {

		// Requete Typé pour trouver la liste de film
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.annee BETWEEN :debut AND :fin",
				Film.class);

		query.setParameter("debut", debut);
		query.setParameter("fin", fin);

		// Stockage du résultat
		List<Film> filmsByAnnee = query.getResultList();

		// Retour du resultat
		return filmsByAnnee;
	}

	@Override
	public List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2) {

		// Requete Typé pour trouver la liste de film
		TypedQuery<Film> query = em.createQuery(
				"SELECT f FROM Film f JOIN f.roles r1 JOIN f.roles r2 WHERE r1.acteur = :acteur1 AND r2.acteur = :acteur2",
				Film.class);

		query.setParameter("acteur1", acteur1);
		query.setParameter("acteur2", acteur2);

		// Stockage du résultat
		List<Film> listFilmByActeurCommun = query.getResultList();

		// Retour du resultat
		return listFilmByActeurCommun;
	}

	@Override
	public List<Film> getFilmByAnneeActeur(String debut, String fin, Acteur acteur) {

		// Requete Typé pour trouver la liste de film
		TypedQuery<Film> query = em.createQuery(
				"SELECT DISTINCT f FROM Film f JOIN f.roles r WHERE f.annee BETWEEN :debut AND :fin AND r.acteur = :acteur",
				Film.class);
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		query.setParameter("acteur", acteur);

		// Stockage du résultat
		List<Film> filmsByAnneeActeur = query.getResultList();

		// Retour du resultat
		return filmsByAnneeActeur;
	}

}
