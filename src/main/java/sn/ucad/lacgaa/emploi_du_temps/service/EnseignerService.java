package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EnseignerRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EnseignerResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.*;
import sn.ucad.lacgaa.emploi_du_temps.repository.EnseignerRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.EtudiantRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.MatiereRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.ProfesseurRepository;

import java.util.List;

@Service
@Transactional
public class EnseignerService {

    private final EnseignerRepository enseignerRepository;
    private final ProfesseurRepository professeurRepository;
    private final MatiereRepository matiereRepository;
    private final EtudiantRepository etudiantRepository;

    public EnseignerService(EnseignerRepository enseignerRepository,
                            ProfesseurRepository professeurRepository,
                            MatiereRepository matiereRepository,
                            EtudiantRepository etudiantRepository) {
        this.enseignerRepository = enseignerRepository;
        this.professeurRepository = professeurRepository;
        this.matiereRepository = matiereRepository;
        this.etudiantRepository = etudiantRepository;
    }

    // Créer un nouvel enseignement
    public EnseignerResponse createEnseignement(EnseignerRequest enseignerRequest) {
        Professeur professeur = professeurRepository.findById(enseignerRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + enseignerRequest.getIdProfesseur()));

        Matiere matiere = matiereRepository.findById(enseignerRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + enseignerRequest.getIdMatiere()));

        Etudiant etudiant = etudiantRepository.findById(enseignerRequest.getIdEtudiant())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + enseignerRequest.getIdEtudiant()));

        Enseigner enseignement = new Enseigner();
        enseignement.setProfesseur(professeur);
        enseignement.setMatiere(matiere);
        enseignement.setEtudiant(etudiant);

        Enseigner savedEnseignement = enseignerRepository.save(enseignement);
        return mapToEnseignerResponse(savedEnseignement);
    }

    // Obtenir tous les enseignements
    public List<EnseignerResponse> getAllEnseignements() {
        return enseignerRepository.findAll().stream()
                .map(this::mapToEnseignerResponse)
                .toList();
    }

    // Obtenir un enseignement par son ID
    public EnseignerResponse getEnseignementById(Integer idEnseignement) {
        Enseigner enseignement = enseignerRepository.findById(idEnseignement)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Enseignement introuvable avec l'ID : " + idEnseignement));
        return mapToEnseignerResponse(enseignement);
    }

    // Mettre à jour un enseignement
    public EnseignerResponse updateEnseignement(Integer idEnseignement, EnseignerRequest enseignerRequest) {
        Enseigner enseignement = enseignerRepository.findById(idEnseignement)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Enseignement introuvable avec l'ID : " + idEnseignement));

        Professeur professeur = professeurRepository.findById(enseignerRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + enseignerRequest.getIdProfesseur()));

        Matiere matiere = matiereRepository.findById(enseignerRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + enseignerRequest.getIdMatiere()));

        Etudiant etudiant = etudiantRepository.findById(enseignerRequest.getIdEtudiant())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + enseignerRequest.getIdEtudiant()));

        enseignement.setProfesseur(professeur);
        enseignement.setMatiere(matiere);
        enseignement.setEtudiant(etudiant);

        Enseigner updatedEnseignement = enseignerRepository.save(enseignement);
        return mapToEnseignerResponse(updatedEnseignement);
    }

    public void deleteEnseignements(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Enseigner> enseignementsToDelete = enseignerRepository.findAllById(ids);

        if (enseignementsToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de enseignements n'existent pas dans la base de données.");
        }

        enseignerRepository.deleteAll(enseignementsToDelete);
    }

    // Supprimer un enseignement par ID
    public void deleteEnseignementById(Integer idEnseignement) {
        if (!enseignerRepository.existsById(idEnseignement)) {
            throw new ModelException("ID_NOT_FOUND", "Enseignement introuvable avec l'ID : " + idEnseignement);
        }
        enseignerRepository.deleteById(idEnseignement);
    }

    // Mapper vers EnseignerResponse
    private EnseignerResponse mapToEnseignerResponse(Enseigner enseignement) {
        EnseignerResponse response = new EnseignerResponse();
        response.setIdEnseignement(enseignement.getIdEnseignement());
        response.setIdProfesseur(enseignement.getProfesseur().getIdProfesseur());
        response.setIdMatiere(enseignement.getMatiere().getIdMatiere());
        response.setIdEtudiant(enseignement.getEtudiant().getIdEtudiant());
        response.setNomMatiere(enseignement.getMatiere().getNomMatiere());
        response.setNomProfesseur(enseignement.getProfesseur().getUtilisateur().getFirstName());
        response.setPrenomProfesseur(enseignement.getProfesseur().getUtilisateur().getLastName());
        response.setTitreProfesseur(enseignement.getProfesseur().getTitre());
        response.setNomEtudiant(enseignement.getEtudiant().getUtilisateur().getLastName());
        response.setPrenomEtudiant(enseignement.getEtudiant().getUtilisateur().getLastName());
        response.setEmailEtudiant(enseignement.getEtudiant().getUtilisateur().getEmail());
        return response;
    }
}
