package cine.appli;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cine.entites.Acteur;
import cine.entites.Film;
import cine.entites.Genre;
import cine.entites.Langue;
import cine.entites.LieuNaissance;
import cine.entites.Pays;
import cine.entites.Realisateur;
import cine.entites.Role;
import cine.service.LectureCSV;

/**
 * Classe permetant la lecture et l'écrivage en base de donné des fichier CSV
 * contenu dans le dossier resources
 * 
 * @author Louis-Valentin CHARVET
 *
 */
public class IntegrationCine {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Connexion a la DB
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cine");
		EntityManager em = emf.createEntityManager();

		// Chemin vers les fichiers csv
		String pathFileFilm = "films.csv";
		String pathFilePays = "Pays.csv";
		String pathFileRealisateur = "realisateurs.csv";
		String pathFileActeur = "acteurs.csv";
		String pathFileFilmRealisateur = "film_realisateurs.csv";
		String pathFileFilmActeur = "castingPrincipal.csv";
		String pathRole = "roles.csv";

		// initialisation de la transaction
		em.getTransaction().begin();

		// Appel de la methode pour lire le fichier CSV et mise en DB de pays
		LectureCSV lectureCsvPays = new LectureCSV();
		List<Pays> arrayPays = lectureCsvPays.parsePays(pathFilePays);
		for (Pays pays : arrayPays) {
			em.persist(pays);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB de langue
		LectureCSV lectureCsvLangue = new LectureCSV();
		List<Langue> arrayLangue = lectureCsvLangue.parseLangue(pathFileFilm);
		for (Langue langues : arrayLangue) {
			em.persist(langues);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB de genre
		LectureCSV lectureCsvGenre = new LectureCSV();
		List<Genre> arrayGenre = lectureCsvGenre.parseGenre(pathFileFilm);
		for (Genre genres : arrayGenre) {
			em.persist(genres);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB des lieu de
		// naissance
		LectureCSV lectureCsvLieuNaissance = new LectureCSV();
		List<LieuNaissance> arrayLieuNaissance = lectureCsvLieuNaissance.parseLieuNaissance(pathFileRealisateur,
				pathFileActeur);
		for (LieuNaissance lieuNaissances : arrayLieuNaissance) {
			em.persist(lieuNaissances);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB des films
		LectureCSV lectureCsvFilm = new LectureCSV();
		List<Film> arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, arrayPays, arrayLangue, arrayGenre);
		for (Film films : arrayFilm) {
			em.persist(films);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB des acteurs
		LectureCSV lectureCsvActeur = new LectureCSV();
		List<Acteur> arrayActeur = lectureCsvActeur.parseActeur(pathFileActeur, arrayLieuNaissance);
		for (Acteur acteurs : arrayActeur) {
			em.persist(acteurs);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB des réalisateurs
		LectureCSV lectureCsvRealisateur = new LectureCSV();
		List<Realisateur> arrayRealisateur = lectureCsvRealisateur.parseRealisateur(pathFileRealisateur,
				arrayLieuNaissance);
		for (Realisateur realisateurs : arrayRealisateur) {
			em.persist(realisateurs);
		}

		// Appel de la methode pour lire le fichier CSV et mise en DB de la relation
		// film / acteur
		LectureCSV lectureCsvFilmActeur = new LectureCSV();
		lectureCsvFilmActeur.parseFilmActeur(pathFileFilmActeur, arrayFilm, arrayActeur);

		// Appel de la methode pour lire le fichier CSV et mise en DB de la relation
		// film / réalisateur
		LectureCSV lectureCsvFilmRealisateur = new LectureCSV();
		lectureCsvFilmRealisateur.parseFilmRealisateur(pathFileFilmRealisateur, arrayFilm, arrayRealisateur);

		// Appel de la methode pour lire le fichier CSV et mise en DB des roles
		LectureCSV lectureCsvRole = new LectureCSV();
		List<Role> arrayRole = lectureCsvRole.parseRole(pathRole, arrayFilm, arrayActeur);
		for (Role roles : arrayRole) {
			em.persist(roles);
		}

		// fermeture de la transaction
		em.getTransaction().commit();

		// Fermeture de la connection a la BDD
		em.close();
		emf.close();
	}
}
