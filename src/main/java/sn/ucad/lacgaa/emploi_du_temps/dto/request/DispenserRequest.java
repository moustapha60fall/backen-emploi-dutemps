package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class DispenserRequest {

    private Integer idProfesseur;
    private Integer idMatiere;

    public Integer getIdProfesseur() {
        return this.idProfesseur;
    }

    public Integer getIdMatiere() {
        return this.idMatiere;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }
}
