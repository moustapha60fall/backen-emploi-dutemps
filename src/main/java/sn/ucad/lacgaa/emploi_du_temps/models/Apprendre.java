package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "apprentissages")
public class Apprendre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idApprentissage;

    @ManyToOne
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    public Apprendre(Integer idApprentissage, Etudiant etudiant, Matiere matiere) {
        this.idApprentissage = idApprentissage;
        this.etudiant = etudiant;
        this.matiere = matiere;
    }

    public Apprendre() {
    }

    public Integer getIdApprentissage() {
        return idApprentissage;
    }

    public void setIdApprentissage(Integer idApprentissage) {
        this.idApprentissage = idApprentissage;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Apprendre{" +
                "idApprentissage=" + idApprentissage +
                ", etudiant=" + etudiant +
                ", matiere=" + matiere +
                '}';
    }
}
