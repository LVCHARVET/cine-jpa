package cine.entites;

import java.util.List;

import javax.persistence.ManyToMany;

public class Genre {
	int id;
	String nom;

	@ManyToMany(mappedBy = "genres")
	List<Film> films;

	public Genre() {
	}

	public Genre(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + ", films=" + films + "]";
	}

	public static Genre getGenreByNom(List<Genre> listGenre, String nomGenre) {
		for (Genre genres : listGenre) {
			if (genres.getNom().equals(nomGenre)) {
				return genres;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
