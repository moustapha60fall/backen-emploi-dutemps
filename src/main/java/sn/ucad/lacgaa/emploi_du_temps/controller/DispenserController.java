package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.DispenserRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.DispenserResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.DispenserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dispenser")
public class DispenserController {

    private final DispenserService dispenserService;

    public DispenserController(DispenserService dispenserService) {
        this.dispenserService = dispenserService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<DispenserResponse> ajouterDispenser(@RequestBody DispenserRequest dispenserRequest) {
        DispenserResponse dispenserResponse = dispenserService.createDispensation(dispenserRequest);
        return ResponseEntity.ok(dispenserResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DispenserResponse>> getAllDispenser() {
        List<DispenserResponse> dispenserResponses = dispenserService.getAllDispensations();
        return ResponseEntity.ok(dispenserResponses);
    }

    @GetMapping("/{idDispensation}")
    public ResponseEntity<DispenserResponse> getDispenserById(@PathVariable Integer idDispensation) {
        DispenserResponse dispenserResponse = dispenserService.getDispensationById(idDispensation);
        return ResponseEntity.ok(dispenserResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteDispensers(@RequestBody List<Integer> ids) {
        dispenserService.deleteDispensers(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idDispensation}")
    public ResponseEntity<Void> deleteDispenserById(@PathVariable Integer idDispensation) {
        dispenserService.deleteDispensationById(idDispensation);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idDispensation}")
    public ResponseEntity<DispenserResponse> updateDispenser(@PathVariable Integer idDispensation, @RequestBody DispenserRequest dispenserRequest) {
        DispenserResponse updatedDispenser = dispenserService.updateDispensation(idDispensation, dispenserRequest);
        return ResponseEntity.ok(updatedDispenser);
    }
}
