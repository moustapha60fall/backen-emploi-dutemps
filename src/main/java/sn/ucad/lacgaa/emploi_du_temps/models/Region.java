package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Integer idRegion;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Ville> villes;

    private String nomRegion;

    public Region(Integer idRegion, List<Utilisateur> utilisateurs, List<Ville> villes, String nomRegion) {
        this.idRegion = idRegion;
        this.utilisateurs = utilisateurs;
        this.villes = villes;
        this.nomRegion = nomRegion;
    }

    public Region() {}

    public Region(Integer idRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    @Override
    public String toString() {
        return "Region{" +
                "idRegion=" + idRegion +
                ", utilisateurs=" + utilisateurs +
                ", villes=" + villes +
                ", nomRegion='" + nomRegion + '\'' +
                '}';
    }
}
