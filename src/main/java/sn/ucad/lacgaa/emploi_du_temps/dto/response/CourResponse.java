package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.enums.Semestre;
import sn.ucad.lacgaa.emploi_du_temps.enums.Titre;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeCours;

public class CourResponse {

    private Integer idCour;
    private Integer idEmploiDuTemp;
    private Integer idMatiere;
    private Integer idProfesseur;
    private Integer idSalle;
    private Integer idClasse;
    private String codeCours;
    private String nomMatiere;
    private Titre titreProfesseur;
    private String nomProfesseur;
    private String nomSalle;
    private String nomClasse;
    private TypeCours typeCours;
    private Jours jours;
    private String horaire;
    private String heureDebut;
    private String heureFin;
    private String anneeAcademique;
    private Semestre semestre;

    public Integer getIdCour() {
        return idCour;
    }

    public void setIdCour(Integer idCour) {
        this.idCour = idCour;
    }

    public Integer getIdEmploiDuTemp() {
        return idEmploiDuTemp;
    }

    public void setIdEmploiDuTemp(Integer idEmploiDuTemp) {
        this.idEmploiDuTemp = idEmploiDuTemp;
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

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Titre getTitreProfesseur() {
        return titreProfesseur;
    }

    public void setTitreProfesseur(Titre titreProfesseur) {
        this.titreProfesseur = titreProfesseur;
    }

    public String getNomProfesseur() {
        return nomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur = nomProfesseur;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
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

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
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

    public String getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(String anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }
}
