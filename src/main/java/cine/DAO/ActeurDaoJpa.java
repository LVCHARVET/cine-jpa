/**
 * 
 */
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
public class ActeurDaoJpa implements ActeurDAO {
	/**
	 * Initialisation d'une connection
	 */
	private EntityManager em;

	/**
	 * Constructeur
	 * 
	 * @param em
	 */
	public ActeurDaoJpa(EntityManager em) {
		this.em = em;
	}

	@Override
	public Acteur getByIdentite(String identite) {
		// Requete Typé pour trouver la Categorie
		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :identite", Acteur.class);

		query.setParameter("identite", identite);

		// Stockage du résultat
		List<Acteur> acteurs = query.getResultList();

		// Verification du resultat et retour
		if (acteurs.isEmpty()) {
			return null;
		} else {
			return acteurs.get(0);
		}
	}

	@Override
	public List<Acteur> getActeurByFilm(Film film) {
		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a JOIN a.films f WHERE f = :film",
				Acteur.class);

		query.setParameter("film", film);

		List<Acteur> listActeurByFilm = query.getResultList();
		return listActeurByFilm;
	}

	@Override
	public List<Acteur> getActeurCommunByFilm(Film film1, Film film2) {
		TypedQuery<Acteur> query = em.createQuery(
				"SELECT a FROM Acteur a JOIN a.roles r1 JOIN a.roles r2 WHERE r1.film = :film1 AND r2.film = :film2",
				Acteur.class);

		query.setParameter("film1", film1);
		query.setParameter("film2", film2);

		List<Acteur> listActeurCommunByFilm = query.getResultList();

		return listActeurCommunByFilm;
	}

}
