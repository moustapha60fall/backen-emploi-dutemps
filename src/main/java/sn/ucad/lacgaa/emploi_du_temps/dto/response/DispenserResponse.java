package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import sn.ucad.lacgaa.emploi_du_temps.enums.Titre;

public class DispenserResponse {

    private Integer idDispensation;
    private Integer idProfesseur;
    private Integer idMatiere;
    private String nomMatiere;
    private String nomProfesseur;
    private String prenomProfesseur;
    private Titre titreProfesseur;
    private String emailProfesseur;
    private String phoneNumberProfrsseur;

    public Integer getIdDispensation() {
        return idDispensation;
    }

    public void setIdDispensation(Integer idDispensation) {
        this.idDispensation = idDispensation;
    }

    public Integer getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomProfesseur() {
        return nomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur = nomProfesseur;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getPrenomProfesseur() {
        return prenomProfesseur;
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        this.prenomProfesseur = prenomProfesseur;
    }

    public Titre getTitreProfesseur() {
        return titreProfesseur;
    }

    public void setTitreProfesseur(Titre titreProfesseur) {
        this.titreProfesseur = titreProfesseur;
    }

    public String getEmailProfesseur() {
        return emailProfesseur;
    }

    public void setEmailProfesseur(String emailProfesseur) {
        this.emailProfesseur = emailProfesseur;
    }

    public String getPhoneNumberProfrsseur() {
        return phoneNumberProfrsseur;
    }

    public void setPhoneNumberProfrsseur(String phoneNumberProfrsseur) {
        this.phoneNumberProfrsseur = phoneNumberProfrsseur;
    }
}
