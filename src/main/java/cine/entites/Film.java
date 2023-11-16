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
import javax.persistence.Table;

@Entity
@Table(name = "FILM")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String idImbd;
	String nom;
	String annee;
	double rating;
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
	@JoinTable(name = "ROLE", joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID_IMDB"), inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR", referencedColumnName = "ID_IMDB"))
	List<Acteur> acteurs;

}
