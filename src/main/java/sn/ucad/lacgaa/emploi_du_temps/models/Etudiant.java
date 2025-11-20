package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseigner> enseigners = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apprendre> apprendres = new ArrayList<>();


    public Etudiant(Integer idEtudiant, Utilisateur utilisateur, Classe classe, List<Enseigner> enseigners, List<Apprendre> apprendres) {
        this.idEtudiant = idEtudiant;
        this.utilisateur = utilisateur;
        this.classe = classe;
        this.enseigners = enseigners;
        this.apprendres = apprendres;
    }

    public Etudiant() {
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
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

    @Override
    public String toString() {
        return "Etudiant{" +
                "idEtudiant=" + idEtudiant +
                ", utilisateur=" + utilisateur +
                ", classe=" + classe +
                ", enseigners=" + enseigners +
                ", apprendres=" + apprendres +
                '}';
    }
}
