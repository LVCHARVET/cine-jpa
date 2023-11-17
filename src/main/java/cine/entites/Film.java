package cine.entites;

import java.util.List;

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

@Entity
@Table(name = "FILM")
public class Film {
	/**
	 * Constructeur
	 * 
	 * @param idImdb
	 * @param nom2
	 * @param annee2
	 * @param rating2
	 * @param url2
	 * @param lieuTournage2
	 * @param resume2
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String idImbd;
	String nom;
	String annee;
	String rating;
	String url;
	String lieuTournage;

	@ManyToMany
	@JoinTable(name = "FILMS_GENRES", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_GENRE"))
	List<Genre> genres;

	@ManyToOne
	@JoinColumn(name = "LANGUE", referencedColumnName = "NOM")
	Langue langue;

	String resume;

	@ManyToOne
	@JoinColumn(name = "PAYS", referencedColumnName = "NOM")
	Pays pays;

	@ManyToMany
	@JoinTable(name = "CASTING_PRINCIPAL", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR"))
	List<Acteur> acteurs;

	@OneToMany
	List<Role> roles;

	@ManyToMany
	@JoinTable(name = "FILMS_REALISATEURS", joinColumns = @JoinColumn(name = "ID_FILM"), inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR"))
	List<Realisateur> realisateurs;

	public Film() {
	}

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

	@Override
	public String toString() {
		return "Film [idImbd=" + idImbd + ", nom=" + nom + ", annee=" + annee + ", rating=" + rating + ", url=" + url
				+ ", lieuTournage=" + lieuTournage + ", genres=" + genres + ", langue=" + langue + ", resume=" + resume
				+ ", pays=" + pays + ", acteurs=" + acteurs + ", roles=" + roles + ", realisateurs=" + realisateurs
				+ "]";
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
