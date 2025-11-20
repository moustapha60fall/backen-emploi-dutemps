package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EnseignerRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EnseignerResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.EnseignerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enseigner")
public class EnseignerController {

    private final EnseignerService enseignerService;

    public EnseignerController(EnseignerService enseignerService) {
        this.enseignerService = enseignerService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<EnseignerResponse> ajouterEnseigner(@RequestBody EnseignerRequest enseignerRequest) {
        EnseignerResponse enseignerResponse = enseignerService.createEnseignement(enseignerRequest);
        return ResponseEntity.ok(enseignerResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnseignerResponse>> getAllEnseigner() {
        List<EnseignerResponse> enseignerResponses = enseignerService.getAllEnseignements();
        return ResponseEntity.ok(enseignerResponses);
    }

    @GetMapping("/{idEnseigner}")
    public ResponseEntity<EnseignerResponse> getEnseignerById(@PathVariable Integer idEnseigner) {
        EnseignerResponse enseignerResponse = enseignerService.getEnseignementById(idEnseigner);
        return ResponseEntity.ok(enseignerResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteEnseigners(@RequestBody List<Integer> ids) {
        enseignerService.deleteEnseignements(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idEnseigner}")
    public ResponseEntity<Void> deleteEnseignerById(@PathVariable Integer idEnseigner) {
        enseignerService.deleteEnseignementById(idEnseigner);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idEnseigner}")
    public ResponseEntity<EnseignerResponse> updateEnseigner(@PathVariable Integer idEnseigner, @RequestBody EnseignerRequest enseignerRequest) {
        EnseignerResponse updatedEnseigner = enseignerService.updateEnseignement(idEnseigner, enseignerRequest);
        return ResponseEntity.ok(updatedEnseigner);
    }
}
