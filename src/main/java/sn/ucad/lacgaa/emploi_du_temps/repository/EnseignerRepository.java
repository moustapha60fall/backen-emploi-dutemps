package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Enseigner;

@Repository
public interface EnseignerRepository extends JpaRepository<Enseigner, Integer> {
}
