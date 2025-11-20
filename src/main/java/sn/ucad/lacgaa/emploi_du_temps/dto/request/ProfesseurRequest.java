package sn.ucad.lacgaa.emploi_du_temps.dto.request;

import sn.ucad.lacgaa.emploi_du_temps.enums.Titre;

public class ProfesseurRequest {

    private Integer idUtilisateur;
    private Titre titre;

    ProfesseurRequest(Integer idUtilisateur, Titre titre) {
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
    }

    public static ProfesseurRequestBuilder builder() {
        return new ProfesseurRequestBuilder();
    }

    public Integer getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public Titre getTitre() {
        return this.titre;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public static class ProfesseurRequestBuilder {
        private Integer idUtilisateur;
        private Titre titre;

        ProfesseurRequestBuilder() {
        }

        public ProfesseurRequestBuilder idUtilisateur(Integer idUtilisateur) {
            this.idUtilisateur = idUtilisateur;
            return this;
        }

        public ProfesseurRequestBuilder titre(Titre titre) {
            this.titre = titre;
            return this;
        }

        public ProfesseurRequest build() {
            return new ProfesseurRequest(this.idUtilisateur, this.titre);
        }

        public String toString() {
            return "ProfesseurRequest.ProfesseurRequestBuilder(idUtilisateur=" + this.idUtilisateur + ", titre=" + this.titre + ")";
        }
    }
}
