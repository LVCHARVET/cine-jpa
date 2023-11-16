package cine.entites;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Realisateur {
	int id;
	String idImdb;
	String identite;
	LocalDate dateNaissance;
	String url;
	
	@ManyToMany
	@JoinTable(name = "FILMS_REALISATEURS", joinColumns = @JoinColumn(name = "ID_REALISATEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILMS"))
	List<Film> films;
	
	@ManyToOne
	@JoinColumn(name = "LIEU_NAISSANCE", referencedColumnName = "NOM")
	LieuNaissance lieuNaissance;
}