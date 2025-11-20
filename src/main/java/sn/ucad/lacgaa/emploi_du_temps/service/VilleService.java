package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.VilleRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.VilleResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Region;
import sn.ucad.lacgaa.emploi_du_temps.models.Ville;
import sn.ucad.lacgaa.emploi_du_temps.repository.RegionRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.VilleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VilleService {

    private final VilleRepository villeRepository;
    private final RegionRepository regionRepository;

    public VilleService(VilleRepository villeRepository, RegionRepository regionRepository) {
        this.villeRepository = villeRepository;
        this.regionRepository = regionRepository;
    }

    public VilleResponse ajouterVille(VilleRequest request) {

        Region region = regionRepository.findById(request.getIdRegion())
                .orElseThrow(() -> new ModelException(
                        "REGION_NOT_FOUND", "Région avec l'ID " + request.getIdRegion() + " non trouvée"));

        Ville ville = new Ville();
        ville.setRegion(region);
        ville.setNomVille(request.getNomVille());

        Ville savedVille = villeRepository.save(ville);
        return mapToVilleResponse(savedVille);
    }

    public VilleResponse getVilleById(Integer idVille) {
        Ville ville = villeRepository.findById(idVille)
                .orElseThrow(() -> new ModelException(
                        "VILLE_NOT_FOUND", "Ville avec l'ID " + idVille + " non trouvée"));
        return mapToVilleResponse(ville);
    }

    public List<VilleResponse> getVillesByRegion(Integer idRegion) {
        List<Ville> villes = villeRepository.findByRegionIdRegion(idRegion);
        return villes.stream()
                .map(this::mapToVilleResponse)
                .collect(Collectors.toList());
    }

    public List<VilleResponse> getAllVilles() {
        List<Ville> villes = villeRepository.findAll();
        return villes.stream()
                .map(this::mapToVilleResponse)
                .collect(Collectors.toList());
    }

    // Mettre à jour une ville
    public VilleResponse updateVille(Integer idVille, VilleRequest villeRequest) {
        Ville ville = villeRepository.findById(idVille)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Ville introuvable avec l'ID : " + idVille));

        Region region = regionRepository.findById(villeRequest.getIdRegion())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Région introuvable avec l'ID : " + villeRequest.getIdRegion()));

        ville.setNomVille(villeRequest.getNomVille());
        ville.setRegion(region);

        Ville updatedVille = villeRepository.save(ville);
        return mapToVilleResponse(updatedVille);
    }

    public void deleteVilles(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Ville> villesToDelete = villeRepository.findAllById(ids);

        if (villesToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de villes n'existent pas dans la base de données.");
        }

        villeRepository.deleteAll(villesToDelete);
    }

    // Supprimer une ville par ID
    public void deleteVilleById(Integer idVille) {
        if (!villeRepository.existsById(idVille)) {
            throw new ModelException("ID_NOT_FOUND", "Ville introuvable avec l'ID : " + idVille);
        }
        villeRepository.deleteById(idVille);
    }

    // Mapper vers VilleResponse
    private VilleResponse mapToVilleResponse(Ville ville) {
        VilleResponse response = new VilleResponse();
        response.setIdVille(ville.getIdVille());
        response.setIdRegion(ville.getRegion().getIdRegion());
        response.setNomVille(ville.getNomVille());
        response.setNomRegion(ville.getRegion().getNomRegion());
        return response;
    }
}
