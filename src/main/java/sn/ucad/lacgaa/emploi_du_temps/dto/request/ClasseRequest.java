package sn.ucad.lacgaa.emploi_du_temps.dto.request;

import sn.ucad.lacgaa.emploi_du_temps.enums.Niveau;
import sn.ucad.lacgaa.emploi_du_temps.enums.Option;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeFiliere;

public class ClasseRequest {

    private String codeClasse;
    private String nomClasse;
    private TypeFiliere typeFiliere;
    private Niveau niveau;
    private Option option;

    public String getCodeClasse() {
        return this.codeClasse;
    }

    public String getNomClasse() {
        return this.nomClasse;
    }

    public TypeFiliere getTypeFiliere() {
        return this.typeFiliere;
    }

    public Niveau getNiveau() {
        return this.niveau;
    }

    public Option getOption() {
        return this.option;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public void setTypeFiliere(TypeFiliere typeFiliere) {
        this.typeFiliere = typeFiliere;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
