package sn.ucad.lacgaa.emploi_du_temps.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Option {

    PROFESSIONNEL("Professionnel"),
    RECHERCHE("Recherche");

    private final String libelle;

    Option(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return this.libelle;
    }

    @JsonCreator
    public static Option fromLibelle(String libelle) {
        for (Option option : Option.values()) {
            if (option.getLibelle().equalsIgnoreCase(libelle)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Option non valide : " + libelle);
    }
}
