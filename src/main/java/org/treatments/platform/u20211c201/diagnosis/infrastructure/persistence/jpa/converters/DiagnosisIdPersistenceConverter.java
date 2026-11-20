package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.DiagnosisId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts diagnosis IDs between the domain model and persistence column values.
 *
 * @author Apellidos, Nombres
 */
@Converter(autoApply = false)
public class DiagnosisIdPersistenceConverter implements AttributeConverter<DiagnosisId, Long> {

    /**
     * Converts the value object to the value stored in the column.
     *
     * @param attribute the diagnosis ID value object
     * @return the numeric identifier, or null if the attribute is null
     */
    @Override
    public Long convertToDatabaseColumn(DiagnosisId attribute) {
        return attribute == null ? null : attribute.diagnosisId();
    }

    /**
     * Converts the value stored in the column to the value object.
     *
     * @param dbData the numeric identifier
     * @return the diagnosis ID value object, or null if the column value is null
     */
    @Override
    public DiagnosisId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new DiagnosisId(dbData);
    }
}
