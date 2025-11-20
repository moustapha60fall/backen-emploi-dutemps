package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ProfesseurRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ProfesseurResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.ProfesseurService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professeurs")
public class ProfesseurController {

    private final ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<ProfesseurResponse> ajouterProfesseur(@RequestBody ProfesseurRequest professeurRequest) {
        ProfesseurResponse professeurResponse = professeurService.createProfesseur(professeurRequest);
        return ResponseEntity.ok(professeurResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfesseurResponse>> getAllProfesseur() {
        List<ProfesseurResponse> professeurResponses = professeurService.getAllProfesseurs();
        return ResponseEntity.ok(professeurResponses);
    }

    @GetMapping("/{idProfesseur}")
    public ResponseEntity<ProfesseurResponse> getProfesseurById(@PathVariable Integer idProfesseur) {
        ProfesseurResponse professeurResponse = professeurService.getProfesseurById(idProfesseur);
        return ResponseEntity.ok(professeurResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteProfesseurs(@RequestBody List<Integer> ids) {
        professeurService.deleteProfesseurs(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idProfesseur}")
    public ResponseEntity<Void> deleteProfesseurById(@PathVariable Integer idProfesseur) {
        professeurService.deleteProfesseurById(idProfesseur);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idProfesseur}")
    public ResponseEntity<ProfesseurResponse> updateProfesseur(@PathVariable Integer idProfesseur, @RequestBody ProfesseurRequest professeurRequest) {
        ProfesseurResponse updatedProfesseur = professeurService.updateProfesseur(idProfesseur, professeurRequest);
        return ResponseEntity.ok(updatedProfesseur);
    }
}
