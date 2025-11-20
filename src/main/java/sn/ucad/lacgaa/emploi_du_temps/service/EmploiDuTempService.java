package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.EmploiDuTempRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.EmploiDuTempResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Classe;
import sn.ucad.lacgaa.emploi_du_temps.models.EmploiDuTemps;
import sn.ucad.lacgaa.emploi_du_temps.repository.ClasseRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.EmploiDuTempRepository;

import java.util.List;

@Service
@Transactional
public class EmploiDuTempService {

    private final EmploiDuTempRepository emploiDuTempRepository;
    private final ClasseRepository classeRepository;

    public EmploiDuTempService(EmploiDuTempRepository emploiDuTempRepository, ClasseRepository classeRepository) {
        this.emploiDuTempRepository = emploiDuTempRepository;
        this.classeRepository = classeRepository;
    }

    // Créer un nouvel emploi du temps
    public EmploiDuTempResponse createEmploiDuTemps(EmploiDuTempRequest emploiDuTempRequest) {

        Classe classe = classeRepository.findById(emploiDuTempRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + emploiDuTempRequest.getIdClasse()));

        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        emploiDuTemps.setClasse(classe);
        emploiDuTemps.setAnneeAcademique(emploiDuTempRequest.getAnneeAcademique());
        emploiDuTemps.setSemestre(emploiDuTempRequest.getSemestre());

        EmploiDuTemps savedEmploiDuTemps = emploiDuTempRepository.save(emploiDuTemps);
        return mapToEmploiDuTempsResponse(savedEmploiDuTemps);
    }

    // Obtenir tous les emploiDuTemps
    public List<EmploiDuTempResponse> getAllEmploiDuTemps() {
        return emploiDuTempRepository.findAll()
                .stream()
                .map(this::mapToEmploiDuTempsResponse)
                .toList();
    }

    // Obtenir un emploiDuTemps par son ID
    public EmploiDuTempResponse getEmploiDuTempById(Integer idEmploiDuTemp) {
        EmploiDuTemps emploiDuTemps = emploiDuTempRepository.findById(idEmploiDuTemp)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Cours introuvable avec l'ID : " + idEmploiDuTemp));
        return mapToEmploiDuTempsResponse(emploiDuTemps);
    }

    // Mettre à jour un emploi du temps
    public EmploiDuTempResponse updateEmploiDuTemps(Integer idEmploiDuTemp, EmploiDuTempRequest emploiDuTempRequest) {
        EmploiDuTemps emploiDuTemp = emploiDuTempRepository.findById(idEmploiDuTemp)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Emploi du temps introuvable avec l'ID : " + idEmploiDuTemp));

        Classe classe = classeRepository.findById(emploiDuTempRequest.getIdClasse())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + emploiDuTempRequest.getIdClasse()));

        emploiDuTemp.setClasse(classe);
        emploiDuTemp.setAnneeAcademique(emploiDuTempRequest.getAnneeAcademique());
        emploiDuTemp.setSemestre(emploiDuTempRequest.getSemestre());

        EmploiDuTemps updatedEmploiDuTemp = emploiDuTempRepository.save(emploiDuTemp);
        return mapToEmploiDuTempsResponse(updatedEmploiDuTemp);
    }

    public void deleteEmploiDuTemps(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<EmploiDuTemps> emploiDuTempsToDelete = emploiDuTempRepository.findAllById(ids);

        if (emploiDuTempsToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de emploiDuTemps n'existent pas dans la base de données.");
        }

        emploiDuTempRepository.deleteAll(emploiDuTempsToDelete);
    }

    // Supprimer un emploi du temps par ID
    public void deleteEmploiDuTempById(Integer idEmploiDuTemp) {
        if (!emploiDuTempRepository.existsById(idEmploiDuTemp)) {
            throw new ModelException("ID_NOT_FOUND", "Emploi du temps introuvable avec l'ID : " + idEmploiDuTemp);
        }
        emploiDuTempRepository.deleteById(idEmploiDuTemp);
    }

    // Mapper vers EmploiDuTempResponse
    private EmploiDuTempResponse mapToEmploiDuTempsResponse(EmploiDuTemps emploiDuTemps) {
        EmploiDuTempResponse response = new EmploiDuTempResponse();
        response.setIdEmploiDuTemp(emploiDuTemps.getIdEmploiDuTemp());
        response.setIdClasse(emploiDuTemps.getClasse().getIdClasse());
        response.setNomClasse(emploiDuTemps.getClasse().getNomClasse());
        response.setAnneeAcademique(emploiDuTemps.getAnneeAcademique());
        response.setSemestre(emploiDuTemps.getSemestre());
        return response;
    }

}
