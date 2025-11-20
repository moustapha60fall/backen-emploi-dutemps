package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.SalleRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.VilleRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.SalleResponse;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.VilleResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.VilleService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/villes")
public class VilleController {

    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<VilleResponse> ajouterVille(@RequestBody VilleRequest villeRequest) {
        return ResponseEntity.ok(villeService.ajouterVille(villeRequest));
    }

    @GetMapping("/{idVille}")
    public ResponseEntity<VilleResponse> getVilleById(@PathVariable Integer idVille) {
        return ResponseEntity.ok(villeService.getVilleById(idVille));
    }

    @GetMapping("/byRegion/{idRegion}")
    public ResponseEntity<List<VilleResponse>> getVilleByRegion(@PathVariable Integer idRegion) {
        return ResponseEntity.ok(villeService.getVillesByRegion(idRegion));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VilleResponse>> getAllVilles() {
        return ResponseEntity.ok(villeService.getAllVilles());
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteVilles(@RequestBody List<Integer> ids) {
        villeService.deleteVilles(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idSalle}")
    public ResponseEntity<Void> deleteVilleById(@PathVariable Integer idVille) {
        villeService.deleteVilleById(idVille);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idVille}")
    public ResponseEntity<VilleResponse> updateVille(@PathVariable Integer idVille, @RequestBody VilleRequest villeRequest) {
        VilleResponse updatedVille = villeService.updateVille(idVille, villeRequest);
        return ResponseEntity.ok(updatedVille);
    }

}
