package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.RegionRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.RegionResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<RegionResponse> ajouterRegion(@RequestBody RegionRequest regionRequest) {
        return ResponseEntity.ok(regionService.ajouterRegion(regionRequest));
    }

    @GetMapping("/{idRegion}")
    public ResponseEntity<RegionResponse> getRegionById(@PathVariable Integer idRegion) {
        return ResponseEntity.ok(regionService.getRegionById(idRegion));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionResponse>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteRegions(@RequestBody List<Integer> ids) {
        regionService.deleteRegions(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idRegion}")
    public ResponseEntity<Void> deleteRegionById(@PathVariable Integer idRegion) {
        regionService.deleteRegionById(idRegion);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idRegion}")
    public ResponseEntity<RegionResponse> updateRegion(@PathVariable Integer idRegion, @RequestBody RegionRequest regionRequest) {
        RegionResponse updatedRegion = regionService.updateRegion(idRegion, regionRequest);
        return ResponseEntity.ok(updatedRegion);
    }
}
