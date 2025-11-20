package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ApprendreRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ApprendreResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Apprendre;
import sn.ucad.lacgaa.emploi_du_temps.models.Etudiant;
import sn.ucad.lacgaa.emploi_du_temps.models.Matiere;
import sn.ucad.lacgaa.emploi_du_temps.repository.ApprendreRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.EtudiantRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.MatiereRepository;

import java.util.List;

@Service
@Transactional
public class ApprendreService {

    private final ApprendreRepository apprendreRepository;
    private final EtudiantRepository etudiantRepository;
    private final MatiereRepository matiereRepository;

    public ApprendreService(ApprendreRepository apprendreRepository,
                            EtudiantRepository etudiantRepository,
                            MatiereRepository matiereRepository) {
        this.apprendreRepository = apprendreRepository;
        this.etudiantRepository = etudiantRepository;
        this.matiereRepository = matiereRepository;
    }

    // Créer un nouvel apprentissage
    public ApprendreResponse createApprentissage(ApprendreRequest apprendreRequest) {
        Etudiant etudiant = etudiantRepository.findById(apprendreRequest.getIdEtudiant())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + apprendreRequest.getIdEtudiant()));

        Matiere matiere = matiereRepository.findById(apprendreRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + apprendreRequest.getIdMatiere()));

        Apprendre apprentissage = new Apprendre();
        apprentissage.setEtudiant(etudiant);
        apprentissage.setMatiere(matiere);

        Apprendre savedApprentissage = apprendreRepository.save(apprentissage);
        return mapToApprendreResponse(savedApprentissage);
    }

    // Obtenir tous les apprentissages
    public List<ApprendreResponse> getAllApprentissages() {
        return apprendreRepository.findAll().stream()
                .map(this::mapToApprendreResponse)
                .toList();
    }

    // Obtenir un apprentissage par son ID
    public ApprendreResponse getApprentissageById(Integer idApprentissage) {
        Apprendre apprentissage = apprendreRepository.findById(idApprentissage)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Apprentissage introuvable avec l'ID : " + idApprentissage));
        return mapToApprendreResponse(apprentissage);
    }

    // Mettre à jour un apprentissage
    public ApprendreResponse updateApprentissage(Integer idApprentissage, ApprendreRequest apprendreRequest) {
        Apprendre apprentissage = apprendreRepository.findById(idApprentissage)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Apprentissage introuvable avec l'ID : " + idApprentissage));

        Etudiant etudiant = etudiantRepository.findById(apprendreRequest.getIdEtudiant())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + apprendreRequest.getIdEtudiant()));

        Matiere matiere = matiereRepository.findById(apprendreRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + apprendreRequest.getIdMatiere()));

        apprentissage.setEtudiant(etudiant);
        apprentissage.setMatiere(matiere);

        Apprendre updatedApprentissage = apprendreRepository.save(apprentissage);
        return mapToApprendreResponse(updatedApprentissage);
    }

    public void deleteApprentissages(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Apprendre> apprentissagesToDelete = apprendreRepository.findAllById(ids);

        if (apprentissagesToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de apprentissages n'existent pas dans la base de données.");
        }

        apprendreRepository.deleteAll(apprentissagesToDelete);
    }

    // Supprimer un apprentissage par ID
    public void deleteApprentissageById(Integer idApprentissage) {
        if (!apprendreRepository.existsById(idApprentissage)) {
            throw new ModelException("ID_NOT_FOUND", "Apprentissage introuvable avec l'ID : " + idApprentissage);
        }
        apprendreRepository.deleteById(idApprentissage);
    }

    // Mapper vers ApprendreResponse
    private ApprendreResponse mapToApprendreResponse(Apprendre apprentissage) {
        ApprendreResponse response = new ApprendreResponse();
        response.setIdApprentissage(apprentissage.getIdApprentissage());
        response.setIdEtudiant(apprentissage.getEtudiant().getIdEtudiant());
        response.setIdMatiere(apprentissage.getMatiere().getIdMatiere());
        response.setNomMatiere(apprentissage.getMatiere().getNomMatiere());
        response.setNomEtudiant(apprentissage.getEtudiant().getUtilisateur().getLastName());
        response.setPrenomEtudiant(apprentissage.getEtudiant().getUtilisateur().getLastName());
        response.setEmailEtudiant(apprentissage.getEtudiant().getUtilisateur().getEmail());
        response.setPhoneNumberEtudiant(apprentissage.getEtudiant().getUtilisateur().getPhoneNumber());
        return response;
    }
}

