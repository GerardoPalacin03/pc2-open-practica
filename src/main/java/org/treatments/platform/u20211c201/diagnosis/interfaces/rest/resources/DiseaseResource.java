package org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Disease resource.
 *
 * @author Apellidos, Nombres
 */
@Schema(
    name = "DiseaseResponse",
    description = "Disease information response",
    example = "{\"id\": 1, \"diseaseName\": \"Influenza\", \"category\": \"RESPIRATORY\", \"causativeAgentId\": 1, \"commonSymptomsId\": 2, \"transmissionMethodId\": 3, \"preventionMethods\": \"Annual vaccination\", \"diagnosisId\": 1, \"mortalityRatePercent\": 0.05, \"incidenceRatePer100k\": 1200}"
)
public record DiseaseResource(
    @Schema(description = "Disease unique identifier", example = "1")
    Long id,

    @Schema(description = "Disease name", example = "Influenza")
    String diseaseName,

    @Schema(description = "Category name", example = "RESPIRATORY")
    String category,

    @Schema(description = "Causative agent identifier", example = "1")
    Long causativeAgentId,

    @Schema(description = "Common symptoms identifier", example = "2")
    Long commonSymptomsId,

    @Schema(description = "Transmission method identifier", example = "3")
    Long transmissionMethodId,

    @Schema(description = "Prevention methods", example = "Annual vaccination")
    String preventionMethods,

    @Schema(description = "Diagnosis identifier", example = "1")
    Long diagnosisId,

    @Schema(description = "Mortality rate, a value between 0.0 and 1.0", example = "0.05")
    Float mortalityRatePercent,

    @Schema(description = "Incidence rate per 100,000 people", example = "1200")
    Integer incidenceRatePer100k
) {
}
