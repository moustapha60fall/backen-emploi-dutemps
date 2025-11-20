package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "villes")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private Integer idVille;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;

    @Column(name = "nom_ville")
    private String nomVille;

    public Ville(Integer idVille, Region region, String nomVille) {
        this.idVille = idVille;
        this.region = region;
        this.nomVille = nomVille;
    }

    public Ville() {}

    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", region=" + region +
                ", nomVille='" + nomVille + '\'' +
                '}';
    }
}

