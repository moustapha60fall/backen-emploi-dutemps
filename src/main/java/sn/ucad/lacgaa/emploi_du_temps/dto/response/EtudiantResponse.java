package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import sn.ucad.lacgaa.emploi_du_temps.enums.Niveau;
import sn.ucad.lacgaa.emploi_du_temps.enums.Option;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeFiliere;

public class EtudiantResponse {

    private Integer id;
    private Integer idEtudiant;
    private Integer idClasse;
    private String nomClasse;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String emailEtudiant;
    private String phoneNumberEtudiant;
    private TypeFiliere typeFiliere;
    private Niveau niveau;
    private Option option;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
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

    public TypeFiliere getTypeFiliere() {
        return typeFiliere;
    }

    public void setTypeFiliere(TypeFiliere typeFiliere) {
        this.typeFiliere = typeFiliere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
