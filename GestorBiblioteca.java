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
		
		public boolean comprovarDisponibilitat(Llibre llibre) {
		    return !llibre.esPrestat();
		}
		
		public List<Prestec> getHistorialUsuari(Usuari usuari) {
		    List<Prestec> historial = new ArrayList<>();
		    for (Prestec p : prestecs) {
		        if (p.getUsuari().equals(usuari)) {
		            historial.add(p);
		        }
		    }
		    return historial;
		
	}
}