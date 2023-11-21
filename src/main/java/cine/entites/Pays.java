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
 * Initialisation de l'entite Pays
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "PAYS")
public class Pays {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** nom */
	@Column(name = "NOM")
	private String nom;

	/** url */
	@Column(name = "URL")
	private String url;

	/** films */
	@OneToMany(mappedBy = "pays")
	private List<Film> films;

	/**
	 * Constructeur jpa
	 * 
	 */
	public Pays() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param url
	 */
	public Pays(String nom, String url) {
		this.nom = nom;
		this.url = url;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", url=" + url + "]";
	}

	/**
	 * Methode de recherche d'un pays dans une liste de pays en fonction de son nom
	 * 
	 * @param listPays
	 * @param nomPays
	 * @return
	 */
	public static Pays getPaysByNom(List<Pays> listPays, String nomPays) {
		for (Pays pays : listPays) {
			if (pays.getNom().equals(nomPays)) {
				return pays;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
