package cine.appli;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cine.DAO.ActeurDAO;
import cine.DAO.ActeurDaoJpa;
import cine.DAO.FilmDAO;
import cine.DAO.FilmDaoJpa;
import cine.entites.Acteur;
import cine.entites.Film;

/**
 * @author Louis-Valentin CHARVET
 *
 */
public class MenuAffichageCine {
	public static void main(String[] args) {

		// Ouverture du scanner du menu
		Scanner scanner = new Scanner(System.in);
		int choix = 0;

		// Connection a la BDD et défénition des DAO
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cine");
		EntityManager em = emf.createEntityManager();
		FilmDAO filmDAO = new FilmDaoJpa(em);
		ActeurDAO acteurDAO = new ActeurDaoJpa(em);

		// Affichage du menu et gestion des réponses utilisateurs
		while (choix != 7) {
			System.out.println("Menu :");
			System.out.println("1. Affichez la filmographie d’un acteur donné");
			System.out.println("2. Affichez du casting d’un film donné");
			System.out.println("3. Affichez les films sortis entre 2 années données");
			System.out.println("4. Affichez les des films communs à 2 acteurs/actrices donnés");
			System.out.println("5. Affichez les acteurs communs à 2 films donnés");
			System.out.println(
					"6. Affichez les films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
			System.out.println("7. Sortir");
			System.out.print("Veuillez choisir une option : ");
			choix = scanner.nextInt();

			switch (choix) {

			// Recherche sur les 10 produits les mieux notés d'une catégorie
			case 1:

				// Prise de la categorie saisie
				Scanner scannerActeur = new Scanner(System.in);
				System.out.println("1. La filmographie d’un acteur donné :");
				System.out.print("Nom de l'acteur : ");
				String nomActeur = scannerActeur.nextLine();
				Acteur acteur = acteurDAO.getByIdentite(nomActeur);

				// Appel de la requete permettant la recherche contenu dans le DAO
				List<Film> listFilmByActeur = filmDAO.getFilmByActeur(acteur);

				// Affichage de la réponse
				System.out.println("La filmographie de " + acteur.getIdentite() + " :");
				for (Film films : listFilmByActeur) {
					System.out.println(films.getNom());
				}

				break;
			case 2:

				// Prise de la marque saisie
				Scanner scannerFilm = new Scanner(System.in);
				System.out.println("1. Casting d’un film donné :");
				System.out.print("Nom du film : ");
				String nomFilm = scannerFilm.nextLine();
				Film film = filmDAO.getByName(nomFilm);

				// Appel de la requete permettant la recherche contenu dans le DAO
				List<Acteur> listActeurByFilm = acteurDAO.getActeurByFilm(film);

				// Affichage de la réponse
				System.out.println("Le casting d’un film donné : " + film.getNom() + " :");
				for (Acteur acteurs : listActeurByFilm) {
					System.out.println(acteurs.getIdentite());
				}

				break;
			case 3:

				// Prise de la categorie saisie
				Scanner scannerAnneDebut = new Scanner(System.in);
				System.out.println("1. Affichez les films sortis entre 2 années données :");
				System.out.print("Année de début : ");
				String anneDebut = scannerAnneDebut.nextLine();
				Scanner scannerAnneFin = new Scanner(System.in);
				System.out.print("Année de fin : ");
				String anneFin = scannerAnneFin.nextLine();

				List<Film> filmByAnnee = filmDAO.getFilmByAnnee(anneDebut, anneFin);

				// Affichage de la réponse
				for (Film films : filmByAnnee) {
					System.out.println(films.getNom());
				}
				break;
			case 4:
				// Prise de la categorie saisie
				Scanner scannerActeur1 = new Scanner(System.in);
				System.out.println("1. La filmographie de deux acteurs donné en commun :");
				System.out.print("Nom de l'acteur 1 : ");
				String nomActeur1 = scannerActeur1.nextLine();
				Scanner scannerActeur2 = new Scanner(System.in);
				System.out.print("Nom de l'acteur 2 : ");
				String nomActeur2 = scannerActeur2.nextLine();
				Acteur acteur1 = acteurDAO.getByIdentite(nomActeur1);
				Acteur acteur2 = acteurDAO.getByIdentite(nomActeur2);

				// Appel de la requete permettant la recherche contenu dans le DAO
				List<Film> listFilmByActeurCommun = filmDAO.getFilmByActeurCommun(acteur1, acteur2);

				// Affichage de la réponse
				System.out.println("La filmographie commune de l'acteur : " + acteur1.getIdentite()
						+ " et de l'acteur : " + acteur2.getIdentite() + " est :");
				for (Film films : listFilmByActeurCommun) {
					System.out.println(films.getNom());
				}
				break;
			case 5:

				// Prise de la categorie saisie
				Scanner scannerFilm1 = new Scanner(System.in);
				System.out.println("1. les acteurs communs à 2 films donnés :");
				System.out.print("Nom du film 1 : ");
				String nomFilm1 = scannerFilm1.nextLine();
				Scanner scannerFilm2 = new Scanner(System.in);
				System.out.print("Nom du film 2 : ");
				String nomFilm2 = scannerFilm2.nextLine();
				Film film1 = filmDAO.getByName(nomFilm1);
				Film film2 = filmDAO.getByName(nomFilm2);

				// Appel de la requete permettant la recherche contenu dans le DAO
				List<Acteur> listActeurCommunByFilm = acteurDAO.getActeurCommunByFilm(film1, film2);

				// Affichage de la réponse
				System.out.println("Les acteurs commun au film : " + film1.getNom() + " et du film : " + film2.getNom()
						+ " est :");
				for (Acteur acteurs : listActeurCommunByFilm) {
					System.out.println(acteurs.getIdentite());
				}
				break;
			case 6:

				// Prise de la marque saisie
				Scanner scannerFilmByAnnee1 = new Scanner(System.in);
				System.out.println(
						"1. les films sortis entre 2 années données et qui ont un acteur/actrice donné au casting :");
				System.out.print("Année de début : ");
				String anneDebutFilm1 = scannerFilmByAnnee1.nextLine();
				Scanner scannerFilmByAnnee2 = new Scanner(System.in);
				System.out.print("Année de fin : ");
				String anneFinFilm2 = scannerFilmByAnnee2.nextLine();
				Scanner scannerFilmByActeur = new Scanner(System.in);
				System.out.print("Acteur : ");
				String annefilmByActeur = scannerFilmByActeur.nextLine();
				Acteur acteurByFilm = acteurDAO.getByIdentite(annefilmByActeur);

				List<Film> listFilmByAnneeActeur = filmDAO.getFilmByAnneeActeur(anneDebutFilm1, anneFinFilm2,
						acteurByFilm);

				// Affichage de la réponse
				System.out.println("Les films sortis entre l'année : " + anneDebutFilm1 + " et " + anneFinFilm2
						+ " avec l'acteur suivant : " + acteurByFilm.getIdentite() + " sont :");
				for (Film films : listFilmByAnneeActeur) {
					System.out.println(films.getNom());
				}

				break;
			case 7:

				// Option pour quitter le Menu
				System.out.println("Au revoir !");
				break;
			default:

				// Saisi utilisateur incorrect
				System.out.println("Choix invalide. Veuillez réessayer.");
				break;
			}
		}

		// Fermeture du scanner
		scanner.close();

		// Fermeture de la connection
		emf.close();
		em.close();
	}
}
