package org.treatments.platform.u20211c201.diagnosis.application.internal.queryservices;

import org.treatments.platform.u20211c201.diagnosis.application.queryservices.DiseaseQueryService;
import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.queries.GetDiseaseByIdQuery;
import org.treatments.platform.u20211c201.diagnosis.domain.repositories.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Application service that resolves disease read queries.
 *
 * @author Apellidos, Nombres
 */
@Service
public class DiseaseQueryServiceImpl implements DiseaseQueryService {
    private final DiseaseRepository diseaseRepository;

    /**
     * Constructor.
     *
     * @param diseaseRepository the {@link DiseaseRepository} port
     */
    public DiseaseQueryServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Disease> handle(GetDiseaseByIdQuery query) {
        return diseaseRepository.findById(query.diseaseId());
    }
}
