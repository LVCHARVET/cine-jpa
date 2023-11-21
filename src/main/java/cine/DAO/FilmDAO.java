/**
 * 
 */
package cine.DAO;

import java.util.List;

import cine.entites.Acteur;
import cine.entites.Film;

/**
 * @author Louis-Valentin CHARVET
 *
 */
public interface FilmDAO {

	/**
	 * Methode cherchant un film par son nom
	 * 
	 * @param nom
	 * @return
	 */
	Film getFilmByNom(String nom);

	/**
	 * Methode cherchant une liste de film par son son acteur
	 * 
	 * @param acteur
	 * @return
	 */
	List<Film> getFilmByActeur(Acteur acteur);

	/**
	 * Methode cherchant une liste de film dans un intervale de d'annee
	 * 
	 * @param debut
	 * @param fin
	 * @return
	 */
	List<Film> getFilmByAnnee(String debut, String fin);

	/**
	 * Methode cherchant une liste de film par son leurs acteurs commun
	 * 
	 * @param acteur1
	 * @param acteur2
	 * @return
	 */
	List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2);

	/**
	 * Methode cherchant une liste de film par son son acteur et dans un interval de
	 * d'annee
	 * 
	 * @param debut
	 * @param fin
	 * @param acteur
	 * @return
	 */
	List<Film> getFilmByAnneeActeur(String debut, String fin, Acteur acteur);
}
