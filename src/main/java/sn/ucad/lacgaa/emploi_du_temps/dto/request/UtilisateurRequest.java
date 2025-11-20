package sn.ucad.lacgaa.emploi_du_temps.dto.request;

import java.time.LocalDateTime;

public class UtilisateurRequest {

    private Integer idUtilisateur;
    private Integer idRegion;
    private Integer idVille;
    private String nomRegion;
    private String nomVille;
    private LocalDateTime lastActivity;
    private String addressProfil;
    private String etesLogee;

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getAddressProfil() {
        return addressProfil;
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
}
