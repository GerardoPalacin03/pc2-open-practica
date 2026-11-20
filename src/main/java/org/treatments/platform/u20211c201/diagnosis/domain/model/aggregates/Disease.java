package org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates;

import org.treatments.platform.u20211c201.diagnosis.domain.model.commands.CreateDiseaseCommand;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.Category;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CommonSymptomsId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.TransmissionMethodId;
import org.treatments.platform.u20211c201.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

/**
 * Disease aggregate root.
 *
 * <p>
 * Represents a disease registered in the Open Treatment database. A disease is classified in exactly one
 * {@link Category} and references, by identity, its causative agent, common symptoms, transmission method
 * and diagnosis, which belong to other bounded contexts or aggregates.
 * </p>
 *
 * @author Apellidos, Nombres
 */
@Getter
public class Disease extends AbstractDomainAggregateRoot<Disease> {
    /**
     * The unique identifier of the disease. It is null until the disease is persisted.
     */
    private Long id;

    /**
     * The name of the disease.
     */
    private String diseaseName;

    /**
     * The category of the disease.
     */
    private Category category;

    /**
     * The identifier of the causative agent.
     */
    private CausativeAgentId causativeAgentId;

    /**
     * The identifier of the common symptoms.
     */
    private CommonSymptomsId commonSymptomsId;

    /**
     * The identifier of the transmission method.
     */
    private TransmissionMethodId transmissionMethodId;

    /**
     * The prevention methods.
     */
    private String preventionMethods;

    /**
     * The identifier of the diagnosis.
     */
    private DiagnosisId diagnosisId;

    /**
     * The mortality rate, between 0.0 and 1.0.
     */
    private Float mortalityRatePercent;

    /**
     * The incidence rate per 100,000 people.
     */
    private Integer incidenceRatePer100k;

    /**
     * Default constructor required by frameworks. It does not produce a valid disease by itself.
     */
    protected Disease() {
    }

    /**
     * Creates a new disease from a {@link CreateDiseaseCommand}.
     *
     * @param command The command carrying the already validated data of the disease.
     */
    public Disease(CreateDiseaseCommand command) {
        this.diseaseName = command.diseaseName();
        this.category = command.category();
        this.causativeAgentId = command.causativeAgentId();
        this.commonSymptomsId = command.commonSymptomsId();
        this.transmissionMethodId = command.transmissionMethodId();
        this.preventionMethods = command.preventionMethods();
        this.diagnosisId = command.diagnosisId();
        this.mortalityRatePercent = command.mortalityRatePercent();
        this.incidenceRatePer100k = command.incidenceRatePer100k();
    }

    /**
     * Reconstitutes an existing disease from persistence state.
     *
     * @param id                   The unique identifier.
     * @param diseaseName          The name of the disease.
     * @param category             The category of the disease.
     * @param causativeAgentId     The causative agent identifier.
     * @param commonSymptomsId     The common symptoms identifier.
     * @param transmissionMethodId The transmission method identifier.
     * @param preventionMethods    The prevention methods.
     * @param diagnosisId          The diagnosis identifier.
     * @param mortalityRatePercent The mortality rate.
     * @param incidenceRatePer100k The incidence rate per 100,000 people.
     * @return The reconstituted Disease instance.
     */
    public static Disease reconstitute(
            Long id,
            String diseaseName,
            Category category,
            CausativeAgentId causativeAgentId,
            CommonSymptomsId commonSymptomsId,
            TransmissionMethodId transmissionMethodId,
            String preventionMethods,
            DiagnosisId diagnosisId,
            Float mortalityRatePercent,
            Integer incidenceRatePer100k) {
        var disease = new Disease();
        disease.id = id;
        disease.diseaseName = diseaseName;
        disease.category = category;
        disease.causativeAgentId = causativeAgentId;
        disease.commonSymptomsId = commonSymptomsId;
        disease.transmissionMethodId = transmissionMethodId;
        disease.preventionMethods = preventionMethods;
        disease.diagnosisId = diagnosisId;
        disease.mortalityRatePercent = mortalityRatePercent;
        disease.incidenceRatePer100k = incidenceRatePer100k;
        return disease;
    }
}
