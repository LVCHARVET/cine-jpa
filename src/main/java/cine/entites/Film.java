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

	// @Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	int id;
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

}
