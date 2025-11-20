package sn.ucad.lacgaa.emploi_du_temps.enums;

public enum TypeFiliere {

    MAGA("Master Analyse Géométrie Algébrique"),
    TDSI("Transmission de Données et Sécurité de l’Information"),
    MS2E("Master Sécurité des Systèmes Embarqués");

    private final String libelle;

    TypeFiliere(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }

}
