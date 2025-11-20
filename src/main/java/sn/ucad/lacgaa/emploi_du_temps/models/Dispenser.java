package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "dispensations")
public class Dispenser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDispensation;

    @ManyToOne
    @JoinColumn(name = "id_professeur", nullable = false)
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    public Dispenser(Integer idDispensation, Professeur professeur, Matiere matiere) {
        this.idDispensation = idDispensation;
        this.professeur = professeur;
        this.matiere = matiere;
    }

    public Dispenser() {
    }

    public Integer getIdDispensation() {
        return idDispensation;
    }

    public void setIdDispensation(Integer idDispensation) {
        this.idDispensation = idDispensation;
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

    @Override
    public String toString() {
        return "Dispenser{" +
                "idDispensation=" + idDispensation +
                ", professeur=" + professeur +
                ", matiere=" + matiere +
                '}';
    }
}
