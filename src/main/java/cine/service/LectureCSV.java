package cine.service;

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
import cine.entites.Role;

/**
 * Class permetant le stockage des methode servant à lire et gérer les fichier
 * CSV
 * 
 * @author Louis-Valentin CHARVET
 *
 */
public class LectureCSV {

	/**
	 * Methode pour lire la premiére liste de lieu de naissance contenu dans le
	 * fichier CSV Acteur
	 * 
	 * @param pathFileActeur
	 * @return
	 */
	public List<String> parseStringLieuNaissance(String pathFileActeur) {

		// Initialisation de la liste de retour
		List<String> stringLieuNaissance = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileActeur);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileActeur);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];

				// Gestion des exceptions de saisie pour le nom du lieu de naissance, tri et
				// ajout à la liste de renvois
				lieuNaissance = lieuNaissance.replaceAll("é", "e");
				lieuNaissance = lieuNaissance.replaceAll("è", "e");
				lieuNaissance = lieuNaissance.replaceAll("Î", "I");
				lieuNaissance = lieuNaissance.trim();
				if (!lieuNaissance.isEmpty()) {
					lieuNaissance = lieuNaissance.substring(0, 1).toUpperCase() + lieuNaissance.substring(1);
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
					}
				}

			}

		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour du resultat
		return stringLieuNaissance;
	}

	/**
	 * Methode pour lire la deuxiéme liste de lieu de naissance contenu dans le
	 * fichier CSV Realisateur
	 * 
	 * @param pathFileRealisateur
	 * @param pathFileActeur
	 * @return
	 */
	public List<LieuNaissance> parseLieuNaissance(String pathFileRealisateur, String pathFileActeur) {

		// Initialisation de la liste de retour et reception
		List<LieuNaissance> arrayLieuNaissance = new ArrayList<>();
		List<String> arrayStringLieuNaissance = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileRealisateur);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileRealisateur);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];

				// Récupération de la premiére ligne parse
				LectureCSV lectureCsvPays = new LectureCSV();
				arrayStringLieuNaissance = lectureCsvPays.parseStringLieuNaissance(pathFileActeur);

				// Gestion des exceptions de saisie pour le nom du lieu de naissance, tri et
				// ajout à la liste de renvois
				lieuNaissance = lieuNaissance.replaceAll("é", "e");
				lieuNaissance = lieuNaissance.replaceAll("è", "e");
				lieuNaissance = lieuNaissance.replaceAll("Î", "I");
				lieuNaissance = lieuNaissance.trim();
				if (!lieuNaissance.isEmpty()) {
					lieuNaissance = lieuNaissance.substring(0, 1).toUpperCase() + lieuNaissance.substring(1);
					if (!arrayStringLieuNaissance.contains(lieuNaissance)) {
						arrayStringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Transformation de la liste de string en liste de LieuNaissance
		for (String lieuNaissances : arrayStringLieuNaissance) {
			LieuNaissance newLieuNaissance = new LieuNaissance(lieuNaissances);
			arrayLieuNaissance.add(newLieuNaissance);
		}

		// Retour de la liste
		return arrayLieuNaissance;
	}

	/**
	 * Methode pour lire le fichier CSV des pays et renvoyer une liste de pays
	 * 
	 * @param pathFile
	 * @return
	 */
	public List<Pays> parsePays(String pathFile) {

		// Initialisation de la liste de retour et reception
		List<Pays> arrayPays = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String nom = tokens[0];
				String url = tokens[1];

				// Création du pays contenu dans cette ligne
				Pays actuelPays = new Pays(nom, url);

				// Ajout a la liste de renvois
				arrayPays.add(actuelPays);
			}

		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayPays;
	}

	/**
	 * Methode pour lire le fichier CSV des genres et renvoyer une liste de Genre
	 * 
	 * @param pathFile
	 * @return
	 */
	public List<Genre> parseGenre(String pathFile) {

		// Initialisation de la liste de retour et reception
		List<Genre> arrayGenre = new ArrayList<>();
		List<String> stringGenre = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String lineGenre = tokens[6];

				// Séparation des différent genre contenu dans le même tokens
				String[] arrayLineGenre = lineGenre.split(",");

				// Gestion, création et ajout a la liste des genres
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

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayGenre;
	}

	/**
	 * Methode pour lire le fichier CSV des langues et renvoyer une liste de langue
	 * 
	 * @param pathFile
	 * @return
	 */
	public List<Langue> parseLangue(String pathFile) {

		// Initialisation de la liste de retour et reception
		List<Langue> arrayLangue = new ArrayList<>();
		List<String> stringLangue = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String lineLangue = tokens[7];

				// Gestion, création et ajout a la liste des langues
				if (!lineLangue.isEmpty()) {
					if (!stringLangue.contains(lineLangue)) {
						stringLangue.add(lineLangue);
						Langue newLangue = new Langue(lineLangue);
						arrayLangue.add(newLangue);
					}
				}
			}
		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayLangue;
	}

	/**
	 * Methode pour lire le fichier CSV des acteurs et renvoyer une liste d'Acteur
	 * 
	 * @param pathFileActeur
	 * @param pathLieuNaissances
	 * @return
	 */
	public List<Acteur> parseActeur(String pathFileActeur, List<LieuNaissance> pathLieuNaissances) {

		// Initialisation de la liste de retour et reception
		List<Acteur> arrayActeur = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileActeur);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileActeur);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];

				// Gestion des exceptions de dates de naissances saisies
				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					if (!tokens[2].contains("-")) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						dateNaissance = LocalDate.parse(tokens[2], formatter);
					}
				}
				String lieuNaissance = tokens[3].trim();
				String url = tokens[4];

				// Appel de la methode de recherche du lieu de naissance
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(pathLieuNaissances,
						lieuNaissance);

				// Création de l'Acteur
				Acteur actuelActeur = new Acteur(idImdb, identite, dateNaissance, url);
				actuelActeur.setLieuNaissance(actuelLieuNaissance);

				// Ajout a la liste d'Acteur
				arrayActeur.add(actuelActeur);
			}
		} catch (

		IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayActeur;
	}

	/**
	 * Methode pour lire le fichier CSV des réalisateur et renvoyer une liste de
	 * Realisateur
	 * 
	 * @param pathFileRealisateur
	 * @param pathLieuNaissances
	 * @return
	 */
	public List<Realisateur> parseRealisateur(String pathFileRealisateur, List<LieuNaissance> pathLieuNaissances) {

		// Initialisation de la liste de retour et reception
		List<Realisateur> arrayRealisateur = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileRealisateur);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileRealisateur);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];
				LocalDate dateNaissance = null;

				// Gestion des exceptions de dates de naissances saisies
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

				// Appel de la methode de recherche du lieu de naissance
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(pathLieuNaissances,
						lieuNaissance);

				// Création du Realisateur
				Realisateur actuelRealisateur = new Realisateur(idImdb, identite, dateNaissance, url);
				actuelRealisateur.setLieuNaissance(actuelLieuNaissance);

				// Ajout a la liste
				arrayRealisateur.add(actuelRealisateur);
			}
		} catch (

		IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Renvois de la liste
		return arrayRealisateur;
	}

	/**
	 * Methode pour lire le fichier CSV des réalisateur et renvoyer une liste de
	 * Film
	 * 
	 * @param pathFileFilm
	 * @param pathPays
	 * @param pathLangue
	 * @param pathGenre
	 * @return
	 */
	public List<Film> parseFilm(String pathFileFilm, List<Pays> pathPays, List<Langue> pathLangue,
			List<Genre> pathGenre) {

		// Initialisation de la liste de retour et reception
		List<Film> arrayFilms = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileFilm);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileFilm);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");

				// Gestion des erreurs de saisie du résumé
				if (tokens.length > 10) {
					tokens[8] = tokens[8] + tokens[9];
					tokens[9] = tokens[10];
				}

				// Lecture du pays et destion des exception de pays vide
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

				// Appel de la methode de recherche du pays par le nom
				Pays actuelPays = Pays.getPaysByNom(pathPays, pays);

				// Appel de la methode de recherche de la langue par le nom
				Langue actuelLangue = Langue.getLangueByNom(pathLangue, langue);

				// Séparation, lecture et appel de la methode de recherche des genres par le nom
				List<Genre> arrayActuelGenre = new ArrayList<>();
				String[] listGenre = ligneGenre.split(",");
				for (String genres : listGenre) {
					Genre actuelGenre = Genre.getGenreByNom(pathGenre, genres);
					arrayActuelGenre.add(actuelGenre);
				}

				// Création du film et ajout du pays, de la langue et des genres
				Film actuelFilm = new Film(idImdb, nom, annee, rating, url, lieuTournage, resume);
				actuelFilm.setLangue(actuelLangue);
				actuelFilm.setGenres(arrayActuelGenre);
				actuelFilm.setPays(actuelPays);

				// Ajout du film a la liste
				arrayFilms.add(actuelFilm);
			}

		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayFilms;
	}

	/**
	 * Methode pour lire le fichier CSV des liens entre les acteurs et les films
	 * 
	 * @param pathFile
	 * @param pathFilm
	 * @param pathActeur
	 */
	public void parseFilmActeur(String pathFile, List<Film> pathFilm, List<Acteur> pathActeur) {

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String idIdbmFilm = tokens[0];
				String idIdbmActeur = tokens[1];

				// Appel de la methode de recherche d'un Film dans une liste par son ID IDBM
				Film actuelFilm = Film.getFilmByIdbm(pathFilm, idIdbmFilm);

				// Appel de la methode de recherche d'un Acteur dans une liste par son ID IDBM
				Acteur actuelActeur = Acteur.getActeurByIdbm(pathActeur, idIdbmActeur);

				// Gestion des exceptions et ajout a du film dans l'acteur et de l'acteur dans
				// le film
				if (actuelFilm != null && actuelActeur != null) {
					actuelFilm.getActeurs().add(actuelActeur);
					actuelActeur.getFilms().add(actuelFilm);
				}
			}
		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}

	/**
	 * Methode pour lire le fichier CSV des liens entre les réalisateurs et les
	 * films
	 * 
	 * @param pathFile
	 * @param pathFilm
	 * @param pathRealisateur
	 */
	public void parseFilmRealisateur(String pathFile, List<Film> pathFilm, List<Realisateur> pathRealisateur) {

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();

			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";");
				String idIdbmFilm = tokens[0];
				String idIdbmRealisateur = tokens[1];

				// Appel de la methode de recherche d'un Film dans une liste par son ID IDBM
				Film actuelFilm = Film.getFilmByIdbm(pathFilm, idIdbmFilm);

				// Appel de la methode de recherche d'un Realistauer dans une liste par son ID
				// IDBM
				Realisateur actuelRealisateur = Realisateur.getRealisateurByIdbm(pathRealisateur, idIdbmRealisateur);

				// Gestion des exceptions et ajout a du film dans le réalisateur et du
				// réalisateur dans
				// le film
				if (actuelFilm != null && actuelRealisateur != null) {
					actuelFilm.getRealisateurs().add(actuelRealisateur);
					actuelRealisateur.getFilms().add(actuelFilm);
				}

			}
		} catch (IOException e) {

			// Gestion des erreurs
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}

	/**
	 * Methode pour lire le fichier CSV des rolesn, de renvoyer une liste de Role
	 * 
	 * @param pathFile
	 * @param pathFilm
	 * @param pathActeur
	 * @return
	 */
	public List<Role> parseRole(String pathFile, List<Film> pathFilm, List<Acteur> pathActeur) {

		// Initialisation de la liste de retour et reception
		List<Role> arrayRole = new ArrayList<>();

		// Chargement du fichier CSV
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFile);

		// Gestion des exceptions
		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFile);
		}

		// Lecture du fichier
		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {

			// Sauter la premiére ligne du tableau
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {

				// Séparation des lignes en tokens
				String[] tokens = line.split(";", -1);

				String idIdmbFilm = tokens[0];
				String idIdmbActeur = tokens[1];
				String personnage = tokens[2];

				// Appel de la methode de recherche d'un Film dans une liste par son ID IDBM
				Film actuelFilm = Film.getFilmByIdbm(pathFilm, idIdmbFilm);

				// Appel de la methode de recherche d'un Acteur dans une liste par son ID IDBM
				Acteur actuelActeur = Acteur.getActeurByIdbm(pathActeur, idIdmbActeur);

				// Création du role
				Role actuelRole = new Role(personnage);

				// Gestion des exceptions, ajout de la relation film / acteur et ajout a la
				// liste
				if (actuelFilm != null && actuelActeur != null) {
					actuelRole.setFilm(actuelFilm);
					actuelRole.setActeur(actuelActeur);

					arrayRole.add(actuelRole);
				}

			}

		} catch (

		IOException e) {

			// Gestion des exceptions
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}

		// Retour de la liste
		return arrayRole;
	}
}
