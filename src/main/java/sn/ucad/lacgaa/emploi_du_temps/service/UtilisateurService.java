package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.RoleResponse;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.UtilisateurResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Region;
import sn.ucad.lacgaa.emploi_du_temps.models.Utilisateur;
import sn.ucad.lacgaa.emploi_du_temps.models.Ville;
import sn.ucad.lacgaa.emploi_du_temps.repository.RegionRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.UtilisateurRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.VilleRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;
    private final RegionRepository regionRepository;
    private final VilleRepository villeRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, RegionRepository regionRepository, VilleRepository villeRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.regionRepository = regionRepository;
        this.villeRepository = villeRepository;
    }

    public UtilisateurResponse getLoggedUtilisateur() {
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = String.valueOf(token.getTokenAttributes().get("email"));

        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ModelException("USER_NOT_FOUND", "Utilisateur introuvable avec l'email: " + email));

        return mapToUtilisateurResponse(user);
    }

    public void syncUser(Utilisateur user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ModelException("SYNC_ERROR", "L'email est requis pour synchroniser l'utilisateur");
        }

        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(user.getEmail());

        Utilisateur saveUser = optionalUser.orElseGet(() -> {
            Utilisateur newUser = new Utilisateur();
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmailVerified(user.getEmailVerified());

            // Récupération de la région et ville "Dakar" en base
            Region defaultRegion = regionRepository.findByNomRegion("Dakar")
                    .orElseThrow(() -> new ModelException("REGION_NOT_FOUND", "La région Dakar n'existe pas en base"));

            Ville defaultVille = (Ville) villeRepository.findByNomVille("Dakar")
                    .orElseThrow(() -> new ModelException("VILLE_NOT_FOUND", "La ville Dakar n'existe pas en base"));

            newUser.setRegion(defaultRegion);
            newUser.setVille(defaultVille);

            return newUser;
        });

        // Mise à jour des informations si l'utilisateur existe déjà
        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());

        // Sauvegarde en base de données
        utilisateurRepository.save(saveUser);
    }

    public Set<RoleResponse> getRolesByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ModelException("USER_NOT_FOUND", "Utilisateur introuvable pour l'email : " + email));
        return utilisateur.getRoles().stream()
                .map(role -> new RoleResponse(role.getIdRole(), role.getNomRole()))
                .collect(Collectors.toSet());
    }

    public List<UtilisateurResponse> getAllUtilisateur() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::mapToUtilisateurResponse)
                .collect(Collectors.toList());
    }

    public List<UtilisateurResponse> getUtilisateursSansAttribution() {
        return utilisateurRepository.findUtilisateursSansAttribution()
                .stream()
                .map(this::mapToUtilisateurResponse)
                .collect(Collectors.toList());
    }

    public UtilisateurResponse getUtilisateurById(Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Utilisateur introuvable avec l'ID : " + idUtilisateur));
        return mapToUtilisateurResponse(utilisateur);
    }

    // Mettre à jour l'image de profil
    public void updateProfileImage(String fileName) {
        UtilisateurResponse utilisateurResponse = getLoggedUtilisateur();
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurResponse.getIdUtilisateur())
                .orElseThrow(() -> new ModelException("USER_NOT_FOUND", "Utilisateur introuvable avec l'ID : " + utilisateurResponse.getIdUtilisateur()));
        utilisateur.setImageProfil(fileName);
        utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateurs(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Utilisateur> utilisateursToDelete = utilisateurRepository.findAllById(ids);

        if (utilisateursToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de utilisateurs n'existent pas dans la base de données.");
        }

        utilisateurRepository.deleteAll(utilisateursToDelete);
    }

    // Supprimer un utilisateur
    public void deleteProfil(Integer idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
    }


    public void saveUserSession(String email) {
        String userId = getUserIdFromKeycloak(email);
        if (userId == null) {
            throw new ModelException("USER_NOT_FOUND", "Utilisateur introuvable dans Keycloak");
        }

        List<Map<String, Object>> sessions = getUserSessionsFromKeycloak(userId);
        if (sessions == null) {
            throw new ModelException("SESSION_NOT_FOUND", "Aucune session trouvée pour cet utilisateur");
        }

        for (Map<String, Object> session : sessions) {
            Long startedTimestamp = (Long) session.get("start");
            Long lastAccessTimestamp = (Long) session.get("lastAccess");

            String started = Instant.ofEpochMilli(startedTimestamp)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm:ss a"));

            String lastAccess = Instant.ofEpochMilli(lastAccessTimestamp)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm:ss a"));

            Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new ModelException("USER_NOT_FOUND", "Utilisateur introuvable en base de données"));

            utilisateur.setSessionStart(started);
            utilisateur.setLastSessionActivity(lastAccess);

            utilisateurRepository.save(utilisateur);
        }
    }

    private String getUserIdFromKeycloak(String email) {
        String url = "http://localhost:8080/admin/realms/my-realm/users?email=" + email;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAdminToken());

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);

        if (response.getBody() != null && !response.getBody().isEmpty()) {
            Map<String, Object> user = (Map<String, Object>) response.getBody().get(0);
            return (String) user.get("id");
        }
        return null;
    }

    private List<Map<String, Object>> getUserSessionsFromKeycloak(String userId) {
        String url = "http://localhost:8080/admin/realms/my-realm/users/" + userId + "/sessions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAdminToken());

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);

        return response.getBody();
    }

    private String getAdminToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/realms/master/protocol/openid-connect/token";

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", "admin-cli");
        requestBody.add("grant_type", "password");
        requestBody.add("username", "tmpadmin");
        requestBody.add("password", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    // Mapper vers UtilisateurResponse
    private UtilisateurResponse mapToUtilisateurResponse(Utilisateur utilisateur) {
        UtilisateurResponse response = new UtilisateurResponse();
        response.setIdUtilisateur(utilisateur.getIdUtilisateur());
        response.setIdRegion(utilisateur.getRegion().getIdRegion());
        response.setNomRegion(utilisateur.getRegion().getNomRegion());
        response.setIdVille(utilisateur.getVille().getIdVille());
        response.setNomVille(utilisateur.getVille().getNomVille());

        response.setEmail(utilisateur.getEmail());
        response.setUsername(utilisateur.getUsername());
        response.setLastName(utilisateur.getLastName());
        response.setFirstName(utilisateur.getFirstName());
        response.setPhoneNumber(utilisateur.getPhoneNumber());
        response.setEmailVerified(utilisateur.getEmailVerified());
        response.setCodePIN(utilisateur.getCodePIN());
        response.setImageProfil(utilisateur.getImageProfil());
        response.setDateBirthday(utilisateur.getDateBirthday());
        response.setCiviliteProfil(utilisateur.getCiviliteProfil());
        response.setAddressProfil(utilisateur.getAddressProfil());
        response.setEtesLogee(utilisateur.getEtesLogee());
        response.setBlogPosts(utilisateur.getBlogPosts());
        response.setNewsletter(utilisateur.getNewsletter());
        response.setLastSessionActivity(utilisateur.getLastSessionActivity());
        response.setSessionStart(utilisateur.getSessionStart());
        return response;
    }



    public UtilisateurResponse updateUtilisateur(Integer idUtilisateur, UtilisateurResponse updatedUser) {
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new ModelException("USER_NOT_FOUND", "Utilisateur introuvable avec l'ID: " + idUtilisateur));

        Region region = regionRepository.findById(updatedUser.getIdRegion())
                .orElseThrow(() -> new ModelException(
                        "REGION_NOT_FOUND", "Région avec l'ID " + updatedUser.getIdRegion() + " non trouvée"));

        Ville ville = villeRepository.findById(updatedUser.getIdVille())
                .orElseThrow(() -> new ModelException(
                        "VILLE_NOT_FOUND", "Ville avec l'ID " + updatedUser.getIdVille() + " non trouvée"));

        user.setRegion(region);
        user.setVille(ville);
        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPhoneNumber() != null) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if (updatedUser.getCodePIN() != null) {
            user.setCodePIN(updatedUser.getCodePIN());
        }
        if (updatedUser.getImageProfil() != null) {
            user.setImageProfil(updatedUser.getImageProfil());
        }
        if (updatedUser.getDateBirthday() != null) {
            user.setDateBirthday(updatedUser.getDateBirthday());
        }
        if (updatedUser.getCiviliteProfil() != null) {
            user.setCiviliteProfil(updatedUser.getCiviliteProfil());
        }
        if (updatedUser.getAddressProfil() != null) {
            user.setAddressProfil(updatedUser.getAddressProfil());
        }
        if (updatedUser.getEtesLogee() != null) {
            user.setEtesLogee(updatedUser.getEtesLogee());
        }
        if (updatedUser.getBlogPosts() != null) {
            user.setBlogPosts(updatedUser.getBlogPosts());
        }
        if (updatedUser.getNewsletter() != null) {
            user.setNewsletter(updatedUser.getNewsletter());
        }

        if (updatedUser.getAddressProfil() != null) {
            user.setAddressProfil(updatedUser.getAddressProfil());
        }

        utilisateurRepository.save(user);
        return mapToUtilisateurResponse(user);
    }

}
