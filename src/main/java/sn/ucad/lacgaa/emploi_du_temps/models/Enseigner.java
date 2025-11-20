package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "enseignements")
public class Enseigner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnseignement;

    @ManyToOne
    @JoinColumn(name = "id_professeur", nullable = false)
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    public Enseigner(Integer idEnseignement, Professeur professeur, Matiere matiere, Etudiant etudiant) {
        this.idEnseignement = idEnseignement;
        this.professeur = professeur;
        this.matiere = matiere;
        this.etudiant = etudiant;
    }

    public Enseigner() {
    }

    public Integer getIdEnseignement() {
        return idEnseignement;
    }

    public void setIdEnseignement(Integer idEnseignement) {
        this.idEnseignement = idEnseignement;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public String toString() {
        return "Enseigner{" +
                "idEnseignement=" + idEnseignement +
                ", professeur=" + professeur +
                ", matiere=" + matiere +
                ", etudiant=" + etudiant +
                '}';
    }
}
