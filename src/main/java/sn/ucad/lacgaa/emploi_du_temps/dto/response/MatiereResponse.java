package sn.ucad.lacgaa.emploi_du_temps.dto.response;

public class MatiereResponse {

    private Integer idMatiere;
    private String codeMatiere;
    private String nomMatiere;

    public Integer getIdMatiere() {
        return this.idMatiere;
    }

    public String getCodeMatiere() {
        return this.codeMatiere;
    }

    public String getNomMatiere() {
        return this.nomMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

}
