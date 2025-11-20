package sn.ucad.lacgaa.emploi_du_temps.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sn.ucad.lacgaa.emploi_du_temps.dto.request.ClasseRequest;
import sn.ucad.lacgaa.emploi_du_temps.dto.response.ClasseResponse;
import sn.ucad.lacgaa.emploi_du_temps.exception.ModelException;
import sn.ucad.lacgaa.emploi_du_temps.models.Classe;
import sn.ucad.lacgaa.emploi_du_temps.repository.ClasseRepository;

import java.util.List;

@Service
@Transactional
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    // Créer une nouvelle classe
    public ClasseResponse createClasse(ClasseRequest classeRequest) {
        Classe classe = new Classe();
        return getClasseResponse(classeRequest, classe);
    }

    // Obtenir toutes les classes
    public List<ClasseResponse> getAllClasses() {
        return classeRepository.findAll().stream()
                .map(this::mapToClasseResponse)
                .toList();
    }

    // Obtenir une classe par son ID
    public ClasseResponse getClasseById(Integer idClasse) {
        Classe classe = classeRepository.findById(idClasse)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + idClasse));
        return mapToClasseResponse(classe);
    }

    // Mettre à jour une classe
    public ClasseResponse updateClasse(Integer idClasse, ClasseRequest classeRequest) {
        Classe classe = classeRepository.findById(idClasse)
                .orElseThrow(() -> new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + idClasse));
        return getClasseResponse(classeRequest, classe);
    }

    public void deleteClasses(List<Integer> ids) {
        // Vérifiez si tous les IDs existent dans la base de données
        List<Classe> classesToDelete = classeRepository.findAllById(ids);

        if (classesToDelete.size() != ids.size()) {
            throw new ModelException("ID_NOT_FOUND", "Certains IDs de classe n'existent pas dans la base de données.");
        }

        classeRepository.deleteAll(classesToDelete);
    }

    // Supprimer une classe par ID
    public void deleteClasseById(Integer idClasse) {
        if (!classeRepository.existsById(idClasse)) {
            throw new ModelException("ID_NOT_FOUND", "Classe introuvable avec l'ID : " + idClasse);
        }
        classeRepository.deleteById(idClasse);
    }

    private ClasseResponse getClasseResponse(ClasseRequest classeRequest, Classe classe) {
        classe.setCodeClasse(classeRequest.getCodeClasse());
        classe.setNomClasse(classeRequest.getNiveau() + " " + classeRequest.getTypeFiliere() + " - " + classeRequest.getOption());
        classe.setTypeFiliere(classeRequest.getTypeFiliere());
        classe.setNiveau(classeRequest.getNiveau());
        classe.setOption(classeRequest.getOption());

        Classe updatedClasse = classeRepository.save(classe);
        return mapToClasseResponse(updatedClasse);
    }

    // Mapper vers ClasseResponse
    private ClasseResponse mapToClasseResponse(Classe classe) {
        ClasseResponse response = new ClasseResponse();
        response.setIdClasse(classe.getIdClasse());
        response.setCodeClasse(classe.getCodeClasse());
        response.setNomClasse(classe.getNomClasse());
        response.setTypeFiliere(classe.getTypeFiliere());
        response.setNiveau(classe.getNiveau());
        response.setOption(classe.getOption());
        return response;
    }

}
