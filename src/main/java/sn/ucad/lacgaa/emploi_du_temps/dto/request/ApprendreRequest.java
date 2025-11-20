package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class ApprendreRequest {

    private Integer idEtudiant;
    private Integer idMatiere;

    public Integer getIdEtudiant() {
        return this.idEtudiant;
    }

    public Integer getIdMatiere() {
        return this.idMatiere;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }
}
