package ProjecteBiblio.ProyectoED;

import java.util.ArrayList;
import java.util.List;

public class Usuari {
	private String nom;
	private List<Llibre> llibresPrestats;

	public Usuari(String nom) {
		this.nom = nom;
		this.llibresPrestats = new ArrayList<>();
	}

	public String getNom() {
		return nom;
	}

	public List<Llibre> getLlibresPrestats() {
		return llibresPrestats;
	}

	public void afegirLlibre(Llibre llibre) {
		llibresPrestats.add(llibre);
	}

	public void retornarLlibre(Llibre llibre) {
		llibresPrestats.remove(llibre);
	}

	@Override
	public String toString() {
		return "Usuari: " + nom + " | Llibres actuals: " + llibresPrestats.size();
	}
}
