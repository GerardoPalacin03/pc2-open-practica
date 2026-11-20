package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.adapters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.domain.repositories.DiseaseRepository;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.assemblers.DiseasePersistenceAssembler;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.repositories.DiseasePersistenceRepository;
import org.treatments.platform.u20211c201.shared.infrastructure.events.DomainEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository adapter that bridges the disease domain repository port with Spring Data JPA.
 *
 * @author Apellidos, Nombres
 */
@Repository
public class DiseaseRepositoryImpl implements DiseaseRepository {

    private final DiseasePersistenceRepository diseasePersistenceRepository;
    private final DomainEventPublisher domainEventPublisher;

    /**
     * Constructor.
     *
     * @param diseasePersistenceRepository the Spring Data repository
     * @param domainEventPublisher         the publisher of the domain events registered on the aggregate
     */
    public DiseaseRepositoryImpl(
            DiseasePersistenceRepository diseasePersistenceRepository,
            DomainEventPublisher domainEventPublisher) {
        this.diseasePersistenceRepository = diseasePersistenceRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Disease> findById(Long id) {
        return diseasePersistenceRepository.findById(id).map(DiseasePersistenceAssembler::toDomainFromPersistence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Disease save(Disease disease) {
        var saved = diseasePersistenceRepository.save(DiseasePersistenceAssembler.toPersistenceFromDomain(disease));
        var savedDisease = DiseasePersistenceAssembler.toDomainFromPersistence(saved);
        domainEventPublisher.publishAndClearEvents(disease);
        return savedDisease;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByDiseaseNameAndCausativeAgentIdAndDiagnosisId(
            String diseaseName, CausativeAgentId causativeAgentId, DiagnosisId diagnosisId) {
        return diseasePersistenceRepository.existsByDiseaseNameAndCausativeAgentIdAndDiagnosisId(
                diseaseName, causativeAgentId, diagnosisId);
    }
}
