package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.entities;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.Category;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CommonSymptomsId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.TransmissionMethodId;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters.CausativeAgentIdPersistenceConverter;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters.CategoryPersistenceConverter;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters.CommonSymptomsIdPersistenceConverter;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters.DiagnosisIdPersistenceConverter;
import org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters.TransmissionMethodIdPersistenceConverter;
import org.treatments.platform.u20211c201.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for diseases.
 *
 * <p>
 * It is stored in the {@code diseases} table of the {@code treatments} schema. The identifier and the
 * creation and last-update dates are inherited from {@link AuditableAbstractPersistenceEntity}.
 * </p>
 *
 * @author Apellidos, Nombres
 */
@Entity
@Table(
        name = "diseases",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_diseases_disease_name_causative_agent_id_diagnosis_id",
                columnNames = {"disease_name", "causative_agent_id", "diagnosis_id"}))
@Getter
@Setter
@NoArgsConstructor
public class DiseasePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "disease_name", nullable = false, length = 80)
    private String diseaseName;

    @Convert(converter = CategoryPersistenceConverter.class)
    @Column(name = "category", nullable = false)
    private Category category;

    @Convert(converter = CausativeAgentIdPersistenceConverter.class)
    @Column(name = "causative_agent_id", nullable = false)
    private CausativeAgentId causativeAgentId;

    @Convert(converter = CommonSymptomsIdPersistenceConverter.class)
    @Column(name = "common_symptoms_id", nullable = false)
    private CommonSymptomsId commonSymptomsId;

    @Convert(converter = TransmissionMethodIdPersistenceConverter.class)
    @Column(name = "transmission_method_id", nullable = false)
    private TransmissionMethodId transmissionMethodId;

    @Column(name = "prevention_methods", nullable = false, length = 50)
    private String preventionMethods;

    @Convert(converter = DiagnosisIdPersistenceConverter.class)
    @Column(name = "diagnosis_id", nullable = false)
    private DiagnosisId diagnosisId;

    @Column(name = "mortality_rate_percent", nullable = false)
    private Float mortalityRatePercent;

    @Column(name = "incidence_rate_per100k", nullable = false)
    private Integer incidenceRatePer100k;
}
