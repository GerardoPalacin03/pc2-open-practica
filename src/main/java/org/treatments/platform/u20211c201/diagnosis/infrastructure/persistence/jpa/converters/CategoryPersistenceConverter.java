package org.treatments.platform.u20211c201.diagnosis.infrastructure.persistence.jpa.converters;

import org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts categories between the domain enumeration and their numeric identifier (1 to 8) stored in the database.
 *
 * @author Apellidos, Nombres
 */
@Converter(autoApply = false)
public class CategoryPersistenceConverter implements AttributeConverter<Category, Integer> {

    /**
     * Converts the category to the numeric value stored in the column.
     *
     * @param attribute the category
     * @return the numeric identifier of the category, or null if the attribute is null
     */
    @Override
    public Integer convertToDatabaseColumn(Category attribute) {
        return attribute == null ? null : attribute.getId();
    }

    /**
     * Converts the numeric value stored in the column to the category.
     *
     * @param dbData the numeric identifier of the category
     * @return the matching category, or null if the column value is null
     */
    @Override
    public Category convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : Category.fromId(dbData);
    }
}
