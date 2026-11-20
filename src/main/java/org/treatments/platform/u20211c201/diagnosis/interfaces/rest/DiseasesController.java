package org.treatments.platform.u20211c201.diagnosis.interfaces.rest;

import org.treatments.platform.u20211c201.diagnosis.application.commandservices.DiseaseCommandService;
import org.treatments.platform.u20211c201.diagnosis.application.queryservices.DiseaseQueryService;
import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.queries.GetDiseaseByIdQuery;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources.CreateDiseaseResource;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources.DiseaseResource;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.transform.CreateDiseaseCommandFromResourceAssembler;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.transform.DiseaseResourceFromEntityAssembler;
import org.treatments.platform.u20211c201.shared.application.result.ApplicationError;
import org.treatments.platform.u20211c201.shared.application.result.Result;
import org.treatments.platform.u20211c201.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller that exposes the diseases of a diagnosis.
 *
 * @author Apellidos, Nombres
 */
@RestController
@RequestMapping(value = "/api/v1/treatments/{diagnosisId}/diseases", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Diseases", description = "Disease management endpoints")
public class DiseasesController {
    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;

    /**
     * Constructor
     *
     * @param diseaseCommandService The {@link DiseaseCommandService} instance
     * @param diseaseQueryService   The {@link DiseaseQueryService} instance
     */
    public DiseasesController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
    }

    /**
     * Create a new disease for a diagnosis
     *
     * @param diagnosisId The diagnosis id taken from the request path
     * @param resource    The {@link CreateDiseaseResource} instance
     * @return The {@link DiseaseResource} resource for the created disease
     */
    @PostMapping
    @Operation(
            summary = "Create a new disease",
            description = "Creates a new disease for the given diagnosis. The disease name must be unique for the same causative agent and diagnosis.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Disease created successfully",
                    content = @Content(schema = @Schema(implementation = DiseaseResource.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "A disease with the same name, causative agent and diagnosis already exists")
    })
    public ResponseEntity<?> createDisease(
            @PathVariable
            @Parameter(description = "Unique diagnosis identifier", example = "1", required = true)
            Long diagnosisId,
            @RequestBody CreateDiseaseResource resource
    ) {
        var createDiseaseCommand = CreateDiseaseCommandFromResourceAssembler.toCommandFromResource(diagnosisId, resource);
        var result = diseaseCommandService.handle(createDiseaseCommand)
                .flatMap(diseaseId -> diseaseQueryService.handle(new GetDiseaseByIdQuery(diseaseId))
                        .<Result<Disease, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Disease", diseaseId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DiseaseResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }
}
