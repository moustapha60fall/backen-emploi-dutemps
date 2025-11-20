package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.MatiereRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.MatiereResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.MatiereService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matieres")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<MatiereResponse> ajouterMatiere(@RequestBody MatiereRequest matiereRequest) {
        MatiereResponse matiereResponse = matiereService.createMatiere(matiereRequest);
        return ResponseEntity.ok(matiereResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MatiereResponse>> getAllMatiere() {
        List<MatiereResponse> matiereResponses = matiereService.getAllMatieres();
        return ResponseEntity.ok(matiereResponses);
    }

    @GetMapping("/{idMatiere}")
    public ResponseEntity<MatiereResponse> getMatiereById(@PathVariable Integer idMatiere) {
        MatiereResponse matiereResponse = matiereService.getMatiereById(idMatiere);
        return ResponseEntity.ok(matiereResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteMatieres(@RequestBody List<Integer> ids) {
        matiereService.deleteMatieres(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idMatiere}")
    public ResponseEntity<Void> deleteMatiereById(@PathVariable Integer idMatiere) {
        matiereService.deleteMatiereById(idMatiere);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idMatiere}")
    public ResponseEntity<MatiereResponse> updateMatiere(@PathVariable Integer idMatiere, @RequestBody MatiereRequest matiereRequest) {
        MatiereResponse updatedMatiere = matiereService.updateMatiere(idMatiere, matiereRequest);
        return ResponseEntity.ok(updatedMatiere);
    }

}
