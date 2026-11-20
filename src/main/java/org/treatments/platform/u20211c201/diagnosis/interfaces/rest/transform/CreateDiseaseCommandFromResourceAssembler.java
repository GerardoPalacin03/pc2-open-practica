package org.treatments.platform.u20211c201.diagnosis.interfaces.rest.transform;

import org.treatments.platform.u20211c201.diagnosis.domain.model.commands.CreateDiseaseCommand;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.Category;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CommonSymptomsId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.TransmissionMethodId;
import org.treatments.platform.u20211c201.diagnosis.interfaces.rest.resources.CreateDiseaseResource;

/**
 * Assembler to convert a CreateDiseaseResource to a CreateDiseaseCommand.
 *
 * @author Apellidos, Nombres
 */
public class CreateDiseaseCommandFromResourceAssembler {
    /**
     * Converts a CreateDiseaseResource and the diagnosis id of the request path to a CreateDiseaseCommand.
     *
     * @param diagnosisId The diagnosis id taken from the request path.
     * @param resource    The {@link CreateDiseaseResource} resource to convert.
     * @return The {@link CreateDiseaseCommand} command that results from the conversion.
     * @throws IllegalArgumentException if the category name is not valid or any identifier is null or not positive.
     */
    public static CreateDiseaseCommand toCommandFromResource(Long diagnosisId, CreateDiseaseResource resource) {
        return new CreateDiseaseCommand(
                resource.diseaseName(),
                Category.fromName(resource.category()),
                new CausativeAgentId(resource.causativeAgentId()),
                new CommonSymptomsId(resource.commonSymptomsId()),
                new TransmissionMethodId(resource.transmissionMethodId()),
                resource.preventionMethods(),
                new DiagnosisId(diagnosisId),
                resource.mortalityRatePercent(),
                resource.incidenceRatePer100k());
    }
}
