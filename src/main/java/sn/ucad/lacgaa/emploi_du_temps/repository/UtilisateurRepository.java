package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);

    @Query("SELECT u FROM Utilisateur u " +
            "WHERE u.idUtilisateur NOT IN (SELECT e.utilisateur.idUtilisateur FROM Etudiant e) " +
            "AND u.idUtilisateur NOT IN (SELECT p.utilisateur.idUtilisateur FROM Professeur p)")
    List<Utilisateur> findUtilisateursSansAttribution();
}
