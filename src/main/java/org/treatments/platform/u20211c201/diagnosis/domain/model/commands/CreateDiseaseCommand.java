package org.treatments.platform.u20211c201.diagnosis.domain.model.commands;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.Category;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CommonSymptomsId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.TransmissionMethodId;

/**
 * Command to create a disease.
 *
 * @param diseaseName          The name of the disease. Cannot be null, blank or longer than 80 characters.
 * @param category             The category of the disease. Cannot be null.
 * @param causativeAgentId     The causative agent identifier. Cannot be null.
 * @param commonSymptomsId     The common symptoms identifier. Cannot be null.
 * @param transmissionMethodId The transmission method identifier. Cannot be null.
 * @param preventionMethods    The prevention methods. Cannot be null, blank or longer than 50 characters.
 * @param diagnosisId          The diagnosis identifier. Cannot be null.
 * @param mortalityRatePercent The mortality rate. Cannot be null and must be between 0.0 and 1.0.
 * @param incidenceRatePer100k The incidence rate per 100,000 people. Cannot be null.
 * @author Apellidos, Nombres
 */
public record CreateDiseaseCommand(
        String diseaseName,
        Category category,
        CausativeAgentId causativeAgentId,
        CommonSymptomsId commonSymptomsId,
        TransmissionMethodId transmissionMethodId,
        String preventionMethods,
        DiagnosisId diagnosisId,
        Float mortalityRatePercent,
        Integer incidenceRatePer100k) {

    /** Maximum length allowed for the disease name. */
    public static final int MAX_DISEASE_NAME_LENGTH = 80;

    /** Maximum length allowed for the prevention methods. */
    public static final int MAX_PREVENTION_METHODS_LENGTH = 50;

    /**
     * Compact constructor for CreateDiseaseCommand.
     * Validates that every attribute is present and satisfies its constraints.
     *
     * @throws IllegalArgumentException if any attribute is missing or violates its constraints.
     */
    public CreateDiseaseCommand {
        if (diseaseName == null || diseaseName.isBlank()) {
            throw new IllegalArgumentException("Disease name cannot be null or blank");
        }
        if (diseaseName.length() > MAX_DISEASE_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Disease name cannot be longer than %d characters".formatted(MAX_DISEASE_NAME_LENGTH));
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (causativeAgentId == null) {
            throw new IllegalArgumentException("Causative agent id cannot be null");
        }
        if (commonSymptomsId == null) {
            throw new IllegalArgumentException("Common symptoms id cannot be null");
        }
        if (transmissionMethodId == null) {
            throw new IllegalArgumentException("Transmission method id cannot be null");
        }
        if (preventionMethods == null || preventionMethods.isBlank()) {
            throw new IllegalArgumentException("Prevention methods cannot be null or blank");
        }
        if (preventionMethods.length() > MAX_PREVENTION_METHODS_LENGTH) {
            throw new IllegalArgumentException(
                    "Prevention methods cannot be longer than %d characters".formatted(MAX_PREVENTION_METHODS_LENGTH));
        }
        if (diagnosisId == null) {
            throw new IllegalArgumentException("Diagnosis id cannot be null");
        }
        if (mortalityRatePercent == null || mortalityRatePercent.isNaN()
                || mortalityRatePercent < 0.0f || mortalityRatePercent > 1.0f) {
            throw new IllegalArgumentException("Mortality rate percent is required and must be between 0.0 and 1.0");
        }
        if (incidenceRatePer100k == null) {
            throw new IllegalArgumentException("Incidence rate per 100k cannot be null");
        }
    }
}
