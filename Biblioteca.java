package Proyecto_ED;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private List<Llibre> llibres;
	private List<Usuari> usuaris;

	public Biblioteca() {
		this.llibres = new ArrayList<>();
		this.usuaris = new ArrayList<>();
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

	public boolean modificarLlibre(String titolActual, String nouTitol, String nouAutor) {
		Llibre llibre = buscarLlibre(titolActual);
		if (llibre == null) {
			return false;
		}
		llibre.setTitol(nouTitol);
		llibre.setAutor(nouAutor);
		return true;
	}

	public boolean eliminarLlibre(String titol) {
		Llibre llibre = buscarLlibre(titol);
		if (llibre == null || llibre.esPrestat()) {
			return false;
		}
		return llibres.remove(llibre);
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

	public boolean modificarUsuari(String nomActual, String nouNom) {
		Usuari usuari = buscarUsuari(nomActual);
		if (usuari == null) {
			return false;
		}
		usuari.setNom(nouNom);
		return true;
	}

	public boolean eliminarUsuari(String nom) {
		Usuari usuari = buscarUsuari(nom);
		if (usuari == null || !usuari.getLlibresPrestats().isEmpty()) {
			return false;
		}
		return usuaris.remove(usuari);
	}

	public List<Llibre> getLlibres() {
		return llibres;
	}

	public List<Usuari> getUsuaris() {
		return usuaris;
	}
}
