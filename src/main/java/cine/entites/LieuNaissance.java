package cine.entites;

import java.util.List;

import javax.persistence.OneToMany;

public class LieuNaissance {
	int id;
	String nom;
	
	@OneToMany
	List<Realisateur> realisateurs;
	
	@OneToMany
	List<Acteur> acteurs;
}
