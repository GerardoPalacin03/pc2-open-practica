package org.treatments.platform.u20211c201.diagnosis.domain.model.queries;

/**
 * Query to get a disease by id.
 *
 * @param diseaseId The ID of the disease to retrieve. Cannot be null or less than or equal to 0.
 * @author Apellidos, Nombres
 */
public record GetDiseaseByIdQuery(Long diseaseId) {
    /**
     * Compact constructor for GetDiseaseByIdQuery.
     * Validates that the diseaseId is not null and is greater than 0.
     *
     * @throws IllegalArgumentException if diseaseId is null or less than or equal to 0.
     */
    public GetDiseaseByIdQuery {
        if (diseaseId == null || diseaseId <= 0) throw new IllegalArgumentException("Disease id is required.");
    }
}
