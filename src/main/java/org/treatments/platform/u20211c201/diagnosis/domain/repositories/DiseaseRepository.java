package org.treatments.platform.u20211c201.diagnosis.domain.repositories;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;

import java.util.Optional;

/**
 * Diagnosis disease repository port.
 *
 * @author Apellidos, Nombres
 */
public interface DiseaseRepository {
    /**
     * Finds a disease by its identifier.
     *
     * @param id the disease identifier
     * @return the matching disease, if found
     */
    Optional<Disease> findById(Long id);

    /**
     * Saves a disease.
     *
     * @param disease the disease to save
     * @return the saved disease, including its generated identifier
     */
    Disease save(Disease disease);

    /**
     * Checks whether a disease with the same name already exists for the same causative agent and diagnosis.
     *
     * @param diseaseName      the disease name
     * @param causativeAgentId the causative agent identifier
     * @param diagnosisId      the diagnosis identifier
     * @return true if such a disease exists, false otherwise
     */
    boolean existsByDiseaseNameAndCausativeAgentIdAndDiagnosisId(
            String diseaseName, CausativeAgentId causativeAgentId, DiagnosisId diagnosisId);
}
