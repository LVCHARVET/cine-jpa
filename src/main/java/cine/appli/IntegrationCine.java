package cine.appli;

import java.io.IOException;
import java.util.ArrayList;
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

public class IntegrationCine {

	public static void main(String[] args) throws IOException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cine");
		EntityManager em = emf.createEntityManager();

		// Chemin vers le fichier csv
		String pathFileFilm = "films.csv";
		String pathFilePays = "Pays.csv";
		String pathFileRealisateur = "realisateurs.csv";
		String pathFileActeur = "acteurs.csv";
		String pathFileFilmRealisateur = "film_realisateurs.csv";
		String pathFileFilmActeur = "castingPrincipal.csv";
		String pathRole = "roles.csv";

		em.getTransaction().begin();

		LectureCSV lectureCsvGenre = new LectureCSV();
		List<Genre> arrayGenre = lectureCsvGenre.parseGenre(pathFileFilm);
		for (Genre genres : arrayGenre) {
			em.persist(genres);
		}

		LectureCSV lectureCsvLangue = new LectureCSV();
		List<Langue> arrayLangue = lectureCsvLangue.parseLangue(pathFileFilm);
		for (Langue langues : arrayLangue) {
			em.persist(langues);
		}

		LectureCSV lectureCsvPays = new LectureCSV();
		List<Pays> arrayPays = lectureCsvPays.parsePays(pathFileFilm);
		for (Pays pays : arrayPays) {
			em.persist(pays);
		}

		LectureCSV lectureCsvLieuNaissance = new LectureCSV();
		List<LieuNaissance> arrayLieuNaissance = lectureCsvLieuNaissance.parseLieuNaissance(pathFileRealisateur,
				pathFileActeur);
		for (LieuNaissance lieuNaissances : arrayLieuNaissance) {
			System.out.println(lieuNaissances);
			em.persist(lieuNaissances);
		}

		LectureCSV lectureCsvFilmRealisateur = new LectureCSV();
		List<String> arrayFilmRealisateur = lectureCsvFilmRealisateur.parseFilmRealisateur(pathFileFilmRealisateur,
				pathFileFilm, pathFilePays, pathFileRealisateur, pathFileActeur);

		LectureCSV lectureCsvFilmActeur = new LectureCSV();
		List<String> arrayFilmActeur = lectureCsvFilmActeur.parseFilmActeur(pathFileFilmActeur, pathFileFilm,
				pathFilePays, pathFileActeur, pathFileRealisateur);

		LectureCSV lectureCsvActeur = new LectureCSV();
		List<Acteur> arrayActeur = lectureCsvActeur.parseActeur(pathFileActeur, pathFileRealisateur,
				arrayLieuNaissance);
		for (Acteur acteurs : arrayActeur) {
			System.out.println(acteurs);
			em.persist(acteurs);
		}

		LectureCSV lectureCsvRealisateur = new LectureCSV();
		List<Realisateur> arrayRealisateur = lectureCsvRealisateur.parseRealisateur(pathFileActeur,
				pathFileRealisateur);
		for (Realisateur realisateurs : arrayRealisateur) {
			em.persist(realisateurs);
		}
		LectureCSV lectureCsvFilm = new LectureCSV();
		List<Film> arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, pathFilePays);
		for (Film films : arrayFilm) {
			em.persist(films);
		}

		LectureCSV lectureCsvRole = new LectureCSV();
		List<Role> arrayRole = lectureCsvRole.parseRole(pathRole, pathFileFilm, pathFilePays, pathFileActeur,
				pathFileRealisateur);
		for (Role roles : arrayRole) {
			em.persist(roles);
		}
		em.getTransaction().commit();

		// Fermeture de la connection a la BDD
		em.close();
		emf.close();
	}
}
