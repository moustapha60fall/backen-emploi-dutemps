package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Semestre {

    SEMESTRE_1("Semestre 1"),
    SEMESTRE_2("Semestre 2");

    private final String libelle;

    Semestre(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return libelle;
    }

    @JsonCreator
    public static Semestre fromLibelle(String libelle) {
        for (Semestre semestre : Semestre.values()) {
            if (semestre.getLibelle().equalsIgnoreCase(libelle)) {
                return semestre;
            }
        }
        throw new IllegalArgumentException("Semestre non valide : " + libelle);
    }
}
