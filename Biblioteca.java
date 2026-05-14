package Proyecto_ED.ProyectoED;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private List<Llibre> llibres;
	private List<Usuari> usuaris;

	public Biblioteca() {
		llibres = new ArrayList<>();
		usuaris = new ArrayList<>();
	}

	public void afegirLlibre(Llibre llibre) {
		llibres.add(llibre);
	}

	public Llibre buscarLlibre(String titol) {
		for (Llibre llibre : llibres) {
			if (llibre.getTitol().equalsIgnoreCase(titol)) {
				return llibre;
			}
		}
		return null;
	}

	public boolean eliminarLlibre(String titol) {
		Llibre llibre = buscarLlibre(titol);

		if (llibre == null) {
			return false;
		}

		if (llibre.esPrestat()) {
			return false;
		}

		llibres.remove(llibre);
		return true;
	}

	public void afegirUsuari(Usuari usuari) {
		usuaris.add(usuari);
	}

	public Usuari buscarUsuari(String nom) {
		for (Usuari usuari : usuaris) {
			if (usuari.getNom().equalsIgnoreCase(nom)) {
				return usuari;
			}
		}
		return null;
	}

	public boolean eliminarUsuari(String nom) {
		Usuari usuari = buscarUsuari(nom);

		if (usuari == null) {
			return false;
		}

		if (!usuari.getLlibresPrestats().isEmpty()) {
			return false;
		}

		usuaris.remove(usuari);
		return true;
	}

	public List<Llibre> getLlibres() {
		return llibres;
	}

	public List<Usuari> getUsuaris() {
		return usuaris;
	}
}
