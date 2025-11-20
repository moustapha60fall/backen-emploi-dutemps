package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.SalleRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.SalleResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.SalleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salles")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<SalleResponse> ajouterSalle(@RequestBody SalleRequest salleRequest) {
        SalleResponse salleResponse = salleService.createSalle(salleRequest);
        return ResponseEntity.ok(salleResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalleResponse>> getAllSalle() {
        List<SalleResponse> salleResponses = salleService.getAllSalles();
        return ResponseEntity.ok(salleResponses);
    }

    @GetMapping("/{idSalle}")
    public ResponseEntity<SalleResponse> getSalleById(@PathVariable Integer idSalle) {
        SalleResponse salleResponse = salleService.getSalleById(idSalle);
        return ResponseEntity.ok(salleResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteSalles(@RequestBody List<Integer> ids) {
        salleService.deleteSalles(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idSalle}")
    public ResponseEntity<Void> deleteSalleById(@PathVariable Integer idSalle) {
        salleService.deleteSalleById(idSalle);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idSalle}")
    public ResponseEntity<SalleResponse> updateSalle(@PathVariable Integer idSalle, @RequestBody SalleRequest salleRequest) {
        SalleResponse updatedSalle = salleService.updateSalle(idSalle, salleRequest);
        return ResponseEntity.ok(updatedSalle);
    }
}
