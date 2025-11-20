package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Niveau {
    L1("Licence 1"),
    L2("Licence 2"),
    L3("Licence 3"),
    M1("Master 1"),
    M2("Master 2");

    private final String libelle;

    Niveau(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return this.libelle;
    }

    @JsonCreator
    public static Niveau fromLibelle(String libelle) {
        for (Niveau niveau : Niveau.values()) {
            if (niveau.getLibelle().equalsIgnoreCase(libelle)) {
                return niveau;
            }
        }
        throw new IllegalArgumentException("Niveau non valide : " + libelle);
    }
}
