package sn.ucad.lacgaa.emploi_du_temps.dto.request;

import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeCours;

public class CourRequest {

    private String codeCours;
    private Integer idMatiere;
    private Integer idProfesseur;
    private Integer idSalle;
    private Integer idClasse;
    private Integer idEmploiDuTemp;
    private TypeCours typeCours;
    private Jours jours;
    private String heureDebut;
    private String heureFin;

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Integer getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public Integer getIdEmploiDuTemp() {
        return idEmploiDuTemp;
    }

    public void setIdEmploiDuTemp(Integer idEmploiDuTemp) {
        this.idEmploiDuTemp = idEmploiDuTemp;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public Jours getJours() {
        return jours;
    }

    public void setJours(Jours jours) {
        this.jours = jours;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
}
