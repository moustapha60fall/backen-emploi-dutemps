package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {
}
