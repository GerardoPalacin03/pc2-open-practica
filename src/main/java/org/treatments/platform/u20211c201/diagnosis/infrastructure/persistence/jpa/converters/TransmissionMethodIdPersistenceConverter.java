package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.TransmissionMethodId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts transmission method IDs between the domain model and persistence column values.
 *
 * @author Apellidos, Nombres
 */
@Converter(autoApply = false)
public class TransmissionMethodIdPersistenceConverter implements AttributeConverter<TransmissionMethodId, Long> {

    /**
     * Converts the value object to the value stored in the column.
     *
     * @param attribute the transmission method ID value object
     * @return the numeric identifier, or null if the attribute is null
     */
    @Override
    public Long convertToDatabaseColumn(TransmissionMethodId attribute) {
        return attribute == null ? null : attribute.transmissionMethodId();
    }

    /**
     * Converts the value stored in the column to the value object.
     *
     * @param dbData the numeric identifier
     * @return the transmission method ID value object, or null if the column value is null
     */
    @Override
    public TransmissionMethodId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new TransmissionMethodId(dbData);
    }
}
