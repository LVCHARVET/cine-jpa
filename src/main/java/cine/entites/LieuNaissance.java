package cine.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Initialisation de l'entite LieuNaissance
 * 
 * @author Louis-Valentin CHARVET
 *
 */
@Entity
@Table(name = "LIEU_NAISSANCE")
public class LieuNaissance {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** nom */
	@Column(name = "NOM")
	private String nom;

	/** realisateurs */
	@OneToMany(mappedBy = "lieuNaissance")
	private List<Realisateur> realisateurs;

	/** acteurs */
	@OneToMany(mappedBy = "lieuNaissance")
	private List<Acteur> acteurs;

	/**
	 * Constructeur jpa
	 * 
	 */
	public LieuNaissance() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 */
	public LieuNaissance(String nom) {
		this.nom = nom;
	}

	/**
	 * Methode to string pour afficher sans erreur stackoverflow
	 */
	@Override
	public String toString() {
		return "LieuNaissance [id=" + id + ", nom=" + nom + "]";
	}

	/**
	 * Methode de recherche d'un lieu de naissence dans une liste de lieu de
	 * naissence en fonction de son nom
	 * 
	 * @param listLieuNaissance
	 * @param nomLieuNaissance
	 * @return
	 */
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
