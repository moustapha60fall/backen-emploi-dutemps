package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class MatiereRequest {

    private String codeMatiere;
    private String nomMatiere;

    public String getCodeMatiere() {
        return this.codeMatiere;
    }

    public String getNomMatiere() {
        return this.nomMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
}
