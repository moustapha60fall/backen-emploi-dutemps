package sn.ucad.lacgaa.emploi_du_temps.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.ucad.lacgaa.emploi_du_temps.models.Utilisateur;
import sn.ucad.lacgaa.emploi_du_temps.service.RoleService;
import sn.ucad.lacgaa.emploi_du_temps.service.UtilisateurService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtUserSyncFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtUserSyncFilter.class);
    private final UtilisateurService utilisateurService;
    private final RoleService roleService;

    public JwtUserSyncFilter(UtilisateurService utilisateurService, RoleService roleService) {
        this.utilisateurService = utilisateurService;
        this.roleService = roleService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.debug("JwtUserSyncFilter: Début du filtre");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken token && token.getTokenAttributes() != null) {
            logger.debug("JwtUserSyncFilter: Token JWT trouvé");
            try {
                // Récupération des claims de l'utilisateur
                Map<String, Object> claims = token.getTokenAttributes();
                String email = (String) claims.getOrDefault("email", "");
                String firstName = (String) claims.getOrDefault("given_name", "");
                String lastName = (String) claims.getOrDefault("family_name", "");
                String username = (String) claims.getOrDefault("preferred_username", "");
                Boolean emailVerified = Boolean.valueOf(String.valueOf(claims.getOrDefault("email_verified", "false")));
                logger.debug("JwtUserSyncFilter: Email récupéré du token : {}", email);

                if (!email.isEmpty()) {
                    Utilisateur user = new Utilisateur();
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setUsername(username);
                    user.setEmailVerified(emailVerified);

                    logger.debug("JwtUserSyncFilter: Synchronisation de l'utilisateur : {}", email);
                    utilisateurService.syncUser(user);

                    utilisateurService.saveUserSession(email);

                    // Synchroniser les rôles
                    List<String> roles = new ArrayList<>();

                    // Rôles au niveau du realm
                    Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
                    if (realmAccess != null && realmAccess.containsKey("roles")) {
                        roles.addAll((List<String>) realmAccess.get("roles"));
                    }

                    // Rôles au niveau des clients (resource_access)
                    Map<String, Object> resourceAccess = (Map<String, Object>) claims.get("resource_access");
                    if (resourceAccess != null) {
                        for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                            Map<String, Object> clientRoles = (Map<String, Object>) entry.getValue();
                            if (clientRoles.containsKey("roles")) {
                                roles.addAll((List<String>) clientRoles.get("roles"));
                            }
                        }
                    }

                    if (!roles.isEmpty()) {
                        logger.debug("JwtUserSyncFilter: Synchronisation des rôles pour l'utilisateur : {}", email);
                        roleService.assignRolesToUser(email, roles);
                    }
                } else {
                    logger.warn("JwtUserSyncFilter: Email manquant dans le token JWT");
                }

            } catch (Exception e) {
                logger.error("JwtUserSyncFilter: Erreur lors de la synchronisation utilisateur : {}", e.getMessage(), e);
            }
        } else {
            logger.debug("JwtUserSyncFilter: Aucun token JWT trouvé ou authentification invalide");
        }

        filterChain.doFilter(request, response);
    }

}
