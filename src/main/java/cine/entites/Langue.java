package cine.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Initialisation de l'entite Langue
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "LANGUE")
public class Langue {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** nom */
	@Column(name = "NOM")
	private String nom;

	/** films */
	@OneToMany(mappedBy = "langue")
	private List<Film> films;

	/**
	 * Constructeur jpa
	 * 
	 */
	public Langue() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 */
	public Langue(String nom) {
		this.nom = nom;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Langue [id=" + id + ", nom=" + nom + "]";
	}

	/**
	 * Methode de recherche d'une langue dans une liste de langue en fonction de son
	 * nom
	 * 
	 * @param listLangue
	 * @param nomLangue
	 * @return
	 */
	public static Langue getLangueByNom(List<Langue> listLangue, String nomLangue) {
		for (Langue langues : listLangue) {
			if (langues.getNom().equals(nomLangue)) {
				return langues;
			}
		}
		return null;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * Setter
	 * 
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
