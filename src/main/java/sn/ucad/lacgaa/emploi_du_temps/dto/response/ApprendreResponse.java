package sn.ucad.lacgaa.emploi_du_temps.dto.response;

public class ApprendreResponse {

    private Integer idApprentissage;
    private Integer idEtudiant;
    private Integer idMatiere;
    private String nomMatiere;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String emailEtudiant;
    private String phoneNumberEtudiant;

    public Integer getIdApprentissage() {
        return idApprentissage;
    }

    public void setIdApprentissage(Integer idApprentissage) {
        this.idApprentissage = idApprentissage;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getEmailEtudiant() {

        return emailEtudiant;
    }

    public void setEmailEtudiant(String emailEtudiant) {
        this.emailEtudiant = emailEtudiant;
    }

    public String getPhoneNumberEtudiant() {
        return phoneNumberEtudiant;
    }

    public void setPhoneNumberEtudiant(String phoneNumberEtudiant) {
        this.phoneNumberEtudiant = phoneNumberEtudiant;
    }
}
