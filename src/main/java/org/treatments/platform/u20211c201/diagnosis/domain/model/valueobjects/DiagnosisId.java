package org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects;

import org.jspecify.annotations.NullMarked;

/**
 * Value object representing a diagnosis identifier.
 *
 * <p>
 * The identifier belongs to an aggregate that lives in another bounded context (Diagnosis),
 * so within the diagnosis context it is referenced strictly by identity.
 * </p>
 *
 * @param diagnosisId The diagnosis identifier. Cannot be null or less than or equal to zero.
 * @author Apellidos, Nombres
 */
@NullMarked
public record DiagnosisId(Long diagnosisId) {

    /**
     * Compact constructor for {@link DiagnosisId}.
     *
     * @param diagnosisId The diagnosis identifier.
     * @throws IllegalArgumentException if {@code diagnosisId} is null or less than or equal to zero.
     */
    public DiagnosisId {
        if (diagnosisId == null || diagnosisId <= 0) {
            throw new IllegalArgumentException("Diagnosis id cannot be null or less than or equal to zero");
        }
    }
}
