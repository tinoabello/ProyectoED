package ProjecteBiblio.ProyectoED;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {
	private List<Prestec> prestecs;

	
	public GestorBiblioteca() {
		this.prestecs = new ArrayList<>();
	}
	//Creem el metode de prestarLlibre
	public void prestarLlibre(Usuari usuari, Llibre llibre) {
		if(!llibre.esPrestat()) {
			llibre.prestar();
			Prestec prestec = new Prestec(usuari, llibre, LocalDate.now());
			usuari.afegirLlibre(llibre);
			System.out.println(usuari.getNom() + "ha agafat el llibre" + llibre.getTitle());
		}else {
			System.out.println("El llibre: " + llibre.getTitle() + " ja està prestat");

		}
	}
