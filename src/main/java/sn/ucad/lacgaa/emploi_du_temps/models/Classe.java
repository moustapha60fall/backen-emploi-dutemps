package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;
import sn.ucad.lacgaa.emploi_du_temps.enums.Niveau;
import sn.ucad.lacgaa.emploi_du_temps.enums.Option;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeFiliere;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classe")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClasse;

    @Column(name = "code_classe", unique = true, nullable = false)
    private String codeClasse;

    @OneToOne(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmploiDuTemps emploiDuTemps;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> cours = new ArrayList<>();;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etudiant> etudiants = new ArrayList<>();

    @Column(name = "nom_classe", nullable = false)
    private String nomClasse;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeFiliere")
    private TypeFiliere typeFiliere;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau")
    private Niveau niveau;

    @Enumerated(EnumType.STRING)
    @Column(name = "option")
    private Option option;

    public Classe(Integer idClasse, String codeClasse, EmploiDuTemps emploiDuTemps, List<Cours> cours, List<Etudiant> etudiants, String nomClasse, TypeFiliere typeFiliere, Niveau niveau, Option option) {
        this.idClasse = idClasse;
        this.codeClasse = codeClasse;
        this.emploiDuTemps = emploiDuTemps;
        this.cours = cours;
        this.etudiants = etudiants;
        this.nomClasse = nomClasse;
        this.typeFiliere = typeFiliere;
        this.niveau = niveau;
        this.option = option;
    }

    public Classe() {
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public EmploiDuTemps getEmploiDuTemps() {
        return emploiDuTemps;
    }

    public void setEmploiDuTemps(EmploiDuTemps emploiDuTemps) {
        this.emploiDuTemps = emploiDuTemps;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
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

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", codeClasse='" + codeClasse + '\'' +
                ", emploiDuTemps=" + emploiDuTemps +
                ", cours=" + cours +
                ", etudiants=" + etudiants +
                ", nomClasse='" + nomClasse + '\'' +
                ", typeFiliere=" + typeFiliere +
                ", niveau=" + niveau +
                ", option=" + option +
                '}';
    }
}
