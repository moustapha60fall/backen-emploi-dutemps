package sn.ucad.lacgaa.emploi_du_temps.dto.request;

public class SalleRequest {

    private String codeSalle;
    private String nomSalle;

    public String getCodeSalle() {
        return this.codeSalle;
    }

    public String getNomSalle() {
        return this.nomSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }
}
