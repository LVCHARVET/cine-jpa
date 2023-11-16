package cine.entites;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Acteur {
	int id;
	String idImdb;
	String identite;
	LocalDate dateNaissance;
	String url;

	@ManyToMany
	@JoinTable(name = "CASTING_PRINCIPAL", joinColumns = @JoinColumn(name = "ID_ACTEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILM"))
	List<Film> films;

	@OneToMany
	List<Role> roles;

	@ManyToOne
	@JoinColumn(name = "LIEU_NAISSANCE", referencedColumnName = "NOM")
	LieuNaissance lieuNaissance;
}
