package org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects;

import org.jspecify.annotations.NullMarked;

/**
 * Value object representing a common symptoms identifier.
 *
 * <p>
 * The identifier belongs to an aggregate that lives in another bounded context (CommonSymptoms),
 * so within the diagnosis context it is referenced strictly by identity.
 * </p>
 *
 * @param commonSymptomsId The common symptoms identifier. Cannot be null or less than or equal to zero.
 * @author Apellidos, Nombres
 */
@NullMarked
public record CommonSymptomsId(Long commonSymptomsId) {

    /**
     * Compact constructor for {@link CommonSymptomsId}.
     *
     * @param commonSymptomsId The common symptoms identifier.
     * @throws IllegalArgumentException if {@code commonSymptomsId} is null or less than or equal to zero.
     */
    public CommonSymptomsId {
        if (commonSymptomsId == null || commonSymptomsId <= 0) {
            throw new IllegalArgumentException("Common symptoms id cannot be null or less than or equal to zero");
        }
    }
}
