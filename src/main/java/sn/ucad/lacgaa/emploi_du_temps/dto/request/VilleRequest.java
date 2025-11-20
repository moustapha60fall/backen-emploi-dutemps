package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class VilleRequest {

    private String nomVille;
    private Integer idRegion;

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
}
