package ProjecteBiblio.ProyectoED;

import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {
	private List<Prestec> prestecs;
	private static final int MAX_LLIBRES_PER_USUARI = 3; // ***MODIFICAT*** Constant per controlar el màxim de llibres

	public GestorBiblioteca() {
		this.prestecs = new ArrayList<>();
	}

	// Creem el metode de prestarLlibre
	public void prestarLlibre(Usuari usuari, Llibre llibre, String dataPrestec) { // ***MODIFICAT*** Afegit paràmetre String dataPrestec en lloc de LocalDate.now()
		//Comprovació del límit de llibres per usuari
		if (usuari.getLlibresPrestats().size() >= MAX_LLIBRES_PER_USUARI) {
			System.out.println(usuari.getNom() + " ja te el maxim de "
					+ MAX_LLIBRES_PER_USUARI + " llibres prestats.");
			return;
		}

		if (!llibre.esPrestat()) {
			llibre.prestar();
			Prestec prestec = new Prestec(usuari, llibre, dataPrestec); // ***MODIFICAT*** Passem el String directament
			prestecs.add(prestec); //Afegim el préstec a la llista
			usuari.afegirLlibre(llibre);
			System.out.println(usuari.getNom() + " ha agafat el llibre: " + llibre.getTitol());
		} else {
			System.out.println("El llibre: " + llibre.getTitol() + " ja esta prestat.");
		}
	}

	// Nou mètode: retornar un llibre
	public void retornarLlibre(Usuari usuari, Llibre llibre) {
		llibre.retornar();
		usuari.retornarLlibre(llibre);
		prestecs.removeIf(p -> p.getLlibre().equals(llibre)
				&& p.getUsuari().equals(usuari));
		System.out.println(usuari.getNom() + " ha retornat: " + llibre.getTitol());
	}

	// Nou mètode: control d'estoc
	public void controlEstoc(String titol, Biblioteca biblioteca) {
		int total = 0;
		int disponibles = 0;
		for (Llibre ll : biblioteca.getLlibres()) {
			if (ll.getTitol().equalsIgnoreCase(titol)) {
				total++;
				if (!ll.esPrestat()) disponibles++;
			}
		}
		System.out.println("Llibre: " + titol
				+ " | Total: " + total
				+ " | Disponibles: " + disponibles);
	}

	//Nou mètode: comprovar disponibilitat
	public boolean comprovarDisponibilitat(Llibre llibre) {
		return !llibre.esPrestat();
	}

	//Nou mètode: historial de préstecs d'un usuari
	public List<Prestec> getHistorialUsuari(Usuari usuari) {
		List<Prestec> historial = new ArrayList<>();
		for (Prestec p : prestecs) {
			if (p.getUsuari().equals(usuari)) {
				historial.add(p);
			}
		}
		return historial;
	}

	//Nou mètode: getter per tots els préstecs
	public List<Prestec> getPrestecs() {
		return prestecs;
	}
}