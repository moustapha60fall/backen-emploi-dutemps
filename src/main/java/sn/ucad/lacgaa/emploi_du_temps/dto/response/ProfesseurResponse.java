package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import sn.ucad.lacgaa.emploi_du_temps.enums.Titre;

public class ProfesseurResponse {

    private Integer id;
    private Integer idProfesseur;
    private String nomProfesseur;
    private String prenomProfesseur;
    private String emailProfesseur;
    private String phoneNumberProfesseur;
    private Titre titre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public String getNomProfesseur() {
        return nomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur = nomProfesseur;
    }

    public String getPrenomProfesseur() {
        return prenomProfesseur;
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        this.prenomProfesseur = prenomProfesseur;
    }

    public String getEmailProfesseur() {
        return emailProfesseur;
    }

    public void setEmailProfesseur(String emailProfesseur) {
        this.emailProfesseur = emailProfesseur;
    }

    public String getPhoneNumberProfesseur() {
        return phoneNumberProfesseur;
    }

    public void setPhoneNumberProfesseur(String phoneNumberProfesseur) {
        this.phoneNumberProfesseur = phoneNumberProfesseur;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }
}
