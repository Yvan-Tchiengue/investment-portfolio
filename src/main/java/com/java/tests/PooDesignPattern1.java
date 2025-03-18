package com.java.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de parking
   Ce système gère un parking avec :

    Vehicule : représente un véhicule avec un numéro d’immatriculation.
    PlaceParking : représente une place de parking (occupée ou non).
    Parking : gère les entrées et sorties des véhicules.
 */
public class PooDesignPattern1 {

	class Vehicule {
	    String immatriculation;

	    public Vehicule(String immatriculation) {
	        this.immatriculation = immatriculation;
	    }
	}

	class PlaceParking {
	    int numero;
	    boolean occupee = false;
	    Vehicule vehicule;

	    public PlaceParking(int numero) {
	        this.numero = numero;
	    }

	    public void occuper(Vehicule vehicule) {
	        this.vehicule = vehicule;
	        this.occupee = true;
	    }

	    public void liberer() {
	        this.vehicule = null;
	        this.occupee = false;
	    }
	}

	class Parking {
	    private List<PlaceParking> places = new ArrayList<>();

	    public Parking(int nombrePlaces) {
	        for (int i = 1; i <= nombrePlaces; i++) {
	            places.add(new PlaceParking(i));
	        }
	    }

	    public boolean entrerVehicule(Vehicule vehicule) {
	        for (PlaceParking place : places) {
	            if (!place.occupee) {
	                place.occuper(vehicule);
	                return true;
	            }
	        }
	        return false;
	    }

	    public boolean sortirVehicule(String immatriculation) {
	        for (PlaceParking place : places) {
	            if (place.occupee && place.vehicule.immatriculation.equals(immatriculation)) {
	                place.liberer();
	                return true;
	            }
	        }
	        return false;
	    }
	}

}
