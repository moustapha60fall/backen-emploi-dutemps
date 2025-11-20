package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ClasseRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ClasseResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<ClasseResponse> ajouterClasse(@RequestBody ClasseRequest classeRequest) {
        ClasseResponse classeResponse = classeService.createClasse(classeRequest);
        return ResponseEntity.ok(classeResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClasseResponse>> getAllClasse() {
        List<ClasseResponse> classeResponses = classeService.getAllClasses();
        return ResponseEntity.ok(classeResponses);
    }

    @GetMapping("/{idClasse}")
    public ResponseEntity<ClasseResponse> getClasseById(@PathVariable Integer idClasse) {
        ClasseResponse classeResponse = classeService.getClasseById(idClasse);
        return ResponseEntity.ok(classeResponse);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> deleteClasses(@RequestBody List<Integer> ids) {
        classeService.deleteClasses(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supprimer/{idClasse}")
    public ResponseEntity<Void> deleteClasseById(@PathVariable Integer idClasse) {
        classeService.deleteClasseById(idClasse);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{idClasse}")
    public ResponseEntity<ClasseResponse> updateClasse(@PathVariable Integer idClasse, @RequestBody ClasseRequest classeRequest) {
        ClasseResponse updatedClasse = classeService.updateClasse(idClasse, classeRequest);
        return ResponseEntity.ok(updatedClasse);
    }

}
