package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.RegionRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.RegionResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Region;
import sn.ucad.lacgaa.emploi_du_temps.repository.RegionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public RegionResponse ajouterRegion(RegionRequest request) {

        Region region = new Region();
        region.setNomRegion(request.getNomRegion());

        Region savedRegion = regionRepository.save(region);
        return mapToRegionResponse(savedRegion);
    }

    public List<RegionResponse> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream()
                .map(this::mapToRegionResponse)
                .collect(Collectors.toList());
    }

    public RegionResponse getRegionById(Integer idRegion) {
        Region region = regionRepository.findById(idRegion)
                .orElseThrow(() -> new ModelException(
                        "REGION_NOT_FOUND", "Région avec l'ID " + idRegion + " non trouvée"));
        return mapToRegionResponse(region);
    }

    // Mettre à jour une région
    public RegionResponse updateRegion(Integer idRegion, RegionRequest regionRequest) {
        Region region = regionRepository.findById(idRegion)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Région introuvable avec l'ID : " + idRegion));

        region.setNomRegion(regionRequest.getNomRegion());
        Region updatedRegion = regionRepository.save(region);
        return mapToRegionResponse(updatedRegion);
    }

    public void deleteRegions(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Region> regionsToDelete = regionRepository.findAllById(ids);

        if (regionsToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de regions n'existent pas dans la base de données.");
        }

        regionRepository.deleteAll(regionsToDelete);
    }

    // Supprimer une région par ID
    public void deleteRegionById(Integer idRegion) {
        if (!regionRepository.existsById(idRegion)) {
            throw new ModelException("ID_NOT_FOUND", "Région introuvable avec l'ID : " + idRegion);
        }
        regionRepository.deleteById(idRegion);
    }

    // Mapper vers RegionResponse
    private RegionResponse mapToRegionResponse(Region region) {
        RegionResponse response = new RegionResponse();
        response.setIdRegion(region.getIdRegion());
        response.setNomRegion(region.getNomRegion());
        return response;
    }
}

