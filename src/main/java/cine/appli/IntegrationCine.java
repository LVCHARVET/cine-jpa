package cine.appli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import cine.entites.Acteur;
import cine.entites.Film;
import cine.entites.Genre;
import cine.entites.Langue;
import cine.entites.LieuNaissance;
import cine.entites.Realisateur;

public class IntegrationCine {

	public static void main(String[] args) throws IOException {
		// Chemin vers le fichier csv
//		String pathFile = "films.csv";
//		LectureCSV lectureCsv = new LectureCSV();
//		List<Genre> arrayGenre = lectureCsv.parseGenre(pathFile);
//		for (Genre genres : arrayGenre) {
//			System.out.println(genres);
//		}

//		String pathFile2 = "films.csv";
//		LectureCSV lectureCsv2 = new LectureCSV();
//		List<Langue> arrayLangue = lectureCsv2.parseLangue(pathFile2);
//		for (Langue langues : arrayLangue) {
//			System.out.println(langues);
//		}
//
//		String pathFile3 = "acteurs.csv";
//		LectureCSV lectureCsv3 = new LectureCSV();
//		List<LieuNaissance> arrayLieuNaissance = lectureCsv3.parseLieuNaissance(pathFile3);
//		for (LieuNaissance lieuNaissances : arrayLieuNaissance) {
//			System.out.println(lieuNaissances);
//		}

//		String pathFileFilm = "films.csv";
//		String pathFilePays = "Pays.csv";
//		LectureCSV lectureCsv = new LectureCSV();
//		List<Film> arrayFilm = lectureCsv.parseFilm(pathFileFilm, pathFilePays);
//		for (Film films : arrayFilm) {
//			System.out.println(films);
//		}

//		String pathFileActeur = "acteurs.csv";
//		LectureCSV lectureCsv = new LectureCSV();
//		List<Acteur> arrayActeur = lectureCsv.parseActeur(pathFileActeur);
//		for (Acteur acteurs : arrayActeur) {
//			System.out.println(acteurs);
//		}

		String pathFileRealisateur = "realisateurs.csv";
		LectureCSV lectureCsvRealisateur = new LectureCSV();
		List<Realisateur> arrayRealisateur = lectureCsvRealisateur.parseRealisateur(pathFileRealisateur);
		for (Realisateur realisateurs : arrayRealisateur) {
			System.out.println(realisateurs);
		}
	}
}
