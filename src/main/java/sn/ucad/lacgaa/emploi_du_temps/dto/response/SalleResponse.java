package sn.ucad.lacgaa.emploi_du_temps.dto.response;

public class SalleResponse {

    private Integer idSalle;
    private String codeSalle;
    private String nomSalle;

    public Integer getIdSalle() {
        return this.idSalle;
    }

    public String getCodeSalle() {
        return this.codeSalle;
    }

    public String getNomSalle() {
        return this.nomSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

}
