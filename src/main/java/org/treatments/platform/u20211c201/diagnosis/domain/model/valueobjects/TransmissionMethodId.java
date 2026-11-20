package org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects;

import org.jspecify.annotations.NullMarked;

/**
 * Value object representing a transmission method identifier.
 *
 * <p>
 * The identifier belongs to an aggregate that lives in another bounded context (TransmissionMethod),
 * so within the diagnosis context it is referenced strictly by identity.
 * </p>
 *
 * @param transmissionMethodId The transmission method identifier. Cannot be null or less than or equal to zero.
 * @author Apellidos, Nombres
 */
@NullMarked
public record TransmissionMethodId(Long transmissionMethodId) {

    /**
     * Compact constructor for {@link TransmissionMethodId}.
     *
     * @param transmissionMethodId The transmission method identifier.
     * @throws IllegalArgumentException if {@code transmissionMethodId} is null or less than or equal to zero.
     */
    public TransmissionMethodId {
        if (transmissionMethodId == null || transmissionMethodId <= 0) {
            throw new IllegalArgumentException("Transmission method id cannot be null or less than or equal to zero");
        }
    }
}
