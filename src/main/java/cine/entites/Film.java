package cine.entites;

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
 * Initialisation de l'entite Film
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "FILM")
public class Film {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** idImbd */
	@Column(name = "ID_IMDB")
	private String idImbd;

	/** nom */
	@Column(name = "NOM")
	private String nom;

	/** annee */
	@Column(name = "ANNEE")
	private String annee;

	/** rating */
	@Column(name = "RATING")
	private String rating;

	/** url */
	@Column(name = "URLD")
	private String url;

	/** lieuTournage */
	@Column(name = "LIEU_TOURNAGE")
	private String lieuTournage;

	/** genres */
	@ManyToMany
	@JoinTable(name = "FILMS_GENRES", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_GENRE"))
	private List<Genre> genres;

	/** langue */
	@ManyToOne
	@JoinColumn(name = "LANGUE")
	private Langue langue;

	/** resume */
	@Column(name = "RESUME", length = 5000)
	private String resume;

	/** pays */
	@ManyToOne
	@JoinColumn(name = "ID_PAYS")
	private Pays pays;

	/** acteurs */
	@ManyToMany
	@JoinTable(name = "CASTING_PRINCIPAL", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR"))
	private List<Acteur> acteurs = new ArrayList<>();

	/** roles */
	@OneToMany(mappedBy = "film")
	private List<Role> roles = new ArrayList<>();

	/** realisateurs */
	@ManyToMany
	@JoinTable(name = "FILMS_REALISATEURS", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR"))
	private List<Realisateur> realisateurs = new ArrayList<>();

	/**
	 * Constructeur jpa
	 * 
	 */
	public Film() {
	}

	/**
	 * Constructeur
	 * 
	 * @param idImbd
	 * @param nom
	 * @param annee
	 * @param rating
	 * @param url
	 * @param lieuTournage
	 * @param resume
	 */
	public Film(String idImbd, String nom, String annee, String rating, String url, String lieuTournage,
			String resume) {
		this.idImbd = idImbd;
		this.nom = nom;
		this.annee = annee;
		this.rating = rating;
		this.url = url;
		this.lieuTournage = lieuTournage;
		this.resume = resume;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "Film [id=" + id + ", idImbd=" + idImbd + ", nom=" + nom + ", annee=" + annee + ", rating=" + rating
				+ ", url=" + url + ", lieuTournage=" + lieuTournage + "]";
	}

	/**
	 * Methode de recherche d'un film dans une liste de film en fonction de son ID
	 * IDBM
	 * 
	 * @param listFilm
	 * @param nomFilm
	 * @return
	 */
	public static Film getFilmByIdbm(List<Film> listFilm, String nomFilm) {
		for (Film films : listFilm) {
			if (films.getIdImbd().equals(nomFilm)) {
				return films;
			}
		}
		return null;
	}

	/**
	 * Getter
	 * 
	 * @return the idImbd
	 */
	public String getIdImbd() {
		return idImbd;
	}

	/**
	 * Setter
	 * 
	 * @param idImbd the idImbd to set
	 */
	public void setIdImbd(String idImbd) {
		this.idImbd = idImbd;
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
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/**
	 * Setter
	 * 
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * Getter
	 * 
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Setter
	 * 
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
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
	 * @return the lieuTournage
	 */
	public String getLieuTournage() {
		return lieuTournage;
	}

	/**
	 * Setter
	 * 
	 * @param lieuTournage the lieuTournage to set
	 */
	public void setLieuTournage(String lieuTournage) {
		this.lieuTournage = lieuTournage;
	}

	/**
	 * Getter
	 * 
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Setter
	 * 
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Getter
	 * 
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/**
	 * Setter
	 * 
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	/**
	 * Getter
	 * 
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Setter
	 * 
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * Getter
	 * 
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/**
	 * Setter
	 * 
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/**
	 * Getter
	 * 
	 * @return the acteurs
	 */
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	/**
	 * Setter
	 * 
	 * @param acteurs the acteurs to set
	 */
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
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
	 * @return the realisateurs
	 */
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/**
	 * Setter
	 * 
	 * @param realisateurs the realisateurs to set
	 */
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

}
