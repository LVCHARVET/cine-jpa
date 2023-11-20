package cine.DAO;

import java.util.List;

import cine.entites.Acteur;
import cine.entites.Film;

/**
 * @author Louis-Valentin CHARVET
 *
 */
public interface ActeurDAO {
	Acteur getByIdentite(String identite);

	List<Acteur> getActeurByFilm(Film film);

	List<Acteur> getActeurCommunByFilm(Film film1, Film film2);
}
