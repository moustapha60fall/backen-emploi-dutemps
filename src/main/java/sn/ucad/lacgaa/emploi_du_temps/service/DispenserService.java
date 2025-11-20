package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.DispenserRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.DispenserResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Dispenser;
import sn.ucad.lacgaa.emploi_du_temps.models.Matiere;
import sn.ucad.lacgaa.emploi_du_temps.models.Professeur;
import sn.ucad.lacgaa.emploi_du_temps.repository.DispenserRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.MatiereRepository;
import sn.ucad.lacgaa.emploi_du_temps.repository.ProfesseurRepository;

import java.util.List;

@Service
@Transactional
public class DispenserService {

    private final DispenserRepository dispenserRepository;
    private final ProfesseurRepository professeurRepository;
    private final MatiereRepository matiereRepository;

    public DispenserService(DispenserRepository dispenserRepository,
                            ProfesseurRepository professeurRepository,
                            MatiereRepository matiereRepository) {
        this.dispenserRepository = dispenserRepository;
        this.professeurRepository = professeurRepository;
        this.matiereRepository = matiereRepository;
    }

    // Créer une nouvelle dispensation
    public DispenserResponse createDispensation(DispenserRequest dispenserRequest) {
        Professeur professeur = professeurRepository.findById(dispenserRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + dispenserRequest.getIdProfesseur()));

        Matiere matiere = matiereRepository.findById(dispenserRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + dispenserRequest.getIdMatiere()));

        Dispenser dispensation = new Dispenser();
        dispensation.setProfesseur(professeur);
        dispensation.setMatiere(matiere);

        Dispenser savedDispensation = dispenserRepository.save(dispensation);
        return mapToDispenserResponse(savedDispensation);
    }

    // Obtenir toutes les dispensations
    public List<DispenserResponse> getAllDispensations() {
        return dispenserRepository.findAll().stream()
                .map(this::mapToDispenserResponse)
                .toList();
    }

    // Obtenir une dispensation par son ID
    public DispenserResponse getDispensationById(Integer idDispensation) {
        Dispenser dispensation = dispenserRepository.findById(idDispensation)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Dispensation introuvable avec l'ID : " + idDispensation));
        return mapToDispenserResponse(dispensation);
    }

    // Mettre à jour une dispensation
    public DispenserResponse updateDispensation(Integer idDispensation, DispenserRequest dispenserRequest) {
        Dispenser dispensation = dispenserRepository.findById(idDispensation)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Dispensation introuvable avec l'ID : " + idDispensation));

        Professeur professeur = professeurRepository.findById(dispenserRequest.getIdProfesseur())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Professeur introuvable avec l'ID : " + dispenserRequest.getIdProfesseur()));

        Matiere matiere = matiereRepository.findById(dispenserRequest.getIdMatiere())
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Matière introuvable avec l'ID : " + dispenserRequest.getIdMatiere()));

        dispensation.setProfesseur(professeur);
        dispensation.setMatiere(matiere);

        Dispenser updatedDispensation = dispenserRepository.save(dispensation);
        return mapToDispenserResponse(updatedDispensation);
    }

    public void deleteDispensers(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Dispenser> dispensationsToDelete = dispenserRepository.findAllById(ids);

        if (dispensationsToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de dispensations n'existent pas dans la base de données.");
        }

        dispenserRepository.deleteAll(dispensationsToDelete);
    }

    // Supprimer une dispensation par ID
    public void deleteDispensationById(Integer idDispensation) {
        if (!dispenserRepository.existsById(idDispensation)) {
            throw new ModelException("ID_NOT_FOUND", "Dispensation introuvable avec l'ID : " + idDispensation);
        }
        dispenserRepository.deleteById(idDispensation);
    }

    // Mapper vers DispenserResponse
    private DispenserResponse mapToDispenserResponse(Dispenser dispensation) {
        DispenserResponse response = new DispenserResponse();
        response.setIdDispensation(dispensation.getIdDispensation());
        response.setIdProfesseur(dispensation.getProfesseur().getIdProfesseur());
        response.setIdMatiere(dispensation.getMatiere().getIdMatiere());
        response.setNomMatiere(dispensation.getMatiere().getNomMatiere());
        response.setNomProfesseur(dispensation.getProfesseur().getUtilisateur().getLastName());
        response.setPrenomProfesseur(dispensation.getProfesseur().getUtilisateur().getLastName());
        response.setTitreProfesseur(dispensation.getProfesseur().getTitre());
        response.setEmailProfesseur(dispensation.getProfesseur().getUtilisateur().getEmail());
        response.setPhoneNumberProfrsseur(dispensation.getProfesseur().getUtilisateur().getPhoneNumber());
        return response;
    }
}
