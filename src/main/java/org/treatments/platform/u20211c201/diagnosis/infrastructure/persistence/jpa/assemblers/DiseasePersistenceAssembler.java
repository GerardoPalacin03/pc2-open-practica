package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.assemblers;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.entities.DiseasePersistenceEntity;

/**
 * Static assembler between disease domain and persistence representations.
 *
 * @author Apellidos, Nombres
 */
public final class DiseasePersistenceAssembler {

    private DiseasePersistenceAssembler() {
    }

    /**
     * Converts a persistence entity to the domain aggregate.
     *
     * @param entity the persistence entity
     * @return the reconstituted {@link Disease}, or null if the entity is null
     */
    public static Disease toDomainFromPersistence(DiseasePersistenceEntity entity) {
        if (entity == null) return null;
        return Disease.reconstitute(
                entity.getId(),
                entity.getDiseaseName(),
                entity.getCategory(),
                entity.getCausativeAgentId(),
                entity.getCommonSymptomsId(),
                entity.getTransmissionMethodId(),
                entity.getPreventionMethods(),
                entity.getDiagnosisId(),
                entity.getMortalityRatePercent(),
                entity.getIncidenceRatePer100k());
    }

    /**
     * Converts a domain aggregate to a persistence entity.
     *
     * @param disease the domain aggregate
     * @return the persistence entity, or null if the disease is null
     */
    public static DiseasePersistenceEntity toPersistenceFromDomain(Disease disease) {
        if (disease == null) return null;
        var entity = new DiseasePersistenceEntity();
        // Only set ID if the disease is being updated (has a non-null ID)
        // For new diseases, leave ID null to allow JPA to generate it
        if (disease.getId() != null) {
            entity.setId(disease.getId());
        }
        entity.setDiseaseName(disease.getDiseaseName());
        entity.setCategory(disease.getCategory());
        entity.setCausativeAgentId(disease.getCausativeAgentId());
        entity.setCommonSymptomsId(disease.getCommonSymptomsId());
        entity.setTransmissionMethodId(disease.getTransmissionMethodId());
        entity.setPreventionMethods(disease.getPreventionMethods());
        entity.setDiagnosisId(disease.getDiagnosisId());
        entity.setMortalityRatePercent(disease.getMortalityRatePercent());
        entity.setIncidenceRatePer100k(disease.getIncidenceRatePer100k());
        return entity;
    }
}
