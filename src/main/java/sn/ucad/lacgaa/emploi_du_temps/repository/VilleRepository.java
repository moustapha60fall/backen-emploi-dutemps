package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Ville;

import java.util.List;
import java.util.Optional;


@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {

    List<Ville> findByRegionIdRegion(Integer idRegion);

    Optional<Object> findByNomVille(String nomVille);
}
