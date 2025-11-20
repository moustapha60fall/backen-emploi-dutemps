package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Apprendre;

@Repository
public interface ApprendreRepository extends JpaRepository<Apprendre, Integer> {
}
