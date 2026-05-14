package ProjecteBiblio.ProyectoED;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Biblioteca biblioteca = new Biblioteca();
		GestorBiblioteca gestor = new GestorBiblioteca();
		int opcio;

		biblioteca.afegirLlibre(new Llibre("1984", "George Orwell"));
		biblioteca.afegirLlibre(new Llibre("El petit princep", "Antoine de Saint-Exupery"));
		biblioteca.afegirUsuari(new Usuari("Carla"));

		do {
			mostrarMenu();
			opcio = llegirEnter(scanner);

			switch (opcio) {
				case 1:
					afegirLlibre(scanner, biblioteca);
					break;
				case 2:
					eliminarLlibre(scanner, biblioteca);
					break;
				case 3:
					llistarLlibres(biblioteca);
					break;
				case 4:
					cercarLlibre(scanner, biblioteca);
					break;
				case 5:
					afegirUsuari(scanner, biblioteca);
					break;
				case 6:
					eliminarUsuari(scanner, biblioteca);
					break;
				case 7:
					llistarUsuaris(biblioteca);
					break;
				case 8:
					cercarUsuari(scanner, biblioteca);
					break;
				case 9:
					prestarLlibre(scanner, biblioteca, gestor);
					break;
				case 10:
					retornarLlibre(scanner, biblioteca, gestor);
					break;
				case 11:
					consultarDisponibilitat(scanner, biblioteca, gestor);
					break;
				case 12:
					mostrarHistorial(scanner, biblioteca, gestor);
					break;
				case 13:
					mostrarEstadistiques(biblioteca, gestor);
					break;
				case 0:
					System.out.println("Adeu!");
					break;
				default:
					System.out.println("Opcio incorrecta.");
			}
		} while (opcio != 0);

		scanner.close();
	}

	private static void mostrarMenu() {
		System.out.println();
		System.out.println("===== BIBLIOTECA =====");
		System.out.println("1. Afegir llibre");
		System.out.println("2. Eliminar llibre");
		System.out.println("3. Llistar llibres");
		System.out.println("4. Cercar llibre");
		System.out.println("5. Afegir usuari");
		System.out.println("6. Eliminar usuari");
		System.out.println("7. Llistar usuaris");
		System.out.println("8. Cercar usuari");
		System.out.println("9. Prestar llibre");
		System.out.println("10. Retornar llibre");
		System.out.println("11. Consultar disponibilitat");
		System.out.println("12. Historial d'un usuari");
		System.out.println("13. Estadistiques");
		System.out.println("0. Sortir");
		System.out.print("Opcio: ");
	}

	private static void afegirLlibre(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Titol: ");
		String titol = scanner.nextLine();

		if (biblioteca.buscarLlibre(titol) != null) {
			System.out.println("Aquest llibre ja existeix.");
			return;
		}

		System.out.print("Autor: ");
		String autor = scanner.nextLine();

		biblioteca.afegirLlibre(new Llibre(titol, autor));
		System.out.println("Llibre afegit.");
	}

	private static void eliminarLlibre(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Titol del llibre: ");
		String titol = scanner.nextLine();

		if (biblioteca.eliminarLlibre(titol)) {
			System.out.println("Llibre eliminat.");
		} else {
			System.out.println("No s'ha pogut eliminar. Potser no existeix o esta prestat.");
		}
	}

	private static void llistarLlibres(Biblioteca biblioteca) {
		if (biblioteca.getLlibres().isEmpty()) {
			System.out.println("No hi ha llibres.");
		} else {
			for (Llibre llibre : biblioteca.getLlibres()) {
				System.out.println(llibre);
			}
		}
	}

	private static void cercarLlibre(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Titol del llibre: ");
		String titol = scanner.nextLine();
		Llibre llibre = biblioteca.buscarLlibre(titol);

		if (llibre == null) {
			System.out.println("No s'ha trobat el llibre.");
		} else {
			System.out.println(llibre);
		}
	}

	private static void afegirUsuari(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Nom de l'usuari: ");
		String nom = scanner.nextLine();

		if (biblioteca.buscarUsuari(nom) != null) {
			System.out.println("Aquest usuari ja existeix.");
			return;
		}

		biblioteca.afegirUsuari(new Usuari(nom));
		System.out.println("Usuari afegit.");
	}

	private static void eliminarUsuari(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Nom de l'usuari: ");
		String nom = scanner.nextLine();

		if (biblioteca.eliminarUsuari(nom)) {
			System.out.println("Usuari eliminat.");
		} else {
			System.out.println("No s'ha pogut eliminar. Potser no existeix o te llibres prestats.");
		}
	}

	private static void llistarUsuaris(Biblioteca biblioteca) {
		if (biblioteca.getUsuaris().isEmpty()) {
			System.out.println("No hi ha usuaris.");
		} else {
			for (Usuari usuari : biblioteca.getUsuaris()) {
				System.out.println(usuari);
			}
		}
	}

	private static void cercarUsuari(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Nom de l'usuari: ");
		String nom = scanner.nextLine();
		Usuari usuari = biblioteca.buscarUsuari(nom);

		if (usuari == null) {
			System.out.println("No s'ha trobat l'usuari.");
		} else {
			System.out.println(usuari);
		}
	}

	private static void prestarLlibre(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);
		Llibre llibre = demanarLlibre(scanner, biblioteca);

		if (usuari == null || llibre == null) {
			System.out.println("Usuari o llibre no trobat.");
			return;
		}

		System.out.print("Data de prestec (dd/mm/aaaa): ");
		String data = scanner.nextLine();

		gestor.prestarLlibre(usuari, llibre, data);
	}

	private static void retornarLlibre(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);
		Llibre llibre = demanarLlibre(scanner, biblioteca);

		if (usuari == null || llibre == null) {
			System.out.println("Usuari o llibre no trobat.");
			return;
		}

		gestor.retornarLlibre(usuari, llibre);
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

	private static void mostrarHistorial(Scanner scanner, Biblioteca biblioteca, GestorBiblioteca gestor) {
		Usuari usuari = demanarUsuari(scanner, biblioteca);

		if (usuari == null) {
			System.out.println("No s'ha trobat l'usuari.");
			return;
		}

		List<Prestec> historial = gestor.getHistorialUsuari(usuari);

		if (historial.isEmpty()) {
			System.out.println("Aquest usuari no te prestecs.");
		} else {
			for (Prestec prestec : historial) {
				System.out.println(prestec);
			}
		}
	}

	private static void mostrarEstadistiques(Biblioteca biblioteca, GestorBiblioteca gestor) {
		int prestats = 0;

		for (Llibre llibre : biblioteca.getLlibres()) {
			if (llibre.esPrestat()) {
				prestats++;
			}
		}

		System.out.println("Total llibres: " + biblioteca.getLlibres().size());
		System.out.println("Llibres prestats: " + prestats);
		System.out.println("Llibres disponibles: " + (biblioteca.getLlibres().size() - prestats));
		System.out.println("Total usuaris: " + biblioteca.getUsuaris().size());
		System.out.println("Prestecs actius: " + gestor.getPrestecs().size());
	}

	private static Usuari demanarUsuari(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Nom de l'usuari: ");
		String nom = scanner.nextLine();
		return biblioteca.buscarUsuari(nom);
	}

	private static Llibre demanarLlibre(Scanner scanner, Biblioteca biblioteca) {
		System.out.print("Titol del llibre: ");
		String titol = scanner.nextLine();
		return biblioteca.buscarLlibre(titol);
	}

	private static int llegirEnter(Scanner scanner) {
		int numero = -1;

		try {
			numero = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			numero = -1;
		}

		return numero;
	}
}
