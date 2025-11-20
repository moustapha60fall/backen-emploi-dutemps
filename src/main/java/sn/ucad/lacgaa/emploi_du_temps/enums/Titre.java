package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Titre {

    PROFESSEUR("Pr."),
    DOCTEUR("Dr."),
    MONSIEUR("Mr."),
    MADAME("Mme");

    private final String libelle;

    Titre(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return libelle;
    }

    @JsonCreator
    public static Titre fromLibelle(String libelle) {
        for (Titre titre : Titre.values()) {
            if (titre.getLibelle().equalsIgnoreCase(libelle)) {
                return titre;
            }
        }
        throw new IllegalArgumentException("Titre non valide : " + libelle);
    }
}
