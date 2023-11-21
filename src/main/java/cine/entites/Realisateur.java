package cine.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Initialisation de l'entite Realisateur
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "REALISATEUR")
public class Realisateur {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** idImdb */
	@Column(name = "ID_IMDB")
	private String idImdb;

	/** identite */
	@Column(name = "IDENTITE")
	private String identite;

	/** dateNaissance */
	@Column(name = "DATE_NAISSANCE")
	private LocalDate dateNaissance;

	/** url */
	@Column(name = "URL")
	private String url;

	/** films */
	@ManyToMany
	@JoinTable(name = "FILMS_REALISATEURS", joinColumns = @JoinColumn(name = "ID_REALISATEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILM"))
	private List<Film> films = new ArrayList<>();

	/** lieuNaissance */
	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	private LieuNaissance lieuNaissance;

	/**
	 * Constructeur jpa
	 * 
	 */
	public Realisateur() {
	}

	/**
	 * Constructeur
	 * 
	 * @param idImdb
	 * @param identite
	 */
	public Realisateur(String idImdb, String identite) {
		this.idImdb = idImdb;
		this.identite = identite;
	}

	/**
	 * Constructeur
	 * 
	 * @param idImdb
	 * @param identite
	 * @param dateNaissance
	 * @param url
	 */
	public Realisateur(String idImdb, String identite, LocalDate dateNaissance, String url) {
		this.idImdb = idImdb;
		this.identite = identite;
		this.dateNaissance = dateNaissance;
		this.url = url;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Realisateur [id=" + id + ", idImdb=" + idImdb + ", identite=" + identite + ", dateNaissance="
				+ dateNaissance + ", url=" + url + "]";
	}

	/**
	 * Methode de recherche d'un realisateur dans une liste de realisateur en
	 * fonction de son nom
	 * 
	 * @param listRealisateur
	 * @param nomRealisateur
	 * @return
	 */
	public static Realisateur getRealisateurByIdbm(List<Realisateur> listRealisateur, String nomRealisateur) {
		for (Realisateur realisateurs : listRealisateur) {
			if (realisateurs.getIdImdb().equals(nomRealisateur)) {
				return realisateurs;
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
	 * @return the idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**
	 * Setter
	 * 
	 * @param idImdb the idImdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/**
	 * Getter
	 * 
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}

	/**
	 * Setter
	 * 
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	/**
	 * Getter
	 * 
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	/**
	 * Getter
	 * 
	 * @return the lieuNaissance
	 */
	public LieuNaissance getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(LieuNaissance lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

}