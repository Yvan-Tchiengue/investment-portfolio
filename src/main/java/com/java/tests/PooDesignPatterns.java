package com.java.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Système de gestion de bibliothèque
    Ce système gère une bibliothèque avec :

    Livre : représente un livre avec un titre et un auteur.
    Utilisateur : représente une personne qui peut emprunter des livres.
    Emprunt : représente un emprunt de livre par un utilisateur.
    Bibliotheque : gère l'ajout, l'emprunt et le retour des livres.
 */
public class PooDesignPatterns {

	class Livre {
	    String titre;
	    String auteur;
	    boolean disponible = true;

	    public Livre(String titre, String auteur) {
	        this.titre = titre;
	        this.auteur = auteur;
	    }
	}

	class Utilisateur {
	    String nom;
	    
	    public Utilisateur(String nom) {
	        this.nom = nom;
	    }
	}

	class Bibliotheque {
	    private List<Livre> livres = new ArrayList<>();
	    private Map<Livre, Utilisateur> emprunts = new HashMap<>();

	    public void ajouterLivre(Livre livre) {
	        livres.add(livre);
	    }

	    public boolean emprunterLivre(Livre livre, Utilisateur utilisateur) {
	        if (livre.disponible) {
	            emprunts.put(livre, utilisateur);
	            livre.disponible = false;
	            return true;
	        }
	        return false;
	    }

	    public void retournerLivre(Livre livre) {
	        if (emprunts.containsKey(livre)) {
	            emprunts.remove(livre);
	            livre.disponible = true;
	        }
	    }
	}

}
