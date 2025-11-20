package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeCours {

    COURS_MAGISTRAL("CM"),
    TRAVAUX_DIRIGES("TD");

    private final String libelle;

    TypeCours(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return this.libelle;
    }

    @JsonCreator
    public static TypeCours fromLibelle(String libelle) {
        for (TypeCours typeCours : TypeCours.values()) {
            if (typeCours.getLibelle().equalsIgnoreCase(libelle)) {
                return typeCours;
            }
        }
        throw new IllegalArgumentException("TypeCours non valide : " + libelle);
    }
}
