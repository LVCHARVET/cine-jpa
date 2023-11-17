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

	public static LieuNaissance getLieuNaissanceByNom(List<LieuNaissance> listLieuNaissance, String nomLieuNaissance) {
		for (LieuNaissance lieuNaissances : listLieuNaissance) {
			if (lieuNaissances.getNom().equals(nomLieuNaissance)) {
				return lieuNaissances;
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
	 * @return the realisateurs
	 */
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/**
	 * Setter
	 * 
	 * @param realisateurs the realisateurs to set
	 */
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	/**
	 * Getter
	 * 
	 * @return the acteurs
	 */
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	/**
	 * Setter
	 * 
	 * @param acteurs the acteurs to set
	 */
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

}
