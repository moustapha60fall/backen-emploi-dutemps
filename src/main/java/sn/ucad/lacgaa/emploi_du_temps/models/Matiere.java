package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matieres")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatiere;

    @Column(name = "code_matiere", unique = true, nullable = false)
    private String codeMatiere;

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> cours = new ArrayList<>();

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseigner> enseigners = new ArrayList<>();

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apprendre> apprendres = new ArrayList<>();

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispenser> dispensers = new ArrayList<>();

    @Column(name = "nom_matiere", nullable = false)
    private String nomMatiere;

    public Matiere(Integer idMatiere, String codeMatiere, List<Cours> cours, List<Enseigner> enseigners, List<Apprendre> apprendres, List<Dispenser> dispensers, String nomMatiere) {
        this.idMatiere = idMatiere;
        this.codeMatiere = codeMatiere;
        this.cours = cours;
        this.enseigners = enseigners;
        this.apprendres = apprendres;
        this.dispensers = dispensers;
        this.nomMatiere = nomMatiere;
    }

    public Matiere() {
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public List<Enseigner> getEnseigners() {
        return enseigners;
    }

    public void setEnseigners(List<Enseigner> enseigners) {
        this.enseigners = enseigners;
    }

    public List<Apprendre> getApprendres() {
        return apprendres;
    }

    public void setApprendres(List<Apprendre> apprendres) {
        this.apprendres = apprendres;
    }

    public List<Dispenser> getDispensers() {
        return dispensers;
    }

    public void setDispensers(List<Dispenser> dispensers) {
        this.dispensers = dispensers;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "idMatiere=" + idMatiere +
                ", codeMatiere='" + codeMatiere + '\'' +
                ", cours=" + cours +
                ", enseigners=" + enseigners +
                ", apprendres=" + apprendres +
                ", dispensers=" + dispensers +
                ", nomMatiere='" + nomMatiere + '\'' +
                '}';
    }
}
