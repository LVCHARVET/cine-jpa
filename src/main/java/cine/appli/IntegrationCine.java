package cine.appli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import cine.entites.Film;

public class IntegrationCine {

	public static void main(String[] args) throws IOException {
		// Chemin vers le fichier csv
		String pathFile = "films.csv";
		LectureCSV lectureCsv = new LectureCSV();
		List<Film> arrayFilm = lectureCsv.parseFilm(pathFile);
		for (Film films : arrayFilm) {
			System.out.println(films);
		}

	}
}
