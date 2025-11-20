package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;
import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.enums.TypeCours;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCour;

    @Column(name = "code_cours", unique = true, nullable = false)
    private String codeCours;

    @ManyToOne
    @JoinColumn(name = "id_emploi_du_temp", nullable = false)
    private EmploiDuTemps emploiDuTemps;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_professeur", nullable = false)
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "id_salle", nullable = false)
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_cours")
    private TypeCours typeCours;

    @Enumerated(EnumType.STRING)
    @Column(name = "jours")
    private Jours jours;

    @Column(name = "horaire", nullable = false)
    private String horaire;

    @Column(name = "heure_debut", nullable = false)
    private String heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private String heureFin;

    public Cours(Integer idCour, String codeCours, EmploiDuTemps emploiDuTemps, Matiere matiere, Professeur professeur, Salle salle, Classe classe, TypeCours typeCours, Jours jours, String horaire, String heureDebut, String heureFin) {
        this.idCour = idCour;
        this.codeCours = codeCours;
        this.emploiDuTemps = emploiDuTemps;
        this.matiere = matiere;
        this.professeur = professeur;
        this.salle = salle;
        this.classe = classe;
        this.typeCours = typeCours;
        this.jours = jours;
        this.horaire = horaire;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Cours() {
    }

    public Integer getIdCour() {
        return idCour;
    }

    public void setIdCour(Integer idCour) {
        this.idCour = idCour;
    }

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public EmploiDuTemps getEmploiDuTemps() {
        return emploiDuTemps;
    }

    public void setEmploiDuTemps(EmploiDuTemps emploiDuTemps) {
        this.emploiDuTemps = emploiDuTemps;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public Jours getJours() {
        return jours;
    }

    public void setJours(Jours jours) {
        this.jours = jours;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idCour=" + idCour +
                ", codeCours='" + codeCours + '\'' +
                ", emploiDuTemps=" + emploiDuTemps +
                ", matiere=" + matiere +
                ", professeur=" + professeur +
                ", salle=" + salle +
                ", classe=" + classe +
                ", typeCours=" + typeCours +
                ", jours=" + jours +
                ", horaire='" + horaire + '\'' +
                ", heureDebut='" + heureDebut + '\'' +
                ", heureFin='" + heureFin + '\'' +
                '}';
    }
}
