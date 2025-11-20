package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class EtudiantRequest {

    private Integer idUtilisateur;
    private Integer idClasse;

    public EtudiantRequest(Integer idUtilisateur, Integer idClasse) {
        this.idUtilisateur = idUtilisateur;
        this.idClasse = idClasse;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    @Override
    public String toString() {
        return "EtudiantRequest{" +
                "idUtilisateur=" + idUtilisateur +
                ", idClasse=" + idClasse +
                '}';
    }
}
