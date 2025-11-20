package sn.ucad.lacgaa.emploi_du_temps.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.RoleResponse;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.UploadResponse;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.UtilisateurResponse;
import sn.ucad.lacgaa.emploi_du_temps.service.UtilisateurService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/utilisateurs")
public class UtilisateurController {

    @Value("${file.upload-dir-profil}")
    private String uploadDir;
    private final UtilisateurService utilisateurService;
    private final ResourceLoader resourceLoader;

    public UtilisateurController(UtilisateurService utilisateurService, ResourceLoader resourceLoader) {
        this.utilisateurService = utilisateurService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/logged")
    public ResponseEntity<UtilisateurResponse> getLoggedUtilisateurInfo() {
        UtilisateurResponse utilisateurResponse = utilisateurService.getLoggedUtilisateur();
        return ResponseEntity.ok(utilisateurResponse);
    }

    @GetMapping("/{email}/roles")
    public ResponseEntity<Set<RoleResponse>> getRolesByUtilisateurEmail(@PathVariable String email) {
        Set<RoleResponse> roleResponses = utilisateurService.getRolesByEmail(email);
        return ResponseEntity.ok(roleResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtilisateurResponse>> getAllUtilisateur() {
        List<UtilisateurResponse> utilisateurResponse = utilisateurService.getAllUtilisateur();
        return ResponseEntity.ok(utilisateurResponse);
    }

    @GetMapping("/sans-attribution")
    public ResponseEntity<List<UtilisateurResponse>> getUtilisateursSansAttribution() {
        List<UtilisateurResponse> utilisateurResponse = utilisateurService.getUtilisateursSansAttribution();
        return ResponseEntity.ok(utilisateurResponse);
    }

    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<UtilisateurResponse> getUtilisateurById(@PathVariable Integer idUtilisateur) {
        UtilisateurResponse utilisateurResponse = utilisateurService.getUtilisateurById(idUtilisateur);
        return ResponseEntity.ok(utilisateurResponse);
    }

    @PutMapping("/update/{idUtilisateur}")
    public ResponseEntity<UtilisateurResponse> updateUtilisateur(
            @PathVariable Integer idUtilisateur,
            @RequestBody UtilisateurResponse updatedUser) {

        UtilisateurResponse utilisateurResponse = utilisateurService.updateUtilisateur(idUtilisateur, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(utilisateurResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUtilisateurs(@RequestBody List<Integer> ids) {
        utilisateurService.deleteUtilisateurs(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idUtilisateur}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Integer idUtilisateur) {
        utilisateurService.deleteProfil(idUtilisateur);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadImage(
            @RequestParam("imageProfil") MultipartFile image) {

        if (image == null || image.isEmpty() || !Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setMessage("Image non fournie ou format de fichier non pris en charge");
            return ResponseEntity.badRequest().body(uploadResponse);
        }

        try {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            try (OutputStream outputStream = Files.newOutputStream(filePath)) {
                outputStream.write(image.getBytes());
            }

            utilisateurService.updateProfileImage(fileName);

            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setFileName(fileName);
            uploadResponse.setMessage("Image téléchargée avec succès");
            return ResponseEntity.ok(uploadResponse);
        } catch (IOException e) {
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setMessage("Erreur lors de l'enregistrement de l'image");
            return ResponseEntity.badRequest().body(uploadResponse);
        }
    }

    @GetMapping("/image/{imageProfil}")
    public ResponseEntity<Resource> getProfilImage(@PathVariable String imageProfil) {
        String imagePath = uploadDir + "/" + imageProfil;

        try {
            Resource imageResource = resourceLoader.getResource("file:" + imagePath);

            if (imageResource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Spécifiez le type MIME de l'image.
                        .body(imageResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
