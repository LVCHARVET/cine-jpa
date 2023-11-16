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

	public LieuNaissance(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "LieuNaissance [nom=" + nom + "]";
	}

}
