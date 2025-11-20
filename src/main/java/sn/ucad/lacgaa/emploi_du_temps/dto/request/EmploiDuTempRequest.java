package sn.ucad.lacgaa.emploi_du_temps.dto.request;

import sn.ucad.lacgaa.emploi_du_temps.enums.Semestre;

public class EmploiDuTempRequest {
    private Integer idClasse;
    private String anneeAcademique;
    private Semestre semestre;

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
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
