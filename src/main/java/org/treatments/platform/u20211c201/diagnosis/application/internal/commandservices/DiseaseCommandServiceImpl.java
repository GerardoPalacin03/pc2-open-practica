package org.treatments.platform.u20211c201.diagnosis.application.internal.commandservices;

import org.treatments.platform.u20211c201.diagnosis.application.commandservices.DiseaseCommandService;
import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.commands.CreateDiseaseCommand;
import org.treatments.platform.u20211c201.diagnosis.domain.repositories.DiseaseRepository;
import org.treatments.platform.u20211c201.shared.application.result.ApplicationError;
import org.treatments.platform.u20211c201.shared.application.result.Result;
import org.springframework.stereotype.Service;

/**
 * Application service that executes disease commands.
 *
 * @author Apellidos, Nombres
 */
@Service
public class DiseaseCommandServiceImpl implements DiseaseCommandService {
    private final DiseaseRepository diseaseRepository;

    /**
     * Constructor.
     *
     * @param diseaseRepository the {@link DiseaseRepository} port
     */
    public DiseaseCommandServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Fails with a conflict error when a disease with the same name already exists for the same
     * causative agent and diagnosis.</p>
     */
    @Override
    public Result<Long, ApplicationError> handle(CreateDiseaseCommand command) {
        if (diseaseRepository.existsByDiseaseNameAndCausativeAgentIdAndDiagnosisId(
                command.diseaseName(), command.causativeAgentId(), command.diagnosisId())) {
            return Result.failure(ApplicationError.conflict("Disease",
                    "Disease '%s' already exists for causative agent %d and diagnosis %d".formatted(
                            command.diseaseName(),
                            command.causativeAgentId().causativeAgentId(),
                            command.diagnosisId().diagnosisId())));
        }
        var disease = new Disease(command);
        try {
            disease = diseaseRepository.save(disease);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-disease", e.getMessage()));
        }
        return Result.success(disease.getId());
    }
}
