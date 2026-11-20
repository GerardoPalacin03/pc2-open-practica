package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.CommonSymptomsId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts common symptoms IDs between the domain model and persistence column values.
 *
 * @author Apellidos, Nombres
 */
@Converter(autoApply = false)
public class CommonSymptomsIdPersistenceConverter implements AttributeConverter<CommonSymptomsId, Long> {

    /**
     * Converts the value object to the value stored in the column.
     *
     * @param attribute the common symptoms ID value object
     * @return the numeric identifier, or null if the attribute is null
     */
    @Override
    public Long convertToDatabaseColumn(CommonSymptomsId attribute) {
        return attribute == null ? null : attribute.commonSymptomsId();
    }

    /**
     * Converts the value stored in the column to the value object.
     *
     * @param dbData the numeric identifier
     * @return the common symptoms ID value object, or null if the column value is null
     */
    @Override
    public CommonSymptomsId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new CommonSymptomsId(dbData);
    }
}
