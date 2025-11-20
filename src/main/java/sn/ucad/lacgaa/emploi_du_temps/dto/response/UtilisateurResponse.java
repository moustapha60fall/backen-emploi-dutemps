package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import java.time.LocalDate;

public class UtilisateurResponse {

    private Integer idUtilisateur;
    private Integer idRegion;
    private Integer idVille;
    private String nomRegion;
    private String nomVille;
    private String email;
    private String lastName;
    private String firstName;
    private String username;
    private Boolean emailVerified;
    private String phoneNumber;
    private String codePIN;
    private String imageProfil;
    private LocalDate dateBirthday;
    private String civiliteProfil;
    private String addressProfil;
    private String etesLogee;
    private Boolean blogPosts;
    private Boolean newsletter;
    private String sessionStart;
    private String lastSessionActivity;

    public Integer getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean getEmailVerified() {
        return this.emailVerified;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodePIN() {
        return codePIN;
    }

    public void setCodePIN(String codePIN) {
        this.codePIN = codePIN;
    }

    public String getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(String imageProfil) {
        this.imageProfil = imageProfil;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getCiviliteProfil() {
        return civiliteProfil;
    }

    public void setCiviliteProfil(String civiliteProfil) {
        this.civiliteProfil = civiliteProfil;
    }

    public String getAddressProfil() {
        return addressProfil;
    }

    public Boolean getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(Boolean blogPosts) {
        this.blogPosts = blogPosts;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public void setAddressProfil(String addressProfil) {
        this.addressProfil = addressProfil;
    }

    public String getEtesLogee() {
        return etesLogee;
    }

    public void setEtesLogee(String etesLogee) {
        this.etesLogee = etesLogee;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getLastSessionActivity() {
        return lastSessionActivity;
    }

    public void setLastSessionActivity(String lastSessionActivity) {
        this.lastSessionActivity = lastSessionActivity;
    }
}
