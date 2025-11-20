package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ApprendreRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ApprendreResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.ApprendreService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apprendres")
public class ApprendreController {

    private final ApprendreService apprendreService;

    public ApprendreController(ApprendreService apprendreService) {
        this.apprendreService = apprendreService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<ApprendreResponse> ajouterApprendre(@RequestBody ApprendreRequest apprendreRequest) {
        ApprendreResponse apprendreResponse = apprendreService.createApprentissage(apprendreRequest);
        return ResponseEntity.ok(apprendreResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApprendreResponse>> getAllApprendre() {
        List<ApprendreResponse> apprendreResponses = apprendreService.getAllApprentissages();
        return ResponseEntity.ok(apprendreResponses);
    }

    @GetMapping("/{idApprentissage}")
    public ResponseEntity<ApprendreResponse> getApprendreById(@PathVariable Integer idApprentissage) {
        ApprendreResponse apprendreResponse = apprendreService.getApprentissageById(idApprentissage);
        return ResponseEntity.ok(apprendreResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteApprendres(@RequestBody List<Integer> ids) {
        apprendreService.deleteApprentissages(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idApprentissage}")
    public ResponseEntity<Void> deleteApprendreById(@PathVariable Integer idApprentissage) {
        apprendreService.deleteApprentissageById(idApprentissage);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idApprentissage}")
    public ResponseEntity<ApprendreResponse> updateApprendre(@PathVariable Integer idApprentissage, @RequestBody ApprendreRequest apprendreRequest) {
        ApprendreResponse updatedApprendre = apprendreService.updateApprentissage(idApprentissage, apprendreRequest);
        return ResponseEntity.ok(updatedApprendre);
    }
}
