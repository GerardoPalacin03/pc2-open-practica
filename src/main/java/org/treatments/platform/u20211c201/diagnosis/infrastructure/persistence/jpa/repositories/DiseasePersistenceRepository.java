package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.repositories;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.entities.DiseasePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for disease persistence entities.
 *
 * @author Apellidos, Nombres
 */
@Repository
public interface DiseasePersistenceRepository extends JpaRepository<DiseasePersistenceEntity, Long> {
    /**
     * Checks whether a disease with the same name exists for the same causative agent and diagnosis.
     *
     * @param diseaseName      the disease name
     * @param causativeAgentId the causative agent identifier
     * @param diagnosisId      the diagnosis identifier
     * @return true if such a disease exists, false otherwise
     */
    boolean existsByDiseaseNameAndCausativeAgentIdAndDiagnosisId(
            String diseaseName, CausativeAgentId causativeAgentId, DiagnosisId diagnosisId);
}
