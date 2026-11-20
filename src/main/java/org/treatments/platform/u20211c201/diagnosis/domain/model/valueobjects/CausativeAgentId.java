package org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects;

import org.jspecify.annotations.NullMarked;

/**
 * Value object representing a causative agent identifier.
 *
 * <p>
 * The identifier belongs to an aggregate that lives in another bounded context (CausativeAgent),
 * so within the diagnosis context it is referenced strictly by identity.
 * </p>
 *
 * @param causativeAgentId The causative agent identifier. Cannot be null or less than or equal to zero.
 * @author Apellidos, Nombres
 */
@NullMarked
public record CausativeAgentId(Long causativeAgentId) {

    /**
     * Compact constructor for {@link CausativeAgentId}.
     *
     * @param causativeAgentId The causative agent identifier.
     * @throws IllegalArgumentException if {@code causativeAgentId} is null or less than or equal to zero.
     */
    public CausativeAgentId {
        if (causativeAgentId == null || causativeAgentId <= 0) {
            throw new IllegalArgumentException("Causative agent id cannot be null or less than or equal to zero");
        }
    }
}
