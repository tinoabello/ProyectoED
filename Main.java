package Proyecto_ED;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Biblioteca biblioteca = new Biblioteca();
		GestorBiblioteca gestor = new GestorBiblioteca();

		carregarDadesInicials(biblioteca);

		int opcio;
		do {
			mostrarMenu();
			opcio = llegirEnter(scanner, "Escull una opcio: ");

			switch (opcio) {
				case 1:
					afegirLlibre(scanner, biblioteca);
					break;
				case 2:
					modificarLlibre(scanner, biblioteca);
					break;
				case 3:
					eliminarLlibre(scanner, biblioteca);
					break;
				case 4:
					llistarLlibres(biblioteca);
					break;
				case 5:
					cercarLlibre(scanner, biblioteca);
					break;
				case 6:
					afegirUsuari(scanner, biblioteca);
					break;
				case 7:
					modificarUsuari(scanner, biblioteca);
					break;
				case 8:
					eliminarUsuari(scanner, biblioteca);
					break;
				case 9:
					llistarUsuaris(biblioteca);
					break;
				case 10:
					cercarUsuari(scanner, biblioteca);
					break;
				case 11:
					prestarLlibre(scanner, biblioteca, gestor);
					break;
				case 12:
					retornarLlibre(scanner, biblioteca, gestor);
					break;
				case 13:
					consultarDisponibilitat(scanner, biblioteca, gestor);
					break;
				case 14:
					mostrarHistorialUsuari(scanner, biblioteca, gestor);
					break;
				case 15:
					mostrarEstadistiques(biblioteca, gestor);
					break;
				case 0:
					System.out.println("Sortint del programa...");
					break;
				default:
					System.out.println("Opcio incorrecta.");
			}
		} while (opcio != 0);

		scanner.close();
	}

	private static void carregarDadesInicials(Biblioteca biblioteca) {
		biblioteca.afegirLlibre(new Llibre("1984", "George Orwell"));
		biblioteca.afegirLlibre(new Llibre("El petit princep", "Antoine de Saint-Exupery"));
		biblioteca.afegirUsuari(new Usuari("Carla"));
	}

	private static void mostrarMenu() {
		System.out.println();
		System.out.println("===== GESTIO BIBLIOTECA =====");
		System.out.println("1. Afegir llibre");
		System.out.println("2. Modificar llibre");
		System.out.println("3. Eliminar llibre");
		System.out.println("4. Llistar llibres");
		System.out.println("5. Cercar llibre");
		System.out.println("6. Afegir usuari");
		System.out.println("7. Modificar usuari");
		System.out.println("8. Eliminar usuari");
		System.out.println("9. Llistar usuaris");
		System.out.println("10. Cercar usuari");
		System.out.println("11. Prestar llibre");
		System.out.println("12. Retornar llibre");
		System.out.println("13. Consultar disponibilitat");
		System.out.println("14. Historial de prestecs d'un usuari");
		System.out.println("15. Estadistiques");
		System.out.println("0. Sortir");
	}

	private static void afegirLlibre(Scanner scanner, Biblioteca biblioteca) {
		String titol = llegirText(scanner, "Titol: ");
		if (biblioteca.buscarLlibre(titol) != null) {
			System.out.println("Aquest llibre ja existeix.");
			return;
		}
		String autor = llegirText(scanner, "Autor: ");
		biblioteca.afegirLlibre(new Llibre(titol, autor));
		System.out.println("Llibre afegit correctament.");
	}

	private static void modificarLlibre(Scanner scanner, Biblioteca biblioteca) {
		String titolActual = llegirText(scanner, "Titol del llibre a modificar: ");
		String nouTitol = llegirText(scanner, "Nou titol: ");
		String nouAutor = llegirText(scanner, "Nou autor: ");
		if (biblioteca.modificarLlibre(titolActual, nouTitol, nouAutor)) {
			System.out.println("Llibre modificat correctament.");
		} else {
			System.out.println("No s'ha trobat el llibre.");
		}
	}

	private static void eliminarLlibre(Scanner scanner, Biblioteca biblioteca) {
		String titol = llegirText(scanner, "Titol del llibre a eliminar: ");
		if (biblioteca.eliminarLlibre(titol)) {
			System.out.println("Llibre eliminat correctament.");
		} else {
			System.out.println("No es pot eliminar. Potser no existeix o esta prestat.");
		}
	}

	private static void llistarLlibres(Biblioteca biblioteca) {
		if (biblioteca.getLlibres().isEmpty()) {
			System.out.println("No hi ha llibres.");
			return;
		}
		for (Llibre llibre : biblioteca.getLlibres()) {
			System.out.println("- " + llibre);
		}
	}

	private static void cercarLlibre(Scanner scanner, Biblioteca biblioteca) {
		String titol = llegirText(scanner, "Titol del llibre a cercar: ");
		Llibre llibre = biblioteca.buscarLlibre(titol);
		System.out.println(llibre != null ? llibre : "No s'ha trobat el llibre.");
	}

	private static void afegirUsuari(Scanner scanner, Biblioteca biblioteca) {
		String nom = llegirText(scanner, "Nom de l'usuari: ");
		if (biblioteca.buscarUsuari(nom) != null) {
			System.out.println("Aquest usuari ja existeix.");
			return;
		}
		biblioteca.afegirUsuari(new Usuari(nom));
		System.out.println("Usuari afegit correctament.");
	}

	private static void modificarUsuari(Scanner scanner, Biblioteca biblioteca) {
		String nomActual = llegirText(scanner, "Nom de l'usuari a modificar: ");
		String nouNom = llegirText(scanner, "Nou nom: ");
		if (biblioteca.modificarUsuari(nomActual, nouNom)) {
			System.out.println("Usuari modificat correctament.");
		} else {
			System.out.println("No s'ha trobat l'usuari.");
		}
	}

	private static void eliminarUsuari(Scanner scanner, Biblioteca biblioteca) {
		String nom = llegirText(scanner, "Nom de l'usuari a eliminar: ");
		if (biblioteca.eliminarUsuari(nom)) {
			System.out.println("Usuari eliminat correctament.");
		} else {
			System.out.println("No es pot eliminar. Potser no existeix o te llibres prestats.");
		}
	}

	private static void llistarUsuaris(Biblioteca biblioteca) {
		if (biblioteca.getUsuaris().isEmpty()) {
			System.out.println("No hi ha usuaris.");
			return;
		}
		for (Usuari usuari : biblioteca.getUsuaris()) {
			System.out.println("- " + usuari);
		}
	}

	private static void cercarUsuari(Scanner scanner, Biblioteca biblioteca) {
		String nom = llegirText(scanner, "Nom de l'usuari a cercar: ");
		Usuari usuari = biblioteca.buscarUsuari(nom);
		System.out.println(usuari != null ? usuari : "No s'ha trobat l'usuari.");
	}

	private static void prestarLlibre(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);
		Llibre llibre = demanarLlibre(scanner, biblioteca);
		String data = llegirText(scanner, "Data de prestec (dd/mm/aaaa): ");
		if (!gestor.prestarLlibre(usuari, llibre, data)) {
			System.out.println("No s'ha pogut fer el prestec.");
		}
	}

	private static void retornarLlibre(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);
		Llibre llibre = demanarLlibre(scanner, biblioteca);
		if (!gestor.retornarLlibre(usuari, llibre)) {
			System.out.println("No s'ha pogut retornar el llibre.");
		}
	}

	private static void consultarDisponibilitat(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Llibre llibre = demanarLlibre(scanner, biblioteca);
		if (llibre == null) {
			System.out.println("No s'ha trobat el llibre.");
		} else if (gestor.comprovarDisponibilitat(llibre)) {
			System.out.println("El llibre esta disponible.");
		} else {
			System.out.println("El llibre esta prestat.");
		}
	}

	private static void mostrarHistorialUsuari(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);
		if (usuari == null) {
			System.out.println("No s'ha trobat l'usuari.");
			return;
		}

		List<Prestec> historial = gestor.getHistorialUsuari(usuari);
		if (historial.isEmpty()) {
			System.out.println("Aquest usuari no te prestecs.");
			return;
		}
		for (Prestec prestec : historial) {
			System.out.println("- " + prestec);
		}
	}

	private static void mostrarEstadistiques(Biblioteca biblioteca, GestorBiblioteca gestor) {
		int llibresPrestats = 0;
		for (Llibre llibre : biblioteca.getLlibres()) {
			if (llibre.esPrestat()) {
				llibresPrestats++;
			}
		}

		System.out.println("Total llibres: " + biblioteca.getLlibres().size());
		System.out.println("Llibres disponibles: " + (biblioteca.getLlibres().size() - llibresPrestats));
		System.out.println("Llibres prestats: " + llibresPrestats);
		System.out.println("Total usuaris: " + biblioteca.getUsuaris().size());
		System.out.println("Prestecs actius: " + gestor.getPrestecs().size());
	}

	private static Usuari demanarUsuari(Scanner scanner, Biblioteca biblioteca) {
		String nom = llegirText(scanner, "Nom de l'usuari: ");
		return biblioteca.buscarUsuari(nom);
	}

	private static Llibre demanarLlibre(Scanner scanner, Biblioteca biblioteca) {
		String titol = llegirText(scanner, "Titol del llibre: ");
		return biblioteca.buscarLlibre(titol);
	}

	private static String llegirText(Scanner scanner, String missatge) {
		System.out.print(missatge);
		return scanner.nextLine().trim();
	}

	private static int llegirEnter(Scanner scanner, String missatge) {
		while (true) {
			System.out.print(missatge);
			String text = scanner.nextLine();
			try {
				return Integer.parseInt(text);
			} catch (NumberFormatException e) {
				System.out.println("Introdueix un numero valid.");
			}
		}
	}
}
