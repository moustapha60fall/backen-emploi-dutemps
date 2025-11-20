package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.MatiereRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.MatiereResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Matiere;
import sn.ucad.lacgaa.emploi_du_temps.repository.MatiereRepository;

import java.util.List;

@Service
@Transactional
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    // Créer une nouvelle matière
    public MatiereResponse createMatiere(MatiereRequest matiereRequest) {
        Matiere matiere = new Matiere();
        matiere.setCodeMatiere(matiereRequest.getCodeMatiere());
        matiere.setNomMatiere(matiereRequest.getNomMatiere());

        Matiere savedMatiere = matiereRepository.save(matiere);
        return mapToMatiereResponse(savedMatiere);
    }

    // Obtenir toutes les matières
    public List<MatiereResponse> getAllMatieres() {
        return matiereRepository.findAll().stream()
                .map(this::mapToMatiereResponse)
                .toList();
    }

    // Obtenir une matière par son ID
    public MatiereResponse getMatiereById(Integer idMatiere) {
        Matiere matiere = matiereRepository.findById(idMatiere)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + idMatiere));
        return mapToMatiereResponse(matiere);
    }

    // Mettre à jour une matiere
    public MatiereResponse updateMatiere(Integer idMatiere, MatiereRequest matiereRequest) {
        Matiere matiere = matiereRepository.findById(idMatiere)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matiere introuvable avec l'ID : " + idMatiere));
        return getMatiereResponse(matiereRequest, matiere);
    }

    public void deleteMatieres(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Matiere> matieresToDelete = matiereRepository.findAllById(ids);

        if (matieresToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de matiere n'existent pas dans la base de données.");
        }

        matiereRepository.deleteAll(matieresToDelete);
    }

    // Supprimer une matiere par ID
    public void deleteMatiereById(Integer idMatiere) {
        if (!matiereRepository.existsById(idMatiere)) {
            throw new ModelException("ID_NOT_FOUND", "Matiere introuvable avec l'ID : " + idMatiere);
        }
        matiereRepository.deleteById(idMatiere);
    }

    private MatiereResponse getMatiereResponse(MatiereRequest classeRequest, Matiere matiere) {
        matiere.setCodeMatiere(classeRequest.getCodeMatiere());
        matiere.setNomMatiere(classeRequest.getNomMatiere());

        Matiere updatedMatiere = matiereRepository.save(matiere);
        return mapToMatiereResponse(updatedMatiere);
    }

    // Mapper vers MatiereResponse
    private MatiereResponse mapToMatiereResponse(Matiere matiere) {
        MatiereResponse response = new MatiereResponse();
        response.setIdMatiere(matiere.getIdMatiere());
        response.setCodeMatiere(matiere.getCodeMatiere());
        response.setNomMatiere(matiere.getNomMatiere());
        return response;
    }
}
