package org.treatments.platform.u20211c201.diagnosis.application.commandservices;

import org.treatments.platform.u20211c201.diagnosis.domain.model.aggregates.Disease;
import org.treatments.platform.u20211c201.diagnosis.domain.model.commands.CreateDiseaseCommand;
import org.treatments.platform.u20211c201.shared.application.result.ApplicationError;
import org.treatments.platform.u20211c201.shared.application.result.Result;

/**
 * Application service contract for commands over the {@link Disease} aggregate.
 *
 * @author Apellidos, Nombres
 */
public interface DiseaseCommandService {
    /**
     * Handles disease creation.
     *
     * @param command command containing the data of the new disease
     * @return created disease identifier or an application error
     * @see CreateDiseaseCommand
     */
    Result<Long, ApplicationError> handle(CreateDiseaseCommand command);
}
