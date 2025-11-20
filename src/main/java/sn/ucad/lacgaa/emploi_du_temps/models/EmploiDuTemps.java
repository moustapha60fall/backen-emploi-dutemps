package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;
import sn.ucad.lacgaa.emploi_du_temps.enums.Semestre;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emploi_du_temps")
public class EmploiDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmploiDuTemp;

    @OneToOne
    @JoinColumn(name = "id_classe", nullable = false, unique = true)
    private Classe classe;

    @OneToMany(mappedBy = "emploiDuTemps", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> cours = new ArrayList<>();

    @Column(name = "annee_academique", nullable = false)
    private String anneeAcademique; // Année en cours et l'année suivante

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre", nullable = false)
    private Semestre semestre; // Semestre 1 ou Semestre 2

    public EmploiDuTemps(Integer idEmploiDuTemp, Classe classe, List<Cours> cours, String anneeAcademique, Semestre semestre) {
        this.idEmploiDuTemp = idEmploiDuTemp;
        this.classe = classe;
        this.cours = cours;
        this.anneeAcademique = anneeAcademique;
        this.semestre = semestre;
    }

    public EmploiDuTemps() {
    }

    public Integer getIdEmploiDuTemp() {
        return idEmploiDuTemp;
    }

    public void setIdEmploiDuTemp(Integer idEmploiDuTemp) {
        this.idEmploiDuTemp = idEmploiDuTemp;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
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

    @Override
    public String toString() {
        return "EmploiDuTemps{" +
                "idEmploiDuTemp=" + idEmploiDuTemp +
                ", classe=" + classe +
                ", cours=" + cours +
                ", anneeAcademique='" + anneeAcademique + '\'' +
                ", semestre=" + semestre +
                '}';
    }
}
