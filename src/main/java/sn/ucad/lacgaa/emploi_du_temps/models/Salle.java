package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSalle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> cours = new ArrayList<>();

    @Column(name = "code_salle", unique = true, nullable = false)
    private String codeSalle;

    @Column(name = "nom_salle", nullable = false)
    private String nomSalle;

    public Salle(Integer idSalle, List<Cours> cours, String codeSalle, String nomSalle) {
        this.idSalle = idSalle;
        this.cours = cours;
        this.codeSalle = codeSalle;
        this.nomSalle = nomSalle;
    }

    public Salle() {
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", cours=" + cours +
                ", codeSalle='" + codeSalle + '\'' +
                ", nomSalle='" + nomSalle + '\'' +
                '}';
    }
}
