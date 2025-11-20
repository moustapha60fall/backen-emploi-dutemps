package sn.ucad.lacgaa.emploi_du_temps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.models.Classe;
import sn.ucad.lacgaa.emploi_du_temps.models.Cours;
import sn.ucad.lacgaa.emploi_du_temps.models.EmploiDuTemps;
import sn.ucad.lacgaa.emploi_du_temps.models.Salle;

import java.util.List;

@Repository
public interface CourRepository extends JpaRepository<Cours, Integer> {

    @Query("SELECT c FROM Cours c WHERE c.classe.idClasse = :idClasse AND c.emploiDuTemps.idEmploiDuTemp = :idEmploiDuTemp")
    List<Cours> findByClasseAndEmploiDuTemp(@Param("idClasse") Integer idClasse, @Param("idEmploiDuTemp") Integer idEmploiDuTemp);

    boolean existsByJoursAndHeureDebutAndHeureFinAndSalleAndIdCourNot(Jours jours, String heureDebut, String heureFin, Salle salle, Integer coursId);

    boolean existsByJoursAndHeureDebutAndHeureFinAndClasseAndIdCourNot(Jours jours, String heureDebut, String heureFin, Classe classe, Integer coursId);

    boolean existsByJoursAndHeureDebutAndHeureFinAndEmploiDuTempsAndClasseAndIdCourNot(
            Jours jours, String heureDebut, String heureFin, EmploiDuTemps emploiDuTemps, Classe classe, Integer coursId);

    @Query("SELECT c FROM Cours c WHERE c.emploiDuTemps.idEmploiDuTemp = :idEmploiDuTemp")
    List<Cours> findByIdEmploiDuTemp(@Param("idEmploiDuTemp") Integer idEmploiDuTemp);
}
