package org.treatments.platform.u20211c201.diagnosis.application.queryservices;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.queries.GetDiseaseByIdQuery;

import java.util.Optional;

/**
 * Application service contract for disease read queries.
 *
 * @author Apellidos, Nombres
 */
public interface DiseaseQueryService {
    /**
     * Handles retrieval of a disease by id.
     *
     * @param query disease-id query
     * @return matching disease, if found
     * @see GetDiseaseByIdQuery
     */
    Optional<Disease> handle(GetDiseaseByIdQuery query);
}
