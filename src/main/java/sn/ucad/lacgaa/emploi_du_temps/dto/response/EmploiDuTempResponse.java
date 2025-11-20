package sn.ucad.lacgaa.emploi_du_temps.dto.response;

import sn.ucad.lacgaa.emploi_du_temps.enums.Semestre;

public class EmploiDuTempResponse {
    private Integer idEmploiDuTemp;
    private Integer idClasse;
    private String nomClasse;
    private String anneeAcademique;
    private Semestre semestre;

    public Integer getIdEmploiDuTemp() {
        return idEmploiDuTemp;
    }

    public void setIdEmploiDuTemp(Integer idEmploiDuTemp) {
        this.idEmploiDuTemp = idEmploiDuTemp;
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
