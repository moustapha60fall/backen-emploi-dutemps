package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EtudiantRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EtudiantResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.EtudiantService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<EtudiantResponse> ajouterEtudiant(@RequestBody EtudiantRequest etudiantRequest) {
        EtudiantResponse etudiantResponse = etudiantService.createEtudiant(etudiantRequest);
        return ResponseEntity.ok(etudiantResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EtudiantResponse>> getAllEtudiant() {
        List<EtudiantResponse> etudiantResponses = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiantResponses);
    }

    @GetMapping("/{idEtudiant}")
    public ResponseEntity<EtudiantResponse> getEtudiantById(@PathVariable Integer idEtudiant) {
        EtudiantResponse etudiantResponse = etudiantService.getEtudiantById(idEtudiant);
        return ResponseEntity.ok(etudiantResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteEtudiants(@RequestBody List<Integer> ids) {
        etudiantService.deleteEtudiants(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idEtudiant}")
    public ResponseEntity<Void> deleteEtudiantById(@PathVariable Integer idEtudiant) {
        etudiantService.deleteEtudiantById(idEtudiant);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idEtudiant}")
    public ResponseEntity<EtudiantResponse> updateEtudiant(@PathVariable Integer idEtudiant, @RequestBody EtudiantRequest etudiantRequest) {
        EtudiantResponse updatedEtudiant = etudiantService.updateEtudiant(idEtudiant, etudiantRequest);
        return ResponseEntity.ok(updatedEtudiant);
    }
}
