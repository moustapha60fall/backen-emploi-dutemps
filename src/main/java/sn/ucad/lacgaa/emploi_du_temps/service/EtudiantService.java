package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EtudiantRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EtudiantResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Classe;
import sn.ucad.lacgaa.emploi_du_temps.models.Etudiant;
import sn.ucad.lacgaa.emploi_du_temps.models.Utilisateur;
import sn.ucad.lacgaa.emploi_du_temps.repository.ClasseRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.EtudiantRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.UtilisateurRepository;

import java.util.List;

@Service
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClasseRepository classeRepository;

    public EtudiantService(EtudiantRepository etudiantRepository, UtilisateurRepository utilisateurRepository, ClasseRepository classeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.classeRepository = classeRepository;
    }

    // Créer un nouvel étudiant
    public EtudiantResponse createEtudiant(EtudiantRequest etudiantRequest) {
        Utilisateur utilisateur = utilisateurRepository.findById(etudiantRequest.getIdUtilisateur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Utilisateur introuvable avec l'ID : " + etudiantRequest.getIdUtilisateur()));

        Classe classe = classeRepository.findById(etudiantRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + etudiantRequest.getIdClasse()));

        Etudiant etudiant = new Etudiant();
        etudiant.setUtilisateur(utilisateur);
        etudiant.setClasse(classe);

        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        return mapToEtudiantResponse(savedEtudiant);
    }

    // Obtenir tous les étudiants
    public List<EtudiantResponse> getAllEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(this::mapToEtudiantResponse)
                .toList();
    }

    // Obtenir un étudiant par son ID
    public EtudiantResponse getEtudiantById(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + id));
        return mapToEtudiantResponse(etudiant);
    }

    // Mettre à jour un étudiant
    public EtudiantResponse updateEtudiant(Integer idEtudiant, EtudiantRequest etudiantRequest) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + idEtudiant));

        Utilisateur utilisateur = utilisateurRepository.findById(etudiantRequest.getIdUtilisateur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Utilisateur introuvable avec l'ID : " + etudiantRequest.getIdUtilisateur()));

        Classe classe = classeRepository.findById(etudiantRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + etudiantRequest.getIdClasse()));

        etudiant.setUtilisateur(utilisateur);
        etudiant.setClasse(classe);

        Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
        return mapToEtudiantResponse(updatedEtudiant);
    }

    public void deleteEtudiants(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Etudiant> etudiantsToDelete = etudiantRepository.findAllById(ids);

        if (etudiantsToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de etudiants n'existent pas dans la base de données.");
        }

        etudiantRepository.deleteAll(etudiantsToDelete);
    }

    // Supprimer un étudiant par ID
    public void deleteEtudiantById(Integer idEtudiant) {
        if (!etudiantRepository.existsById(idEtudiant)) {
            throw new ModelException("ID_NOT_FOUND", "Étudiant introuvable avec l'ID : " + idEtudiant);
        }
        etudiantRepository.deleteById(idEtudiant);
    }

    // Mapper vers EtudiantResponse
    private EtudiantResponse mapToEtudiantResponse(Etudiant etudiant) {
        EtudiantResponse response = new EtudiantResponse();
        response.setId(etudiant.getUtilisateur().getIdUtilisateur());
        response.setIdClasse(etudiant.getClasse().getIdClasse());
        response.setIdEtudiant(etudiant.getIdEtudiant());
        response.setNomEtudiant(etudiant.getUtilisateur().getLastName());
        response.setPrenomEtudiant(etudiant.getUtilisateur().getFirstName());
        response.setEmailEtudiant(etudiant.getUtilisateur().getEmail());
        response.setPhoneNumberEtudiant(etudiant.getUtilisateur().getPhoneNumber());
        response.setNomClasse(etudiant.getClasse().getNomClasse());
        response.setOption(etudiant.getClasse().getOption());
        response.setNiveau(etudiant.getClasse().getNiveau());
        response.setTypeFiliere(etudiant.getClasse().getTypeFiliere());
        return response;
    }
}
