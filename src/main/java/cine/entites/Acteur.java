package cine.entites;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ManyToMany;

public class Acteur {
	int id;
	String idImdb;
	String identite;
	LocalDate dateNaissance;
	String url;

	@ManyToMany(mappedBy = "acteurs")
	List<Acteur> acteurs;
}
