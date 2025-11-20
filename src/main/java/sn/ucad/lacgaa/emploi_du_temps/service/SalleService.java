package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.SalleRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.SalleResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Salle;
import sn.ucad.lacgaa.emploi_du_temps.repository.SalleRepository;

import java.util.List;

@Service
@Transactional
public class SalleService {

    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    // Créer une nouvelle salle
    public SalleResponse createSalle(SalleRequest salleRequest) {
        Salle salle = new Salle();
        salle.setCodeSalle(salleRequest.getCodeSalle());
        salle.setNomSalle(salleRequest.getNomSalle());

        Salle savedSalle = salleRepository.save(salle);
        return mapToSalleResponse(savedSalle);
    }

    // Obtenir toutes les salles
    public List<SalleResponse> getAllSalles() {
        return salleRepository.findAll().stream()
                .map(this::mapToSalleResponse)
                .toList();
    }

    // Obtenir une salle par son ID
    public SalleResponse getSalleById(Integer idSalle) {
        Salle salle = salleRepository.findById(idSalle)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Salle introuvable avec l'ID : " + idSalle));
        return mapToSalleResponse(salle);
    }

    // Mettre à jour une salle
    public SalleResponse updateSalle(Integer idSalle, SalleRequest salleRequest) {
        Salle salle = salleRepository.findById(idSalle)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Salle introuvable avec l'ID : " + idSalle));
        return getSalleResponse(salleRequest, salle);
    }

    public void deleteSalles(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Salle> sallesToDelete = salleRepository.findAllById(ids);

        if (sallesToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de salle n'existent pas dans la base de données.");
        }

        salleRepository.deleteAll(sallesToDelete);
    }

    // Supprimer une salle par ID
    public void deleteSalleById(Integer idSalle) {
        if (!salleRepository.existsById(idSalle)) {
            throw new ModelException("ID_NOT_FOUND", "Salle introuvable avec l'ID : " + idSalle);
        }
        salleRepository.deleteById(idSalle);
    }

    private SalleResponse getSalleResponse(SalleRequest classeRequest, Salle salle) {
        salle.setCodeSalle(classeRequest.getCodeSalle());
        salle.setNomSalle(classeRequest.getNomSalle());

        Salle updatedSalle = salleRepository.save(salle);
        return mapToSalleResponse(updatedSalle);
    }

    // Mapper vers SalleResponse
    private SalleResponse mapToSalleResponse(Salle salle) {
        SalleResponse response = new SalleResponse();
        response.setIdSalle(salle.getIdSalle());
        response.setCodeSalle(salle.getCodeSalle());
        response.setNomSalle(salle.getNomSalle());
        return response;
    }
}
