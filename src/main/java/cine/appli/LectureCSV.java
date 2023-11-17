package cine.appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import cine.entites.Acteur;
import cine.entites.Film;
import cine.entites.Genre;
import cine.entites.Langue;
import cine.entites.LieuNaissance;
import cine.entites.Pays;
import cine.entites.Realisateur;
import cine.entites.Role;

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
	List<Film> arrayFilm = new ArrayList<>();
	List<Realisateur> arrayRealisateur = new ArrayList<>();
	List<Acteur> arrayActeur = new ArrayList<>();

	public List<String> parseStringLieuNaissance(String pathFileActeur) {

		List<String> stringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileActeur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileActeur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];
				if (!lieuNaissance.isEmpty()) {
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return stringLieuNaissance;
	}

	public List<LieuNaissance> parseLieuNaissance(String pathFileRealisateur, String pathFileActeur) {

		List<LieuNaissance> arrayLieuNaissance = new ArrayList<>();
		List<String> arrayStringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileRealisateur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileRealisateur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];

				LectureCSV lectureCsvPays = new LectureCSV();
				arrayStringLieuNaissance = lectureCsvPays.parseStringLieuNaissance(pathFileActeur);

				if (!lieuNaissance.isEmpty()) {
					if (!arrayStringLieuNaissance.contains(lieuNaissance)) {
						arrayStringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		System.out.println(arrayStringLieuNaissance.size());
		HashSet<String> triLieuNaissance = new HashSet<>(arrayStringLieuNaissance);
		System.out.println(triLieuNaissance.size());
		for (String lieuNaissances : arrayStringLieuNaissance) {
			LieuNaissance newLieuNaissance = new LieuNaissance(lieuNaissances);
			arrayLieuNaissance.add(newLieuNaissance);
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

	public List<Acteur> parseActeur(String pathFileActeur, String pathFileRealisateur,
			List<LieuNaissance> lieuNiassances) {

		List<Acteur> arrayActeur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileActeur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileActeur);
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

				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(lieuNiassances, lieuNaissance);

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

	public List<Realisateur> parseRealisateur(String pathFileRealisateur, String pathFileActeur) {

		List<Realisateur> arrayRealisateur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileRealisateur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileRealisateur);
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
					arrayLieuNaissanceRealisateur = lectureCsvLieuNaissance.parseLieuNaissance(pathFileRealisateur,
							pathFileActeur);
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

	public List<String> parseFilmActeur(String pathFile, String pathFileFilm, String pathFilPays, String pathFileActeur,
			String pathFileRealisateur) {

		List<String> arrayFilmActeur = new ArrayList<>();
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
				String idIdbmFilm = tokens[0];
				String idIdbmActeur = tokens[1];

				if (arrayFilm.isEmpty()) {
					LectureCSV lectureCsvFilm = new LectureCSV();
					arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, pathFilPays);
				}

				if (arrayActeur.isEmpty()) {
					LectureCSV lectureCsvActeur = new LectureCSV();
					arrayActeur = lectureCsvActeur.parseActeur(pathFileActeur, pathFileRealisateur);
				}

				Film actuelFilm = Film.getFilmByIdbm(arrayFilm, idIdbmFilm);

				Acteur actuelActeur = Acteur.getActeurByIdbm(arrayActeur, idIdbmActeur);

				if (actuelFilm != null && actuelActeur != null) {
					actuelFilm.getActeurs().add(actuelActeur);
					actuelActeur.getFilms().add(actuelFilm);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilmActeur;
	}

	public List<String> parseFilmRealisateur(String pathFile, String pathFileFilm, String pathFilPays,
			String pathFileRealisateur, String pathFileActeur) {

		List<String> arrayFilmRealisateur = new ArrayList<>();
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
				String idIdbmFilm = tokens[0];
				String idIdbmRealisateur = tokens[1];

				if (arrayFilm.isEmpty()) {
					LectureCSV lectureCsvFilm = new LectureCSV();
					arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, pathFilPays);
				}

				if (arrayRealisateur.isEmpty()) {
					LectureCSV lectureCsvRealisateur = new LectureCSV();
					arrayRealisateur = lectureCsvRealisateur.parseRealisateur(pathFileRealisateur, pathFileActeur);
				}

				Film actuelFilm = Film.getFilmByIdbm(arrayFilm, idIdbmFilm);

				Realisateur actuelRealisateur = Realisateur.getRealisateurByIdbm(arrayRealisateur, idIdbmRealisateur);

				actuelFilm.getRealisateurs().add(actuelRealisateur);
				actuelRealisateur.getFilms().add(actuelFilm);

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilmRealisateur;
	}

	public List<Role> parseRole(String pathFile, String pathFileFilm, String pathFilPays, String pathFileActeur,
			String pathFileRealisateur) {

		List<Role> arrayRole = new ArrayList<>();
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

				String idIdmbFilm = tokens[0];
				String idIdmbActeur = tokens[1];
				String personnage = tokens[2];

				if (arrayFilm.isEmpty()) {
					LectureCSV lectureCsvFilm = new LectureCSV();
					arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm, pathFilPays);
				}

				if (arrayActeur.isEmpty()) {
					LectureCSV lectureCsvActeur = new LectureCSV();
					arrayActeur = lectureCsvActeur.parseActeur(pathFileActeur, pathFileRealisateur);
				}

				Film actuelFilm = Film.getFilmByIdbm(arrayFilm, idIdmbFilm);
				Acteur actuelActeur = Acteur.getActeurByIdbm(arrayActeur, idIdmbActeur);

				Role actuelRole = new Role(personnage);
				actuelRole.setFilm(actuelFilm);
				actuelRole.setActeur(actuelActeur);

				arrayRole.add(actuelRole);
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRole;
	}
}
