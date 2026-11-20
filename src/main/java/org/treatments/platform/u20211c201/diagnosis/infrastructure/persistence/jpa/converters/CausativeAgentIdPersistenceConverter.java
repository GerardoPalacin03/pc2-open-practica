package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CausativeAgentId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts causative agent IDs between the domain model and persistence column values.
 *
 * @author Apellidos, Nombres
 */
@Converter(autoApply = false)
public class CausativeAgentIdPersistenceConverter implements AttributeConverter<CausativeAgentId, Long> {

    /**
     * Converts the value object to the value stored in the column.
     *
     * @param attribute the causative agent ID value object
     * @return the numeric identifier, or null if the attribute is null
     */
    @Override
    public Long convertToDatabaseColumn(CausativeAgentId attribute) {
        return attribute == null ? null : attribute.causativeAgentId();
    }

    /**
     * Converts the value stored in the column to the value object.
     *
     * @param dbData the numeric identifier
     * @return the causative agent ID value object, or null if the column value is null
     */
    @Override
    public CausativeAgentId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new CausativeAgentId(dbData);
    }
}
