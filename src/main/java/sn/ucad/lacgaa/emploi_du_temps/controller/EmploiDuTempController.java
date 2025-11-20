package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EmploiDuTempRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EmploiDuTempResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.EmploiDuTempService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emploiDuTemps")
public class EmploiDuTempController {

    private final EmploiDuTempService emploiDuTempService;

    public EmploiDuTempController(EmploiDuTempService emploiDuTempService) {
        this.emploiDuTempService = emploiDuTempService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<EmploiDuTempResponse> ajouterEmploiDuTemp(@RequestBody EmploiDuTempRequest emploiDuTempRequest) {
        EmploiDuTempResponse emploiDuTempResponse = emploiDuTempService.createEmploiDuTemps(emploiDuTempRequest);
        return ResponseEntity.ok(emploiDuTempResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmploiDuTempResponse>> getAllEmploiDuTemp() {
        List<EmploiDuTempResponse> emploiDuTempResponses = emploiDuTempService.getAllEmploiDuTemps();
        return ResponseEntity.ok(emploiDuTempResponses);
    }

    @GetMapping("/{idEmploiDuTemp}")
    public ResponseEntity<EmploiDuTempResponse> getEmploiDuTempById(@PathVariable Integer idEmploiDuTemp) {
        EmploiDuTempResponse emploiDuTempResponse = emploiDuTempService.getEmploiDuTempById(idEmploiDuTemp);
        return ResponseEntity.ok(emploiDuTempResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteEmploiDuTemps(@RequestBody List<Integer> ids) {
        emploiDuTempService.deleteEmploiDuTemps(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idEmploiDuTemp}")
    public ResponseEntity<Void> deleteEmploiDuTempById(@PathVariable Integer idEmploiDuTemp) {
        emploiDuTempService.deleteEmploiDuTempById(idEmploiDuTemp);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idEmploiDuTemp}")
    public ResponseEntity<EmploiDuTempResponse> updateClasse(@PathVariable Integer idEmploiDuTemp, @RequestBody EmploiDuTempRequest emploiDuTempRequest) {
        EmploiDuTempResponse updateEmploiDuTemp = emploiDuTempService.updateEmploiDuTemps(idEmploiDuTemp, emploiDuTempRequest);
        return ResponseEntity.ok(updateEmploiDuTemp);
    }

}
