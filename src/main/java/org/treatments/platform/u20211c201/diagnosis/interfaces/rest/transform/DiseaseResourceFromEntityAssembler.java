package org.treatments.platform.u20211c201.diagnosis.interfaces.rest.transform;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources.DiseaseResource;

/**
 * Assembler to convert a Disease entity to a DiseaseResource.
 *
 * @author Apellidos, Nombres
 */
public class DiseaseResourceFromEntityAssembler {
    /**
     * Converts a Disease entity to a DiseaseResource.
     *
     * @param entity The {@link Disease} entity to convert.
     * @return The {@link DiseaseResource} resource that results from the conversion.
     */
    public static DiseaseResource toResourceFromEntity(Disease entity) {
        return new DiseaseResource(
                entity.getId(),
                entity.getDiseaseName(),
                entity.getCategory().name(),
                entity.getCausativeAgentId().causativeAgentId(),
                entity.getCommonSymptomsId().commonSymptomsId(),
                entity.getTransmissionMethodId().transmissionMethodId(),
                entity.getPreventionMethods(),
                entity.getDiagnosisId().diagnosisId(),
                entity.getMortalityRatePercent(),
                entity.getIncidenceRatePer100k());
    }
}
