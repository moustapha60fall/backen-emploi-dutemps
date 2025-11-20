package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;
import sn.ucad.lacgaa.emploi_du_temps.enums.Titre;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professeurs")
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfesseur;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "idUtilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> cours = new ArrayList<>();

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseigner> enseigners = new ArrayList<>();

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispenser> dispensers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "titre")
    private Titre titre;

    public Professeur(Integer idProfesseur, Utilisateur utilisateur, List<Cours> cours, List<Enseigner> enseigners, List<Dispenser> dispensers, Titre titre) {
        this.idProfesseur = idProfesseur;
        this.utilisateur = utilisateur;
        this.cours = cours;
        this.enseigners = enseigners;
        this.dispensers = dispensers;
        this.titre = titre;
    }

    public Professeur() {
    }

    public Integer getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(Integer idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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

    public List<Dispenser> getDispensers() {
        return dispensers;
    }

    public void setDispensers(List<Dispenser> dispensers) {
        this.dispensers = dispensers;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "idProfesseur=" + idProfesseur +
                ", utilisateur=" + utilisateur +
                ", cours=" + cours +
                ", enseigners=" + enseigners +
                ", dispensers=" + dispensers +
                ", titre=" + titre +
                '}';
    }
}
