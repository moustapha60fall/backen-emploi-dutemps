package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class EnseignerRequest {

    private Integer idProfesseur;
    private Integer idMatiere;
    private Integer idEtudiant;

    public Integer getIdProfesseur() {
        return this.idProfesseur;
    }

    public Integer getIdMatiere() {
        return this.idMatiere;
    }

    public Integer getIdEtudiant() {
        return this.idEtudiant;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
}
