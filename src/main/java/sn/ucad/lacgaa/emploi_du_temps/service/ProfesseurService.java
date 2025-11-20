package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ProfesseurRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ProfesseurResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Professeur;
import sn.ucad.lacgaa.emploi_du_temps.models.Utilisateur;
import sn.ucad.lacgaa.emploi_du_temps.repository.ProfesseurRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.UtilisateurRepository;

import java.util.List;

@Service
@Transactional
public class ProfesseurService {

    private final ProfesseurRepository professeurRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository, UtilisateurRepository utilisateurRepository) {
        this.professeurRepository = professeurRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // Créer un nouveau professeur
    public ProfesseurResponse createProfesseur(ProfesseurRequest professeurRequest) {
        Utilisateur utilisateur = utilisateurRepository.findById(professeurRequest.getIdUtilisateur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + professeurRequest.getIdUtilisateur()));

        Professeur professeur = new Professeur();
        professeur.setUtilisateur(utilisateur);
        professeur.setTitre(professeurRequest.getTitre());

        Professeur savedProfesseur = professeurRepository.save(professeur);
        return mapToProfesseurResponse(savedProfesseur);
    }

    // Obtenir tous les professeurs
    public List<ProfesseurResponse> getAllProfesseurs() {
        return professeurRepository.findAll().stream()
                .map(this::mapToProfesseurResponse)
                .toList();
    }

    // Obtenir un professeur par son ID
    public ProfesseurResponse getProfesseurById(Integer id) {
        Professeur professeur = professeurRepository.findById(id)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + id));
        return mapToProfesseurResponse(professeur);
    }

    // Mettre à jour un professeur
    public ProfesseurResponse updateProfesseur(Integer idProfesseur, ProfesseurRequest professeurRequest) {
        Professeur professeur = professeurRepository.findById(idProfesseur)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + idProfesseur));

        Utilisateur utilisateur = utilisateurRepository.findById(professeurRequest.getIdUtilisateur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Utilisateur introuvable avec l'ID : " + professeurRequest.getIdUtilisateur()));

        professeur.setUtilisateur(utilisateur);
        professeur.setTitre(professeurRequest.getTitre());

        Professeur updatedProfesseur = professeurRepository.save(professeur);
        return mapToProfesseurResponse(updatedProfesseur);
    }

    public void deleteProfesseurs(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Professeur> professeursToDelete = professeurRepository.findAllById(ids);

        if (professeursToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de professeurs n'existent pas dans la base de données.");
        }

        professeurRepository.deleteAll(professeursToDelete);
    }

    // Supprimer un professeur par ID
    public void deleteProfesseurById(Integer idProfesseur) {
        if (!professeurRepository.existsById(idProfesseur)) {
            throw new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + idProfesseur);
        }
        professeurRepository.deleteById(idProfesseur);
    }

    // Mapper vers ProfesseurResponse
    private ProfesseurResponse mapToProfesseurResponse(Professeur professeur) {
        ProfesseurResponse response = new ProfesseurResponse();
        response.setId(professeur.getUtilisateur().getIdUtilisateur());
        response.setIdProfesseur(professeur.getIdProfesseur());
        response.setNomProfesseur(professeur.getUtilisateur().getLastName());
        response.setPrenomProfesseur(professeur.getUtilisateur().getFirstName());
        response.setEmailProfesseur(professeur.getUtilisateur().getEmail());
        response.setPhoneNumberProfesseur(professeur.getUtilisateur().getPhoneNumber());
        response.setTitre(professeur.getTitre());
        return response;
    }
}
