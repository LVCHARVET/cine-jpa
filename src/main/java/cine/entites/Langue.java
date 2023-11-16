package cine.entites;

import java.util.List;

import javax.persistence.OneToMany;

public class Langue {
	int id;
	String nom;

	@OneToMany
	List<Film> films;

	public Langue(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Langue [id=" + id + ", nom=" + nom + ", films=" + films + "]";
	}

	public static Langue getLangueByNom(List<Langue> listLangue, String nomLangue) {
		for (Langue langues : listLangue) {
			if (langues.getNom().equals(nomLangue)) {
				return langues;
			}
		}
		return null;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * Setter
	 * 
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
