package org.treatments.platform.u20211c201.diagnosis.domain.model.valueobjects;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Enumeration representing the category of a disease.
 *
 * <p>
 * Each category has a numeric identifier, which is the value preserved in the database,
 * and a name, which is the value exchanged through the REST API.
 * </p>
 *
 * @author Apellidos, Nombres
 */
public enum Category {
    /** Diseases caused by pathogens such as bacteria, viruses or parasites. */
    INFECTIOUS(1),
    /** Diseases of the heart and blood vessels. */
    CARDIOVASCULAR(2),
    /** Diseases of the digestive system. */
    GASTROINTESTINAL(3),
    /** Diseases of muscles, bones and joints. */
    MUSCULOSKELETAL(4),
    /** Diseases of the nervous system. */
    NEUROLOGICAL(5),
    /** Diseases of the respiratory system. */
    RESPIRATORY(6),
    /** Mental health conditions. */
    MENTAL(7),
    /** Diseases of the immune system. */
    IMMUNE(8);

    private final int id;

    Category(int id) {
        this.id = id;
    }

    /**
     * Gets the numeric identifier of the category.
     *
     * @return the numeric identifier, from 1 to 8
     */
    public int getId() {
        return id;
    }

    /**
     * Resolves a category from its name. The name must match exactly the name of one of the categories.
     *
     * @param name the category name, for example {@code RESPIRATORY}
     * @return the matching category
     * @throws IllegalArgumentException if the name is null, blank or does not match any category
     */
    public static Category fromName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
        return Arrays.stream(values())
                .filter(category -> category.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category '%s' is not valid. Valid categories: %s".formatted(
                                name,
                                Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", ")))));
    }

    /**
     * Resolves a category from its numeric identifier.
     *
     * @param id the numeric identifier, from 1 to 8
     * @return the matching category
     * @throws IllegalArgumentException if the identifier does not match any category
     */
    public static Category fromId(int id) {
        return Arrays.stream(values())
                .filter(category -> category.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category id '%d' is not valid".formatted(id)));
    }
}
