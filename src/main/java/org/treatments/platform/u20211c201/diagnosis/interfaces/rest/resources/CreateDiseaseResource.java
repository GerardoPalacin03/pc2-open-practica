package org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Create disease resource. The diagnosis identifier is not part of the body because it comes from the request path.
 *
 * @author Apellidos, Nombres
 */
@Schema(
    name = "CreateDiseaseRequest",
    description = "Request payload for creating a new disease. The diagnosis id is taken from the request path.",
    example = "{\"diseaseName\": \"Influenza\", \"category\": \"RESPIRATORY\", \"causativeAgentId\": 1, \"commonSymptomsId\": 2, \"transmissionMethodId\": 3, \"preventionMethods\": \"Annual vaccination\", \"mortalityRatePercent\": 0.05, \"incidenceRatePer100k\": 1200}"
)
public record CreateDiseaseResource(
    @Schema(description = "Disease name", example = "Influenza", minLength = 1, maxLength = 80)
    String diseaseName,

    @Schema(
        description = "Category name. It must match one of the existing categories.",
        example = "RESPIRATORY",
        allowableValues = {"INFECTIOUS", "CARDIOVASCULAR", "GASTROINTESTINAL", "MUSCULOSKELETAL",
                "NEUROLOGICAL", "RESPIRATORY", "MENTAL", "IMMUNE"}
    )
    String category,

    @Schema(description = "Causative agent identifier. It must be greater than zero.", example = "1", minimum = "1")
    Long causativeAgentId,

    @Schema(description = "Common symptoms identifier. It must be greater than zero.", example = "2", minimum = "1")
    Long commonSymptomsId,

    @Schema(description = "Transmission method identifier. It must be greater than zero.", example = "3", minimum = "1")
    Long transmissionMethodId,

    @Schema(description = "Prevention methods", example = "Annual vaccination", minLength = 1, maxLength = 50)
    String preventionMethods,

    @Schema(description = "Mortality rate, a value between 0.0 and 1.0", example = "0.05", minimum = "0.0", maximum = "1.0")
    Float mortalityRatePercent,

    @Schema(description = "Incidence rate per 100,000 people", example = "1200")
    Integer incidenceRatePer100k
) {
}
