package cine.DAO;

import java.util.List;

import cine.entites.Acteur;
import cine.entites.Film;

/** 
 * @author Louis-Valentin CHARVET
 *
 */
public interface ActeurDAO {

	/**
	 * Methode cherchant un acteur par son identite
	 * 
	 * @param identite
	 * @return
	 */
	Acteur getByIdentite(String identite);

	/**
	 * Methode cherchant une list d'acteur par leur film
	 * 
	 * @param film
	 * @return
	 */
	List<Acteur> getActeurByFilm(Film film);

	/**
	 * Methode cherchant une liste d'acteur par leurs films en commun
	 * 
	 * @param film1
	 * @param film2
	 * @return
	 */
	List<Acteur> getActeurCommunByFilm(Film film1, Film film2);
}
