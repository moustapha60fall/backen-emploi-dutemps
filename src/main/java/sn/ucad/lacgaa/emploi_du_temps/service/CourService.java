package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.CourRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.CourResponse;
import sn.ucad.lacgaa.emploi_du_temps.enums.Jours;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.*;
import sn.ucad.lacgaa.emploi_du_temps.repository.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourService {

    private final CourRepository courRepository;
    private final MatiereRepository matiereRepository;
    private final ProfesseurRepository professeurRepository;
    private final SalleRepository salleRepository;
    private final ClasseRepository classeRepository;
    private final EmploiDuTempRepository emploiDuTempRepository;

    public CourService(CourRepository courRepository, MatiereRepository matiereRepository, ProfesseurRepository professeurRepository, SalleRepository salleRepository, ClasseRepository classeRepository, EmploiDuTempRepository emploiDuTempRepository) {
        this.courRepository = courRepository;
        this.matiereRepository = matiereRepository;
        this.professeurRepository = professeurRepository;
        this.salleRepository = salleRepository;
        this.classeRepository = classeRepository;
        this.emploiDuTempRepository = emploiDuTempRepository;
    }

    // Créer un nouveau cours
    public CourResponse createCour(CourRequest courRequest) {
        Matiere matiere = matiereRepository.findById(courRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + courRequest.getIdMatiere()));

        Professeur professeur = professeurRepository.findById(courRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + courRequest.getIdProfesseur()));

        Salle salle = salleRepository.findById(courRequest.getIdSalle())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Salle introuvable avec l'ID : " + courRequest.getIdSalle()));

        Classe classe = classeRepository.findById(courRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + courRequest.getIdClasse()));
        EmploiDuTemps emploiDuTemps = emploiDuTempRepository.findById(courRequest.getIdEmploiDuTemp())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "EmploiDuTemps introuvable avec l'ID : " + courRequest.getIdEmploiDuTemp()));

        // Vérifier que la classe de l'emploi du temps correspond à la classe du cours
        if (!emploiDuTemps.getClasse().getIdClasse().equals(classe.getIdClasse())) {
            throw new ModelException("CLASSE_MISMATCH", "La classe associée au cours ne correspond pas à celle de l'emploi du temps.");
        }

        Cours cours = new Cours();
        cours.setCodeCours(courRequest.getCodeCours());
        cours.setMatiere(matiere);
        cours.setProfesseur(professeur);
        cours.setSalle(salle);
        cours.setClasse(classe);
        cours.setEmploiDuTemps(emploiDuTemps);
        cours.setTypeCours(courRequest.getTypeCours());
        cours.setJours(courRequest.getJours());
        cours.setHoraire(courRequest.getHeureDebut() + " - " + courRequest.getHeureFin());
        cours.setHeureDebut(courRequest.getHeureDebut());
        cours.setHeureFin(courRequest.getHeureFin());

        // Vérifier les conflits avant d'enregistrer
        verifierConflits(cours);

        Cours savedCours = courRepository.save(cours);
        return mapToCourResponse(savedCours);
    }

    // Mettre à jour une cour
    public CourResponse updateCour(Integer idCour, CourRequest courRequest) {
        Cours cours = courRepository.findById(idCour)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Cour introuvable avec l'ID : " + idCour));
        return getCourResponse(courRequest, cours);
    }

    private CourResponse getCourResponse(CourRequest courRequest, Cours cours) {
        // Mettre à jour les propriétés du cours avec les données de la requête
        cours.setCodeCours(courRequest.getCodeCours());
        cours.setTypeCours(courRequest.getTypeCours());
        cours.setJours(courRequest.getJours());
        cours.setHoraire(courRequest.getHeureDebut() + " - " + courRequest.getHeureFin());
        cours.setHeureDebut(courRequest.getHeureDebut());
        cours.setHeureFin(courRequest.getHeureFin());

        // Mettre à jour les relations (matière, professeur, salle, classe, emploi du temps)
        Matiere matiere = matiereRepository.findById(courRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + courRequest.getIdMatiere()));
        cours.setMatiere(matiere);

        Professeur professeur = professeurRepository.findById(courRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + courRequest.getIdProfesseur()));
        cours.setProfesseur(professeur);

        Salle salle = salleRepository.findById(courRequest.getIdSalle())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Salle introuvable avec l'ID : " + courRequest.getIdSalle()));
        cours.setSalle(salle);

        Classe classe = classeRepository.findById(courRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + courRequest.getIdClasse()));
        cours.setClasse(classe);

        EmploiDuTemps emploiDuTemps = emploiDuTempRepository.findById(courRequest.getIdEmploiDuTemp())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "EmploiDuTemps introuvable avec l'ID : " + courRequest.getIdEmploiDuTemp()));
        cours.setEmploiDuTemps(emploiDuTemps);

        // Vérifier les conflits avant de mettre à jour
        verifierConflits(cours);

        // Enregistrer les modifications
        Cours updatedCours = courRepository.save(cours);

        // Retourner la réponse
        return mapToCourResponse(updatedCours);
    }


    // Obtenir tous les cours
    public List<CourResponse> getAllCours() {
        return courRepository.findAll().stream()
                .map(this::mapToCourResponse)
                .toList();
    }

    // Obtenir un cours par son ID
    public CourResponse getCourById(Integer idCour) {
        Cours cours = courRepository.findById(idCour)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Cours introuvable avec l'ID : " + idCour));
        return mapToCourResponse(cours);
    }

    // Regrouper les cours par jour d'un EmploiDuTemp
    public Map<Jours, List<CourResponse>> getCoursGroupedByJours(Integer idEmploiDuTemp) {
        return courRepository.findByIdEmploiDuTemp(idEmploiDuTemp)
                .stream()
                .map(this::mapToCourResponse)
                .collect(Collectors.groupingBy(CourResponse::getJours));
    }

    public void deleteCours(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Cours> coursToDelete = courRepository.findAllById(ids);

        if (coursToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de cour n'existent pas dans la base de données.");
        }

        courRepository.deleteAll(coursToDelete);
    }

    // Supprimer une cour par ID
    public void deleteCourById(Integer idCour) {
        if (!courRepository.existsById(idCour)) {
            throw new ModelException("ID_NOT_FOUND", "Cour introuvable avec l'ID : " + idCour);
        }
        courRepository.deleteById(idCour);
    }

    private void verifierConflits(Cours cours) {
        Integer coursId = cours.getIdCour();

        boolean conflitSalle = courRepository.existsByJoursAndHeureDebutAndHeureFinAndSalleAndIdCourNot(
                cours.getJours(), cours.getHeureDebut(), cours.getHeureFin(), cours.getSalle(), coursId
        );

        boolean conflitClasse = courRepository.existsByJoursAndHeureDebutAndHeureFinAndClasseAndIdCourNot(
                cours.getJours(), cours.getHeureDebut(), cours.getHeureFin(), cours.getClasse(), coursId
        );

        boolean conflitEmploiDuTemps = courRepository.existsByJoursAndHeureDebutAndHeureFinAndEmploiDuTempsAndClasseAndIdCourNot(
                cours.getJours(), cours.getHeureDebut(), cours.getHeureFin(), cours.getEmploiDuTemps(), cours.getClasse(), coursId
        );

        if (conflitSalle) {
            throw new ModelException("CONFLIT_SALLE", "La salle " + cours.getSalle().getNomSalle() +
                    " est déjà occupée de " + cours.getHeureDebut() + " à " + cours.getHeureFin());
        }

        if (conflitClasse) {
            throw new ModelException("CONFLIT_CLASSE", "La classe " + cours.getClasse().getNomClasse() +
                    " a déjà un cours prévu de " + cours.getHeureDebut() + " à " + cours.getHeureFin());
        }

        if (conflitEmploiDuTemps) {
            throw new ModelException("CONFLIT_EMPLOI_DU_TEMPS", "Un autre cours est déjà planifié dans cet emploi du temps à ce créneau.");
        }
    }

    // Mapper vers CourResponse
    private CourResponse mapToCourResponse(Cours cours) {
        CourResponse response = new CourResponse();
        response.setIdCour(cours.getIdCour());
        response.setIdSalle(cours.getSalle().getIdSalle());
        response.setIdClasse(cours.getClasse().getIdClasse());
        response.setIdProfesseur(cours.getProfesseur().getIdProfesseur());
        response.setIdMatiere(cours.getMatiere().getIdMatiere());
        response.setIdEmploiDuTemp(cours.getEmploiDuTemps().getIdEmploiDuTemp());
        response.setCodeCours(cours.getCodeCours());
        response.setNomMatiere(cours.getMatiere().getNomMatiere());
        response.setNomProfesseur(cours.getProfesseur().getUtilisateur().getLastName());
        response.setTitreProfesseur(cours.getProfesseur().getTitre());
        response.setNomSalle(cours.getSalle().getNomSalle());
        response.setNomClasse(cours.getClasse().getNomClasse());
        response.setTypeCours(cours.getTypeCours());
        response.setJours(cours.getJours());
        response.setHoraire(cours.getHoraire());
        response.setHeureDebut(cours.getHeureDebut());
        response.setHeureFin(cours.getHeureFin());
        response.setAnneeAcademique(cours.getEmploiDuTemps().getAnneeAcademique());
        response.setSemestre(cours.getEmploiDuTemps().getSemestre());
        return response;
    }
}
