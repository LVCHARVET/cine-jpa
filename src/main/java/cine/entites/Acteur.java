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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Initialisation de l'entite Acteur
 * 
 * @author Louis-Valentin CHARVET
 *
 */

@Entity
@Table(name = "ACTEUR")
public class Acteur {

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
	@JoinTable(name = "CASTING_PRINCIPAL", joinColumns = @JoinColumn(name = "ID_ACTEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILM"))
	private List<Film> films = new ArrayList<>();

	/** roles */
	@OneToMany(mappedBy = "acteur")
	private List<Role> roles;

	/** lieuNaissance */
	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	private LieuNaissance lieuNaissance;

	/**
	 * Constructeur jpa
	 * 
	 */
	public Acteur() {
	}

	/**
	 * Constructeur
	 * 
	 * @param identite
	 */
	public Acteur(String identite) {
		super();
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
	public Acteur(String idImdb, String identite, LocalDate dateNaissance, String url) {
		this.idImdb = idImdb;
		this.identite = identite;
		this.dateNaissance = dateNaissance;
		this.url = url;
	}

	/**
	 * Constructeur
	 * 
	 * @param idImdb
	 * @param identite
	 */
	public Acteur(String idImdb, String identite) {
		this.idImdb = idImdb;
		this.identite = identite;
	}

	/**
	 * @param listActeur
	 * @param nomActeur
	 * @return
	 */
	public static Acteur getActeurByIdbm(List<Acteur> listActeur, String nomActeur) {
		for (Acteur acteurs : listActeur) {
			if (acteurs.getIdImdb().equals(nomActeur)) {
				return acteurs;
			}
		}
		return null;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Acteur [id=" + id + ", idImdb=" + idImdb + ", identite=" + identite + ", dateNaissance=" + dateNaissance
				+ ", url=" + url + "]";
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
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Setter
	 * 
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
