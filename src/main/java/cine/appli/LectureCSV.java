package cine.appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cine.entites.Acteur;
import cine.entites.Film;
import cine.entites.Genre;
import cine.entites.Langue;
import cine.entites.LieuNaissance;
import cine.entites.Pays;
import cine.entites.Realisateur;

/**
 * @author Louis-Valentin CHARVET
 *
 */
public class LectureCSV {

	List<LieuNaissance> arrayLieuNaissanceActeur = new ArrayList<>();
	List<LieuNaissance> arrayLieuNaissanceRealisateur = new ArrayList<>();
	List<Genre> arrayGenre = new ArrayList<>();
	List<Langue> arrayLangue = new ArrayList<>();
	List<Pays> arrayPays = new ArrayList<>();

	public List<LieuNaissance> parseLieuNaissance(String pathFile) {

		List<LieuNaissance> arrayLieuNaissance = new ArrayList<>();
		List<String> stringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3].trim();
				if (!lieuNaissance.isEmpty()) {
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
						LieuNaissance newLieuNaissance = new LieuNaissance(lieuNaissance);
						arrayLieuNaissance.add(newLieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayLieuNaissance;
	}

	public List<Pays> parsePays(String pathFile) {

		List<Pays> arrayPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String nom = tokens[0];
				String url = tokens[1];

				Pays actuelPays = new Pays(nom, url);

				arrayPays.add(actuelPays);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayPays;
	}

	public List<Genre> parseGenre(String pathFile) {

		List<Genre> arrayGenre = new ArrayList<>();
		List<String> stringGenre = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineGenre = tokens[6];
				String[] arrayLineGenre = lineGenre.split(",");
				for (String lineGenres : arrayLineGenre) {
					if (!lineGenres.isEmpty()) {
						if (!stringGenre.contains(lineGenres)) {
							stringGenre.add(lineGenres);
							Genre newGenre = new Genre(lineGenres);
							arrayGenre.add(newGenre);
						}
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayGenre;
	}

	public List<Langue> parseLangue(String pathFile) {

		List<Langue> arrayLangue = new ArrayList<>();
		List<String> stringLangue = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineLangue = tokens[7];
				if (!lineLangue.isEmpty()) {
					if (!stringLangue.contains(lineLangue)) {
						stringLangue.add(lineLangue);
						Langue newLangue = new Langue(lineLangue);
						arrayLangue.add(newLangue);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayLangue;
	}

	public List<Acteur> parseActeur(String pathFile) {

		List<Acteur> arrayActeur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];
				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					if (!tokens[2].contains("-")) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						dateNaissance = LocalDate.parse(tokens[2], formatter);
					}
				}
				String lieuNaissance = tokens[3].trim();
				String url = tokens[4];

				if (arrayLieuNaissanceActeur.isEmpty()) {
					LectureCSV lectureCsvLieuNaissance = new LectureCSV();
					arrayLieuNaissanceActeur = lectureCsvLieuNaissance.parseLieuNaissance(pathFile);
				}
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(arrayLieuNaissanceActeur,
						lieuNaissance);

				Acteur actuelActeur = new Acteur(idImdb, identite, dateNaissance, url);
				actuelActeur.setLieuNaissance(actuelLieuNaissance);

				arrayActeur.add(actuelActeur);
			}
		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayActeur;
	}

	public List<Realisateur> parseRealisateur(String pathFile) {

		List<Realisateur> arrayRealisateur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];
				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
						dateNaissance = LocalDate.parse(tokens[2], formatter);
					} catch (Exception e) {
//                        System.out.println("La chaîne n'est pas au format de date désiré.");
					}
				}
				String lieuNaissance = tokens[3].trim();
				String url = tokens[4];

				if (arrayLieuNaissanceRealisateur.isEmpty()) {
					LectureCSV lectureCsvLieuNaissance = new LectureCSV();
					arrayLieuNaissanceRealisateur = lectureCsvLieuNaissance.parseLieuNaissance(pathFile);
				}
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(arrayLieuNaissanceRealisateur,
						lieuNaissance);

				Realisateur actuelRealisateur = new Realisateur(idImdb, identite, dateNaissance, url);
				actuelRealisateur.setLieuNaissance(actuelLieuNaissance);

				arrayRealisateur.add(actuelRealisateur);
			}
		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRealisateur;
	}

	public List<Film> parseFilm(String pathFileFilm, String pathFilePays) {

		List<Film> arrayFilms = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileFilm);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileFilm);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				if (tokens.length > 10) {
					tokens[8] = tokens[8] + tokens[9];
					tokens[9] = tokens[10];
				}

				String pays = null;

				if (tokens.length == 9) {

					pays = "Pas de pays";

				} else {

					pays = tokens[9];
				}

				String idImdb = tokens[0];
				String nom = tokens[1];
				String annee = tokens[2];
				String rating = tokens[3];
				String url = tokens[4];
				String lieuTournage = tokens[5];
				String ligneGenre = tokens[6];
				String langue = tokens[7];
				String resume = tokens[8];

				if (arrayPays.isEmpty()) {
					LectureCSV lectureCsvPays = new LectureCSV();
					arrayPays = lectureCsvPays.parsePays(pathFilePays);
				}
				Pays actuelPays = Pays.getPaysByNom(arrayPays, pays);

				if (arrayLangue.isEmpty()) {
					LectureCSV lectureCsvLangue = new LectureCSV();
					arrayLangue = lectureCsvLangue.parseLangue(pathFileFilm);
				}
				Langue actuelLangue = Langue.getLangueByNom(arrayLangue, langue);

				List<Genre> arrayActuelGenre = new ArrayList<>();
				if (arrayGenre.isEmpty()) {
					LectureCSV lectureCsvGenre = new LectureCSV();
					arrayGenre = lectureCsvGenre.parseGenre(pathFileFilm);
				}
				String[] listGenre = ligneGenre.split(",");
				for (String genres : listGenre) {
					Genre actuelGenre = Genre.getGenreByNom(arrayGenre, genres);
					arrayActuelGenre.add(actuelGenre);
				}
				
				

				Film actuelFilm = new Film(idImdb, nom, annee, rating, url, lieuTournage, resume);
				actuelFilm.setLangue(actuelLangue);
				actuelFilm.setGenres(arrayActuelGenre);
				actuelFilm.setPays(actuelPays);

				arrayFilms.add(actuelFilm);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilms;
	}

}
