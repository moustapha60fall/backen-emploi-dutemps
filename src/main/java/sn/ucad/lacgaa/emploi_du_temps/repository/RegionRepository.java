package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.models.Region;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByNomRegion(String nomRegion);

}
