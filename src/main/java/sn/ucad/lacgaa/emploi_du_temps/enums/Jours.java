package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Jours {

    LUNDI("Lundi"),
    MARDI("Mardi"),
    MERCREDI("Mercredi"),
    JEUDI("Jeudi"),
    VENDREDI("Vendredi"),
    SAMEDI("Samedi");

    private final String libelle;

    Jours(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return this.libelle;
    }

    @JsonCreator
    public static Jours fromLibelle(String libelle) {
        for (Jours jours : Jours.values()) {
            if (jours.getLibelle().equalsIgnoreCase(libelle)) {
                return jours;
            }
        }
        throw new IllegalArgumentException("Jours non valide : " + libelle);
    }
}
