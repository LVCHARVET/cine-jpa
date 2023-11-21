package cine.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Initialisation de l'entite Genre
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "GENRE")
public class Genre {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** nom */
	@Column(name = "NOM")
	private String nom;

	/** films */
	@ManyToMany(mappedBy = "genres")
	private List<Film> films;

	/**
	 * Constructeur jpa
	 * 
	 */
	public Genre() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 */
	public Genre(String nom) {
		this.nom = nom;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + "]";
	}

	/**
	 * Methode de recherche d'un genre dans une liste de genre en fonction de son
	 * nom
	 * 
	 * @param listGenre
	 * @param nomGenre
	 * @return
	 */
	public static Genre getGenreByNom(List<Genre> listGenre, String nomGenre) {
		for (Genre genres : listGenre) {
			if (genres.getNom().equals(nomGenre)) {
				return genres;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * @param films
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
