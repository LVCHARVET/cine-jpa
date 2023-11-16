package cine.entites;

import java.util.List;

import javax.persistence.OneToMany;

public class Pays {
	int id;
	String nom;
	String url;

	@OneToMany
	List<Film> films;

	public Pays(String nom, String url) {
		this.nom = nom;
		this.url = url;
	}

	public Pays() {
	}

	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", url=" + url + ", films=" + films + "]";
	}

	public static Pays getPaysByNom(List<Pays> listPays, String nomPays) {
		for (Pays pays : listPays) {
			if (pays.getNom().equals(nomPays)) {
				return pays;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
