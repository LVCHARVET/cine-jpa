package cine.entites;

import java.util.List;

import javax.persistence.ManyToMany;

public class Genre {
	int id;
	String nom;

	@ManyToMany(mappedBy="genres")
	List<Film> films;
}
