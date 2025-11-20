package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.CourRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.CourResponse;
import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.service.CourService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cours")
public class CourController {

    private final CourService courService;

    public CourController(CourService courService) {
        this.courService = courService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<CourResponse> ajouterCour(@RequestBody CourRequest courRequest) {
        CourResponse courResponse = courService.createCour(courRequest);
        return ResponseEntity.ok(courResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourResponse>> getAllCour() {
        List<CourResponse> courResponses = courService.getAllCours();
        return ResponseEntity.ok(courResponses);
    }

    @GetMapping("/{idCour}")
    public ResponseEntity<CourResponse> getCourById(@PathVariable Integer idCour) {
        CourResponse courResponse = courService.getCourById(idCour);
        return ResponseEntity.ok(courResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteCours(@RequestBody List<Integer> ids) {
        courService.deleteCours(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idCour}")
    public ResponseEntity<Void> deleteCourById(@PathVariable Integer idCour) {
        courService.deleteCourById(idCour);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idCour}")
    public ResponseEntity<CourResponse> updateCour(@PathVariable Integer idCour, @RequestBody CourRequest courRequest) {
        CourResponse updatedCour = courService.updateCour(idCour, courRequest);
        return ResponseEntity.ok(updatedCour);
    }

    @GetMapping("/grouped/by/day/{idEmploiDuTemp}")
    public ResponseEntity<Map<Jours, List<CourResponse>>> getCoursGroupedByJours(
            @PathVariable Integer idEmploiDuTemp) {
        Map<Jours, List<CourResponse>> horairesParJour = courService.getCoursGroupedByJours(idEmploiDuTemp);
        return ResponseEntity.ok(horairesParJour);
    }

}
