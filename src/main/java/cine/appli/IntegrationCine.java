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

		LectureCSV lectureCsvPays = new LectureCSV();
		List<Pays> arrayPays = lectureCsvPays.parsePays(pathFilePays);
		for (Pays pays : arrayPays) {
			em.persist(pays);
		}

		LectureCSV lectureCsvLangue = new LectureCSV();
		List<Langue> arrayLangue = lectureCsvLangue.parseLangue(pathFileFilm);
		for (Langue langues : arrayLangue) {
			em.persist(langues);
		}

		LectureCSV lectureCsvGenre = new LectureCSV();
		List<Genre> arrayGenre = lectureCsvGenre.parseGenre(pathFileFilm);
		for (Genre genres : arrayGenre) {
			em.persist(genres);
		}

		LectureCSV lectureCsvLieuNaissance = new LectureCSV();
		List<LieuNaissance> arrayLieuNaissance = lectureCsvLieuNaissance.parseLieuNaissance(pathFileRealisateur,
				pathFileActeur);
		for (LieuNaissance lieuNaissances : arrayLieuNaissance) {
			em.persist(lieuNaissances);
		}

		LectureCSV lectureCsvFilm = new LectureCSV();
		List<Film> arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, arrayPays, arrayLangue, arrayGenre);
		for (Film films : arrayFilm) {
			em.persist(films);
		}

		LectureCSV lectureCsvActeur = new LectureCSV();
		List<Acteur> arrayActeur = lectureCsvActeur.parseActeur(pathFileActeur, pathFileRealisateur,
				arrayLieuNaissance);
		for (Acteur acteurs : arrayActeur) {
			em.persist(acteurs);
		}

		LectureCSV lectureCsvRealisateur = new LectureCSV();
		List<Realisateur> arrayRealisateur = lectureCsvRealisateur.parseRealisateur(pathFileRealisateur, pathFileActeur,
				arrayLieuNaissance);
		for (Realisateur realisateurs : arrayRealisateur) {
			em.persist(realisateurs);
		}

		LectureCSV lectureCsvFilmActeur = new LectureCSV();
		lectureCsvFilmActeur.parseFilmActeur(pathFileFilmActeur, arrayFilm, arrayActeur);

		LectureCSV lectureCsvFilmRealisateur = new LectureCSV();
		lectureCsvFilmRealisateur.parseFilmRealisateur(pathFileFilmRealisateur, arrayFilm, arrayRealisateur);

		LectureCSV lectureCsvRole = new LectureCSV();
		List<Role> arrayRole = lectureCsvRole.parseRole(pathRole, arrayFilm, arrayActeur);
		for (Role roles : arrayRole) {
			em.persist(roles);
		}
		em.getTransaction().commit();

		// Fermeture de la connection a la BDD
		em.close();
		emf.close();
	}
}
