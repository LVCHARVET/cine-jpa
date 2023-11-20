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
	List<Film> getFilmByActeur(Acteur acteur);

	Film getFilmByNom(String nom);

	List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2);

	List<Film> getFilmByAnnee(String debut, String fin);

	Film getByName(String nom);

	List<Film> getFilmByAnneeActeur(String debut, String fin, Acteur acteur);
}
